/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.awesomecontrols.chartlib.c3wrapper;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo D. Ré {@literal <marcelo.re@gmail.com>}
 */
public class C3LoadUrl extends C3Dataset {
    private final static Logger LOGGER = Logger.getLogger(C3LoadUrl.class .getName());
    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }

    public C3LoadUrl(String url) {
        super("url");
        config.put("url", url);
    }
    
    public C3LoadUrl setChartType(C3ChartType chartType) {
        config.put("type", chartType.getType());
        return this;
    }
    
    public C3LoadUrl unload(String... ids) {
        
        config.put("unload", List.of(ids));
        return this;
    }
}
