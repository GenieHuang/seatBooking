<template>
	<el-dialog v-model="dialogVisible" title="修改密码" width="500px" draggable :before-close="closeDialog">
		<el-form :model="formData" ref="formRef" :rules="formRules" label-width="90px">
			<el-form-item prop="oldPassword" label="旧密码：">
				<el-input type="password" show-password v-model="formData.oldPassword" placeholder="请输入旧密码" />
			</el-form-item>
			<el-form-item prop="newPassword" label="新密码：">
				<el-input type="password" show-password v-model="formData.newPassword" placeholder="请输入新密码" />
			</el-form-item>
			<el-form-item prop="reNewPassword" label="确认密码：">
				<el-input type="password" show-password v-model="formData.reNewPassword" placeholder="请再次输入新密码" />
			</el-form-item>
		</el-form>
		<template #footer>
			<span class="dialog-footer">
				<el-button @click="closeDialog">取消</el-button>
				<el-button type="primary" @click="handleSubmit">确认</el-button>
			</span>
		</template>
	</el-dialog>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { GlobalStore } from "@/stores";
import { UserModule } from "@/api/interface/userModule";
import { modifyPasswordByUserIdAPI } from "@/api/modules/userModule";
import { FormInstance } from "element-plus";
import { useGetGlobalProperties } from "@/utils/common/useGetGlobalProperties";
//*全局变量
const globalProperties = useGetGlobalProperties();
//* pinia
const globalStore = GlobalStore();
interface FormData extends UserModule.ReqModifyPasswordByUserId {
	reNewPassword: string;
}
//* ref
const formRef = ref<FormInstance>();
//* data
const dialogVisible = ref(false);
const formData = ref<FormData>({
	userId: -1,
	oldPassword: "",
	newPassword: "",
	reNewPassword: ""
});
const formRules = {
	oldPassword: [{ required: true, message: "请输入旧密码", trigger: "blur" }],
	newPassword: [
		{
			required: true,
			message: "请输入新密码",
			trigger: "blur"
		}
	],
	reNewPassword: [
		{
			required: true,
			message: "再次请输入新密码",
			trigger: "blur"
		},
		{
			validator: confirmCheckPassword,
			trigger: "blur"
		}
	]
};
//* method
// openDialog
const openDialog = () => {
	dialogVisible.value = true;
};
function confirmCheckPassword(rule: any, value: any, callback: any) {
	if (formData.value.newPassword !== formData.value.reNewPassword && formData.value.newPassword) {
		return callback(new Error("两次密码不一致，请再次确认"));
	}
	callback();
}
function handleSubmit() {
	formRef.value?.validate(async valid => {
		if (!valid) return;
		const { reNewPassword, ...params } = formData.value;
		console.log("re", reNewPassword);
		await modifyPasswordByUserIdAPI({
			...params,
			userId: globalStore.userInfo.userId
		});
		globalProperties.$message.success("修改密码成功");
		dialogVisible.value = false;
		formRef.value?.resetFields();
	});
}
function closeDialog() {
	formRef.value?.resetFields();
	dialogVisible.value = false;
}
defineExpose({ openDialog });
</script>
