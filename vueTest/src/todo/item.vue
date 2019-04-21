<template>
	<div :class="['todo-item', todo.completed ? 'completed' : '']">
		<input type="checkbox"
		class="toggle"
		v-model="todo.completed" 
		>
		<label>
			{{todo.content}}
		</label>
		<img src="../../src/img/叉.png" class="destroy"
		@click="deleteTodo()">
	</div>
</template>

<script>
	export default {
		props: {
			todo: {
				type: Object,
				required: true
			}
		},
		methods: {
			deleteTodo() {
				// 在方法里使用store 要加上this
				this.$store.state.show = !this.$store.state.show;
				this.$emit('del')
			}
		}
	}
</script>

<style>
	.todo-item {
		width: 100%;
		height: 35px;
		border-bottom: 1px solid #B6B1B1;
		font-size: 20px;
		color: #646060;
		margin-top: 10px;
	}
	.completed {
		text-decoration: line-through;
		font-style: italic !important;
		color: #CCC6C6;
	}
	.toggle {
		width: 20px;
		height: 20px;
		margin-right: 20px;
		cursor: pointer;
		border: none;
		vertical-align: -3px;
		margin-left: 10px;
		border-radius: 50%;
		opacity: 0.3;
	}
	.destroy {
		float: right;
		cursor: pointer;
		margin-right: 20px;
		border: none;
		width: 20px;
		height: 20px;
		opacity: 0.5;
	}
	.destroy:hover {
		opacity: 1;
	}
</style>
