import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Login from '@/components/Login'
import MedicalprofessionalDashboard from '@/components/MedicalProfessional/Dashboard'
import MedicalAdminDashboard from '@/components/MedicalAdmin/Dashboard'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/MedicalP/Dashboard/:userId',
      name: 'MedicalprofessionalDashboard',
      component:MedicalprofessionalDashboard
    },
    {
      path: '/MedicalAdmin/Dashboard/:userId',
      name: 'MedicalAdminDashboard',
      component:MedicalprofessionalDashboard
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
      path: '/',
      name: 'Home',
      component: Home
    }
  ]
})
