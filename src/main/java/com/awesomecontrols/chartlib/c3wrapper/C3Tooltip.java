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
public class C3Tooltip {
    private final static Logger LOGGER = Logger.getLogger(C3Tooltip.class .getName());
    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }
    
    JSONObject config;
    
    public C3Tooltip() {
        config = new JSONObject();
    }
    
    public JSONObject getConfig() {
        return this.config;
    }

    //=====================================================
    
    /**
     * Show or hide tooltip.
     * @param b default: true
     * @return 
     */
    public C3Tooltip show(boolean b) {
        this.config.put("show", b);
        return this;
    }
   
    /**
     * Set if tooltip is grouped or not for the data points.
     * @param b default: true
     * @return 
     */
    public C3Tooltip grouped(boolean b) {
        this.config.put("grouped", b);
        return this;
    }
   
    
    
}
