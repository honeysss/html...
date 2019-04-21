<template>
	<div id="app">
		<Header></Header>
		<Todo></Todo>
		<Footer></Footer>
		<div class="cover" @click="$store.dispatch('change_show')"></div>
		<h1 v-text="$store.getters.not_show"></span>
	</div>
</template>



<script>
	import Header from './todo/headApp.vue';
	import Footer from './todo/footerApp.vue';
	import Todo from './todo/main.vue';


	export default {
			components: {
				Header,
				Footer,
				Todo
			}
		}
</script>

<style>
	#app {
		color: pink;
	}
	.cover {
		width: 100%;
		height: 100%;
		background-color: #DDD9D9;
		opacity: 0.7;
		position: absolute;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		z-index: 0;
	}
</style>