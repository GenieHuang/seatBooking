import { ReqPage } from "@/api/interface/index";

export namespace UserModule {
	// 请求参数：用户列表
	export interface ReqGetUserList extends ReqPage {
		username?: string;
		name?: string;
		status?: number;
	}
	// 请求参数：修改用户状态
	export interface ReqSwitchUserStatus {
		userId: number;
	}
	// 请求参数： 修改用户信息
	export interface ReqAddOrEditUserInfo {
		userInfo: {
			name?: string;
			username?: string;
			role?: number;
			userId?: number;
			password?: string;
			status?: number;
		};
		school?: string;
		type?: number;
		department?: string;
	}
	// 请求参数：修改密码
	export interface ReqModifyPasswordByUserId {
		userId: number;
		oldPassword: string;
		newPassword: string;
	}
}
