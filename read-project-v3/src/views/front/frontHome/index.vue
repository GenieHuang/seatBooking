<template>
	<div class="h100 card over-y-auto">
		<!-- <div class="carousel">
			<el-carousel height="200px">
				<el-carousel-item v-for="item in 4" :key="item">
					<h3 class="small justify-center" text="2xl">{{ item }}</h3>
				</el-carousel-item>
			</el-carousel>
		</div> -->
		<div class="content flex flex-drc-cln flex-ac">
			<read-title class="pt20 pb20" style="width: 800px">
				<template #default>最新公告：</template>
			</read-title>
			<div class="board-content">
				<div class="board-title flex flex-ac" :title="`${boardInfo.title}（${boardInfo.boardTime}）`">
					<span class="fw600 c6B6D74 f16">更新时间：{{ boardInfo.boardTime }}</span>
				</div>
				<div class="board-msg">
					<span class="fw600 mr5 f20">{{ boardInfo.title }}</span>
					<div class="f16 mt20" style="line-height: 32px; text-indent: 20px">{{ boardInfo.msg }}</div>
				</div>
				<div class="bg-img">
					<el-image :src="globalStore.baseUrl + '/pic/getImage/?fileName=' + boardInfo?.bUrl">
						<template #error>
							<div class="image-slot">
								<el-icon><icon-picture /></el-icon>
							</div>
						</template>
					</el-image>
				</div>
			</div>
		</div>
	</div>
</template>

<script lang="ts" setup name="frontHome">
import { onMounted, ref } from "vue";
import { getNewBoard } from "@/api/modules/boardManage";
import { GlobalStore } from "@/stores";
//* data
const boardInfo = ref<any>({
	bUrl: "",
	boardTime: "",
	msg: "",
	title: ""
});
//* pinia
const globalStore = GlobalStore();
//* lifeCircle
onMounted(() => {
	getNewBoard().then(res => {
		boardInfo.value = res?.data?.[0] || [];
	});
});
</script>

<style lang="scss" scoped>
.carousel {
	display: flex;
	justify-content: center;
	.el-carousel {
		width: 600px;
		border: 1px solid #e5eaf3;
	}
}
.board-content {
	width: 800px;
	min-height: 400px;
	padding: 10px;
	.board-title {
		box-sizing: border-box;
		height: 40px;
		padding-left: 20px;
		margin-bottom: 10px;
		overflow: hidden;
		font-size: 24px;
		text-overflow: ellipsis;
		white-space: nowrap;
		border: 1px solid #cccccc;
	}
	.board-msg {
		padding-top: 10px;
	}
	.bg-img {
		display: flex;
		align-items: center;
		justify-content: center;
		.el-image {
			max-width: 400px;
			max-height: 300px;
		}
		img {
			width: 100%;
			height: 100%;
		}
	}
}
</style>
