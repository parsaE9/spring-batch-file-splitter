package com.shaparak.batch.config;

import com.shaparak.batch.tasklet.DeleteInputTasklet;
import com.shaparak.batch.tasklet.LogTasklet;
import com.shaparak.batch.tasklet.RowNumberTasklet;
import com.shaparak.batch.tasklet.ZipOutputTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private BatchStepConfig batchStepConfig;

    @Autowired
    private AchStepConfig achStepConfig;




    @Bean
    public Job runJob() throws Exception {
        return jobBuilderFactory.get("ShaparakBatchJob")
//                .start(splitFlow())

                .flow(batchStepConfig.batchStep())
                .next(achStepConfig.achStep())
                .next(stepBuilderFactory.get("deleteInputStep").tasklet(deleteInputTasklet()).build())
                .next(stepBuilderFactory.get("rowNumberStep").tasklet(rowNumberTasklet()).build())
                .next(stepBuilderFactory.get("logStep").tasklet(logTasklet()).build())
                .next(stepBuilderFactory.get("zipOutputStep").tasklet(logTasklet()).build())

                .end()
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