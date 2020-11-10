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
public class C3DataRegion {
    private final static Logger LOGGER = Logger.getLogger(C3DataRegion.class .getName());
    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }
    
    JSONObject reg;

    public C3DataRegion() {
        reg = new JSONObject();
    }

    public C3DataRegion setStart(double start) {
        this.reg.put("start",start);
        return this;
    }

    public C3DataRegion setEnd(double end) {
        this.reg.put("end",end);
        return this;
    }

    public C3DataRegion setStyle(String style) {
        this.reg.put("style",style);
        return this;
    }

    public C3DataRegion setLabel(String label) {
        this.reg.put("label",label);
        return this;
    }

    public C3DataRegion setPaddingX(int paddingX) {
        this.reg.put("paddingX",paddingX);
        return this;
    }

    public C3DataRegion setPaddinY(int paddinY) {
        this.reg.put("paddingY",paddinY);
        return this;
    }

    public C3DataRegion setVertical(boolean vertical) {
        this.reg.put("vertical",vertical);
        return this;
    }

    public JSONObject getValues() {
        return this.reg;
    }
    
}
