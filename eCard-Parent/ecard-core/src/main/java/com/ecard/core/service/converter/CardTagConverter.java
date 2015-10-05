/*
 * CardTagConverter
 */
package com.ecard.core.service.converter;

import com.ecard.core.model.CardTag;
import com.ecard.core.model.CardTagId;
import com.ecard.core.vo.TagForCard;
import com.ecard.core.vo.TagGroup;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;

/**
 *
 * @author vinhla
 */
public final class CardTagConverter {
    public static List<CardTag>  convertCardTagList(List<com.ecard.core.model.CardTag> cardTagModelList) 
                                            throws IllegalAccessException, InvocationTargetException{
        List<CardTag> userTagList = new ArrayList<CardTag>();
        if (CollectionUtils.isNotEmpty(cardTagModelList)){
            CardTag cardTag;
            for (com.ecard.core.model.CardTag cardTagModel : cardTagModelList) {
                cardTag = new CardTag();
                CardTagId cardTagId = new CardTagId();
                cardTagId.setCardId(cardTagModel.getCardInfo().getCardId());
                cardTagId.setTagId(cardTagModel.getUserTag().getTagId());
                cardTag.setId(cardTagId);
                
                cardTag.setId(cardTagId);

                userTagList.add(cardTag);
            }
        }
        return userTagList;
    }
    
    public static List<com.ecard.core.vo.CardTag> convertCompanyCardTagList(List<com.ecard.core.model.CardTag> cardTagModelList) 
                                            throws IllegalAccessException, InvocationTargetException{
        List<com.ecard.core.vo.CardTag> userTagList = new ArrayList<com.ecard.core.vo.CardTag>();
        if (CollectionUtils.isNotEmpty(cardTagModelList)){
            com.ecard.core.vo.CardTag cardTag;
            for (com.ecard.core.model.CardTag cardTagModel : cardTagModelList) {
                cardTag = new com.ecard.core.vo.CardTag();
                
                cardTag.setTagName(cardTagModel.getUserTag().getTagName());
                cardTag.setCardId(cardTagModel.getId().getCardId());
                cardTag.setName(cardTagModel.getCardInfo().getName());
                cardTag.setCompanyName(cardTagModel.getCardInfo().getCompanyName());
                cardTag.setDepartmentName(cardTagModel.getCardInfo().getDepartmentName());
                cardTag.setPositionName(cardTagModel.getCardInfo().getPositionName());
                cardTag.setImageFile(cardTagModel.getCardInfo().getImageFile());
                userTagList.add(cardTag);
            }
        }
        return userTagList;
    }

    public static List<TagGroup> convertCardTagIdList(List<TagForCard> cardTagModelList)
                                            throws IllegalAccessException, InvocationTargetException{
        List<TagGroup> tagGroups = new ArrayList<TagGroup>();
        TagForCard cardTagId;
        
        if (cardTagModelList.size() != 0){            
//            for (TagForCard cardTagModel : cardTagModelList) {
//                 listTagId = cardTagModelList.stream().filter(t->t.getTagId()==cardTagModel.getTagId())
//                        .findAny().get().getCardId();
//                 
//                 
//                Arrays.stream(listTagId).forEach(System.out::println);
//                
//                cardTagId = new TagForCard(cardTagModel.getUserId(), cardTagModel.getCardId(), cardTagModel.getTagId(),cardTagModel.getTagName());                
//                cardTagIdList.add(cardTagId);
//            }
            tagGroups= cardTagModelList.stream().collect(Collectors.groupingBy(g->g.getTagId())).entrySet().stream().map(t->{
               TagGroup tg = new TagGroup();
               List<Integer> cardIds = new ArrayList<Integer>();
               
               tg.setTagId(t.getKey());
               tg.setUserId(t.getValue().stream().map(x->x.getUserId()).findFirst().get());
               if(t.getValue().get(0).getCardId() != null){
                   tg.setListCardIds(t.getValue().stream().map(x->x.getCardId()).collect(Collectors.toList()));
               } 
               else{
                   tg.setListCardIds(cardIds);
               }
               tg.setTagName(t.getValue().stream().map(x->x.getTagName()).findFirst().get());               
            return tg;  
           }).collect(Collectors.toList());           
        }
        
        return tagGroups;
    }
}
