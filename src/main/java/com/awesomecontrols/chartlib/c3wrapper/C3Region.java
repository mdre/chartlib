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
public class C3Region {
    private final static Logger LOGGER = Logger.getLogger(C3Region.class .getName());
    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }
    
    JSONObject reg;

    
    public enum RegionAxis {
        x,y,y2
    }
    
    /**
     * Show rectangles inside the chart.
     * 
     * This option accepts array including object that has axis, start, end and class. The keys start, end and class are optional.
     * axis must be x, y or y2. start and end should be the value where regions start and end. If not specified, the edge values 
     * will be used. If timeseries x axis, date string, Date object and unixtime integer can be used. If class is set, the region 
     * element will have it as class.
     * Default: []
     * 
     * Format:
     * chart().addRegion(new C3Region().setAxis(C3Region.RegionAxis.x).setEnd(1).setClass("regionX"))
     *        .addRegion(new C3Region().setAxis(C3Region.RegionAxis.x).setStart(2).setEnd(4).setClass("regionX"))
     *        .addRegion(new C3Region().setAxis(C3Region.RegionAxis.x).setStart(5).setClass("regionX"))
     *        .addRegion(new C3Region().setAxis(C3Region.RegionAxis.y).setEnd(50).setClass("regionY"))
     *        ....
     *        ....
     *        .initialize();
     * 
     */
    public C3Region() {
        reg = new JSONObject();
    }

    
    public C3Region setAxis(RegionAxis a) {
        this.reg.put("axis",a);
        return this;
    }
    
    public C3Region setStart(float start) {
        this.reg.put("start",start);
        return this;
    }

    public C3Region setEnd(float end) {
        this.reg.put("end",end);
        return this;
    }

    public C3Region setClass(String c) {
        this.reg.put("class",c);
        return this;
    }

    public JSONObject getValues() {
        return this.reg;
    }
    
}
