<template>
	<div class="h100 flex-drc-cln flex">
		<ProTable ref="proTable" title="自习室信息" :columns="columns" :requestApi="getTableList" :dataCallback="dataCallback">
			<template #tableHeader>
				<el-button type="primary" :icon="Plus" @click="handleOpenDialog('add')">新增自习室</el-button>
			</template>
			<template #rUrl="{ row: { rUrl } }">
				<el-image
					preview-teleported
					style="width: 50px; height: 50px"
					v-if="rUrl"
					:src="`${globalStore.baseUrl}/pic/getImage/?fileName=${rUrl}`"
					:preview-src-list="[`${globalStore.baseUrl}/pic/getImage/?fileName=${rUrl}`]"
				/>
				<span v-else>暂无图片</span>
			</template>
			<template #operation="{ row, row: { roomId, rStatus } }">
				<el-button type="text" @click="handleOpenDialog('edit', row)">编辑</el-button>
				<el-button type="text" @click="handleSwitchStatus(roomId, rStatus)">{{ rStatus === 1 ? "开启" : "禁用" }}</el-button>
			</template>
		</ProTable>
		<el-dialog v-if="dialogVisible" v-model="dialogVisible" :title="dialogTitle" width="600px" draggable>
			<!-- 下面的model必须v-bind形式，:model等价于v-bind:model,绑定表单数据源对象;rules绑定校验规则 -->
			<el-form :model="formData" :rules="formRules" ref="formRef" label-width="100px">
				<!-- 这里写上prop，校验表单数据源对象中的属性，如果要校验多层，假设需要校验rCode里面的a字段，那就在rules里加.：rCode.a()，这里的prop和formRules是对应的 -->
				<el-form-item label="自习室编号：" prop="rCode">
					<el-input v-model.trim="formData.rCode" placeholder="请输入自习室编号"></el-input>
				</el-form-item>
				<el-form-item label="自习室名称：" prop="roomName">
					<el-input v-model.trim="formData.roomName" placeholder="请输入自习室名称"></el-input>
				</el-form-item>
				<el-form-item label="座位数量：" prop="num">
					<!-- controls-position="right" -->
					<!-- class="w100" -->
					<el-input-number v-model="formData.num" :min="1" :max="180" :step="1" step-strictly />
				</el-form-item>
				<el-form-item label="所在楼层：">
					<el-input v-model.trim="formData.floor" placeholder="请输入所在楼层"></el-input>
				</el-form-item>
				<el-form-item label="座位图">
					<el-upload class="avatar-uploader" :action="picUrl" :show-file-list="false" :on-success="handlePicUpload">
						<img v-if="formData.rUrl" :src="globalStore.baseUrl + '/pic/getImage/?fileName=' + formData.rUrl" class="avatar" />
						<el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
					</el-upload>
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button @click="handleClose">取 消</el-button>
					<el-button type="primary" @click="handleSubmit">确 认</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>

<script setup lang="tsx" name="studyRoomInfo">
import { ref, reactive, computed } from "vue";
import { ColumnProps } from "@/components/ProTable/interface";
import ProTable from "@/components/ProTable/index.vue";
import { SettingModule } from "@/api/interface/setting";
import { getRoomListByTypeAPI, addOrEditStudyRoomAPI, switchRoomStatusAPI } from "@/api/modules/setting";
import { Plus } from "@element-plus/icons-vue";
import { userStatus } from "@/utils/serviceDict";
import { FormInstance } from "element-plus";
import { useGetGlobalProperties } from "@/utils/common/useGetGlobalProperties";
import { GlobalStore } from "@/stores";
import type { UploadProps } from "element-plus";
//* 全局变量
const globalProperties = useGetGlobalProperties();
//* pinia
const globalStore = GlobalStore();
//* ref
const proTable = ref();
const formRef = ref<FormInstance>();

//* data
// 表格配置项
const columns: ColumnProps<any>[] = [
	{
		prop: "roomId",
		label: "ID"
	},
	{
		prop: "rCode",
		label: "自习室编号",
		search: {
			el: "input",
			props: {
				placeholder: "请输入自习室编号"
			}
		}
	},
	{
		prop: "roomName",
		label: "自习室名称"
	},
	{
		prop: "num",
		label: "座位数量"
	},
	{
		prop: "floor",
		label: "所在楼层"
	},
	{
		prop: "rUrl",
		label: "座位图"
	},
	{
		prop: "rStatus",
		label: "状态",
		enum: userStatus,
		search: {
			el: "select",
			props: {
				placeholder: "请选择状态"
			}
		}
	},
	{ prop: "operation", label: "操作", fixed: "right", width: 200 }
];
// 弹窗控制
const dialogVisible = ref<boolean>(false);
const dialogType = ref<string>("add");
const formData = ref<SettingModule.ReqAddOrEditStudyRoomInfo>({
	rCode: "",
	roomName: "",
	num: undefined,
	floor: "",
	rUrl: ""
});
const copyFormData = {
	rCode: "",
	roomName: "",
	num: undefined,
	floor: "",
	rUrl: ""
};
// 校验
const formRules = reactive({
	rCode: [{ required: true, message: "请输入自习室编号", trigger: "blur" }],
	roomName: [{ required: true, message: "请输入自习室名称", trigger: "blur" }],
	num: [{ required: true, message: "请输入座位数量", trigger: "blur" }],
	floor: [{ required: true, message: "请输入所在楼层", trigger: "blur" }],
	rUrl: [{ required: true, message: "请上传座位图", trigger: "change" }]
});
//* computed
const dialogTitle = computed(() => {
	return dialogType.value === "add" ? "新增自习室" : "编辑自习室";
});
const picUrl = computed(() => {
	return globalStore.baseUrl + "/pic/upload";
});
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
	return getRoomListByTypeAPI({
		rType: 2,
		...params
	});
};
// 打开新增/编辑弹窗
function handleOpenDialog(type: "add" | "edit", row?: any) {
	dialogType.value = type;
	if (type === "add") {
		formData.value = {
			...copyFormData
		};
		dialogVisible.value = true;
	} else if (type === "edit") {
		const { rCode, roomName, num, floor, rUrl, roomId = undefined } = row;
		formData.value = {
			roomId,
			roomName,
			rCode,
			floor,
			num,
			rUrl
		};
		dialogVisible.value = true;
	}
}
// 关闭弹窗
function handleClose() {
	formData.value = {
		...copyFormData
	};
	dialogVisible.value = false;
}
// 上传图片
const handlePicUpload: UploadProps["onSuccess"] = ({ data }) => {
	formData.value.rUrl = data;
};
// 新增/更新自习室信息
function handleSubmit() {
	formRef.value?.validate(async valid => {
		if (!valid) return;
		await addOrEditStudyRoomAPI(formData.value);
		dialogVisible.value = false;
		if (formData.value.roomId) {
			globalProperties.$commonMsg.update();
		} else {
			globalProperties.$commonMsg.add();
		}
		proTable.value.getTableList();
	});
}
// 更新教室
function handleSwitchStatus(roomId: number, status: number) {
	globalProperties.$commonMsgBox.switchStatus().then(async () => {
		await switchRoomStatusAPI({
			roomId
		});
		if (status === 1) {
			globalProperties.$message.success("启用成功");
		} else {
			globalProperties.$message.success("禁用成功");
		}
		proTable.value.getTableList();
	});
}
</script>
<style lang="scss" scoped>
.avatar-uploader .avatar {
	display: block;
	width: 100px;
	height: 100px;
}
</style>
