<template>
	<div id="root2">
		<input type="text" v-model="dialog.show"/>
		<div>这是show  {{dialog.show}}</div>
		<div>这是day  {{dialog.day}}</div>
		<div>这是getters  {{oppsitShow}}</div>
		<button @click="change_show">改变show</button>
		<button @click="change_day">改变day</button>
		<button @click="change_all">改变所有</button>
	</div>
</template>

<script>
	import {mapState, mapActions, mapMutations, mapGetters} from 'vuex';

	export default {
		// 使用计算方法 此处的dialog返回的是this.$store.state.dialog
		// 所以在模板中可以直接调用dialog中的数据
		
		computed: {
			// dialog() {
			// 	return this.$store.state.dialog;
			// }
				
			// 使用mapState 和上面的computed方法的效果一样
			// 放在computed里面
			// 将 `this.dialog` 映射为 `this.$store.state.dialog`
			...mapState({
				dialog(state) {
					return state.dialog;
				}
			}),
			
			// 将this.oppsitShow 映射为 this.$store.getters.not_show
			...mapGetters({
				oppsitShow: 'not_show'
			})
			
			// ...mapState({
			// 	dialog: state => state.dialog;
			// })
		},
		// 想对store里的数据进行操作，也可以通过再methods里设置相应的方法
		// 等于是把store里的mutations和actions封装了一下
		methods: {
			// change_show() {
			// 	this.$store.commit('change_show');
			// },
			// change_day() {
			// 	this.$store.commit('change_day');
			// },
			
			...mapMutations([
					'change_show',  // 将 `this.change_show()` 映射为 `this.$store.commit('change_show')`
					'change_day'   // 将 `this.change_day()` 映射为 `this.$store.commit('change_day')`
				]),
			
			
			// 将 `this.change_all()` 映射为 `this.$store.dispatch('change_show')`
			// mapActions方法要放到methods中
			...mapActions({
				change_all: 'change_show'
			}),

			// change_all() {
			// 	this.$store.dispatch('change_show');
			// }


		}
		

	}
</script>