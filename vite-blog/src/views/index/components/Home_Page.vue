<script setup>
import Avatar from "../../../components/Avatar.vue";
</script>

<template>
  <div>
    <div class="header">
      <div class="header_nav" style="color: #fff">
        <el-menu
          class="el-menu-demo"
          mode="horizontal"
        >
          <el-menu-item index="1">Processing Center</el-menu-item>
          <el-sub-menu index="2">
            <template #title>nav1</template>
            <el-menu-item index="2-1">item one</el-menu-item>
            <el-menu-item index="2-2">item two</el-menu-item>
            <el-menu-item index="2-3">item three</el-menu-item>
            <el-sub-menu index="2-4">
              <template #title>item four</template>
              <el-menu-item index="2-4-1">item one</el-menu-item>
              <el-menu-item index="2-4-2">item two</el-menu-item>
              <el-menu-item index="2-4-3">item three</el-menu-item>
            </el-sub-menu>
          </el-sub-menu>
          <el-menu-item index="3" disabled>Info</el-menu-item>
          <el-menu-item index="4">Orders</el-menu-item>
        </el-menu>
      </div>
      <div class="header_title">
        <div
          style="
            color: #fff;
            font-weight: bold;
            font-size: 48px;
            text-shadow: 2px 2px 2px #666;
          "
        >
          猫's Blog
        </div>
        <div
          style="
            color: #fff;
            font-weight: bold;
            letter-spacing: 1px;
            font-size: 25px;
          "
        >
          I'm very vegetable and very poor.
        </div>
      </div>
    </div>
    <div class="content">
      <div class="content_left">
        <div class="content1">Content1</div>
      </div>
      <div class="content_right">
        <div class="content_util" style="height: 180px">
          <router-view>
            <Avatar />
          </router-view>
        </div>
        <div class="content_util" style="height: 270px">comments</div>
        <div class="content_util" style="height: 180px">donation</div>
        <div class="content_util" style="height: 220px">recommend</div>
      </div>
    </div>

    <div class="footer">
      <div>
        <p>Ant Design ©2018 Created by Ant UED</p>
      </div>
    </div>
  </div>
</template>

<style lang="scss">
.header {
  position: relative;
  width: 100%;
  height: 32vh;
  background-image: url("../../../public/bk6.jpg");
  &_nav {
    display: flex;
    position: fixed;
    top: 0;
    width: 100%;
    height: 4vh;
    z-index: 10;
    background-color: rgba($color: #000000, $alpha: 0.4);
  }
  &_title {
    position: absolute;
    text-align: center;
    left: 50%;
    top: 50%;
    translate: -50% -50%;
    transition: 0.5s;
  }
  &_navItem {
    padding-left: 15px;
    padding-top: 5px;
    padding-right: 20px;
    border: red solid 1px;
  }
}

.content {
  display: flex;
  justify-content: center;
  width: 100%;
  margin: 20px 0;

  &_left {
    display: flex;
    flex-direction: column;
    margin-right: 30px;
    width: 40%;
    height: 1000px;
    background-color: red;
  }

  &_right {
    position: sticky;
    top: 7%;
    width: 20%;
    height: 910px;
    background-color: yellow;
  }

  &_util {
    margin-bottom: 20px;
    background-color: wheat;
    display: flex;
    justify-content: center;
    &:last-child {
      margin-bottom: 0;
    }
  }
}

.footer {
  width: 100%;
  height: 18vh;
  background-color: aqua;
  text-align: center;
}
</style>
