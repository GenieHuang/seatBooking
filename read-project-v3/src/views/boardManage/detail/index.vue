<template>
	<div class="h100 card">
		<el-form label-width="100px">
			<el-form-item label="标题：">
				<div class="title flex flex-al-center" :title="props.title">
					{{ props.title }}
				</div>
			</el-form-item>
			<el-form-item label="内容：">
				<div class="msg">
					{{ props.msg }}
				</div>
			</el-form-item>
			<el-form-item label="插图：">
				<div class="pic flex flex-al-center">
					<el-image
						style="width: 100px; height: 100px"
						v-if="props.bUrl"
						:src="globalStore.baseUrl + '/pic/getImage/?fileName=' + props.bUrl"
						:preview-src-list="[`${globalStore.baseUrl}/pic/getImage/?fileName=${props.bUrl}`]"
						class="avatar"
					/>
					<span v-else>暂无图片</span>
				</div>
			</el-form-item>
			<el-form-item>
				<el-button @click="goback">返 回</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script lang="ts" setup>
import { defineProps, withDefaults } from "vue";
import { GlobalStore } from "@/stores";
import { useRouter } from "vue-router";
interface Props {
	title?: string;
	msg?: string;
	bUrl?: string;
}
//* props
const props = withDefaults(defineProps<Props>(), {
	title: "",
	msg: "",
	bUrl: ""
});
//* router
const router = useRouter();

//* pinia
const globalStore = GlobalStore();
//* method
// 取消返回公告列表
function goback() {
	router.push({
		path: "/boardManage/list"
	});
}
</script>

<style lang="scss" scoped>
.title {
	box-sizing: border-box;
	width: 600px;
	height: 40px;
	padding: 5px 10px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	border: 1px solid #cccccc;
}
.msg {
	box-sizing: border-box;
	width: 600px;
	height: 200px;
	padding: 5px 10px;
	overflow-y: auto;
	border: 1px solid #cccccc;
}
</style>
