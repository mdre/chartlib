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
public class C3Column extends C3BaseData {
    private final static Logger LOGGER = Logger.getLogger(C3Column.class .getName());
    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }

    public C3Column() {
        super("columns");
        config.put("columns", new JSONArray());
    }
    
    public C3Column addColumn(String id, Number... values) {
        JSONArray rows = config.optJSONArray("columns");
        ArrayList d = new ArrayList();
        d.add(id);
        d.addAll(List.of(values));
        rows.put(d);
        config.put("columns", rows);
        
        return this;
    }
    
    public C3Column addColumn(String id, List<Number> values) {
        JSONArray rows = config.optJSONArray("columns");
        ArrayList d = new ArrayList();
        d.add(id);
        d.addAll(values);
        rows.put(d);
        config.put("columns", rows);
        
        return this;
    }
    
}
