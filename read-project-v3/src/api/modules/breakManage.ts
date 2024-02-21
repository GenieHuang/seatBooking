import http from "@/api/index";
import { BreakManageModule } from "@/api/interface/breakManage";

// 违约记录信息
export const getBreakRecordInfoAPI = (params: BreakManageModule.ReqBreakRecordInfo) => {
	return http.get<any>(`/break/search`, params, { headers: { noLoading: false } });
};
// 更改违约状态
export const switchBreakStatusAPI = (params: BreakManageModule.ReqSwitchBreakStatus) => {
	return http.get<any>(`/break/status`, params, { headers: { noLoading: false } });
};
