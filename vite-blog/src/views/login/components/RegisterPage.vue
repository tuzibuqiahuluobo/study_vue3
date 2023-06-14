<template>
    <div class="register">
        <div class="register_registerPage" style="width: 100vw;">
            <div>
                <div style="font-size: 25px ;color: black ;font-weight: bold;text-align: center">注册新用户</div>
                <div style="font-size: 14px; color: gray ;text-align: center">注册用户需要填写以下信息</div>
            </div>
            <div style="width: 100vw;display: flex;justify-content: center">
                <div style="width: 50vw">
                    <el-form :model="form" :rules="rules" @validate="onValidate" ref="formRef">
                        <el-form-item prop="username">
                            <el-input v-model="form.username" type="text" :maxlength="8" placeholder="用户名"
                                      size="large">
                                <template #prefix>
                                    <el-icon>
                                        <User/>
                                    </el-icon>
                                </template>
                            </el-input>
                        </el-form-item>
                        <el-form-item prop="password">
                            <el-input v-model="form.password" type="password" :maxlength="16" placeholder="密码"
                                      show-password
                                      size="large">
                                <template #prefix>
                                    <el-icon>
                                        <Lock/>
                                    </el-icon>
                                </template>
                            </el-input>
                        </el-form-item>
                        <el-form-item prop="password_repeat">
                            <el-input v-model="form.password_repeat" type="password" :maxlength="16"
                                      placeholder="重复密码"
                                      show-password
                                      size="large">
                                <template #prefix>
                                    <el-icon>
                                        <Lock/>
                                    </el-icon>
                                </template>
                            </el-input>
                        </el-form-item>
                        <el-form-item prop="email">
                            <el-input v-model="form.email" type="email" placeholder="电子邮件地址" size="large">
                                <template #prefix>
                                    <el-icon>
                                        <Message/>
                                    </el-icon>
                                </template>
                            </el-input>
                        </el-form-item>
                        <el-form-item prop="code">
                            <el-row style="justify-content: space-between;">
                                <el-col :span="17">
                                    <el-input v-model="form.code" type="text" :maxlength="6" placeholder="请输入验证码"
                                              size="large">
                                        <template #prefix>
                                            <el-icon>
                                                <EditPen/>
                                            </el-icon>
                                        </template>
                                    </el-input>
                                </el-col>
                                <el-col :span="5">
                                    <el-button type="success" @click="validateEmail" :disabled="!isEmailValid"
                                               :loding="isSending">
                                        {{ getCode }}
                                    </el-button>
                                </el-col>
                            </el-row>
                        </el-form-item>
                    </el-form>
                </div>
            </div>
            <div style="width: 100vw;display: flex;justify-content: center">
                <div style="margin-top: 20px">
                    <el-button type="success" @click="register()">立即注册</el-button>
                </div>
            </div>
            <div
                    style="margin-top: 20px; font-size: 14px;line-height: 15px;width: 100vw;display: flex;justify-content: center">
                已有账号？
                <el-link type="primary" @click="router.push('/')">立即登录</el-link>
            </div>
        </div>
    </div>
</template>

<script setup>
import {EditPen, Lock, Message, User} from "@element-plus/icons-vue";
import router from "../../../router/index.js";
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {post} from "../../../net/index.js"

const form = reactive({
    username: '',
    password: '',
    password_repeat: '',
    email: '',
    code: ''
})

const validateUsername = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请输入用户名'))
    } else if (!/^[a-zA-Z0-9\u4e00-\u9fa5]+$/.test(value)) {
        callback(new Error('用户名不能包含特殊字符，只能是中文/英文'))
    } else {
        callback()
    }
}

const validatePassword = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请再次输入密码'))
    } else if (value !== form.password) {
        callback(new Error("两次输入的密码不一致！"))
    } else {
        callback()
    }
}


const rules = {
    username: [
        {validator: validateUsername, trigger: ['blur', 'change']},
        {min: 2, max: 8, message: '用户名长度必须在2-8字符之间', trigger: ['blur', 'change']},
    ],
    password: [
        {required: true, message: '请输入密码', trigger: ['blur', 'change']},
        {min: 6, max: 16, message: '密码长度必须在6-16字符之间', trigger: ['blur', 'change']}
    ],
    password_repeat: [
        {validator: validatePassword, trigger: ['blur', 'change']},
    ],
    email: [
        {required: true, message: '请输入电子邮件地址', trigger: ['blur', 'change']},
        {type: 'email', message: '请输入合法的电子邮件地址', trigger: ['blur', 'change'],}
    ],
    code: [
        {required: true, message: '请输入获取到的验证码', trigger: 'blur'},
        {min: 6, max: 8, message: '验证码长度在6-8位，请检查是否输入正确', trigger: ['blur', 'change']}
    ]
}
const formRef = ref()
const isEmailValid = ref(false)

const onValidate = (prop, isValid) => {
    if (prop === 'email') {
        isEmailValid.value = isValid
    }
}

const register = () => {
    formRef.value.validate((isValid) => {
        if (isValid) {
            post('/api/auth/register', {
                username: form.username,
                password: form.password,
                password_repeat: form.password_repeat,
                email: form.email,
                code: form.code
            }, (message) => {
                ElMessage.success(message)
                router.push('/')
            })
        } else {
            ElMessage.warning('请完整填写注册内容')
        }
    })
}

const validateEmail = () => {
    // 按钮进入倒计时，防止用户频繁点击
    const timer = setInterval(() => {
        if (getCode.value === "获取验证码") {
            getCode.value = "59s";
            isEmailValid.value = true;
        } else if (getCode.value === "1s") {
            getCode.value = "获取验证码";
            isEmailValid.value = false;
            clearInterval(timer);
        } else {
            getCode.value = parseInt(getCode.value) - 1 + "s";
        }
    }, 1000);
    post("/api/auth/valid/email", {
        email: form.email
    }, (message) => {
        ElMessage.success(message)
    })
}


// 获取验证码按钮的文字
const getCode = ref("获取验证码")
// 是否正在发送验证码
const isSending = ref(false)
// 发送验证码结束
isSending.value = false;


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