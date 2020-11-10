/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.awesomecontrols.chartlib.c3wrapper;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import elemental.json.JsonValue;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * C3.js chart wrapper.
 * 
 * @author Marcelo D. Ré {@literal <marcelo.re@gmail.com>}
 */
@Tag("c3-chart") 
@NpmPackage(value = "d3", version = "6.2.0")
@NpmPackage(value = "c3", version = "0.7.20")
@JsModule("./chartlib/c3-chart.js")
public class C3Chart extends Component implements HasTheme, HasStyle, HasComponents  {
    private final static Logger LOGGER = Logger.getLogger(C3Chart.class .getName());
    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }
    
    JSONObject config;
    private IOnInit onInit;
    private IOnRendered onRendered;
    private IOnMouseOver onMouseOver;
    private IOnMouseOut onMouseOut;
    private IOnResize onResize;
    private IOnResized onResized;
    private C3Data data;
    private C3Legend legends;

    public C3Chart() {
        config = new JSONObject();
    }
    
    public C3Chart initialize() {
        LOGGER.log(Level.FINEST, "send config and initialize...");
        getElement().callJsFunction("initialize",config.toString());
        return this;
    }
    
    public JSONObject getConfig() {
        return this.config;
    }
    
    public C3Chart setConfig(String jsonConfig) {
        this.config = new JSONObject(jsonConfig);
        return this;
    }
    
    /**
     * Set the chart width
     * @param w the width in pixels
     * @return 
     */
    public C3Chart setWidth(int w) {
        JSONObject size = config.optJSONObject("size");
        if (size==null) {
            size = new JSONObject();
        }
        size.put("width", w);
        config.put("size", size);
        return this;
    }
    
    /**
     * Set the chart height
     * @param h the height in pixels
     * @return 
     */
    public C3Chart setHeight(int h) {
        JSONObject size = config.optJSONObject("size");
        if (size==null) {
            size = new JSONObject();
        }
        size.put("height", h);
        config.put("size", size);
        return this;
    }
    
    /**
     * Set the chart top padding
     * @param t the top padding in pixels
     * @return 
     */
    public C3Chart setPaddingTop(int t) {
        JSONObject padding = config.optJSONObject("padding");
        if (padding==null) {
            padding = new JSONObject();
        }
        padding.put("top", t);
        config.put("padding", padding);
        return this;
    }
    
    /**
     * Set the chart right padding
     * @param r the right padding in pixels
     * @return 
     */
    public C3Chart setPaddingRight(int r) {
        JSONObject padding = config.optJSONObject("padding");
        if (padding==null) {
            padding = new JSONObject();
        }
        padding.put("right", r);
        config.put("padding", padding);
        return this;
    }
    
    /**
     * Set the chart bottom padding
     * @param b the bottom padding in pixels
     * @return 
     */
    public C3Chart setPaddingBottom(int b) {
        JSONObject padding = config.optJSONObject("padding");
        if (padding==null) {
            padding = new JSONObject();
        }
        padding.put("bottom", b);
        config.put("padding", padding);
        return this;
    }
    
    /**
     * Set the chart left padding
     * @param l the left padding in pixels
     * @return 
     */
    public C3Chart setPaddingLeft(int l) {
        JSONObject padding = config.optJSONObject("padding");
        if (padding==null) {
            padding = new JSONObject();
        }
        padding.put("left", l);
        config.put("padding", padding);
        return this;
    }
    
    /**
     * Set Set custom color pattern.
     * @param cp a string array with color patterns to use
     * @return 
     */
    public C3Chart setColorPattern(List<String> cp) {
        JSONObject color = config.optJSONObject("color");
        if (color==null) {
            color = new JSONObject();
        }
        color.put("pattern", cp);
        config.put("color", color);
        return this;
    }
    
    /**
     * Indicate if the chart should have interactions.
     * If false is set, all of interactions (showing/hiding tooltip, selection, mouse events, etc) will be disabled.
     * 
     * @param i default: true
     * @return 
     */
    public C3Chart setEnableInteration(boolean i) {
        JSONObject interaction = config.optJSONObject("interaction");
        if (interaction==null) {
            interaction = new JSONObject();
        }
        interaction.put("enabled", i);
        config.put("interaction", interaction);
        return this;
    }
    
    
    /**
     * Set duration of transition (in milliseconds) for chart animation.
     * Note:
     * If 0 or null set, transition will be skipped. So, this makes initial rendering faster especially in case you have a lot of data.
     * 
     * @param d default: 350
     * @return 
     */
    public C3Chart setTransitionDuration(int d) {
        JSONObject transition = config.optJSONObject("transition");
        if (transition==null) {
            transition = new JSONObject();
        }
        transition.put("duration", d);
        config.put("transition", transition);
        return this;
    }
    
    /**
     * Set a callback to execute when the chart is initialized.
     * @param oi is the callback funtion to send the event
     * @return 
     */
    public C3Chart setOnInit(IOnInit oi) {
        this.onInit = oi;
        config.put("oninit", true);
        return this;
    }
    
    @ClientCallable
    private void onInitEvent() {
        this.onInit.onInit();
    }
    
    
    /**
     * Set a callback which is executed when the chart is rendered. Basically, this callback will be called in each time when the chart is redrawed.
     * Default:
     * function () {}
     * 
     * @param or is the callback funtion to send the event
     * @return 
     */
    public C3Chart setOnRendered(IOnRendered or) {
        this.onRendered = or;
        config.put("onrendered", true);
        return this;
    }
    
    @ClientCallable
    private void onRenderedEvent() {
        this.onRendered.onRendered();
    }
    
    
    /**
     * Set a callback to execute when mouse enters the chart.
     * Default:
     * function () {}
     * 
     * @param omo is the callback funtion to send the event
     * @return 
     */
    public C3Chart setOnMouseOver(IOnMouseOver omo) {
        this.onMouseOver = omo;
        config.put("onmouseover", true);
        return this;
    }
    
    @ClientCallable
    private void onMouseOverEvent() {
        this.onMouseOver.onMouseOver();
    }
    
    
    /**
     * Set a callback to execute when mouse leaves the chart.
     * Default:
     * function () {}
     * 
     * @param omo is the callback funtion to send the event
     * @return 
     */
    public C3Chart setOnMouseOut(IOnMouseOut omo) {
        this.onMouseOut = omo;
        config.put("onmouseout", true);
        return this;
    }
    
    @ClientCallable
    private void onMouseOutEvent() {
        this.onMouseOut.onMouseOut();
    }
    
    
    /**
     * Set a callback to execute when user resizes the screen.
     * @param or is the callback funtion to send the event
     * @return 
     */
    public C3Chart setOnResize(IOnResize or) {
        this.onResize = or;
        config.put("onresize", true);
        return this;
    }
    
    @ClientCallable
    private void onResizeEvent() {
        this.onResize.onResize();
    }
    
    /**
     * Set a callback to execute when screen resize finished.
     * @param or is the callback funtion to send the event
     * @return 
     */
    public C3Chart setOnResized(IOnResized or) {
        this.onResized = or;
        config.put("onresize", true);
        return this;
    }
    
    @ClientCallable
    private void onResizedEvent() {
        this.onResized.onResized();
    }
    
    //=====================================================
    // Data section 
    //=====================================================
    public C3Chart setData(C3Data d) {
        this.data = d;
        this.config.put("data", d.getDataConfig());
        return this;
    }
    //-----------------------------------------------------
    //---- Data callbacks redirections ----
    @ClientCallable
    private void dataOnClickEvent(JsonValue d) {
        this.data.fireOnClick(d);
    }
    
    @ClientCallable
    private void dataOnMouseOverEvent(JsonValue d) {
        this.data.fireOnMouseOver(d);
    }
    
    @ClientCallable
    private void dataOnMouseOutEvent(JsonValue d) {
        this.data.fireOnMouseOut(d);
    }
    
    
    //=====================================================
    
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
    public C3Chart addRegion(C3Region region) {
        JSONArray regions = config.optJSONArray("regions");
        if (regions==null) {
            regions = new JSONArray();
        }
        regions.put(region.getValues());
        
        // add Data Regions config to the Regions config
        config.put("regions", regions);
        return this;
    }
    
    
    
    public C3Chart setAxis(C3Axis a) {
        config.put("axis", a.getConfig());
        return this;
    }
    
    
    public C3Chart setGrid(C3Grid g) {
        this.config.put("grid", g.getConfig());
        return this;
    }
    
    /**
     * Configure legends
     * 
     * @param l
     * @return 
     */
    public C3Chart setLegend(C3Legend l) {
        legends = l;
        config.put("legend", l.getConfig());
        return this;
    }
    
    //-----------------------------------------------------
    //---- Data callbacks redirections ----
    @ClientCallable
    private void legendItemOnClickEvent(JsonValue d) {
        this.legends.fireOnClick(d);
    }
    
    @ClientCallable
    private void legendItemOnMouseOverEvent(JsonValue d) {
        this.legends.fireOnMouseOver(d);
    }
    
    @ClientCallable
    private void legendItemOnMouseOutEvent(JsonValue d) {
        this.legends.fireOnMouseOut(d);
    }
    
    //=====================================================
    
    
    public C3Chart setTooltip(C3Tooltip t) {
        config.put("tooltip", t.getConfig());
        return this;
    }
    
//    public C3Chart setSubchart(C3Subchart s) {
//        
//    }
//    
//    public C3Chart setZoom(C3Zoom z) {
//        
//    }
//  
    /**
     * Add config parameters for each chart type.
     * 
     * @param t one of chart types config.
     * @return 
     */
    public C3Chart setTypeConfig(C3TypeConfig t) {
        config.put(t.getTypeName(), t.getConfig());
        return this;
    }
    
    
    
}
