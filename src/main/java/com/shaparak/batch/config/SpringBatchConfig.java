package com.shaparak.batch.config;

import com.shaparak.batch.config.step.AchStepConfig;
import com.shaparak.batch.config.step.BatchStepConfig;
import com.shaparak.batch.tasklet.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired(required = false)
    private BatchStepConfig batchStepConfig;

    @Autowired
    private AchStepConfig achStepConfig;

    @Value("${zip.output.files}")
    private boolean zipOutputFiles;

    @Value("${delete.extracted.input}")
    private boolean deleteExtractedInput;

    @Value("${create.psp.batch-file}")
    private boolean createPspBatch;

    @Value("${create.bank.batch-file}")
    private boolean createBankBatch;


    @Bean
    public Job runJob() throws Exception {
        return jobBuilderFactory.get("ShaparakBatchJob")
                .start(getFlow())
                .end()
                .build();
    }



    private Flow getFlow() throws Exception {
        boolean createBatch = createBankBatch || createPspBatch;

        if (zipOutputFiles && deleteExtractedInput && createBatch) {
            return new FlowBuilder<Flow>("JobFlow")
                    .from(batchStepConfig.batchStep())
                    .next(achStepConfig.achStep())
                    .next(deleteInputStep())
                    .next(rowNumberStep())
                    .next(logStep())
                    .next(zipOutputStep())
                    .next(deleteEmptyDirsStep())
                    .end();
        } else if (zipOutputFiles && createBatch) {
            return new FlowBuilder<Flow>("JobFlow")
                    .from(batchStepConfig.batchStep())
                    .next(achStepConfig.achStep())
                    .next(rowNumberStep())
                    .next(logStep())
                    .next(zipOutputStep())
                    .next(deleteEmptyDirsStep())
                    .end();
        } else if (deleteExtractedInput && createBatch) {
            return new FlowBuilder<Flow>("JobFlow")
                    .from(batchStepConfig.batchStep())
                    .next(achStepConfig.achStep())
                    .next(deleteInputStep())
                    .next(rowNumberStep())
                    .next(logStep())
                    .next(deleteEmptyDirsStep())
                    .end();
        } else if (zipOutputFiles && deleteExtractedInput) {
            return new FlowBuilder<Flow>("JobFlow")
                    .from(achStepConfig.achStep())
                    .next(deleteInputStep())
                    .next(rowNumberStep())
                    .next(logStep())
                    .next(zipOutputStep())
                    .next(deleteEmptyDirsStep())
                    .end();
        } else {
            return new FlowBuilder<Flow>("JobFlow")
                    .from(achStepConfig.achStep())
                    .next(rowNumberStep())
                    .next(logStep())
                    .next(deleteEmptyDirsStep())
                    .end();
        }
    }



    @Bean
    public Step deleteInputStep() {
        return stepBuilderFactory.get("deleteInputStep")
                .tasklet(deleteInputTasklet())
                .build();
    }

    @Bean
    public Step rowNumberStep() {
        return stepBuilderFactory.get("rowNumberStep")
                .tasklet(rowNumberTasklet())
                .build();
    }

    @Bean
    public Step logStep() {
        return stepBuilderFactory.get("logStep")
                .tasklet(logTasklet())
                .build();
    }

    @Bean
    public Step zipOutputStep() {
        return stepBuilderFactory.get("zipOutputStep")
                .tasklet(zipOutputTasklet())
                .build();
    }

    @Bean
    public Step deleteEmptyDirsStep() {
        return stepBuilderFactory.get("deleteEmptyDirsStep")
                .tasklet(deleteEmptyDirsTasklet())
                .build();
    }



    @Bean
    public Tasklet deleteInputTasklet() {
        return new DeleteInputTasklet();
    }

    @Bean
    public Tasklet rowNumberTasklet() {
        return new RowNumberTasklet();
    }

    @Bean
    public Tasklet logTasklet() {
        return new LogTasklet();
    }

    @Bean
    public Tasklet zipOutputTasklet() {
        return new ZipOutputTasklet();
    }

    @Bean
    public Tasklet deleteEmptyDirsTasklet() { return new DeleteEmptyDirsTasklet(); }


//    @Bean
//    public Flow splitFlow() throws Exception {
//        return new FlowBuilder<SimpleFlow>("splitFlow")
//                .split(taskExecutor5())
//                .add(flow1(), flow2())
//                .build();
//    }
//
//    @Bean
//    public Flow flow1() throws Exception {
//        return new FlowBuilder<SimpleFlow>("flow1")
//                .start(batchStepConfig.batchStep())
//                .build();
//
//    }
//
//    @Bean
//    public Flow flow2() throws Exception {
//        return new FlowBuilder<SimpleFlow>("flow2")
//                .start(achStepConfig.achStep())
//                .build();
//    }
//
//    // TODO: work on this
//    // lower number of batchstep threads
//
//    @Bean
//    public TaskExecutor taskExecutor5() {
//        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
//        asyncTaskExecutor.setConcurrencyLimit(500);
//        return asyncTaskExecutor;
//    }


}