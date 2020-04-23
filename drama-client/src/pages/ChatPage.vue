<template>
  <div class="drama-page">
<!--    <div class="handle-box">-->
<!--      <el-button type="primary" @click="centerDialogVisible = true">发布帖子</el-button>-->
<!--    </div>-->
<!--    <div class="handle-box">-->
<!--      <el-button type="primary" icon="delete" class="handle-del mr10" @click="delAll">批量删除</el-button>-->
      <el-input v-model="select_word" placeholder="搜索帖子" class="handle-input mr10"></el-input>
      <el-button type="primary" @click="centerDialogVisible = true">发布帖子</el-button>
<!--    </div>-->
    <div class="drama-header">
      <ul>
        <li
          v-for="(item, index) in dramaCate"
          :key="index"
          :class="{active: item.name === activeName}"
          @click="handleChangeView(item.name)">
          {{item.name}}
        </li>
<!--        <el-button type="primary" @click="centerDialogVisible = true">发布帖子</el-button>-->
<!--        <el-input v-model="select_word" placeholder="搜索美剧" class="handle-input mr10"></el-input>-->
      </ul>
    </div>
    <content-list :contentList="data"></content-list>
    <div class="pagination">
      <div class="pagination">

        <el-pagination
          @current-change="handleCurrentChange"
          background
          layout="total, prev, pager, next"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="albumDatas.length">
        </el-pagination>

      </div>
    </div>

    <!--添加新用户-->
    <el-dialog title="添加用户" :visible.sync="centerDialogVisible" width="400px" center>
      <el-form :model="registerForm" status-icon :rules="rules" ref="registerForm" label-width="70px" class="demo-ruleForm">
        <el-form-item prop="title" label="标题">
          <el-input v-model="registerForm.title" placeholder="标题"></el-input>
        </el-form-item>
        <el-form-item prop="content" label="内容">
          <el-input  type="textarea" placeholder="内容" v-model="registerForm.content" ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addChat">确 定</el-button>
  </span>
    </el-dialog>

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
        // dramaCate: [
        //   {
        //     // name: '全部帖子',
        //     // name: '',
        //     //type: 'One'
        //   },
        //
        // ],
        // tableData: [],
        // tempDate: [],
        // is_search: false,
        // multipleSelection: [], // 记录要删除的歌曲
        centerDialogVisible: false,
        // editVisible: false,
        // delVisible: false,
        // select_word: '',
        form: {
          id: '',
          userId: '',
          title: '',
          content: '',
          createTime: ''
        },
        registerForm: { // 注册
          title: '',
          content: ''
        },
        activeName: '全部帖子',
        pageSize: 15, // 页数
        currentPage: 1, // 当前页
        select_word: '',
        tempDate: [],
        albumDatas: [] // 歌单
      }
    },
    computed: {
      ...mapGetters([
        'userId'
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
            if (item.title.includes(this.select_word)) {
              this.albumDatas.push(item)
            }
          }
        }
      }
    },
    mounted () {
      this.handleChangeView('全部帖子')
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
        if (name === '全部帖子') {
          this.getDrama()
          //this.getDrama(this.cur_page)
        } else {
          this.getDrama()
        }
      },

      addChat () {
        let _this = this
        var params = new URLSearchParams()
        params.append('userId', this.userId)
        params.append('title', this.registerForm.title)
        params.append('content', this.registerForm.content)
        axios.post(`${_this.$store.state.configure.HOST}/chat/add`,params)
        // axios.get(`${_this.$store.state.HOST}/chat/add`, params)
          .then(res => {
            if (res.data.code === 1) {
              _this.getDrama()
              _this.registerForm = {}
              _this.$notify({
                title: '添加成功',
                type: 'success'
              })
            } else {
              _this.$notify({
                title: '添加失败',
                type: 'error'
              })
            }
          })
          .catch(failResponse => {})
        _this.centerDialogVisible = false
      },

      // 获取全部帖子
      getDrama () {
        let _this = this
        _this.albumDatas = []
        _this.tempDate = []
        axios.get(`${_this.$store.state.configure.HOST}/chat`)
          .then((res) => {
            _this.currentPage = 1
            _this.albumDatas = res.data
            _this.tempDate = res.data
          })
      },

    }
  }
</script>

<style scoped>
  .handle-box {
    margin-bottom: 20px;
  }

  .handle-input {
    width: 610px;
    margin-top: 30px;
    margin-left: 250px;
    display: inline-block;
  }


  .handle-box button{
    display: block;
    width: 10%;
    margin-left: 385px;
    background-color: #FF8C00
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



