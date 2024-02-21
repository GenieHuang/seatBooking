import http from "@/api/index";
import { RecordManageModule } from "@/api/interface/recordManage";

// 预约记录信息
export const getBookRecordInfoAPI = (params: RecordManageModule.ReqSubcribeRecordInfo) => {
	return http.get<any>(`/book/search`, params, { headers: { noLoading: false } });
};
// 取消预约或修改预约状态
export const switchSubscribeStatusAPI = (params: RecordManageModule.ReqSubscribeStatusModify) => {
	return http.get<any>(`/book/status`, params, { headers: { noLoading: false } });
};
// 新增预约
export const insertBookRecordAPI = (params: RecordManageModule.ReqInsertBook) => {
	return http.post<any>(`/book/insertBook`, params, { headers: { noLoading: false } });
};
// 查询已预约房间
export const searchBookedRoomAPI = (params: RecordManageModule.ReqBookedRoom) => {
	return http.get<any>(`/book/ableBookRoom`, params, { headers: { noLoading: false } });
};
