import Http from "./http.js";

export const loginApi = (params) => Http.post('/user/login',params);

export const getUserInfoApi = (params) => Http.post('/user');

export const getBoardNewMsgApi = (params) => Http.get('/board/newMsg',params);

export const frontSearchApi = (params) => Http.get('/book/frontSearch',params);
//新增预约记录
export const addBookRecordApi = (params) => Http.post('/book/insertBook',params);
// 首页预约
export const uniSearchApi = (params) => Http.get('/book/uniSearch',params);
// 更新预约状态
export const updateBookStatus = (params) => Http.get('/book/status', params);
// 查询可预约房间
export const searchBookedRoomAPI = (params) => {
	return Http.get(`/book/ableBookRoom`, params);
}
// 新增预约
export const insertBookRecordAPI = (params) => {
	return Http.post(`/book/insertBook`, params);
};
