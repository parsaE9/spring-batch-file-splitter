//package com.shaparak.batch.config;
//
//import com.shaparak.batch.BatchApplication;
//import com.shaparak.batch.classifier.batch.BankRecordClassifier;
//import com.shaparak.batch.classifier.batch.PspRecordClassifier;
//import com.shaparak.batch.dto.BatchRecord;
//import com.shaparak.batch.listener.ItemWriteListenerImpl;
//import com.shaparak.batch.processor.BatchRecordProcessor;
//import com.shaparak.batch.service.UnzipService;
//import com.shaparak.batch.writer.batch.BankItemWriter;
//import com.shaparak.batch.writer.batch.PspItemWriter;
//import org.apache.commons.io.filefilter.WildcardFileFilter;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.LineMapper;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
//import org.springframework.batch.item.support.CompositeItemWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.core.task.TaskExecutor;
//
//import java.io.File;
//import java.io.FileFilter;
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//public class BatchStepConfig {
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private PspItemWriter pspItemWriter;
//
//    @Autowired
//    private BankItemWriter bankItemWriter;
//
//    @Autowired
//    private UnzipService unzipService;
//
//    @Value("${unzipped.input.file.destination.path}")
//    private String unzippedInputFilePath;
//
//    @Value("${create.bank-file}")
//    private boolean bankFlag;
//
//    @Value("${create.psp-file}")
//    private boolean pspFlag;
//
//    @Value("${thread.count.batch.task}")
//    private int threadCount;
//
//
//    @Bean
//    public FlatFileItemReader<BatchRecord> reader() throws Exception {
////        unzipService.clearFolders();
////        unzipService.unzip();
//
//        FlatFileItemReader<BatchRecord> itemReader = new FlatFileItemReader<>();
//        File dir = new File(unzippedInputFilePath + "/Batch_Details/");
//        FileFilter fileFilter = new WildcardFileFilter("*");
//        File[] files = dir.listFiles(fileFilter);
//        if (files == null || files.length == 0)
//            throw new Exception("no input txt file found!");
//        else if (files.length > 1)
//            throw new Exception("more than 1 txt file found!");
//        BatchApplication.jobDetailsMap.put("inputTxtBatchFilePath", files[0].getAbsolutePath());
//        itemReader.setResource(new FileSystemResource(files[0].getAbsolutePath()));
//        itemReader.setName("ShaparakBatchReader");
//        itemReader.setLineMapper(lineMapper());
//        return itemReader;
//    }
//
//
//    private LineMapper<BatchRecord> lineMapper() {
//        DefaultLineMapper<BatchRecord> lineMapper = new DefaultLineMapper<>();
//        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
//        lineTokenizer.setDelimiter("|");
//        lineTokenizer.setStrict(false);
//        lineTokenizer.setNames("row_number", "psp_code", "acceptorCode", "traceCode", "localDate", "localTime", "reciveDate", "iban", "depositeDate", "depositeType", "depositeCircleNumber", "terminalType", "processType", "cardType", "amountShaparak", "referenceCode", "depositeFlag", "acceptorCommission", "pspCommission", "pspNetCommission", "shaparakCommission", "terminalCode", "cardNumber", "extraData1", "extraData2", "extraData3", "extraData4");
//        BeanWrapperFieldSetMapper<BatchRecord> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        fieldSetMapper.setTargetType(BatchRecord.class);
//        lineMapper.setLineTokenizer(lineTokenizer);
//        lineMapper.setFieldSetMapper(fieldSetMapper);
//        return lineMapper;
//    }
//
//
//    @Bean
//    public BatchRecordProcessor processor() {
//        return new BatchRecordProcessor();
//    }
//
//
//    @Bean
//    public CompositeItemWriter<BatchRecord> compositeItemWriter() throws Exception {
//        CompositeItemWriter<BatchRecord> itemWriter = new CompositeItemWriter<BatchRecord>();
//        if (bankFlag && pspFlag)
//            itemWriter.setDelegates(Arrays.asList(pspClassifierCompositeItemWriter(), bankClassifierCompositeItemWriter()));
//        else if (bankFlag)
//            itemWriter.setDelegates(List.of(bankClassifierCompositeItemWriter()));
//        else if (pspFlag)
//            itemWriter.setDelegates(List.of(pspClassifierCompositeItemWriter()));
//        else
//            throw new Exception("bankFlag and pspFlag can not be false at same time");
//
//        return itemWriter;
//    }
//
//    @Bean
//    public Step batchStep() throws Exception {
//        return stepBuilderFactory.get("batchStep").<BatchRecord, BatchRecord>chunk(1000)
//                .reader(reader())
////                .processor(processor())
//                .writer(compositeItemWriter())
//                .listener(new ItemWriteListenerImpl())
//
//
//                .stream(pspItemWriter.sep2SwitchItemWriter())
//                .stream(pspItemWriter.sep1SwitchItemWriter())
//                .stream(pspItemWriter.sep3SwitchItemWriter())
//                .stream(pspItemWriter.pna1SwitchItemWriter())
//                .stream(pspItemWriter.pna2SwitchItemWriter())
//                .stream(pspItemWriter.pec1SwitchItemWriter())
//                .stream(pspItemWriter.sayn1SwitchItemWriter())
//                .stream(pspItemWriter.sayn2SwitchItemWriter())
//                .stream(pspItemWriter.fanvSwitchItemWriter())
//                .stream(pspItemWriter.kicc1SwitchItemWriter())
//                .stream(pspItemWriter.kicc2SwitchItemWriter())
//                .stream(pspItemWriter.mabnSwitchItemWriter())
//                .stream(pspItemWriter.sada1SwitchItemWriter())
//                .stream(pspItemWriter.sada2SwitchItemWriter())
//                .stream(pspItemWriter.pep1SwitchItemWriter())
//                .stream(pspItemWriter.pep2SwitchItemWriter())
//                .stream(pspItemWriter.persSwitchItemWriter())
//                .stream(pspItemWriter.ecd1SwitchItemWriter())
//                .stream(pspItemWriter.ecd2SwitchItemWriter())
//                .stream(pspItemWriter.bpm1SwitchItemWriter())
//                .stream(pspItemWriter.bpm2SwitchItemWriter())
//                .stream(pspItemWriter.pec2SwitchItemWriter())
//                .stream(pspItemWriter.sshpSwitchItemWriter())
//                .stream(pspItemWriter.hubSwitchItemWriter())
//
//
//                .stream(bankItemWriter.markaziBankItemWriter())
//                .stream(bankItemWriter.sanatBankItemWriter())
//                .stream(bankItemWriter.mellatBankItemWriter())
//                .stream(bankItemWriter.refahBankItemWriter())
//                .stream(bankItemWriter.maskanBankItemWriter())
//                .stream(bankItemWriter.sepahBankItemWriter())
//                .stream(bankItemWriter.keshavarziBankItemWriter())
//                .stream(bankItemWriter.melliBankItemWriter())
//                .stream(bankItemWriter.tejaratBankItemWriter())
//                .stream(bankItemWriter.saderatBankItemWriter())
//                .stream(bankItemWriter.toseeSaderatBankItemWriter())
//                .stream(bankItemWriter.postBankItemWriter())
//                .stream(bankItemWriter.toseeTaavonItemWriter())
//                .stream(bankItemWriter.etebariToseeeBankItemWriter())
//                .stream(bankItemWriter.ghavaminBankItemWriter())
//                .stream(bankItemWriter.karafarinBankItemWriter())
//                .stream(bankItemWriter.parsianBankItemWriter())
//                .stream(bankItemWriter.eghtesadNovinBankItemWriter())
//                .stream(bankItemWriter.samanBankItemWriter())
//                .stream(bankItemWriter.pasargadBankItemWriter())
//                .stream(bankItemWriter.sarmayeBankItemWriter())
//                .stream(bankItemWriter.sinaBankItemWriter())
//                .stream(bankItemWriter.mehrBankItemWriter())
//                .stream(bankItemWriter.shahrBankItemWriter())
//                .stream(bankItemWriter.ayandeBankItemWriter())
//                .stream(bankItemWriter.ansarBankItemWriter())
//                .stream(bankItemWriter.gardeshgariBankItemWriter())
//                .stream(bankItemWriter.hekmatIranianBankItemWriter())
//                .stream(bankItemWriter.dayBankItemWriter())
//                .stream(bankItemWriter.iranZaminBankItemWriter())
//                .stream(bankItemWriter.resalatBankItemWriter())
//                .stream(bankItemWriter.kosarBankItemWriter())
//                .stream(bankItemWriter.asgariyeBankItemWriter())
//                .stream(bankItemWriter.khavarmianeBankItemWriter())
//                .stream(bankItemWriter.iranVenezuelaBankItemWriter())
//                .stream(bankItemWriter.noorBankItemWriter())
//                .stream(bankItemWriter.shaparakItemWriter())
//                .stream(bankItemWriter.mehreEghtesadBankItemWriter())
//                .stream(bankItemWriter.shaparakPaymentFacilitatorBankItemWriter())
//                .stream(bankItemWriter.refahiBankItemWriter())
//                .stream(bankItemWriter.fuelBankItemWriter())
//
//
//                .taskExecutor(taskExecutor())
//                .throttleLimit(threadCount)
////                .listener(new ChunkListenerImpl())                 // test speed with and without listener
//                .build();
//    }
//
//
//    @Bean
//    public TaskExecutor taskExecutor() {
//        System.out.println(threadCount);
//        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
//        asyncTaskExecutor.setConcurrencyLimit(threadCount);
//        return asyncTaskExecutor;
//    }
//
//
//    @Bean
//    public ClassifierCompositeItemWriter<BatchRecord> pspClassifierCompositeItemWriter() throws Exception {
//        ClassifierCompositeItemWriter<BatchRecord> compositeItemWriter = new ClassifierCompositeItemWriter<>();
//        compositeItemWriter.setClassifier(new PspRecordClassifier(
//                pspItemWriter.sep2SwitchItemWriter(),
//                pspItemWriter.sep1SwitchItemWriter(),
//                pspItemWriter.sep3SwitchItemWriter(),
//                pspItemWriter.pna1SwitchItemWriter(),
//                pspItemWriter.pna2SwitchItemWriter(),
//                pspItemWriter.pec1SwitchItemWriter(),
//                pspItemWriter.sayn1SwitchItemWriter(),
//                pspItemWriter.sayn2SwitchItemWriter(),
//                pspItemWriter.fanvSwitchItemWriter(),
//                pspItemWriter.kicc1SwitchItemWriter(),
//                pspItemWriter.kicc2SwitchItemWriter(),
//                pspItemWriter.mabnSwitchItemWriter(),
//                pspItemWriter.sada1SwitchItemWriter(),
//                pspItemWriter.sada2SwitchItemWriter(),
//                pspItemWriter.pep1SwitchItemWriter(),
//                pspItemWriter.pep2SwitchItemWriter(),
//                pspItemWriter.persSwitchItemWriter(),
//                pspItemWriter.ecd1SwitchItemWriter(),
//                pspItemWriter.ecd2SwitchItemWriter(),
//                pspItemWriter.bpm1SwitchItemWriter(),
//                pspItemWriter.bpm2SwitchItemWriter(),
//                pspItemWriter.pec2SwitchItemWriter(),
//                pspItemWriter.sshpSwitchItemWriter(),
//                pspItemWriter.hubSwitchItemWriter()
//        ));
//        return compositeItemWriter;
//    }
//
//    @Bean
//    public ClassifierCompositeItemWriter<BatchRecord> bankClassifierCompositeItemWriter() throws Exception {
//        ClassifierCompositeItemWriter<BatchRecord> compositeItemWriter = new ClassifierCompositeItemWriter<>();
//        compositeItemWriter.setClassifier(new BankRecordClassifier(
//                bankItemWriter.markaziBankItemWriter(),
//                bankItemWriter.sanatBankItemWriter(),
//                bankItemWriter.mellatBankItemWriter(),
//                bankItemWriter.refahBankItemWriter(),
//                bankItemWriter.maskanBankItemWriter(),
//                bankItemWriter.sepahBankItemWriter(),
//                bankItemWriter.keshavarziBankItemWriter(),
//                bankItemWriter.melliBankItemWriter(),
//                bankItemWriter.tejaratBankItemWriter(),
//                bankItemWriter.saderatBankItemWriter(),
//                bankItemWriter.toseeSaderatBankItemWriter(),
//                bankItemWriter.postBankItemWriter(),
//                bankItemWriter.toseeTaavonItemWriter(),
//                bankItemWriter.etebariToseeeBankItemWriter(),
//                bankItemWriter.ghavaminBankItemWriter(),
//                bankItemWriter.karafarinBankItemWriter(),
//                bankItemWriter.parsianBankItemWriter(),
//                bankItemWriter.eghtesadNovinBankItemWriter(),
//                bankItemWriter.samanBankItemWriter(),
//                bankItemWriter.pasargadBankItemWriter(),
//                bankItemWriter.sarmayeBankItemWriter(),
//                bankItemWriter.sinaBankItemWriter(),
//                bankItemWriter.mehrBankItemWriter(),
//                bankItemWriter.shahrBankItemWriter(),
//                bankItemWriter.ayandeBankItemWriter(),
//                bankItemWriter.ansarBankItemWriter(),
//                bankItemWriter.gardeshgariBankItemWriter(),
//                bankItemWriter.hekmatIranianBankItemWriter(),
//                bankItemWriter.dayBankItemWriter(),
//                bankItemWriter.iranZaminBankItemWriter(),
//                bankItemWriter.resalatBankItemWriter(),
//                bankItemWriter.kosarBankItemWriter(),
//                bankItemWriter.asgariyeBankItemWriter(),
//                bankItemWriter.khavarmianeBankItemWriter(),
//                bankItemWriter.iranVenezuelaBankItemWriter(),
//                bankItemWriter.noorBankItemWriter(),
//                bankItemWriter.shaparakItemWriter(),
//                bankItemWriter.mehreEghtesadBankItemWriter(),
//                bankItemWriter.shaparakPaymentFacilitatorBankItemWriter(),
//                bankItemWriter.refahiBankItemWriter(),
//                bankItemWriter.fuelBankItemWriter()
//        ));
//        return compositeItemWriter;
//    }
//
//
//}