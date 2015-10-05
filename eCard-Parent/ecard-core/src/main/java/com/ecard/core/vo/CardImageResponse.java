/*
 * CardImageResponse
 */
package com.ecard.core.vo;

/**
 *
 * @author vinhla
 */
public class CardImageResponse extends AbstractCommonRes{
    private CardImage imageFile;

    /**
     * @return the imageFile
     */
    public CardImage getImageFile() {
        return imageFile;
    }

    /**
     * @param imageFile the imageFile to set
     */
    public void setImageFile(CardImage imageFile) {
        this.imageFile = imageFile;
    }
}
