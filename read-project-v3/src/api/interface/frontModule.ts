import { ReqPage } from "@/api/interface/index";

export namespace FrontModule {
	// 请求参数：预约记录列表
	export interface ReqSubscribeInfo extends ReqPage {
		bookId?: string;
		bookStatus?: number;
		userId: number;
	}
}
