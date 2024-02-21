import http from "@/api";
import { SettingModule } from "@/api/interface/setting";

// 教室列表：会议室/自习室
export const getRoomListByTypeAPI = (params: SettingModule.ReqRoomInfo) => {
	return http.get<any>(`/room/search`, params, { headers: { noLoading: false } });
};
// 新增或更新会议室
export const addOrEditMeetingRoomAPI = (params: SettingModule.ReqAddOrEditMeetingRoomInfo) => {
	return http.get<any>("/room/auditMeet", params, { headers: { noLoading: true } });
};
// 新增或更新自习室
export const addOrEditStudyRoomAPI = (params: SettingModule.ReqAddOrEditStudyRoomInfo) => {
	return http.get<any>("/room/auditStudy", params, { headers: { noLoading: true } });
};
// 新增或更新教室
export const addOrEditClassRoomAPI = (params: SettingModule.ReqAddOrEditStudyRoomInfo) => {
	return http.get<any>("/room/auditClassroom", params, { headers: { noLoading: true } });
};
// 更新会议室/自习室/教室状态
export const switchRoomStatusAPI = (params: SettingModule.ReqSwitchRoomStatus) => {
	return http.get<any>("/room/status", params, { headers: { noLoading: true } });
};
