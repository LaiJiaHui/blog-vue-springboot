<template>

  <el-card :body-style="{ padding: '8px 18px' }">
    <div slot="header" class="me-category-header">
      <span>分类</span>
    </div>
    <ul class="me-category-list">
      <li class="me-category-item" v-for="c in categorys" :key="c.id"><a @click="view(c.id)">{{c.categoryname}}</a></li>
    </ul>
  </el-card>

</template>

<script>
  export default {
    name: 'CardCategory',
    props: {
      categorys: Array
    },
    data() {
      return {}
    },
    methods: {
      view(id) {
        this.$router.push({path: `/category/${id}`})
      }
    }
  }
</script>

<style scoped>
  .me-category-header {
    font-weight: 600;
  }

  .me-category-list {
    list-style-type: none;
  }

  .me-category-item {
    padding: 4px;
    font-size: 14px;
    color: #5FB878;
  }

  .me-category-item a:hover {
    text-decoration: underline;
  }
</style>
