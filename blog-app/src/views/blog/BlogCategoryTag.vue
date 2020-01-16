<template>
  <div class="me-ct-body" v-title :data-title="title">
    <el-container class="me-ct-container">
      <el-main>
        <div class="me-ct-title me-area">
          <template v-if="this.$route.params.type === 'tag'">
            <img class="me-ct-picture" :src="ct.avatar?ct.avatar:defaultAvatar"/>
            <h3 class="me-ct-name">{{ct.tagname}}</h3>
          </template>

          <template v-else>
            <img class="me-ct-picture" :src="ct.avatar?ct.avatar:defaultAvatar"/>
            <h3 class="me-ct-name">{{ct.categoryname}}</h3>
            <p>{{ct.description}}</p>
          </template>

          <span class="me-ct-meta">{{ct.articles}} 文章</span>
        </div>

        <el-container>
          <el-aside id="el-left">
            <el-tree
              :data="data"
              :props="defaultProps"
              @node-click="handleNodeClick">
            </el-tree>
          </el-aside>

          <el-main>
          <div class="me-ct-articles">
            <article-scroll-page v-bind="article"></article-scroll-page>
          </div>
          </el-main>

          <el-aside id="el-right">
            <card-me class="me-area"></card-me>
          </el-aside>
        </el-container>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import CardMe from '@/components/card/CardMe'
  import ArticleScrollPage from '@/views/common/ArticleScrollPage'
  import {getArticlesByCategory, getArticlesByTag} from '@/api/article'
  import {getTagDetail} from '@/api/tag'
  import {getCategoryDetail} from '@/api/category'
  import defaultAvatar from '@/assets/img/logo.png'


  export default {
    name: 'BlogCategoryTag',
    created() {
      this.getCategoryOrTagAndArticles()
    },
    watch: {
      '$route': 'getCategoryOrTagAndArticles'
    },
    data() {
      return {
        defaultAvatar: defaultAvatar,
        ct: {},
        article: {
          query: {
            tagId: '',
            categoryId: ''
          }
        },
        data: [{
          label: '一级 1',
          children: [{
            label: '二级 1-1',
            children: [{
              label: '三级 1-1-1'
            }]
          }]
        }, {
          label: '一级 2',
          children: [{
            label: '二级 2-1',
            children: [{
              label: '三级 2-1-1'
            }]
          }, {
            label: '二级 2-2',
            children: [{
              label: '三级 2-2-1'
            }]
          }]
        }, {
          label: '一级 3',
          children: [{
            label: '二级 3-1',
            children: [{
              label: '三级 3-1-1'
            }]
          }, {
            label: '二级 3-2',
            children: [{
              label: '三级 3-2-1'
            }]
          }]
        }],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      }
    },
    computed: {
      title() {
        if(this.$route.params.type === 'tag'){
          return `${this.ct.tagname} - 标签 - Gou Dan`
        }
        return `${this.ct.categoryname} - 文章分类 - Gou Dan`
      }
    },
    methods: {
      handleNodeClick(data) {
        console.log(data);
      },
      getCategoryOrTagAndArticles() {
        let id = this.$route.params.id
        let type = this.$route.params.type
        if ('tag' === type) {
          this.getTagDetail(id)
          this.article.query.tagId = id
        } else {
          this.getCategoryDetail(id)
          this.article.query.categoryId = id
        }

      },
      getCategoryDetail(id) {
        let that = this
        getCategoryDetail(id).then(data => {
          that.ct = data.data
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '文章分类加载失败', showClose: true})
          }
        })
      },
      getTagDetail(id) {
        let that = this
        getTagDetail(id).then(data => {
          that.ct = data.data
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '标签加载失败', showClose: true})
          }
        })
      },
      getArticlesByCategory(id) {
        let that = this
        getArticlesByCategory(id).then(data => {
          that.articles = data.data
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '文章加载失败', showClose: true})
          }
        })
      },
      getArticlesByTag(id) {
        let that = this
        getArticlesByTag(id).then(data => {
          that.articles = data.data
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '文章加载失败', showClose: true})
          }
        })
      }
    },
    components: {
      'card-me': CardMe,
      ArticleScrollPage
    }
  }
</script>

<style>

  #el-left {
    margin-top: 50px;
    margin-left: 30px;
    width: 320px;
  }
  #el-right {
    margin-top: 50px;
    margin-right: 50px;
    width: 320px;
  }
  .me-ct-body {
    margin: 60px auto 140px;
    min-width: 100%;
  }

  .el-main {
    padding: 0;
  }

  .me-ct-title {
    text-align: center;
    height: 150px;
    padding: 20px;
  }

  .me-ct-picture {
    width: 60px;
    height: 60px;
  }

  .me-ct-name {
    font-size: 28px;
  }

  .me-ct-meta {
    font-size: 12px;
    color: #969696;
  }

  .me-ct-articles {
    width: 760px;
    margin-top: 30px;
    margin-left: 70px;
  }

</style>
