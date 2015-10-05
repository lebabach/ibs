/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.dao;

import com.ecard.core.model.UserCardMemoId;
import com.ecard.core.vo.CardInfoMemo;
import java.util.List;

/**
 *
 * @author admin
 */
public interface CardMemoDAO {
    
    public List<CardInfoMemo> getMemoCard(Integer cardId);
    
    public Integer registerCardMemo(UserCardMemoId cardMemo);
    
    public void createCardMemo(UserCardMemoId cardMemo);
    
    public void deleteCardMemo(UserCardMemoId cardMemo);

}
