<template>
  <div id="search-page">
    <div class="my-bg"></div>
    <div class="my-body">
      <div class="content">
        <nav class="searchList-nav" ref="change">
          <span :class="{isActive: toggle === 'dramas'}" @click="handleChangeView('dramas', 0)">美剧</span>
<!--          <span :class="{isActive: toggle === 'drams'}" @click="handleChangeView('dramas', 1)">类型</span>-->
        </nav>
        <component :is="currentView"></component>
      </div>
    </div>
  </div>
</template>

<script>
import searchSongs from '../components/search/SearchSongs'
import searchSongLists from '../components/search/SearchSongLists'
import { mapGetters } from 'vuex'
import { mixin } from '../mixins'

export default {
  name: 'search-page',
  components: {
    searchSongs,
    searchSongLists
  },
  mixins: [mixin],
  data () {
    return {
      toggle: 'Songs',
      currentView: 'searchSongs'
    }
  },
  computed: {
    ...mapGetters([
      'searchword'
    ])
  },
  watch: {
    searchword: function () {
      // this.getSong()
      this.getDrama()
    }
  },
  methods: {
    // 切换组件
    handleChangeView: function (component) {
      this.currentView = 'search' + component
      this.toggle = component
    }
  }
}
</script>

<style scoped>
.my-bg {
  width: 100%;
  height: 100px;
  background-color: #93d2f8;
}

.my-body{
  position: relative;
  padding: 0 100px;
}

.content {
  background-color: white;
  margin: 50px 0;
  border-radius: 12px;
  padding: 20px 40px;
  min-width: 900px;
}

.searchList-nav {
  display: flex;
  justify-content: space-around;
  margin: 20px 0;
  font-size: 1.5rem;
  color: black;
}

.searchList-nav span {
  line-height: 50px;
  cursor: pointer;
}

.isActive {
  font-weight: 600;
  border-bottom:5px solid black;
}
</style>
