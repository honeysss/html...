<template>
	<header class="main-head">
		<h1 @click="$store.commit('change-show')">WTodo{{$store.state.dialog.show}}</h1>
	</header>
</template>

<style>
	.main-head {
		text-align: center;
		z-index: 10;
	}
	.main-head h1 {
		font-weight: bold;
		color: red;
		font-size: 100px;
		font-family: '幼圆';
		margin: 50px;
	}
</style>