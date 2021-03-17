// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Resource from 'vue-resource'
import VeeValidate from 'vee-validate'
import ValidationMessages from 'vee-validate/dist/locale/ru'
import env from './../env'
import Paginate from 'vuejs-paginate'
Vue.config.productionTip = false

Vue.component('paginate', Paginate)
Vue.use(Resource)
Vue.use(VeeValidate, {
  locale: 'ru',
  events: 'input|blur|change',
  dictionary: {
    ru: ValidationMessages
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  data () {
    return {
      env: env
    }
  },
  template: '<App/>'
})
