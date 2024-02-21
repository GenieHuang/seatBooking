<template>
	<view class="person-info">
		<div class="banner">
			<u-image src="/static/avatar.png" shape="circle" width="200rpx" height="200rpx"></u-image>
		</div>
		<view class="user-content1">
			<u-cell-group border >
				<u-cell-item
				    size="large"
				    title="姓名"
					:value="userInfo.name"
					:arrow="false"
					center
				></u-cell-item>
				<u-cell-item
				    size="large"
				    title="学工号"
					:value="userInfo.username"
					:arrow="false"
					center
				></u-cell-item>
			</u-cell-group>
		</view>
		<view class="user-content2">
			<u-cell-group border >
				<u-cell-item
				    size="large"
				    title="学院"
					:value="userInfo.school"
					:arrow="false"
					center
				></u-cell-item>
				<u-cell-item
				    size="large"
				    title="用户类别"
					:value="userType[userInfo.type]"
					:arrow="false"
					center
				></u-cell-item>
				<u-cell-item
				    size="large"
				    title="所在系"
					:value="departmentType[userInfo.department]"
					:arrow="false"
					center
				></u-cell-item>
			</u-cell-group>
		</view>
	</view>
</template>

<script setup>
import {ref} from "vue";
import { onShow } from '@dcloudio/uni-app'
import {getUserInfoApi} from "/request/index.js";
import { userType, departmentType} from "/utils/enum.js";


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
</script>

<style lang="scss" scoped>
.person-info {
	overflow: auto;
	width: 100vw;
	height: 100vh;
	background: #ece9e9;
	.banner {
		box-sizing: border-box;
		display: flex;
		justify-content: center;
		align-items: center;
		width: 100%;
		height: 300rpx;
		background-color: #3771BB;
	}
	.user-content2 {
		margin-top: 40rpx;
	}
}
</style>
