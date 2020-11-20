/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.awesomecontrols.chartlib.c3wrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

/**
 *
 * @author Marcelo D. Ré {@literal <marcelo.re@gmail.com>}
 */
public class C3LoadColumn extends C3Dataset {
    private final static Logger LOGGER = Logger.getLogger(C3LoadColumn.class .getName());
    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }

    public C3LoadColumn() {
        super("columns");
        config.put("columns", new JSONArray());
    }
    
    public C3LoadColumn addColumn(String id, Number... values) {
        JSONArray rows = config.optJSONArray("columns");
        ArrayList d = new ArrayList();
        d.add(id);
        d.addAll(List.of(values));
        rows.put(d);
        config.put("columns", rows);
        
        return this;
    }
    
    public C3LoadColumn addColumn(String id, List<Number> values) {
        JSONArray rows = config.optJSONArray("columns");
        ArrayList d = new ArrayList();
        d.add(id);
        d.addAll(values);
        rows.put(d);
        config.put("columns", rows);
        
        return this;
    }
    
    public C3LoadColumn setChartType(C3ChartType chartType) {
        config.put("type", chartType.getType());
        return this;
    }
    
    public C3LoadColumn unload(String... ids) {
        
        config.put("unload", List.of(ids));
        return this;
    }
}
