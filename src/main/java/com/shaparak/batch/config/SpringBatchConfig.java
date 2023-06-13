package com.shaparak.batch.config;

import com.shaparak.batch.BatchApplication;
import com.shaparak.batch.classifier.BankRecordClassifier;
import com.shaparak.batch.classifier.PspRecordClassifier;
import com.shaparak.batch.dto.BatchRecord;
import com.shaparak.batch.listener.ItemWriteListenerImpl;
import com.shaparak.batch.processor.BatchRecordProcessor;
import com.shaparak.batch.service.UnzipService;
import com.shaparak.batch.writer.BankItemWriter;
import com.shaparak.batch.writer.PspItemWriter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

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
                .flow(batchStepConfig.batchStep())
                .next(achStepConfig.achStep())
                .end()
                .build();
    }


}