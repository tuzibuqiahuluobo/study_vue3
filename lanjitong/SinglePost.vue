<script setup>
import { computed } from "vue";

const props = defineProps({
  post: Object,
});

const snippet = computed(() => {
  return props.post.body.substring(0, 100) + "...";
});

const bgColor = computed(() => {
  const r = Math.floor(Math.random() * 100);
  const g = Math.floor(Math.random() * 159);
  const b = Math.floor(Math.random() * 100);

  return `rgb(${r}, ${g}, ${b})`;
});
</script>

<template>
  <div
    class="post"
    :style="{
      backgroundColor: bgColor,
      backdropFilter: 'blur(1px)',
      opacity: 0.7,
    }"
  >
    <router-link :to="{ name: 'Details', params: { id: post.id } }">
      <h3>
        {{ post.title }}
      </h3>
    </router-link>
    <p>{{ snippet }}</p>
    <span v-for="tag in post.tags" :key="tag">#{{ tag }}&nbsp;&nbsp;</span>
  </div>
</template>

<style scoped>
.post {
  margin: 0 40px 15px;
  padding-bottom: 30px;
  padding-left: 50px;
  border-radius: 4px 4px 4px 4px;
  box-shadow: 10px 10px 5px bgColor;
}
.post h3 {
  display: inline-block;
  position: relative;
  font-size: 26px;
  color: white;
  margin-bottom: 10px;
  max-width: 400px;
}
.post h3::before {
  content: "";
  display: block;
  background: #ff8800;
  width: 100%;
  height: 100%;
  position: absolute;
  z-index: -1;
  padding-right: 40px;
  border-radius: 4px 4px 4px 4px;
  left: -30px;
}
</style>
