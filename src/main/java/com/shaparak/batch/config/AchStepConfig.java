package com.shaparak.batch.config;

import com.shaparak.batch.classifier.ach.AchRecordClassifier;
import com.shaparak.batch.dto.xml.CdtTrfTxInfDto;
import com.shaparak.batch.processor.AchRecordProcessor;
import com.shaparak.batch.writer.ach.AchItemWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
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

    @Autowired
    private AchItemWriter achItemWriter;

    @Value("${thread.count.batch.task}")
    private int threadCount;

    @Value("${unzipped.input.file.destination.path}")
    private String unzippedInputFilePath;


    @Bean
    public ResourceAwareItemReaderItemStream<CdtTrfTxInfDto> achXmlReader() {
        Jaxb2Marshaller AchMarshaller = new Jaxb2Marshaller();
        AchMarshaller.setClassesToBeBound(CdtTrfTxInfDto.class);

        return new StaxEventItemReaderBuilder<CdtTrfTxInfDto>()
                .name("AchReader")
                .addFragmentRootElements("CdtTrfTxInf")
                .unmarshaller(AchMarshaller)
                .encoding("UTF-8")
                .build();
    }


    @Bean
    public MultiResourceItemReader<CdtTrfTxInfDto> achMultiResourceItemReader() {
        String path = unzippedInputFilePath + "/ACH";
        File[] files = new File(path).listFiles();
        List<Resource> resources = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getAbsolutePath().endsWith(".CT"))
                resources.add(new FileSystemResource(files[i].getAbsolutePath()));
        }
        Resource[] resourcesArray = new Resource[resources.size()];
        resources.toArray(resourcesArray);

        MultiResourceItemReader<CdtTrfTxInfDto> reader = new MultiResourceItemReader<>();
        reader.setDelegate(achXmlReader());
        reader.setResources(resourcesArray);
        return reader;
    }


    @Bean
    public AchRecordProcessor achProcessor() {
        return new AchRecordProcessor();
    }


    @Bean
    public Step achStep() throws Exception {
        return stepBuilderFactory.get("achStep").<CdtTrfTxInfDto, CdtTrfTxInfDto>chunk(1000)
                .reader(achMultiResourceItemReader())
                .processor(achProcessor())
                .writer(achClassifierCompositeItemWriter())

                .stream(achItemWriter.sep2SwitchAchItemWriter())
                .stream(achItemWriter.sep1SwitchAchItemWriter())
                .stream(achItemWriter.sep3SwitchAchItemWriter())
                .stream(achItemWriter.pna1SwitchAchItemWriter())
                .stream(achItemWriter.pna2SwitchAchItemWriter())
                .stream(achItemWriter.pec1SwitchAchItemWriter())
                .stream(achItemWriter.sayn1SwitchAchItemWriter())
                .stream(achItemWriter.sayn2SwitchAchItemWriter())
                .stream(achItemWriter.fanvSwitchAchItemWriter())
                .stream(achItemWriter.kicc1SwitchAchItemWriter())
                .stream(achItemWriter.kicc2SwitchAchItemWriter())
                .stream(achItemWriter.mabnSwitchAchItemWriter())
                .stream(achItemWriter.sada1SwitchAchItemWriter())
                .stream(achItemWriter.sada2SwitchAchItemWriter())
                .stream(achItemWriter.pep1SwitchAchItemWriter())
                .stream(achItemWriter.pep2SwitchAchItemWriter())
                .stream(achItemWriter.persSwitchAchItemWriter())
                .stream(achItemWriter.ecd1SwitchAchItemWriter())
                .stream(achItemWriter.ecd2SwitchAchItemWriter())
                .stream(achItemWriter.bpm1SwitchAchItemWriter())
                .stream(achItemWriter.bpm2SwitchAchItemWriter())
                .stream(achItemWriter.pec2SwitchAchItemWriter())
                .stream(achItemWriter.sshpSwitchAchItemWriter())
                .stream(achItemWriter.hubSwitchAchItemWriter())


//                .taskExecutor(taskExecutor()).throttleLimit(threadCount)
                .build();
    }


    @Bean
    public TaskExecutor achTaskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(threadCount);
        return asyncTaskExecutor;
    }


    @Bean
    public ClassifierCompositeItemWriter<CdtTrfTxInfDto> achClassifierCompositeItemWriter() throws Exception {
        ClassifierCompositeItemWriter<CdtTrfTxInfDto> classifierCompositeItemWriter = new ClassifierCompositeItemWriter<>();
        classifierCompositeItemWriter.setClassifier(new AchRecordClassifier(
                achItemWriter.sep2SwitchAchItemWriter(),
                achItemWriter.sep1SwitchAchItemWriter(),
                achItemWriter.sep3SwitchAchItemWriter(),
                achItemWriter.pna1SwitchAchItemWriter(),
                achItemWriter.pna2SwitchAchItemWriter(),
                achItemWriter.pec1SwitchAchItemWriter(),
                achItemWriter.sayn1SwitchAchItemWriter(),
                achItemWriter.sayn2SwitchAchItemWriter(),
                achItemWriter.fanvSwitchAchItemWriter(),
                achItemWriter.kicc1SwitchAchItemWriter(),
                achItemWriter.kicc2SwitchAchItemWriter(),
                achItemWriter.mabnSwitchAchItemWriter(),
                achItemWriter.sada1SwitchAchItemWriter(),
                achItemWriter.sada2SwitchAchItemWriter(),
                achItemWriter.pep1SwitchAchItemWriter(),
                achItemWriter.pep2SwitchAchItemWriter(),
                achItemWriter.persSwitchAchItemWriter(),
                achItemWriter.ecd1SwitchAchItemWriter(),
                achItemWriter.ecd2SwitchAchItemWriter(),
                achItemWriter.bpm1SwitchAchItemWriter(),
                achItemWriter.bpm2SwitchAchItemWriter(),
                achItemWriter.pec2SwitchAchItemWriter(),
                achItemWriter.sshpSwitchAchItemWriter(),
                achItemWriter.hubSwitchAchItemWriter()
        ));

        return classifierCompositeItemWriter;
    }


}