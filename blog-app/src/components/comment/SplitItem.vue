<template>
  <div class="me-view-comment-item">
    <div class="me-view-comment-author">
      <el-row>
        <el-col :span="2">
      <a class="">
        <img class="me-view-picture" :src="avatar"></img>
      </a>
        </el-col>
        <el-col :span="22">
      <div class="me-view-info">
        <span class="me-reply-user">{{split.authorName}}</span>
        <span class="me-view-comment-content">&nbsp;&nbsp;&nbsp;&nbsp;{{split.content}}</span>
      </div>
          <div class="me-view-info">
            &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <span>{{split.createDate | format}}</span>
          <span class="me-view-comment-tools">
          <a class="me-view-comment-tool" @click="showComment(-1)">
          &nbsp;&nbsp; <i class="me-icon-comment"></i>评论
          </a>
            <a class="me-view-comment-tool" @click="thumbs(split)">
               <i class="el-icon-thumb"></i>&nbsp;点赞数:{{split.thumbup}}
            </a>
          </span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div>

      <div class="me-reply-list">
        <div class="me-reply-item" v-for="c in split.childrens" :key="c.id">
          <div style="font-size: 14px">
            <span class="me-reply-user">{{c.authorName}}:&nbsp;&nbsp;</span>
            <span v-if="c.toUserName" class="me-reply-user">@{{c.toUserName}} </span>
            <span>&nbsp;&nbsp;{{c.content}}</span>
          </div>
          <div class="me-view-meta">
            &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="padding-right: 10px">{{c.createDate | format}}</span>
            <a class="me-view-comment-tool" @click="showComment(split.id, c.authorName)">
              <i class="me-icon-comment"></i>&nbsp;回复
            </a>
            <a class="me-view-comment-tool" @click="thumbs(c)">
               <i class="el-icon-thumb"></i>&nbsp;点赞数:{{c.thumbup}}
            </a>
          </div>

        </div>

        <div class="me-view-comment-write" v-show="commentShow">

          <el-input
            v-model="reply.content"
            type="input"
            style="width: 90%"
            :placeholder="placeholder"
            class="me-view-comment-text"
            resize="none">
          </el-input>

          <el-button style="margin-left: 8px" @click="publishComment()" type="text">评论</el-button>
        </div>
      </div>
    </div>
    <el-divider></el-divider>
  </div>

</template>

<script>
  import default_avatar from '@/assets/img/default_avatar.png'
  import {replySplit,thumbsSplit} from '@/api/split'

  export default {
    name: "SplitItem",
    props: {
      split: Object,
      index: Number,
      rootCommentCounts: Number
    },
    data() {
      return {
        placeholder: '你的吐槽...',
        commentShow: false,
        commentShowIndex: '',
        reply: this.getEmptyReply()
      }
    },
    computed: {
      avatar() {
        return default_avatar
      }
    },
    methods: {
      thumbs(split){
        let that = this
        thumbsSplit(split.id).then(data => {
          if(data.data.thumbs){
            split.thumbup=split.thumbup+1
            that.$message({type: 'success', message: '点赞成功', showClose: true})
          }else {
            split.thumbup=split.thumbup-1
            that.$message({type: 'success', message: '取消点赞', showClose: true})
          }
      }).catch(error => {
          if (error !== 'error') {
          that.$message({type: 'error', message: '点赞失败', showClose: true})
        }
      })

      },
      showComment(commentShowIndex, toUserName) {
        this.reply = this.getEmptyReply()

        if (this.commentShowIndex !== commentShowIndex) {

          if (toUserName) {
            this.placeholder = `@${toUserName} `
            this.reply.toUserName = toUserName
          } else {
            this.placeholder = '你的吐槽...'
          }

          this.commentShow = true
          this.commentShowIndex = commentShowIndex
        } else {
          this.commentShow = false
          this.commentShowIndex = ''
        }
      },
      publishComment() {
        let that = this
        if (!that.reply.content) {
          return;
        }

        replySplit(that.reply).then(data => {
          that.$message({type: 'success', message: '评论成功', showClose: true})
          that.split = data.data.split
          that.showComment(that.commentShowIndex)
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '评论失败', showClose: true})
          }
        })

      },
      getEmptyReply() {
        return {
          parentId: this.split.id,
          toUserName: '',
          content: ''
        }
      }
    }
  }
</script>

<style>
  .me-view-tag-item {
    margin: 0 4px;
  }

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

  .v-show-content {
    padding: 8px 25px 15px 0px !important;
  }

  .v-note-wrapper .v-note-panel {
    box-shadow: none !important;
  }

  .me-view-comment-item {
    margin-top: 20px;
    padding-bottom: 16px;
    border-bottom: 1px solid #f0f0f0;
  }

  .me-view-comment-author {
    margin: 10px 0;
    vertical-align: middle;
  }

  .me-view-nickname {
    font-size: 14px;
  }

  .me-view-comment-content {
    line-height: 1.5;
  }

  .me-view-comment-tools {
    margin-top: 4px;
    margin-bottom: 10px;
  }

  .me-view-comment-tool {
    font-size: 13px;
    color: #a6a6a6;
    padding-right: 14px;
  }

  .v-note-wrapper .v-note-panel .v-note-show .v-show-content, .v-note-wrapper .v-note-panel .v-note-show .v-show-content-html {
    background: #fff !important;
  }

  .me-reply-list {
    padding-left: 16px;
    border-left: 4px solid #c5cac3;
  }

  .me-reply-item {
    margin-bottom: 8px;
    border-bottom: 1px solid #f0f0f0;
  }

  .me-reply-user {
    color: #78b6f7;
  }
</style>
