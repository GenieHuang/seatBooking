<template>
	<div class="h100 flex-drc-cln flex">
		<ProTable ref="proTable" title="公告列表" :columns="columns" :requestApi="getTableList" :dataCallback="dataCallback">
			<template #tableHeader>
				<el-button type="primary" :icon="Plus" @click="handleAddBoard">新增公告</el-button>
			</template>
			<template #bUrl="{ row: { bUrl } }">
				<el-image
					preview-teleported
					style="width: 50px; height: 50px"
					v-if="bUrl"
					:src="`${globalStore.baseUrl}/pic/getImage/?fileName=${bUrl}`"
					:preview-src-list="[`${globalStore.baseUrl}/pic/getImage/?fileName=${bUrl}`]"
				/>
				<span v-else>暂无图片</span>
			</template>
			<template #operation="{ row }">
				<el-button type="text" @click="goToShow(row)">查看</el-button>
			</template>
		</ProTable>
	</div>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { ColumnProps } from "@/components/ProTable/interface";
import { getBoardListAPI } from "@/api/modules/boardManage";
import { Plus } from "@element-plus/icons-vue";
import { GlobalStore } from "@/stores";
//* ref
// this.$ref.protTable
const proTable = ref();
//* router
const router = useRouter();
//* pinia
const globalStore = GlobalStore();
//* data
const columns: ColumnProps<any>[] = [
	{
		type: "index",
		label: "序号"
	},
	{
		prop: "title",
		label: "标题"
	},
	{
		prop: "msg",
		label: "正文"
	},
	{
		prop: "bUrl",
		label: "插图"
	},
	{
		prop: "boardTime",
		label: "创建时间"
	},
	{ prop: "operation", label: "操作", fixed: "right", width: 200 }
];
//* methods
const dataCallback = (data: any) => {
	return {
		list: data.data,
		total: data.total,
		pageNum: data.pageNum,
		pageSize: data.pageSize
	};
};

// 如果你想在请求之前对当前请求参数做一些操作，可以自定义如下函数：params 为当前所有的请求参数（包括分页），最后返回请求列表接口
// 默认不做操作就直接在 ProTable 组件上绑定	:requestApi="getUserList"
const getTableList = (params: any) => {
	return getBoardListAPI(params);
};
// 跳转新增公告页面
function handleAddBoard() {
	router.push({
		path: "/boardManage/add"
	});
}
// 查看公告
function goToShow(row: any) {
	console.log("row::", row);
	router.push({
		path: "/boardManage/detail",
		query: row
	});
}
</script>

<style lang="scss"></style>
