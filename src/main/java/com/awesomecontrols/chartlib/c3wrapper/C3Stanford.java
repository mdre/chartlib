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
public class C3Stanford extends C3TypeConfig {

    private final static Logger LOGGER = Logger.getLogger(C3Stanford.class.getName());

    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }

    public C3Stanford() {
        super("stanford");
    }

    //=====================================================
    /**
     * Change the minimum value of the stanford color scale.
     *
     * @param m Default: auto
     * @return
     */
    public C3Stanford setScaleMin(double m) {
        config.put("scaleMin", m);
        return this;
    }

    /**
     * Change the maximum value of the stanford color scale.
     *
     * @param m Default: auto
     * @return
     */
    public C3Stanford setScaleMax(double m) {
        config.put("scaleMax", m);
        return this;
    }

    /**
     * Change the width of the stanford color scale.
     *
     * @param w Default: 20
     * @return
     */
    public C3Stanford setScaleWidth(double w) {
        config.put("scaleWidth", w);
        return this;
    }

    /**
     * NOT WORKING!!!! Set formatter for stanford color scale axis tick text.
     *
     * This option accepts the string 'pow10', a d3.format object and any function you define.
     *
     * Default: d3.format("d") - decimal notation, rounded to integer
     *
     * @param f
     * @return
     */
    public C3Stanford setScaleFormat(String f) {
        //FIXME: revisar esto!!!
        config.put("scaleFormat", f);
        return this;
    }

    /**
     * Set the values for stanford color scale axis tick text.
     *
     * This option accepts a function that returns an array of numbers.
     *
     * @param f Default: undefined
     * @return
     */
    public C3Stanford setScaleValues(String f) {
        //FIXME: revisar esto!!!
        config.put("scaleValues", f);
        return this;
    }

    /**
     * Set the color interpolator for stanford color scale.
     *
     * This option is a d3.interpolate* object or any function you define that receives a value between 0 and 1, and returns a color as string.
     *
     * Default: d3.interpolateHslLong(d3.hsl(250, 1, 0.5), d3.hsl(0, 1, 0.5))
     *
     * @param f
     * @return
     */
//    public C3Stanford setColors(String f) {
//        //FIXME: NOT WORKING!!
//        config.put("colors", f);
//
//        return this;
//    }
    /**
     * Set the padding for the stanford color scale.
     *
     * This option accepts array including object that has top, right, bottom and left. Default: undefined
     *
     * @param top
     * @param right
     * @param bottom
     * @param left
     * @return
     */
    public C3Stanford setPadding(double top, double right, double bottom, double left) {
        //FIXME: NOT WORKING!!
        JSONObject padding = config.optJSONObject("padding");
        if (padding == null) {
            padding = new JSONObject();
        }
        padding.put("top", top);
        padding.put("right", right);
        padding.put("bottom", bottom);
        padding.put("left", left);

        config.put("padding", padding);

        return this;
    }

    /**
     * Show text anywhere inside the chart.
     *
     * This option accepts array including object that has x, y, content and class. The key class is optional.
     *
     * x and y are the starting position of the text, content is the text content to show. If class is set, the text element will have it as class.
     * Default: []
     *
     * @param x
     * @param y
     * @param content
     * @param clazz could be null or ""
     * @return
     */
    public C3Stanford setTexts(double x, double y, String content, String clazz) {
        JSONArray texts = config.optJSONArray("texts");
        if (texts == null) {
            texts = new JSONArray();
        }
        JSONObject t = new JSONObject();
        t.put("x", x);
        t.put("y", y);
        t.put("content", content);
        if (clazz != null || !clazz.isEmpty()) {
            t.put("class", clazz);
        }

        config.put("texts", texts);

        return this;
    }

    /**
     * Show lines anywhere inside the chart.
     *
     * This option accepts array including object that has value_x1, value_y1, value_x2, value_y2 and class. The key class is optional.
     *
     * value_x1 and value_y1 are the starting position of the line, value_x2 and value_y2 are the ending position of the line. If class is set, the
     * line element will have it as class. 
     * 
     * Default: []
     *
     * @param value_x1
     * @param value_y1
     * @param value_x2
     * @param value_y2
     * @param clazz
     * @return
     */
    public C3Stanford setLines(double value_x1, double value_y1, double value_x2, double value_y2, String clazz) {
        JSONArray lines = config.optJSONArray("lines");
        if (lines == null) {
            lines = new JSONArray();
        }
        JSONObject l = new JSONObject();
        l.put("value_x1", value_x1);
        l.put("value_y1", value_y1);
        l.put("value_x2", value_x2);
        l.put("value_y2", value_y2);
        if (clazz != null || !clazz.isEmpty()) {
            l.put("class", clazz);
        }

        config.put("lines", lines);

        return this;
    }


//    public C3Stanford setRegions(){
//    TODO: implement this!!!!    
//    }
}
