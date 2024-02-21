import { AuthStore } from "@/stores/modules/auth";
import { createRouter, createWebHashHistory } from "vue-router";
import { GlobalStore } from "@/stores";
import { LOGIN_URL, ROUTER_WHITE_LIST } from "@/config/config";
import { staticRouter, errorRouter } from "@/routers/modules/staticRouter";
import NProgress from "@/config/nprogress";
import { houtaiMenuList, qiantaiMenuList, qiantaiMenuListJZG } from "@/utils/serviceDict";
import { ElNotification } from "element-plus";
import { getTimeState } from "@/utils/util";
/**
 * @description 动态路由参数配置简介 📚
 * @param path ==> 菜单路径
 * @param name ==> 菜单别名
 * @param redirect ==> 重定向地址
 * @param component ==> 视图文件路径
 * @param meta ==> 菜单信息
 * @param meta.icon ==> 菜单图标
 * @param meta.title ==> 菜单标题
 * @param meta.activeMenu ==> 当前路由为详情页时，需要高亮的菜单
 * @param meta.isLink ==> 是否外链
 * @param meta.isHide ==> 是否隐藏
 * @param meta.isFull ==> 是否全屏
 * @param meta.isAffix ==> 是否固定在 tabs nav
 * @param meta.isKeepAlive ==> 是否缓存
 * */
const router = createRouter({
	history: createWebHashHistory(""),
	routes: [...staticRouter, ...errorRouter],
	strict: false,
	scrollBehavior: () => ({ left: 0, top: 0 })
});

/**
 * @description 路由拦截 beforeEach
 * */
router.beforeEach(async (to, from, next) => {
	console.log("to::", to);
	const globalStore = GlobalStore();
	const authStore = AuthStore();
	if (!authStore?.authMenuList || authStore?.authMenuList?.length <= 0) {
		if (globalStore.userInfo.role === 0) {
			authStore.setAuthMenuList(houtaiMenuList);
		} else if (globalStore.userInfo.role === 1) {
			authStore.setAuthMenuList(houtaiMenuList);
		} else if (globalStore.userInfo.role === 2) {
			if (globalStore.userInfo.type === 1) {
				authStore.setAuthMenuList(qiantaiMenuList);
			} else if (globalStore.userInfo.type === 2) {
				authStore.setAuthMenuList(qiantaiMenuListJZG);
			} else {
				ElNotification({
					title: getTimeState(),
					message: "数据错误",
					type: "error",
					duration: 3000
				});
			}
		} else if (globalStore.userInfo.role) {
		}
	}
	// 1.NProgress 开始
	NProgress.start();

	// 2.动态设置标题
	const title = import.meta.env.VITE_GLOB_APP_TITLE;
	document.title = to.meta.title ? `${to.meta.title} - ${title}` : title;

	// 4.判断访问页面是否在路由白名单地址中，如果存在直接放行
	if (ROUTER_WHITE_LIST.includes(to.path)) return next();

	// 5.判断是否有 Token，没有重定向到 login
	if (!globalStore.token) return next({ path: LOGIN_URL, replace: true });
	// 7.正常访问页面
	next();
});
/**
 * @description 路由跳转结束
 * */
router.afterEach(() => {
	NProgress.done();
});

/**
 * @description 路由跳转错误
 * */
router.onError(error => {
	NProgress.done();
	console.warn("路由错误", error.message);
});

export default router;
