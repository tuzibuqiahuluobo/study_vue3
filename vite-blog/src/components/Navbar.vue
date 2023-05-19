<script setup></script>

<template>
  <header>
    <router-link to="/">首页</router-link>
    <router-link to="/create">新建</router-link>
  </header>
</template>

<style scoped>
header {
  align-items: center;
  margin: 0 auto;
  padding: 15px;
  padding-top: 20px;
  margin-top: 20px;
  background-color: rgba(255, 255, 255, 0.2);
}
header h1 {
  color: #ffffff;
  font-size: 48px;
}
header a {
  color: #bbb;
  text-decoration: none;
  margin-left: 20px;
}
header a.router-link-active {
  color: #444;
  font-weight: bold;
}
</style>
