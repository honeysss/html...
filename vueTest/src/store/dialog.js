export default {
	// store中的数据 类似于data
	state: {
		show: false,
		day: 'monday'
	},
	// 相当于一些方法 类似于methods 在模板中利用$store.commit('')来出发对应的方法
	mutations: {
		change_show(state) {
			// 如果为true 等于false 如果为false 等于true
			state.show = state.show ? false : true;
		},
		change_day(state) {
			state.day = state.day === 'monday' ? 'tuesday' : 'monday';
		}
		
		// 箭头函数
		// change_show: state => state.show = state.show ? false : true; 
		// change_day: state => state.day = state.day === 'mondy' ? 'tuesday' : 'monday';	
	},
	// 如果mutations中有多个方法 想在模板中一次触发多个方法 可以使用actions
	// 方法中的参数context相当于$store,可以自己定义触发mutations中的什么方法
	// 在模板中这样使用:  $store.dispatch('方法名')
	actions: {
		change_show(context) {
			context.commit('change_show');
			context.commit('change_day');
		}
	},
	// gettters方法相当于vue中的computed 都是计算属性的方法
	// 如果模板中需要修改store中的值 用computed也可以修改，但是只能修改一个组件中的值
	// 如果要修改很多组件，则可以利用getters方法统一修改
	// 使用的时候：$store.getters.not_show(方法名)
	getters: {
		not_show(state) {
			return !state.show;
		}
	}
}