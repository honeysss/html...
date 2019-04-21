import Vue from 'vue';
import vuex from 'vuex';
Vue.use(vuex);

var store = new vuex.Store({
	state: {
		show: false
	}
})

// 把store向外导出去
export default store;