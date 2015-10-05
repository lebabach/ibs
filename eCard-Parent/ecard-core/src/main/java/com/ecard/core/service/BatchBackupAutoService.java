/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.service;


/**
 *
 * @author Windows
 */
public interface BatchBackupAutoService {
    
    public void backupAutoDatabase(String date);
    
    public void backupManualDatabase();
}
