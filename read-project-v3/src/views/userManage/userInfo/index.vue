<template>
	<div class="h100 flex-drc-cln flex">
		<ProTable ref="proTable" title="用户信息" :columns="columns" :requestApi="getTableList" :dataCallback="dataCallback">
			<template #tableHeader>
				<el-button type="primary" :icon="Plus" @click="() => handleOpenEdit('add')">新增</el-button>
			</template>
			<template #isBookSeat="{ row: { isBookSeat } }">
				<span>{{ isBookSeat === 0 ? "否" : "是" }}</span>
			</template>
			<template #isBookMeet="{ row: { isBookMeet } }">
				<span>{{ isBookMeet === 0 ? "否" : "是" }}</span>
			</template>
			<template #operation="{ row: { userId, status }, row }">
				<el-button type="text" @click="() => handleOpenEdit('edit', row)">编辑</el-button>
				<el-button type="text" @click="() => handleSwitchStatus(userId, status)">{{ status === 1 ? "开启" : "禁用" }}</el-button>
			</template>
		</ProTable>
		<el-dialog v-if="dialogVisible" v-model="dialogVisible" :title="dialogTitle" width="600px" draggable>
			<el-form :model="formData" ref="formRef" :rules="formRules" label-width="100px">
				<el-form-item label="姓名：" prop="userInfo.name">
					<el-input v-model.trim="formData.userInfo.name" placeholder="请输入姓名"></el-input>
				</el-form-item>
				<el-form-item label="学工号：" prop="userInfo.username">
					<el-input v-model.trim="formData.userInfo.username" placeholder="请输入学工号"></el-input>
				</el-form-item>
				<el-form-item label="登录角色：" prop="userInfo.role">
					<el-select class="w100" v-model="formData.userInfo.role" placeholder="请选择">
						<el-option v-for="(item, index) in roleTypeOptions" :key="index" :label="item.label" :value="item.value"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="用户类别：" prop="type">
					<el-select class="w100" v-model="formData.type" placeholder="请选择">
						<el-option v-for="(item, index) in userTypeStatus" :key="index" :label="item.label" :value="item.value"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="学院：" prop="school">
					<el-input v-model.trim="formData.school" placeholder="请输入学院"></el-input>
				</el-form-item>
				<el-form-item v-if="formData.userInfo.role === 2" label="所在系：" prop="department">
					<el-select class="w100" v-model="formData.department" placeholder="请选择">
						<el-option
							v-for="(item, index) in departmentTypeList"
							:key="index"
							:label="item.label"
							:value="item.value"
						></el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else label="所在系：">
					<el-select class="w100" v-model="formData.department" placeholder="请选择">
						<el-option
							v-for="(item, index) in departmentTypeList"
							:key="index"
							:label="item.label"
							:value="item.value"
						></el-option>
					</el-select>
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button @click="handleEditClose">取 消</el-button>
					<el-button type="primary" @click="handleEditSubmit">确 认</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>

<script setup lang="tsx" name="userInfo">
import { ref, reactive, computed } from "vue";
import { ColumnProps } from "@/components/ProTable/interface";
import ProTable from "@/components/ProTable/index.vue";
import { getUserListAPI, switchUserStatusAPI, addOrEditUserInfoAPI } from "@/api/modules/userModule";
import { UserModule } from "@/api/interface/userModule";
import { useGetGlobalProperties } from "@/utils/common/useGetGlobalProperties";
import { Plus } from "@element-plus/icons-vue";
import { userStatus, userTypeStatus, roleTypeStatus, departmentTypeList } from "@/utils/serviceDict";
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
		prop: "userId",
		label: "ID"
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
		prop: "school",
		label: "学院"
	},
	{
		prop: "department",
		label: "所在系",
		enum: departmentTypeList
	},
	{
		prop: "isBookSeat",
		label: "是否存在已预约自习室座位"
	},
	{
		prop: "isBookMeet",
		label: "是否存在已预约会议室"
	},
	{
		prop: "type",
		label: "用户类别",
		enum: userTypeStatus
	},
	{
		prop: "role",
		label: "登录角色",
		enum: roleTypeStatus
	},
	{
		prop: "status",
		label: "账户状态",
		enum: userStatus,
		search: {
			el: "select",
			props: {
				placeholder: "请输入学工号"
			}
		}
	},
	{ prop: "operation", label: "操作", fixed: "right", width: 200 }
];
// 编辑弹窗控制参数
const dialogVisible = ref<boolean>(false);
const dialogType = ref<string>("add");
const roleTypeOptions = roleTypeStatus.filter(item => [1, 2].includes(item.value));
//表单
const formData = ref<UserModule.ReqAddOrEditUserInfo>({
	userInfo: {
		name: "",
		username: "",
		role: 2,
		password: "",
		status: 0
	},
	school: "",
	type: 1,
	department: ""
});
const resetFormData = {
	userInfo: {
		name: "",
		username: "",
		role: 2,
		password: "",
		status: 0
	},
	school: "",
	type: 1,
	department: ""
};
//校验
const formRules = reactive({
	"userInfo.name": [{ required: true, message: "请输入姓名", trigger: "blur" }],
	"userInfo.username": [{ required: true, message: "请输入学工号", trigger: "blur" }],
	"userInfo.role": [{ required: true, message: "请选择角色", trigger: "change" }],
	school: [{ message: "请输入学院", trigger: "blur" }],
	type: [{ required: true, message: "请选择用户类别", trigger: "change" }],
	department: [{ required: true, message: "请选择所在系", trigger: "change" }]
});
//* computed
const dialogTitle = computed(() => {
	return dialogType.value === "add" ? "新增用户" : "编辑用户";
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
	return getUserListAPI(params);
};
// 新增或编辑用户
function handleOpenEdit(type: "add" | "edit", row?: any) {
	dialogType.value = type;
	if (type === "add") {
		formData.value = JSON.parse(JSON.stringify(resetFormData));
		dialogVisible.value = true;
	} else if (type === "edit") {
		const { name, username, school, type, role, userId, department = "" } = row;
		formData.value = {
			userInfo: {
				name,
				username,
				role,
				userId
			},
			school,
			type,
			department
		};
		dialogVisible.value = true;
	}
}
// 提交弹窗
function handleEditSubmit() {
	formRef.value?.validate(async valid => {
		if (!valid) return;
		await addOrEditUserInfoAPI({
			...formData.value,
			userInfo: {
				...formData.value.userInfo,
				password: formData.value.userInfo.username
			}
		});
		if (formData.value.userInfo.userId) {
			globalProperties.$commonMsg.update();
		} else {
			globalProperties.$commonMsg.add();
		}
		proTable.value.getTableList();
		dialogVisible.value = false;
	});
}
// 关闭弹窗
function handleEditClose() {
	formData.value = JSON.parse(JSON.stringify(resetFormData));
	dialogVisible.value = false;
}
// 修改用户状态
function handleSwitchStatus(userId: number, status: number) {
	globalProperties.$commonMsgBox.switchStatus().then(async () => {
		await switchUserStatusAPI({
			userId
		});
		if (status === 0) {
			globalProperties.$message.success("禁用成功");
		} else {
			globalProperties.$message.success("启用成功");
		}
		proTable.value.getTableList();
	});
	console.log("userId", userId);
}
</script>
