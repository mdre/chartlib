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

@Tag("arc-gauge") 
@NpmPackage(value = "d3", version = "6.2.0")
@NpmPackage(value = "c3", version = "0.7.20")
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
        config.put("width", 200);
        config.put("height", 200);
        
    }
    
    public ArcGauge initialize() {
        LOGGER.log(Level.FINEST, "send config and initialize...");
        getElement().callJsFunction("initialize",config.toString());
        return this;
    }
    
    
}

