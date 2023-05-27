
<template>
  <div>
    <el-button @click="logout()" type="danger" plain>退出登录</el-button>
  </div>
</template>

<script setup>

import {get} from "../../../net/index.js";
import {ElMessage} from "element-plus";
import router from "../../../router/index.js";

const logout = () => {
  get('/api/auth/Sign_out',(message)=>{
      ElMessage.success(message)
      router.push('/index')
  })
}


</script>

<style>

</style>