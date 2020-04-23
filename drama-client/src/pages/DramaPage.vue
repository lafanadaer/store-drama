<template>
  <div class="drama-page">
<!--    <div class="handle-box">-->
<!--      &lt;!&ndash;      <el-button type="primary" icon="delete" class="handle-del mr10" @click="delAll">批量删除</el-button>&ndash;&gt;-->
<!--      <el-input v-model="select_word" placeholder="筛选关键词" class="handle-input mr10"></el-input>-->
<!--      &lt;!&ndash;      <el-button type="primary" @click="centerDialogVisible = true">搜索美剧</el-button>&ndash;&gt;-->
<!--    </div>-->
    <div class="drama-header">
      <ul>
        <el-input v-model="select_word" placeholder="搜索美剧" class="handle-input mr10"></el-input>
        <li
          v-for="(item, index) in dramaCate"
          :key="index"
          :class="{active: item.name === activeName}"
          @click="handleChangeView(item.name)">
          {{item.name}}
        </li>
      </ul>
    </div>

    <content-list :contentList="data"></content-list>
    <div class="pagination">

<!--      <div class="pagination">-->
        <el-pagination
          @current-change="handleCurrentChange"
          background
          layout="total, prev, pager, next"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="albumDatas.length">
        </el-pagination>
<!--      </div>-->
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import { mapGetters } from 'vuex'
  import ContentList from '../components/ContentList'
  import {mixin} from '../mixins'

  export default {
    name: 'drama-page',
    mixins: [mixin],
    components: {
      ContentList
    },
    data () {
      return {
        dramaCate: [
          {
            name: '全部美剧',
            //type: 'One'
          },
          {
            name: '剧情',
            //type: 'Two'
          },
          {
            name: '悬疑',
            //type: 'Three'
          },
          {
            name: '科幻',
            //type: 'Four'
          },
          {
            name: '喜剧',
            //type: 'Five'
          },
          {
            name: '动作',
            //type: 'Six'
          },
          {
            name: '爱情',
            //type: 'Seven'
          },
        ],
        // tableData: [],
        tempDate: [],//记录美剧信息，用于搜索时能临时记录一份美剧信息
        is_search: false,
        select_word: '',
        activeName: '全部美剧',
        pageSize: 15, // 页数
        currentPage: 1, // 当前页
        albumDatas: [] // 美剧
      }
    },
    computed: {
      ...mapGetters([
        'dramas'
      ]),
      // 计算当前表格中的数据
      data () {
        return this.albumDatas.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
      }
    },

    watch: {
      select_word: function () {
        if (this.select_word === '') {
          this.albumDatas = this.tempDate
        } else {
          this.albumDatas = []
          for (let item of this.tempDate) {
            if (item.name.includes(this.select_word)) {
              this.albumDatas.push(item)
            }
          }
        }
      }
    },
    mounted () {
      this.handleChangeView('全部美剧')
    },
    methods: {
      // 获取当前页
      handleCurrentChange (val) {
        this.currentPage = val
      },
      // 获取美剧
      handleChangeView: function (name) {
        this.activeName = name
        this.albumDatas = []
        if (name === '全部美剧') {
          this.getDrama()
          //this.getDrama(this.cur_page)
        } else {
          this.getSongListOfStyle(name)
        }
      },
      // getCategoryName () {
      //   let _this = this
      //   let value = document.getElementById('categoryName').value
      //   _this.$axios.get(`${_this.$store.state.HOST}/drama/name/detail?name=${value}`).then(function (res) {
      //     if (!res.data.length) {
      //       _this.$notify({
      //         title: '系统暂无该分类',
      //         type: 'warning'
      //       })
      //     } else {
      //       _this.addSong(res.data[0].id)
      //     }
      //   }).catch(function (error) {
      //     console.log(error)
      //   })
      // },


      // 获取全部美剧
      getDrama () {
        let _this = this
        _this.albumDatas = []
        _this.tempDate = []
        axios.get(`${_this.$store.state.configure.HOST}/drama`)
          .then((res) => {
            _this.currentPage = 1
            _this.albumDatas = res.data
            _this.tempDate = res.data
          })
      },
      // 通过分类获取美剧
      getSongListOfStyle (name) {
        let _this = this
        axios.get(`${_this.$store.state.configure.HOST}/drama/categoryName/detail?name=${name}`)
          .then((res) => {
            _this.currentPage = 1
            _this.albumDatas = res.data
          })
          .catch(function (error) {
            console.log(error.response)
          })
          // .then(res => {
          //   _this.currentPage = 1
          //   _this.albumDatas = res.data
          // })
      }
    }
  }
</script>

<style scoped>
  .handle-box {
    margin-bottom: 20px;
    margin-left: 700px;
  }
  .handle-input {
    width: 210px;
    /*margin-left: 110px;*/
    display: inline-block;
  }
  .el-input__inner{
    background-color: aqua
  }
  .drama-page {
    margin: 30px 150px;
    padding-bottom: 50px;
    min-width: 800px;
    background-color: #ffffff;
  }

  .drama-header {
    width: 100%;
    padding: 0 40px;
  }

  li {
    display: inline-block;
    line-height: 40px;
    margin: 40px 20px 15px 20px;
    font-size: 20px;
    font-weight: 400;
    color: #67757f;
    border-bottom: none;
    cursor: pointer;
  }

  .active {
    color: black;
    font-weight: 600;
    border-bottom: 4px solid black;
  }

  .pagination {
    display: flex;
    justify-content: center;
  }
</style>

<!--<template>-->
<!--  <div class="drama-page">-->
<!--    <div class="drama-header">-->
<!--      <ul>-->
<!--        <li-->
<!--          v-for="(item, index) in dramaCate"-->
<!--          :key="index"-->
<!--          :class="{active: item.name === activeName}"-->
<!--          @click="handleChangeView(item.name)">-->
<!--          {{item.name}}-->
<!--        </li>-->
<!--      </ul>-->
<!--    </div>-->
<!--    <div class="pagination">-->
<!--      <content-list :contentList="data"></content-list>-->
<!--      <div class="pagination">-->
<!--        <el-pagination-->
<!--          @current-change="handleCurrentChange"-->
<!--          background-->
<!--          layout="total, prev, pager, next"-->
<!--          :current-page="currentPage"-->
<!--          :page-size="pageSize"-->
<!--          :total="albumDatas.length">-->
<!--        </el-pagination>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--  import axios from 'axios'-->
<!--  import { mapGetters } from 'vuex'-->
<!--  import ContentList from '../components/ContentList'-->
<!--  import {mixin} from '../mixins'-->

<!--  export default {-->
<!--    name: 'drama-page',-->
<!--    mixins: [mixin],-->
<!--    components: {-->
<!--      ContentList-->
<!--    },-->
<!--    data () {-->
<!--      return {-->
<!--        dramaCate: [-->
<!--          {-->
<!--            name: '全部美剧',-->
<!--            type: 'One'-->
<!--          },-->
<!--          {-->
<!--            name: '剧情',-->
<!--            type: '1'-->
<!--          },-->
<!--          {-->
<!--            name: '悬疑',-->
<!--            type: '2'-->
<!--          },-->
<!--          {-->
<!--            name: '科幻',-->
<!--            type: '3'-->
<!--          },-->
<!--          {-->
<!--            name: '喜剧',-->
<!--            type: '4'-->
<!--          },-->
<!--          {-->
<!--            name: '动作',-->
<!--            type: '5'-->
<!--          },-->
<!--          {-->
<!--            name: '爱情',-->
<!--            type: 'Seven'-->
<!--          },-->
<!--        ],-->
<!--        activeName: '全部美剧',-->
<!--        pageSize: 15, // 页数-->
<!--        currentPage: 1, // 当前页-->
<!--        albumDatas: [] // 歌单-->
<!--      }-->
<!--    },-->
<!--    computed: {-->
<!--      ...mapGetters([-->
<!--        'songsList'-->
<!--      ]),-->
<!--      // 计算当前表格中的数据-->
<!--      data () {-->
<!--        return this.albumDatas.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)-->
<!--      }-->
<!--    },-->
<!--    mounted () {-->
<!--      this.handleChangeView('全部美剧')-->
<!--    },-->
<!--    methods: {-->
<!--      // 获取当前页-->
<!--      handleCurrentChange (val) {-->
<!--        this.currentPage = val-->
<!--      },-->
<!--      handleChangeView: function (item) {-->
<!--        this.activeName = item.name-->
<!--        this.albumDatas = []-->
<!--        if (item.name === '全部美剧') {-->
<!--          this.getDrama(this.cur_page)-->
<!--        } else {-->
<!--          this.getSongListOfStyle(item.type)-->
<!--        }-->
<!--      },-->
<!--      // 获取美剧-->
<!--      // handleChangeView: function (name) {-->
<!--      //   this.activeName = name-->
<!--      //   this.albumDatas = []-->
<!--      //   if (name === '全部美剧') {-->
<!--      //     this.getDrama(this.cur_page)-->
<!--      //   } else {-->
<!--      //     this.getSongListOfStyle(name)-->
<!--      //   }-->
<!--      // },-->
<!--      // getCategoryName () {-->
<!--      //   let _this = this-->
<!--      //   let value = document.getElementById('categoryName').value-->
<!--      //   _this.$axios.get(`${_this.$store.state.HOST}/drama/name/detail?name=${value}`).then(function (res) {-->
<!--      //     if (!res.data.length) {-->
<!--      //       _this.$notify({-->
<!--      //         title: '系统暂无该分类',-->
<!--      //         type: 'warning'-->
<!--      //       })-->
<!--      //     } else {-->
<!--      //       _this.addSong(res.data[0].id)-->
<!--      //     }-->
<!--      //   }).catch(function (error) {-->
<!--      //     console.log(error)-->
<!--      //   })-->
<!--      // },-->
<!--      // 获取全部美剧-->
<!--      getDrama () {-->
<!--        let _this = this-->
<!--        axios.get(`${_this.$store.state.configure.HOST}/drama`)-->
<!--          .then((res) => {-->
<!--            _this.currentPage = 1-->
<!--            _this.albumDatas = res.data-->
<!--          })-->
<!--      },-->
<!--      // 通过分类获取美剧-->
<!--      getSongListOfStyle (categoryId) {-->
<!--        let _this = this-->
<!--        axios.get(`${_this.$store.state.configure.HOST}/drama/category/detail?categoryId=${categoryId}`)-->
<!--          .then((res) => {-->
<!--            _this.currentPage = 1-->
<!--            _this.albumDatas = res.data-->
<!--          })-->
<!--          .catch(function (error) {-->
<!--            console.log(error.response)-->
<!--          })-->
<!--        // .then(res => {-->
<!--        //   _this.currentPage = 1-->
<!--        //   _this.albumDatas = res.data-->
<!--        // })-->
<!--      }-->
<!--    }-->
<!--  }-->
<!--</script>-->

<!--<style scoped>-->
<!--  .drama-page {-->
<!--    margin: 30px 150px;-->
<!--    padding-bottom: 50px;-->
<!--    min-width: 800px;-->
<!--    background-color: #ffffff;-->
<!--  }-->

<!--  .drama-header {-->
<!--    width: 100%;-->
<!--    padding: 0 40px;-->
<!--  }-->

<!--  li {-->
<!--    display: inline-block;-->
<!--    line-height: 40px;-->
<!--    margin: 40px 20px 15px 20px;-->
<!--    font-size: 20px;-->
<!--    font-weight: 400;-->
<!--    color: #67757f;-->
<!--    border-bottom: none;-->
<!--    cursor: pointer;-->
<!--  }-->

<!--  .active {-->
<!--    color: black;-->
<!--    font-weight: 600;-->
<!--    border-bottom: 4px solid black;-->
<!--  }-->

<!--  .pagination {-->
<!--    display: flex;-->
<!--    justify-content: center;-->
<!--  }-->
<!--</style>-->

