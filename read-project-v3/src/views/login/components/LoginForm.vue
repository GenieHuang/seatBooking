<template>
	<el-form ref="loginFormRef" :model="loginForm" :rules="loginRules" size="large">
		<el-form-item prop="username">
			<el-input v-model="loginForm.username" placeholder="请输入用户名">
				<template #prefix>
					<el-icon class="el-input__icon"><user /></el-icon>
				</template>
			</el-input>
		</el-form-item>
		<el-form-item prop="password">
			<el-input type="password" v-model="loginForm.password" placeholder="请输入密码" show-password autocomplete="new-password">
				<template #prefix>
					<el-icon class="el-input__icon"><lock /></el-icon>
				</template>
			</el-input>
		</el-form-item>
	</el-form>
	<div class="login-btn">
		<el-button :icon="CircleClose" round @click="resetForm(loginFormRef)" size="large">重置</el-button>
		<el-button :icon="UserFilled" round @click="login(loginFormRef)" size="large" type="primary" :loading="loading">
			登录
		</el-button>
	</div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from "vue";
import { useRouter } from "vue-router";
import { Login } from "@/api/interface";
import { ElNotification } from "element-plus";
import { loginApi } from "@/api/modules/login";
import { GlobalStore } from "@/stores";
import { AuthStore } from "@/stores/modules/auth";
import { TabsStore } from "@/stores/modules/tabs";
import { KeepAliveStore } from "@/stores/modules/keepAlive";
import { getTimeState } from "@/utils/util";
import { HOME_URL, FRONT_HOME_URL } from "@/config/config";
import { CircleClose, UserFilled } from "@element-plus/icons-vue";
import type { ElForm } from "element-plus";
import { getUserInfoAPI } from "@/api/modules/userModule";
import { houtaiMenuList, qiantaiMenuList, qiantaiMenuListJZG } from "@/utils/serviceDict";

const router = useRouter();
const tabsStore = TabsStore();
const keepAlive = KeepAliveStore();
const globalStore = GlobalStore();
const authStore = AuthStore();

// 定义 formRef（校验规则）
type FormInstance = InstanceType<typeof ElForm>;
const loginFormRef = ref<FormInstance>();
const loginRules = reactive({
	username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
	password: [{ required: true, message: "请输入密码", trigger: "blur" }]
});

const loading = ref(false);
const loginForm = reactive<Login.ReqLoginForm>({ username: "", password: "" });
const login = (formEl: FormInstance | undefined) => {
	if (!formEl) return;
	formEl.validate(async valid => {
		if (!valid) return;
		loading.value = true;
		try {
			// 执行登录接口
			const { data } = await loginApi({ ...loginForm });
			globalStore.setLoginInfo(data);
			const res = await getUserInfoAPI();
			const { userInfo, ...newParams } = res.data;
			globalStore.setUserInfo({
				...userInfo,
				...newParams
			});
			// 清空 tabs、keepAlive 保留的数据
			tabsStore.closeMultipleTab();
			keepAlive.setKeepAliveName();
			if (globalStore.userInfo.role === 1 || globalStore.userInfo.role === 0) {
				authStore.setAuthMenuList(houtaiMenuList);
				// 跳转到首页
				router.push(HOME_URL);
				ElNotification({
					title: getTimeState(),
					message: "欢迎登录！",
					type: "success",
					duration: 3000
				});
			} else {
				if (globalStore.userInfo.type === 1) {
					authStore.setAuthMenuList(qiantaiMenuList);
				} else if (globalStore.userInfo.type === 2) {
					authStore.setAuthMenuList(qiantaiMenuListJZG);
				} else {
					return ElNotification({
						title: getTimeState(),
						message: "数据错误",
						type: "error",
						duration: 3000
					});
				}

				// 跳转到首页
				router.push(FRONT_HOME_URL);
				ElNotification({
					title: getTimeState(),
					message: "欢迎登录！",
					type: "success",
					duration: 3000
				});
			}
		} finally {
			loading.value = false;
		}
	});
};

// resetForm
const resetForm = (formEl: FormInstance | undefined) => {
	if (!formEl) return;
	formEl.resetFields();
};

onMounted(() => {
	// 监听enter事件（调用登录）
	document.onkeydown = (e: any) => {
		e = window.event || e;
		if (e.code === "Enter" || e.code === "enter" || e.code === "NumpadEnter") {
			if (loading.value) return;
			login(loginFormRef.value);
		}
	};
});
</script>

<style scoped lang="scss">
@import "../index.scss";
</style>
