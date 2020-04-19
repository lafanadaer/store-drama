<template>
  <div class="table">
    <div class="container">
      <div class="handle-box">
        <el-button type="primary" icon="delete" class="handle-del mr10" @click="delAll">批量删除</el-button>
        <el-input v-model="select_word" placeholder="筛选关键词" class="handle-input mr10"></el-input>
        <el-button type="primary" @click="centerDialogVisible = true">添加美剧</el-button>
      </div>
      <el-table :data="data" stripe border style="width: 100%" ref="multipleTable" height="500px" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="40"></el-table-column>
        <el-table-column label="美剧图片" width="100">
          <template slot-scope="scope">
            <img :src="getUrl(scope.row.pic)" alt="" style="width: 80px;"/>
            <el-upload
              class="upload-demo"
              :action="uploadUrl(scope.row.id)"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <el-button size="small" type="primary">更新图片</el-button>
            </el-upload>
          </template>
        </el-table-column>
        <el-table-column label="分类-美剧" prop="name" width="150"></el-table-column>
<!--        <el-table-column label="分类" prop="categoryName" width="150"></el-table-column>-->
        <el-table-column label="简介">
          <template slot-scope="scope">
            <p style="height: 100px; overflow: scroll">{{ scope.row.introduction }}</p>
          </template>
        </el-table-column>
        <el-table-column label="用户评论" width="150">
          <template  slot-scope="scope">
            <el-button size="mini" @click="getComment(data[scope.$index].id)">评论</el-button>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
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

    <!--添加美剧-->
    <el-dialog title="添加美剧" :visible.sync="centerDialogVisible" width="400px" center>
      <!--      <el-form-->
      <!--        :model="registerForm"-->
      <!--        status-icon-->
      <!--        ref="registerForm"-->
      <!--        label-width="80px"-->
      <!--        class="demo-ruleForm"-->
      <!--      >-->
      <!--        <el-form-item prop="categoryName" label="分类">-->
      <!--          <el-input v-model="registerForm.categoryName" placeholder="分类"></el-input>-->
      <!--        </el-form-item>-->
      <!--        <el-form-item prop="name" label="美剧名">-->
      <!--          <el-input v-model="registerForm.name" placeholder="美剧名"></el-input>-->
      <!--        </el-form-item>-->
      <!--        <el-form-item prop="introduction" label="介绍">-->
      <!--          <el-input v-model="registerForm.introduction" placeholder="介绍"></el-input>-->
      <!--        </el-form-item>-->
      <form action="" id="tf">
        <div style="display: flex; justify-content: space-around;">
          <div style="width:48%">
            <label>分类</label>
            <el-input type="text" name="categoryName" id="categoryName"></el-input>
          </div>
          <div style="line-height: 80px">-</div>
<!--          <div style="width:48%">-->
<!--            <label>分类</label>-->
<!--            <el-input type="text" name="categoryName"></el-input>-->
<!--          </div>-->
          <div style="width:48%">
            <label>美剧名</label>
            <el-input type="text" name="name"></el-input>
          </div>
        </div>
        <div>
          <label>美剧介绍</label>
          <el-input type="textarea" value="" name="introduction"></el-input>
        </div>
        <!--      </el-form>-->
      </form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="centerDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="getSingerName">确 定</el-button>
            </span>
    </el-dialog>

    <!-- 编辑弹出框 -->
    <el-dialog title="编辑" :visible.sync="editVisible" width="400px">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="分类-美剧">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="简介">
          <el-input v-model="form.introduction"></el-input>
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
    import {mixin} from '../mixins'
    import { mapGetters } from 'vuex'
    import '@/assets/js/iconfont.js'

    export default {
        name: 'drama-page',
        mixins: [mixin],
        data () {
            return {
                registerForm: { // 注册
                    name: '',
                    categoryName: '',
                    introduction: '',
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
                    categoryId: '',
                    name: '',
                    introduction: '',
                    pic: '',
                    createTime: '',
                    updateTime: ''
                },
                pageSize: 5, // 页数
                currentPage: 1, // 当前页
                idx: -1 // 记录当前点中的行
            }
        },

        computed: {
            // 计算当前表格中的数据
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
                _this.$axios.get(`${_this.$store.state.HOST}/drama`).then((res) => {
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

                // var arr=new Array();
                // arr.push(this.categoryName);
                // arr.push(this.name);
                // var str=arr.join("-");
                // var str ;
                // str = this.categoryName;
                // str = str + this.name;
                //var str1 = this.categoryName
                // var str2 = this.name
                // var str3 ="-"
                // var str
                // str = str1 + str3
                // str = this.categoryName
                // str = str + this.name
                var form = new FormData(document.getElementById('tf'))
                form.append('categoryId', id)
                // form.append('name', str)
                // form.append('categoryId', id)
                // form.append('name', this.name)
                // form.append('introduction', this.introduction)
                // form.append('name', this.tf.name)
                // form.append('introduction', this.tf.introduction)
                _this.$axios.post(`${_this.$store.state.HOST}/drama/add`, form)
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
                    categoryId: row.categoryId,
                    name: row.name,
                    introduction: row.introduction,
                    createTime: row.createTime,
                    updateTime: row.updateTime,
                    pic: row.pic,
                }
                this.editVisible = true
            },
            getComment (id) {
                this.$router.push({path: '/Comment', query: {id: id, type: 1}})
            },
            // 保存编辑
            saveEdit () {
                var params = new URLSearchParams()
                params.append('id', this.form.id)
                params.append('categoryId', this.form.categoryId)
                params.append('name', this.form.name)
                params.append('introduction', this.form.introduction)
                // params.append('lyric', this.form.lyric)
                this.$axios.post(`${this.$store.state.HOST}/drama/update`, params)
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
                _this.$axios.get(`${_this.$store.state.HOST}/drama/delete?id=${_this.idx}`)
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

