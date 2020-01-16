<template>
  <div v-title data-title="狗蛋的码农日志">
    <el-container>
      <el-aside>

        <card-me class="me-area"></card-me>

        <card-category  cardHeader="文章分类" :categorys="categorys"></card-category>

        <card-tag  cardHeader="最热标签" :tags="hotTags"></card-tag>

        <card-archive cardHeader="文章归档" :archives="archives"></card-archive>

      </el-aside>

      <el-main class="me-articles">

        <article-scroll-page></article-scroll-page>

      </el-main>

      <el-aside>

        <card-article cardHeader="最热文章" :articles="hotArticles"></card-article>

        <card-article cardHeader="最新文章" :articles="newArticles"></card-article>

      </el-aside>

    </el-container>
  </div>
</template>

<script>
  import CardMe from '@/components/card/CardMe'
  import CardArticle from '@/components/card/CardArticle'
  import CardArchive from '@/components/card/CardArchive'
  import CardTag from '@/components/card/CardTag'
  import CardCategory from '@/components/card/CardCategory'
  import ArticleScrollPage from '@/views/common/ArticleScrollPage'

  import {getArticles, getHotArtices, getNewArtices} from '@/api/article'
  import {getHotTags} from '@/api/tag'
  import {listArchives} from '@/api/article'
  import {getAllCategorysDetail} from '@/api/category'

  export default {
    name: 'Index',
    created() {
      this.getCategorys()
      this.getHotArtices()
      this.getNewArtices()
      this.getHotTags()
      this.listArchives()
    },
    data() {
      return {
        categorys: [],
        hotTags: [],
        hotArticles: [],
        newArticles: [],
        archives: []
      }
    },
    methods: {
      getCategorys() {
        let that = this
        getAllCategorysDetail().then(data => {
          that.categorys = data.data
      }).catch(error => {
          if (error !== 'error') {
          that.$message({type: 'error', message: '文章分类加载失败', showClose: true})
        }
      })
      },
      getHotArtices() {
        let that = this
        getHotArtices().then(data => {
          that.hotArticles = data.data
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '最热文章加载失败!', showClose: true})
          }

        })

      },
      getNewArtices() {
        let that = this
        getNewArtices().then(data => {
          that.newArticles = data.data
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '最新文章加载失败!', showClose: true})
          }

        })

      },
      getHotTags() {
        let that = this
        getHotTags().then(data => {
          that.hotTags = data.data
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '最热标签加载失败!', showClose: true})
          }

        })
      },
      listArchives() {
        listArchives().then((data => {
          this.archives = data.data
        })).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '文章归档加载失败!', showClose: true})
          }
        })
      }

    },
    components: {
      'card-me': CardMe,
      'card-article': CardArticle,
      'card-tag': CardTag,
      'card-category': CardCategory,
      ArticleScrollPage,
      CardArchive
    }
  }
</script>

<style scoped>

  .el-container {
    width: 1440px;
  }

  .el-aside {
    margin-right: 20px;
    margin-left: 20px;
    width: 320px;
  }

  .el-main {
    padding: 0px;
    line-height: 16px;
  }

  .el-card {
    border-radius: 0;
  }

  .el-card:not(:first-child) {
    margin-top: 20px;
  }
</style>
