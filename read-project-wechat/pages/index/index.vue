<template>
	<view class="content">
	</view>
</template>

<script setup>
	import {
		onShow
	} from '@dcloudio/uni-app'

	onShow(() => {
		console.log("oNshow")
		if (uni.getStorageSync('token')) {
			uni.switchTab({
				url: '/pages/home/home'
			})
		} else {
			uni.navigateTo({
				url: '/pages/login/login'
			})
		}
	})
</script>

<style>
</style>