<template>
  <div class="the-header">
    <!--图标-->
    <div class="head-logo" @click="goHomePage">
<!--      <div class="logo-hd">-->
<!--        <svg class="icon" aria-hidden="true">-->
<!--          <use xlink:href="#icon-erji"></use>-->
<!--        </svg>-->
<!--      </div>-->
      <span>{{musicName}}</span>
    </div>
    <ul class="navbar" ref="change">
      <li :class="{active: item.name === activeName}" v-for="item in navMsg" :key="item.path" @click="goPage(item.path, item.name)">
        {{item.name}}
      </li>
      <li>
        <div class="head-search">
          <input type="text" placeholder="搜索美剧" @keyup.enter="goSearch()" v-model="keywords">
          <div class="search-btn"  @click="goSearch()" >
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-sousuo"></use>
            </svg>
          </div>
        </div>
      </li>
      <li :class="{active: item.name === activeName}" v-for="item in loginMsg" :key="item.type" @click="goPage(item.path, item.name)" v-if="!loginIn">{{item.name}}</li>
    </ul>
    <!--用户-->
    <ul class="menu">
      <li v-for="(item, index) in menuList" :key="index" @click="goMenuList(item.path)">{{item.name}}</li>
    </ul>
    <div id="user" @click="show()" v-if="loginIn">
      <img :src=attachImageUrl(avator) alt="">
    </div>
  </div>
</template>

<script>
import { mixin } from '../mixins'
import { mapGetters } from 'vuex'

export default {
  name: 'the-header',
  mixins: [mixin],
  data () {
    return {
      musicName: '24-drama',
      navMsg: [{
        name: '首页',
        path: '/home-page'
      }, {
        name: '美剧分类',
        path: '/drama-page'
      }, {
        name: '讨论区',
        path: '/chat-page'
        // path: '/bbs-album-page'
      }, {
        name: '我的美剧',
        path: '/my-song-page'
      }],
      loginMsg: [{
        name: '登录',
        path: '/'
      }, {
        name: '注册',
        path: '/register-page'
      }],
      menuList: [{
        name: '个人信息',
        path: '/personal-info-page'
      }, {
        name: '帖子信息',
        path: '/personal-chat-page'
      }, {
        name: '修改头像',
        path: '/upLoad-page'
      }, {
        name: '退出',
        path: 0
      }],
      keywords: ''
    }
  },
  computed: {
    ...mapGetters([
      'userId',
      'activeName',
      'avator',
      'username',
      'loginIn'
    ])
  },
  methods: {
    show () {
      document.querySelector('#user').addEventListener('click', function (e) {
        document.querySelector('.menu').classList.add('show')
        e.stopPropagation()// 关键在于阻止冒泡
      }, false)
      // 点击“菜单”内部时，阻止事件冒泡。(这样点击内部时，菜单不会关闭)
      document.querySelector('.menu').addEventListener('click', function (e) {
        e.stopPropagation()
      }, false)
      document.addEventListener('click', function () {
        document.querySelector('.menu').classList.remove('show')
      }, false)
    },
    goHomePage () {
      this.$router.push({path: '/home-page'})
    },
    goPage (path, value) {
      document.querySelector('.menu').classList.remove('show')
      this.changeIndex(value)
      if (!this.loginIn && path === '/my-song-page') {
        this.$notify({
          title: '请先登录',
          type: 'warning'
        })
      } else {
        this.$router.push({path: path})
      }
    },

    changeIndex (value) {
      this.$store.commit('setActiveName', value)
    },
    goMenuList (path) {
      document.querySelector('.menu').classList.remove('show')
      if (path) {
        this.$router.push({path: path})
      } else {
        this.$store.commit('setLoginIn', false)
        this.$router.go(0)
      }
    },
    goSearch () {
      this.$store.commit('setSearchword', this.keywords)
      this.$router.push({path: '/search-page', query: {keywords: this.keywords}})
    }
  }
}
</script>

<style scoped>
.the-header {
  background-color: #fefefe;
  width: 100%;
  height: 80px;
  position: relative;
  box-shadow: 0 0 10px black;
}

.head-logo {
  width: 150px;
  line-height: 80px;
  font-size: 28px;
  font-weight: bold;
  margin-left: 90px;
  display: inline-block;
  cursor: pointer;
}

.logo-hd{
  position: absolute;
  top:15px;
  left: 30px;
}

.logo-hd .icon {
  width: 50px;
  height: 50px;
  fill: currentColor;
  color: black;
}

/*nav*/
.navbar {
  box-sizing: border-box;
  height: 80px;
}

.navbar li {
  margin: 0 10px;
  padding: 0 10px;
  height: 48px;
  font-size: 20px;
  text-align: center;
  color: #67757f;
  border-bottom: none;
  cursor: pointer;
}

/*搜索*/
.head-search {
  display: flex;
  border-radius: 5px;
  overflow: hidden;
}

.head-search input {
  height: 30px;
  width: 270px;
  font-size: 16px;
  border: 0;
  text-indent: 10px;
  background-color: #ebeef0;
}

input:focus {
  outline: none;
}

.search-btn{
  background-color: #FF8C00;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 60px;
  height: 30px;
  cursor: pointer;
}

.icon{
  width: 0.8em;
  height: 0.8em;
  fill: currentColor;
  color: white;
  font-size: 1.5em;
}

/*用户*/
#user {
  border-radius: 50%;
  width: 50px;
  height: 50px;
  overflow: hidden;
  margin: 15px;
  position: absolute;
  right: 150px;
  display: inline-block;
}

#user:hover {
  cursor: pointer;
}

#user img {
  width: 100%;
}

.menu {
  display: none;
  background-color: #fff;
  width: 150px;
  position: absolute;
  right: 140px;
  top: 90px;
  box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.4);
  cursor: pointer;
  z-index: 100;
  text-align: center;
}

.menu li:hover{
  background-color: #95d2f6;
  color: white;
}

.menu :nth-child(1):before {
  content: " ";
  display: block;/*独占一行*/
  position: absolute;
  right: 45px;
  top: -5px; /*果断的露出上半部分*/
  width: 10px;
  height: 10px;
  background-color: #ffffff;
  transform: rotate(45deg); /*一个正方形倾斜四十五度就是三角了但是要把下半部分藏起来*/
}

.menu :nth-child(1):hover:before{
  background-color: #95d2f6;
}

.menu li {
  display: inline-block;
  width: 100%;
  height: 40px;
  line-height: 40px;
}

.show {
  display: block;
}

.active{
  color: #FF8C00 !important;
  border-bottom: 6px solid #FF8C00 !important;
}
</style>
