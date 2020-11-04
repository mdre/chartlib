/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.awesomecontrols.chartlib.c3wrapper;

import elemental.json.JsonValue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Marcelo D. Ré {@literal <marcelo.re@gmail.com>}
 */
public class C3Legend {
    private final static Logger LOGGER = Logger.getLogger(C3Legend.class .getName());
    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }
    
    JSONObject config;
    private ILegendItemOnClick onClick;
    private ILegendItemOnMouseOver onMouseOver;
    private ILegendItemOnMouseOut onMouseOut;
    public enum Position {
        bottom, right, inset
    }
    
    public enum Anchor {
        TOPLEFT("top-left"),
        TOPRIGHT("top-right"),
        BOTTOMLEFT("bottom-left"),
        BOTTOMRIGHT("bottom-right");
        
        String type;

        private Anchor(String type) {
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
    
    public C3Legend() {
        config = new JSONObject();
    }
    
    public JSONObject getConfig() {
        return this.config;
    }
    //=====================================================
    
    /**
     * Show or hide legend.
     * @param b default: true
     * @return 
     */
    public C3Legend show(boolean b) {
        this.config.put("show", b);
        return this;
    }
    
    /**
     * Hide legend
     * 
     * If true given, all legend will be hidden. 
     * 
     * @param b default: false
     * @return 
     */
    public C3Legend hide(boolean b) {
        config.put("hide", b);
        return this;
    }
    
    /**
     * If string or array given, only the legend that has the id will be hidden.
     * Ex: .setLegend(new C3Legend().hide("data1","data2"))
     * @param d
     * @return 
     */
    public C3Legend hide(String... d) {
        config.put("hide", d);
        return this;
    }
    
    /**
     * Change the position of legend.
     * 
     * Currently bottom, right and inset are supported.
     * 
     * @param p default: bottom
     * @return 
     */
    public C3Legend position(Position p) {
        config.put("position", p);
        return this;
    }
    
    
    /**
     * Change inset legend attributes.
     * 
     * This option accepts object that has the keys anchor, x, y and step.
     * 
     * anchor decides the position of the legend. These anchors are available:
     * 
     * top-left
     * top-right
     * bottom-left
     * bottom-right
     * 
     * x and y set the position of the legend based on the anchor.
     * 
     * step defines the max step the legend has (e.g. If 2 set and legend has 3 legend item, the legend 2 columns).
     * 
     * @param a
     * @param x
     * @param y
     * @param step
     * @return 
     */
    public C3Legend inset(Anchor a, int x, int y, int step) {
        JSONObject i = new JSONObject();
        i.put("anchor", a);
        i.put("x", x);
        i.put("y", y);
        i.put("step",step);
        
        config.put("inset", i);
        return this;
    }
    
    /**
     * Change inset legend attributes.
     * 
     * This option accepts object that has the keys anchor, x, y and step.
     * 
     * anchor decides the position of the legend. These anchors are available:
     * 
     * top-left
     * top-right
     * bottom-left
     * bottom-right
     * 
     * x and y set the position of the legend based on the anchor.
     * @param a
     * @param x
     * @param y
     * @return 
     */
    public C3Legend inset(Anchor a, int x, int y) {
        JSONObject i = new JSONObject();
        i.put("anchor", a);
        i.put("x", x);
        i.put("y", y);
        i.put("step","undefined");
        
        config.put("inset", i);
        return this;
    }
    
    
    
    
    public C3Legend setOnClick(ILegendItemOnClick onClick) {
        this.onClick = onClick;
        
        JSONObject item = config.optJSONObject("item");
        if (item==null) {
            item = new JSONObject();
        }
        item.put("onclick", true);
        
        this.config.put("item",item);
        return this;
    }

    void fireOnClick(JsonValue id) {
        this.onClick.onClick(id);
    }
    
    /**
     * Set a callback for mouseover event on each data point.
     * 
     * This callback will be called when mouse cursor moves onto each data point and will receive d as the 
     * argument. d is the data where mouse cursor moves onto. In this callback, this will be the Chart object.
     * 
     * @param omo
     * @return 
     */
    public C3Legend setOnMouseOver(ILegendItemOnMouseOver omo) {
        this.onMouseOver = omo;
        JSONObject item = config.optJSONObject("item");
        if (item==null) {
            item = new JSONObject();
        }
        item.put("onmouseover", true);
        
        this.config.put("item",item);

        return this;
    }
    
    void fireOnMouseOver(JsonValue d) {
        this.onMouseOver.onMouseOver(d);
    }
    
    
    public C3Legend setOnMouseOut(ILegendItemOnMouseOut omo) {
        this.onMouseOut = omo;
        JSONObject item = config.optJSONObject("item");
        if (item==null) {
            item = new JSONObject();
        }
        item.put("onmouseout", true);
        
        this.config.put("item",item);

        return this;
    }
    
    void fireOnMouseOut(JsonValue d) {
        this.onMouseOut.onMouseOut(d);
    }
    
    
}
