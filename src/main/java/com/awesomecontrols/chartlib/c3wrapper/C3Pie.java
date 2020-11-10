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
public class C3Pie extends C3TypeConfig {

    private final static Logger LOGGER = Logger.getLogger(C3Pie.class.getName());

    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }

    public C3Pie() {
        super("pie");
    }

    //=====================================================
    /**
     * Show or hide label on each pie piece.
     *
     * @param b Default: true
     * @return
     */
    public C3Pie setLabelShow(boolean b) {
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
     * Ex: .setLabelFormat("function (value, ratio, id) { return d3.format('$')(value);")
     *
     * @param b Default: undefined
     * @return
     */
    public C3Pie setLabelFormat(String f) {
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
     * Set threshold to show/hide labels.
     *
     * @param f Default: 0.05
     * @return
     */
    public C3Pie setLabelThreshold(double f) {
        JSONObject label = config.optJSONObject("label");
        if (label == null) {
            label = new JSONObject();
        }
        label.put("threshold", f);

        config.put("label", label);

        return this;
    }

    /**
     * Enable or disable expanding pie pieces.
     *
     * @param e Default: true
     * @return
     */
    public C3Pie setExpand(boolean e) {
        config.put("expand", e);

        return this;
    }

    /**
     * Sets the angular separation between each adjacent arc. 
     *
     * @param a Default: 0
     * @return
     */
    public C3Pie setPadAngle(double a) {
        config.put("padAngle", a);
        return this;
    }

}
