package com.shaparak.batch.config;

import com.shaparak.batch.dto.xml.CdtTrfTxInf;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class AchStepConfig {


    @Bean
    public ItemReader<CdtTrfTxInf> reader() {
        Jaxb2Marshaller studentMarshaller = new Jaxb2Marshaller();
        studentMarshaller.setClassesToBeBound(CdtTrfTxInf.class);

        return new StaxEventItemReaderBuilder<CdtTrfTxInf>()
                .name("studentReader")
                .resource(new ClassPathResource("ct.xml"))
                .addFragmentRootElements("CdtTrfTxInf")
                .unmarshaller(studentMarshaller)
                .build();
    }


}