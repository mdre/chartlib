package com.awesomecontrols.chartlib;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

@Tag("arc-gauge") 
@JsModule("./chartlib/arc-gauge.js")
public class ArcGauge extends Component implements HasTheme, HasStyle, HasComponents {
    private static final long serialVersionUID = 8843104328921005320L;

    private final static Logger LOGGER = Logger.getLogger(ArcGauge.class.getName());
    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }
    
    JSONObject config;

    public ArcGauge() {
        config = new JSONObject();
        config.put("data",95);
    }
    
    public ArcGauge initialize() {
        LOGGER.log(Level.FINEST, "send config and initialize...");
        getElement().callJsFunction("initialize",config.toString());
        return this;
    }
    
    
}

