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
public class C3Spline extends C3TypeConfig {

    private final static Logger LOGGER = Logger.getLogger(C3Spline.class.getName());

    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }

    public enum InterpolationType {        
        LINEAR("linear"),
        LINEARCLOSED("linear-closed"),
        BASIS("basis"),
        BASISOPEN("basis-open"),
        BASISCLOSED("basis-closed"),
        BUNDLE("bundle"),
        CARDINAL("cardinal"),
        CARDINALOPEN("cardinal-open"),
        CARDINALCLOSED("cardinal-closed"),
        MONOTONE("monotone"),
        STEP("step"),
        STEPBEFORE("step-before"),
        STEPAFTER("step-after");

        String type;

        private InterpolationType(String type) {
            this.type = type;
        }
        
        public String getType() {
            return this.type;
        }

        @Override
        public String toString() {
            return this.type; 
        }
    }

    public C3Spline() {
        super("interpolation");
    }


    //=====================================================
    /**
     * Set if min or max value will be 0 on area chart.
     *
     * @param b Default: true
     * @return
     */
    public C3Spline setInterpolationType(InterpolationType t) {
        this.config.put("type", t.getType());
        return this;
    }

}
