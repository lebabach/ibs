package com.ecard.core.batch.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        String[] str = {"classpath:batch/context.xml", "classpath:batch/batch-collect-name.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(str);	
    }
}
