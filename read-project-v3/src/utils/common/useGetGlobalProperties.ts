import { getCurrentInstance } from "vue";

// 获取全局属性的模块
export function useGetGlobalProperties() {
	const {
		// Vue.appContext.app.config.globalProperties：$commonMsgBox、$commonMsg
		appContext: {
			app: {
				config: { globalProperties }
			}
		}
	} = getCurrentInstance() as any;
	return { ...globalProperties };
}
