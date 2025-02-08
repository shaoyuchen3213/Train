import { createApp } from 'vue'
import Antd from 'ant-design-vue';
import App from './App.vue'
import router from './router'
import store from './store'
import 'ant-design-vue/dist/reset.css';
import * as Icons from '@ant-design/icons-vue'
import axios from 'axios';


const app = createApp(App)
app.use(Antd).use(store).use(router).mount('#app')

const icons = Icons;
for( const i in icons) {
    app.component(i, icons[i]);
}


/**
 * axios Interceptor
 */

axios.interceptors.request.use(function (config) {
    console.log('Parameter', config);
    return config;
}, error => {
    return Promise.reject(error);
});

axios.interceptors.response.use(function (response) {
    console.log('Response result', response);
    return response;
}, error=> {
    console.log('Error', error);
    return Promise.reject(error);
})