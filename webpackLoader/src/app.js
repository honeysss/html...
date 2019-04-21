import Layer from './components/layer/layer.js';
import './components/css/common.css';

window.onload = function () {
	const App = function() {
		var dom = document.getElementById('app');
		var layer = new Layer();

		// 因为此时的layer返回的是一个函数
		// layer.tpl等于是调用函数
		// 在调用函数的时候可以为函数传参
		// 这里传的参数都放进了最开始的也是就layer.tpl文件中
		dom.innerHTML = layer.tpl ({
			name: 'honey',
			arr: ['小猪', '小狗', '小猫']
		});
	};

App();

};

