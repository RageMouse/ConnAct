import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import store from './store/store'

Vue.config.productionTip = false

Vue.use(VueAxios, axios);

new Vue({
  render: h => h(App),
  vuetify,
  router,
  store,
  axios,
  VueAxios
}).$mount('#app')

export default {
  name: 'App',
  components: {
  }
}