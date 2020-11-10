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
public class C3Point extends C3TypeConfig {

    private final static Logger LOGGER = Logger.getLogger(C3Point.class.getName());

    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }


    public C3Point() {
        super("point");
    }

    //=====================================================
    /**
     * Show or hide tooltip.
     *
     * @param b default: true
     * @return
     */
    public C3Point show(boolean b) {
        this.config.put("show", b);
        return this;
    }

    /**
     * The radius size of each point.
     *
     * @param r Default: 2.5
     * @return
     */
    public C3Point setRadius(double r) {
        this.config.put("r", r);
        return this;
    }

    /**
     * Set format for the title of tooltip.
     *
     * Specified function receives x and index of the data point to show.
     *
     * @param f Default: undefined
     * @return
     */
    public C3Point setFocusExpand(boolean e) {
        JSONObject focus = config.optJSONObject("focus");
        if (focus == null) {
            focus = new JSONObject();
        }

        JSONObject expand = focus.optJSONObject("expand");
        if (expand == null) {
            expand = new JSONObject();
        }
        expand.put("enabled", e);
        focus.put("expand", expand);

        config.put("focus", focus);

        return this;
    }

    /**
     * The radius size of each point on focus. 
     *
     * @param r Default: point.r * 1.75
     * @return
     */
    public C3Point setFocusExpandRadius(double r) {
        JSONObject focus = config.optJSONObject("focus");
        if (focus == null) {
            focus = new JSONObject();
        }

        JSONObject expand = focus.optJSONObject("expand");
        if (expand == null) {
            expand = new JSONObject();
        }
        expand.put("r", r);
        focus.put("expand", expand);

        config.put("focus", focus);

        return this;
    }

    /**
     * The radius of each point on selected. 
     *
     * @param r Default: point.r * 4
     * @return
     */
    public C3Point setSelectedRadius(double r) {
        JSONObject select = config.optJSONObject("select");
        if (select == null) {
            select = new JSONObject();
        }

        
        select.put("r", r);

        config.put("select", select);

        return this;
    }

}
