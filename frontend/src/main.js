import Vue from "vue";
import App from "./App.vue";
import router from "./router/router";
import store from "./store/store";
import mdiVue from "mdi-vue/v2";
import * as mdijs from "@mdi/js";

// Import Bootstrap and BootstrapVue CSS files (order is important)
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin

Vue.config.productionTip = false;

Vue.use(mdiVue, {
  icons: mdijs,
});
Vue.use(IconsPlugin);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
