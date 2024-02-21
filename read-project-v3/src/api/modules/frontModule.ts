import http from "@/api/index";
import { FrontModule } from "@/api/interface/frontModule";

// 预约列表
export const getSubscribeList = (params: FrontModule.ReqSubscribeInfo) => {
	return http.get<any>("/book/frontSearch", params, { headers: { noLoading: false } });
};
