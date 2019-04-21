const path = require('path')

const config = {
	entry: path.join(__dirname, 'hello.js'),
	output: {
		filename: 'bundle.js',
		path: path.join(__dirname, 'dist')
	},
	module: {
		rules: [{
			test: /\.txt$/,
			use: 'raw-loader'
		}]
	},
	mode: 'production'
};

module.exports = config;