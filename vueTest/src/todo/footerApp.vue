<template>
	<div id="footer">
		<span @click="$store.commit('change_day')">Written by wcl{{$store.state.dialog.day}}</span>
	</div>
</template>



<style>
#footer {
	text-align: center;
	margin-bottom: 10px;
	margin-top: 30%;
	z-index: 10;
}

#footer span {
	font-size: 20px;
	color: red;
	font-weight: bold;
}
</style>