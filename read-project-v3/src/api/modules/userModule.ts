import http from "@/api";
import { UserModule } from "@/api/interface/userModule";
// 用户列表
export const getUserListAPI = (params: UserModule.ReqGetUserList) => {
	return http.get<any>(`/user/search`, params, { headers: { noLoading: false } });
};
// 用户信息
export const getUserInfoAPI = () => {
	return http.post<any>("/user", undefined, { headers: { noLoading: false } });
};
// 修改用户状态
export const switchUserStatusAPI = (params: UserModule.ReqSwitchUserStatus) => {
	return http.get<any>("/user/status", params, { headers: { noLoading: false } });
};
// 修改用户信息
export const addOrEditUserInfoAPI = (params: UserModule.ReqAddOrEditUserInfo) => {
	return http.post("/user/insertOrUpdateUser", params, { headers: { noLoading: false } });
};
// 修改密码
export const modifyPasswordByUserIdAPI = (params: UserModule.ReqModifyPasswordByUserId) => {
	return http.post<any>("/user/changePassword", params, { headers: { noLoading: false } });
};
