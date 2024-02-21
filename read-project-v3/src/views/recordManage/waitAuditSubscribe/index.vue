<template>
	<div class="h100 flex-drc-cln flex">
		<ProTable ref="proTable" title="待审核记录" :columns="columns" :requestApi="getTableList" :dataCallback="dataCallback">
			<template #bookSpace="{ row }">
				{{ row.roomType === 3 ? `${row.rCode}-${row.sCode}` : `${row.rCode}` }}
			</template>
			<template #operation="{ row }">
				<el-button type="text" @click="handleCancelSubscribe(row, 1)">通过</el-button>
				<el-button type="text" @click="handleCancelSubscribe(row, 7)">驳回</el-button>
				<el-button type="text" @click="handleShowDetail(row)">详情</el-button>
			</template>
		</ProTable>
		<el-dialog v-if="editDialogVisible" v-model="editDialogVisible" title="查看详情" width="600px" draggable>
			<el-form :model="detailData" label-width="100px">
				<el-form-item label="活动主题">
					<div class="topic">{{ detailData.topic || "暂无信息" }}</div>
				</el-form-item>
				<el-form-item label="预约原因">
					<div class="reason">{{ detailData.reason || "暂无信息" }}</div>
				</el-form-item>
			</el-form>
		</el-dialog>
	</div>
</template>

<script setup lang="tsx" name="waitAuditSubscribe">
import { ref } from "vue";
import { ColumnProps } from "@/components/ProTable/interface";
import ProTable from "@/components/ProTable/index.vue";
import { getBookRecordInfoAPI, switchSubscribeStatusAPI } from "@/api/modules/recordManage";
import { useGetGlobalProperties } from "@/utils/common/useGetGlobalProperties";
import { subscribeType } from "@/utils/serviceDict";
//* 全局变量
const globalProperties = useGetGlobalProperties();
//* ref
const proTable = ref();
//* data
// 表格配置项
const columns: ColumnProps<any>[] = [
	{
		prop: "bookId",
		label: "预约号",
		search: {
			el: "input",
			props: {
				placeholder: "请输入预约号"
			}
		}
	},
	{
		prop: "name",
		label: "姓名",
		search: {
			el: "input",
			props: {
				placeholder: "请输入姓名"
			}
		}
	},
	{
		prop: "username",
		label: "学工号",
		search: {
			el: "input",
			props: {
				placeholder: "请输入学工号"
			}
		}
	},
	{
		prop: "bookType",
		label: "预约类型",
		enum: subscribeType,
		search: {
			el: "select",
			props: {
				placeholder: "请选择预约类型"
			}
		}
	},
	{
		prop: "bookSpace",
		label: "预约空间"
	},
	{
		prop: "advisor",
		label: "指导老师"
	},
	{
		prop: "roomName",
		label: "房间名称"
	},
	{
		prop: "createBook",
		label: "预约记录时间"
	},
	{
		prop: "bookDate",
		label: "预约日期"
	},
	{
		prop: "startTime",
		label: "开始时间"
	},
	{
		prop: "endTime",
		label: "结束时间"
	},
	{ prop: "operation", label: "操作", fixed: "right", width: 300 }
];
const detailData = ref({
	topic: "",
	reason: ""
});
const editDialogVisible = ref<boolean>(false);
//* method
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
	return getBookRecordInfoAPI({
		bookStatus: 6,
		...params
	});
};
// 更改预约状态
function handleCancelSubscribe(row: any, bookStatus: number) {
	const { bookId } = row;
	globalProperties.$commonMsgBox.switchStatus().then(async () => {
		await switchSubscribeStatusAPI({
			bookId,
			bookStatus: bookStatus
		});
		if (bookStatus === 7) {
			globalProperties.$message.success("驳回成功");
		} else if (bookStatus === 1) {
			globalProperties.$message.success("已通过");
		}
		proTable.value.getTableList();
	});
}
// 查看详情
function handleShowDetail(row: any) {
	detailData.value = { ...row };
	editDialogVisible.value = true;
}
</script>
<style lang="scss" scoped>
.topic {
	display: flex;
	align-items: center;
	width: 100%;
	height: 32px;
	padding: 0 10px;
	overflow: hidden;
	border: 1px solid #cccccc;
}
.reason {
	width: 100%;
	height: 300px;
	padding: 5px 10px;
	overflow: auto;
	border: 1px solid #cccccc;
}
</style>
