import { LoginModule } from "@/api/interface/login";
import http from "@/api";

/**
 * @name 登录模块
 */
// * 用户登录
export const loginApi = (params: LoginModule.ReqLogin) => {
	return http.post<LoginModule.ResLogin>(`/user/login`, params, { headers: { noLoading: true } }); // 正常 post json 请求  ==>  application/json
};
