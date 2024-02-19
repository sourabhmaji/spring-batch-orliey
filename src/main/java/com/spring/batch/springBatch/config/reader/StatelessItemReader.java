package com.spring.batch.springBatch.config.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

public class StatelessItemReader implements ItemReader<String> {
    private final Iterator<String> iterator ;
    public StatelessItemReader(List<String> list){
        iterator = list.iterator();
    }
    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(iterator.hasNext()){
            return iterator.next();
        }
        else{
            return null;
        }
    }
}
