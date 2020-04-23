import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/pages/LoginPage'
import RegisterPage from '@/pages/RegisterPage'
import HomePage from '@/pages/HomePage'
import DramaPage from '@/pages/DramaPage'
import SongListPage from '@/pages/SongListPage'
import BBSPage from '@/pages/BBSPage'
import SingerPage from '@/pages/SingerPage'
import ChatPage from '@/pages/ChatPage'
import MySongPage from '@/pages/MySongPage'
import DramaAlbumPage from '@/pages/DramaAlbumPage'
import SongListAlbumPage from '@/pages/SongListAlbumPage'
import SingerAlbumPage from '@/pages/SingerAlbumPage'
import ChatAlbumPage from '@/pages/ChatAlbumPage'
import BBSAlbumPage from '@/pages/BBSAlbumPage'
import SearchPage from '@/pages/SearchPage'
import PersonalInfoPage from '@/pages/PersonalInfoPage'
import PersonalChatPage from '@/pages/PersonalChatPage'
import UpLoadPage from '@/pages/UpLoadPage'
import PlayerPage from '@/pages/PlayerPage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '*',
      redirect: '/404'
    },
    {
      path: '/404',
      component: resolve => require(['../pages/404.vue'], resolve)
    },
    {
      path: '/',
      name: 'login-page',
      component: LoginPage
    },
    {
      path: '/register-page',
      name: 'register-page',
      component: RegisterPage
    },
    {
      path: '/home-page',
      name: 'home-page',
      component: HomePage
    },
    {
      path: '/chat-page',
      name: 'chat-page',
      component: ChatPage
    },
    {
      path: '/drama-page',
      name: 'drama-page',
      component: DramaPage
    },
    {
      path: '/song-list-page',
      name: 'song-list-page',
      component: SongListPage
    },
    {
      path: '/my-song-page',
      name: 'my-song-page',
      component: MySongPage
    },
    {
      path: '/drama-album-page/:id',
      name: 'drama-album-page',
      component: DramaAlbumPage
    },
    {
      path: '/bbs-album-page',
      name: 'bbs-album-page',
      component: BBSAlbumPage
    },
    {
      path: '/song-list-album-page/:id',
      name: 'song-list-album-page',
      component: SongListAlbumPage
    },
    {
      path: '/bbs-page',
      name: 'bbs-page',
      component:BBSPage
    },
    {
      path: '/singer-page',
      name: 'singer-page',
      component: SingerPage
    },
    {
      path: '/chat-album-page/:id',
      name: 'chat-album-page',
      component: ChatAlbumPage
    },
    {
      path: '/singer-album-page/:id',
      name: 'singer-album-page',
      component: SingerAlbumPage
    },
    {
      path: '/player-page/:id',
      name: 'player-page',
      component: PlayerPage
    },
    {
      path: '/search-page',
      name: 'search-page',
      component: SearchPage
    },
    {
      path: '/personal-info-page',
      name: 'personal-info-page',
      component: PersonalInfoPage
    },
    {
      path: '/personal-chat-page',
      name: 'personal-chat-page',
      component: PersonalChatPage
    },
    {
      path: '/upLoad-page',
      name: 'upLoad-page',
      component: UpLoadPage
    }
  ]
})
