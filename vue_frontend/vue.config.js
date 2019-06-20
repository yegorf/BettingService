const BASE_URL = '/';

module.exports = {
    publicPath: BASE_URL,
    assetsDir: 'static',
    productionSourceMap: false,
    devServer: {
        compress: true,
        open: true,
        host: 'localhost',
        port: 8081,
        proxy: {
            '': {
                target: 'http://localhost:8080',
            },
        },
    },
};