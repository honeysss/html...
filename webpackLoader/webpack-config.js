
var htmlWebpackPlugin = require('html-webpack-plugin');
var path = require('path');

module.exports = {
	entry: path.resolve(__dirname, 'src/app.js'),
	output: {
		path: path.resolve(__dirname, 'dist'),
		filename: 'js/[name].bundle.js'
	},
	module: {
		rules: [
		// js
			{
				test: /\.js$/,
				// exclude: [path.resolve(__dirname, 'node_modules')],
				include: [path.resolve(__dirname, 'src')],
				loader: 'babel-loader',
				options: {
					// 使用最新的esc
					presets: ['env']
				}
			},
			// css
			{
				test: /\.css$/,
				use: [ 
				'style-loader', 
				{
					loader: 'css-loader',
					// options: {
					// 	importLoaders: 1
					// }
				},
				{
					// 这是webpack升级之后的写法
					loader: 'postcss-loader',
					options: {
						plugins: [
						// 我也不知道postcss-import是什么意思 但是如果不引入进来
						// 在css中引入别的需要带浏览器前缀的css文件时不会被postcss解析
							require('postcss-import') (),
							require('autoprefixer') ({
								browsers: ['last 5 versions']
							})
						]
					}
				}
				]
			},
			// less, sscc是一样的 再下载一个scss-loader
			{
				test: /.\less$/,
				use: [
					'style-loader',
					'css-loader',
					{
						// 这是webpack升级之后的写法
						loader: 'postcss-loader',
						options: {
							plugins: [
							// 我也不知道postcss-import是什么意思 但是如果不引入进来
							// 在css中引入别的需要带浏览器前缀的css文件时不会被postcss解析
								require('postcss-import') (),
								require('autoprefixer') ({
									browsers: ['last 5 versions']
								})
							]
						}
					},
					'less-loader'
				]
			},
			// html
			{
				test: /.\html$/,
				use: [ 'html-loader' ]
			},
			// ejs
			{
				test: /\.tpl$/,
				use: [ 'ejs-loader' ]
			},
			// // img 用file-loader
			// {
			// 	test: /\.(png|svg|jpg|gif)$/i,
			// 	use: {
			// 		loader: 'file-loader',
			// 		options: {
			// 			name: 'img/[name]-[hash:5].[ext]'
			// 		}
			// 	}
			// }
			
			// img 用url-loader
			// 用url打包不会生成图片打包的文件 会把文件分别打包到其他文件中 这个时候文件大小反而变得更大
			// 另外，如果图片较多，会发很多http请求，会降低页面性能。这个问题可以通过url-loader解决。
			// url-loader会将引入的图片编码，生成dataURl。
			// 相当于把图片数据翻译成一串字符。再把这串字符打包到文件中，最终只需要引入这个文件就能访问图片了。
			// 当然，如果图片较大，编码会消耗性能。因此url-loader提供了一个limit参数，
			// 小于limit字节的文件会被转为DataURl，大于limit的还会使用file-loader进行copy。
			// 经过http请求传输过来的图片有一个好处就是可以有图片缓存 如果经常需要打开该文件 这样会快一些
			{
				test: /\.(png|jpg|svg|gif)$/i,
				use: [
				// ext是文件后缀
					'url-loader?limit=200000&name=img/[name]-[hash:5]-[ext]',
					// 压缩图片
					'image-webpack-loader'
					// options: {
					// 	// limit的值大于用file-loader打包的大小url-loader才会生效
					// 	limit: 200000
					// }
				]
			}

		]
	},

	plugins: [
	// 定义了html的插件 引入一个html模板 打包出一个html（也可以打包出多个）
		new htmlWebpackPlugin({
			filename: 'index.html',
			template: 'template.html',
			inject: 'body',
			title: 'webpackLoader'
		})
	]
};
