/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.awesomecontrols.chartlib.c3wrapper;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo D. Ré {@literal <marcelo.re@gmail.com>}
 */
public class C3Flow extends C3BaseData {
    private final static Logger LOGGER = Logger.getLogger(C3Flow.class .getName());
    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }

    public C3Flow(C3Column c) {
        config.put("columns", c.getConfig());
    }
    
    public C3Flow(C3Row r) {
        config.put("rows", r.getConfig());
    }
    
    public C3Flow(String url) {
        super("url");
        config.put("url", url);
    }
    
    public C3Flow setLength(int l) {
        config.put("length", l);
        return this;
    }
    
    public C3Flow setDuration(int duration) {
        config.put("duration", duration);
        return this;
    }
    
    public C3Flow setTo(String to) {
        config.put("to", to);
        return this;
    }
}
