/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomecontrols.chartlib.c3wrapper;

/**
 * C3 Chart types
 * @author Marcelo D. Ré {@literal <marcelo.re@gmail.com>}
 */
public enum C3ChartType {
    LINE("line"),
    SPLINE("spline"),
    STEP("step"),
    AREA("area"),
    AREASPLINE("area-spline"),
    AREASTEP("area-step"),
    BAR("bar"),
    SCATTER("scatter"),
    STANFORD("stanford"),
    PIE("pie"),
    DONUT("donut"),
    GAUGE("gauge");

    String type;

    private C3ChartType(String type) {
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
