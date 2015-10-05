/*
 * PrusalHistoryResponse class
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class PrusalHistoryResponse extends AbstractCommonRes {
    private List<PrusalHistory> listPrusalHistory;

    /**
     * @return the listPrusalHistory
     */
    public List<PrusalHistory> getPrusalHistory() {
        return listPrusalHistory;
    }

    /**
     * @param listPrusalHistory the listCardInfo to set
     */
    public void setPrusalHistory(List<PrusalHistory> listPrusalHistory) {
        this.listPrusalHistory = listPrusalHistory;
    }
    
}
