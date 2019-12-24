<template>
  <el-card :body-style="{ padding: '8px 18px' }">
    <div slot="header" class="me-category-header">
      <span>{{cardHeader}}</span>
    </div>

    <ul class="me-category-list">
      <li v-for="a in articles" @click="view(a.id)" :style="itemStyle" :key="a.id" >
        <div>
        <img :src="a.displayPicture" width="250" height="140" />
        </div>
        <a class="me-category-title">{{a.title}}</a>
        <div class="me-category-time" >
	    	<i class="el-icon-time"></i>&nbsp;{{a.createDate | format}}
	    </div>


      </li>
    </ul>
  </el-card>

</template>

<script>
  import { formatTime } from "../../utils/time";

  export default {
    name: 'CardArticle',
    props: {
      cardHeader: {
        type: String,
        required: true
      },
      articles: {
        type: Array,
        required: true
      },
      itemStyle: Object
    },
    data() {
      return {}
    },
    methods: {
      view(id) {
        this.$router.push({path: `/view/${id}`})
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

  .me-category-title {
    padding: 4px;
    font-size: 16px;
    color: #5FB878;
  }
  .me-category-time{
    padding: 4px;
    font-size: 14px;
  }
  .me-category-item a:hover {
    text-decoration: underline;
  }
</style>
