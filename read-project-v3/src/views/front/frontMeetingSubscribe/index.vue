<template>
	<div class="meeting card h100 pt30">
		<el-form :model="formData" :rules="formRules" ref="formRef" label-width="120px">
			<el-row>
				<el-col :span="24">
					<el-form-item label="预约日期：" prop="bookDate">
						<el-date-picker
							class="w100"
							v-model="formData.bookDate"
							type="date"
							format="YYYY-MM-DD"
							value-format="YYYY-MM-DD"
							placeholder="请选择预约日期"
							:disabled-date="disableBookDate"
							:clearable="false"
							@change="searchBookedRoom"
						/>
					</el-form-item>
				</el-col>
				<el-col :span="24">
					<el-row>
						<el-col :span="24">
							<el-form-item label="预约时间：" prop="startTime">
								<div class="w100 flex">
									<span style="min-width: 70px; color: #606266">开始时间 </span>
									<el-time-select
										v-model="formData.startTime"
										start="08:00"
										end="21:30"
										step="00:30"
										:max-time="formData.endTime"
										format="HH:mm"
										placeholder="预约开始时间"
										@change="searchBookedRoom"
										:clearable="false"
									/>
								</div>
							</el-form-item>
						</el-col>
						<el-col :span="24">
							<el-form-item prop="endTime">
								<div class="w100 flex">
									<span style="min-width: 70px; color: #606266">结束时间 </span>
									<el-time-select
										v-model="formData.endTime"
										start="08:30"
										end="22:00"
										step="00:30"
										:min-time="formData.startTime"
										format="HH:mm"
										placeholder="预约结束时间"
										:clearable="false"
										@change="searchBookedRoom"
									/>
								</div>
							</el-form-item>
						</el-col>
					</el-row>
				</el-col>

				<el-col :span="24">
					<el-form-item label="预约会议室：" prop="roomId">
						<el-select v-model="formData.roomId" placeholder="请选择会议室" filterable clearable>
							<el-option
								v-for="item in meetingData"
								:key="item.roomId"
								:label="`${item.rCode}  ${item.roomName}`"
								:value="item.roomId"
							></el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col :span="24">
					<el-form-item label="会议主题：" prop="topic">
						<el-input v-model="formData.topic" placeholder="请输入会议主题" />
					</el-form-item>
				</el-col>
				<el-col :span="24">
					<el-form-item label="预约原因：" prop="reason">
						<el-input
							type="textarea"
							v-model="formData.reason"
							maxlength="50"
							show-word-limit
							:rows="6"
							placeholder="请输入预约原因"
							resize="none"
						/>
					</el-form-item>
				</el-col>
				<el-col :span="24" v-if="globalStore?.userInfo?.type === 1">
					<el-form-item label="指导老师：" prop="advisor">
						<el-input v-model="formData.advisor" placeholder="请输入指导老师" />
					</el-form-item>
				</el-col>
			</el-row>
			<el-form-item>
				<div class="w100 flex">
					<el-button style="margin-right: 30px" size="large" @click="reset">重置</el-button>
					<el-button type="primary" size="large" @click="handleSubmit">提交</el-button>
				</div>
			</el-form-item>
		</el-form>
	</div>
</template>

<script lang="ts" setup name="frontMeetingSubscribe">
import { RecordManageModule } from "@/api/interface/recordManage";
import { searchBookedRoomAPI, insertBookRecordAPI } from "@/api/modules/recordManage";
import { ref, onMounted } from "vue";
import dayjs from "dayjs";
import { GlobalStore } from "@/stores";
import { useGetGlobalProperties } from "@/utils/common/useGetGlobalProperties";
import { FormInstance } from "element-plus";
import { getUserInfoAPI } from "@/api/modules/userModule";
//* 全局变量
const globalProperties = useGetGlobalProperties();
//*pinia
const globalStore = GlobalStore();
//* ref
const formRef = ref<FormInstance>();
//*data
const formData = ref<RecordManageModule.ReqInsertBook>({
	userId: globalStore.userInfo.userId,
	bookType: 1,
	roomId: undefined,
	bookStatus: undefined,
	reason: "",
	topic: "",
	advisor: undefined,
	bookDate: "",
	startTime: "",
	endTime: "",
	reviewTime: undefined
});
const formRules = {
	bookDate: [
		{
			required: true,
			message: "请选择预约日期",
			trigger: ["change", "blur"]
		}
	],
	roomId: [
		{
			required: true,
			message: "请选择预约会议室",
			trigger: ["change", "blur"]
		}
	],
	startTime: [
		{
			required: true,
			message: "请选择预约开始时间",
			trigger: ["change", "blur"]
		}
	],
	endTime: [
		{
			required: true,
			message: "请选择预约结束时间",
			trigger: ["change", "blur"]
		}
	],
	topic: [
		{
			required: true,
			message: "请输入会议主题",
			trigger: "blur"
		}
	],
	reason: [
		{
			required: true,
			message: "请输入预约原因",
			trigger: "blur"
		}
	],
	advisor: [
		{
			required: true,
			message: "请输入指导老师",
			trigger: "blur"
		}
	]
};
const meetingData = ref<any>([]);
//* method
// 禁用日期
function disableBookDate(date: any) {
	const selectTime = dayjs(date).unix();
	const currentTime = dayjs(dayjs().format("YYYY-MM-DD")).unix();
	return selectTime < currentTime;
}
// 获取可选教室
async function searchBookedRoom() {
	const { bookDate, startTime, endTime } = formData.value;
	formData.value.roomId = undefined;
	if (!bookDate || !startTime || !endTime) {
		return;
	}
	const { data } = await searchBookedRoomAPI({
		rType: 1,
		bookType: 1,
		bookDate,
		startTime: bookDate + " " + startTime + ":00",
		endTime: bookDate + " " + endTime + ":00"
	});

	meetingData.value = (data || []).filter((item: any) => {
		return item.isResearch || (!item.isResearch && item.roomDepart === globalStore.userInfo?.department);
	});
}
// 提交
function handleSubmit() {
	formRef.value?.validate(async valid => {
		if (!valid) return;

		const { type, isBookMeet = 1 } = globalStore.userInfo;
		if (!type) {
			return globalProperties.$message.warning("数据错误，请重新登录");
		}
		if (isBookMeet !== 0) {
			return globalProperties.$message.error("当前存在已预约/待审核会议室预约，无法再次预约");
		}
		const { bookDate, startTime, endTime } = formData.value;
		await insertBookRecordAPI({
			...formData.value,
			bookStatus: type === 2 ? 1 : type === 1 ? 6 : undefined,
			advisor: type === 2 ? undefined : formData.value.advisor,
			startTime: bookDate + " " + startTime + ":00",
			endTime: bookDate + " " + endTime + ":00",
			reviewTime: type === 2 ? dayjs().format("YYYY-MM-DD HH:mm:ss") + "" : undefined
		});
		globalProperties.$message.success("预约成功");
		const res = await getUserInfoAPI();
		const { userInfo, ...newParams } = res.data;
		globalStore.setUserInfo({
			...userInfo,
			...newParams
		});
		formRef.value?.resetFields();
		meetingData.value = [];
	});
}
// 重置
function reset() {
	formRef.value?.resetFields();
	meetingData.value = [];
}

onMounted(async () => {
	const res = await getUserInfoAPI();
	const { userInfo, ...newParams } = res.data;
	globalStore.setUserInfo({
		...userInfo,
		...newParams
	});
});
</script>

<style lang="scss" scoped>
.el-form {
	width: 600px;
	margin-left: 100px;
}
.el-select {
	width: 100%;
}
</style>
