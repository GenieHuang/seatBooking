import http from "@/api/index";
import { BoardManageModule } from "../interface/boardManage";
import qs from "qs";
// 公告列表
export const getBoardListAPI = (params: BoardManageModule.ReqBoardInfo) => {
	return http.post<any>(`/board/search?${qs.stringify(params)}`, undefined, { headers: { noLoading: false } });
};
// 新增公告
export const addBoardAPI = (params: BoardManageModule.ReqAddBoardInfo) => {
	return http.post<any>("/board/insert", params, { headers: { noLoading: false } });
};
// 查询最新一条公告信息
export const getNewBoard = () => {
	return http.get<any>("/board/newMsg", undefined, { headers: { noLoading: false } });
};
