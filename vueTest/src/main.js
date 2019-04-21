// 整个项目的入口文件

import Vue from 'vue';
import App from './app.vue';
import './css/global.css';
// import store from './store/store.js';
import store from './store/module.js';

// 第二个页面的模板
import Test from './test.vue';

new Vue({
  el: "#root",
  components: {App},
  template: '<App/>',
  store
});

// 可以实现多个页面
// 每个页面都可以使用store里的值
// 如果想让不同页面之间的数据通信 可以采用vuex
new Vue({
	el: "#root2",
	components: {Test},
	template: '<Test/>',
	store
})

