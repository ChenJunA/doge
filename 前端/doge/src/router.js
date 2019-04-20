import Vue from 'vue'
import Router from 'vue-router'
import login from './components/login.vue'
import home from './views/home.vue'
import register from './components/register.vue'
import userPage from './views/userPage'
import updateUser from './views/updateUser'
import dogPage from './views/dogPage'
import forumPage from './views/forumPage'
import forumInfoPage from './views/forumInfoPage'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'login',
      component: login
    },
    {
      path: '/register',
      name: 'register',
      component: register
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/home',
      name: 'home',
      component: home,
      children: [
        {
          path: 'pageHeader',
          name: 'pageHeader',
          component: () => import(/* webpackChunkName: "pageHeader" */ './components/pageHeader.vue')
        },
        {
          path: 'pageContent',
          name: 'pageContent',
          component: () => import(/* webpackChunkName: "pageContent" */ './components/pageContent.vue')
        }
      ]
    },
    {
      path: '/userPage',
      name: 'userPage',
      component: userPage
    },
    {
      path: '/updateUser',
      name: 'updateUser',
      component: updateUser
    },
    {
      path: '/dogPage',
      name: 'dogPage',
      component: dogPage
    },
    {
      path: '/forum',
      name: 'forum',
      component: forumPage
    },
    {
      path: '/forumInfoPage',
      name: 'forumInfoPage',
      component: forumInfoPage
    }
  ]
})
