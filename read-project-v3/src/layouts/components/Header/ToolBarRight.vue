<template>
	<div class="tool-bar-ri">
		<div class="header-icon">
			<AssemblySize id="assemblySize" />
			<Language id="language" v-if="false" />
			<SearchMenu id="searchMenu" v-if="false" />
			<ThemeSetting id="themeSetting" />
			<Message id="message" v-if="false" />
			<Fullscreen id="fullscreen" v-if="false" />
		</div>
		<span class="username">{{ userInfo.name }}</span>
		<Avatar />
	</div>
</template>

<script setup lang="ts">
import SearchMenu from "./components/SearchMenu.vue";
import Fullscreen from "./components/Fullscreen.vue";
import Message from "./components/Message.vue";
import Language from "./components/Language.vue";
import ThemeSetting from "./components/ThemeSetting.vue";
import AssemblySize from "./components/AssemblySize.vue";
import Avatar from "./components/Avatar.vue";
import { GlobalStore } from "@/stores";
import { computed } from "vue";
//* pinia
const globalStore = GlobalStore();
//* computed
const userInfo = computed(() => {
	return globalStore.userInfo || {};
});
</script>

<style scoped lang="scss">
.tool-bar-ri {
	display: flex;
	align-items: center;
	justify-content: center;
	margin: 0 30px 0 0;
	.header-icon {
		display: flex;
		align-items: center;
		& > * {
			margin-left: 21px;
		}
	}
	.username {
		margin: 0 20px;
		font-size: 15px;
	}
}
</style>
