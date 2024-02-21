<template>
	<div class="card flex-drc-cln flex h100">
		<el-form :model="formData" ref="formRef" :rules="formRules" label-width="100px">
			<el-form-item label="标题" prop="title">
				<el-input v-model="formData.title" placeholder="请输入公告标题" />
			</el-form-item>
			<el-form-item label="内容" prop="msg">
				<el-input type="textarea" v-model="formData.msg" :rows="6" placeholder="请输入公告内容" />
			</el-form-item>
			<el-form-item label="插图">
				<el-upload class="avatar-uploader" :action="picUrl" :show-file-list="false" :on-success="handlePicUpload">
					<img v-if="formData.bUrl" :src="globalStore.baseUrl + '/pic/getImage/?fileName=' + formData.bUrl" class="avatar" />
					<el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
				</el-upload>
			</el-form-item>
			<el-form-item>
				<el-button @click="goback">取 消</el-button>
				<el-button type="primary" @click="handleSubmit">保 存</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script lang="ts" setup>
import { ref, computed, reactive } from "vue";
import { useRouter } from "vue-router";
import { GlobalStore } from "@/stores";
import { FormInstance, UploadProps } from "element-plus";
import { useGetGlobalProperties } from "@/utils/common/useGetGlobalProperties";
import { BoardManageModule } from "@/api/interface/boardManage";
import { addBoardAPI } from "@/api/modules/boardManage";
//* 全局变量
const globalProperties = useGetGlobalProperties();
//* pinia
const globalStore = GlobalStore();
//* router
const router = useRouter();
//* ref
const formRef = ref<FormInstance>();
//* data
const formData = ref<BoardManageModule.ReqAddBoardInfo>({
	title: "",
	msg: "",
	bUrl: ""
});
// 校验
const formRules = reactive({
	title: [{ required: true, message: "请输入公告标题", trigger: "blur" }],
	msg: [{ required: true, message: "请输入公告内容", trigger: "blur" }]
});
//* computed
const picUrl = computed(() => {
	return globalStore.baseUrl + "/pic/upload";
});
//* method
const handlePicUpload: UploadProps["onSuccess"] = ({ data }) => {
	formData.value.bUrl = data;
};
// 取消返回公告列表
function goback() {
	globalProperties.$commonMsgBox.customer("警告", "此操作将返回公告列表页，且当前页面的数据将会丢失，是否继续？").then(() => {
		router.push({
			path: "/boardManage/list"
		});
	});
}
function handleSubmit() {
	formRef.value?.validate(async valid => {
		if (!valid) return;
		await addBoardAPI(formData.value);
		globalProperties.$commonMsgBox
			.customer("提示", "新增公告成功，是否继续新增？", {
				type: "success"
			})
			.then(() => {
				formRef.value?.resetFields();
			})
			.catch(() => {
				router.push({
					path: "/boardManage/list"
				});
			});
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
