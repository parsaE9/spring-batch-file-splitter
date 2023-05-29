package com.shaparak.batch.config;

import com.shaparak.batch.classifier.BankRecordClassifier;
import com.shaparak.batch.classifier.PspRecordClassifier;
import com.shaparak.batch.model.Record;
import com.shaparak.batch.processor.RecordProcessor;
import com.shaparak.batch.writer.bank.BankItemWriter;
import com.shaparak.batch.writer.psp.PspItemWriter;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@AllArgsConstructor
public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    private PspItemWriter pspItemWriter;

    private BankItemWriter bankItemWriter;

    @Bean
    public CompositeItemWriter compositeItemWriter() {
        List<ItemWriter> writers = new ArrayList<>(2);
        writers.add(fileItemWriter1());
        writers.add(fileItemWriter2());

        CompositeItemWriter itemWriter = new CompositeItemWriter();

        itemWriter.setDelegates(writers);

        return itemWriter;
    }


    @Bean
    public FlatFileItemReader<Record> reader() {
        FlatFileItemReader<Record> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("C:\\Users\\p.aliesfahani\\Desktop\\batch\\in\\Batch_Details\\Batch_14011202_Cycle_01_Details.shap"));
//        itemReader.setResource(new FileSystemResource("C:\\Users\\p.aliesfahani\\Desktop\\batch\\in\\Batch_Details\\input.shap"));
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
    public ClassifierCompositeItemWriter<Record> pspClassifierCompositeItemWriter() throws Exception {
        ClassifierCompositeItemWriter<Record> compositeItemWriter = new ClassifierCompositeItemWriter<>();
        compositeItemWriter.setClassifier(new PspRecordClassifier(
                pspItemWriter.pspItemWriter(),
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
                bankItemWriter.bankItemWriter(),
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
                bankItemWriter.bank24ItemWriter()
        ));
        return compositeItemWriter;
    }


    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1").<Record, Record>chunk(1000)
                .reader(reader())
//                .processor(processor())
                .writer(pspClassifierCompositeItemWriter())
                .stream(pspItemWriter.pspItemWriter())
                .stream(pspItemWriter.psp2ItemWriter())
                .stream(pspItemWriter.psp3ItemWriter())
                .stream(pspItemWriter.psp4ItemWriter())
                .stream(pspItemWriter.psp5ItemWriter())
                .stream(pspItemWriter.psp6ItemWriter())
                .stream(pspItemWriter.psp7ItemWriter())
                .stream(pspItemWriter.psp8ItemWriter())
                .stream(pspItemWriter.psp9ItemWriter())
                .stream(pspItemWriter.psp10ItemWriter())
                .stream(pspItemWriter.psp11ItemWriter())
                .stream(pspItemWriter.psp12ItemWriter())
                .stream(pspItemWriter.psp13ItemWriter())
                .stream(pspItemWriter.psp14ItemWriter())
                .stream(pspItemWriter.psp15ItemWriter())
                .stream(pspItemWriter.psp16ItemWriter())
                .stream(pspItemWriter.psp17ItemWriter())
                .stream(pspItemWriter.psp18ItemWriter())
                .stream(pspItemWriter.psp19ItemWriter())
                .stream(pspItemWriter.psp20ItemWriter())
                .stream(pspItemWriter.psp21ItemWriter())
                .stream(pspItemWriter.psp22ItemWriter())
                .stream(pspItemWriter.psp23ItemWriter())
                .stream(pspItemWriter.psp24ItemWriter())
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