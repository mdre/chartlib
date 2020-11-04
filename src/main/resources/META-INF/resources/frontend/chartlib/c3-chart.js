import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';
import { ThemableMixin } from '@vaadin/vaadin-themable-mixin/vaadin-themable-mixin.js';
import * as d3 from 'd3';
import * as c3 from 'c3';
import "@vaadin/flow-frontend/chartlib/c3-css-loader.js";


/**
 * `c3-chart`
 *
 * @customElement
 * @polymer
 */
class C3Chart extends ThemableMixin(PolymerElement) {
    static get template() {
        return html `
            <style>
                
            </style> 

            <div id="chart" class="chart" style="width: 200px; height: 200px;">
            </div>
            `;
    }

    log(...logVal) {
        if (this.logEnabled) {
            console.log(...logVal);
        }
    }
    
    constructor() {
        super();
        this.logEnabled=true;
    }
    
    static get is() {
        return 'c3-chart';
    }
    
    
    
    initialize(conf) {
        this.log("initialize Chart");
        this.log(conf);
        this.chartConfig = JSON.parse(conf);
        this.chartConfig.bindto = this.$.chart;
        this.$.chart.style.width = "" + this.chartConfig.width + "px";
        this.$.chart.style.height = "" + this.chartConfig.height + "px";
        
        // replace reference call
        if (this.chartConfig.oninit) {
            this.log("onInit!!");
            this.chartConfig.oninit = () => {this.$server.onInitEvent();};
            this.log(this.chartConfig.oninit);
        }
        
        if (this.chartConfig.onmouseover) {
            this.log("onMouseOver!!");
            this.chartConfig.onmouseover = () => {this.$server.onMouseOverEvent();};
            this.log(this.chartConfig.onmouseover);
        }
        
        //--------------------------------------------------------
        // Data section
        //--------------------------------------------------------
        //      callbacks
        //--------------------------------------------------------
        if (this.chartConfig.data.onclick) {
            this.log("dataOnClick!!");
            this.chartConfig.data.onclick = (d,e) => {this.$server.dataOnClickEvent(d);};
        }
        
        if (this.chartConfig.data.onmouseover) {
            this.log("dataOnMouseOver!!");
            this.chartConfig.data.onmouseover = (d) => {this.$server.dataOnMouseOverEvent(d);};
        }
        
        if (this.chartConfig.data.onmouseout) {
            this.log("dataOnMouseOut!!");
            this.chartConfig.data.onmouseout = (d) => {this.$server.dataOnMouseOutEvent(d);};
        }
        
        //--------------------------------------------------------
        //      functions
        //--------------------------------------------------------
        if (this.chartConfig.data.isselectable) {
            this.log("isSelectable!!");
            this.chartConfig.data.isselectable = convertToFunc(this.chartConfig.data.isselectable);
        }

        //--------------------------------------------------------
        // Legends section
        //--------------------------------------------------------
        //      callbacks
        //--------------------------------------------------------
        this.log("legend section");
        if (this.chartConfig.legend) {
            if (this.chartConfig.legend.item.onclick) {
                this.log("LegendItemOnClick!!");
                this.chartConfig.legend.item.onclick = (id) => {this.$server.legendItemOnClickEvent(id);};
            }
            this.log("legend 1");
            if (this.chartConfig.legend.item.onmouseover) {
                this.log("legendItemOnMouseOver!!");
                this.chartConfig.legend.item.onmouseover = (id) => {this.$server.legendItemOnMouseOverEvent(id);};
            }
            this.log("legend 2");

            if (this.chartConfig.legend.item.onmouseout) {
                this.log("legendItemOnMouseOut!!");
                this.chartConfig.legend.item.onmouseout = (id) => {this.$server.legendItemOnMouseOutEvent(id);};
            }
            this.log("legend 3");
        }
        
        //--------------------------------------------------------
        
        this.log("post-transform chart config");
        this.log(this.chartConfig);
        
        var chart = c3.generate(this.chartConfig);
                    
        this.log("---- FIN INITIALIZE ----");
    }
  
    // convert a String to a Javascript function
    convertToFunc(funcText) {
        var f = JSON.parse(funcText, function (key, value) {
                            if (value && (typeof value === 'string') && value.indexOf("function") === 0) {
                                // we can only pass a function as string in JSON ==> doing a real function
                                var jsFunc = new Function('return ' + value)();
                                return jsFunc;
                            }
                            return value;
                        });
        return f;
    }
};

customElements.define(C3Chart.is, C3Chart);