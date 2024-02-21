import { createApp } from "vue";
import App from "./App.vue";
// reset style sheet
import "@/styles/reset.scss";
// CSS common style sheet
import "@/styles/common.scss";
// iconfont css
import "@/assets/iconfont/iconfont.scss";
// font css
import "@/assets/fonts/font.scss";
// element plus
import ElementPlus from "element-plus";
// element icons
import * as Icons from "@element-plus/icons-vue";
// element css
import "element-plus/dist/index.css";
// element dark(内置暗黑模式)
import "element-plus/theme-chalk/dark/css-vars.css";
// custom element dark(自定义暗黑模式)
import "@/styles/theme/element-dark.scss";
// custom element css
import "@/styles/element.scss";
// custom directives
import directives from "@/directives/index";
// vue Router
import router from "@/routers/index";
// vue i18n
import I18n from "@/languages/index";
// pinia store
import pinia from "@/stores/index";
// svg icons
import "virtual:svg-icons-register";
// registryComponent、registryCommonMethod通过插件的方式注册组件和全局方法，避免在main.ts中一个个注册，简化代码
// 引入挂载公共组件插件
import registryComponent from "@/plugins/registryComponent";
//引入挂载公共方法的插件（新增/删除/更新等公共message提示，messageBox公共部分提示）
import registryCommonMethod from "./plugins/registryCommonMethod";
// 引入公共样式
import "@/styles/common/global.scss";
import "@/styles/common/public.scss";

const app = createApp(App);
// 注册element Icons组件
Object.keys(Icons).forEach(key => {
	app.component(key, Icons[key as keyof typeof Icons]);
});

app
	.use(router)
	.use(I18n)
	.use(pinia)
	.use(directives)
	.use(ElementPlus)
	.use(registryComponent)
	.use(registryCommonMethod)
	.mount("#app");
