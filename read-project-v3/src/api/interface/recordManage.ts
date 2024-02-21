import { ReqPage } from "@/api/interface/index";

//* 开启命名空间，防止导入不同包的重名变量/方法覆盖
export namespace RecordManageModule {
	// 请求参数：预约记录
	export interface ReqSubcribeRecordInfo extends ReqPage {
		username?: string;
		name?: string;
		bookType?: number;
		bookStatus?: number;
	}
	// 请求参数：取消预约或修改预约状态
	export interface ReqSubscribeStatusModify {
		bookId: string;
		bookStatus: number;
		userId?: number;
		reason?: string;
	}
	// 新增预约记录
	export interface ReqInsertBook {
		userId?: string;
		bookType?: number;
		roomId?: number;
		seatId?: number;
		bookDate?: string;
		startTime?: string;
		endTime?: string;
		bookStatus?: number;
		reviewTime?: string;
		reason?: string;
		topic?: string;
		advisor?: string;
	}
	// 查询已经预约房间
	export interface ReqBookedRoom {
		bookType?: number;
		rType?: number;
		bookDate?: string;
		startTime?: string;
		endTime?: string;
	}
}
