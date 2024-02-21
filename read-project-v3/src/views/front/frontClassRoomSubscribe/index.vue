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
										end="20:00"
										step="01:00"
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
										start="08:40"
										end="20:40"
										step="01:00"
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
					<el-form-item label="预约教室：" prop="roomId">
						<el-select v-model="formData.roomId" placeholder="请选择教室" filterable clearable>
							<el-option
								v-for="item in classRoomData"
								:key="item.roomId"
								:label="`${item.rCode}  ${item.roomName} - ${item.num}人`"
								:value="item.roomId"
							></el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col :span="24">
					<el-form-item label="活动主题：" prop="topic">
						<el-input v-model="formData.topic" placeholder="请输入活动主题" />
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

<script lang="ts" setup name="frontClassRoomSubscribe">
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
	bookType: 3,
	roomId: undefined,
	bookStatus: undefined,
	reason: "",
	topic: "",
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
			message: "请选择预约教室",
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
			message: "请输入活动主题",
			trigger: "blur"
		}
	],
	reason: [
		{
			required: true,
			message: "请输入预约原因",
			trigger: "blur"
		}
	]
};
const classRoomData = ref<any>([]);
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
		rType: 3,
		bookType: 3,
		bookDate,
		startTime: bookDate + " " + startTime + ":00",
		endTime: bookDate + " " + endTime + ":00"
	});
	classRoomData.value = data || [];
}
// 提交
function handleSubmit() {
	formRef.value?.validate(async valid => {
		if (!valid) return;
		const { type, isBookClass = 1 } = globalStore.userInfo;
		if (!type) {
			return globalProperties.$message.warning("数据错误，请重新登录");
		}
		if (isBookClass !== 0) {
			return globalProperties.$message.error("当前存在已预约/待审核教室预约，无法再次预约");
		}
		const { bookDate, startTime, endTime } = formData.value;
		await insertBookRecordAPI({
			...formData.value,
			bookStatus: type === 2 ? 6 : undefined,
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
		classRoomData.value = [];
	});
}
// 重置
function reset() {
	formRef.value?.resetFields();
	classRoomData.value = [];
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
