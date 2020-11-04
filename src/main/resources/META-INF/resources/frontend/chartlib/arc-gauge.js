import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';
import { ThemableMixin } from '@vaadin/vaadin-themable-mixin/vaadin-themable-mixin.js';
import * as d3 from 'd3';
import * as c3 from 'c3';
import "@vaadin/flow-frontend/chartlib/arc-gauge-css-loader.js";


/**
 * `arc-gauge`
 * An arc gauge element
 *
 * @customElement
 * @polymer
 */
class ArcGauge extends ThemableMixin(PolymerElement) {
    static get template() {
        return html `
            <style>
                
            </style> 

            <div id="arcgauge" class="arcgauge" style="width: 200px; height: 200px;">
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
        return 'arc-gauge';
    }
    
    
    
    initialize(conf) {
        this.log("initialize ArcGauge");
        this.log(conf);
        this.arcConfig = JSON.parse(conf);
        this.$.arcgauge.style.width = "" + this.arcConfig.width + "px";
        this.$.arcgauge.style.height = "" + this.arcConfig.height + "px";
//        this.loadLiquidFillGauge(this.bubbleConfig);
        
        var chart = c3.generate({
                        bindto: this.$.arcgauge,
                        data: {
                            columns: [
                                ['data', 90.4]
                            ],
                            type: 'gauge',
                            onclick: function (d, i) { console.log("onclick", d, i); },
                            onmouseover: function (d, i) { console.log("onmouseover", d, i); },
                            onmouseout: function (d, i) { console.log("onmouseout", d, i); }
                        },
                        gauge: {
//                            label: {
//                                format: function(value, ratio) {
//                                    return value;
//                                },
//                                show: false // to turn off the min/max labels.
//                            },
//                            min: 0, // 0 is default, //can handle negative min e.g. vacuum / voltage / current flow / rate of change
//                            max: 100, // 100 is default
//                        units: ' %',
//                        width: 39 // for adjusting arc thickness
                        },
                        color: {
                            pattern: ['#FF0000', '#F97600', '#F6C600', '#60B044'], // the three color levels for the percentage values.
                            threshold: {
                    //            unit: 'value', // percentage is default
                    //            max: 200, // 100 is default
                                values: [30, 60, 90, 100]
                            }
                        },
                        size: {
                            height: 180
                        }
                    });
                    
        this.log("---- FIN INITIALIZE ----");
    }
  
    
};

customElements.define(ArcGauge.is, ArcGauge);