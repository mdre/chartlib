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
public class C3Gauge extends C3TypeConfig {

    private final static Logger LOGGER = Logger.getLogger(C3Gauge.class.getName());

    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }

    public C3Gauge() {
        super("gauge");
    }

    //=====================================================
    /**
     * Show or hide label on each pie piece.
     *
     * @param b Default: true
     * @return
     */
    public C3Gauge setLabelShow(boolean b) {
        JSONObject label = config.optJSONObject("label");
        if (label == null) {
            label = new JSONObject();
        }
        label.put("show", b);

        config.put("label", label);

        return this;
    }

    //=====================================================
    /**
     * NOT WORKING!!!! 
     * Set formatter for the label on each pie piece.
     *
     * Ex: .setLabelFormat("function (value, ratio) { return value; }")
     *
     * @param b Default: undefined
     * @return
     */
    public C3Gauge setLabelFormat(String f) {
        //FIXME: NOT WORKING!!
        JSONObject label = config.optJSONObject("label");
        if (label == null) {
            label = new JSONObject();
        }
        label.put("format", f);

        config.put("label", label);

        return this;
    }

    /**
     * Enable or disable expanding gauge.
     *
     * @param e Default: true
     * @return
     */
    public C3Gauge setExpand(boolean e) {
        config.put("expand", e);

        return this;
    }

    /**
     * Set min value of the gauge.
     *
     * @param m Default: 0
     * @return
     */
    public C3Gauge setMin(double m) {
        config.put("min", m);
        return this;
    }

    /**
     * Set max value of the gauge.
     *
     * @param m Default: 100
     * @return
     */
    public C3Gauge setMax(double m) {
        config.put("max", m);
        return this;
    }

    /**
     * Set units of the gauge. 
     *
     * @param u Default: undefined
     * @return
     */
    public C3Gauge setUnits(String u) {
        config.put("units", u);
        return this;
    }

    /**
     * Set width of donut chart.
     *
     * @param w Default: auto
     * @return
     */
    public C3Gauge setWidth(double w) {
        config.put("width", w);
        return this;
    }


}
