import { ElMessageBox, ElMessageBoxOptions, MessageBoxData, ElMessage, MessageOptions } from "element-plus";
class CommonMsgbox {
	delete(
		message = "此操作将永久删除该数据, 是否继续?",
		title = "提示",
		options: ElMessageBoxOptions = {
			type: "warning",
			confirmButtonText: "确认",
			cancelButtonText: "取消"
		}
	): Promise<MessageBoxData> {
		return ElMessageBox.confirm(message, title, {
			message,
			title,
			...options
		});
	}
	switchStatus(
		message = "此操作将修改该数据状态 是否继续?",
		title = "提示",
		options: ElMessageBoxOptions = {
			type: "warning",
			confirmButtonText: "确认",
			cancelButtonText: "取消"
		}
	): Promise<MessageBoxData> {
		return ElMessageBox.confirm(message, title, {
			message,
			title,
			...options
		});
	}
	customer(
		title = "",
		message = "此操作将永久删除该文件, 是否继续?",
		options: ElMessageBoxOptions = {
			type: "warning",
			confirmButtonText: "确认",
			cancelButtonText: "取消",
			showCancelButton: true,
			closeOnClickModal: false,
			center: false
		}
	): Promise<MessageBoxData> {
		const {
			confirmButtonText = "确认",
			cancelButtonText = "取消",
			center = false,
			showCancelButton = true,
			closeOnClickModal = false,
			...otherOptions
		} = options;
		return ElMessageBox({
			type: "warning",
			title,
			message,
			confirmButtonText,
			cancelButtonText,
			showCancelButton,
			closeOnClickModal,
			center,
			...otherOptions
		});
	}
}
class CommonMsg {
	add(messageOptions: MessageOptions = {}, msg: string = "新增成功") {
		const { type = "success", duration = 3000, showClose = true, ...otherOptions } = messageOptions;
		ElMessage({
			type,
			message: msg,
			duration,
			showClose,
			...otherOptions
		});
	}
	delete(messageOptions: MessageOptions = {}, msg: string = "删除成功") {
		const { type = "success", duration = 3000, showClose = true, ...otherOptions } = messageOptions;
		ElMessage({
			type,
			message: msg,
			duration,
			showClose,
			...otherOptions
		});
	}
	update(messageOptions: MessageOptions = {}, msg: string = "更新成功") {
		const { type = "success", duration = 3000, showClose = true, ...otherOptions } = messageOptions;
		ElMessage({
			type,
			message: msg,
			duration,
			showClose,
			...otherOptions
		});
	}
	error(messageOptions: MessageOptions = {}, msg: string = "操作失败") {
		const { type = "success", duration = 3000, showClose = true, ...otherOptions } = messageOptions;
		ElMessage({
			type,
			message: msg,
			duration,
			showClose,
			...otherOptions
		});
	}
}
export const commonMsgBox = new CommonMsgbox();
export const commonMsg = new CommonMsg();
