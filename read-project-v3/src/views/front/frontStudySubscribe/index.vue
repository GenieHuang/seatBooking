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
							<el-form-item label="" prop="endTime" :hide-required-asterisk="true">
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
				<el-col :span="12">
					<el-form-item label="预约自习室：" prop="roomId">
						<el-select v-model="formData.roomId" placeholder="请选择自习室" @change="changeStudyRoom">
							<el-option
								v-for="item in studyData"
								:key="item.roomId"
								:label="`${item.rCode}  ${item.roomName} - ${item.num}人`"
								:value="item.roomId"
							></el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col :span="12">
					<el-form-item label="预约座位：" prop="seatId">
						<el-select v-model="formData.seatId" placeholder="请选择座位">
							<el-option v-for="item in realSeatData" :key="item.seatId" :label="item.sCode" :value="item.seatId"></el-option>
						</el-select>
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

<script lang="ts" setup name="frontStudySubscribe">
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
//* data
const formData = ref<RecordManageModule.ReqInsertBook>({
	userId: globalStore.userInfo.userId,
	bookType: 2,
	roomId: undefined,
	seatId: undefined,
	bookStatus: undefined,
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
			message: "请选择预约自习室",
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
	seatId: [
		{
			required: true,
			message: "请选择预约座位",
			trigger: "blur"
		}
	]
};
const studyData = ref<any>([]);
let copySeatData: any[] = [];
const realSeatData = ref<any>([]);
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
		rType: 2,
		bookType: 2,
		bookDate,
		startTime: bookDate + " " + startTime + ":00",
		endTime: bookDate + " " + endTime + ":00"
	});
	const newStudyData: any[] = [];
	(data || []).forEach((item1: any) => {
		const findIndex = newStudyData.findIndex((item2: any) => {
			return item2.roomId === item1.roomId;
		});
		if (findIndex < 0) {
			newStudyData.push(item1);
		}
	});
	studyData.value = newStudyData;
	copySeatData = JSON.parse(JSON.stringify(data)) || [];
}
function changeStudyRoom(value: string) {
	console.log("changeStudyRoom", value);
	realSeatData.value =
		copySeatData.filter((item: any) => {
			return item.roomId === value;
		}) || [];
	console.log("realSeatData", realSeatData);
}
// 提交
function handleSubmit() {
	formRef?.value?.validate(async valid => {
		if (!valid) return;
		const { type, isBookSeat } = globalStore.userInfo;
		if (!type) {
			return globalProperties.$message.error("数据错误，请重新登录");
		} else if (type === 2) {
			return globalProperties.$message.error("仅学生预约自习室，请联系管理员");
		}
		if (isBookSeat !== 0) {
			return globalProperties.$message.error("当前存在已预约/待审核自习室预约，无法再次预约");
		}
		const { bookDate, startTime, endTime } = formData.value;
		await insertBookRecordAPI({
			...formData.value,
			bookStatus: 1,
			startTime: bookDate + " " + startTime + ":00",
			endTime: bookDate + " " + endTime + ":00",
			reviewTime: type === 2 ? dayjs().unix() + "" : undefined
		});
		globalProperties.$message.success("预约成功");
		reset();
		const res = await getUserInfoAPI();
		const { userInfo, ...newParams } = res.data;
		globalStore.setUserInfo({
			...userInfo,
			...newParams
		});
	});
}
// 重置
function reset() {
	formRef.value?.resetFields();
	studyData.value = [];
	realSeatData.value = [];
	copySeatData = [];
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
