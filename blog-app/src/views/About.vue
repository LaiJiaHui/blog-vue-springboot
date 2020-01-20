<template>
  <div v-title :data-title="title">
    <el-container class="me-area">
      <el-main class="me-main">
        <div class="me-view-content">
          <pre>
     <h1> 博客简介 | Blog：</h1>
      程 序：基于SpringBoot+Vue开发的个人博客。

      域 名：www.linguochao.com。

      服务器：阿里云轻量级应用服务器(2G)。

      备案号：粤ICP备19146982号。

    <h1> 个人简介 | Resume：</h1>
      人物：狗蛋

      性别：男

      年龄：93

      身高：< 2m

      体重：> 65kg

      现居：中国·广东·广州

      职业：Java开发工程师

    <h1> 搭建博客目的 | Goal：</h1>
         计算机、互联网这一类的东西，很多很庞杂，要想全部记住是不可能的事。所以我就使用有道云记录
     自己的笔记，方便自己什么时候遗忘了，再拿起来看看，这是最开始的目的。

         随着接触互联网的东西越来越多，分享这个词在我的身边越来越多的被提及，CSDN、博客园等等博客
     网站出现在我的眼前，我开始觉得，自己写的东西，如果能够实实在在的帮助到别人，那才是我记录的意义。
     所以我一边在自己的小本本上记录，一边开始注册博客网站的账号，开始在网络上写博客。

         为什么开始做自己的网站，我记得有一天很晚了，我发现网络上关于某一块的知识很少，并不全面，
     我从下午一直写到了晚上，废寝忘食，晚上十点左右，我写完了，将博客发出去的时候，心里参数一口气。
     于是我开始把博客链接分享给别人，以为会对别人有帮助，结果发现404，我那时才知道原来CSDN的博客，
     竟然是要审核的，与此同时，CSDN网站的广告越来越多。

         我不认为个人博客网站需要多漂亮美丽，“内涵”很重要，如果装饰的很花哨，而内容平平一般，
     我觉得博客分享的意义便不复存在。

   <h1> 技术方向 | Technology：</h1>
      掌握 SpringMVC、Spring、MyBatis、SpringData、SpringBoot等主流框架技术；

      掌握 Dubbon、Zookeeper、Eureka、Feign、Hystrix、Zuul、CloudConfig、CloudBus等微服务技术

      掌握RabbitMQ、FastDFS、SpringTask、POI、FreeMarker、阿里短信、微信支付等解决方案；

      掌握Html、CSS、JavaScript、JQuery、AngurlaJS、VueJS , ES6 等前端技术；

      掌握Shiro、JWT、Spring-Security、CAS、MD5、Base64等安全认证技术；

      掌握MySql、Redis、MongoDB、Solr 、ElasticSearch等数据处理技术；

      掌握IDEA、Git、Maven、Nginx、Docker、PostMan等工具；
             </pre>
        </div>

      </el-main>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: 'Log',
    data() {
      return {}
    },
    computed: {
      title() {
        return '日志 - Gou Dan'
      }
    }
  }
</script>

<style scoped>
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

  h1 {
    font-size: 18px;
    color: #06F;
  }

</style>
