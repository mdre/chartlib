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
import org.json.JSONObject;

/**
 *
 * @author Marcelo D. Ré {@literal <marcelo.re@gmail.com>}
 */
public class C3Axis {

    private final static Logger LOGGER = Logger.getLogger(C3Axis.class.getName());

    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }

    public enum AxisXType {
        timeseries,
        category,
        indexed
    }

    public enum LabelPosition {
        INNERRIGHT("inner-right"),
        INNERCENTER("inner-center"),
        INNERLEFT("inner-left"),
        OUTERRIGHT("outer-right"),
        OUTERCENTER("outer-center"),
        OUTERLEFT("outer-left"),
        INNERTOP("inner-top"),
        INNERMIDDLE("inner-middle"),
        INNERBOTTOM("inner-bottom"),
        OUTERTOP("outer-top"),
        OUTERMIDDLE("outer-middle"),
        OUTERBOTTOM("outer-bottom");

        String type;

        private LabelPosition(String type) {
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

    public enum YScaleType {
        linear,
        time,
        timeseries,
        log
    }

    JSONObject config;

    public C3Axis() {
        this.config = new JSONObject();
    }

    public JSONObject getConfig() {
        return this.config;
    }

    public C3Axis setConfig(String c) {
        this.config = new JSONObject(c);
        return this;
    }

    //=====================================================
    /**
     * Switch x and y axis position.
     *
     * @param b
     *
     * @return
     */
    public C3Axis setRotated(boolean b) {
        config.put("rotated", b);
        return this;
    }

    /**
     * Show or hide x axis.
     *
     * @param b Default: true
     *
     * @return
     */
    public C3Axis setXShow(boolean b) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        x.put("show", b);

        config.put("x", x);

        return this;
    }

    /**
     * Set type of x axis.
     *
     * Available Values: timeseries, category, indexed
     *
     * Default: indexed
     *
     * @param type
     *
     * @return
     */
    public C3Axis setXType(AxisXType type) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        x.put("type", type);

        config.put("x", x);

        return this;
    }

    /**
     * Set how to treat the timezone of x values.
     *
     * If true, treat x value as localtime. If false, convert to UTC internally.
     *
     * @param b
     *
     * @return
     */
    public C3Axis setXLocalTime(boolean b) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        x.put("localtime", b);

        config.put("x", x);

        return this;
    }

    /**
     * Set category names on category axis.
     *
     * This must be an array that includes category names in string. If category names are included in the date by data.x option, this is not
     * required.
     *
     * @param categories
     *
     * @return
     */
    public C3Axis setXCategories(List<String> categories) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        x.put("categories", categories);

        config.put("x", x);

        return this;
    }

    /**
     * Centerise ticks on category axis.
     *
     * @param b
     *
     * @return
     */
    public C3Axis setXTickCentered(boolean b) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        JSONObject tick = x.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }
        tick.put("centered", b);

        x.put("tick", tick);

        config.put("x", x);

        return this;
    }

    /**
     * A function to format tick value. Format string is also available for timeseries data.
     *
     * @param f
     *
     * @return
     */
    public C3Axis setXTickFormat(String f) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        JSONObject tick = x.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }
        tick.put("format", f);

        x.put("tick", tick);

        config.put("x", x);

        return this;
    }

    /**
     * Setting for culling ticks.
     *
     * If true is set, the ticks will be culled, then only limitted tick text will be shown. This option does not hide the tick lines. If false is
     * set, all of ticks will be shown.
     *
     * We can change the number of ticks to be shown by axis.x.tick.culling.max. Default:
     *
     * true for indexed axis and timeseries axis false for category axis
     *
     * @param b
     *
     * @return
     */
    public C3Axis setXTickCulling(boolean b) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        JSONObject tick = x.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }
        tick.put("culling", b);

        x.put("tick", tick);

        config.put("x", x);

        return this;
    }

    /**
     * The number of tick texts will be adjusted to less than this value.
     *
     * @param max Default: 10
     *
     * @return
     */
    public C3Axis setXTickCullingMax(double max) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        JSONObject tick = x.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }

        JSONObject culling = tick.optJSONObject("culling");
        if (culling == null) {
            culling = new JSONObject();
        }
        culling.put("max", max);

        tick.put("culling", culling);

        x.put("tick", tick);

        config.put("x", x);

        return this;
    }

    /**
     * The number of x axis ticks to show.
     *
     * This option hides tick lines together with tick text. If this option is used on timeseries axis, the ticks position will be determined
     * precisely and not nicely positioned (e.g. it will have rough second value).
     *
     * @param c Default: undefined
     *
     * @return
     */
    public C3Axis setXTickCount(double c) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        JSONObject tick = x.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }
        tick.put("count", c);

        x.put("tick", tick);

        config.put("x", x);

        return this;
    }

    /**
     * Fit x axis ticks.
     *
     * If true set, the ticks will be positioned nicely. If false set, the ticks will be positioned according to x value of the data points.
     *
     * @param b Default: true
     *
     * @return
     */
    public C3Axis setXTickFit(boolean b) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        JSONObject tick = x.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }
        tick.put("fit", b);

        x.put("tick", tick);

        config.put("x", x);

        return this;
    }

    /**
     * Set the x values of ticks manually.
     *
     * If this option is provided, the position of the ticks will be determined based on those values. This option works with timeseries data and the
     * x values will be parsed accoding to the type of the value and data.xFormat option.
     *
     * @param values Default: null
     *
     * @return
     */
    public C3Axis setXTickValues(List<Double> values) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        JSONObject tick = x.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }
        tick.put("values", values);

        x.put("tick", tick);

        config.put("x", x);

        return this;
    }

    /**
     * Rotate x axis tick text.
     *
     * If you set negative value, it will rotate to opposite direction.
     *
     * @param r Default: 0
     *
     * @return
     */
    public C3Axis setXTickRotate(double r) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        JSONObject tick = x.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }
        tick.put("rotate", r);

        x.put("tick", tick);

        config.put("x", x);

        return this;
    }

    /**
     * Show x axis outer tick.
     *
     * @param b Default: true
     *
     * @return
     */
    public C3Axis setXTickOuter(boolean b) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        JSONObject tick = x.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }
        tick.put("outer", b);

        x.put("tick", tick);

        config.put("x", x);

        return this;
    }

    /**
     * Enable multiline.
     *
     * If this option is set true, when a tick's text on the x-axis is too long, it splits the text into multiple lines in order to avoid text
     * overlapping.
     *
     * @param b Default: true
     *
     * @return
     */
    public C3Axis setXTickMultiline(boolean b) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        JSONObject tick = x.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }
        tick.put("multiline", b);

        x.put("tick", tick);

        config.put("x", x);

        return this;
    }

    /**
     * If this option is set and is above 0, the number of lines will be adjusted to less than this value and tick's text is ellipsified.
     *
     * @param m
     *
     * @return
     */
    public C3Axis setXTickMultilineMax(double m) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        JSONObject tick = x.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }
        tick.put("multilineMax", m);

        x.put("tick", tick);

        config.put("x", x);

        return this;
    }

    /**
     * Set max value of x axis range.
     *
     * @param m
     *
     * @return
     */
    public C3Axis setXMax(double m) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        x.put("max", m);

        config.put("x", x);

        return this;
    }

    /**
     * Set min value of x axis range.
     *
     * @param m
     *
     * @return
     */
    public C3Axis setXMin(double m) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        x.put("min", m);

        config.put("x", x);

        return this;
    }

    /**
     * Set padding for x axis.
     *
     * If this option is set, the range of x axis will increase/decrease according to the values. If no padding is needed in the ragen of x axis, 0
     * should be set. On category axis, this option will be ignored.
     *
     * @param left
     * @param right
     *
     * @return
     */
    public C3Axis setXPadding(double top, double right,  double bottom, double left ) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        JSONObject padding = x.optJSONObject("padding");
        if (padding == null) {
            padding = new JSONObject();
        }
        padding.put("left", left);
        padding.put("right", right);
        padding.put("bottom", bottom);
        padding.put("left", left);

        x.put("padding", padding);

        config.put("x", x);

        return this;
    }

    /**
     * Set height of x axis.
     *
     * The height of x axis can be set manually by this option. If you need more space for x axis, please use this option for that.
     * The unit is pixel.
     *
     * @param h
     *
     * @return
     */
    public C3Axis setXHeight(int h) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        x.put("height", h);

        config.put("x", x);

        return this;
    }

    /**
     * Set default extent for subchart and zoom. This can be an array or function that returns an array.
     *
     * @param e
     *
     * @return
     */
    public C3Axis setXExtent(List<Double> e) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        x.put("extent", e);

        config.put("x", x);

        return this;
    }

    public C3Axis setXLabel(String l) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        x.put("label", l);

        config.put("x", x);

        return this;
    }

    /**
     * Set label on x axis.
     *
     * You can set x axis label and change its position by this option. string and object can be passed and we can change the position by passing
     * object that has position key. Available position differs according to the axis direction (vertical or horizontal). If string set, the position
     * will be the default.
     *
     * If it's horizontal axis:
     *
     * - inner-right [default]
     * - inner-center
     * - inner-left
     * - outer-right
     * - outer-center
     * - outer-left
     *
     * If it's vertical axis:
     *
     * - inner-top [default]
     * - inner-middle
     * - inner-bottom
     * - outer-top
     * - outer-middle
     * - outer-bottom
     *
     *
     * @param l
     * @param position
     *
     * @return
     */
    public C3Axis setXLabel(String l, LabelPosition position) {
        JSONObject x = config.optJSONObject("x");
        if (x == null) {
            x = new JSONObject();
        }
        JSONObject label = x.optJSONObject("label");
        if (label == null) {
            label = new JSONObject();
        }
        label.put("text", l);
        label.put("position", position.getType());

        x.put("label", label);

        config.put("x", x);

        return this;
    }

    /**
     * Show or hide y axis.
     *
     * @param b
     *
     * @return
     */
    public C3Axis setYShow(boolean b) {
        JSONObject y = config.optJSONObject("y");
        if (y == null) {
            y = new JSONObject();
        }
        y.put("show", b);

        config.put("y", y);

        return this;
    }

    /**
     * Show y axis inside of the chart.
     *
     * @param b
     *
     * @return
     */
    public C3Axis setYInner(boolean b) {
        JSONObject y = config.optJSONObject("y");
        if (y == null) {
            y = new JSONObject();
        }
        y.put("inner", b);

        config.put("y", y);

        return this;
    }

    /**
     * Scale type for Y axis.
     * Available Values:
     *
     * linear
     * time¹
     * timeseries¹
     * log² Experimental
     *
     * ¹: The timeseries scale is an alias of time.
     *
     * ²: The log scale is experimental and may not work in all cases (stacked, etc.)
     * Default:
     * linear
     *
     * @param type
     *
     * @return
     */
    public C3Axis setYType(YScaleType type) {
        JSONObject y = config.optJSONObject("y");
        if (y == null) {
            y = new JSONObject();
        }
        y.put("type", type);

        config.put("y", y);

        return this;
    }

    /**
     * Set max value of y axis.
     * Note:
     *
     * Padding will be added based on this value, so if you don't need the padding, please set
     * axis.y.padding to disable it (e.g. axis.y.padding = 0).
     *
     * @param m
     *
     * @return
     */
    public C3Axis setYMax(double m) {
        JSONObject y = config.optJSONObject("y");
        if (y == null) {
            y = new JSONObject();
        }
        y.put("max", m);

        config.put("y", y);

        return this;
    }

    /**
     * Set min value of y axis.
     * Note:
     *
     * Padding will be added based on this value, so if you don't need the padding, please set
     * axis.y.padding to disable it (e.g. axis.y.padding = 0).
     *
     * @param m
     *
     * @return
     */
    public C3Axis setYMin(double m) {
        JSONObject y = config.optJSONObject("y");
        if (y == null) {
            y = new JSONObject();
        }
        y.put("min", m);

        config.put("y", y);

        return this;
    }

    /**
     * Change the direction of y axis.
     *
     * If true set, the direction will be from the top to the bottom.
     *
     * @param b
     *
     * @return
     */
    public C3Axis setYInverted(boolean b) {
        JSONObject y = config.optJSONObject("y");
        if (y == null) {
            y = new JSONObject();
        }
        y.put("inverted", b);

        config.put("y", y);

        return this;
    }

    /**
     * Set center value of y axis.
     *
     * @param c
     *
     * @return
     */
    public C3Axis setYCenter(double c) {
        JSONObject y = config.optJSONObject("y");
        if (y == null) {
            y = new JSONObject();
        }
        y.put("center", c);

        config.put("y", y);

        return this;
    }

    public C3Axis setYLabel(String l) {
        JSONObject y = config.optJSONObject("y");
        if (y == null) {
            y = new JSONObject();
        }
        y.put("label", l);

        config.put("y", y);

        return this;
    }

    /**
     * Set label on y axis.
     *
     * You can set x axis label and change its position by this option. string and object can be passed and we can change the position by passing
     * object that has position key. Available position differs according to the axis direction (vertical or horizontal). If string set, the position
     * will be the default.
     *
     * If it's horizontal axis:
     *
     * - inner-right [default]
     * - inner-center
     * - inner-left
     * - outer-right
     * - outer-center
     * - outer-left
     *
     * If it's vertical axis:
     *
     * - inner-top [default]
     * - inner-middle
     * - inner-bottom
     * - outer-top
     * - outer-middle
     * - outer-bottom
     *
     *
     * @param l
     * @param position
     *
     * @return
     */
    public C3Axis setYLabel(String l, LabelPosition position) {
        JSONObject y = config.optJSONObject("y");
        if (y == null) {
            y = new JSONObject();
        }
        JSONObject label = y.optJSONObject("label");
        if (label == null) {
            label = new JSONObject();
        }
        label.put("text", l);
        label.put("position", position.getType());

        y.put("label", label);

        config.put("y", y);

        return this;
    }

    /**
     * Set formatter for y axis tick text.
     *
     * This option accepts d3.format object as well as a function you define.
     *
     * @param f
     *
     * @return
     */
    public C3Axis setYTickFormat(String f) {
        JSONObject y = config.optJSONObject("y");
        if (y == null) {
            y = new JSONObject();
        }
        JSONObject tick = y.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }
        tick.put("format", f);

        y.put("tick", tick);

        config.put("y", y);

        return this;
    }

    /**
     * Show or hide outer tick.
     *
     * @param b
     *
     * @return
     */
    public C3Axis setYTickOuter(boolean b) {
        JSONObject y = config.optJSONObject("y");
        if (y == null) {
            y = new JSONObject();
        }
        JSONObject tick = y.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }
        tick.put("outer", b);

        y.put("tick", tick);

        config.put("y", y);

        return this;
    }

    /**
     * Set y axis tick values manually.
     *
     * @param values
     *
     * @return
     */
    public C3Axis setYTickValues(List<Double> values) {
        JSONObject y = config.optJSONObject("y");
        if (y == null) {
            y = new JSONObject();
        }
        JSONObject tick = y.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }
        tick.put("values", values);

        y.put("tick", tick);

        config.put("y", y);

        return this;
    }

    /**
     * Set the number of y axis ticks.
     * Note:
     *
     * The position of the ticks will be calculated precisely, so the values on the ticks will not be rounded nicely. In the case, axis.y.tick.format
     * or axis.y.tick.values will be helpful.
     *
     * @param c
     *
     * @return
     */
    public C3Axis setYTickCount(double c) {
        JSONObject y = config.optJSONObject("y");
        if (y == null) {
            y = new JSONObject();
        }
        JSONObject tick = y.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }
        tick.put("count", c);

        y.put("tick", tick);

        config.put("y", y);

        return this;
    }

    /**
     * Set padding for y axis.
     *
     * You can set padding for y axis to create more space on the edge of the axis. This option accepts object and it can include top and bottom. top,
     * bottom will be treated as pixels.
     *
     * @param top
     * @param bottom
     *
     * @return
     */
    public C3Axis setYPadding(int top, int bottom) {
        JSONObject y = config.optJSONObject("y");
        if (y == null) {
            y = new JSONObject();
        }
        JSONObject padding = y.optJSONObject("padding");
        if (padding == null) {
            padding = new JSONObject();
        }
        padding.put("top", top);
        padding.put("bottom", bottom);

        y.put("padding", padding);

        config.put("y", y);

        return this;
    }

    /**
     * Set default range of y axis.
     *
     * This option set the default value for y axis when there is no data on init.
     *
     * @param min
     * @param max
     *
     * @return
     */
    public C3Axis setYDefault(double min, double max) {
        JSONObject y = config.optJSONObject("y");
        if (y == null) {
            y = new JSONObject();
        }

        JSONArray def = y.optJSONArray("default");

        if (def == null) {
            def = new JSONArray();
        }
        def.put(min);
        def.put(max);

        y.put("default", def);

        config.put("y", y);

        return this;
    }

    
    
    /**
     * Show or hide y axis.
     *
     * @param b
     *
     * @return
     */
    public C3Axis setY2Show(boolean b) {
        JSONObject y2 = config.optJSONObject("y2");
        if (y2 == null) {
            y2 = new JSONObject();
        }
        y2.put("show", b);

        config.put("y2", y2);

        return this;
    }

    /**
     * Show y axis inside of the chart.
     *
     * @param b
     *
     * @return
     */
    public C3Axis setY2Inner(boolean b) {
        JSONObject y2 = config.optJSONObject("y2");
        if (y2 == null) {
            y2 = new JSONObject();
        }
        y2.put("inner", b);

        config.put("y2", y2);

        return this;
    }

    /**
     * Scale type for Y axis.
     * Available Values:
     *
     * linear
     * time¹
     * timeseries¹
     * log² Experimental
     *
     * ¹: The timeseries scale is an alias of time.
     *
     * ²: The log scale is experimental and may not work in all cases (stacked, etc.)
     * Default:
     * linear
     *
     * @param type
     *
     * @return
     */
    public C3Axis setY2Type(YScaleType type) {
        JSONObject y2 = config.optJSONObject("y2");
        if (y2 == null) {
            y2 = new JSONObject();
        }
        y2.put("type", type);

        config.put("y2", y2);

        return this;
    }

    /**
     * Set max value of y2 axis.
     * Note:
     *
     * Padding will be added based on this value, so if you don't need the padding, please set
     * axis.y.padding to disable it (e.g. axis.y.padding = 0).
     *
     * @param m
     *
     * @return
     */
    public C3Axis setY2Max(double m) {
        JSONObject y2 = config.optJSONObject("y2");
        if (y2 == null) {
            y2 = new JSONObject();
        }
        y2.put("max", m);

        config.put("y2", y2);

        return this;
    }

    /**
     * Set min value of y axis.
     * Note:
     *
     * Padding will be added based on this value, so if you don't need the padding, please set
     * axis.y.padding to disable it (e.g. axis.y.padding = 0).
     *
     * @param m
     *
     * @return
     */
    public C3Axis setY2Min(double m) {
        JSONObject y2 = config.optJSONObject("y2");
        if (y2 == null) {
            y2 = new JSONObject();
        }
        y2.put("min", m);

        config.put("y2", y2);

        return this;
    }

    /**
     * Change the direction of y axis.
     *
     * If true set, the direction will be from the top to the bottom.
     *
     * @param b
     *
     * @return
     */
    public C3Axis setY2Inverted(boolean b) {
        JSONObject y2 = config.optJSONObject("y2");
        if (y2 == null) {
            y2 = new JSONObject();
        }
        y2.put("inverted", b);

        config.put("y2", y2);

        return this;
    }

    /**
     * Set center value of y axis.
     *
     * @param c
     *
     * @return
     */
    public C3Axis setY2Center(double c) {
        JSONObject y2 = config.optJSONObject("y2");
        if (y2 == null) {
            y2 = new JSONObject();
        }
        y2.put("center", c);

        config.put("y2", y2);

        return this;
    }

    public C3Axis setY2Label(String l) {
        JSONObject y2 = config.optJSONObject("y2");
        if (y2 == null) {
            y2 = new JSONObject();
        }
        y2.put("label", l);

        config.put("y2", y2);

        return this;
    }

    /**
     * Set label on y axis.
     *
     * You can set x axis label and change its position by this option. string and object can be passed and we can change the position by passing
     * object that has position key. Available position differs according to the axis direction (vertical or horizontal). If string set, the position
     * will be the default.
     *
     * If it's horizontal axis:
     *
     * - inner-right [default]
     * - inner-center
     * - inner-left
     * - outer-right
     * - outer-center
     * - outer-left
     *
     * If it's vertical axis:
     *
     * - inner-top [default]
     * - inner-middle
     * - inner-bottom
     * - outer-top
     * - outer-middle
     * - outer-bottom
     *
     *
     * @param l
     * @param position
     *
     * @return
     */
    public C3Axis setY2Label(String l, LabelPosition position) {
        JSONObject y2 = config.optJSONObject("y2");
        if (y2 == null) {
            y2 = new JSONObject();
        }
        JSONObject label = y2.optJSONObject("label");
        if (label == null) {
            label = new JSONObject();
        }
        label.put("text", l);
        label.put("position", position.getType());

        y2.put("label", label);

        config.put("y2", y2);

        return this;
    }

    /**
     * Set formatter for y axis tick text.
     *
     * This option accepts d3.format object as well as a function you define.
     *
     * @param f
     *
     * @return
     */
    public C3Axis setY2TickFormat(String f) {
        JSONObject y2 = config.optJSONObject("y");
        if (y2 == null) {
            y2 = new JSONObject();
        }
        JSONObject tick = y2.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }
        tick.put("format", f);

        y2.put("tick", tick);

        config.put("y2", y2);

        return this;
    }

    /**
     * Show or hide outer tick.
     *
     * @param b
     *
     * @return
     */
    public C3Axis setY2TickOuter(boolean b) {
        JSONObject y2 = config.optJSONObject("y2");
        if (y2 == null) {
            y2 = new JSONObject();
        }
        JSONObject tick = y2.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }
        tick.put("outer", b);

        y2.put("tick", tick);

        config.put("y2", y2);

        return this;
    }

    /**
     * Set y axis tick values manually.
     *
     * @param values
     *
     * @return
     */
    public C3Axis setY2TickOuter(List<Double> values) {
        JSONObject y2 = config.optJSONObject("y2");
        if (y2 == null) {
            y2 = new JSONObject();
        }
        JSONObject tick = y2.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }
        tick.put("values", values);

        y2.put("tick", tick);

        config.put("y2", y2);

        return this;
    }

    /**
     * Set the number of y axis ticks.
     * Note:
     *
     * The position of the ticks will be calculated precisely, so the values on the ticks will not be rounded nicely. In the case, axis.y.tick.format
     * or axis.y.tick.values will be helpful.
     *
     * @param c
     *
     * @return
     */
    public C3Axis setY2TickCount(double c) {
        JSONObject y2 = config.optJSONObject("y2");
        if (y2 == null) {
            y2 = new JSONObject();
        }
        JSONObject tick = y2.optJSONObject("tick");
        if (tick == null) {
            tick = new JSONObject();
        }
        tick.put("count", c);

        y2.put("tick", tick);

        config.put("y2", y2);

        return this;
    }

    /**
     * Set padding for y axis.
     *
     * You can set padding for y axis to create more space on the edge of the axis. This option accepts object and it can include top and bottom. top,
     * bottom will be treated as pixels.
     *
     * @param top
     * @param bottom
     *
     * @return
     */
    public C3Axis setY2Padding(int top, int bottom) {
        JSONObject y2 = config.optJSONObject("y2");
        if (y2 == null) {
            y2 = new JSONObject();
        }
        JSONObject padding = y2.optJSONObject("padding");
        if (padding == null) {
            padding = new JSONObject();
        }
        padding.put("top", top);
        padding.put("bottom", bottom);

        y2.put("padding", padding);

        config.put("y2", y2);

        return this;
    }

    /**
     * Set default range of y axis.
     *
     * This option set the default value for y axis when there is no data on init.
     *
     * @param min
     * @param max
     *
     * @return
     */
    public C3Axis setY2Default(double min, double max) {
        JSONObject y2 = config.optJSONObject("y");
        if (y2 == null) {
            y2 = new JSONObject();
        }

        JSONArray def = y2.optJSONArray("default");

        if (def == null) {
            def = new JSONArray();
        }
        def.put(min);
        def.put(max);

        y2.put("default", def);

        config.put("y2", y2);

        return this;
    }
    
}
