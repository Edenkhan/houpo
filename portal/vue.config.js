module.exports = {
  pages: {
    index: {
      title: '首页',
      entry: 'src/pages/index.js',
      filename: 'index.html'
    },
    login: {
      title: '登录',
      entry: 'src/pages/user/login.js',
      filename: 'user/login.html'
    },
    verify: {
      title: '登录验证',
      entry: 'src/pages/user/verify.js',
      filename: 'user/verify.html'
    },
    home: {
      title: '个人中心',
      entry: 'src/pages/user/home.js',
      filename: 'user/home.html'
    },
    profile:{
      title: '个人资料',
      entry: 'src/pages/user/profile.js',
      filename: 'user/profile.html'
    },
    manger:{
      title: '报名管理',
      entry: 'src/pages/user/manger.js',
      filename: 'user/manger.html'
    },
    result:{
      title: '测评报告',
      entry: 'src/pages/user/result.js',
      filename: 'user/result.html'
    },
    assess:{
      title: '测评报告',
      entry: 'src/pages/user/assess.js',
      filename: 'user/assess.html'
    },
    share:{
      title: '测评分享',
      entry: 'src/pages/user/share.js',
      filename: 'user/share.html'
    },
    about:{
      title: '关于我们',
      entry: 'src/pages/user/about.js',
      filename: 'user/about.html'
    },
    update:{
      title: '厚朴人才·修改资料',
      entry: 'src/pages/user/update.js',
      filename: 'user/update.html'
    },
    tip: {
      title: '提示',
      entry: 'src/pages/tip.js',
      filename: 'tip.html'
    },
    workplace:{
      title:'职场百分百',
      entry:'src/pages/workplace.js',
      filename:'workplace.html'
    },
    intro: {
      title: '简介',
      entry: 'src/pages/intro.js',
      filename: 'intro.html'
    },
    employment: {
      title: '就业直通车',
      entry: 'src/pages/employment.js',
      filename: 'employment.html'
    },
    activity: {
      title: '活动',
      entry: 'src/pages/activity/activity.js',
      filename: 'activity.html'
    },
    article: {
      title: '文章',
      entry: 'src/pages/article/article.js',
      filename: 'article.html'
    },
    onlyThirty: {
      title: '测评报告',
      entry: 'src/pages/onlyThirty/index.js',
      filename: 'onlyThirty/index.html'
    },

  },
  css: {
    loaderOptions: {
      sass: {
        prependData: `@import "@/scss/_variables.scss"; @import "@/scss/main.scss";`
      },
    }
  },
  productionSourceMap: false,
  devServer: {
    port: 8013,
    disableHostCheck: true,
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:8081',
        changeOrigin: false,
        pathRewrite: {'^/api': ''}
      },
      '/files': {
        target: 'http://127.0.0.1:8081',
        changeOrigin: false
      }
    }
  },
  // configureWebpack:{
	// 	resolve:{
	// 		alias:{
	// 			'@':'src',
	// 			'assets':'@/assets'

	// 		}
	// 	}
	// }
};
