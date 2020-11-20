/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.awesomecontrols.chartlib.c3wrapper;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

/**
 *
 * @author Marcelo D. Ré {@literal <marcelo.re@gmail.com>}
 */
public class C3LoadRow extends C3Dataset {
    private final static Logger LOGGER = Logger.getLogger(C3LoadRow.class .getName());
    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }

    /**
     * Create a row dataset.
     * @param headers the header of each column
     */
    public C3LoadRow(String... headers) {
        super("row");
        this.config.put("rows", List.of(List.of(headers)));
    }
    
    public C3LoadRow addRow(Number... values) {
        JSONArray rows = config.optJSONArray("rows");
        rows.put(List.of(values));
        config.put("rows", rows);
        
        return this;
    }
    
    public C3LoadRow addRows(List<List<Number>> values) {
        List rows = config.optJSONArray("rows").toList();
        rows.addAll(values);
        config.put("rows", rows);
        
        return this;
    }
    
    
    public C3LoadRow setChartType(C3ChartType chartType) {
        config.put("type", chartType.getType());
        return this;
    }
    
    public C3LoadRow unload(String... ids) {
        
        config.put("unload", List.of(ids));
        return this;
    }
}
