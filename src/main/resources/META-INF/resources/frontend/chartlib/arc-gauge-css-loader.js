import c3styles from '@vaadin/flow-frontend/chartlib/lib/c3.css';

const $_documentContainerC3 = document.createElement('template');

$_documentContainerC3.innerHTML = `
  <dom-module id="arc-gauge-css" theme-for="arc-gauge">
    <template><style>${c3styles}</style></template>
  </dom-module>`;
document.head.appendChild($_documentContainerC3.content);


