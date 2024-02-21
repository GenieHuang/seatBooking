import { ReqPage } from "@/api/interface/index";

export namespace BreakManageModule {
	// 请求参数：违约记录
	export interface ReqBreakRecordInfo extends ReqPage {
		username: string;
		bookId: string;
		name: string;
	}
	// 改变违约状态
	export interface ReqSwitchBreakStatus {
		breakId: number;
	}
}
