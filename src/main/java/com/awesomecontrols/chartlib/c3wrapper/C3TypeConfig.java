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
public abstract class C3TypeConfig {

    private final static Logger LOGGER = Logger.getLogger(C3TypeConfig.class.getName());

    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }

    JSONObject config;
    private String typeName;

    public C3TypeConfig(String typeName) {
        this.typeName = typeName;
        config = new JSONObject();
    }

    public JSONObject getConfig() {
        return this.config;
    }
    
    public String getTypeName() {
        return typeName;
    }
}
