<template>
	<div class="read-title f16">
		<span class="read-line"></span>
		<span class="title-text">
			<slot></slot>
		</span>
		<span class="remark">
			<slot name="remark"></slot>
		</span>
	</div>
</template>
<style scoped lang="scss">
.read-title {
	display: flex;
	align-items: center;
	height: 28px;
	font-size: 24px;
	background-color: transparent;
}
.read-line {
	width: 4px;
	height: 24px;
	margin-right: 5px;
	background-color: #2e5afe;
	border-radius: 2px;
}
.title-text {
	font-weight: 600;
	line-height: 22px;
	color: #0d0e21;
}
.remark {
	font-size: 14px;
	color: #6b6d74;
}
</style>
