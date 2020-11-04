/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.awesomecontrols.chartlib;

import com.awesomecontrols.chartlib.c3wrapper.C3Chart;
import com.awesomecontrols.chartlib.c3wrapper.C3Data;
import com.awesomecontrols.chartlib.c3wrapper.C3DataRegion;
import com.awesomecontrols.chartlib.c3wrapper.C3Grid;
import com.awesomecontrols.chartlib.c3wrapper.C3GridXLine;
import com.awesomecontrols.chartlib.c3wrapper.C3GridYLine;
import com.awesomecontrols.chartlib.c3wrapper.C3Legend;
import com.awesomecontrols.chartlib.c3wrapper.C3Region;
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
                            
                             // DON'T FORGET TO ALWAYS CALL TO INITIALIZE AT THE END!!!!
                            .initialize()
                ;
        
        System.out.println(c3chart.getConfig().toString(4));
        
    }
}
