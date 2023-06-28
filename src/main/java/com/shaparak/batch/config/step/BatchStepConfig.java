package com.shaparak.batch.config.step;

import com.shaparak.batch.BatchApplication;
import com.shaparak.batch.classifier.batch.BankRecordClassifier;
import com.shaparak.batch.classifier.batch.PspRecordClassifier;
import com.shaparak.batch.dto.batch.BatchRecord;
import com.shaparak.batch.listener.BatchItemWriterListener;
import com.shaparak.batch.processor.BatchRecordProcessor;
import com.shaparak.batch.config.writer.batch.BankBatchItemWriter;
import com.shaparak.batch.config.writer.batch.PspBatchItemWriter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.batch.core.Step;
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
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
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
@ConditionalOnExpression("${create.psp.batch-file:true} or ${create.bank.batch-file:true}")
public class BatchStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private PspBatchItemWriter pspBatchItemWriter;

    @Autowired
    private BankBatchItemWriter bankBatchItemWriter;

    @Value("${unzipped.input.file.destination.path}")
    private String unzippedInputFilePath;

    @Value("${create.bank.batch-file}")
    private boolean bankFlag;

    @Value("${create.psp.batch-file}")
    private boolean pspFlag;

    @Value("${thread.count.batch.task}")
    private int threadCount;


    @Bean
    public FlatFileItemReader<BatchRecord> reader() throws Exception {
        FlatFileItemReader<BatchRecord> itemReader = new FlatFileItemReader<>();
        File dir = new File(unzippedInputFilePath + "/Batch_Details/");
        FileFilter fileFilter = new WildcardFileFilter("*");
        File[] files = dir.listFiles(fileFilter);
        if (files == null || files.length == 0)
            throw new Exception("no input txt file found!");
        else if (files.length > 1)
            throw new Exception("more than 1 txt file found!");
        BatchApplication.jobDetailsMap.put("inputTxtBatchFilePath", files[0].getAbsolutePath());
        itemReader.setResource(new FileSystemResource(files[0].getAbsolutePath()));
        itemReader.setName("ShaparakBatchReader");
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }


    private LineMapper<BatchRecord> lineMapper() {
        DefaultLineMapper<BatchRecord> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter("|");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("row_number", "psp_code", "acceptorCode", "traceCode", "localDate", "localTime", "reciveDate", "iban", "depositeDate", "depositeType", "depositeCircleNumber", "terminalType", "processType", "cardType", "amountShaparak", "referenceCode", "depositeFlag", "acceptorCommission", "pspCommission", "pspNetCommission", "shaparakCommission", "terminalCode", "cardNumber", "origTxnInfo", "acceptor_Net_Commission", "acceptor_bank_Commission", "business_category_code", "reserve");
        BeanWrapperFieldSetMapper<BatchRecord> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(BatchRecord.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }


    @Bean
    public BatchRecordProcessor processor() {
        return new BatchRecordProcessor();
    }


    @Bean
    public CompositeItemWriter<BatchRecord> compositeItemWriter() throws Exception {
        CompositeItemWriter<BatchRecord> itemWriter = new CompositeItemWriter<BatchRecord>();
        if (bankFlag && pspFlag)
            itemWriter.setDelegates(Arrays.asList(pspClassifierCompositeItemWriter(), bankClassifierCompositeItemWriter()));
        else if (bankFlag)
            itemWriter.setDelegates(List.of(bankClassifierCompositeItemWriter()));
        else if (pspFlag)
            itemWriter.setDelegates(List.of(pspClassifierCompositeItemWriter()));

        return itemWriter;
    }


    @Bean
    public Step batchStep() throws Exception {
        return stepBuilderFactory.get("batchStep").<BatchRecord, BatchRecord>chunk(1000)
                .reader(reader())
//                .processor(processor())
                .writer(compositeItemWriter())
                .listener(new BatchItemWriterListener())


                .stream(pspBatchItemWriter.sep2SwitchItemWriter())
                .stream(pspBatchItemWriter.sep1SwitchItemWriter())
                .stream(pspBatchItemWriter.sep3SwitchItemWriter())
                .stream(pspBatchItemWriter.pna1SwitchItemWriter())
                .stream(pspBatchItemWriter.pna2SwitchItemWriter())
                .stream(pspBatchItemWriter.pec1SwitchItemWriter())
                .stream(pspBatchItemWriter.sayn1SwitchItemWriter())
                .stream(pspBatchItemWriter.sayn2SwitchItemWriter())
                .stream(pspBatchItemWriter.fanvSwitchItemWriter())
                .stream(pspBatchItemWriter.kicc1SwitchItemWriter())
                .stream(pspBatchItemWriter.kicc2SwitchItemWriter())
                .stream(pspBatchItemWriter.mabnSwitchItemWriter())
                .stream(pspBatchItemWriter.sada1SwitchItemWriter())
                .stream(pspBatchItemWriter.sada2SwitchItemWriter())
                .stream(pspBatchItemWriter.pep1SwitchItemWriter())
                .stream(pspBatchItemWriter.pep2SwitchItemWriter())
                .stream(pspBatchItemWriter.persSwitchItemWriter())
                .stream(pspBatchItemWriter.ecd1SwitchItemWriter())
                .stream(pspBatchItemWriter.ecd2SwitchItemWriter())
                .stream(pspBatchItemWriter.bpm1SwitchItemWriter())
                .stream(pspBatchItemWriter.bpm2SwitchItemWriter())
                .stream(pspBatchItemWriter.pec2SwitchItemWriter())
                .stream(pspBatchItemWriter.sshpSwitchItemWriter())
                .stream(pspBatchItemWriter.hubSwitchItemWriter())


                .stream(bankBatchItemWriter.markaziBankItemWriter())
                .stream(bankBatchItemWriter.sanatBankItemWriter())
                .stream(bankBatchItemWriter.mellatBankItemWriter())
                .stream(bankBatchItemWriter.refahBankItemWriter())
                .stream(bankBatchItemWriter.maskanBankItemWriter())
                .stream(bankBatchItemWriter.sepahBankItemWriter())
                .stream(bankBatchItemWriter.keshavarziBankItemWriter())
                .stream(bankBatchItemWriter.melliBankItemWriter())
                .stream(bankBatchItemWriter.tejaratBankItemWriter())
                .stream(bankBatchItemWriter.saderatBankItemWriter())
                .stream(bankBatchItemWriter.toseeSaderatBankItemWriter())
                .stream(bankBatchItemWriter.postBankItemWriter())
                .stream(bankBatchItemWriter.toseeTaavonItemWriter())
                .stream(bankBatchItemWriter.etebariToseeeBankItemWriter())
                .stream(bankBatchItemWriter.ghavaminBankItemWriter())
                .stream(bankBatchItemWriter.karafarinBankItemWriter())
                .stream(bankBatchItemWriter.parsianBankItemWriter())
                .stream(bankBatchItemWriter.eghtesadNovinBankItemWriter())
                .stream(bankBatchItemWriter.samanBankItemWriter())
                .stream(bankBatchItemWriter.pasargadBankItemWriter())
                .stream(bankBatchItemWriter.sarmayeBankItemWriter())
                .stream(bankBatchItemWriter.sinaBankItemWriter())
                .stream(bankBatchItemWriter.mehrBankItemWriter())
                .stream(bankBatchItemWriter.shahrBankItemWriter())
                .stream(bankBatchItemWriter.ayandeBankItemWriter())
                .stream(bankBatchItemWriter.ansarBankItemWriter())
                .stream(bankBatchItemWriter.gardeshgariBankItemWriter())
                .stream(bankBatchItemWriter.hekmatIranianBankItemWriter())
                .stream(bankBatchItemWriter.dayBankItemWriter())
                .stream(bankBatchItemWriter.iranZaminBankItemWriter())
                .stream(bankBatchItemWriter.resalatBankItemWriter())
                .stream(bankBatchItemWriter.kosarBankItemWriter())
                .stream(bankBatchItemWriter.asgariyeBankItemWriter())
                .stream(bankBatchItemWriter.khavarmianeBankItemWriter())
                .stream(bankBatchItemWriter.iranVenezuelaBankItemWriter())
                .stream(bankBatchItemWriter.noorBankItemWriter())
                .stream(bankBatchItemWriter.shaparakItemWriter())
                .stream(bankBatchItemWriter.mehreEghtesadBankItemWriter())
                .stream(bankBatchItemWriter.shaparakPaymentFacilitatorBankItemWriter())
                .stream(bankBatchItemWriter.refahiBankItemWriter())
                .stream(bankBatchItemWriter.fuelBankItemWriter())


                .taskExecutor(taskExecutor())
                .throttleLimit(threadCount)
                .build();
    }


    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(threadCount);
        return asyncTaskExecutor;
    }


    @Bean
    public ClassifierCompositeItemWriter<BatchRecord> pspClassifierCompositeItemWriter() throws Exception {
        ClassifierCompositeItemWriter<BatchRecord> compositeItemWriter = new ClassifierCompositeItemWriter<>();
        compositeItemWriter.setClassifier(new PspRecordClassifier(
                pspBatchItemWriter.sep2SwitchItemWriter(),
                pspBatchItemWriter.sep1SwitchItemWriter(),
                pspBatchItemWriter.sep3SwitchItemWriter(),
                pspBatchItemWriter.pna1SwitchItemWriter(),
                pspBatchItemWriter.pna2SwitchItemWriter(),
                pspBatchItemWriter.pec1SwitchItemWriter(),
                pspBatchItemWriter.sayn1SwitchItemWriter(),
                pspBatchItemWriter.sayn2SwitchItemWriter(),
                pspBatchItemWriter.fanvSwitchItemWriter(),
                pspBatchItemWriter.kicc1SwitchItemWriter(),
                pspBatchItemWriter.kicc2SwitchItemWriter(),
                pspBatchItemWriter.mabnSwitchItemWriter(),
                pspBatchItemWriter.sada1SwitchItemWriter(),
                pspBatchItemWriter.sada2SwitchItemWriter(),
                pspBatchItemWriter.pep1SwitchItemWriter(),
                pspBatchItemWriter.pep2SwitchItemWriter(),
                pspBatchItemWriter.persSwitchItemWriter(),
                pspBatchItemWriter.ecd1SwitchItemWriter(),
                pspBatchItemWriter.ecd2SwitchItemWriter(),
                pspBatchItemWriter.bpm1SwitchItemWriter(),
                pspBatchItemWriter.bpm2SwitchItemWriter(),
                pspBatchItemWriter.pec2SwitchItemWriter(),
                pspBatchItemWriter.sshpSwitchItemWriter(),
                pspBatchItemWriter.hubSwitchItemWriter()
        ));
        return compositeItemWriter;
    }

    @Bean
    public ClassifierCompositeItemWriter<BatchRecord> bankClassifierCompositeItemWriter() throws Exception {
        ClassifierCompositeItemWriter<BatchRecord> compositeItemWriter = new ClassifierCompositeItemWriter<>();
        compositeItemWriter.setClassifier(new BankRecordClassifier(
                bankBatchItemWriter.markaziBankItemWriter(),
                bankBatchItemWriter.sanatBankItemWriter(),
                bankBatchItemWriter.mellatBankItemWriter(),
                bankBatchItemWriter.refahBankItemWriter(),
                bankBatchItemWriter.maskanBankItemWriter(),
                bankBatchItemWriter.sepahBankItemWriter(),
                bankBatchItemWriter.keshavarziBankItemWriter(),
                bankBatchItemWriter.melliBankItemWriter(),
                bankBatchItemWriter.tejaratBankItemWriter(),
                bankBatchItemWriter.saderatBankItemWriter(),
                bankBatchItemWriter.toseeSaderatBankItemWriter(),
                bankBatchItemWriter.postBankItemWriter(),
                bankBatchItemWriter.toseeTaavonItemWriter(),
                bankBatchItemWriter.etebariToseeeBankItemWriter(),
                bankBatchItemWriter.ghavaminBankItemWriter(),
                bankBatchItemWriter.karafarinBankItemWriter(),
                bankBatchItemWriter.parsianBankItemWriter(),
                bankBatchItemWriter.eghtesadNovinBankItemWriter(),
                bankBatchItemWriter.samanBankItemWriter(),
                bankBatchItemWriter.pasargadBankItemWriter(),
                bankBatchItemWriter.sarmayeBankItemWriter(),
                bankBatchItemWriter.sinaBankItemWriter(),
                bankBatchItemWriter.mehrBankItemWriter(),
                bankBatchItemWriter.shahrBankItemWriter(),
                bankBatchItemWriter.ayandeBankItemWriter(),
                bankBatchItemWriter.ansarBankItemWriter(),
                bankBatchItemWriter.gardeshgariBankItemWriter(),
                bankBatchItemWriter.hekmatIranianBankItemWriter(),
                bankBatchItemWriter.dayBankItemWriter(),
                bankBatchItemWriter.iranZaminBankItemWriter(),
                bankBatchItemWriter.resalatBankItemWriter(),
                bankBatchItemWriter.kosarBankItemWriter(),
                bankBatchItemWriter.asgariyeBankItemWriter(),
                bankBatchItemWriter.khavarmianeBankItemWriter(),
                bankBatchItemWriter.iranVenezuelaBankItemWriter(),
                bankBatchItemWriter.noorBankItemWriter(),
                bankBatchItemWriter.shaparakItemWriter(),
                bankBatchItemWriter.mehreEghtesadBankItemWriter(),
                bankBatchItemWriter.shaparakPaymentFacilitatorBankItemWriter(),
                bankBatchItemWriter.refahiBankItemWriter(),
                bankBatchItemWriter.fuelBankItemWriter()
        ));
        return compositeItemWriter;
    }


}