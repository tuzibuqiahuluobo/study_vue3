<template>
    <div class="resetPassword">
        <div class="resetPassword_enterEmailPage" style="width: 100vw;" v-if="active===0">
            <div style="font-size: 25px ;color: black ;font-weight: bold;text-align: center">重置密码</div>
            <div style="font-size: 14px; color: gray ;text-align: center">请输入电子邮箱以重置密码</div>
            <div style="margin-top: 60px; width: 100vw;display: flex;justify-content: center">
                <el-form :model="form" :rules="rules" @validate="onValidate" ref="formRef">
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
                                <el-button type="success" @click="validateEmail" :disabled="!isEmailValid">获取验证码
                                </el-button>
                            </el-col>
                        </el-row>
                    </el-form-item>
                </el-form>
            </div>
            <div style="width: 100vw;display: flex;justify-content: center">
                <div style="margin-top: 20px">
                    <el-button type="success" @click="active = 1">下一步</el-button>
                </div>
            </div>
        </div>
        <div class="resetPassword_resetPasswordPage" style="width: 100vw;" v-if="active===1">
            <div style="font-size: 25px ;color: black ;font-weight: bold;text-align: center">重置密码</div>
            <div style="font-size: 14px; color: gray ;text-align: center">请输入新密码</div>
            <div style="margin-top: 60px; width: 100vw;display: flex;justify-content: center">
                <el-form :model="form" :rules="rules" @validate="onValidate" ref="formRef">
                    <el-form-item prop="password">
                        <el-input v-model="form.password" type="password" :maxlength="16" placeholder="新密码"
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
                        <el-input v-model="form.password_repeat" type="password" :maxlength="16" placeholder="重复密码"
                                  show-password
                                  size="large">
                            <template #prefix>
                                <el-icon>
                                    <Lock/>
                                </el-icon>
                            </template>
                        </el-input>
                    </el-form-item>

                </el-form>
            </div>
            <div style="width: 100vw;display: flex;justify-content: center">
                <div style="margin-top: 20px">
                    <el-button type="success" @click="active = 2">立即重置</el-button>
                </div>
            </div>
        </div>
        <div class="resetPassword_finishPage" style="width: 100vw;" v-if="active===2">
            <div style="font-size: 25px ;color: black ;font-weight: bold;text-align: center;margin-top: 250px">重置密码成功！请重新登录
            </div>
            <div style="width: 100vw;display: flex;justify-content: center">
                <div style="margin-top: 20px">
                    <el-button type="success" @click="router.push('/')">立即登录</el-button>
                </div>
            </div>
        </div>
        <div class="resetPassword_Steps">
            <el-steps :space="200" :active="active" finish-status="success" align-center>
                <el-step title="输入电子邮箱"/>
                <el-step title="重新设定密码"/>
            </el-steps>
        </div>

    </div>
</template>

<script setup>
import {EditPen, Lock, Message} from "@element-plus/icons-vue";
import {reactive, ref} from "vue";
import {post} from "../../../net/index.js";
import {ElMessage} from "element-plus";
import router from "../../../router/index.js";


const formRef = ref()
const isEmailValid = ref(false)
const form = reactive({
    password: '',
    password_repeat: '',
    email: '',
    code: ''
})

const active = ref(0)
const onValidate = (prop, isValid) => {
    if (prop === 'email') {
        isEmailValid.value = isValid
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

const validateEmail = () => {
    post("/api/auth/valid/email", {
        email: form.email
    }, (message) => {
        ElMessage.success(message)
    })
}

const rules = {
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

const resetEmail = () => {
    resetEmail()
}

</script>


<style lang="scss">
.resetPassword {
  position: relative;
  width: 100%;
  height: 100vh;
  overflow: hidden;

  &_enterEmailPage {
    display: flex;
    justify-content: flex-start;
    flex-direction: column;
    padding-top: 30vh;
    width: 100%;
  }

  &_resetPasswordPage {
    display: flex;
    justify-content: flex-start;
    flex-direction: column;
    padding-top: 30vh;
    width: 100%;
  }

  &_finishPagePage {
    display: flex;
    justify-content: flex-start;
    flex-direction: column;
    padding-top: 30vh;
    width: 100%;
  }

  &_Steps {
    padding-top: 150px;
  }
}

.el-steps {
  display: flex;
  justify-content: center;
}
</style>