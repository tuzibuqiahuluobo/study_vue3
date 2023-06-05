import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import 'element-plus/dist/index.css'
import axios from 'axios';

import VideoPlayer from 'vue-video-player'
require('vue-video-player/node_modules/video.js/dist/video-js.css')
require('vue-video-player/src/custom-theme.css')


const app = createApp(App);

axios.defaults.baseURL = 'http://localhost:8080'

app.use(router);
app.use(VideoPlayer)
app.mount('#app')
