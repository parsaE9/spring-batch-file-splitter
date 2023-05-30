package com.shaparak.batch.config;

import com.shaparak.batch.aggregator.BankLineAggregator;
import com.shaparak.batch.classifier.BankRecordClassifier;
import com.shaparak.batch.classifier.PspRecordClassifier;
import com.shaparak.batch.header.HeaderWriter;
import com.shaparak.batch.model.Record;
import com.shaparak.batch.processor.RecordProcessor;
import com.shaparak.batch.writer.bank.BankItemWriter;
import com.shaparak.batch.writer.psp.PspItemWriter;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor
public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    private PspItemWriter pspItemWriter;

    private BankItemWriter bankItemWriter;


    @Bean
    public FlatFileItemReader<Record> reader() {
        FlatFileItemReader<Record> itemReader = new FlatFileItemReader<>();
//        itemReader.setResource(new FileSystemResource("C:\\Users\\p.aliesfahani\\Desktop\\batch\\in\\Batch_Details\\Batch_14011202_Cycle_01_Details.shap"));
        itemReader.setResource(new FileSystemResource("C:\\Users\\p.aliesfahani\\Desktop\\batch\\in\\Batch_Details\\input.shap"));
        itemReader.setName("ShaparakBatchReader");
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }


    private LineMapper<Record> lineMapper() {
        DefaultLineMapper<Record> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter("|");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("row_number", "psp_code", "acceptorCode", "traceCode", "localDate", "localTime", "reciveDate", "iban", "depositeDate", "depositeType", "depositeCircleNumber", "terminalType", "processType", "cardType", "amountShaparak", "referenceCode", "depositeFlag", "acceptorCommission", "pspCommission", "pspNetCommission", "shaparakCommission", "terminalCode", "cardNumber", "extraData1", "extraData2", "extraData3", "extraData4");
        BeanWrapperFieldSetMapper<Record> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Record.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }


    @Bean
    public RecordProcessor processor() {
        return new RecordProcessor();
    }


    @Bean
    public CompositeItemWriter<Record> compositeItemWriter() throws Exception {
        List<ItemWriter<? super Record>> writers = new ArrayList<>(2);
//        writers.add(pspClassifierCompositeItemWriter());
//        writers.add(bankClassifierCompositeItemWriter());
        writers.add(writer1());
        writers.add(writer2());

        CompositeItemWriter<Record> itemWriter = new CompositeItemWriter<Record>();

        itemWriter.setDelegates(writers);

        return itemWriter;
    }


    @Bean
    public ClassifierCompositeItemWriter<Record> pspClassifierCompositeItemWriter() throws Exception {
        ClassifierCompositeItemWriter<Record> compositeItemWriter = new ClassifierCompositeItemWriter<>();
        compositeItemWriter.setClassifier(new PspRecordClassifier(
                pspItemWriter.psp1ItemWriter(),
                pspItemWriter.psp2ItemWriter(),
                pspItemWriter.psp3ItemWriter(),
                pspItemWriter.psp4ItemWriter(),
                pspItemWriter.psp5ItemWriter(),
                pspItemWriter.psp6ItemWriter(),
                pspItemWriter.psp7ItemWriter(),
                pspItemWriter.psp8ItemWriter(),
                pspItemWriter.psp9ItemWriter(),
                pspItemWriter.psp10ItemWriter(),
                pspItemWriter.psp11ItemWriter(),
                pspItemWriter.psp12ItemWriter(),
                pspItemWriter.psp13ItemWriter(),
                pspItemWriter.psp14ItemWriter(),
                pspItemWriter.psp15ItemWriter(),
                pspItemWriter.psp16ItemWriter(),
                pspItemWriter.psp17ItemWriter(),
                pspItemWriter.psp18ItemWriter(),
                pspItemWriter.psp19ItemWriter(),
                pspItemWriter.psp20ItemWriter(),
                pspItemWriter.psp21ItemWriter(),
                pspItemWriter.psp22ItemWriter(),
                pspItemWriter.psp23ItemWriter(),
                pspItemWriter.psp24ItemWriter()
                ));
        return compositeItemWriter;
    }

    @Bean
    public ClassifierCompositeItemWriter<Record> bankClassifierCompositeItemWriter() throws Exception {
        ClassifierCompositeItemWriter<Record> compositeItemWriter = new ClassifierCompositeItemWriter<>();
        compositeItemWriter.setClassifier(new BankRecordClassifier(
                bankItemWriter.bank1ItemWriter(),
                bankItemWriter.bank2ItemWriter(),
                bankItemWriter.bank3ItemWriter(),
                bankItemWriter.bank4ItemWriter(),
                bankItemWriter.bank5ItemWriter(),
                bankItemWriter.bank6ItemWriter(),
                bankItemWriter.bank7ItemWriter(),
                bankItemWriter.bank8ItemWriter(),
                bankItemWriter.bank9ItemWriter(),
                bankItemWriter.bank10ItemWriter(),
                bankItemWriter.bank11ItemWriter(),
                bankItemWriter.bank12ItemWriter(),
                bankItemWriter.bank13ItemWriter(),
                bankItemWriter.bank14ItemWriter(),
                bankItemWriter.bank15ItemWriter(),
                bankItemWriter.bank16ItemWriter(),
                bankItemWriter.bank17ItemWriter(),
                bankItemWriter.bank18ItemWriter(),
                bankItemWriter.bank19ItemWriter(),
                bankItemWriter.bank20ItemWriter(),
                bankItemWriter.bank21ItemWriter(),
                bankItemWriter.bank22ItemWriter(),
                bankItemWriter.bank23ItemWriter(),
                bankItemWriter.bank24ItemWriter(),
                bankItemWriter.bank25ItemWriter(),
                bankItemWriter.bank26ItemWriter(),
                bankItemWriter.bank27ItemWriter(),
                bankItemWriter.bank28ItemWriter(),
                bankItemWriter.bank29ItemWriter(),
                bankItemWriter.bank30ItemWriter(),
                bankItemWriter.bank31ItemWriter(),
                bankItemWriter.bank32ItemWriter(),
                bankItemWriter.bank33ItemWriter(),
                bankItemWriter.bank34ItemWriter(),
                bankItemWriter.bank35ItemWriter(),
                bankItemWriter.bank36ItemWriter(),
                bankItemWriter.bank37ItemWriter(),
                bankItemWriter.bank38ItemWriter(),
                bankItemWriter.bank39ItemWriter(),
                bankItemWriter.bank40ItemWriter(),
                bankItemWriter.bank41ItemWriter(),
                bankItemWriter.bank42ItemWriter()
        ));
        return compositeItemWriter;
    }


    private FlatFileItemWriter<Record> writer1() throws Exception {
        String bankOutputPath = new File("C:\\Users\\p.aliesfahani\\Desktop\\batch\\a.txt").getAbsolutePath();
        FlatFileItemWriter<Record> writer = new FlatFileItemWriter<>();
        writer.setLineAggregator(new BankLineAggregator());
        writer.setResource(new FileSystemResource(bankOutputPath));
        writer.afterPropertiesSet();
        return writer;
    }
    private FlatFileItemWriter<Record> writer2() throws Exception {
        String bankOutputPath = new File("C:\\Users\\p.aliesfahani\\Desktop\\batch\\b.txt").getAbsolutePath();
        FlatFileItemWriter<Record> writer = new FlatFileItemWriter<>();
        writer.setLineAggregator(new BankLineAggregator());
        writer.setResource(new FileSystemResource(bankOutputPath));
        writer.afterPropertiesSet();
        return writer;
    }


    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1").<Record, Record>chunk(1000)
                .reader(reader())
//                .processor(processor())
                .writer(writer1())
//                .writer(compositeItemWriter())
//                .stream(writer1())
//                .stream(writer2())
//                .stream((ItemStream) pspClassifierCompositeItemWriter())
//                .stream((ItemStream) bankClassifierCompositeItemWriter())
//                .stream(pspItemWriter.pspItemWriter())
//                .stream(pspItemWriter.psp2ItemWriter())
//                .stream(pspItemWriter.psp3ItemWriter())
//                .stream(pspItemWriter.psp4ItemWriter())
//                .stream(pspItemWriter.psp5ItemWriter())
//                .stream(pspItemWriter.psp6ItemWriter())
//                .stream(pspItemWriter.psp7ItemWriter())
//                .stream(pspItemWriter.psp8ItemWriter())
//                .stream(pspItemWriter.psp9ItemWriter())
//                .stream(pspItemWriter.psp10ItemWriter())
//                .stream(pspItemWriter.psp11ItemWriter())
//                .stream(pspItemWriter.psp12ItemWriter())
//                .stream(pspItemWriter.psp13ItemWriter())
//                .stream(pspItemWriter.psp14ItemWriter())
//                .stream(pspItemWriter.psp15ItemWriter())
//                .stream(pspItemWriter.psp16ItemWriter())
//                .stream(pspItemWriter.psp17ItemWriter())
//                .stream(pspItemWriter.psp18ItemWriter())
//                .stream(pspItemWriter.psp19ItemWriter())
//                .stream(pspItemWriter.psp20ItemWriter())
//                .stream(pspItemWriter.psp21ItemWriter())
//                .stream(pspItemWriter.psp22ItemWriter())
//                .stream(pspItemWriter.psp23ItemWriter())
//                .stream(pspItemWriter.psp24ItemWriter())
                .taskExecutor(taskExecutor())
                .build();
    }


    @Bean
    public Job runJob() throws Exception {
        return jobBuilderFactory.get("ShaparakBatchJob")
                .flow(step1())
                .end()
                .build();

    }


    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(400);
        return asyncTaskExecutor;
    }


}