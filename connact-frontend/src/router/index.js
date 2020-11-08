import Vue from 'vue'
import VueRouter from 'vue-router'
import HelloWorld from '../components/HelloWorld'
import Chat from '../views/Chat'
import MyEvents from '../views/MyEvents.vue'
import People from '../views/People'
import Event from '../views/Event'
import Notification from '../views/Notification'
import Profile from '../views/Profile'
import Register from '../views/Register'
import Login from '../views/Login'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Hello World!',
    component: HelloWorld,
    meta: { requiresAuth: false, requiresAdmin: false }
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
  ,
  {
    path: '/chat',
    name: 'Chat',
    component: Chat
  },
  {
    path: '/MyEvents',
    name: 'My Events',
    component: MyEvents
  },
  {
    path: '/People',
    name: 'People',
    component: People
  },
  {
    path: '/Event',
    name: 'Event',
    component: Event
  },
  {
    path: '/Notification',
    name: 'Notification',
    component: Notification
  },
  {
    path: '/Profile',
    name: 'Profile',
    component: Profile
  },
  {
    path: '/Register',
    name: 'Register',
    component: Register
  },
  {
    path: '/Login',
    name: 'Login',
    component: Login
  }
]

const router = new VueRouter({
  routes
})

export default router
