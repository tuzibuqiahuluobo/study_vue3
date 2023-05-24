<script setup>
import Navbar from "./components/Navbar.vue";
import Index from "./views/index/Index.vue";
</script>

<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<style>
body {
  font-family: -apple-system, BlinkMacSystemFont, opensans, Optima,
    "Microsoft Yahei", sans-serif;
  line-height: 1.8;
  font-size: 16px;
  background: #f5f5f5;
  color: #848484;
  font-weight: 300;
  cursor: url("./public/cursor.cur"), default;
  overflow-x: hidden;
  margin: 0;
  height: 100%;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  box-sizing: border-box;
}
</style>
