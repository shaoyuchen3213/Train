import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import cors from "cors"
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../components/login.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  cors
})

export default router
