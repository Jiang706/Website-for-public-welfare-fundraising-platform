import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
	import discussxiangmujinzhan from '@/views/modules/discussxiangmujinzhan/list'
	import juanzengjilu from '@/views/modules/juanzengjilu/list'
	import xiangmujinzhan from '@/views/modules/xiangmujinzhan/list'
	import mujuanzhe from '@/views/modules/mujuanzhe/list'
	import xiangmuleixing from '@/views/modules/xiangmuleixing/list'
	import juanzengzhe from '@/views/modules/juanzengzhe/list'
	import syslog from '@/views/modules/syslog/list'
	import gonggaoxinxi from '@/views/modules/gonggaoxinxi/list'
	import config from '@/views/modules/config/list'
	import discussmujuanxiangmu from '@/views/modules/discussmujuanxiangmu/list'
	import mujuanxiangmu from '@/views/modules/mujuanxiangmu/list'
	import diquxinxi from '@/views/modules/diquxinxi/list'


//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/discussxiangmujinzhan',
		name: '项目进展评论',
		component: discussxiangmujinzhan
	}
	,{
		path: '/juanzengjilu',
		name: '捐赠记录',
		component: juanzengjilu
	}
	,{
		path: '/xiangmujinzhan',
		name: '项目进展',
		component: xiangmujinzhan
	}
	,{
		path: '/mujuanzhe',
		name: '募捐者',
		component: mujuanzhe
	}
	,{
		path: '/xiangmuleixing',
		name: '项目类型',
		component: xiangmuleixing
	}
	,{
		path: '/juanzengzhe',
		name: '捐赠者',
		component: juanzengzhe
	}
	,{
		path: '/syslog',
		name: '系统日志',
		component: syslog
	}
	,{
		path: '/gonggaoxinxi',
		name: '公告信息',
		component: gonggaoxinxi
	}
	,{
		path: '/config',
		name: '轮播图管理',
		component: config
	}
	,{
		path: '/discussmujuanxiangmu',
		name: '募捐项目评论',
		component: discussmujuanxiangmu
	}
	,{
		path: '/mujuanxiangmu',
		name: '募捐项目',
		component: mujuanxiangmu
	}
	,{
		path: '/diquxinxi',
		name: '地区信息',
		component: diquxinxi
	}
	]
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
