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
        this.logEnabled = true;
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
            this.chartConfig.oninit = () => {
                this.$server.onInitEvent();
            };
            this.log(this.chartConfig.oninit);
        }

        if (this.chartConfig.onmouseover) {
            this.log("onMouseOver!!");
            this.chartConfig.onmouseover = () => {
                this.$server.onMouseOverEvent();
            };
            this.log(this.chartConfig.onmouseover);
        }

        //--------------------------------------------------------
        // Data section
        //--------------------------------------------------------
        //      callbacks
        //--------------------------------------------------------
        if (this.chartConfig.data.onclick) {
            this.log("dataOnClick!!");
            this.chartConfig.data.onclick = (d, e) => {
                this.$server.dataOnClickEvent(d);
            };
        }

        if (this.chartConfig.data.onmouseover) {
            this.log("dataOnMouseOver!!");
            this.chartConfig.data.onmouseover = (d) => {
                this.$server.dataOnMouseOverEvent(d);
            };
        }

        if (this.chartConfig.data.onmouseout) {
            this.log("dataOnMouseOut!!");
            this.chartConfig.data.onmouseout = (d) => {
                this.$server.dataOnMouseOutEvent(d);
            };
        }

        //--------------------------------------------------------
        //      functions
        //--------------------------------------------------------
        if (this.chartConfig.data.isselectable) {
            this.log("isSelectable!!");
            this.chartConfig.data.isselectable = parseFunction(this.chartConfig.data.isselectable);
        }

        if (this.chartConfig.data.color) {
            this.log("color!!");
            this.chartConfig.data.color = parseFunction(this.chartConfig.data.colors);
        }


        //--------------------------------------------------------
        // Legends section
        //--------------------------------------------------------
        //      callbacks
        //--------------------------------------------------------
        if (this.chartConfig.legend) {
            this.log("legend section");
            if (this.chartConfig.legend.item) {
                if (this.chartConfig.legend.item.onclick) {
                    this.log("LegendItemOnClick!!");
                    this.chartConfig.legend.item.onclick = (id) => {
                        this.$server.legendItemOnClickEvent(id);
                    };
                }
                if (this.chartConfig.legend.item.onmouseover) {
                    this.log("legendItemOnMouseOver!!");
                    this.chartConfig.legend.item.onmouseover = (id) => {
                        this.$server.legendItemOnMouseOverEvent(id);
                    };
                }
                if (this.chartConfig.legend.item.onmouseout) {
                    this.log("legendItemOnMouseOut!!");
                    this.chartConfig.legend.item.onmouseout = (id) => {
                        this.$server.legendItemOnMouseOutEvent(id);
                    };
                }
            }
        }

        //--------------------------------------------------------
        // Tooltip section
        //--------------------------------------------------------
        //--------------------------------------------------------
        //      functions
        //--------------------------------------------------------
        if (this.chartConfig.tooltip) {
            this.log("Tooltip section")
            if (this.chartConfig.tooltip.format) {
                if (this.chartConfig.tooltip.format.title) {
                    this.log("title func: " + this.chartConfig.tooltip.format.title);
                    this.chartConfig.tooltip.format.title = this.parseFunction(this.chartConfig.tooltip.format.title);
                    this.log("-----");
                }

                if (this.chartConfig.tooltip.format.name) {
                    this.log("name func: " + this.chartConfig.tooltip.format.name);
                    this.chartConfig.tooltip.format.name = this.parseFunction(this.chartConfig.tooltip.format.name);
                    this.log("-----");
                }
                
                if (this.chartConfig.tooltip.format.value) {
                    this.log("name func: " + this.chartConfig.tooltip.format.value);
                    this.chartConfig.tooltip.format.value = this.parseFunction(this.chartConfig.tooltip.format.value);
                    this.log("-----");
                }
            }
            
            if (this.chartConfig.tooltip.position) {
                this.chartConfig.tooltip.position = this.parseFunction(this.chartConfig.tooltip.position);
            }
            
            if (this.chartConfig.tooltip.contents) {
                this.chartConfig.tooltip.contents = this.parseFunction(this.chartConfig.tooltip.contents);
            }
        }



        //--------------------------------------------------------
        // Axis section
        //--------------------------------------------------------
        if (this.chartConfig.axis) {
            this.log("Axis section");
            try {
                if (this.chartConfig.axis.x.tick.format) {
                    this.chartConfig.axis.x.tick.format = this.parseFunction(this.chartConfig.axis.x.tick.format);
                }
                
            } catch(e){}
            
            try {
                if (this.chartConfig.axis.y.tick.format) {
                    this.chartConfig.axis.y.tick.format = this.parseFunction(this.chartConfig.axis.y.tick.format);
                }
                
            } catch(e){}
            
            try {
                if (this.chartConfig.axis.y2.tick.format) {
                    this.chartConfig.axis.y2.tick.format = this.parseFunction(this.chartConfig.axis.y2.tick.format);
                }
                
            } catch(e){}
        }
        
        
        //--------------------------------------------------------
        // Charts types section
        //--------------------------------------------------------
        if (this.chartConfig.pie) {
            this.log("pie config section");
            try {
                if (this.chartConfig.pie.label.format) {
                    this.log("pie label format");
                    this.pieLabelFormat = this.parseFunction(this.chartConfig.pie.label.format);
                    this.chartConfig.pie.label.format = (value, ratio, id) => { return this.pieLabelFormat(value, ratio, id);};
                    this.log("----------------");
                }
            } catch(e) {}
            this.log("----------- end pie section -----------");
        }


        
        if (this.chartConfig.stanford) {
            this.log("stanford section");
            if (this.chartConfig.stanford.scaleValues) {
                this.chartConfig.stanford.scaleValues = this.parseFunction(this.chartConfig.stanford.scaleValues);
            }
                
            if (this.chartConfig.stanford.colors) {
                //this.chartConfig.stanford.colors = this.parseFunction(this.chartConfig.stanford.colors);
            }
            
        }
        //--------------------------------------------------------

        this.log("post-transform chart config");
        this.log(this.chartConfig);

        this.chart = c3.generate(this.chartConfig);

        this.log("---- FIN INITIALIZE ----");
    }

    // convert a String to a Javascript function
    parseFunction(str, name) {
        var fn_body_idx = str.indexOf('{'),
            fn_body = str.substring(fn_body_idx + 1, str.lastIndexOf('}')),
            fn_declare = str.substring(0, fn_body_idx),
            fn_params = fn_declare.substring(fn_declare.indexOf('(') + 1, fn_declare.lastIndexOf(')')),
            args = fn_params.split(',');

        args.push(fn_body);

        function Fn() {
            return Function.apply(this, args);
        }
        Fn.prototype = Function.prototype;
        
        return new Fn();
    }
    
    //--------------------------------------------------------
    // API
    //--------------------------------------------------------
    focus(sTargetIds) {
        var targetIds = JSON.parse(sTargetIds);
        if (targetIds.targets.length>0) {
            this.chart.focus(targetIds.targets);
        } else {
            this.chart.focus();
        }
    }
    
    defocus(sTargetIds) {
        var targetIds = JSON.parse(sTargetIds);
        if (targetIds.targets.length>0) {
            this.chart.defocus(targetIds.targets);
        } else {
            this.chart.defocus();
        }
    }
    
    revert(sTargetIds) {
        var targetIds = JSON.parse(sTargetIds);
        if (targetIds.targets.length>0) {
            this.chart.revert(targetIds.targets);
        } else {
            this.chart.revert();
        }
    }
    
    show(sTargetIds) {
        var targetIds = JSON.parse(sTargetIds);
        this.log(targetIds);
        if (targetIds.targets.length>0) {
            if (!(typeof targetIds.withLegend === 'undefined')) {
               this.chart.show(targetIds.targets, {withLegend: targetIds.withLegend});
            } else {
                this.chart.show(targetIds.targets);
            }
        } else {
            this.chart.show();
        }
    }
    
    hide(sTargetIds) {
        var targetIds = JSON.parse(sTargetIds);
        this.log(targetIds);
        if (targetIds.targets.length>0) {
            if (!(typeof targetIds.withLegend === 'undefined')) {
               this.chart.hide(targetIds.targets, {withLegend: targetIds.withLegend});
            } else {
                this.chart.hide(targetIds.targets);
            }
        } else {
            this.chart.hide();
        }
    }
    
    
    toggle(sTargetIds) {
        var targetIds = JSON.parse(sTargetIds);
        this.log(targetIds);
        if (targetIds.targets.length>0) {
            if (!(typeof targetIds.withLegend === 'undefined')) {
               this.chart.toggle(targetIds.targets, {withLegend: targetIds.withLegend});
            } else {
                this.chart.toggle(targetIds.targets);
            }
        } else {
            this.chart.toggle();
        }
    }
    
    load(dataset) {
        var ds = JSON.parse(dataset);
        this.log(ds);
        this.chart.load(ds);
    }
    
    unload(ids) {
        var tIds = JSON.parse(ids);
        this.log(tIds);
        this.chart.unload(tIds);
    }
    
    flow(dataset) {
        var ds = JSON.parse(dataset);
        this.log(ds);
        this.chart.flow(ds);
    }
    
    select(param) {
        var p = JSON.parse(param);
        this.log(p);
        this.log(""+(p.ids));
        this.log(p.resetOthers);
        if (p.ids && p.idx && p.resetOthers) {
            this.log("ids+idx+ro");
            this.chart.select(p.ids,p.idx,p.resetOthers);
        } else if (p.ids && p.idx) {
            this.log("ids+idx");
            this.chart.select(p.ids, p.idx);
        } else if (p.ids) {
            this.log("ids");
            this.chart.select(p.ids);
        } else if (p.idx) {
            this.log("idx");
            this.chart.select(p.idx);
        }
        this.log("end select");
    }
    
    unselect(param) {
        var p = JSON.parse(param);
        this.log(p);
        this.log(""+(p.ids));
        this.log(p.resetOthers);
        if (p.ids && p.idx) {
            this.log("ids+idx");
            this.chart.unselect(p.ids, p.idx);
        } else if (p.ids) {
            this.log("ids");
            this.chart.unselect(p.ids);
        } else if (p.idx) {
            this.log("idx");
            this.chart.unselect(p.idx);
        }
        this.log("end select");
    }
    
    selected() {
        var s = this.chart.select();
        this.$server.selectedCallback(s);
    }
    
    selected(id) {
        var s = this.chart.selected(id);
        this.log(s);
        this.$server.selectedCallback(s);
    }
    
    
    transform(param) {
        var p = JSON.parse(param);
        this.log("transform",p);
        if (p.ids) {
           this.chart.transform(p.type,p.ids);
        } else {
            this.chart.transform(p.type);
        }
            
        this.log("end transform");
    }
    
    group(param) {
        var p = JSON.parse(param);
        this.log("groups",p);
        this.chart.groups(p.groups);
            
        this.log("end groups");
    }
}
;

customElements.define(C3Chart.is, C3Chart);