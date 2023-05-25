<script setup>
import getPost from "../composibles/getPost";
import Spinner from "../components/Spinner.vue";
import { useRoute } from "vue-router";

const route = useRoute();
const { post, load } = getPost(route.params.id);

load();

const props = defineProps({
  id: String,
});
</script>

<template>
  <div class="post" v-if="post">
    <h3>
      {{ post.title }}
    </h3>
    <p class="pre">{{ post.body }}</p>
  </div>
  <div v-else>
    <Spinner />
  </div>
</template>

<style scoped>
.tags a {
  margin-right: 10px;
}
.post {
  max-width: 1200px;

  margin: 0 auto;
}
.post p {
  color: #000000;
  line-height: 1.5em;
  margin-top: 40px;
}
.post h3 {
  display: inline-block;
  position: relative;
  font-size: 26px;
  color: rgb(0, 0, 0);
  margin-bottom: 10px;
  max-width: 400px;
}
.post h3::before {
  content: "";
  display: block;
  width: 100%;
  height: 100%;
  background: #ff0000;
  position: absolute;
  z-index: -1;
  padding-right: 40px;
  left: -30px;
}
.pre {
  white-space: pre-wrap;
}
</style>
