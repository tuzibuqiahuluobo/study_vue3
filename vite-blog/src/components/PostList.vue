<script setup>
import SinglePost from "../components/SinglePost.vue";
import { onMounted, onUnmounted, onUpdated, watchEffect } from "vue";

onMounted(() => {
  // alert("页面渲染之前执行，执行完，页面就出来了");
  //   数据请求
});

onUnmounted(() => {
  // alert("组件注销之前执行，执行完组件就不再页面显示了");
  //better-scroll 销毁掉三方插件实例的一些对象
});

// onUpdated(() => {
//   alert("当组件内的内容发生变化，就会执行这个更新狗子函数");
// });

defineProps({
  posts: Array,
});
</script>

<template>
  <div class="post-list">
    <div v-for="post in posts" :key="post.id">
      <SinglePost :post="post" />
    </div>
  </div>
</template>

<style></style>
