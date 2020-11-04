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
public class C3GridYLine {
    private final static Logger LOGGER = Logger.getLogger(C3GridYLine.class .getName());
    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }
    
    public enum TextPosition {
        start,middle,end
    }
    
    
    JSONObject config;
    
    /**
     * Show additional grid lines along x axis.
     * 
     * This option accepts array including object that has value, text, position and class. text, 
     * position and class are optional. For position, start, middle and end (default) are available.
     * 
     * If x axis is category axis, value can be category name. If x axis is timeseries axis, value can
     * be date string, Date object and unixtime integer.
     * 
     * Default: []
     * 
     */
    public C3GridYLine() {
        this.config = new JSONObject();
    }
    
    public C3GridYLine setValue(float v) {
        this.config.put("value", v);
        return this;
    }
    
    public C3GridYLine setText(String t) {
        this.config.put("text", t);
        return this;
    }
    
    public C3GridYLine setClass(String c) {
        this.config.put("class", c);
        return this;
    }
    
    public C3GridYLine setTextPosition(TextPosition p) {
        this.config.put("position", p);
        return this;
    }
    
    public C3GridYLine setAxis(C3YAxis axis) {
        this.config.put("axis", axis);
        return this;
    }
    
    public JSONObject getConfig() {
        return this.config;
    }
}
