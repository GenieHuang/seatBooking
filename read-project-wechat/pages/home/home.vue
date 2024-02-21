<template>
	<view class="home">
		<view class="banner">
			<u-swiper class="swiper" :list="swiperList"></u-swiper>
		</view>
		<view class="icon" style="padding-top: 40rpx;padding-left: 20rpx;">
			<img src="/static/tishi.png" style="width: 60rpx;height: 60rpx" />
		</view>
		<view class="notice">
			<view class="notice-title">{{boardInfo.title}}</view>
			<view class="notice-time">{{boardInfo.boardTime}}</view>
			<view class="notice-content">{{boardInfo.msg}}</view>
			<view style="text-align: center">
				<u-image v-if="boardInfo.bUrl" style="display: flex;justify-content: center;" lazyLoad
					:src="`http://localhost:9090/pic/getImage?fileName=${boardInfo.bUrl}`" width="500rpx"
					height="200rpx"></u-image>
			</view>
		</view>
		<view class="yuyuexinxi" v-for="item in yuyueInfoList" :key="item.bookId">
			<div class="yuyue-item">
				<div class="yueyue-item-label">预约空间：</div>
				<div class="yuyue-item-content">{{item.bookSpace}}</div>
			</div>
			<div class="yuyue-item">
				<div class="yueyue-item-label">预约时间：</div>
				<div class="yuyue-item-content">{{item.startTime}}-{{item.endTime}}</div>
			</div>
			<div class="yuyue-item">
				<div class="yueyue-item-label" v-if="item.bookType === 1 || item.bookType === 3">活动主题：</div>
				<div class="yuyue-item-content">{{item.topic}}</div>
			</div>
			<div class="yuyue-btn">
				<u-button size="mini" :custom-style="{
					backgroundColor: '#ecc82b'
					}"
					@click="handleCancelBook(item)">取消预约</u-button>
			</div>
		</view>
		<u-modal v-model="show" content="是否取消预约？" @confirm="confirmCancelBook"></u-modal>
	</view>
</template>

<script setup>
	import {
		ref
	} from "vue";
	import {
		onShow
	} from '@dcloudio/uni-app'
	import {
		getBoardNewMsgApi,
		uniSearchApi,
		updateBookStatus
	} from "/request/index.js";

	const swiperList = [
		{
			image: '/static/swiper1.jpg',
		},
		{
			image: '/static/swiper2.jpg',
		}
	];
	const boardInfo = ref({})
	const yuyueInfoList = ref();
	let currentCancelItem = null;
	const show = ref(false);
	onShow(async (option) => {
		const data = await getBoardNewMsgApi();
		if (data && data[0]) {
			boardInfo.value = data[0]
			console.log("boardInfo", boardInfo)
		}
		getYuyueList();
	})
	async function getYuyueList() {
		const data = await uniSearchApi({
			userId: uni.getStorageSync("userInfo")?.userId
		})
		if (data && data.length > 0) {
			yuyueInfoList.value = data.map(item => {
				return {
					...item,
					endTime: item?.endTime?.substring(11)
				}
			});
		} else {
			yuyueInfoList.value = [];
			uni.showToast({
				type: "info",
				title: '当前没有已预约记录'
			})
		}
	}
	function handleCancelBook(item) {
		console.log("item::item", item);
		currentCancelItem = item;
		show.value = true;
	}
	async function confirmCancelBook() {
		const { bookId } = currentCancelItem
		await updateBookStatus({
			userId: uni.getStorageSync("userInfo")?.userId,
			bookId,
			bookStatus: 5
		})
		uni.showToast({
			type: "success",
			title: '取消预约成功'
		})
		getYuyueList();
	}
</script>

<style lang="scss" scoped>
	.home {
		font-family: monospace;
	}
	.home {
		overflow: auto;
		width: 100vw;
		height: 100vh;
		background: #ece9e9;

		.banner {
			position: relative;
			width: 100%;
			height: 200rpx;
			margin-bottom: 130rpx;
			border-bottom-left-radius: 50rpx;
			border-bottom-right-radius: 50rpx;
			background-color: #3771BB;
			.swiper {
				position: absolute;
				bottom: 0;
				left: 50%;
				transform: translate(-50%, 50%);
				width: 600rpx;
			}
		}

		.notice,
		.yuyuexinxi {
			box-sizing: border-box;
			width: 720rpx;

			border-radius: 10rpx;
			background-color: #fff;

		}

		.notice {
			min-height: 400rpx;
			padding: 20rpx 30rpx;
			margin: 40rpx auto;

			.notice-title {
				font-size: 36rpx;
				font-weight: 700;
			}

			.notice-time {
				display: flex;
				justify-content: flex-end;
				padding: 10rpx 20rpx;
				font-weight: 600;
			}

			.notice-content {
				padding: 10rpx 20rpx;
				color: #909399;
				line-height: 60rpx;
				text-indent: 30rpx;
			}
		}

		.yuyuexinxi {
			position: relative;
			margin: 40rpx auto;
			padding: 50rpx 20rpx;

			.yuyue-item {
				display: flex;
				align-items: center;
				height: 60rpx;
				font-size: 28rpx;
			}

			.yuyue-btn {
				position: absolute;
				bottom: 20rpx;
				right: 20rpx;
			}
		}
	}
</style>