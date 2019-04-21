const path = require('path');
const { VueLoaderPlugin } = require('vue-loader');
const htmlWebpackPlugin = require('html-webpack-plugin');
const webpack = require('webpack');
const isDev = process.env.NODE_ENV === 'development';
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

const config = {
	// 在web上运行
	target: 'web',
	entry: path.resolve(__dirname, 'src/main.js'),
	output: {
		path: path.resolve(__dirname, 'dist'),
		filename: 'js/[name].js'
	},
	module: {
		rules: [
			{
				test: /\.vue$/,
				use: ['vue-loader'],
				include: path.resolve(__dirname, 'src')
			},
			{
				test: /\.jsx$/,
				use: [{
					loader: 'babel-loader',
					options: {
						presets: [
							"env"
						],
						plugins: [
							"transform-vue-jsx"
						]
					}
				}]
			},
			{
				test: /\.html$/,
				use: ['html-loader']
			},
			{
				test: /\.(jpg|svg|jpeg|png)$/,
				use: ['url-loader?limit=2000&name=img/[name]-[hash:5].[ext]']
			},
			{
				test: /\.styl$/,
				use: ['style-loader', 'css-loader', 'stylus-loader']
			}
		]
	},
	resolve: {
    extensions: ['.js', '.vue', '.json'],
    alias: {
      'vue$': 'vue/dist/vue.esm.js'
    }
  },
	plugins: [
		new VueLoaderPlugin(),
		new htmlWebpackPlugin({
			filename: 'index.html',
			template: 'src/index.html',
			title: 'vue-webpack',
			inject: true
		}),
		new htmlWebpackPlugin({
			filename: 'index2.html',
			template: 'src/index2.html',
			title: 'indexTest',
			inject: true
		}),
		new webpack.DefinePlugin({
			'process.env': {
				// 判断一下是生产环境还是开发环境 这里的development，production如果不用单引号引起来
				// 在webpack进行编译的时候会变成process.env.NODE_ENV = development
				NODE_ENV: isDev ? '"development"' : '"production"'
			}
		})
	]
}

// 如果是开发环境
if(isDev) {
	config.module.rules.push(
		{
			test: /\.css$/,
			// use都是数组
			use: [
				'style-loader', 
				'css-loader?importLoader=1',
				{
					loader: 'postcss-loader',
					options: {
						plugins: [
							require('postcss-import') (),
							require('autoprefixer')({
								browers: ['last 5 versions']
							})
						]
					}
				}
			]
		}
	),
	config.devtool = '#cheap-module-eval-source-map',
	// 为开发环境配置
	config.devServer = {
		// 端口号
		port: 8000,
		// IP地址 不使用localhost的原因是这样写在别人的电脑上也可以访问 
		// 也可以通过本机的内网IP进行访问 手机也可以访问
		host: '0.0.0.0',
		// 在开发的过程中如果有错误都显示到网页上(true)
		overlay: {
				erros: true
		},
		hot: true

	},
	config.plugins.push(
		new webpack.HotModuleReplacementPlugin(),
		new webpack.NoEmitOnErrorsPlugin()
	)
}else {
	config.entry = {
		main: path.resolve(__dirname, 'src/main.js'),
		vendor: ['vue']
	}
	config.output.filename = 'js/[name].[chunkhash:8].js',
	config.module.rules.push({
		test: /\.css$/,
			use: [
			// 这个loader不用加引号
			{
				// 用这个loader打包css文件
				loader: MiniCssExtractPlugin.loader,
				options: {
					// 打包的css文件的输出路径
					publicPath: path.resolve(__dirname, 'dist')
				}
			},
			'css-loader',
			{
				loader: 'postcss-loader',
				options: {
					plugins: [
						require('postcss-import') (),
						require('autoprefixer')({
							browers: ['last 5 versions']
						})
					]
				}
			}
		]

	}),
	// 往plugins里push内容的时候不用加花括号
	config.plugins.push(
		new MiniCssExtractPlugin({
			// 打包的css文件的名称
			filename: 'css/style.[contentHash:8].css',
			chunkFilename: 'css/style.[contentHash:8].css'
		})
	),
	// 分离js 单独打包类库文件（vue）
	config.optimization = {
		splitChunks: { 
			cacheGroups: { 
				commons: { 
					name: "vendor", 
					chunks: "initial", 
					minChunks: 2 
				} 
			} 
		}
	}
}






module.exports = config;

