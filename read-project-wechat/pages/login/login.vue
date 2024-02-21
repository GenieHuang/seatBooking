<template>
	<view class="login-box">
		<div class="login-header">
			<div class="login-title">座位预约系统</div>
		</div>
		<u-form class="login-form" :model="formData" ref="uFormRef">
			<u-form-item prop="username">
				<u-input v-model="formData.username" placeholder="请输入学工号" />
			</u-form-item>
			<u-form-item prop="password">
				<u-input type="password" v-model="formData.password" placeholder="请输入密码" />
			</u-form-item>
		</u-form>
		<u-button type="primary" :custom-style="loginStyle" @click="handleLogin">登录</u-button>
	</view>
</template>

<script setup>
	import {
		ref
	} from 'vue';
	import {
		loginApi,
		getUserInfoApi
	} from "/request/index.js";
	import {
		onReady
	} from "@dcloudio/uni-app";
	//* ref
	const uFormRef = ref();
	//* data
	// 自定义样式			
	const loginStyle = {
		marginTop: '40rpx'
	};
	// 登录表单
	const formData = ref({
		name: "",
		intro: ""
	});
	const rules = {
		username: [{
			required: true,
			message: '请输入学工号',
			trigger: ['change', 'blur'],
		}],
		password: [{
			required: true,
			message: '请输入密码',
			trigger: ['change', 'blur'],
		}]
	}
	//* method
	function handleLogin() {
		uFormRef.value.validate(async valid => {
			if(!valid) return;
			const data = await loginApi(formData.value)
			uni.setStorageSync("token", data?.token || "");
			const data2 = await getUserInfoApi();
			const {
				userInfo,
				...otherInfo
			} = data2;
			uni.setStorageSync("userInfo", {
				...userInfo,
				...otherInfo
			});
			const userInfoStore = uni.getStorageSync("userInfo");
			if (uni.getStorageSync("token")) {
				if (userInfoStore.role === 0 || userInfoStore.role === 1 || !userInfoStore.role) {
					uni.clearStorageSync();
					uni.showToast({
						type: "warning",
						title: '登录角色错误'
					});
				} else if (userInfoStore.role === 2) {
					uni.showToast({
						type: "success",
						title: '登录成功！'
					})
					setTimeout(() => {
						uni.switchTab({
							url: '/pages/home/home'
						});
					}, 1000)
				}
			}
		})
	}
	onReady(() => {
		console.log("uFormRef",uFormRef)
		uFormRef.value.setRules(rules)
	});
</script>

<style lang="scss" scoped>
	.login-box {
		padding: 40rpx 60rpx;

		.login-header {
			display: flex;
			align-items: center;
			height: 120rpx;
			font-size: 60rpx;
			font-weight: 700;
		}

		.login-form {
			box-sizing: border-box;
		}
	}
</style>