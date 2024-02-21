// * 系统全局字典

export const houtaiMenuList = [
	{
		path: "/boardManage",
		name: "boardManage",
		redirect: "/boardManage/list",
		meta: {
			icon: "Message",
			title: "公告信息",
			isLink: "",
			isHide: false,
			isAffix: false,
			isKeepAlive: false
		},
		children: [
			{
				path: "/boardManage/list",
				name: "boardManageList",
				component: () => import("@/views/boardManage/list/index.vue"),
				meta: {
					icon: "List",
					title: "公告列表",
					isLink: "",
					isHide: false,
					isAffix: false,
					isKeepAlive: false
				}
			},
			{
				path: "/boardManage/add",
				name: "boardManageAdd",
				component: () => import("@/views/boardManage/add/index.vue"),
				meta: {
					icon: "HomeFilled",
					title: "新增公告",
					isLink: "",
					isHide: true,
					isAffix: false,
					isKeepAlive: false
				}
			},
			{
				path: "/boardManage/detail",
				name: "boardManageDetail",
				component: () => import("@/views/boardManage/detail/index.vue"),
				meta: {
					icon: "HomeFilled",
					title: "公告详情",
					isLink: "",
					isHide: true,
					isAffix: false,
					isKeepAlive: false
				},
				props: (route: any) => {
					console.log("/boardManage/detail::route::", route);
					return route.query;
				}
			}
		]
	},
	{
		path: "/settingManage",
		name: "settingManage",
		redirect: "/settingManage/meetingRecord",
		meta: {
			icon: "Setting",
			title: "配置管理",
			isLink: "",
			isHide: false,
			isAffix: false,
			isKeepAlive: false
		},
		children: [
			{
				path: "/settingManage/meetingRecord",
				name: "meetingRecord",
				component: () => import("@/views/settingManage/meetingRecord/index.vue"),
				meta: {
					icon: "List",
					title: "会议室信息",
					isLink: "",
					isHide: false,
					isAffix: false,
					isKeepAlive: false
				}
			},
			{
				path: "/settingManage/studyRoomInfo",
				name: "studyRoomInfo",
				component: () => import("@/views/settingManage/studyRoomInfo/index.vue"),
				meta: {
					icon: "List",
					title: "自习室信息",
					isLink: "",
					isHide: false,
					isAffix: false,
					isKeepAlive: false
				}
			},
			{
				path: "/settingManage/classRoomInfo",
				name: "classRoomInfo",
				component: () => import("@/views/settingManage/classRoomInfo/index.vue"),
				meta: {
					icon: "List",
					title: "教室信息",
					isLink: "",
					isHide: false,
					isAffix: false,
					isKeepAlive: false
				}
			}
		]
	},
	{
		path: "/recordManage",
		name: "recordManage",
		redirect: "/recordManage/subscribe",
		meta: {
			icon: "Setting",
			title: "记录管理",
			isLink: "",
			isHide: false,
			isAffix: false,
			isKeepAlive: false
		},
		children: [
			{
				path: "/recordManage/subscribe",
				name: "subscribe",
				component: () => import("@/views/recordManage/subscribe/index.vue"),
				meta: {
					icon: "List",
					title: "预约记录",
					isLink: "",
					isHide: false,
					isAffix: false,
					isKeepAlive: false
				}
			},
			{
				path: "/recordManage/breakContractRecord",
				name: "breakContractRecord",
				component: () => import("@/views/recordManage/breakContractRecord/index.vue"),
				meta: {
					icon: "List",
					title: "违约记录",
					isLink: "",
					isHide: false,
					isAffix: false,
					isKeepAlive: false
				}
			},
			{
				path: "/recordManage/waitAuditSubscribe",
				name: "waitAuditSubscribe",
				component: () => import("@/views/recordManage/waitAuditSubscribe/index.vue"),
				meta: {
					icon: "List",
					title: "待审核记录",
					isLink: "",
					isHide: false,
					isAffix: false,
					isKeepAlive: false
				}
			}
		]
	},
	{
		path: "/userManage",
		name: "userManage",
		redirect: "/userManage/userInfo",
		meta: {
			icon: "User",
			title: "用户管理",
			isLink: "",
			isHide: false,
			isAffix: false,
			isKeepAlive: false
		},
		children: [
			{
				path: "/userManage/userInfo",
				name: "userInfo",
				component: () => import("@/views/userManage/userInfo/index.vue"),
				meta: {
					icon: "List",
					title: "用户信息",
					isLink: "",
					isHide: false,
					isAffix: false,
					isKeepAlive: false
				}
			}
		]
	},
	{
		path: "/echarts",
		name: "echarts",
		redirect: "/echarts/waterChart",
		meta: {
			icon: "TrendCharts",
			title: "Echarts",
			isLink: "",
			isHide: true,
			isFull: false,
			isAffix: false,
			isKeepAlive: true
		},
		children: [
			{
				path: "/echarts/waterChart",
				name: "waterChart",
				component: () => import("@/views/echarts/waterChart/index.vue"),
				meta: {
					icon: "Menu",
					title: "水型图",
					isLink: "",
					isHide: false,
					isFull: false,
					isAffix: false,
					isKeepAlive: true
				}
			},
			{
				path: "/echarts/columnChart",
				name: "columnChart",
				component: () => import("@/views/echarts/columnChart/index.vue"),
				meta: {
					icon: "Menu",
					title: "柱状图",
					isLink: "",
					isHide: false,
					isFull: false,
					isAffix: false,
					isKeepAlive: true
				}
			},
			{
				path: "/echarts/lineChart",
				name: "lineChart",
				component: () => import("@/views/echarts/lineChart/index.vue"),
				meta: {
					icon: "Menu",
					title: "折线图",
					isLink: "",
					isHide: false,
					isFull: false,
					isAffix: false,
					isKeepAlive: true
				}
			},
			{
				path: "/echarts/pieChart",
				name: "pieChart",
				component: () => import("@/views/echarts/pieChart/index.vue"),
				meta: {
					icon: "Menu",
					title: "饼图",
					isLink: "",
					isHide: false,
					isFull: false,
					isAffix: false,
					isKeepAlive: true
				}
			},
			{
				path: "/echarts/radarChart",
				name: "radarChart",
				component: () => import("@/views/echarts/radarChart/index.vue"),
				meta: {
					icon: "Menu",
					title: "雷达图",
					isLink: "",
					isHide: false,
					isFull: false,
					isAffix: false,
					isKeepAlive: true
				}
			},
			{
				path: "/echarts/nestedChart",
				name: "nestedChart",
				component: () => import("@/views/echarts/nestedChart/index.vue"),
				meta: {
					icon: "Menu",
					title: "嵌套环形图",
					isLink: "",
					isHide: false,
					isFull: false,
					isAffix: false,
					isKeepAlive: true
				}
			}
		]
	}
];
export const adminMenuList = [
	{
		path: "/boardManage",
		name: "boardManage",
		redirect: "/boardManage/list",
		meta: {
			icon: "Message",
			title: "公告信息",
			isLink: "",
			isHide: false,
			isAffix: false,
			isKeepAlive: false
		},
		children: [
			{
				path: "/boardManage/list",
				name: "boardManageList",
				component: () => import("@/views/boardManage/list/index.vue"),
				meta: {
					icon: "List",
					title: "公告列表",
					isLink: "",
					isHide: false,
					isAffix: false,
					isKeepAlive: false
				}
			},
			{
				path: "/boardManage/add",
				name: "boardManageAdd",
				component: () => import("@/views/boardManage/add/index.vue"),
				meta: {
					icon: "HomeFilled",
					title: "新增公告",
					isLink: "",
					isHide: true,
					isAffix: false,
					isKeepAlive: false
				}
			},
			{
				path: "/boardManage/detail",
				name: "boardManageDetail",
				component: () => import("@/views/boardManage/detail/index.vue"),
				meta: {
					icon: "HomeFilled",
					title: "公告详情",
					isLink: "",
					isHide: true,
					isAffix: false,
					isKeepAlive: false
				},
				props: (route: any) => {
					console.log("/boardManage/detail::route::", route);
					return route.query;
				}
			}
		]
	},
	{
		path: "/settingManage",
		name: "settingManage",
		redirect: "/settingManage/meetingRecord",
		meta: {
			icon: "HomeFilled",
			title: "配置管理",
			isLink: "",
			isHide: false,
			isAffix: false,
			isKeepAlive: false
		},
		children: [
			{
				path: "/settingManage/meetingRecord",
				name: "meetingRecord",
				component: () => import("@/views/settingManage/meetingRecord/index.vue"),
				meta: {
					icon: "HomeFilled",
					title: "会议室信息",
					isLink: "",
					isHide: false,
					isAffix: false,
					isKeepAlive: false
				}
			},
			{
				path: "/settingManage/studyRoomInfo",
				name: "studyRoomInfo",
				component: () => import("@/views/settingManage/studyRoomInfo/index.vue"),
				meta: {
					icon: "",
					title: "自习室信息",
					isLink: "",
					isHide: false,
					isAffix: false,
					isKeepAlive: false
				}
			},
			{
				path: "/settingManage/classRoomInfo",
				name: "classRoomInfo",
				component: () => import("@/views/settingManage/classRoomInfo/index.vue"),
				meta: {
					icon: "HomeFilled",
					title: "教室信息",
					isLink: "",
					isHide: false,
					isAffix: false,
					isKeepAlive: false
				}
			}
		]
	},
	{
		path: "/recordManage",
		name: "recordManage",
		redirect: "/recordManage/subscribe",
		meta: {
			icon: "HomeFilled",
			title: "记录管理",
			isLink: "",
			isHide: false,
			isAffix: false,
			isKeepAlive: false
		},
		children: [
			{
				path: "/recordManage/subscribe",
				name: "subscribe",
				component: () => import("@/views/recordManage/subscribe/index.vue"),
				meta: {
					icon: "HomeFilled",
					title: "预约记录",
					isLink: "",
					isHide: false,
					isAffix: false,
					isKeepAlive: false
				}
			},
			{
				path: "/recordManage/breakContractRecord",
				name: "breakContractRecord",
				component: () => import("@/views/recordManage/breakContractRecord/index.vue"),
				meta: {
					icon: "HomeFilled",
					title: "违约记录",
					isLink: "",
					isHide: false,
					isAffix: false,
					isKeepAlive: false
				}
			},
			{
				path: "/recordManage/waitAuditSubscribe",
				name: "waitAuditSubscribe",
				component: () => import("@/views/recordManage/waitAuditSubscribe/index.vue"),
				meta: {
					icon: "HomeFilled",
					title: "待审核记录",
					isLink: "",
					isHide: false,
					isAffix: false,
					isKeepAlive: false
				}
			}
		]
	},
	{
		path: "/userManage",
		name: "userManage",
		redirect: "/userManage/userInfo",
		meta: {
			icon: "HomeFilled",
			title: "用户管理",
			isLink: "",
			isHide: false,
			isAffix: false,
			isKeepAlive: false
		},
		children: [
			{
				path: "/userManage/userInfo",
				name: "userInfo",
				component: () => import("@/views/userManage/userInfo/index.vue"),
				meta: {
					icon: "HomeFilled",
					title: "用户信息",
					isLink: "",
					isHide: false,
					isAffix: false,
					isKeepAlive: false
				}
			}
		]
	}
];
export const qiantaiMenuList = [
	{
		path: "/frontHome",
		name: "frontHome",
		component: () => import("@/views/front/frontHome/index.vue"),
		meta: {
			icon: "HomeFilled",
			title: "首页",
			isLink: "",
			isHide: false,
			isAffix: false,
			isKeepAlive: false
		}
	},
	{
		path: "/frontMeetingSubscribe",
		name: "frontMeetingSubscribe",
		component: () => import("@/views/front/frontMeetingSubscribe/index.vue"),
		meta: {
			icon: "Document",
			title: "会议室预约",
			isLink: "",
			isHide: false,
			isAffix: false,
			isKeepAlive: false
		}
	},
	{
		path: "/frontStudySubscribe",
		name: "frontStudySubscribe",
		component: () => import("@/views/front/frontStudySubscribe/index.vue"),
		meta: {
			icon: "Document",
			title: "自习室预约",
			isLink: "",
			isHide: false,
			isAffix: false,
			isKeepAlive: false
		}
	},
	{
		path: "/frontSubscribeRecord",
		name: "frontSubscribeRecord",
		component: () => import("@/views/front/frontSubscribeRecord/index.vue"),
		meta: {
			icon: "List",
			title: "预约记录",
			isLink: "",
			isHide: false,
			isAffix: false,
			isKeepAlive: false
		}
	}
];
export const qiantaiMenuListJZG = [
	{
		path: "/frontHome",
		name: "frontHome",
		component: () => import("@/views/front/frontHome/index.vue"),
		meta: {
			icon: "HomeFilled",
			title: "首页",
			isLink: "",
			isHide: false,
			isAffix: false,
			isKeepAlive: false
		}
	},
	{
		path: "/frontMeetingSubscribe",
		name: "frontMeetingSubscribe",
		component: () => import("@/views/front/frontMeetingSubscribe/index.vue"),
		meta: {
			icon: "Document",
			title: "会议室预约",
			isLink: "",
			isHide: false,
			isAffix: false,
			isKeepAlive: false
		}
	},
	{
		path: "/frontClassRoomSubscribe",
		name: "frontClassRoomSubscribe",
		component: () => import("@/views/front/frontClassRoomSubscribe/index.vue"),
		meta: {
			icon: "Document",
			title: "教室预约",
			isLink: "",
			isHide: false,
			isAffix: false,
			isKeepAlive: false
		}
	},
	{
		path: "/frontSubscribeRecord",
		name: "frontSubscribeRecord",
		component: () => import("@/views/front/frontSubscribeRecord/index.vue"),
		meta: {
			icon: "List",
			title: "预约记录",
			isLink: "",
			isHide: false,
			isAffix: false,
			isKeepAlive: false
		}
	}
];
/**
 * @description：用户状态
 */
export const userStatus = [
	{ label: "全部", value: undefined },
	{ label: "启用", value: 0, color: "#009688" },
	{ label: "禁用", value: 1, color: "#f56c6c" }
];
/**
 * @description: 预约类型
 */
export const subscribeType = [
	{
		label: "会议室预约",
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
];
/**
 * @description: 预约状态
 */
export const subscribeStatus = [
	{
		label: "已预约",
		value: 1
	},
	{
		label: "进行中",
		value: 2
	},
	{
		label: "已完成",
		value: 3
	},
	{
		label: "已违约",
		value: 4
	},
	{
		label: "已取消",
		value: 5
	},
	{
		label: "待审核",
		value: 6
	},
	{
		label: "已驳回",
		value: 7
	}
];
/**
 * @description 用户类别
 * */
export const userTypeStatus = [
	{
		label: "学生",
		value: 1,
		color: "#e6a23c"
	},
	{
		label: "教职工",
		value: 2,
		color: "#009688"
	}
];
/**
 * @description：角色
 */
export const roleTypeStatus = [
	{
		label: "超级管理员",
		value: 0,
		color: "#009688"
	},
	{
		label: "管理员",
		value: 1,
		color: "#67c23a"
	},
	{
		label: "用户",
		value: 2,
		color: "#e6a23c"
	}
];
/**
 * @description：所在系
 * Finance
 * InformationManagement
 * Marketing
 * AccountingAndFinance
 * DataS1cience
 * Management
 * EconomyAndtrade
 */
export const departmentTypeList = [
	{
		label: "金融学系",
		value: "Finance"
	},
	{
		label: "管理科学与信息管理系",
		value: "InformationManagement"
	},
	{
		label: "市场营销系",
		value: "Marketing"
	},
	{
		label: "会计与财务系",
		value: "AccountingAndFinance"
	},
	{
		label: "数据科学与交叉学科系",
		value: "DataScience"
	},
	{
		label: "企业管理系",
		value: "Management"
	},
	{
		label: "经济与贸易系",
		value: "EconomyAndtrade"
	}
];
