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
public class C3Line extends C3TypeConfig {

    private final static Logger LOGGER = Logger.getLogger(C3Line.class.getName());

    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }

//    JSONObject config;

    public enum StepType {
        STEP("step"),
        STEPBEFORE("step-before"),
        STEPAFTER("step-after");

        String type;

        private StepType(String type) {
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

    public C3Line() {
        super("line");
//        config = new JSONObject();
    }

//    public JSONObject getConfig() {
//        return this.config;
//    }

    //=====================================================
    /**
     * Set if null data point will be connected or not.
     *
     * If true set, the region of null data will be connected without any data point. If false set, the region of null data will not be connected and
     * get empty.
     *
     * @param b Default: false
     * @return
     */
    public C3Line setConnectNull(boolean b) {
        this.config.put("connectNull", b);
        return this;
    }

    /**
     * Change step type for step chart.
     *
     * step, step-before and step-after can be used. 
     *
     * @param Default: 'step'
     * @return
     */
    public C3Line setFocusExpand(StepType st) {
        JSONObject step = config.optJSONObject("step");
        if (step == null) {
            step = new JSONObject();
        }
        
        step.put("type", st.getType());

        config.put("step", step);

        return this;
    }

}
