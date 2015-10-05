/*
 * BatchWriteCardImage
 */
package com.ecard.core.service;

import com.ecard.core.model.CardInfo;
import java.util.List;
import org.quartz.SchedulerException;

/**
 *
 * @author vinhla
 */
public interface BatchWriteCardImage {
    public void writeCardImage(List<CardInfo> cardInfo) throws SchedulerException;
}
