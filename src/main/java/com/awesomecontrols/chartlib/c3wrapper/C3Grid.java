/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.awesomecontrols.chartlib.c3wrapper;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Marcelo D. Ré {@literal <marcelo.re@gmail.com>}
 */
public class C3Grid {
    private final static Logger LOGGER = Logger.getLogger(C3Grid.class .getName());
    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }
    
    JSONObject config;
    
    public C3Grid() {
        config = new JSONObject();
    }

    /**
     * Show grids along x axis.
     * 
     * @param b default: false
     * @return 
     */
    public C3Grid setXShow(boolean b) {
        JSONObject x = config.optJSONObject("x");
        if (x==null) {
            x = new JSONObject();
        }
        x.put("show", b);
        
        config.put("x", x);
        return this;
    }

    
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
    public C3Grid addXLines(C3GridXLine l) {
        JSONObject x = config.optJSONObject("x");
        if (x==null) {
            x = new JSONObject();
        }
        JSONArray lines = x.optJSONArray("lines");
        if (lines==null){
            lines = new JSONArray();
        }
        lines.put(l.getConfig());
        
        x.put("lines", lines);
        
        config.put("x", x);
        return this;
    }
    
    /**
     * Show grids along y axis.
     * 
     * @param b default: false
     * @return 
     */
    public C3Grid setYShow(boolean b) {
        JSONObject y = config.optJSONObject("y");
        if (y==null) {
            y = new JSONObject();
        }
        y.put("show", b);
        
        config.put("y", y);
        return this;
    }

    
    /**
     * Show additional grid lines along y axis.
     * 
     * This option accepts array including object that has value, text, position and class.
     * 
     * Default: []
     * 
     */
    public C3Grid addYLines(C3GridYLine l) {
        JSONObject y = config.optJSONObject("y");
        if (y==null) {
            y = new JSONObject();
        }
        JSONArray lines = y.optJSONArray("lines");
        if (lines==null){
            lines = new JSONArray();
        }
        lines.put(l.getConfig());
        
        y.put("lines", lines);
        
        config.put("y", y);
        return this;
    }

    
    public JSONObject getConfig() {
        return this.config;
    }
    
}
