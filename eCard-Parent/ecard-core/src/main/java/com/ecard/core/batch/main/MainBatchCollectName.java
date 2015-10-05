/*
 * MainBatchCollectName
 */
package com.ecard.core.batch.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author vinhla
 */
public class MainBatchCollectName {
    public static void main(String[] args) {
        String[] str = {"classpath:batch/batch-process-collect-name.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(str);		
    }
}
