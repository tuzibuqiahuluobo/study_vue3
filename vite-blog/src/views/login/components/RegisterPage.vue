<template>
    <div class="register">
        <div class="register_registerPage" style="width: 100vw;">
            <div>
                <div style="font-size: 25px ;color: black ;font-weight: bold;text-align: center">注册新用户</div>
                <div style="font-size: 14px; color: gray ;text-align: center">注册用户需要填写以下信息</div>
            </div>
            <div style="width: 100vw;display: flex;justify-content: center">
                <div style="width: 50vw">
                    <el-input v-model="form.username" type="text" placeholder="用户名" size="large">
                        <template #prefix>
                            <el-icon>
                                <User/>
                            </el-icon>
                        </template>
                    </el-input>
                    <el-input v-model="form.password" type="password" style="margin-top: 20px" placeholder="密码" show-password size="large">
                        <template #prefix>
                            <el-icon>
                                <Lock/>
                            </el-icon>
                        </template>
                    </el-input>
                    <el-input v-model="form.password_repeat" type="password" style="margin-top: 20px" placeholder="重复密码" show-password
                              size="large">
                        <template #prefix>
                            <el-icon>
                                <Lock/>
                            </el-icon>
                        </template>
                    </el-input>
                    <el-input v-model="form.email" type="email" style="margin-top: 20px" placeholder="电子邮件地址">
                        <template #prefix>
                            <el-icon>
                                <Message/>
                            </el-icon>
                        </template>
                    </el-input>
                </div>
            </div>
            <div style="width: 100vw;display: flex;justify-content: center">
                <div style="width: 50vw;margin-top: 20px">
                    <el-row style="justify-content: space-between;">
                        <el-col :span="17">
                            <el-input v-model="form.code" type="text" placeholder="请输入验证码">
                                <template #prefix>
                                    <el-icon>
                                        <EditPen/>
                                    </el-icon>
                                </template>
                            </el-input>
                        </el-col>
                        <el-col :span="5">
                            <el-button type="success">获取验证码</el-button>
                        </el-col>
                    </el-row>
                </div>
            </div>
            <div style="width: 100vw;display: flex;justify-content: center">
                <div style="margin-top: 20px">
                    <el-button>立即注册</el-button>
                </div>
            </div>
            <div style="margin-top: 20px; font-size: 14px;line-height: 15px;width: 100vw;display: flex;justify-content: center">
                已有账号？
                <el-link type="primary" @click="router.push('/')">立即登录</el-link>
            </div>
        </div>
    </div>
</template>

<script setup>
import {EditPen, Lock, Message, User} from "@element-plus/icons-vue";
import router from "../../../router/index.js";
import {reactive} from "vue";

const form = reactive({
    username:'',
    password:'',
    password_repeat:'',
    email:'',
    code:''
})
  

</script>

<style lang="scss">
.register {
  width: 100%;
  height: 100vh;
  overflow: hidden;

  &_registerPage {
    display: flex;
    justify-content: flex-start;
    flex-direction: column;
    padding-top: 30vh;
    width: 100%;
  }
}
</style>