<template>
	<el-dialog v-model="dialogVisible" title="个人信息" width="500px" draggable>
		<el-descriptions :column="2">
			<el-descriptions-item label="" label-class-name="description-label">
				<template #label>
					<div class="description-label">学工号：</div>
				</template>
				<span>{{ userInfo?.username || "--" }}</span>
			</el-descriptions-item>
			<el-descriptions-item label="" label-class-name="description-label">
				<template #label>
					<span class="description-label">姓名：</span>
				</template>
				<span>{{ userInfo?.name || "--" }}</span>
			</el-descriptions-item>
			<el-descriptions-item label="" label-class-name="description-label">
				<template #label>
					<span class="description-label">角色：</span>
				</template>
				<el-tag :color="showRole.color" effect="dark">
					{{ showRole.label }}
				</el-tag>
			</el-descriptions-item>
			<el-descriptions-item label="" label-class-name="description-label">
				<template #label>
					<span class="description-label">用户类型：</span>
				</template>
				<el-tag :color="showRole.color" effect="dark">
					{{ showUserType.label }}
				</el-tag>
			</el-descriptions-item>
			<el-descriptions-item label="" label-class-name="description-label">
				<template #label>
					<span class="description-label">所在系：</span>
				</template>
				<el-tag effect="dark">
					{{ showDepartment.label }}
				</el-tag>
			</el-descriptions-item>
			<el-descriptions-item label="" label-class-name="description-label">
				<template #label>
					<span class="description-label">学院：</span>
				</template>
				<el-tag effect="dark">
					{{ userInfo.school }}
				</el-tag>
			</el-descriptions-item>
			school
		</el-descriptions>
		<template #footer>
			<span class="dialog-footer">
				<el-button @click="dialogVisible = false">取消</el-button>
				<el-button type="primary" @click="dialogVisible = false">确认</el-button>
			</span>
		</template>
	</el-dialog>
</template>

<script setup lang="ts">
import { ref, computed } from "vue";
import { GlobalStore } from "@/stores";
import { roleTypeStatus, departmentTypeList, userTypeStatus } from "@/utils/serviceDict";
//* pinia
const globalStore = GlobalStore();

//* data
const dialogVisible = ref(false);
//* computed
const userInfo = computed(() => {
	return globalStore.userInfo || {};
});
const showRole = computed(() => {
	return (
		roleTypeStatus.find(item => {
			return item.value === globalStore.userInfo.role;
		}) || {
			label: "未知",
			color: "#f56c6c"
		}
	);
});
const showDepartment = computed(() => {
	return (
		departmentTypeList.find(item => {
			return item.value === globalStore.userInfo.department;
		}) || {
			value: "",
			label: "暂无"
		}
	);
});
const showUserType = computed(() => {
	return (
		userTypeStatus.find(item => {
			return item.value === globalStore.userInfo.type;
		}) || {
			value: "",
			label: "暂无"
		}
	);
});
//* method
const openDialog = () => {
	dialogVisible.value = true;
};

defineExpose({ openDialog });
</script>
<style lang="scss" scoped>
.el-tag {
	border-width: 0 !important;
}
.description-label {
	display: inline-block;
	width: 80px !important;
	text-align: right;
}
</style>
