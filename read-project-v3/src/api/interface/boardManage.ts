import { ReqPage } from "@/api/interface/index";

//ts的命名空间
export namespace BoardManageModule {
	// 请求参数：公告列表
	export interface ReqBoardInfo extends ReqPage {
		title?: string; //占位
	}
	// 请求参数：新增公告
	export interface ReqAddBoardInfo {
		title: string;
		msg: string;
		bUrl: string;
	}
}
