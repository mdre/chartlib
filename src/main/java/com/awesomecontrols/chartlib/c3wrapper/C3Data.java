/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomecontrols.chartlib.c3wrapper;

import elemental.json.JsonValue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Marcelo D. Ré {@literal <marcelo.re@gmail.com>}
 */
public class C3Data {

    private final static Logger LOGGER = Logger.getLogger(C3Data.class.getName());

    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }

    JSONObject config;
    private IDataOnClick onClick;
    private IDataOnMouseOver onMouseOver;
    private IDataOnMouseOut onMouseOut;

    public C3Data() {
        this.config = new JSONObject();
    }

    public JSONObject getDataConfig() {
        return this.config;
    }

    /**
     * Load a CSV or JSON file from a URL. Note that this will not work if loading via the "file://" protocol as the most browsers will block
     * XMLHTTPRequests.
     *
     * @param url is the path to data.
     * @return
     */
    public C3Data setUrl(String url) {
        this.config.remove("url");
        this.config.put("url", url);
        return this;
    }

    /**
     * Parse a JSON object for data. See also data.keys.
     * https://c3js.org/reference.html#data-json
     * 
     * @param json: an array of data.
     * @return
     */
    public C3Data setJsonData(JSONObject json) {
        this.config.remove("json");
        this.config.put("json", json);
        return this;
    }

    /**
     * Load data from a multidimensional array, with the first element containing the data names, the following containing related data in that order.
     * Format: 
     * ArrayList<List> data = new ArrayList<>(); 
     * data.add(List.of("data1", "data2", "data3")); 
     * data.add(List.of(90, 120, 300));
     * data.add(List.of(40, 160, 240)); 
     * data.add(List.of(50, 200, 290)); 
     * data.add(List.of(120, 160, 230)); 
     * data.add(List.of(80, 130, 300));
     * data.add(List.of(90, 220, 320));
     *
     * chart.setRowData(data);
     *
     * @param rows
     * @return
     */
    public C3Data setRowData(List<List> rows) {
        this.config.put("rows", rows);
        return this;
    }

    /**
     * Set data from a multidimensional array, with each element containing an array consisting of a datum name and associated data values.
     *
     * Format: 
     * ArrayList<List> data = new ArrayList<>(); 
     * data.add(List.of("data1", 30, 20, 50, 40, 60, 50)); 
     * data.add(List.of("data2", 200, 130, 90, 240, 130, 220)); 
     * data.add(List.of("data3", 300, 200, 160, 400, 250, 250));
     *
     * chart.setColumnsData(data);
     *
     * @param columns
     * @return
     */
    public C3Data setColumnsData(List<List> columns) {
        this.config.put("columns", columns);
        return this;
    }
    
    /**
     * Load data from a multidimensional array, with each element containing an array 
     * consisting of a datum name and associated data values.
     * Ex: addColumnData(List.of('data1', 30, 20, 50, 40, 60, 50))
     * 
     * @param values
     * @return 
     */
    public C3Data addColumnData(List values) {
        JSONArray columns = this.config.optJSONArray("columns");
        if (columns==null) {
            columns = new JSONArray();
        }
        columns.put(values);
        this.config.put("columns", columns);
        return this;
    }

    
    /**
     * Used if loading JSON via data.url:
     *
     * @param mime ex: "json"
     * @return
     */
    public C3Data setMimeType(String mime) {
        this.config.put("mimeType", mime);
        return this;
    }

    /**
     * Choose which JSON object keys correspond to desired data.
     * https://c3js.org/reference.html#data-keys
     * 
     * @param keys
     * @return
     */
    public C3Data setKeys(List<String> keys) {
        JSONObject value = new JSONObject();
        value.put("value", keys);
        this.config.put("keys", value);
        return this;
    }

    /**
     * Specify the key of x values in the data. We can show the data with non-index x values by this option. 
     * This option is required when the type of x axis is timeseries. If this option is set on category axis, 
     * the values of the data on the key will be used for category names.
     *  
     *
     * @param xData
     * @return
     */
    public C3Data setXKey(String xData) {
        this.config.put("x", xData);
        return this;
    }

    /**
     * Specify the keys of the x values for each data. This option can be used if we want to show the data that has different x values.
     *
     * Note: data.x should be used if the all of data have same x values.
     *
     * @param xs
     * @return
     */
    public C3Data setXSKey(Map<String, String> xs) {
        this.config.put("xs", xs);
        return this;
    }

    /**
     * Set a format to parse string specified as x. Default: %Y-%m-%d
     *
     * @param f string format: '%Y-%m-%d %H:%M:%S'
     * @return
     */
    public C3Data setXFormat(String f) {
        this.config.put("xFormat", f);
        return this;
    }

    
    public C3Data setEpochs(String e) {
        this.config.put("epochs", e);
        return this;
    }

    
    
    /**
     * Set custom data name.
     * 
     * @param names custom names
     * @return
     */
    public C3Data setNames(Map<String, String> names) {
        this.config.put("names", names);
        return this;
    }

    /**
     * Set custom data class. If this option is specified, the element g for the data has an additional class that has the prefix c3-target- (e.g.
     * c3-target-additional-data1-class).
     *
     * @param classes
     * @return
     */
    public C3Data setClasses(Map<String, String> classes) {
        this.config.put("classes", classes);
        return this;
    }

    /**
     * add groups for the data for stacking.
     *
     * @param groups
     * @return
     */
    public C3Data addGroup(List<String> group) {
        JSONArray groups = config.optJSONArray("groups");
        if (groups==null) {
            groups = new JSONArray();
        }
        groups.put(group);
        this.config.put("groups", groups);
        return this;
    }
    
    /**
     * Set y axis the data related to. y and y2 can be used.
     *
     * @param axes
     * @return
     */
    public C3Data setAxes(Map<String, String> axes) {
        this.config.put("axes", axes);
        return this;
    }

    /**
     * Set chart type at once.
     * If this option is specified, the type will be applied to every data. This setting can be overwritten by data.types.
     * 
     * @param type
     * @return 
     */
    public C3Data setChartType(C3ChartType type) {
        this.config.put("type", type.getType());
        return this;
    }

    /**
     * Set chart type for each data.
     * This setting overwrites ChartType setting.
     * 
     * @param types
     * @return 
     */
    public C3Data setDataTypes(Map<String,C3ChartType> types) {
        HashMap<String,String> t = new HashMap<>();
        //transform types to it string value
        types.forEach((k,v)-> t.put(k, v.getType()));
        this.config.put("types", t);
        return this;
    }

    /**
     * Show labels on each data points.
     * @param show Default: false
     * @return 
     */
    public C3Data showLabels(boolean show) {
        this.config.put("labels", show);
        return this;
    }
    
    //TODO: implementar
//    public C3Data setLabelsFormat() {
//        this.config.put("labels", show);
//        return this;
//    }
    
    
    public enum DataOrder {
        DESC("desc"),
        ASC("asc"),
        NULL("null");
       
        private String order; 
        private DataOrder(String o) {
            this.order = o;
        }

        @Override
        public String toString() {
            return this.order;
        }
        
    }

    /**
     * Define the order of the data.
     * 
     * This option changes the order of stacking the data and pieces of pie/donut. If null specified, it will be 
     * the order the data loaded. If function specified, it will be used to sort the data and it will receive the 
     * data as argument.
     * 
     * Available Values:
     *     desc
     *     asc
     *     NOT IMPLEMENTED YET!! function (data1, data2) { ... }
     *     null
     * 
     * @param o Default: desc
     * @return 
     */
    public C3Data setOrder(DataOrder o ) {
        this.config.put("order", o);
        return this;
    }
    
    
    
    
    
    /**
     * Define regions for each data.
     * 
     * The values must be an array for each data and it should include an object that
     * has start, end, style. If start is not set, the start will be the first data point. 
     * If end is not set, the end will be the last data point.
     * 
     * Currently this option supports only line chart and dashed style. If this option specified, 
     * the line will be dashed only in the regions.
     * 
     * An optional label property can be provided to display a label for the region. If a label option is not
     * specified, no label will be displayed for the region. For each region, you may also specify the paddingY 
     * and paddingX options to control the position of label text. Finally, a vertical option can be used to 
     * identify whether or not the label text should be rotated 90 degrees.
     * 
     * Default: {}
     * 
     * @param data
     * @param r
     * @return 
     */
    public C3Data addRegion(String data, C3DataRegion r) {
        JSONObject regions = config.optJSONObject("regions");
        if (regions==null) {
            regions = new JSONObject();
        }
        // get data regions
        JSONArray dataRegions = regions.optJSONArray(data);
        if (dataRegions==null) {
            dataRegions = new JSONArray();
        }
        // add region to data region
        dataRegions.put(r.getValues());
        regions.put(data, dataRegions);
        
        // add Data Regions config to the Regions config
        config.put("regions", regions);
        return this;
    }
    
    /**
     * Set color converter function.
     * 
     * This option should a function and the specified function receives color (e.g. '#ff0000') and 
     * d that has data parameters like id, value, index, etc. And it must return a string that 
     * represents color (e.g. '#00ff00').
     * 
     * format: "function (color, d) { ... }"
     * 
     * Default: undefined
     * @param color must be a valid javascript funtion.
     * @return 
     */
    public C3Data setColor(String color ) {
        this.config.put("colors", color);
        return this;
    }
    
    
    /**
     * Set color for each data.
     * Default: {}
     * Ex: c3data.setColors(Map.of("data1", "#ff0000", 
     *                             "data2", "#00ff00", 
     *                             "data3", "#0000ff"));
     * @param colors
     * @return 
     */
    public C3Data setColors(Map<String,String> colors ) {
        this.config.put("colors", colors);
        return this;
    }
    
    /**
     * Hide each data when the chart appears.
     * If true specified, all of data will be hidden.
     * @param h deafult: false
     * @return 
     */
    public C3Data setHide(boolean h ) {
        this.config.put("hide", h);
        return this;
    }
    
    /**
     * Hide each data when the chart appears.
     * 
     * If multiple ids specified as an array, those will be hidden.
     * 
     * @param h default: true.
     * @return 
     */
    public C3Data setHide(List<String> h ) {
        this.config.put("hide", h);
        return this;
    }
    
    
    /**
     * Set text displayed when empty data.
     * 
     * @param t
     * @return 
     */
    public C3Data setEmptyLabelText(String t) {
        JSONObject empty = config.optJSONObject("empty");
        if (empty==null) {
            empty = new JSONObject();
        }
        // get data regions
        JSONObject label = empty.optJSONObject("label");
        if (label==null) {
            label = new JSONObject();
        }
        // add region to data region
        label.put("text",t);
        empty.put("label", label);
        
        // add Data Regions config to the Regions config
        config.put("empty", empty);
        return this;
    }
    
    
    /**
     * Set data selection enabled.
     * 
     * If this option is set true, we can select the data points and get/set its state of selection 
     * by API (e.g. select, unselect, selected).
     * @param s
     * @return 
     */
    public C3Data setSelectionEnabled(boolean s ) {
        JSONObject selection = config.optJSONObject("selection");
        if (selection==null) {
            selection = new JSONObject();
        }
        selection.put("enabled", s);
        
        this.config.put("selection", selection);
        return this;
    }
    
    /**
     * Set grouped selection enabled.
     * 
     * If this option set true, multiple data points that have same x value will be selected by one selection.
     * 
     * @param g
     * @return 
     */
    public C3Data setSelectionGrouped(boolean g ) {
        JSONObject selection = config.optJSONObject("selection");
        if (selection==null) {
            selection = new JSONObject();
        }
        selection.put("grouped", g);
        
        this.config.put("selection", selection);
        return this;
    }
    
    /**
     * Set multiple data points selection enabled.
     * If this option set true, multiple data points can have the selected state at the same time. 
     * If false set, only one data point can have the selected state and the others will be unselected 
     * when the new data point is selected.
     * 
     * Default: true
     * 
     * @param m
     * @return 
     */
    public C3Data setSelectionMultiple(boolean m ) {
        JSONObject selection = config.optJSONObject("selection");
        if (selection==null) {
            selection = new JSONObject();
        }
        selection.put("multiple", m);
        
        this.config.put("selection", selection);
        return this;
    }
    
    /**
     * Enable to select data points by dragging.
     * 
     * If this option set true, data points can be selected by dragging.
     * Note:
     * If this option set true, scrolling on the chart will be disabled because dragging event will handle the event.
     * 
     * Default: false
     * 
     * @param d
     * @return 
     */
    public C3Data setSelectionDraggable(boolean d ) {
        JSONObject selection = config.optJSONObject("selection");
        if (selection==null) {
            selection = new JSONObject();
        }
        selection.put("draggable", d);
        
        this.config.put("selection", selection);
        return this;
    }
    
    /**
     * Set a callback for each data point to determine if it's selectable or not.
     * 
     * The callback will receive d as an argument and it has some parameters like id, value, index. 
     * This callback should return boolean.
     * 
     * Default: function () { return true; }
     * 
     * @param f
     * @return 
     */
    public C3Data setSelectionIsSelectable(String f ) {
        JSONObject selection = config.optJSONObject("selection");
        if (selection==null) {
            selection = new JSONObject();
        }
        selection.put("isselectable", f);
        
        this.config.put("selection", selection);
        return this;
    }
    
    
    /**
     * Set the stacking to be normalized
     * 
     * For stacking, the `data.groups` option should be set and have positive values. 
     * The yAxis will be set in percentage value (0 ~ 100%).
     * 
     * Default: false
     * 
     * @param n 
     * @return 
     */
    public C3Data setStackNormalize(boolean n) {
        JSONObject stack = config.optJSONObject("stack");
        if (stack==null) {
            stack = new JSONObject();
        }
        stack.put("normalize", n);
        
        this.config.put("stack", stack);
        return this;
    }
    
    /**
     * Set a callback for click event on each data point.
     * 
     * This callback will be called when each data point clicked and will receive d and element as the 
     * arguments. d is the data clicked and element is the element clicked. In this callback, this will 
     * be the Chart object.
     * 
     * @param onClick
     * @return 
     */
    public C3Data setOnClick(IDataOnClick onClick) {
        this.onClick = onClick;
        this.config.put("onclick",true);
        return this;
    }

    void fireOnClick(JsonValue d) {
        this.onClick.onClick(d);
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
    public C3Data setOnMouseOver(IDataOnMouseOver omo) {
        this.onMouseOver = omo;
        this.config.put("onmouseover", true);
        return this;
    }
    
    void fireOnMouseOver(JsonValue d) {
        this.onMouseOver.onMouseOver(d);
    }
    
    
    public C3Data setOnMouseOut(IDataOnMouseOut omo) {
        this.onMouseOut = omo;
        this.config.put("onmouseout", true);
        return this;
    }
    
    void fireOnMouseOut(JsonValue d) {
        this.onMouseOut.onMouseOut(d);
    }
    
    
}
