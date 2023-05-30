<script setup>
import router from "../router/index.js";
//
import { reactive, toRefs } from 'vue'
const state = reactive({
    circleUrl:
        'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',

})
const { circleUrl, } = toRefs(state)
//




</script>

<template>
    <div>
        <el-avatar :size="'large'" :src="circleUrl" @click="router.push('/')" />
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
