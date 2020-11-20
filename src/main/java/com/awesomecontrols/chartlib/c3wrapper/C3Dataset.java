/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.awesomecontrols.chartlib.c3wrapper;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Marcelo D. Ré {@literal <marcelo.re@gmail.com>}
 */
public abstract class C3Dataset {
    private final static Logger LOGGER = Logger.getLogger(C3Dataset.class .getName());
    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }
    
    JSONObject config;
    private String dataName;

    public C3Dataset(String dataName) {
        this.dataName = dataName;
        config = new JSONObject();
    }

    public JSONObject getConfig() {
        return this.config;
    }
    
    public String getDataName() {
        return dataName;
    }
}
