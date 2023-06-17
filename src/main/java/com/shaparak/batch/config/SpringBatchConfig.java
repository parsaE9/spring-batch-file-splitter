package com.shaparak.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private BatchStepConfig batchStepConfig;

    @Autowired
    private AchStepConfig achStepConfig;


    @Bean
    public Job runJob() throws Exception {
        return jobBuilderFactory.get("ShaparakBatchJob")
                .start(splitFlow())

//                .flow(batchStepConfig.batchStep())
//                .flow(achStepConfig.achStep())

                .end()
                .build();
    }


    @Bean
    public Flow splitFlow() throws Exception {
        return new FlowBuilder<SimpleFlow>("splitFlow")
                .split(taskExecutor5())
                .add(flow1(), flow2())
                .build();
    }

    @Bean
    public Flow flow1() throws Exception {
        return new FlowBuilder<SimpleFlow>("flow1")
                .start(batchStepConfig.batchStep())
                .build();

    }

    @Bean
    public Flow flow2() throws Exception {
        return new FlowBuilder<SimpleFlow>("flow2")
                .start(achStepConfig.achStep())
                .build();
    }

    // TODO: work on this
    // lower number of batchstep threads

    @Bean
    public TaskExecutor taskExecutor5() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(100);
        return asyncTaskExecutor;
    }



}