/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.awesomecontrols.chartlib;

import com.awesomecontrols.chartlib.c3wrapper.C3Axis;
import com.awesomecontrols.chartlib.c3wrapper.C3Bar;
import com.awesomecontrols.chartlib.c3wrapper.C3Chart;
import com.awesomecontrols.chartlib.c3wrapper.C3ChartType;
import com.awesomecontrols.chartlib.c3wrapper.C3Column;
import com.awesomecontrols.chartlib.c3wrapper.C3Data;
import com.awesomecontrols.chartlib.c3wrapper.C3DataRegion;
import com.awesomecontrols.chartlib.c3wrapper.C3Grid;
import com.awesomecontrols.chartlib.c3wrapper.C3GridXLine;
import com.awesomecontrols.chartlib.c3wrapper.C3GridYLine;
import com.awesomecontrols.chartlib.c3wrapper.C3Legend;
import com.awesomecontrols.chartlib.c3wrapper.C3Line;
import com.awesomecontrols.chartlib.c3wrapper.C3Load;
import com.awesomecontrols.chartlib.c3wrapper.C3Pie;
import com.awesomecontrols.chartlib.c3wrapper.C3Point;
import com.awesomecontrols.chartlib.c3wrapper.C3Region;
import com.awesomecontrols.chartlib.c3wrapper.C3Row;
import com.awesomecontrols.chartlib.c3wrapper.C3Tooltip;
import com.awesomecontrols.chartlib.c3wrapper.C3YAxis;
import com.awesomecontrols.chartlib.c3wrapper.ILegendItemOnClick;
import com.awesomecontrols.chartlib.c3wrapper.ILegendItemOnMouseOut;
import com.awesomecontrols.chartlib.c3wrapper.ILegendItemOnMouseOver;
import elemental.json.JsonValue;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo D. Ré {@literal <marcelo.re@gmail.com>}
 */
public class Test {
    private final static Logger LOGGER = Logger.getLogger(Test.class .getName());
    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }
    
    public static void main(String[] args) {
        Test test = new Test();
//        test.testChart();
        test.testRow();
//        test.testColumn();
    }
    
    public void testChart() {
        C3Chart c3chart = new C3Chart()
                            .setData(new C3Data()
                                        .addRegion("data1", new C3DataRegion().setStart(1).setEnd(2).setStyle("dashed"))
                                        .addRegion("data1", new C3DataRegion().setStart(3).setLabel("Region 2").setPaddingX(2).setPaddinY(2).setVertical(true))
                                        .addRegion("data2", new C3DataRegion().setStart(1).setEnd(2).setStyle("dashed"))
                                        .setColors(Map.of("data1", "#ff0000", 
                                                          "data2", "#00ff00", 
                                                          "data3", "#0000ff"))
                                        .setHide(List.of("data1", "data2"))
                                        .setEmptyLabelText("No Data")
                                        .setSelectionEnabled(true)
                                        .setSelectionGrouped(true)
                                        .setSelectionMultiple(true)
                                        .setSelectionDraggable(true)
                                        .setSelectionIsSelectable("function(d){return true;}")
                                        .setStackNormalize(true)
                                        .setDataTypes(Map.of("data1",C3ChartType.AREASPLINE,
                                                             "data2",C3ChartType.AREASTEP
                                                            )
                                        )
                            )
                            .addRegion(new C3Region().setAxis(C3Region.RegionAxis.x).setEnd(1).setClass("regionX"))
                            .addRegion(new C3Region().setAxis(C3Region.RegionAxis.x).setStart(2).setEnd(4).setClass("regionX"))
                            .addRegion(new C3Region().setAxis(C3Region.RegionAxis.x).setStart(5).setClass("regionX"))
                            .addRegion(new C3Region().setAxis(C3Region.RegionAxis.y).setEnd(50).setClass("regionY"))
                            
                            .setGrid(new C3Grid()
                                        .setXShow(true)
                                        .addXLines(new C3GridXLine().setValue(2).setText("Label on 2"))
                                        .addXLines(new C3GridXLine().setValue(5).setText("Label on 5").setClass("label-5"))
                                        .addXLines(new C3GridXLine().setValue(6).setText("Label on 6").setTextPosition(C3GridXLine.TextPosition.start))
                                    
                                        .addYLines(new C3GridYLine().setValue(50).setText("Label 50 for y"))
                                        .addYLines(new C3GridYLine().setValue(1300).setText("Label 1300 for y2").setAxis(C3YAxis.y2).setTextPosition(C3GridYLine.TextPosition.start))
                            )
                
                
                            .setLegend(new C3Legend().hide("data1","data2")
                                                     .position(C3Legend.Position.right)
                                                     .inset(C3Legend.Anchor.TOPLEFT, 10, 10, 2)
                                                     .setOnClick(new ILegendItemOnClick() {
                                                                    @Override
                                                                    public void onClick(JsonValue d) {
                                                                        LOGGER.log(Level.INFO, "LegendItemOnClick: "+d);
                                                                    }
                                                            })
                                                            .setOnMouseOver(new ILegendItemOnMouseOver() {
                                                                    @Override
                                                                    public void onMouseOver(JsonValue d) {
                                                                            LOGGER.log(Level.INFO, "LegendItemOnMouseOver: "+d);
                                                                    }
                                                            })
                                                            .setOnMouseOut(new ILegendItemOnMouseOut() {
                                                                    @Override
                                                                    public void onMouseOut(JsonValue d) {
                                                                            LOGGER.log(Level.INFO, "LegendItemOnMouseOut: "+d);
                                                                    }
                                                            })
                            
                            )
                
                            .setTooltip(new C3Tooltip()
                                                .formatName("function (name, ratio, id, index) { return name; }")
                                                .formatTittle("function (x, index) { return 'Data ' + x; }")
                                                .formatValue(" function (value, ratio, id, index) { return ratio; }")
                            )
                            
                
                            .setTypeConfig(new C3Point()
                                                .setFocusExpand(true)
                                                .setRadius(3)
                                                .setSelectedRadius(5)
                                    
                            )
                
                            .setTypeConfig(new C3Line().setConnectNull(true).setFocusExpand(C3Line.StepType.STEPAFTER))
                
                            .setTypeConfig(new C3Bar().setWidthRatio(0.7f).setZerobased(true))
                
                            .setTypeConfig(new C3Pie()
                                                .setLabelShow(true)
                                                .setLabelFormat("function (value, ratio, id) { return d3.format('$')(value);}")
                                                .setLabelThreshold(0.09)
                                                .setExpand(true)
                            )
                
                            .setAxis(new C3Axis()
                                        .setXCategories(List.of("cat1","cat2","cat3"))
                                        .setXTickCentered(true)
                            )
                
                             // DON'T FORGET TO ALWAYS CALL TO INITIALIZE AT THE END!!!!
                            .initialize()
                ;
        
        System.out.println(c3chart.getConfig().toString(4));
        
    }
    
    public void testRow() {
        C3Load r = new C3Load(new C3Row("data1","data2","data3")
                                .addRow(90, 120, 300)
                                .addRow(40, 160, 240))
                                .setChartType(C3ChartType.BAR)
                                .unload("data4");
        System.out.println(r.getJSONConfig().toString(4));
        List d = List.of(List.of(90, 120, 300),List.of(40, 160, 240));
        
        r = new C3Load(new C3Row("data1","data2","data3").addRows(d));
        
        System.out.println(r.getJSONConfig().toString(4));
    }
    
    public void testColumn() {
        C3Load c = new C3Load(new C3Column()
                                    .addColumn("data1", 130, 120, 150, 140, 160, 150)
                                    .addColumn("data4", List.of(30, 20, 50, 40, 60, 50))
                            )
                            .setChartType(C3ChartType.BAR)
                            .unload("data4")
                ;
        System.out.println(c.getJSONConfig().toString(4));
    }
}
