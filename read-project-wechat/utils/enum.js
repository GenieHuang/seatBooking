/**
 * @description 用户类别
 * */
export const userType = {
	1: "学生",
	2: "教职工"
}
/**
 * @description 所在系
 * */
export const departmentType = {
	Finance: "金融学系",
	InformationManagement: "管理科学与信息管理系",
	Marketing: "市场营销系",
	AccountingAndFinance: "会计与财务系",
	DataScience: "数据科学与交叉学科系",
	Management: "企业管理系",
	EconomyAndtrade: "经济与贸易系"
}
/**
 * @description: 预约类型
 */
export const subscribeType = {
	1: "会议室预约",
	2: "自习室预约",
	3: "教室预约"
}
/**
 * @description:预约类型
 */
export const subscribeTypeList = [
	{
		label: '会议室预约',
		value: 1
	},
	{
		label: "自习室预约",
		value: 2
	},
	{
		label: "教室预约",
		value: 3
	}
]

/**
 * @description: 预约状态
 */
export const subscribeStatus = {
	1: {
		text: "已预约",
		style: {
			color: "#E99D42 !important",
			backgroundColor: "rgba(233,157,66,0.1) !important"
		}
	},
	2: {
		text: "进行中",
		style: {
			color: "#81B337 !important",
			backgroundColor: "rgba(129,179,55,0.1) !important"
		}
	},
	3: {
		text: "已完成",
		style: {
			color: "#0453A4 !important",
			backgroundColor: "rgba(4,83,164,0.1) !important"
		}
	},
	4: {
		text: "已违约",
		style: {
			color: "#BD3124 !important",
			backgroundColor: "rgba(189,49,36,0.1) !important"
		}
	},
	5: {
		text: "已取消",
		style: {
			color: "#BD3124 !important",
			backgroundColor: "rgba(189,49,36,0.1) !important"
		}
	},
	6: {
		text: "待审核",
		style: {
			color: "#E99D42 !important",
			backgroundColor: "rgba(233,157,66,0.1) !important"
		}
	},
	7: {
		text: "已驳回",
		style: {
			color: "#BD3124 !important",
			backgroundColor: "rgba(189,49,36,0.1) !important"
		}
	}
}