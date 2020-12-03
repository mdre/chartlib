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
public class C3Chart extends Component implements HasTheme, HasStyle, HasComponents {

    private final static Logger LOGGER = Logger.getLogger(C3Chart.class.getName());

    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.FINEST);
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
        getElement().callJsFunction("initialize", config.toString());
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
     *
     * @param w the width in pixels
     *
     * @return
     */
    public C3Chart setWidth(int w) {
        JSONObject size = config.optJSONObject("size");
        if (size == null) {
            size = new JSONObject();
        }
        size.put("width", w);
        config.put("size", size);
        return this;
    }

    /**
     * Set the chart height
     *
     * @param h the height in pixels
     *
     * @return
     */
    public C3Chart setHeight(int h) {
        JSONObject size = config.optJSONObject("size");
        if (size == null) {
            size = new JSONObject();
        }
        size.put("height", h);
        config.put("size", size);
        return this;
    }

    /**
     * Set the chart top padding
     *
     * @param t the top padding in pixels
     *
     * @return
     */
    public C3Chart setPaddingTop(int t) {
        JSONObject padding = config.optJSONObject("padding");
        if (padding == null) {
            padding = new JSONObject();
        }
        padding.put("top", t);
        config.put("padding", padding);
        return this;
    }

    /**
     * Set the chart right padding
     *
     * @param r the right padding in pixels
     *
     * @return
     */
    public C3Chart setPaddingRight(int r) {
        JSONObject padding = config.optJSONObject("padding");
        if (padding == null) {
            padding = new JSONObject();
        }
        padding.put("right", r);
        config.put("padding", padding);
        return this;
    }

    /**
     * Set the chart bottom padding
     *
     * @param b the bottom padding in pixels
     *
     * @return
     */
    public C3Chart setPaddingBottom(int b) {
        JSONObject padding = config.optJSONObject("padding");
        if (padding == null) {
            padding = new JSONObject();
        }
        padding.put("bottom", b);
        config.put("padding", padding);
        return this;
    }

    /**
     * Set the chart left padding
     *
     * @param l the left padding in pixels
     *
     * @return
     */
    public C3Chart setPaddingLeft(int l) {
        JSONObject padding = config.optJSONObject("padding");
        if (padding == null) {
            padding = new JSONObject();
        }
        padding.put("left", l);
        config.put("padding", padding);
        return this;
    }

    /**
     * Set Set custom color pattern.
     *
     * @param cp a string array with color patterns to use
     *
     * @return
     */
    public C3Chart setColorPattern(List<String> cp) {
        JSONObject color = config.optJSONObject("color");
        if (color == null) {
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
     *
     * @return
     */
    public C3Chart setEnableInteration(boolean i) {
        JSONObject interaction = config.optJSONObject("interaction");
        if (interaction == null) {
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
     *
     * @return
     */
    public C3Chart setTransitionDuration(int d) {
        JSONObject transition = config.optJSONObject("transition");
        if (transition == null) {
            transition = new JSONObject();
        }
        transition.put("duration", d);
        config.put("transition", transition);
        return this;
    }

    /**
     * Set a callback to execute when the chart is initialized.
     *
     * @param oi is the callback funtion to send the event
     *
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
     *
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
     *
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
     *
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
     *
     * @param or is the callback funtion to send the event
     *
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
     *
     * @param or is the callback funtion to send the event
     *
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
     * .addRegion(new C3Region().setAxis(C3Region.RegionAxis.x).setStart(2).setEnd(4).setClass("regionX"))
     * .addRegion(new C3Region().setAxis(C3Region.RegionAxis.x).setStart(5).setClass("regionX"))
     * .addRegion(new C3Region().setAxis(C3Region.RegionAxis.y).setEnd(50).setClass("regionY"))
     * ....
     * ....
     * .initialize();
     *
     */
    public C3Chart addRegion(C3Region region) {
        JSONArray regions = config.optJSONArray("regions");
        if (regions == null) {
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
     *
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
     *
     * @return
     */
    public C3Chart setTypeConfig(C3TypeConfig t) {
        config.put(t.getTypeName(), t.getConfig());
        return this;
    }

    //=====================================================
    // API
    //=====================================================
    /**
     * This API highlights specified targets and fade out the others.
     *
     * You can specify multiple targets by giving an array that includes id as String. If no argument is given, all of targets will be highlighted.
     * Arguments:
     *
     * .focus(targetIds)
     *
     * targetIds String or Array
     *
     * Target ids to be highlighted.
     *
     * @param targetIds
     *
     * @return
     */
    public C3Chart focus(List<String> targetIds) {
        JSONObject t = new JSONObject();
        t.put("targets", targetIds);
        LOGGER.log(Level.INFO, "param: " + t.toString(4));
        getElement().callJsFunction("focus", t.toString());
        return this;
    }

    /**
     * see focus(List<String> targetIds)
     *
     * @param targetId
     *
     * @return
     */
    public C3Chart focus(String targetId) {
        LOGGER.log(Level.INFO, "focus");
        List<String> l = List.of(targetId);
        this.focus(l);
        return this;
    }

    /**
     * This API fades out specified targets and reverts the others.
     *
     * You can specify multiple targets by giving an array that includes id as String. If no argument is given, all of targets will be faded out.
     * Arguments:
     *
     * .defocus(targetIds)
     *
     *
     * @param targetIds
     *
     * @return
     */
    public C3Chart defocus(List<String> targetIds) {
        JSONObject t = new JSONObject();
        t.put("targets", targetIds);
        LOGGER.log(Level.INFO, "param: " + t.toString(4));
        getElement().callJsFunction("defocus", t.toString());
        return this;
    }

    /**
     * see defocus(List<String> targetIds)
     *
     * @param targetId
     *
     * @return
     */
    public C3Chart defocus(String targetId) {
        this.defocus(List.of(targetId));
        return this;
    }

    /**
     * see defocus(List<String> targetIds)
     *
     * @param targetId
     *
     * @return
     */
    public C3Chart defocus() {
        this.defocus(List.of());
        return this;
    }

    /**
     * This API reverts specified targets.
     *
     * You can specify multiple targets by giving an array that includes id as String. If no argument is given, all of targets will be reverted.
     * Arguments:
     *
     * .revert(targetIds)
     *
     *
     * @param targetIds
     *
     * @return
     */
    public C3Chart revert(List<String> targetIds) {
        JSONObject t = new JSONObject();
        t.put("targets", targetIds);
        getElement().callJsFunction("revert", t.toString());
        return this;
    }

    /**
     * see revert(List<String> targetIds)
     *
     * @param targetId
     *
     * @return
     */
    public C3Chart revert(String targetId) {
        this.revert(List.of(targetId));
        return this;
    }

    /**
     * see revert(List<String> targetIds)
     *
     * @param targetId
     *
     * @return
     */
    public C3Chart revert() {
        this.revert(List.of());
        return this;
    }

    /**
     * This API shows specified targets.
     *
     * You can specify multiple targets by giving an array that includes id as String. If no argument is given, all of targets will be shown.
     * Arguments:
     *
     * .show(targetIds, options)
     *
     * @param targetIds
     *
     * @return
     */
    public C3Chart show(List<String> targetIds, Boolean withLegend) {
        JSONObject t = new JSONObject();
        t.put("targets", targetIds);

        if (withLegend != null) {
            t.put("withLegend", withLegend);
        }

        getElement().callJsFunction("show", t.toString());
        return this;
    }

    /**
     * see show(List<String> targetIds, Boolean withLegend)
     *
     * @param targetId
     *
     * @return
     */
    public C3Chart show(List<String> targetIds) {
        this.show(targetIds, null);
        return this;
    }

    /**
     * see show(List<String> targetIds)
     *
     * @param targetId
     *
     * @return
     */
    public C3Chart show(String targetId) {
        this.show(List.of(targetId));
        return this;
    }

    /**
     * see show(List<String> targetIds)
     *
     * @param targetId
     *
     * @return
     */
    public C3Chart show() {
        this.show(List.of());
        return this;
    }

    /**
     * This API hides specified targets.
     *
     * You can specify multiple targets by giving an array that includes id as String. If no argument is given, all of targets will be hidden.
     * Arguments:
     *
     * .hide(targetIds, options)
     *
     * targetIds String or Array
     *
     * Target ids to be hidden.
     *
     * options Object
     *
     * If withLegend is set true, legend will be hidden together with the specified data.
     *
     * @param targetIds
     * @param withLegend
     *
     * @return
     */
    public C3Chart hide(List<String> targetIds, Boolean withLegend) {
        JSONObject t = new JSONObject();
        t.put("targets", targetIds);

        if (withLegend != null) {
            t.put("withLegend", withLegend);
        }

        getElement().callJsFunction("hide", t.toString());
        return this;
    }

    /**
     * see hide(List<String> targetIds, Boolean withLegend)
     *
     * @param targetId
     *
     * @return
     */
    public C3Chart hide(List<String> targetIds) {
        this.hide(targetIds, null);
        return this;
    }

    /**
     * see hide(List<String> targetIds)
     *
     * @param targetId
     *
     * @return
     */
    public C3Chart hide(String targetId) {
        this.hide(List.of(targetId));
        return this;
    }

    /**
     * see hide(List<String> targetIds)
     *
     * @param targetId
     *
     * @return
     */
    public C3Chart hide() {
        this.hide(List.of());
        return this;
    }

    /**
     * This API toggles (shows or hides) specified targets.
     *
     * You can specify multiple targets by giving an array that includes id as String. If no argument is given, all of targets will be toggles.
     * Arguments:
     *
     * .toggle(targetIds, options)
     *
     * targetIds String or Array
     *
     * Target ids to be toggled.
     *
     * options Object
     *
     * If withLegend is set true, legend will be toggled together with the specified data.
     *
     * @param targetIds
     * @param withLegend
     *
     * @return
     */
    public C3Chart toggle(List<String> targetIds, Boolean withLegend) {
        JSONObject t = new JSONObject();
        t.put("targets", targetIds);

        if (withLegend != null) {
            t.put("withLegend", withLegend);
        }

        getElement().callJsFunction("toggle", t.toString());
        return this;
    }

    /**
     * see toggle(List<String> targetIds, Boolean withLegend)
     *
     * @param targetId
     *
     * @return
     */
    public C3Chart toggle(List<String> targetIds) {
        this.toggle(targetIds, null);
        return this;
    }

    /**
     * see toggle(List<String> targetIds)
     *
     * @param targetId
     *
     * @return
     */
    public C3Chart toggle(String targetId) {
        this.toggle(List.of(targetId));
        return this;
    }

    /**
     * see toggle(List<String> targetIds)
     *
     * @param targetId
     *
     * @return
     */
    public C3Chart toggle() {
        this.toggle(List.of());
        return this;
    }

    public C3Chart load(C3Load load) {
        LOGGER.log(Level.FINEST, "load");
        getElement().callJsFunction("load", load.getJSONConfig().toString());
        return this;
    }

    public C3Chart unload(List<String> ids) {
        JSONObject ul = new JSONObject();
        ul.put("ids", ids);
        getElement().callJsFunction("unload", ul.toString());
        return this;
    }

    public C3Chart flow(C3BaseData dataset) {
        LOGGER.log(Level.FINEST, "load");
        getElement().callJsFunction("load", dataset.getJSONConfig().toString());
        return this;
    }

    /**
     * Change data point state to selected.
     *
     * By this API, you can select data points. To use this API, data.selection.enabled needs to be set true.
     * Arguments:
     *
     * .select(ids, indices, resetOthers)
     *
     * ids:
     * Specify target ids to be selected. If this argument is not given, all targets will be the candidate.
     *
     * indices
     * Specify indices to be selected. If this argument is not given, all data points will be the candidate.
     *
     * resetOthers boolean
     *
     * If this argument is set true, the data points that are not specified by ids, indices will be unselected.
     *
     * @param ids Specify target ids to be selected. If this argument is not given, all targets will be the candidate.
     * @param idx Specify indices to be selected. If this argument is not given, all data points will be the candidate.
     * @param resetOthers If this argument is set true, the data points that are not specified by ids, indices will be unselected.
     *
     * @return
     */
    public C3Chart select(List<String> ids, List<Integer> idx) {
        JSONObject param = new JSONObject();
        if (ids != null) {
            param.put("ids", ids);
        }
        if (idx != null) {
            param.put("idx", idx);
        }
        getElement().callJsFunction("select ", param.toString());
        return this;
    }

    public C3Chart select(List<String> ids, List<Integer> idx, boolean resetOthers) {
        JSONObject param = new JSONObject();
        if (ids != null) {
            param.put("ids", ids);
        }
        if (idx != null) {
            param.put("idx", idx);
        }
        param.put("resetOthers", resetOthers);
        getElement().callJsFunction("select ", param.toString());
        return this;
    }

    /**
     * Change data point state to unselected.
     *
     * By this API, you can unselect data points. To use this API, data.selection.enabled needs to be set true.
     * Arguments:
     *
     * .unselect(ids, indices)
     *
     * ids Array
     *
     * Specify target ids to be unselected. If this argument is not given, all targets will be the candidate.
     *
     * indices Array
     *
     * Specify indices to be unselected. If this argument is not given, all data points will be the candidate.
     * Example:
     *
     * // all data points of data1 will be unselected.
     * chart.unselect(list.of("data1"));
     *
     * // 3 data points on index 1, 3, 5 of data1 will be unselected.
     * chart.unselect(list.of("data1"), list.of(1,3,5));
     *
     *
     * @param ids Specify target ids to be unselected. If this argument is not given, all targets will be the candidate.
     * @param idx Specify indices to be unselected. If this argument is not given, all data points will be the candidate.
     *
     * @return
     */
    public C3Chart unselect(List<String> ids, List<Integer> idx) {
        JSONObject param = new JSONObject();
        if (ids != null) {
            param.put("ids", ids);
        }
        if (idx != null) {
            param.put("idx", idx);
        }
        getElement().callJsFunction("unselect", param.toString());
        return this;
    }

    ISelected selected;

    /**
     * See selected(targetId)
     *
     * @param selected
     *
     * @return
     */
    public C3Chart selected(ISelected selected) {
        this.selected = selected;
        getElement().callJsFunction("selected");
        return this;
    }

    /**
     * Get selected data points.
     *
     * By this API, you can get selected data points information. To use this API, data.selection.enabled needs to be set true.
     * Arguments:
     *
     * .selected(targetId)
     *
     * targetId String
     *
     * You can filter the result by giving target id that you want to get. If not given, all of data points will be returned.
     * Example:
     *
     * // all selected data points will be returned.
     * chart.selected();
     *
     * // all selected data points of data1 will be returned.
     * chart.selected("data1");
     *
     *
     * @param id
     * @param selected
     *
     * @return
     */
    public C3Chart selected(String id, ISelected selected) {
        this.selected = selected;

        getElement().callJsFunction("selected", id);
        return this;
    }

    @ClientCallable
    private void selectedCallback(JsonValue result) {
        this.selected.selected(result);
    }

    /**
     * Change the type of the chart.
     * Arguments:
     *
     * .transform(type, targetIds)
     *
     * type String
     *
     * Specify the type to be transformed. The types listed in data.type can be used.
     *
     * targetIds String or Array
     *
     * Specify targets to be transformed. If not given, all targets will be the candidate.
     * Example:
     *
     * // all targets will be bar chart.
     * chart.transform("bar",null);
     *
     * // only data1 will be bar chart.
     * chart.transform("bar", List.of("data1"));
     *
     * // only data1 and data2 will be bar chart.
     * chart.transform("bar", List.of("data1", "data2"));
     *
     *
     * @param type
     * @param targetIds
     *
     * @return
     */
    public C3Chart transform(C3ChartType type, List<String> targetIds) {
        JSONObject param = new JSONObject();
        param.put("type", type.getType());
        if (targetIds != null) {
            param.put("ids", targetIds);
        }
        getElement().callJsFunction("transform", param.toString());
        return this;
    }

    /**
     * Update groups for the targets.Arguments:
     *
     * .groups(groups)
     *
     * groups Array
     *
     * This argument needs to be an Array that includes one or more Array that includes target ids to be grouped.
     * Example:
     *
     * // data1 and data2 will be a new group.
     * chart.groups(List.of(List.of("data1", "data2")));
     *
     * @param groups This argument needs to be an Array that includes one or more Array that includes target ids to be grouped.
     *
     * @return
     */
    public C3Chart group(List<List<String>> groups) {
        JSONObject param = new JSONObject();
        param.put("groups", groups);
        getElement().callJsFunction("groups", param.toString());
        return this;
    }

}
