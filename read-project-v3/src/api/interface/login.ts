//* 登录模块
export namespace LoginModule {
	//登录请求参数
	export interface ReqLogin {
		username: string; // 用户名
		password: string; // 密码
	}
	//登录响应参数
	export interface ResLogin {
		token: string; // token
		username: string; // 用户id
	}
}
