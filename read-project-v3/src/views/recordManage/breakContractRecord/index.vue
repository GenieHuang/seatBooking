<template>
	<div class="h100 flex-drc-cln flex">
		<ProTable ref="proTable" title="违约记录" :columns="columns" :requestApi="getTableList" :dataCallback="dataCallback">
			<template #operation="{ row: { breakStatus }, row }">
				<el-button type="text" @click="switchBreakStatus(row)">{{ breakStatus === 1 ? "开启" : "禁用" }}</el-button>
			</template>
		</ProTable>
	</div>
</template>

<script setup lang="tsx" name="breakContractRecord">
import { ref } from "vue";
import { ColumnProps } from "@/components/ProTable/interface";
import ProTable from "@/components/ProTable/index.vue";
import { userStatus } from "@/utils/serviceDict";
import { useGetGlobalProperties } from "@/utils/common/useGetGlobalProperties";
import { getBreakRecordInfoAPI, switchBreakStatusAPI } from "@/api/modules/breakManage";

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
		prop: "bookId",
		label: "预约号"
	},
	{
		prop: "createBook",
		label: "预约记录时间"
	},
	{
		prop: "reason",
		label: "违约原因"
	},
	{
		prop: "breakTime",
		label: "违约时间"
	},
	{
		prop: "breakStatus",
		label: "违约状态",
		enum: userStatus
	},
	{ prop: "operation", label: "操作", fixed: "right", width: 200 }
];
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
	return getBreakRecordInfoAPI({
		bookStatus: 4,
		...params
	});
};
// 修改违约状态
function switchBreakStatus(row: any) {
	const { breakId, breakStatus } = row;
	globalProperties.$commonMsgBox.switchStatus().then(async () => {
		await switchBreakStatusAPI({
			breakId
		});
		if (breakStatus === 1) {
			globalProperties.$message.success("启用成功");
		} else {
			globalProperties.$message.success("禁用成功");
		}
		proTable.value.getTableList();
	});
}
</script>
