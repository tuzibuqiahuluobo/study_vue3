<template>
    <div class="login">
      <div class="login_img">
          <div class="login_loginPage">
              <div class="login_font" style="font-size: 25px ;color: black ;font-weight: bold;text-align: center">登录</div>
              <div class="login_font" style="font-size: 14px; color: gray ;text-align: center">在进入系统之前请先输入用户名和密码进行登录</div>
              <div  class="login_input" style="margin-top: 60px; width: 100vw;display: flex;justify-content: center" >
                <div style="width: 50vw">
                  <el-input type="text" placeholder="用户名/邮箱" size="large">
                      <template #prefix>
                          <el-icon>
                              <User />
                          </el-icon>
                      </template>
                  </el-input>
                  <el-input type="password" style="margin-top: 20px" placeholder="密码" show-password size="large">
                      <template #prefix>
                          <el-icon>
                              <Lock />
                          </el-icon>
                      </template>
                  </el-input>
                </div>
              </div>
              <div style="display: flex;justify-content: center;width: 100vw">
                  <el-row class="login_checkbox" style="margin-top: 10px;margin-bottom: 10px;justify-content: center;width: 100%">
                        <el-col :span="10" style="text-align: center;">
                            <el-checkbox v-model="checked1" label="记住我" size="large"></el-checkbox>
                        </el-col>
                        <el-col :span="10" style="text-align: center;">
                            <el-link>忘记密码</el-link>
                        </el-col>
                  </el-row>
              </div>
              <div class="login_button" >
                  <el-button type="success" plain>登录</el-button>
              </div>
              <el-divider>
                  <span style="color: grey">没有账号</span>
              </el-divider>
              <div class="login_button">
                  <el-button type="warning" plain>注册</el-button>
              </div>
          </div>
      </div>

    </div>
</template>

<script setup>
import {Lock, User} from "@element-plus/icons-vue";



</script>

<style lang="scss">
.login{
    display: flex;
    width: 100%;
    height:100vh;
    justify-content: center;
    overflow: hidden;
    &_img{
        width: 100%;
        height: 100%;
        //background-image: url("../public/bjtx.jpg");
        background-repeat: no-repeat;
        background-color: rgba($color: #ffff, $alpha: 1);
    }
    &_loginPage{
        display: flex;
        justify-content: center;
        flex-direction: column;
      padding-top: 30vh;
      width: 100%;
    }
    &_login_input{

    }
    &_button{
        display: flex;
        justify-content: center;
        margin-top: 30px;
        margin-right: 0;
    }
}
</style>