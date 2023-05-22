import Vue from "vue";
import App from "./App.vue";
import router from "./router/router";
import store from "./store/store";
// import vuetify from "./plugins/vuetify";

// Import Bootstrap and BootstrapVue CSS files (order is important)
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import vuetify from './plugins/vuetify'

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App)
}).$mount("#app");
