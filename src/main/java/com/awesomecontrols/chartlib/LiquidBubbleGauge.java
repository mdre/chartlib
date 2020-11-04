package com.awesomecontrols.chartlib;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

@Tag("liquid-bubble-gauge") 
@NpmPackage(value = "d3", version = "6.2.0")
@JsModule("./chartlib/liquid-bubble-gauge.js")
public class LiquidBubbleGauge extends Component implements HasTheme, HasStyle, HasComponents {
    private static final long serialVersionUID = 8843104328921005320L;

    private final static Logger LOGGER = Logger.getLogger(LiquidBubbleGauge.class.getName());
    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }
    
    JSONObject config;

    /**
     * Liquid Bubble Gauge component for Vaadin 14+
     * 
     * Based on the code published by Rob
     * https://codepen.io/rowinf/pen/pvBQNG?editors=0010
     * 
     * and 
     * 
     * @license Open source under BSD 2-clause (http://choosealicense.com/licenses/bsd-2-clause/)
     * Copyright (c) 2015, Curtis Bratton
     * All rights reserved.
     * https://bl.ocks.org/brattonc/5e5ce9beee483220e2f6#index.html
     */
    public LiquidBubbleGauge() {
        config = new JSONObject();
        config.put("minValue", 0); // The gauge minimum value.
        config.put("maxValue",100); // The gauge maximum value.
        config.put("circleThickness", 0.05); // The outer circle thickness as a percentage of it's radius.
        config.put("circleFillGap", 0.05); // The size of the gap between the outer circle and wave circle as a percentage of the outer circles radius.
        config.put("circleColor", "#178BCA"); // The color of the outer circle.
        config.put("waveHeight", 0.05); // The wave height as a percentage of the radius of the wave circle.
        config.put("waveCount", 1); // The number of full waves per width of the wave circle.
        config.put("waveRiseTime", 1000); // The amount of time in milliseconds for the wave to rise from 0 to it's final height.
        config.put("waveAnimateTime", 18000); // The amount of time in milliseconds for a full wave to enter the wave circle.
        config.put("waveRise", true); // Control if the wave should rise from 0 to it's full height, or start at it's full height.
        config.put("waveHeightScaling", true); // Controls wave size scaling at low and high fill percentages. When true, wave height reaches it's maximum at 50% fill, and minimum at 0% and 100% fill. This helps to prevent the wave from making the wave circle from appear totally full or empty when near it's minimum or maximum fill.
        config.put("waveAnimate", true); // Controls if the wave scrolls or is static.
        config.put("waveColor", "#178BCA"); // The color of the fill wave.
        config.put("waveOffset", 0); // The amount to initially offset the wave. 0 = no offset. 1 = offset of one full wave.
        config.put("textVertPosition", .5); // The height at which to display the percentage text withing the wave circle. 0 = bottom, 1 = top.
        config.put("textSize", 1); // The relative height of the text to display in the wave circle. 1 = 50%
        config.put("valueCountUp", true); // If true, the displayed value counts up from 0 to it's final value upon loading. If false, the final value is displayed.
        config.put("displayPercent", true); // If true, a % symbol is displayed after the value.
        config.put("textColor", "#045681"); // The color of the value text when the wave does not overlap it.
        config.put("waveTextColor", "#A4DBf8"); // The color of the value text when the wave overlaps it.
        config.put("liquidLevel",0.0);
        config.put("radious",100);
        
        
    }
    
    /**
     * Set the liquid level content 
     * @param level default: 0.0
     */
    public LiquidBubbleGauge setLiquidLevel(float level) {
        config.put("liquidLevel",level);
        return this;
    }
    
    /**
     * The gauge minimum value.
     * @param min default: 0
     * @return 
     */
    public LiquidBubbleGauge setMinValue(int min) {
        config.put("minValue", min); // The gauge minimum value.
        return this;
    }
    
    /**
     * The gauge maximum value.
     * @param max defalult: 100
     * @return 
     */
    public LiquidBubbleGauge setMaxValue(int max) {
        config.put("maxValue",max); // The gauge maximum value.
        return this;
    }
    
    /**
     * The outer circle thickness as a percentage of it's radius.
     * @param tick defalult: 0.05
     * @return 
     */
    public LiquidBubbleGauge setCircleThickness(float tick) {
        config.put("circleThickness", tick); // The outer circle thickness as a percentage of it's radius.
        return this;
    }
    
    /**
     * The size of the gap between the outer circle and wave circle as a percentage of the outer circles radius.
     * @param gap  defalult: 0.05
     * @return 
     */
    public LiquidBubbleGauge setCircleFillGap(float gap) {
        config.put("circleFillGap", gap); // The size of the gap between the outer circle and wave circle as a percentage of the outer circles radius.
        return this;
    }
    
    /**
     * The color of the outer circle.
     * @param color default: "#178BCA"
     * @return 
     */
    public LiquidBubbleGauge setCircleColor(String color) {
        config.put("circleColor", color); // The color of the outer circle.
        return this;
    }
    
    
    /**
     * The wave height as a percentage of the radius of the wave circle.
     * @param height  default: 0.05
     * @return 
     */
    public LiquidBubbleGauge setWaveHeight(float height) {
        config.put("waveHeight", height); // The wave height as a percentage of the radius of the wave circle.
        return this;
    }
    
    /**
     * The number of full waves per width of the wave circle.
     * @param count default: 1
     * @return 
     */
    public LiquidBubbleGauge setWaveCount(int count) {
        config.put("waveCount", count); // The number of full waves per width of the wave circle.
        return this;
    }
    
    /**
     * The amount of time in milliseconds for the wave to rise from 0 to it's final height.
     * 
     * @param  risetime  default: 1000
     * @return 
     */
    public LiquidBubbleGauge setWaveRiseTime(int risetime) {
        config.put("waveRiseTime", risetime); // The amount of time in milliseconds for the wave to rise from 0 to it's final height.
        return this;
    }
    
    /**
     * The amount of time in milliseconds for a full wave to enter the wave circle.
     * 
     * @param animateTime default: 18000
     * @return 
     */
    public LiquidBubbleGauge setWaveAnimateTime(int animateTime) {
        config.put("waveAnimateTime", animateTime); // The amount of time in milliseconds for a full wave to enter the wave circle.
        return this;
    }
    
    /**
     * Control if the wave should rise from 0 to it's full height, or start at it's full height.
     * @param wr default: true
     * @return 
     */
    public LiquidBubbleGauge setWaveRise(boolean wr) {
        config.put("waveRise", wr); // Control if the wave should rise from 0 to it's full height, or start at it's full height.
        return this;
    }
    
    /**
     * Controls wave size scaling at low and high fill percentages. When true, wave height reaches it's maximum at 50% fill, 
     * and minimum at 0% and 100% fill. This helps to prevent the wave from making the wave circle from appear totally full 
     * or empty when near it's minimum or maximum fill.
     * 
     * @param scaling default: true
     * @return 
     */
    public LiquidBubbleGauge setWaveHeightScaling(boolean scaling) {
        config.put("waveHeightScaling", scaling); // Controls wave size scaling at low and high fill percentages. When true, wave height reaches it's maximum at 50% fill, and minimum at 0% and 100% fill. This helps to prevent the wave from making the wave circle from appear totally full or empty when near it's minimum or maximum fill.
        return this;
    }
    
    /**
     * Controls if the wave scrolls or is static.
     * @param animate default: true
     * @return 
     */
    public LiquidBubbleGauge setWaveAnimate(boolean animate) {
        config.put("waveAnimate", animate); // Controls if the wave scrolls or is static.
        return this;
    }
    
    /**
     * The color of the fill wave.
     * 
     * @param color default: "#178BCA"
     * @return 
     */
    public LiquidBubbleGauge setWaveColor(String color) {
        config.put("waveColor", color); // The color of the fill wave.
        return this;
    }
    
    /**
     * The amount to initially offset the wave. 0 = no offset. 1 = offset of one full wave.
     * @param offset default: 0
     * @return 
     */
    public LiquidBubbleGauge setWaveOffset(float offset) {
        config.put("waveOffset", offset); // The amount to initially offset the wave. 0 = no offset. 1 = offset of one full wave.
        return this;
    }
    
    /**
     * The height at which to display the percentage text withing the wave circle. 0 = bottom, 1 = top.
     * 
     * @param position default: 0.5
     * @return 
     */
    public LiquidBubbleGauge setTextVertPosition(float position) {
        config.put("textVertPosition", position); // The height at which to display the percentage text withing the wave circle. 0 = bottom, 1 = top.
        return this;
    }
    
    /**
     * The relative height of the text to display in the wave circle. 1 = 50%
     * @param size default: 1
     * @return 
     */
    public LiquidBubbleGauge setTextSize(float size) {
        config.put("textSize", 1); // The relative height of the text to display in the wave circle. 1 = 50%
        return this;
    }
    
    /**
     * If true, the displayed value counts up from 0 to it's final value upon loading. If false, the final value is displayed.
     * 
     * @param countUp default: true
     * @return 
     */
    public LiquidBubbleGauge setValueCountUp(boolean countUp) {
        config.put("valueCountUp", countUp); // If true, the displayed value counts up from 0 to it's final value upon loading. If false, the final value is displayed.
        return this;
    }
    
    /**
     * If true, a % symbol is displayed after the value.
     * @param display default: true
     * @return 
     */
    public LiquidBubbleGauge setDisplayPercent(boolean display) {
        config.put("displayPercent", display); // If true, a % symbol is displayed after the value.
        return this;
    }
    
    /**
     * The color of the value text when the wave does not overlap it.
     * @param color default: "#045681"
     * @return 
     */
    public LiquidBubbleGauge setTextColor(String color) {
        config.put("textColor", color); // The color of the value text when the wave does not overlap it.
        return this;
    }
    
    /**
     * The color of the value text when the wave overlaps it.
     * @param color default: "#A4DBf8"
     * @return 
     */
    public LiquidBubbleGauge setWaveTextColor(String color) {
        config.put("waveTextColor", color); // The color of the value text when the wave overlaps it.
        return this;
    }
    
//    @Override
//    protected void onAttach(AttachEvent attachEvent) {
//        super.onAttach(attachEvent); //To change body of generated methods, choose Tools | Templates.
//        LOGGER.log(Level.FINEST, "onAttach");
//        getElement().callJsFunction("initialize",config.toString());
//    }
    
    /**
     * Set the radious in pixels
     * @param r default: 100px
     * @return 
     */
    public LiquidBubbleGauge setRadious(int r) {
        config.put("radious", r); // The color of the value text when the wave overlaps it.
        return this;
    }
    
    
    public LiquidBubbleGauge initialize() {
        LOGGER.log(Level.FINEST, "send config and initialize...");
        getElement().callJsFunction("initialize",config.toString());
        return this;
    }
    
    
}

