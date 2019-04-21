var htmlWebpackPlugin = require('html-webpack-plugin');

const path = require('path');

module.exports = {
	entry: {
			main: path.join(__dirname, 'src/script/main.js'),
			main1: path.join(__dirname, 'src/script/main1.js'),
			main2: path.join(__dirname, 'src/script/main2.js'),
			main3: path.join(__dirname, 'src/script/main3.js'),
		},
	output: {
		path: path.join(__dirname, 'dist'),
		filename: 'js/[name].js',
		// 如果有线上需求 可以利用这种方式
		publicPath: 'http://cdn.com/'
	},
	plugins: [
		new htmlWebpackPlugin({
			filename: 'main1.html',
			template: 'index.html',
			inject: false,
			title: 'this is main1',
			date: new Date(),
			// 属性也可以自定义
			// happy: 'yes'
			minify: {
				// 删除注释 删除空格
				removeComments: true
				// collapseWhitespace: true
			},
			chunks: ['main', 'main1']
		}),

		new htmlWebpackPlugin({
			filename: 'main2.html',
			template: 'index.html',
			inject: false,
			title: 'this is main2',
			date: new Date(),
			// 属性也可以自定义
			// happy: 'yes'
			minify: {
				// 删除注释 删除空格
				removeComments: true
				// collapseWhitespace: true
			},
			chunks: ['main', 'main2']
		}),
		new htmlWebpackPlugin({
			filename: 'main3.html',
			template: 'index.html',
			// 设置true或false的时候不要加引号
			inject: false,
			title: 'this is main3',
			date: new Date(),
			// 属性也可以自定义
			// happy: 'yes'
			minify: {
				// 删除注释 删除空格
				removeComments: true
				// collapseWhitespace: true
			},
			chunks: ['main', 'main3']
		})
	]
};
