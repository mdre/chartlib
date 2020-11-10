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
public class C3Tooltip {

    private final static Logger LOGGER = Logger.getLogger(C3Tooltip.class.getName());

    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }

    JSONObject config;

    public C3Tooltip() {
        config = new JSONObject();
    }

    public JSONObject getConfig() {
        return this.config;
    }

    //=====================================================
    /**
     * Show or hide tooltip.
     *
     * @param b default: true
     * @return
     */
    public C3Tooltip show(boolean b) {
        this.config.put("show", b);
        return this;
    }

    /**
     * Set if tooltip is grouped or not for the data points.
     *
     * @param b default: true
     * @return
     */
    public C3Tooltip grouped(boolean b) {
        this.config.put("grouped", b);
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
    public C3Tooltip formatTittle(String f) {
        JSONObject format = config.optJSONObject("format");
        if (format == null) {
            format = new JSONObject();
        }
        // add the function
        format.put("title", f);

        config.put("format", format);

        return this;
    }

    /**
     * Set format for the name of each data in tooltip.
     *
     * Specified function receives name, ratio, id and index of the data point to show. ratio will be undefined if the chart is not donut/pie/gauge.
     *
     * @param f Default: undefined
     * @return
     */
    public C3Tooltip formatName(String f) {
        JSONObject format = config.optJSONObject("format");
        if (format == null) {
            format = new JSONObject();
        }
        // add the function
        format.put("name", f);

        config.put("format", format);

        return this;
    }

    /**
     * Set format for the value of each data in tooltip.
     *
     * Specified function receives name, ratio, id and index of the data point to show. ratio will be undefined if the chart is not donut/pie/gauge.
     *
     * If undefined returned, the row of that value will be skipped.
     *
     * EX: .formatValue(" function (value, ratio, id, index) { return ratio; }")
     *
     * @param f default: undefined
     *
     * @return
     */
    public C3Tooltip formatValue(String f) {
        JSONObject format = config.optJSONObject("format");
        if (format == null) {
            format = new JSONObject();
        }
        // add the function
        format.put("value", f);

        config.put("format", format);

        return this;
    }

    /**
     * Set custom position for the tooltip.
     *
     * This option can be used to modify the tooltip position by returning object that has top and left. Ex: .setPositiom("function (data, width,
     * height, element) {return {top: 0, left: 0};}")
     *
     * @param f
     * @return
     */
    public C3Tooltip setPosition(String f) {
        config.put("format", f);

        return this;
    }

    /**
     * Set custom HTML for the tooltip.
     *
     * Specified function receives data, defaultTitleFormat, defaultValueFormat and color of the data 
     * point to show. If tooltip.grouped is true, data
     * includes multiple data points. Default: undefined Format:
     *
     * .setContents("function (d, defaultTitleFormat, defaultValueFormat, color) { return ... // formatted html as you want } }")
     *
     *
     * @param f
     * @return
     */
    public C3Tooltip setContents(String f) {
        config.put("contents", f);

        return this;
    }

    /**
     * Show the tooltips based on the horizontal position of the mouse.
     * @param b
     * @return 
     */
    public C3Tooltip setHorizontal(boolean b) {
        config.put("horizontal", b);

        return this;
    }
}
