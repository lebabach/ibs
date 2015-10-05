/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.CardMemoDAO;
import com.ecard.core.model.UserCardMemoId;
import com.ecard.core.service.CardMemoService;
import com.ecard.core.vo.CardInfoMemo;

/**
 *
 * @author admin
 */
@Service("cardMemoService")
@Transactional
public class CardMemoServiceImpl implements CardMemoService {
    @Autowired
    CardMemoDAO cardMemoDAO;
    
    public List<CardInfoMemo> getMemoCard(Integer cardId){
        return cardMemoDAO.getMemoCard(cardId);
    }
    
    public Integer registerCardMemo(UserCardMemoId cardMemo) {
        return cardMemoDAO.registerCardMemo(cardMemo);
    }
    
    public void createCardMemo(UserCardMemoId cardMemo) {
        cardMemoDAO.createCardMemo(cardMemo);
    }
    
    public void deleteCardMemo(UserCardMemoId cardMemo) {
        cardMemoDAO.deleteCardMemo(cardMemo);
    }
    
}
