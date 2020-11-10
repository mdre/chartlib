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
public class C3Area extends C3TypeConfig {

    private final static Logger LOGGER = Logger.getLogger(C3Area.class.getName());

    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }


    public C3Area() {
        super("area");
    }


    //=====================================================
    /**
     * Set if min or max value will be 0 on area chart.
     *
     * @param b Default: true
     * @return
     */
    public C3Area setZerobased(boolean b) {
        this.config.put("zerobased", b);
        return this;
    }

}
