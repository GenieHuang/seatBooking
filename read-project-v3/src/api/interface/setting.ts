import { ReqPage } from "@/api/interface/index";

export namespace SettingModule {
	//请求参数：会议室信息
	export interface ReqRoomInfo extends ReqPage {
		rType: number;
	}
	// 请求参数：更新会议室/自习室/教室状态
	export interface ReqSwitchRoomStatus {
		roomId: number;
	}
	// 请求参数：新增/更新会议室信息
	export interface ReqAddOrEditMeetingRoomInfo {
		rCode?: string;
		roomName?: string;
		district?: string;
		floor?: string;
		roomId?: number;
		isResearch?: boolean;
		roomDepart?: string;
	}
	// 请求参数：新增或更新自习室
	export interface ReqAddOrEditStudyRoomInfo {
		rCode?: string;
		num?: number;
		roomName?: string;
		district?: string;
		floor?: string;
		rUrl?: string;
		roomId?: number;
	}
	// 请求参数：新增或更新教室
	export interface ReqAddOrEditClassRoomInfo {
		rCode?: string;
		num?: number;
		roomName?: string;
		district?: string;
		roomId?: number;
	}
}
