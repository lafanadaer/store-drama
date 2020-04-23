<!--<template>-->
<!--  <div id="album">-->
<!--    <div class="album-bg"></div>-->
<!--    <div class="album-slide">-->
<!--      <div class="album-slide">-->
<!--        <div class="album-img">-->
<!--          <img :src=attachImageUrl(avator) alt="">-->
<!--        </div>-->
<!--        <ul class="album-info">-->
<!--          <li>昵称： {{username}}</li>-->
<!--          <li>性别： {{userSex}}</li>-->
<!--          <li>生日： {{birth}}</li>-->
<!--          <li>地区： {{location}}</li>-->
<!--        </ul>-->
<!--      </div>-->
<!--    </div>-->
<!--    <div class="album-content">-->
<!--      <div class="album-title">个性签名: {{introduction}}</div>-->
<!--      <div class="songs-body">-->
<!--        <album-content :songList="collectList">-->
<!--          <template slot="title">我的收藏</template>-->
<!--        </album-content>-->
<!--        -->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import axios from 'axios'-->
<!--import {mixin} from '../mixins'-->
<!--import { mapGetters } from 'vuex'-->
<!--import AlbumContent from '../components/AlbumContent'-->

<!--export default {-->
<!--  name: 'my-song-page',-->
<!--  components: {-->
<!--    AlbumContent-->
<!--  },-->
<!--  mixins: [mixin],-->
<!--  data () {-->
<!--    return {-->
<!--      avator: '',-->
<!--      username: '',-->
<!--      userSex: '',-->
<!--      birth: '',-->
<!--      location: '',-->
<!--      introduction: '',-->
<!--      collection: [], // 存放收藏的歌曲ID-->
<!--      collectList: [] // 收藏的歌曲-->
<!--    }-->
<!--  },-->
<!--  computed: {-->
<!--    ...mapGetters([-->
<!--      'userId',-->
<!--      'id',-->
<!--      'listOfSongs' // 存放的音乐-->
<!--    ])-->
<!--  },-->
<!--  mounted () {-->
<!--    this.getMsg(this.userId)-->
<!--    this.getCollection(this.userId)-->
<!--  },-->
<!--  methods: {-->
<!--    getMsg (id) {-->
<!--      let _this = this-->
<!--      axios.get(_this.$store.state.configure.HOST + '/user/detail?id=' + id)-->
<!--        .then(response => {-->
<!--          _this.username = response.data[0].username-->
<!--          _this.getuserSex(response.data[0].sex)-->
<!--          _this.birth = response.data[0].birth-->
<!--          _this.introduction = response.data[0].introduction-->
<!--          _this.location = response.data[0].location-->
<!--          _this.avator = response.data[0].avator-->
<!--        })-->
<!--    },-->
<!--    getuserSex (sex) {-->
<!--      if (sex === 0) {-->
<!--        this.userSex = '女'-->
<!--      } else if (sex === 1) {-->
<!--        this.userSex = '男'-->
<!--      }-->
<!--    },-->

<!--    // 收藏的歌曲ID-->
<!--    getCollection (userId) {-->
<!--      let _this = this-->
<!--      axios.get(_this.$store.state.configure.HOST + '/comment/user/detail?userId=' + userId)-->
<!--        .then(function (response) {-->
<!--          _this.collection = response.data-->
<!--          // 通过歌曲ID获取歌曲信息-->
<!--          // for (let item of _this.collection) {-->
<!--          //   _this.getCollectSongs(item.songId)-->
<!--          // }-->
<!--          _this.$store.commit('setListOfSongs', _this.collectList)-->
<!--        })-->
<!--        .catch(function (error) {-->
<!--          console.log(error)-->
<!--        })-->
<!--    },-->
<!--    // 获取收藏的歌曲-->
<!--    getCollectSongs (id) {-->
<!--      let _this = this-->
<!--      axios.get(_this.$store.state.configure.HOST + '/song/detail?id=' + id)-->
<!--        .then(function (response) {-->
<!--          _this.collectList.push(response.data[0])-->
<!--        })-->
<!--        .catch(function (error) {-->
<!--          console.log(error)-->
<!--        })-->
<!--    }-->

<!--    // // 收藏的歌曲ID-->
<!--    // getCollection (userId) {-->
<!--    //   let _this = this-->
<!--    //   axios.get(_this.$store.state.configure.HOST + '/collection/detail?userId=' + userId)-->
<!--    //     .then(function (response) {-->
<!--    //       _this.collection = response.data-->
<!--    //       // 通过歌曲ID获取歌曲信息-->
<!--    //       for (let item of _this.collection) {-->
<!--    //         _this.getCollectSongs(item.songId)-->
<!--    //       }-->
<!--    //       _this.$store.commit('setListOfSongs', _this.collectList)-->
<!--    //     })-->
<!--    //     .catch(function (error) {-->
<!--    //       console.log(error)-->
<!--    //     })-->
<!--    // },-->
<!--    // // 获取收藏的歌曲-->
<!--    // getCollectSongs (id) {-->
<!--    //   let _this = this-->
<!--    //   axios.get(_this.$store.state.configure.HOST + '/song/detail?id=' + id)-->
<!--    //     .then(function (response) {-->
<!--    //       _this.collectList.push(response.data[0])-->
<!--    //     })-->
<!--    //     .catch(function (error) {-->
<!--    //       console.log(error)-->
<!--    //     })-->
<!--    // }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->
<!--/*歌单背景*/-->
<!--.album-bg {-->
<!--  width: 100%;-->
<!--  height: 200px;-->
<!--  background-color: #FF8C00;-->
<!--}-->

<!--/*歌单左侧*/-->
<!--.album-slide {-->
<!--  float: left;-->
<!--  width: 400px;-->
<!--}-->

<!--/*歌单图像*/-->
<!--.album-img {-->
<!--  height: 200px;-->
<!--  width: 200px;-->
<!--  display: inline-block;-->
<!--  position: relative;-->
<!--  top:-100px;-->
<!--  left: 50px;-->
<!--  border-radius: 50%;-->
<!--  overflow: hidden;-->
<!--  border: 5px solid white;-->
<!--}-->

<!--.album-img img {-->
<!--  width: 100%;-->
<!--}-->

<!--/*歌单信息*/-->
<!--.album-info {-->
<!--  color: black;-->
<!--  width: 500px;-->
<!--  font-size: 20px;-->
<!--  font-weight: 500;-->
<!--  margin-top: -100px;-->
<!--  margin-left: 100px;-->
<!--  padding: 30px 30px;-->
<!--}-->

<!--.album-info li {-->
<!--  width: 100%;-->
<!--  line-height: 40px;-->
<!--}-->

<!--/*歌单内容*/-->
<!--.album-content{-->
<!--  margin-left: 300px;-->
<!--  padding: 40px 100px;-->
<!--}-->

<!--/*歌单题目*/-->
<!--.album-title {-->
<!--  font-size: 30px;-->
<!--  font-weight: 600;-->
<!--}-->

<!--.songs-body {-->
<!--  margin-top: 50px;-->
<!--}-->
<!--</style>-->
<template>
  <div id="album">
    <div class="album-bg"></div>
    <div class="album-slide">
      <div class="album-slide">
        <div class="album-img">
          <img :src=attachImageUrl(avator) alt="">
        </div>
        <ul class="album-info">
          <li>昵称： {{username}}</li>
          <li>性别： {{userSex}}</li>
          <li>生日： {{birth}}</li>
          <li>地区： {{location}}</li>
          <li>个性签名： {{introduction}}</li>
        </ul>
      </div>
    </div>
    <div class="album-content">
      <div class="album-title">我的评论:</div>

      <div class="container">
<!--        <div class="handle-box">-->
<!--          <el-button type="primary" icon="delete" class="handle-del mr10" @click="delAll">批量删除</el-button>-->
<!--          <el-input v-model="select_word" placeholder="筛选关键词" class="handle-input mr10"></el-input>-->
<!--        </div>-->
<!--        <div class="songs-body">-->

<!--        </div>-->
        <el-table
          :data="tableData"
          stripe
          border
          style="width: 100%"
          ref="multipleTable"
          @selection-change="handleSelectionChange"
        >
<!--          <el-table-column type="selection" width="50"></el-table-column>-->
<!--          <el-table-column prop="createTime" label="创建时间"></el-table-column>-->
          <el-table-column prop="createTime" label="创建时间"></el-table-column>
          <el-table-column prop="content" label="评论内容"></el-table-column>
          <el-table-column label="操作" width="150">
            <template slot-scope="scope">
              <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
              <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
<!--      <div class="songs-body">-->
<!--        <album-content :songList="collectList">-->
<!--          <template slot="title">我的收藏</template>-->
<!--        </album-content>-->

<!--      </div>-->
    </div>

    <!-- 编辑弹出框 -->
    <el-dialog title="编辑" :visible.sync="editVisible" width="400px">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="评论内容">
          <el-input v-model="form.content"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveEdit">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 删除提示框 -->
    <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
      <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="delVisible = false">取 消</el-button>
        <el-button type="primary" @click="deleteRow">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import axios from 'axios'
  import {mixin} from '../mixins'
  import { mapGetters } from 'vuex'
  import AlbumContent from '../components/AlbumContent'

  export default {
    name: 'my-song-page',
    components: {
      AlbumContent
    },
    mixins: [mixin],
    data () {
      return {
        id: '',
        avator: '',
        username: '',
        userSex: '',
        birth: '',
        location: '',
        introduction: '',
        tableData: [],
        tempDate: [],
        multipleSelection: [],
        editVisible: false,
        delVisible: false,
        collection: [], // 存放收藏的歌曲ID
        collectList: [],// 收藏的歌曲
        form: {
          id: '',
          userId: '',
          dramaId: '',
          chatId: '',
          content: '',
          type: '',
          up: ''
        },
        idx: -1
      }
    },
    computed: {
      ...mapGetters([
        'userId',
        'id',
        'listOfSongs' // 存放的音乐
      ])
    },
    mounted () {
      this.getMsg(this.userId)
      this.getCollection(this.userId)
    },
    created () {
      this.getData()
    },
    methods: {
      getData () {
        let _this = this
        _this.tableData = []
        _this.tempDate = []
        axios.get(`${_this.$store.state.configure.HOST}/comment/user/detail?userId=${this.userId}`)
          .then((res) => {
            _this.currentPage = 1
            _this.tableData = res.data
            _this.tempDate = res.data
          })
          .catch(function (error) {
            console.log(error.response)
          })

      },
      // getData () {
      //   var _this = this
      //   // var a = this.userId
      //   _this.tableData = []
      //   _this.tempDate = []
      //   let url = ''
      //   // if (_this.$route.query.type === 1) {
      //   //   url = `${_this.$store.state.HOST}/comment/drama/detail?dramaId=`
      //   // } else if (_this.$route.query.type === 0) {
      //   //   url = `${_this.$store.state.HOST}/comment/chat/detail?chatId=`
      //   // }
      //   _this.$axios.get(`${_this.$store.state.HOST}/comment/user/detail?userId=${id}`).then(res => {
      //   // _this.$axios.get(url + _this.$route.query.id).then(res => {
      //     for (let item of res.data) {
      //       _this.getUsers(item.userId, item)
      //     }
      //   })
      // },
      getUsers (id, item) {
        let _this = this
        _this.$axios.get(`${_this.$store.state.HOST}/user/detail?id=${id}`)
          .then(function (res) {
            let o = item
            o.name = res.data[0].username
            _this.tableData.push(o)
            _this.tempDate.push(o)
          })
          .catch(function (error) {
            console.log(error)
          })
      },
      getMsg (id) {
        let _this = this
        axios.get(_this.$store.state.configure.HOST + '/user/detail?id=' + id)
          .then(response => {
            _this.username = response.data[0].username
            _this.getuserSex(response.data[0].sex)
            _this.birth = response.data[0].birth
            _this.introduction = response.data[0].introduction
            _this.location = response.data[0].location
            _this.avator = response.data[0].avator
          })
      },
      getuserSex (sex) {
        if (sex === 0) {
          this.userSex = '女'
        } else if (sex === 1) {
          this.userSex = '男'
        }
      },

      handleEdit (row) {
        this.idx = row.id
        this.form = {
          id: row.id,
          userId: row.userId,
          dramaId: row.dramaId,
          chatId: row.chatId,
          content: row.content,
          type: row.type,
          up: row.up
        }
        this.editVisible = true
      },
      // 保存编辑
      saveEdit () {
        let _this = this
        var params = new URLSearchParams()
        params.append('id', this.form.id)
        params.append('userId', this.form.userId)
        if (this.form.dramaId === null) {
          params.append('dramaId', '')
        } else {
          params.append('dramaId', this.form.dramaId)
        }
        if (this.form.chatId === null) {
          params.append('chatId', '')
        } else {
          params.append('chatId', this.form.chatId)
        }
        params.append('content', this.form.content)
        params.append('type', this.form.type)
        params.append('up', this.form.up)
        // this.$axios.post(`${_this.$store.state.configure.HOST}/comment/update`, params)
        axios.post(`${_this.$store.state.configure.HOST}/comment/update`, params)
          .then(res => {
            if (res.data.code === 1) {
              this.getData()
              this.$notify({
                title: '编辑成功',
                type: 'success'
              })
            } else {
              this.$notify({
                title: '编辑失败',
                type: 'error'
              })
            }
          })
          .catch(failResponse => {})
        this.editVisible = false
      },
      // 获取批量要删除的列表
      handleSelectionChange (val) {
        this.multipleSelection = val
      },
      // 获取要删除列表的id
      handleDelete (id) {
        this.idx = id
        this.delVisible = true
      },
      // 确定删除
      deleteRow () {
        var _this = this
        axios.get(`${_this.$store.state.configure.HOST}/comment/delete?id=${_this.idx}`)
          .then(res => {
            if (res.data) {
              _this.getData()
              _this.$notify({
                title: '删除成功',
                type: 'success'
              })
            } else {
              _this.$notify({
                title: '删除失败',
                type: 'error'
              })
            }
          })
          .catch(failResponse => {})
        _this.delVisible = false
      },

      // // 收藏的歌曲ID
      // getCollection (userId) {
      //   let _this = this
      //   axios.get(_this.$store.state.configure.HOST + '/comment/user/detail?userId=' + userId)
      //     .then(function (response) {
      //       _this.collection = response.data
      //       // 通过歌曲ID获取歌曲信息
      //       // for (let item of _this.collection) {
      //       //   _this.getCollectSongs(item.songId)
      //       // }
      //       _this.$store.commit('setListOfSongs', _this.collectList)
      //     })
      //     .catch(function (error) {
      //       console.log(error)
      //     })
      // },
      // // 获取收藏的歌曲
      // getCollectSongs (id) {
      //   let _this = this
      //   axios.get(_this.$store.state.configure.HOST + '/song/detail?id=' + id)
      //     .then(function (response) {
      //       _this.collectList.push(response.data[0])
      //     })
      //     .catch(function (error) {
      //       console.log(error)
      //     })
      // }

      // // 收藏的歌曲ID
      // getCollection (userId) {
      //   let _this = this
      //   axios.get(_this.$store.state.configure.HOST + '/collection/detail?userId=' + userId)
      //     .then(function (response) {
      //       _this.collection = response.data
      //       // 通过歌曲ID获取歌曲信息
      //       for (let item of _this.collection) {
      //         _this.getCollectSongs(item.songId)
      //       }
      //       _this.$store.commit('setListOfSongs', _this.collectList)
      //     })
      //     .catch(function (error) {
      //       console.log(error)
      //     })
      // },
      // // 获取收藏的歌曲
      // getCollectSongs (id) {
      //   let _this = this
      //   axios.get(_this.$store.state.configure.HOST + '/song/detail?id=' + id)
      //     .then(function (response) {
      //       _this.collectList.push(response.data[0])
      //     })
      //     .catch(function (error) {
      //       console.log(error)
      //     })
      // }
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

  /*歌单左侧*/
  .album-slide {
    float: left;
    width: 400px;
  }

  /*歌单图像*/
  .album-img {
    height: 200px;
    width: 200px;
    display: inline-block;
    position: relative;
    top:-100px;
    left: 50px;
    border-radius: 50%;
    overflow: hidden;
    border: 5px solid white;
  }

  .album-img img {
    width: 100%;
  }

  /*歌单信息*/
  .album-info {
    color: black;
    width: 500px;
    font-size: 20px;
    font-weight: 500;
    margin-top: -100px;
    margin-left: 100px;
    padding: 30px 30px;
  }

  .album-info li {
    width: 100%;
    line-height: 40px;
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

  .songs-body {
    margin-top: 50px;
  }
</style>

