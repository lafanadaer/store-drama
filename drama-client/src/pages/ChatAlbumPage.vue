<!--<template>-->
<!--  <div class="chat-album-page">-->
<!--    <div class="container">-->
<!--      <div class="handle-box">-->
<!--        <el-button type="primary" @click="centerDialogVisible = true">发帖</el-button>-->
<!--      </div>-->
<!--      <el-table :data="data" border stripe style="width: 100%" height="500px" ref="multipleTable" @selection-change="handleSelectionChange">-->
<!--        <el-table-column prop="userId" label="作者" width="200"></el-table-column>-->
<!--        <el-table-column prop="title" label="标题" width="600"></el-table-column>-->
<!--        <el-table-column label="详情" width="80">-->
<!--          <template  slot-scope="scope">-->
<!--            <el-button size="mini" @click="getComment(data[scope.$index].id)">详情</el-button>-->
<!--          </template>-->
<!--        </el-table-column>-->
<!--      </el-table>-->
<!--      <div class="pagination">-->
<!--        <el-pagination-->
<!--          @current-change="handleCurrentChange"-->
<!--          background-->
<!--          layout="total, prev, pager, next"-->
<!--          :current-page="currentPage"-->
<!--          :page-size="pageSize"-->
<!--          :total="tableData.length">-->
<!--        </el-pagination>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--  import axios from 'axios'-->
<!--  import { mixin } from '../mixins'-->
<!--  import { mapGetters } from 'vuex'-->
<!--  import AlbumContent from '../components/AlbumContent'-->
<!--  import Comment from '../components/Comment'-->

<!--  export default {-->
<!--    name: 'chat-album-page',-->
<!--    components: {-->
<!--      AlbumContent,-->
<!--      Comment,-->
<!--      data () {-->
<!--        return this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)-->
<!--      },-->
<!--    },-->
<!--    mixins: [mixin],-->
<!--    data () {-->
<!--      return {-->
<!--        tableData: [],-->
<!--        tempDate: [],-->
<!--        centerDialogVisible: false,-->
<!--        form: {-->
<!--          id: '',-->
<!--          userId:'',-->
<!--          title: '',-->
<!--          content: '',-->
<!--          // createTime: ''-->
<!--        },-->
<!--        pageSize: 5, // 页数-->
<!--        currentPage: 1, // 当前页-->
<!--        idx: -1-->
<!--      }-->
<!--    },-->

<!--    created () {-->
<!--      this.getData(id)-->
<!--    },-->
<!--    methods: {-->
<!--      getData (id) {-->

<!--        let _this = this-->
<!--        _this.tableData = []-->
<!--        _this.tempDate = []-->
<!--        // let url = ''-->
<!--        // // if (_this.$route.query.type === 0) {-->
<!--        // //   url = `${_this.$store.state.HOST}/comment/song/detail?songId=`-->
<!--        // // } else if (_this.$route.query.type === 1) {-->
<!--        // //   url = `${_this.$store.state.HOST}/comment/songList/detail?songListId=`-->
<!--        // // }-->
<!--        // // url = `${_this.$store.state.HOST}/comment/chat/detail?chatId=`-->
<!--        // // _this.$axios.get(url + _this.$route.query.id).then(res => {-->
<!--        // url = `${_this.$store.state.configure.HOST}/comment/chat/detail?chatId=`-->

<!--        // axios.get(url + _this.$route.query.id).then(res => {-->
<!--        _this.$axios.get(`${_this.$store.state.configure.HOST}/comment/chat/detail?id=${id}`)-->
<!--          .then(function (res) {-->
<!--          _this.tableData = res.data-->
<!--          _this.tempDate = res.data-->
<!--          _this.currentPage = 1-->
<!--          // for (let item of res.data) {-->
<!--          //   _this.getUsers(item.userId, item)-->
<!--          // }-->
<!--        })-->
<!--      },-->
<!--      // getUsers (id, item) {-->
<!--      //   let _this = this-->
<!--      //   _this.$axios.get(`${_this.$store.state.HOST}/user/detail?id=${id}`)-->
<!--      //     .then(function (res) {-->
<!--      //       let o = item-->
<!--      //       o.name = res.data[0].username-->
<!--      //       _this.tableData.push(o)-->
<!--      //       _this.tempDate.push(o)-->
<!--      //     })-->
<!--      //     .catch(function (error) {-->
<!--      //       console.log(error)-->
<!--      //     })-->
<!--      // },-->


<!--    }-->
<!--  }-->
<!--</script>-->

<!--<style scoped>-->
<!--  .handle-box {-->
<!--    margin-bottom: 20px;-->
<!--  }-->

<!--  .handle-input {-->
<!--    width: 300px;-->
<!--    display: inline-block;-->
<!--  }-->
<!--  .pagination {-->
<!--    display: flex;-->
<!--    justify-content: center;-->
<!--  }-->
<!--</style>-->

<template>
  <div class="album">
    <div class="album-bg"></div>
    <div class="album-body">
      <div class="album-slide">
        <div class="album-img">
          <img :src=attachImageUrl(singers.pic) alt="">
        </div>
        <div class="album-info">
<!--          <h2>主题：</h2>-->
          <span>
            {{singers.title}}
          </span>
          <h2>----------------------</h2>
          <h2>讨论：</h2>
          <span>
            {{singers.content}}
          </span>
        </div>
      </div>
      <div class="album-content">
<!--        <div class="album-title">-->
<!--          <p>{{singers.title}}</p>-->
<!--        </div>-->

        <!--歌曲-->
        <div class="songs-body">
          <album-content :songList="listOfSongs">
            <!--            <template slot="title">简介</template>-->
            <span>
            {{singers.content}}
          </span>
          </album-content>
          <comment :id="chatId" :type="0"></comment>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import { mixin } from '../mixins'
  import { mapGetters } from 'vuex'
  import AlbumContent from '../components/AlbumContent'
  import Comment from '../components/Comment'

  export default {
    name: 'chat-album-page',
    components: {
      AlbumContent,
      Comment
    },
    data () {
      return {
        // songLists: [],
        // singers: {},
        chatId: '', // 歌单ID
        count: 0, // 点赞数
        //dramaId: '', // 歌单ID
        value3: 0,
        value5: 0
      }
    },
    computed: {
      ...mapGetters([
        'loginIn', // 登录标识
        'tempList', // 单个歌单信息
        'listOfSongs', // 存放的音乐
        'userId', // 用户ID
        'avator' // 用户头像
      ])
    },
    created () {
      this.chatId = this.tempList.id // 给歌单ID赋值
      this.singers = this.tempList
      this.getSongId() // 获取歌单里面的歌曲ID
      // this.getRank(this.chatId) // 获取评分
    },
    mixins: [mixin],
    methods: {
      // 收集歌单里面的歌曲
      getSongId () {
        let _this = this
        axios.get(`${_this.$store.state.configure.HOST}/listSong/detail?songListId=${this.songListId}`)
          .then(function (response) {
            // 获取歌单里的歌曲信息
            for (let item of response.data) {
              _this.getSongList(item.songId)
            }
            _this.$store.commit('setListOfSongs', _this.songLists)
          })
          .catch(function (error) {
            console.log(error)
          })
      },
      // 获取单里的歌曲
      getSongList (id) {
        let _this = this
        axios.get(`${_this.$store.state.configure.HOST}/chat/detail?id=${id}`)
          .then(res => {
            _this.songLists.push(res.data[0])
          })
          .catch(function (error) {
            console.log(error)
          })
      },
      // 获取评分
      getRank (id) {
        let _this = this
        axios.get(`${_this.$store.state.configure.HOST}/rank?dramaId=${id}`)
          .then(res => {
            _this.value5 = res.data / 2
          })
          .catch(function (error) {
            console.log(error)
          })
      },

    }
  }
</script>

<style scoped>
  /*歌单背景*/
  .album-bg {
    width: 100%;
    height: 200px;
    background-color: #FF8C00;
  }

  .album-body {
    width: 100%;
    display: inline-block;
  }

  /*歌单左侧*/
  .album-slide {
    float: left;
    width: 400px;
  }

  /*歌单图像*/
  .album-img {
    height: 300px;
    width: 300px;
    display: inline-block;
    position: relative;
    top:-100px;
    left: 50px;
    border-radius: 10%;
    overflow: hidden;
    border: 5px solid white;
  }

  .album-img img {
    width: 100%;
  }

  /*歌单信息*/
  .album-info {
    font-size: 20px;
    font-weight: 500;
    margin-top: -80px;
    padding: 30px 50px;
  }

  .album-info > span {
    color: rgba(0, 0, 0, 0.5);
  }

  /*歌单内容*/
  .album-content{
    margin-left: 300px;
    padding: 40px 100px;
  }

  /*歌单题目*/
  .album-title {
    font-size: 30px;
    font-weight: 600;
  }

  /*歌单打分*/
  .album-score {
    display: flex;
    align-items: center;
    margin: 50px;
  }

  .album-score > div {
    margin-left: 100px;
  }

  .album-score > span{
    font-size: 60px;
  }

  .album-score h3 {
    margin: 10px 0;
  }

  /*歌曲列表*/
  .songs-body {
    background-color: white;
    border-radius: 12px;
    padding: 0 40px 50px 40px;
    min-width: 700px;
  }

</style>
