import Vue from 'vue';
// 因为vue模板的父元素如果想传递给子元素值，要先在父元素绑定，子元素再通过props接收，才可以在子元素中使用
// 如果子元素想传递给父元素值，要通过绑定事件，emit到父元素，父元素再通过监听事件，才能获取到子元素传递过来的值
// 这样是一件很麻烦的事情，所以有了vuex
// vuex是可以自定义数据，方法，在全局的vue组件中都可以使用或修改。
import vuex from 'vuex';
Vue.use(vuex);

import dialog_store from './dialog.js';

export default new vuex.Store({
	// 引入别的store模板
	// 成为特定的组件
	// dialog中的内容在引用时应该这样写: $store.state.dialog.show
	
	modules: {
		dialog: dialog_store
		// 如果再需要别的组件
		// others: others...
	}
})