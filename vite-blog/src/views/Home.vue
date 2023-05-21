<script setup>
import PostList from "../components/PostList.vue";
import getPosts from "../composibles/getPosts";
import Spinner from "../components/Spinner.vue";
import TagCloud from "../components/TagCloud.vue";


const { posts, load } = getPosts();
load();
</script>

<template>
  <div v-if="posts.length" class="layout">
    <PostList :posts="posts" />
    <TagCloud :posts="posts" />
  </div>
  <div v-else>
    <Spinner />
  </div>
</template>

<style scoped>
.layout {
  display: grid;
  grid-template-columns: 3fr 1fr;
  gap: 20px;
}
</style>

<script></script>
