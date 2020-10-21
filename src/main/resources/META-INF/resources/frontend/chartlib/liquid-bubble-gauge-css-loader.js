import styles from '@vaadin/flow-frontend/chartlib/liquid-bubble-gauge.css';

const $_documentContainerLBG = document.createElement('template');

$_documentContainerLBG.innerHTML = `
  <dom-module id="liquid-bubble-gauge-css" theme-for="liquid-bubble-gauge">
    <template><style>${styles}</style></template>
  </dom-module>`;
document.head.appendChild($_documentContainerLBG.content);


