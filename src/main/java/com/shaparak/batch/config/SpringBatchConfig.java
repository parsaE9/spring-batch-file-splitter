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
                    .from(initStep())
                    .next(batchStepConfig.batchStep())
                    .next(achStepConfig.achMasterStep())
                    .next(deleteExtractedInputStep())
                    .next(rowNumberStep())
                    .next(logStep())
                    .next(deleteExtraOutputStep())
                    .next(zipOutputStep())
                    .end();
        } else if (zipOutputFiles && createBatch) {
            return new FlowBuilder<Flow>("JobFlow")
                    .from(initStep())
                    .next(batchStepConfig.batchStep())
                    .next(achStepConfig.achMasterStep())
                    .next(rowNumberStep())
                    .next(logStep())
                    .next(deleteExtraOutputStep())
                    .next(zipOutputStep())
                    .end();
        } else if (deleteExtractedInput && createBatch) {
            return new FlowBuilder<Flow>("JobFlow")
                    .from(initStep())
                    .next(batchStepConfig.batchStep())
                    .next(achStepConfig.achMasterStep())
                    .next(deleteExtractedInputStep())
                    .next(rowNumberStep())
                    .next(logStep())
                    .next(deleteExtraOutputStep())
                    .end();
        } else if (zipOutputFiles && deleteExtractedInput) {
            return new FlowBuilder<Flow>("JobFlow")
                    .from(initStep())
                    .next(achStepConfig.achMasterStep())
                    .next(deleteExtractedInputStep())
                    .next(logStep())
                    .next(deleteExtraOutputStep())
                    .next(zipOutputStep())
                    .end();
        } else if (createBatch) {
            return new FlowBuilder<Flow>("JobFlow")
                    .from(initStep())
                    .next(batchStepConfig.batchStep())
                    .next(achStepConfig.achMasterStep())
                    .next(rowNumberStep())
                    .next(logStep())
                    .next(deleteExtraOutputStep())
                    .end();
        } else if (deleteExtractedInput) {
            return new FlowBuilder<Flow>("JobFlow")
                    .from(initStep())
                    .next(achStepConfig.achMasterStep())
                    .next(deleteExtractedInputStep())
                    .next(logStep())
                    .next(deleteExtraOutputStep())
                    .end();
        } else if (zipOutputFiles) {
            return new FlowBuilder<Flow>("JobFlow")
                    .from(initStep())
                    .next(achStepConfig.achMasterStep())
                    .next(logStep())
                    .next(deleteExtraOutputStep())
                    .next(zipOutputStep())
                    .end();
        } else {
            return new FlowBuilder<Flow>("JobFlow")
                    .from(initStep())
                    .next(achStepConfig.achMasterStep())
                    .next(logStep())
                    .next(deleteExtraOutputStep())
                    .end();
        }
    }


    @Bean
    public Step deleteExtractedInputStep() {
        return stepBuilderFactory.get("deleteInputStep")
                .tasklet(deleteExtractedInputTasklet())
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
    public Step deleteExtraOutputStep() {
        return stepBuilderFactory.get("deleteExtraOutputStep")
                .tasklet(deleteExtraOutputTasklet())
                .build();
    }

    @Bean
    public Step initStep() {
        return stepBuilderFactory.get("initStep")
                .tasklet(initTasklet())
                .build();
    }


    @Bean
    public Tasklet deleteExtractedInputTasklet() {return new DeleteExtractedInputTasklet();}

    @Bean
    public Tasklet rowNumberTasklet() {
        return new RowNumberTasklet();
    }

    @Bean
    public Tasklet logTasklet() {
        return new LogTasklet();
    }

    @Bean
    public Tasklet zipOutputTasklet() { return new ZipOutputTasklet(); }

    @Bean
    public Tasklet deleteExtraOutputTasklet() {
        return new DeleteExtraOutputTasklet();
    }

    @Bean
    public Tasklet initTasklet() {
        return new InitTasklet();
    }


}