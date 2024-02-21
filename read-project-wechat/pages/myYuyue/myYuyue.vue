<template>
	<view class="my-yuyue">
		<div class="yuyue-wall"></div>
		<template v-if="subscribeList && subscribeList.length > 0">
			<view class="yuyue-xinxi" v-for="item in subscribeList" :key="item.bookId">
				<div class="status">
					<div class="status-tag" :style="subscribeStatus[item.bookStatus]?.style">
						{{subscribeStatus[item.bookStatus]?.text}}
					</div>
				</div>
				<div class="yuyue-xinxi-item">
					<div class="label">预约空间：</div>
					<div class="content">{{item.bookSpace}}</div>
				</div>
				<div class="yuyue-xinxi-item">
					<div class="label">预约类型：</div>
					<div class="content">{{subscribeType[item.bookType]}}</div>
				</div>
				<div class="yuyue-xinxi-item">
					<div class="label">预约时间：</div>
					<div class="content">{{`${item.startTime || ''}-${item.endTime || ''}`}}</div>
				</div>
				<div class="yuyue-xinxi-item">
					<div class="label">活动主题：</div>
					<div class="content">{{item.topic || '--'}}</div>
				</div>
			</view>
		</template>
		<u-empty v-else text="预约记录为空" mode="list"></u-empty>
	</view>
</template>

<script setup>
	import {
		ref
	} from "vue";
	import {
		onShow,
		onReachBottom,
		onPullDownRefresh
	} from '@dcloudio/uni-app'
	import {
		frontSearchApi
	} from "/request/index.js";
	import {
		subscribeType,
		subscribeStatus
	} from "/utils/enum.js";

	//* data
	const subscribeList = ref([]);
	const subscribeParams = ref({
		pageNum: 1,
		pageSize: 10,
		userId: uni.getStorageSync("userInfo")?.userId
	});
	const isLoadingData = false;
	//* lifecircle
	onShow(() => {
		subscribeParams.value.pageNum = 1;
		subscribeList.value = [];
		getSubscribeList();
	})
	onPullDownRefresh(() => {
		getSubscribeList();
	})
	onReachBottom(() => {
		getSubscribeList();
	})
	//* method
	async function getSubscribeList() {
		console.log("执行了getSubscribeList")
		isLoadingData = true;
		let {
			data = [], pageNum, pageSize, total
		} = await frontSearchApi(subscribeParams.value);
		data = (data || []).map(item => {
			return {
				...item,
				endTime: item?.endTime?.substring(11)
			}
		})
		if (subscribeList.value.length >= total) {
			return
		} else {
			console.log("执行到这列",subscribeList.value.length)
			subscribeList.value = [...subscribeList.value, ...data];
			subscribeParams.value.pageNum++;
			subscribeParams.value.pageSize;
			console.log("subscribeList::", subscribeList)
			isLoadingData = false;
		}
		
	}
</script>

<style lang="scss" scoped>
	.my-yuyue {
		/* display: flex;
		justify-content: center;
		flex-wrap: wrap; */
		width: 100vw;
		min-height: 100vh;
		padding-bottom: 40rpx;
		background: #ece9e9;
		.yuyue-wall {
			width: 10rpx;
			height: 2rpx;
		}
		.yuyue-xinxi {
			position: relative;
			display: flex;
			justify-content: center;
			flex-wrap: wrap;
			box-sizing: border-box;
			width: 700rpx;
			height: 260rpx;
			margin: 40rpx auto 0;
			padding: 20rpx 0;
			border-radius: 10rpx;
			background-color: #fff;

			.status {
				position: absolute;
				top: 20rpx;
				right: 20rpx;
			}

			.status-tag {
				display: flex;
				justify-content: center;
				align-items: center;
				width: 120rpx;
				border-radius: 6rpx;
				font-size: 24rpx;
				height: 60rpx;
			}

			.yuyue-xinxi-item {
				display: flex;
				flex-wrap: wrap;
				width: 100%;
				margin-bottom: 10rpx;
			}

			.label {
				width: 160rpx;
				text-align: right;
			}
		}
	}
</style>