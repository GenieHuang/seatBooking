import type { App } from "vue";
import ProTable from "@/components/ProTable/index.vue";
import ReadTitle from "@/components/common/ReadTitle/index.vue";

export default {
	// vue2之前版本的plugins插件写法
	install(app: App) {
		app.component("ProTable", ProTable);
		app.component("ReadTitle", ReadTitle);
	}
};
