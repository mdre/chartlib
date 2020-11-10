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
public class C3Bar extends C3TypeConfig {

    private final static Logger LOGGER = Logger.getLogger(C3Bar.class.getName());

    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }


    public C3Bar() {
        super("bar");
    }


    //=====================================================
    /**
     * Change the width of bar chart.
     * Default: auto
     * 
     * @param b
     * @return 
     */
    public C3Bar setWidth(double w) {
        this.config.put("width", w);
        return this;
    }
    
    /**
     * Change the width of bar chart by ratio.
     * @param w
     * @return 
     */
    public C3Bar setWidthRatio(double r) {
        JSONObject width = new JSONObject();
        width.put("ratio", r);
                
        this.config.put("width", width);
        return this;
    }
    
    /**
     * Set if min or max value will be 0 on area chart.
     *
     * @param b Default: true
     * @return
     */
    public C3Bar setZerobased(boolean b) {
        this.config.put("zerobased", b);
        return this;
    }
    

}
