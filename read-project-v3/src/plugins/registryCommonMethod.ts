import type { App } from "vue";
import { commonMsgBox, commonMsg } from "@/utils/common/index";
export default {
	/**
	 * 将封装的公共操作提示和是否继续操作的弹窗通过插件的方式注册为全局变量（在入口文件main.ts进行注册）
	 * 注册完全局变量需要使用的话，需要引入utils/useGetGlobalProperties文件
	 *  */
	install(app: App) {
		app.config.globalProperties.$commonMsgBox = commonMsgBox;
		app.config.globalProperties.$commonMsg = commonMsg;
	}
};
