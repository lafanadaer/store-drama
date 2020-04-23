<template>
  <div class="bbs-album-page">
    <div class="container">
      <div class="handle-box">
        <el-button type="primary" @click="centerDialogVisible = true">发帖</el-button>
      </div>
      <el-table :data="data" border stripe style="width: 100%" height="500px" ref="multipleTable" @selection-change="handleSelectionChange">
        <el-table-column prop="name" label="作者" width="200"></el-table-column>
        <el-table-column prop="title" label="标题" width="200"></el-table-column>
        <el-table-column label="详情" width="80">
          <template  slot-scope="scope">
            <el-button size="mini" @click="getContent(data[scope.$index].id)">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
          @current-change="handleCurrentChange"
          background
          layout="total, prev, pager, next"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="tableData.length">
        </el-pagination>
      </div>
    </div>

    <!--发帖-->
    <el-dialog title="发帖" :visible.sync="centerDialogVisible" width="400px" center>
      <el-form :model="registerForm" status-icon ref="registerForm" label-width="70px" class="demo-ruleForm">
        <el-form-item prop="title" label="标题">
          <el-input v-model="registerForm.title" placeholder="标题"></el-input>
        </el-form-item>
        <el-form-item prop="introduction" label="内容">
          <el-input v-model="registerForm.content" placeholder="内容"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="centerDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="addsongList">确 定</el-button>
            </span>
    </el-dialog>
  </div>
</template>

<script>
  import {mixin} from '../mixins'
  import axios from 'axios'

  export default {
    name: 'bbs-album-page',
    data () {
      return {
        registerForm: {
          title: '',
          content: ''
        },
        tableData: [],
        tempDate: [],
        centerDialogVisible: false,
        form: {
          id: '',
          user_id:'',
          title: '',
          content: ''
        },
        pageSize: 5, // 页数
        currentPage: 1, // 当前页
        idx: -1
      }
    },
    computed: {
      // 计算当前表格中的数据
      data () {
        return this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
      }
    },

    created () {
      this.getData()
    },
    mixins: [mixin],
    methods: {

      // 获取歌单信息
      getData () {
        let _this = this
        _this.tableData = []
        _this.tempDate = []
        axios.get(`${_this.$store.state.HOST}/chat`).then((res) => {
          _this.tableData = res.data
          _this.tempDate = res.data
          _this.currentPage = 1
        })
      },
      // 获取当前页
      handleCurrentChange (val) {
        this.currentPage = val
      },
      getContent (id) {
        this.$router.push({path: '/listSong', query: {id: id}})
      },


      // 发帖
      addsongList () {
        let _this = this
        let params = new URLSearchParams()
        params.append('name', _this.registerForm.name)
        params.append('title', _this.registerForm.title)
        _this.$axios.post(`${_this.$store.state.HOST}/chat/add`, params).then(res => {
          if (res.data.code === 1) {
            _this.getData()
            _this.registerForm = {}
            _this.$notify({
              title: '发帖成功',
              type: 'success'
            })
          } else {
            _this.$notify({
              title: '发帖失败',
              type: 'error'
            })
          }
        }).catch(failResponse => {})
        _this.centerDialogVisible = false
      },
    }
  }
</script>

<style scoped>
  .handle-box {
    margin-bottom: 20px;
  }

  .handle-input {
    width: 300px;
    display: inline-block;
  }
  .pagination {
    display: flex;
    justify-content: center;
  }
</style>
