<script setup>
import Login from "../views/index/components/Login.vue";
</script>

<template>
  <div class="Avatar">
        <router-link to="/Login">头像</router-link>
  </div>
</template>

<style lang="scss">
.Avatar{
    width: 300px;
    height: 100px;
  background-color: white;
}
.router-link-active {
  text-decoration: none;
  width: 100px;
  background-color: blue;
}
a {
  text-decoration: none;
  width: 100px;
  background-color: red;
}

</style>
