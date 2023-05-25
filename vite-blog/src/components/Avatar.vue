<script setup>
import Login from "../views/login/Login.vue";
import {useRouter} from "vue-router";
//
import { reactive, toRefs } from 'vue'
const state = reactive({
    circleUrl:
        'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',

})
const { circleUrl, } = toRefs(state)
//

const router = useRouter()
const toViews = () => {
    router.push({
        path:"/Login"
    });
}

</script>

<template>
    <div>
        <el-avatar :size="'large'" :src="circleUrl" @click="toViews" />
    </div>
</template>

<style lang="scss">

.Avatar{
    width: 100px;
    height: 100px;
  background-color: white;
}
.router-link-active {
  text-decoration: none;
 
}
a {
  text-decoration: none;
  width: 100px;
  background-color: red;
}

</style>
