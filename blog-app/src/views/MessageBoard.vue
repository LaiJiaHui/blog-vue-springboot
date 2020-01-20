<template>

  <div class="me-message" v-title :data-title="title">
    <el-container class="">
      <el-main class="me-main">
        <el-alert
          title="欢迎吐槽。。。。。。。。。。。"
          type="success"
          center
          show-icon>
        </el-alert>

        <div class="me-view-comment">
          <div class="me-view-comment-write">
            <el-row :gutter="20">
              <el-col :span="2">
                <a class="">
                  <img class="me-view-picture" :src="avatar"></img>
                </a>
              </el-col>
              <el-col :span="22">
                <el-input
                  type="textarea"
                  :autosize="{ minRows: 2}"
                  placeholder="吐槽区..."
                  class="me-view-comment-text"
                  v-model="split.content"
                  resize="none">
                </el-input>
              </el-col>
            </el-row>

            <el-row :gutter="20">
              <el-col :span="2" :offset="22">
                <el-button type="text" @click="publishComment()">发表</el-button>
              </el-col>
            </el-row>

            <split-item
              v-for="(c,index) in splits"
              :split="c"
              :index="index"
              :rootCommentCounts="splits.length"
              :key="c.id">
            </split-item>

          </div>
        </div>
      </el-main>

    </el-container>
  </div>
</template>

<script>
  import SplitItem from '@/components/comment/SplitItem'
  import {createSplit, replySplit,thumbsSplit,getSplitlist} from '@/api/split'
  import default_avatar from '@/assets/img/default_avatar.png'
  export default {
    name: 'MessageBoard',
    created(){
      this.getSplit()
    },
    data() {
      return {
        pageVo: {
          pageNum: 0 ,
          pageSize: 10
        },
        splits:[],
        split: {
          content: ''
        }
      }
    },
    computed: {
      avatar() {
        let avatar = this.$store.state.avatar

        if (avatar.length > 0) {
          return avatar
        }
        return default_avatar
      },
      title (){
        return '留言板 - Gou Dan'
      }
    },

    methods: {
      publishComment() {
        let that = this
        if (!that.split.content) {
          return;
        }

        createSplit(that.split).then(data => {
          that.$message({type: 'success', message: '吐槽成功', showClose: true})
          that.split.content = ''
          that.getSplit()
      }).catch(error => {
          if (error !== 'error') {
          that.$message({type: 'error', message: '吐槽失败', showClose: true})
        }
      })
      },

      getSplit(){
        let that = this
        getSplitlist(that.pageVo).then(data => {
          that.splits = data.data.splits
        })
      }

    },
    components: {
      SplitItem
    }
  }
</script>

<style scoped>

  .me-view-comment {
    margin-top: 60px;
  }
  .me-view-comment-title {
    font-weight: 600;
    border-bottom: 1px solid #f0f0f0;
    padding-bottom: 20px;
  }

  .me-view-comment-write {
    margin-top: 20px;
  }

  .me-view-comment-text {
    font-size: 16px;
  }
  .me-message {
  }

  .el-container {
    width: 700px;
  }

  .me-main {
    overflow: hidden;
  }

  .me-log-box {
    margin-left: 30%;
    margin-top: 20px;

  }

</style>
