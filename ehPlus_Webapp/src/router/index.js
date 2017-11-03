import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import MedicalprofessionalDashboard from '@/components/MedicalProfessional/Dashboard'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/MedicalP/Dashboard',
      name: 'MedicalprofessionalDashboard',
      component:MedicalprofessionalDashboard
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
      path: '/',
      name: 'Hello',
      component: HelloWorld
    }
  ]
})
