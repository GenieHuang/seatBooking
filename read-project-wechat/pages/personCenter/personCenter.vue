<template>
	<view class="person-center">
		<view class="banner">
			<view class="avatar">
				<u-image src="/static/avatar.png" shape="circle" width="140rpx" height="140rpx"></u-image>
				<div class="userinfo">
					<view class="username">{{ userInfo.name || '--' }}</view>
					<view class="department">{{ departmentType[userInfo.department] || '--' }}</view>
				</div>
			</view>
			<view class="daohanglan">
				<u-cell-group border>
					<u-cell-item icon="order" size="large" title="我的预约" center index="yuyue"
						@click="handleClickCell"></u-cell-item>
					<u-cell-item icon="account-fill" size="large" title="个人资料" center index="ziliao"
						@click="handleClickCell"></u-cell-item>
					<u-cell-item icon="zhuanfa" size="large" title="退出登录" center index="tuichu"
						@click="handleClickCell"></u-cell-item>
				</u-cell-group>
			</view>
		</view>

	</view>
</template>

<script setup>
import {ref} from "vue";
import { onShow } from '@dcloudio/uni-app'
import { departmentType } from "/utils/enum.js";
import {getUserInfoApi} from "/request/index.js";
//* computed
//*data
const userInfo = ref({});
//* lifecircle
onShow(async () => {
	const { userInfo:data, ...otherInfo } = await getUserInfoApi();
	userInfo.value = {
		...data,
		...otherInfo
	}
	uni.setStorageSync("userInfo", userInfo.value);
})
//* method
function handleClickCell(index) {
	console.log("当前点击的index：：", index)
	switch (index) {
		case "yuyue":
			uni.navigateTo({
				url: "/pages/myYuyue/myYuyue"
			});
			break;
		case "ziliao":
			uni.navigateTo({
				url: "/pages/personInfo/personInfo"
			});
			break;
		case "tuichu":
			uni.clearStorageSync();
			uni.showToast({
				type: "success",
				title: "退出登录"
			});
			setTimeout(() => {
				uni.navigateTo({
					url: "/pages/login/login"
				});
			}, 1000)
			break;
		default:
			break;
	}
}
</script>

<style lang="scss" scoped>
.person-center {
	overflow: auto;
	width: 100vw;
	height: 100vh;
	background: #ece9e9;

	.banner {
		position: relative;
		box-sizing: border-box;
		width: 100%;
		height: 260rpx;
		margin-bottom: 100rpx;
		padding: 40rpx;
		border-bottom-left-radius: 50rpx;
		border-bottom-right-radius: 50rpx;
		background-color: #3771BB;

		.avatar {
			display: flex;
			align-items: center;

			.userinfo {
				padding: 20rpx;
				color: #fff;
				font-size: 32rpx;
			}

			.username {
				padding-bottom: 20rpx;
				font-size: 36rpx;
				font-weight: 700;
			}
		}

		.daohanglan {
			overflow: hidden;
			margin-top: 40rpx;
			border-radius: 10rpx;
			background-color: #fff;
		}
	}
}
</style>