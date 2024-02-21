const baseUrl = "http://localhost:9090"
class Http {
	async get(url,params = {}, header = {}) {
		return new Promise(async (resolve,reject) => {
			const { data:{data,code, msg} } = await uni.request({
				url: baseUrl + url,
				method: "GET",
				data: params,
				header: {
					token: uni.getStorageSync("token") || "",
					...header
				}
			});
			if(code === "401") {
				uni.showToast({
					type: "error",
					title: '请登录过期，请重新登录，1s后自动跳转登录页'
				})
				setTimeout(() => {
					uni.navigateTo({
						url: '/pages/login/login'
					})
				},1000)
				resolve(msg);
			} else if (code === "200") {
				resolve(data)
			} else {
				uni.showToast({
					type: "error",
					title: msg
				})
				reject(msg)
			}
		})
	}
	async post(url,params = {}, header = {}) {
		return new Promise(async (resolve,reject) => {
			const { data:{data,code, msg} } = await uni.request({
				url: baseUrl + url,
				method: "POST",
				data: params,
				header: {
					token: uni.getStorageSync("token") || "",
					...header
				}
			});
			if(code === "401") {
				uni.showToast({
					type: "error",
					title: '请登录过期，请重新登录，1s后自动跳转登录页'
				})
				uni.clearStorageSync();
				setTimeout(() => {
					uni.navigateTo({
						url: '/pages/login/login'
					})
				},1000);
				resolve(msg);
			} else if (code === "200") {
				resolve(data)
			} else {
				uni.showToast({
					type: "error",
					title: msg
				})
				reject(msg)
			}
		})
	}
}
export default new Http();