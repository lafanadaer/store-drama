<template>
  <div class="table">
    <div class="my-bg"></div>
    <div class="container">
<!--      <div class="handle-box">-->
<!--        <el-button type="primary" icon="delete" class="handle-del mr10" @click="delAll">批量删除</el-button>-->
<!--        <el-input v-model="select_word" placeholder="筛选关键词" class="handle-input mr10"></el-input>-->
<!--        <el-button type="primary" @click="centerDialogVisible = true">添加美剧</el-button>-->
<!--      </div>-->
      <el-table :data="data" stripe border style="width: 100%" ref="multipleTable" height="500px" @selection-change="handleSelectionChange">
        <el-table-column label="标题" prop="title" width="300"></el-table-column>
        <el-table-column label="内容">
          <template slot-scope="scope">
            <p style="height: 100px; overflow: scroll">{{ scope.row.content }}</p>
          </template>
        </el-table-column>
<!--        <el-table-column label="用户评论" width="150">-->
<!--          <template  slot-scope="scope">-->
<!--            <el-button size="mini" @click="getComment(data[scope.$index].id)">评论</el-button>-->
<!--          </template>-->
<!--        </el-table-column>-->
        <el-table-column label="操作" width="150">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
<!--            <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>-->
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


    <el-dialog title="编辑" :visible.sync="editVisible" width="400px">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="标题">
          <el-input v-model="form.title"></el-input>
        </el-form-item>
        <el-form-item label="内容">
          <el-input v-model="form.content"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
    </el-dialog>

<!--    &lt;!&ndash; 删除提示框 &ndash;&gt;-->
<!--    <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>-->
<!--      <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>-->
<!--      <span slot="footer" class="dialog-footer">-->
<!--                <el-button @click="delVisible = false">取 消</el-button>-->
<!--                <el-button type="primary" @click="deleteRow">确 定</el-button>-->
<!--            </span>-->
<!--    </el-dialog>-->
  </div>
</template>
<script>
  import {mixin} from '../mixins'
  import axios from 'axios'
  import { mapGetters } from 'vuex'
  import '@/assets/js/iconfont.js'

  export default {
    name: 'personal-chat-page',
    mixins: [mixin],
    data () {
      return {
        registerForm: { // 注册
          // name: '',
          title: '',
          content: '',
        },
        tableData: [], // 记录用户信息，用于显示
        tempDate: [], // 记录用户信息，用于搜索时能临时记录一份用户信息
        is_search: false,
        multipleSelection: [], // 记录要删除的用户信息
        centerDialogVisible: false,
        editVisible: false, // 显示编辑框
        delVisible: false, // 显示删除框
        select_word: '', // 记录输入框输入的内容
        form: { // 记录编辑的信息
          id: '',
          // categoryName: '',
          //categoryId: '',
          userId: '',
          title: '',
          content: '',
          // pic: '',
          // createTime: '',
          // updateTime: ''
        },
        pageSize: 5, // 页数
        currentPage: 1, // 当前页
        idx: -1 // 记录当前点中的行
      }
    },

    computed: {
      // 计算当前表格中的数据
      ...mapGetters([
          'userId', // 用户ID
      ]),
      data () {
        return this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
      }
    },
    watch: {
      select_word: function () {
        if (this.select_word === '') {
          this.tableData = this.tempDate
        } else {
          this.tableData = []
          for (let item of this.tempDate) {
            if (item.name.includes(this.select_word)) {
              this.tableData.push(item)
            }
          }
        }
      }
    },
    created () {
      this.getData()
    },
    destroyed () {
      this.$store.commit('setIsPlay', false)
    },
    methods: {
      // 拉取数据
      getData () {
        var _this = this
        _this.tableData = []
        _this.tempDate = []
        // axios.get(`${_this.$store.state.configure.HOST}/chat`).then((res) => {
        axios.get(`${_this.$store.state.configure.HOST}/chat/user/detail?userId=${this.userId}`)
          .then((res) => {

          // console.log('美剧信息===========>', res.data)
          _this.tableData = res.data
          _this.tempDate = res.data
          _this.currentPage = 1
        })
      },
      // 更新美剧图片
      uploadUrl (id) {
        return `${this.$store.state.HOST}/drama/img/update?id=${id}`
      },

      // // 获取当前页
      handleCurrentChange (val) {
        this.currentPage = val
      },
      // 添加音乐
      getSingerName () {
        let _this = this
        let value = document.getElementById('categoryName').value
        _this.$axios.get(`${_this.$store.state.HOST}/dramaCate/name/detail?name=${value}`).then(function (res) {
          if (!res.data.length) {
            _this.$notify({
              title: '系统暂无该分类',
              type: 'warning'
            })
          } else {
            _this.addSong(res.data[0].id)
          }
        }).catch(function (error) {
          console.log(error)
        })
      },
      handleSongSuccess (res, file) {
        let _this = this
        if (res.code === 1) {
          _this.getData()
          _this.$notify({
            title: '上传成功',
            type: 'success'
          })
        } else {
          _this.$notify({
            title: '上传失败',
            type: 'error'
          })
        }
      },
      addSong (id) {
        let _this = this
        // var params = new URLSearchParams()
        // params.append('categoryId', id)
        // params.append('name', this.registerForm.name)
        // params.append('introduction', this.registerForm.introduction)
        // _this.$axios.post(`${_this.$store.state.HOST}/drama/add`, params)

        var form = new FormData(document.getElementById('tf'))

        form.append('chatId', id)
        form.append('title', this.name)
        form.append('content', this.content)
        // form.append('name', this.tf.name)
        // form.append('introduction', this.tf.introduction)
        _this.$axios.post(`${_this.$store.state.HOST}/chat/add`, form)
          .then(res => {
            if (res.data.code === 1) {
              _this.getData()
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

      handleEdit (row) {
        this.idx = row.id
        this.form = {
          id: row.id,
          // chatId: row.chatId,
          userId: row.userId,
          title: row.title,
          content: row.content,
          // createTime: row.createTime,
          // updateTime: row.updateTime,
          // pic: row.pic,
        }
        this.editVisible = true
      },

      // handleEdit (row) {
      //     this.idx = row.id
      //     this.form = {
      //         id: row.id,
      //         // chatId: row.chatId,
      //         userId: row.userId,
      //         title: row.title,
      //         content: row.content,
      //         createTime: row.createTime,
      //         // updateTime: row.updateTime,
      //         // pic: row.pic,
      //     }
      //     this.editVisible = true
      // },
      getComment (id) {
        this.$router.push({path: '/Comment', query: {id: id, type: 0}})
      },
      // 保存编辑
      saveEdit () {
        let _this = this
        var params = new URLSearchParams()
        params.append('id', this.form.id)
        // params.append('categoryId', this.form.chatId)
        params.append('userId', this.form.userId)
        params.append('title', this.form.title)
        params.append('content', this.form.content)
        // params.append('lyric', this.form.lyric)
        axios.post(`${_this.$store.state.configure.HOST}/chat/update`, params)
          .then(response => {
            if (response.data) {
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
      // 确定删除
      deleteRow () {
        var _this = this
        // axios.get(`${_this.$store.state.configure.HOST}/chat/delete?id=${_this.idx}`)
        axios.get(`${_this.$store.state.configure.HOST}/chat/delete?id=${id}`)
          .then(response => {
            if (response.data) {
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
      // parseLyric (text) {
      //     let lines = text.split('\n')
      //     let pattern = /\[\d{2}:\d{2}.(\d{3}|\d{2})\]/g
      //     let result = []
      //     for (let item of lines) {
      //         if (pattern.test(item)) {
      //             let value = item.replace(pattern, '') // 存歌词
      //             result.push(value)
      //         }
      //     }
      //     return result
      // }
    }
  }

</script>

<style scoped>
  .my-bg {
    width: 100%;
    height: 100px;
    background-color: #FF8C00;
  }
  .container{
    width: 900px;
    margin-left: 300px;
    margin-top: 50px;
  }
  .handle-box {
    margin-bottom: 20px;
  }
  .handle-input {
    width: 300px;
    display: inline-block;
  }
  .el-input__inner{
    background-color: aqua
  }
  .play {
    position: absolute;
    z-index: 100;
    width: 80px;
    height: 80px;
    top: 20px;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
  }
  .icon {
    width: 2em;
    height: 2em;
    color: white;
    fill: currentColor;
    overflow: hidden;
  }
  .pagination {
    display: flex;
    justify-content: center;
  }
</style>

