<template>
	<div class="h100 flex-drc-cln flex">
		<ProTable ref="proTable" title="预约信息" :columns="columns" :requestApi="getTableList" :dataCallback="dataCallback">
			<template #operation="{ row, row: { bookStatus } }">
				<el-button type="text" :disabled="bookStatus === 5 || bookStatus === 3" @click="handleCancelSubscribe(row)"
					>取消预约</el-button
				>
				<el-button type="text" @click="handleOpenDialog(row)">编辑状态</el-button>
				<el-button type="text" @click="handleShowDetail(row)">查看详情</el-button>
			</template>
		</ProTable>
		<el-dialog v-if="dialogVisible" v-model="dialogVisible" title="编辑状态" width="600px" draggable>
			<el-form :model="formData" :rules="formRules" ref="formRef" label-width="100px">
				<el-form-item label="状态" prop="bookStatus">
					<el-select v-model.trim="formData.bookStatus" placeholder="请选择">
						<el-option v-for="(item, index) in subscribeStatus" :key="index" :label="item.label" :value="item.value"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="违约原因：" v-if="formData.bookStatus === 4" prop="reason">
					<el-input type="textarea" :rows="4" placeholder="请输入内容" resize="none" v-model="formData.reason" />
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button @click="handleClose">取 消</el-button>
					<el-button type="primary" @click="handleSubmit">确 认</el-button>
				</span>
			</template>
		</el-dialog>
		<el-dialog v-if="editDialogVisible" v-model="editDialogVisible" title="查看详情" width="600px" draggable>
			<el-form :model="detailData" label-width="100px">
				<el-form-item label="活动主题">
					<div class="topic">{{ detailData.topic }}12321321</div>
				</el-form-item>
				<el-form-item label="预约原因">
					<div class="reason">{{ detailData.reason }}12312</div>
				</el-form-item>
			</el-form>
		</el-dialog>
	</div>
</template>

<script setup lang="tsx" name="subscribe">
import { ref, reactive } from "vue";
import { ColumnProps } from "@/components/ProTable/interface";
import ProTable from "@/components/ProTable/index.vue";
import { RecordManageModule } from "@/api/interface/recordManage";
import { getBookRecordInfoAPI, switchSubscribeStatusAPI } from "@/api/modules/recordManage";
import { subscribeType, subscribeStatus } from "@/utils/serviceDict";
import { useGetGlobalProperties } from "@/utils/common/useGetGlobalProperties";
import { FormInstance } from "element-plus";
//* 全局变量
const globalProperties = useGetGlobalProperties();
//* ref
const proTable = ref();
const formRef = ref<FormInstance>();
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
		enum: [
			{
				label: "全部",
				value: undefined
			},
			...subscribeType
		],
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
	{
		prop: "reviewTime",
		label: "审核时间"
	},
	{
		prop: "bookStatus",
		label: "状态",
		enum: [
			{
				label: "全部",
				value: undefined
			},
			...subscribeStatus
		],
		search: {
			el: "select",
			props: {
				placeholder: "请选择状态"
			}
		}
	},
	{ prop: "operation", label: "操作", fixed: "right", width: 300 }
];
const dialogVisible = ref<boolean>(false);
const editDialogVisible = ref<boolean>(false);
const formData = ref<RecordManageModule.ReqSubscribeStatusModify>({
	bookId: "",
	bookStatus: 0,
	reason: ""
});
const detailData = ref<any>({
	topic: "",
	reason: ""
});
// 校验
const formRules = reactive({
	bookStatus: [{ required: true, message: "请选择预约状态", trigger: "change" }],
	reason: [{ required: true, message: "请输入违约原因", trigger: "blur" }]
});
//* method
const dataCallback = (data: any) => {
	return {
		list: data.data,
		total: data.total,
		pageNum: data.pageNum || 1,
		pageSize: data.pageSize || 10
	};
};
// 如果你想在请求之前对当前请求参数做一些操作，可以自定义如下函数：params 为当前所有的请求参数（包括分页），最后返回请求列表接口
// 默认不做操作就直接在 ProTable 组件上绑定	:requestApi="getUserList"
const getTableList = (params: any) => {
	return getBookRecordInfoAPI(params);
};
// 取消预约
function handleCancelSubscribe(row: any) {
	const { bookId } = row;
	globalProperties.$commonMsgBox.switchStatus().then(async () => {
		await switchSubscribeStatusAPI({
			bookId,
			bookStatus: 5
		});
		globalProperties.$message.success("取消预约成功");
		proTable.value.getTableList();
	});
}
// 打开编辑状态弹窗
function handleOpenDialog(row: any) {
	const { bookId, bookStatus, userId, reason } = row;
	formData.value = {
		bookId,
		bookStatus,
		userId,
		reason
	};
	dialogVisible.value = true;
}
// 关闭编辑弹窗
function handleClose() {
	dialogVisible.value = false;
}
//详情
function handleShowDetail(row: any) {
	detailData.value = { ...row };
	editDialogVisible.value = true;
}
// 提交编辑状态
function handleSubmit() {
	formRef?.value?.validate(async valid => {
		if (!valid) return;
		await switchSubscribeStatusAPI({
			...formData.value,
			reason: formData.value.bookStatus === 4 ? formData.value.reason : undefined
		});
		globalProperties.$commonMsg.update();
		proTable.value.getTableList();
		dialogVisible.value = false;
	});
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
