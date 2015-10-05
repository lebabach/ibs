/*
 * FileUploadModel
 */
package com.ecard.core.batch.util;

import java.io.File;

/**
 *
 * @author vinhla
 */
public class FileUploadModel {
    private boolean status;
    private String fileName;
    private String message;
    private File file;

    public FileUploadModel() {
        this.file = null;
        this.status = false;
    }

    public FileUploadModel(boolean status, String fileName, String message, File file) {
        this.status = status;
        this.fileName = fileName;
        this.message = message;
        this.file = file;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
