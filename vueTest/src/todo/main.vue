<template>
	<div id="real-app">
		<input class="add-input" 
		type="text" name="todo" 
		:placeholder="content"
		@keyup.enter="addTodo" 
		/>
		<!-- 父元素把todo的值传给子元素 -->
		<!-- 在这里获取到每一个todo 和每一个todo的index值 -->
		<!-- 在子元素要删除该todo的时候 父元素只需要删除index为该值的todo就可以了 -->
		<Item :todo="todo"
		v-for="(todo, index) in filterTodos"
		:key="todo.id"
		@del="deleteItem(index)"
		></Item>
		<Tabs :filter="filter" :todos="todos"
		@toggle="toggleFilter"
		@clear="clearAllCompleted"
		></Tabs>
		<!-- 从子元素接收到的参数不用在监听的时候声明 在函数实现的时候用就可以了 -->
	</div>
</template>

<script>
	
	import Item from './item.vue';
	import Tabs from './tabs.vue';

	export default {
		computed: {
			filterTodos() {
				// 如果点击的是all  则返回todos
				if(this.filter === 'all') {
					return this.todos
				}
				// 判断当前点击的是否为已完成 如果是 则为ture
				// 假设现在点击的是active 则completed为false
				const completed = this.filter === 'finished';
				// 筛选出当前todos.completed的值的为true或false的值
				// 现在筛选出来的就是todo里completed为false的所有项
				return this.todos.filter(todo => completed === todo.completed);
			}
		},
		// 声明从其他地方引过来的组件 引用后可以直接使用
		components: {
			Item,
			Tabs,
		},
		data() {
			return {
				id: 0,
				todos: [],
				content: '接下去要做什么?',
				filter: ''
			}
		},
		methods: {
			addTodo(e) {
				// unshift是从数组的第一个位置插入元素
				// 这里
				if(e.target.value.trim() !== '') {
					this.todos.unshift({
						id: this.id++,
						completed: false,
						content: e.target.value.trim()
					});
					e.target.value = '';
				}
				// 可以将placeholder里的内容清空
				// this.content = '';
			},
			deleteItem(index) {
				this.todos.splice(index, 1);
				// this.todos.splice(this.todos.findIndex(todo => todo.id === id), 1);
			},
			toggleFilter(state) {
				this.filter = state;
			},
			clearAllCompleted() {
				// 让this.todos重新赋值  把当前todos里的completed为false的也就是还未完成的过滤出来
				this.todos = this.todos.filter(todo => !todo.completed);
			}
		}
	}
</script>

<style>
#real-app {
	display: block;
	width: 600px;
	height: auto;
	background-color: white;
	position: absolute;
	left: 50%;
	margin-left: -300px;
	z-index: 2;

}
#real-app .add-input {
	padding-left: 5px;
	width: 70%;
	margin-left: 15%;
	height: 50px;
	box-shadow: 0 0 2px #B6B1B1;
	text-indent: 4px;
	font-size: 30px;
	margin-bottom: 10px;
	border: none;
	margin-top: 5px;
	color: #706F6F;
}

</style>