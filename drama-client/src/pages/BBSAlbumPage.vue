<!--<template>-->
<!--  <div class="bbs-album-page">-->
<!--    <el-table-->
<!--      :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"-->
<!--      style="width: 100%">-->
<!--      <el-table-column-->
<!--        label="作者"-->
<!--        prop="name">-->
<!--        <template slot-scope="scope">-->
<!--          <div>{{ scope.row.name }}</div>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column-->
<!--        label="标题"-->
<!--        prop="title">-->
<!--        <template slot-scope="scope">-->
<!--          <div>{{ scope.row.title }}</div>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column-->
<!--        align="right">-->
<!--        <template slot="header" slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            @click="handleChat(scope.$index, scope.row)">发帖</el-button>-->
<!--        </template>-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            @click="handleDetail(scope.$index, scope.row)">详情</el-button>-->

<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->

<!--    <el-pagination-->
<!--      background-->
<!--      layout="prev, pager, next"-->
<!--      :page-size="pageSize"-->
<!--      :total="total"-->
<!--      @current-change="page">-->
<!--    </el-pagination>-->
<!--  </div>-->
<!--</template>-->






<!--<script>-->
<!--  import axios from 'axios'-->
<!--  export default {-->
<!--    data () {-->
<!--      return {-->

<!--        tableData: [],-->
<!--        tempDate: [],-->

<!--        pageSize: 5, // 页数-->
<!--        currentPage: 1, // 当前页-->
<!--        idx: -1-->
<!--      }-->
<!--    },-->
<!--    computed: {-->
<!--      // 计算当前表格中的数据-->
<!--      data () {-->
<!--        return this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)-->
<!--      }-->
<!--    },-->
<!--    watch: {-->
<!--      select_word: function () {-->
<!--        if (this.select_word === '') {-->
<!--          this.tableData = this.tempDate-->
<!--        } else {-->
<!--          this.tableData = []-->
<!--          for (let item of this.tempDate) {-->
<!--            if (item.title.includes(this.select_word)) {-->
<!--              this.tableData.push(item)-->
<!--            }-->
<!--          }-->
<!--        }-->
<!--      }-->
<!--    },-->
<!--    // data() {-->
<!--    //   return {-->
<!--    //     tableData: [],-->
<!--    //     search: ''-->
<!--    //   }-->
<!--    // },-->
<!--    methods: {-->
<!--      getData () {-->
<!--        let _this = this-->
<!--        _this.tableData = []-->
<!--        _this.tempDate = []-->
<!--        _this.$axios.get(`${_this.$store.state.HOST}/songList`).then((res) => {-->
<!--          _this.tableData = res.data-->
<!--          _this.tempDate = res.data-->
<!--          _this.currentPage = 1-->
<!--        })-->
<!--      },-->
<!--      handleEdit(index, row) {-->
<!--        console.log(index, row);-->
<!--      },-->
<!--      handleDelete(index, row) {-->
<!--        console.log(index, row);-->
<!--      },-->
<!--      page(currentPage){-->
<!--        const _this = this-->
<!--        axios.get('http://localhost:8888/chat/'+(currentPage-1)+'/3').then(function(resp){-->
<!--          console.log(resp)-->
<!--          _this.tableData = resp.data.content-->
<!--          _this.pageSize = resp.data.size-->
<!--          _this.total = resp.data.totalElements-->
<!--        })-->
<!--      }-->
<!--    },-->
<!--    created() {-->
<!--      const _this = this-->
<!--      axios.get('http://localhost:8888/book/0/3').then(function(resp){-->
<!--        console.log(resp)-->
<!--        _this.tableData = resp.data.content-->
<!--        _this.pageSize = resp.data.size-->
<!--        _this.total = resp.data.totalElements-->
<!--      })-->
<!--    }-->
<!--  }-->
<!--</script>-->

<template>
  <div class="bbs-album-page">
    <div class="container">
      <div class="handle-box">
        <el-button type="primary" @click="centerDialogVisible = true">发帖</el-button>
      </div>
      <el-table :data="data" border stripe style="width: 100%" height="500px" ref="multipleTable" @selection-change="handleSelectionChange">
        <el-table-column prop="userId" label="作者" width="200"></el-table-column>
        <el-table-column prop="title" label="标题" width="600"></el-table-column>
        <el-table-column label="详情" width="80">
          <template  slot-scope="scope">
            <el-button size="mini" @click="getComment(data[scope.$index].id)">详情</el-button>
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
        <el-form-item prop="content" label="内容">
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
  import axios from 'axios'
  import { mixin } from '../mixins'
  import {mapGetters} from 'vuex'

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
          userId:'',
          title: '',
          content: '',
          // createTime: ''
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
      },
      ...mapGetters([
        'userId',
      ])
    },
    // computed: {
    //   ...mapGetters([
    //     'userId',
    //   ])
    // },

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
        axios.get(`${_this.$store.state.configure.HOST}/chat`)
          .then((res) => {
          _this.tableData = res.data
          _this.tempDate = res.data
          _this.currentPage = 1
        })
      },
      // 获取当前页
      handleCurrentChange (val) {
        this.currentPage = val
      },
      getComment (id) {
        // this.$router.push({path: '/drama-album-page', query: {id: id, type: 1}})
        // this.$router.push({path: '/drama-album-page', query: {id: id}})
        this.$router.push({path: '/drama-album-page', query: {id: id}})


      },

      // getCollection (){
      //   let ab = this
      //   axios.get(ab.$store.state.configure.HOST + '/user/detail?id=' + id)
      //     .then(function (response) {
      //       ab.collection = response.data
      //       // _this.$store.commit('setListOfSongs', _this.collectList)
      //         })
      //
      // },
      // 发帖
      addsongList () {

        let _this = this
        let params = new URLSearchParams()
        params.append('userId', _this.userId)
        params.append('title', _this.registerForm.title)
        params.append('content', _this.registerForm.content)
        //params.append('create_time', _this.form.createTime)
        axios.post(`${_this.$store.state.configure.HOST}/chat/add`, params).then(res => {
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
