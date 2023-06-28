package com.shaparak.batch.config.step;

import com.shaparak.batch.BatchApplication;
import com.shaparak.batch.classifier.ach.AchRecordClassifier;
import com.shaparak.batch.dto.ach.AchRecord;
import com.shaparak.batch.dto.csv.SwitchDto;
import com.shaparak.batch.processor.AchRecordProcessor;
import com.shaparak.batch.service.CsvService;
import com.shaparak.batch.service.UnzipService;
import com.shaparak.batch.config.writer.ach.AchItemWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.support.MultiResourcePartitioner;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.io.File;
import java.io.FileWriter;
import java.net.MalformedURLException;
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

    @Value("${output.directory.path}")
    private String outputDirectoryPath;


    @Bean("partitioner")
    @StepScope
    public Partitioner partitioner() {
        File[] files = new File(unzippedInputFilePath + "/ACH").listFiles();
        List<Resource> resources = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getAbsolutePath().endsWith(".CT"))
                resources.add(new FileSystemResource(files[i].getAbsolutePath()));
        }

        Resource[] resourcesArray = new Resource[resources.size()];
        resources.toArray(resourcesArray);

        BatchApplication.jobDetailsMap.put("inputAchFilesCount", resources.size() + "");

        MultiResourcePartitioner partitioner = new MultiResourcePartitioner();
        partitioner.setResources(resourcesArray);
        partitioner.partition(resources.size());
        return partitioner;
    }

    @Bean
    @StepScope
    @DependsOn("partitioner")
    public StaxEventItemReader<AchRecord> achReader(@Value("#{stepExecutionContext['fileName']}") String fileName) throws MalformedURLException {
        Jaxb2Marshaller AchMarshaller = new Jaxb2Marshaller();
        AchMarshaller.setClassesToBeBound(AchRecord.class);

        return new StaxEventItemReaderBuilder<AchRecord>()
                .name("AchReader")
                .addFragmentRootElements("CdtTrfTxInf")
                .unmarshaller(AchMarshaller)
                .resource(new UrlResource(fileName))
                .encoding("UTF-8")
                .build();
    }


    @Bean
    public AchRecordProcessor achProcessor() {
        return new AchRecordProcessor();
    }


    @Bean
    public Step achSlaveStep() throws Exception {
        return stepBuilderFactory.get("achSlaveStep").<AchRecord, AchRecord>chunk(1000)
                .reader(achReader(null))
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

//                .taskExecutor().throttleLimit()

                .build();
    }


    @Bean
    public Step achMasterStep() throws Exception {
        createOutputAchFiles();
        return stepBuilderFactory.get("achMasterStep")
                .partitioner("achSlaveStep", partitioner())
                .step(achSlaveStep())
                .taskExecutor(achTaskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor achTaskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(30);
        return asyncTaskExecutor;
    }


    @Bean
    @StepScope
    public ClassifierCompositeItemWriter<AchRecord> achClassifierCompositeItemWriter() throws Exception {
        ClassifierCompositeItemWriter<AchRecord> classifierCompositeItemWriter = new ClassifierCompositeItemWriter<>();
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


    private void createOutputAchFiles() {
        try {
            File PspOutputDir;
            String filePath;
            for (SwitchDto switchDto : CsvService.switchMap.values()) {
                PspOutputDir = new File(outputDirectoryPath + "/PSPs/" + switchDto.getFolderName());
                if (!PspOutputDir.exists())
                    PspOutputDir.mkdirs();
                filePath = outputDirectoryPath + "/PSPs/" + switchDto.getFolderName() + "/" + "Ach_Cycle_01_" + UnzipService.fileDate + "_" + switchDto.getIin() + ".txt";
                File achOutputFile = new File(filePath);
                achOutputFile.createNewFile();
                FileWriter myWriter = new FileWriter(filePath);
                myWriter.write(getAchHeader() + "\n");
                myWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getAchHeader() {
        return "Acceptor_name%IBAN%Deposite_Identity%Amount%Trace_code%payment_Identity";
    }


}