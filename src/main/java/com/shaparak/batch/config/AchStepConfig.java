package com.shaparak.batch.config;

import com.shaparak.batch.dto.xml.CdtTrfTxInfDto;
import com.shaparak.batch.processor.AchRecordProcessor;
import com.shaparak.batch.writer.MyCustomWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.support.SynchronizedItemStreamReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class AchStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Value("${thread.count.batch.task}")
    private int threadCount;

    @Value("${unzipped.input.file.destination.path}")
    private String unzippedInputFilePath;


    @Bean
    public ResourceAwareItemReaderItemStream<CdtTrfTxInfDto> xmlReader() {
        Jaxb2Marshaller AchMarshaller = new Jaxb2Marshaller();
        AchMarshaller.setClassesToBeBound(CdtTrfTxInfDto.class);

        return new StaxEventItemReaderBuilder<CdtTrfTxInfDto>()
                .name("AchReader")
//                .resource(new FileSystemResource(files[0].getAbsolutePath()))
                .addFragmentRootElements("CdtTrfTxInf")
                .unmarshaller(AchMarshaller)
                .build();
    }


    @Bean
    public MultiResourceItemReader<CdtTrfTxInfDto> multiResourceItemReader() {
        String path =  unzippedInputFilePath + "/ACH";
        File[] files = new File(path).listFiles();
        List<Resource> resources = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            resources.add(new FileSystemResource(files[i].getAbsolutePath()));
        }
        Resource[] resourcesArray = new Resource[resources.size()];
        resources.toArray(resourcesArray);

        MultiResourceItemReader<CdtTrfTxInfDto> reader = new MultiResourceItemReader<>();
        reader.setDelegate(xmlReader());
        reader.setResources(resourcesArray);
        return reader;
    }


    @Bean
    public AchRecordProcessor processor() {
        return new AchRecordProcessor();
    }


    @Bean
    public MyCustomWriter<CdtTrfTxInfDto> writer() {
        return new MyCustomWriter<>();
    }


    @Bean
    public Step achStep() throws Exception {
        return stepBuilderFactory.get("batchStep").<CdtTrfTxInfDto, CdtTrfTxInfDto>chunk(1000)
                .reader(multiResourceItemReader())
                .processor(processor())
                .writer(writer())

//                .taskExecutor(taskExecutor()).throttleLimit(threadCount)
                .build();
    }


    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(threadCount);
        return asyncTaskExecutor;
    }


}