import { createRouter, createWebHistory } from 'vue-router'
import cors from "cors"
const routes = [
  {
    path: '/login',
    component: () => import('../views/login.vue')
  },
  {
    path: '/',
    component: () => import('../views/main.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  cors
})

export default router
