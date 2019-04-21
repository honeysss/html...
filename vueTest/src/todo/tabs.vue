<template>
	<div class="helper">
		<span class="left">{{unFinished}} items left</span>


		<span class="tabs">
			<span
			v-for="state in states"
			:key="state"
			:class="['state', filter === state ? 'actived' : '' ]"
			@click="toggleFilter(state)"
			>
				{{state}}
			</span>
		</span>

		<span class="clear" @click="clearAllCompleted">Clear Completed</span>
	</div>
</template>

<script>
	export default {
		computed: {
			unFinished() {
				return this.todos.filter(todo => !todo.completed).length;
			}
		},
		props: {
			filter: {
				type: String,
				requred: true
			},
			todos: {
				type: Array,
				required: true
			}
		},
		data() {
			return {
				states: ['all', 'active', 'finished']
			}
		},
		methods: {
			toggleFilter(state) {
				// alert(state);
				this.$emit('toggle', state);
			},
			clearAllCompleted() {
				this.$emit('clear');
			}
		}
	}
</script>

<style>
	.helper {
		width: 100%;
		height: 40px;
		font-size: 16px;
		color: #575353;
		margin-top: 20px;
	}
	.left {
		float: left;
		margin-left: 10px;
		margin-right: 90px;
	}
	.tabs {
		
	}
	.state {
		padding: 5px;
		border: 1px solid #D0CBCB;
		border-radius: 5px;
		margin-right: 5px;
		cursor: pointer;
	}
	.state:hover,
	.actived {
		color: red;
		border-color: red;
	}
	.clear {
		float: right;
		margin-right: 10px;
		cursor: pointer;
	}
	.clear:hover {
		color: red;
	}
</style>