package com.shaparak.batch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyCustomWriter<T> implements ItemWriter<T> {


    @Override
    public void write(List<? extends T> items) throws Exception {
        System.out.println("custom item writer");
    }


}