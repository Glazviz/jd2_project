import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Sensor from '@/components/Sensor'

Vue.use(Router)

export default new Router({
  mode: 'history',
  linkActiveClass: 'active',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/sensor/:id',
      name: 'Sensor',
      component: Sensor
    }
  ]
})
