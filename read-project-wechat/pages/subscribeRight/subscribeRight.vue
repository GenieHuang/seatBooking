<template>
  <view class="subscribe-right">
    <view class="banner"> </view>
    <div class="subscribe-content">
      <!-- 预约类型 -->
      <u-select
        v-model="showObj.bookType"
        :list="bookTypeList"
        @confirm="handleConfirm($event, 'bookType')"
      ></u-select>
      <!-- 预约日期 -->
      <u-calendar
        mode="date"
        v-model="showObj.bookDate"
        min-date="2023-04-29"
        max-date="2024-04-29"
        @change="handleConfirm($event, 'bookDate')"
      ></u-calendar>
      <!-- 开始时间 -->
      <u-select
        v-model="showObj.startTime"
        mode="mutil-column-auto"
        :list="startTimeList"
        @confirm="handleConfirm($event, 'startTime')"
        :default-value="defaultFormData.startTime"
      ></u-select>
      <!-- 结束时间 -->
      <u-select
        v-model="showObj.endTime"
        mode="mutil-column-auto"
        :list="endTimeList"
        @confirm="handleConfirm($event, 'endTime')"
        :default-value="defaultFormData.endTime"
      ></u-select>
      <!-- 预约教室 -->
      <u-select
        v-model="showObj.roomId"
        :list="roomList"
        @confirm="handleConfirm($event, 'roomId')"
        :default-value="defaultFormData.roomId"
      ></u-select>
      <!-- 选择座位 -->
      <u-select
        v-model="showObj.seatId"
        :list="seatList"
        @confirm="handleConfirm($event, 'seatId')"
      ></u-select>
      <u-cell-group :border="false">
        <u-cell-item
          title="预约类型"
          :value="formShowData.bookType"
          size="large"
          center
          index="bookType"
          @click="handleClickCell"
        ></u-cell-item>
      </u-cell-group>
      <div class="wall"></div>
      <u-cell-group border>
        <u-cell-item
          title="预约日期"
          :value="formShowData.bookDate"
          size="large"
          center
          index="bookDate"
          @click="handleClickCell"
        ></u-cell-item>
        <u-cell-item
          title="开始时间"
          :value="formShowData.startTime"
          size="large"
          center
          index="startTime"
          @click="handleClickCell"
        ></u-cell-item>
        <u-cell-item
          title="结束时间"
          :value="formShowData.endTime"
          size="large"
          center
          index="endTime"
          @click="handleClickCell"
        ></u-cell-item>
      </u-cell-group>
      <div class="wall"></div>
      <u-cell-group :border="false">
        <u-cell-item
          :title="showBookType[formData.bookType]"
          :value="formShowData.roomId"
          size="large"
          center
          index="roomId"
          @click="handleClickCell"
        ></u-cell-item>
        <u-cell-item
          v-if="formData.bookType === 2"
          title="选择座位"
          :value="formShowData.seatId"
          size="large"
          center
          index="seatId"
          @click="handleClickCell"
        ></u-cell-item>
      </u-cell-group>
      <div v-if="formData.bookType === 2 && formData.rUrl">
        <u-image width="100%" height="200rpx" :src="formData.rUrl"></u-image>
      </div>
      <div class="wall"></div>
      <view class="form-content" v-if="formData.bookType !== 2">
        <u-field
          v-model="formData.topic"
          :label="showTopic[formData.bookType]"
          placeholder="请输入"
        >
        </u-field>
        <u-field
          v-model="formData.reason"
          label="预约原因"
          placeholder="请输入"
        >
        </u-field>
        <u-field
          v-if="formData.bookType === 1 && userInfo?.type === 1"
          v-model="formData.advisor"
          label="指导老师"
          placeholder="请输入"
        >
        </u-field>
      </view>
      <div class="wall"></div>
      <div class="operate-btn">
        <u-button @click="reset">重置</u-button>
        <div class="wall"></div>
        <u-button class="yuyue-btn" type="primary" @click="handleSubmit"
          >立即预约</u-button
        >
      </div>
    </div>
  </view>
</template>

<script setup>
import { ref } from "vue";
import { subscribeTypeList } from "/utils/enum.js";
import { onShow } from "@dcloudio/uni-app";
import {
  searchBookedRoomAPI,
  insertBookRecordAPI,
  getUserInfoApi,
} from "/request/index.js";
let userInfo = ref({});
//* 表单参数
const formData = ref({
  bookType: 1,
  bookDate: undefined,
  startTime: undefined,
  endTime: undefined,
  roomId: undefined,
  seatId: undefined,
  rUrl: undefined,
  topic: undefined,
  reason: undefined,
  advisor: undefined,
});
const showBookType = {
  1: "预约会议室",
  2: "预约自习室",
  3: "预约教室",
};
const showTopic = {
  1: "会议主题",
  3: "活动主题",
};
// cell展示数据内容
const formShowData = ref({
  bookType: "会议室预约",
  bookDate: undefined,
  startTime: undefined,
  endTime: undefined,
  roomId: undefined,
  seatId: undefined,
});
// 下拉框控制
const showObj = ref({
  bookType: false,
  bookDate: false,
  startTime: false,
  endTime: false,
  roomId: false,
  seatId: false,
});
// 默认选中的时间
const defaultFormData = ref({
  startTime: [0, 0],
  endTime: [0, 0],
  roomId: [],
});
// 可选类型
const bookTypeList = ref([]);
// 可选小时
const hourList = [
  {
    label: "08 : ",
    value: "08",
    extra: 0,
  },
  {
    label: "09 : ",
    value: "09",
    extra: 2,
  },
  {
    label: "10 : ",
    value: "10",
    extra: 4,
  },
  {
    label: "11 : ",
    value: "11",
    extra: 6,
  },
  {
    label: "12 : ",
    value: "12",
    extra: 8,
  },
  {
    label: "13 : ",
    value: "13",
    extra: 10,
  },
  {
    label: "14 : ",
    value: "14",
    extra: 12,
  },
  {
    label: "15 : ",
    value: "15",
    extra: 14,
  },
  {
    label: "16 : ",
    value: "16",
    extra: 16,
  },
  {
    label: "17 : ",
    value: "17",
    extra: 18,
  },
  {
    label: "18 : ",
    value: "18",
    extra: 20,
  },
  {
    label: "19 : ",
    value: "19",
    extra: 22,
  },
  {
    label: "20 : ",
    value: "20",
    extra: 24,
  },
  {
    label: "21 : ",
    value: "21",
    extra: 26,
  },
  {
    label: "22 : ",
    value: "22",
    extra: 28,
  },
];
// 可选分钟
const miniteList = [
  {
    label: "00",
    value: "00",
    extra: 0,
  },
  {
    label: "30",
    value: "30",
    extra: 1,
  },
];
// 教室可选小时
const hourList2 = [
  {
    label: "08 : ",
    value: "08",
    extra: 0,
  },
  {
    label: "09 : ",
    value: "09",
    extra: 60,
  },
  {
    label: "10 : ",
    value: "10",
    extra: 120,
  },
  {
    label: "11 : ",
    value: "11",
    extra: 180,
  },
  {
    label: "12 : ",
    value: "12",
    extra: 240,
  },
  {
    label: "13 : ",
    value: "13",
    extra: 300,
  },
  {
    label: "14 : ",
    value: "14",
    extra: 360,
  },
  {
    label: "15 : ",
    value: "15",
    extra: 420,
  },
  {
    label: "16 : ",
    value: "16",
    extra: 480,
  },
  {
    label: "17 : ",
    value: "17",
    extra: 540,
  },
  {
    label: "18 : ",
    value: "18",
    extra: 600,
  },
  {
    label: "19 : ",
    value: "19",
    extra: 660,
  },
  {
    label: "20 : ",
    value: "20",
    extra: 720,
  },
  {
    label: "21 : ",
    value: "21",
    extra: 780,
  },
];
// 教室可选分钟
const miniteList2 = [
  {
    label: "00",
    value: "00",
    extra: 0,
  },
  {
    label: "40",
    value: "40",
    extra: 40,
  },
];
// 开始时间
const startTimeList = ref([]);
// 结束时间
const endTimeList = ref([]);
// 教室列表
const roomList = ref([]);
// 座位列表
const seatList = ref([]);
const copySeatList = ref([]);
onShow(() => {
  initTimeList();
  userInfo.value = uni.getStorageSync("userInfo");
  if (userInfo.value.type === 1) {
    bookTypeList.value = subscribeTypeList.filter((item) => {
      return [1, 2].includes(item.value);
    });
  } else if (userInfo.value.type === 2) {
    bookTypeList.value = subscribeTypeList.filter((item) => {
      return [1, 3].includes(item.value);
    });
  }
});
// 页面加载时初始化开始时间
function initTimeList() {
  if (formData.value.bookType !== 3) {
    startTimeList.value = hourList
      .filter((item1) => {
        return item1.value !== "22";
      })
      .map((item2) => {
        return {
          ...item2,
          children: miniteList,
        };
      });
  } else {
    startTimeList.value = hourList2.map((item1) => {
      return {
        ...item1,
        children: [
          {
            label: "00",
            value: "00",
            extra: 0,
          },
        ],
      };
    });
  }
}
// 选择开始时间初始化结束时间
function initEndTimeList(event) {
  if (formData.value.bookType !== 3) {
    const total = (event[0]?.extra || 0) + (event[1]?.extra || 0);
    const newEndTimeList = [];
    endTimeList.value = [];
    hourList.forEach((item1) => {
      const children = miniteList.filter((item2) => {
        return (
          (item1.extra + item2.extra > total && item1.value !== "22") ||
          (item1.value === "22" && item2.value !== "30")
        );
      });
      newEndTimeList.push({
        ...item1,
        children: children || [],
      });
    });
    endTimeList.value = newEndTimeList.filter((item) => {
      return item && item.children && item.children.length > 0;
    });
  } else {
    const total = (event[0]?.extra || 0) + (event[1]?.extra || 0);
    const newEndTimeList = [];
    endTimeList.value = [];
    hourList2.forEach((item1) => {
      const children = [
        {
          label: "40",
          value: "40",
          extra: 40,
        },
      ].filter((item2) => {
        return item1.extra + item2.extra > total;
      });
      newEndTimeList.push({
        ...item1,
        children: children || [],
      });
    });
    endTimeList.value = newEndTimeList.filter((item) => {
      return item && item.children && item.children.length > 0;
    });
  }
}

function handleClickCell(property) {
  showObj.value[property] = true;
}
// 点击确认
async function handleConfirm(event, type) {
  console.log("event::", event);
  switch (type) {
    case "bookType":
      formData.value.bookType = event[0].value;
      formShowData.value.bookType = event[0].label;
      reset();
      initTimeList();
      await searchBookedRoom();
      break;
    case "bookDate":
      formData.value.bookDate = event.result;
      formShowData.value.bookDate = event.result;
      formShowData.value.startTime = undefined;
      formShowData.value.endTime = undefined;
      formShowData.value.roomId = undefined;
      formShowData.value.seatId = undefined;
      formData.value.startTime = undefined;
      formData.value.endTime = undefined;
      formData.value.roomId = undefined;
      formData.value.seatId = undefined;
      formData.value.rUrl = undefined;
      endTimeList.value = [];
      roomList.value = [];
      seatList.value = [];
      await searchBookedRoom();
      break;
    case "startTime":
      formShowData.value.endTime = undefined;
      formShowData.value.roomId = undefined;
      formShowData.value.seatId = undefined;
      formData.value.endTime = undefined;
      formData.value.roomId = undefined;
      formData.value.seatId = undefined;
      formData.value.rUrl = undefined;
      endTimeList.value = [];
      roomList.value = [];
      seatList.value = [];
      initEndTimeList(event);
      let index1 = 0;
      let index2 = 0;
      startTimeList.value.forEach((item1, idx1) => {
        if (item1.value === event[0].value) {
          index1 = idx1;
        }
        if (item1.children && item1.children.length > 0) {
          item1.children.forEach((item2, idx2) => {
            if (item2.value === event[1].value) {
              index2 = idx2;
            }
          });
        }
      });
      defaultFormData.value.startTime = [index1, index2];
      formData.value.startTime = event[0].value + ":" + event[1].value;
      formShowData.value.startTime = event[0].label + event[1].label;
      await searchBookedRoom();
      break;
    case "endTime":
      formShowData.value.roomId = undefined;
      formShowData.value.seatId = undefined;
      formData.value.roomId = undefined;
      formData.value.seatId = undefined;
      formData.value.rUrl = undefined;
      roomList.value = [];
      seatList.value = [];
      let index3 = 0;
      let index4 = 0;
      endTimeList.value.forEach((item1, idx1) => {
        if (item1.value === event[0].value) {
          index3 = idx1;
        }
        if (item1.children && item1.children.length > 0) {
          item1.children.forEach((item2, idx2) => {
            if (item2.value === event[1].value) {
              index4 = idx2;
            }
          });
        }
      });
      defaultFormData.value.endTime = [index3, index4];
      formData.value.endTime = event[0].value + ":" + event[1].value;
      formShowData.value.endTime = event[0].label + event[1].label;
      await searchBookedRoom();
      break;
    case "roomId":
      formShowData.value.seatId = undefined;
      formData.value.seatId = undefined;
      formData.value.rUrl = event[0]?.extra?.rUrl
        ? "http://localhost:9090/pic/getImage?fileName=" + event[0]?.extra?.rUrl
        : undefined;
      seatList.value = [];
      formData.value.roomId = event[0].value;
      formShowData.value.roomId = event[0].label;
      defaultFormData.value.roomId = [
        roomList.value.findIndex((item) => item.value === event[0].value) ||
          undefined,
      ];
      if (formData.value.bookType === 2) {
        formData.value.seatId = undefined;
        formShowData.value.seatId = undefined;
        defaultFormData.value.seatId = [];
        seatList.value = (
          copySeatList.value.filter((item) => {
            return item.roomId === event[0].value;
          }) || []
        ).map((item2) => {
          const { seatId, sCode } = item2;
          return {
            label: sCode,
            value: seatId,
            extra: { ...item2 },
          };
        });
      }
      break;
    case "seatId":
      formData.value.seatId = event[0].value;
      formShowData.value.seatId = event[0].label;
      defaultFormData.value.seatId = [
        seatList.value.findIndex((item) => item.value === event[0].value) ||
          undefined,
      ];
      break;
  }
}
// 获取可选教室
async function searchBookedRoom() {
  const { bookType, bookDate, startTime, endTime } = formData.value;
  formData.value.roomId = undefined;
  formShowData.value.roomId = undefined;
  roomList.value = [];
  if (!bookType || !bookDate || !startTime || !endTime) {
    formData.roomId = undefined;
    return;
  }
  const data = await searchBookedRoomAPI({
    rType: bookType,
    bookType: bookType,
    bookDate,
    startTime: bookDate + " " + startTime + ":00",
    endTime: bookDate + " " + endTime + ":00",
  });
  if (bookType === 1) {
    roomList.value = data
      .filter((item1) => {
        return (
          item1.isResearch ||
          (!item1.isResearch && item1.roomDepart === userInfo.value.department)
        );
      })
      .map((item2) => {
        const { rCode, roomName, roomId, num } = item2;
        return {
          label:
            formData.value.bookType === 1
              ? `${rCode} ${roomName}`
              : `${rCode} ${roomName}——${num}人`,
          value: roomId,
          extra: {
            ...item2,
          },
        };
      });
  } else if (bookType === 2) {
    const newStudyData = [];
    copySeatList.value = data || [];
    (data || []).forEach((item1) => {
      const findIndex = newStudyData.findIndex((item2) => {
        return item2.roomId === item1.roomId;
      });
      if (findIndex < 0) {
        newStudyData.push(item1);
      }
    });

    roomList.value = newStudyData.map((item2) => {
      const { rCode, roomName, roomId, num } = item2;
      return {
        label: `${rCode} ${roomName}——${num}人`,
        value: roomId,
        extra: {
          ...item2,
        },
      };
    });
  } else if (bookType === 3) {
    roomList.value = data.map((item2) => {
      const { rCode, roomName, roomId, num } = item2;
      return {
        label: `${rCode} ${roomName}——${num}人`,
        value: roomId,
        extra: {
          ...item2,
        },
      };
    });
  }
}

function reset() {
  formData.value = {
    ...formData.value,
    bookDate: undefined,
    startTime: undefined,
    endTime: undefined,
    roomId: undefined,
    seatId: undefined,
    rUrl: undefined,
    topic: undefined,
    reason: undefined,
    advisor: undefined,
  };
  endTimeList.value = [];
  roomList.value = [];
  seatList.value = [];
  formShowData.value = {
    ...formShowData.value,
    bookDate: undefined,
    startTime: undefined,
    endTime: undefined,
    roomId: undefined,
    seatId: undefined,
    topic: undefined,
    reason: undefined,
  };
  defaultFormData.value = {
    startTime: [0, 0],
    endTime: [0, 0],
    roomId: [],
  };
}
// 提交
async function handleSubmit() {
  const {
    bookType,
    bookDate,
    startTime,
    endTime,
    advisor,
    topic,
    roomId,
    seatId,
    reason,
  } = formData.value;
  let currentTime = new Date();
  const year = currentTime.getFullYear();
  const month =
    currentTime.getMonth() < 10
      ? "0" + currentTime.getMonth()
      : currentTime.getMonth();
  const date =
    currentTime.getDate() < 10
      ? "0" + currentTime.getDate()
      : currentTime.getDate();
  const hours =
    currentTime.getHours() < 10
      ? "0" + currentTime.getHours()
      : currentTime.getHours();
  const minutes =
    currentTime.getMinutes() < 10
      ? "0" + currentTime.getMinutes()
      : currentTime.getMinutes();
  const seconds =
    currentTime.getSeconds() < 10
      ? "0" + currentTime.getSeconds()
      : currentTime.getSeconds();
  currentTime = `${year}-${month}-${date} ${hours}:${minutes}:${seconds}`;
  if (bookType === 1) {
    if (userInfo.value.isBookMeet !== 0) {
      return uni.showToast({
        type: "error",
        title: "当前存在已预约/待审核会议室预约，无法再次预约",
      });
    }
    if (!bookType || !bookDate || !startTime || !endTime || !topic || !roomId) {
      return uni.showToast({
        type: "warning",
        title: "请补全信息",
      });
    }
    await insertBookRecordAPI({
      userId: userInfo.value.userId,
      rType: bookType,
      bookType,
      topic,
      reason,
      roomId,
      bookStatus:
        userInfo.value.type === 2
          ? 1
          : userInfo.value.type === 1
          ? 6
          : undefined,
      advisor: userInfo.value.type === 2 ? undefined : formData.value.advisor,
      bookDate,
      startTime: bookDate + " " + startTime + ":00",
      endTime: bookDate + " " + endTime + ":00",
      reviewTime: userInfo.value.type === 2 ? currentTime : undefined,
    });
    uni.showToast({
      type: "success",
      title: "预约成功",
    });
    reset();
  } else if (bookType === 2) {
    if (userInfo.value.isBookSeat !== 0) {
      return uni.showToast({
        type: "error",
        title: "当前存在已预约/待审核自习室预约，无法再次预约",
      });
    }
    if (
      !bookType ||
      !bookDate ||
      !startTime ||
      !endTime ||
      !roomId ||
      !seatId
    ) {
      return uni.showToast({
        type: "warning",
        title: "请补全信息",
      });
    }
    await insertBookRecordAPI({
      userId: userInfo.value.userId,
      bookType,
      roomId,
      seatId,
      bookStatus: 1,
	  bookDate,
      startTime: bookDate + " " + startTime + ":00",
      endTime: bookDate + " " + endTime + ":00",
      reviewTime: userInfo.value.type === 2 ? currentTime : undefined,
    });
    uni.showToast({
      type: "success",
      title: "预约成功",
    });
    reset();
  } else if (bookType === 3) {
    if (userInfo.value.isBookClass !== 0) {
      return uni.showToast({
        type: "warning",
        title: "当前存在已预约/待审核教室预约，无法再次预约",
      });
    }
    if (
      !bookType ||
      !bookDate ||
      !startTime ||
      !endTime ||
      !roomId ||
      !topic ||
      !reason
    ) {
      return uni.showToast({
        type: "warning",
        title: "请补全信息",
      });
    }
    await insertBookRecordAPI({
      userId: userInfo.value.userId,
      bookType,
      roomId,
      topic,
      reason,
      bookStatus: userInfo.value.type === 2 ? 6 : undefined,
	  bookDate,
      startTime: bookDate + " " + startTime + ":00",
      endTime: bookDate + " " + endTime + ":00",
      reviewTime: userInfo.value.type === 2 ? currentTime : undefined,
    });
    uni.showToast({
      type: "success",
      title: "预约成功",
    });
    reset();
  }
  const data2 = await getUserInfoApi();
  const { userInfo: userInfo2, ...otherInfo } = data2;
  uni.setStorageSync("userInfo", {
    ...userInfo2,
    ...otherInfo,
  });
}
</script>

<style lang="scss" scoped>
.subscribe-right {
  overflow: auto;
  width: 100vw;
  height: 100vh;
  background: #ece9e9;

  .banner {
    position: absolute;
    box-sizing: border-box;
    width: 100%;
    height: 260rpx;
    padding: 40rpx;
    border-bottom-left-radius: 50rpx;
    border-bottom-right-radius: 50rpx;
    background-color: #3771bb;
  }

  .subscribe-content {
    box-sizing: border-box;
    width: 100vw;
    padding: 0 40rpx;
    background-color: transparent;
    transform: translateY(40rpx);
  }

  .form-content {
    background-color: #fff;
  }
}

::v-deep.u-cell__value {
  color: #4360ef !important;
}

.wall {
  height: 20rpx;
  background-color: transparent;
}

::v-deep.yuyue-btn {
  .u-btn--primary {
    background-color: #0453a4 !important;
  }
}
</style>
