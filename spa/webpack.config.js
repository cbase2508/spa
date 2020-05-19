var path = require('path');
const { NpmAutoInstallWebpackPlugin } = require("npm-auto-install-webpack-plugin");


module.exports = {
    entry: {
    	
    	app: ['babel-polyfill', './src/main/js/app.js']
    },
    devtool: 'source-map',
    cache: true,
    output: {
    	path: __dirname,
        filename: './src/main/webapp/js/build/[name]-bundle.js'
    },
    module : {
        loaders : [
          {
            test : /\.jsx?/,
            loader : 'babel-loader',
            exclude: /node_modules/,  // <------ I have forgot it and got "Cannot read property 'crypto' of undefined"
            query: { presets: [ 'env','es2015','stage-0','react' ] }
          },
          { test: /\.css$/, loader: "style-loader!css-loader" },
        ]
     },
     plugins: [
               new NpmAutoInstallWebpackPlugin(true),
             ],
};
