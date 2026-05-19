import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Storeup from '../pages/storeup/list'
import payList from '../pages/pay'

import mujuanzheList from '../pages/mujuanzhe/list'
import mujuanzheDetail from '../pages/mujuanzhe/detail'
import mujuanzheAdd from '../pages/mujuanzhe/add'
import juanzengzheList from '../pages/juanzengzhe/list'
import juanzengzheDetail from '../pages/juanzengzhe/detail'
import juanzengzheAdd from '../pages/juanzengzhe/add'
import xiangmuleixingList from '../pages/xiangmuleixing/list'
import xiangmuleixingDetail from '../pages/xiangmuleixing/detail'
import xiangmuleixingAdd from '../pages/xiangmuleixing/add'
import diquxinxiList from '../pages/diquxinxi/list'
import diquxinxiDetail from '../pages/diquxinxi/detail'
import diquxinxiAdd from '../pages/diquxinxi/add'
import mujuanxiangmuList from '../pages/mujuanxiangmu/list'
import mujuanxiangmuDetail from '../pages/mujuanxiangmu/detail'
import mujuanxiangmuAdd from '../pages/mujuanxiangmu/add'
import juanzengjiluList from '../pages/juanzengjilu/list'
import juanzengjiluDetail from '../pages/juanzengjilu/detail'
import juanzengjiluAdd from '../pages/juanzengjilu/add'
import xiangmujinzhanList from '../pages/xiangmujinzhan/list'
import xiangmujinzhanDetail from '../pages/xiangmujinzhan/detail'
import xiangmujinzhanAdd from '../pages/xiangmujinzhan/add'
import gonggaoxinxiList from '../pages/gonggaoxinxi/list'
import gonggaoxinxiDetail from '../pages/gonggaoxinxi/detail'
import gonggaoxinxiAdd from '../pages/gonggaoxinxi/add'
import chatmessageList from '../pages/chatmessage/list'
import chatmessageDetail from '../pages/chatmessage/detail'
import chatmessageAdd from '../pages/chatmessage/add'
import friendList from '../pages/friend/list'
import friendDetail from '../pages/friend/detail'
import friendAdd from '../pages/friend/add'
import syslogList from '../pages/syslog/list'
import syslogDetail from '../pages/syslog/detail'
import syslogAdd from '../pages/syslog/add'
import discussmujuanxiangmuList from '../pages/discussmujuanxiangmu/list'
import discussmujuanxiangmuDetail from '../pages/discussmujuanxiangmu/detail'
import discussmujuanxiangmuAdd from '../pages/discussmujuanxiangmu/add'
import discussxiangmujinzhanList from '../pages/discussxiangmujinzhan/list'
import discussxiangmujinzhanDetail from '../pages/discussxiangmujinzhan/detail'
import discussxiangmujinzhanAdd from '../pages/discussxiangmujinzhan/add'
import merkleVerify from '../pages/merkle/verify'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'mujuanzhe',
					component: mujuanzheList
				},
				{
					path: 'mujuanzheDetail',
					component: mujuanzheDetail
				},
				{
					path: 'mujuanzheAdd',
					component: mujuanzheAdd
				},
				{
					path: 'juanzengzhe',
					component: juanzengzheList
				},
				{
					path: 'juanzengzheDetail',
					component: juanzengzheDetail
				},
				{
					path: 'juanzengzheAdd',
					component: juanzengzheAdd
				},
				{
					path: 'xiangmuleixing',
					component: xiangmuleixingList
				},
				{
					path: 'xiangmuleixingDetail',
					component: xiangmuleixingDetail
				},
				{
					path: 'xiangmuleixingAdd',
					component: xiangmuleixingAdd
				},
				{
					path: 'diquxinxi',
					component: diquxinxiList
				},
				{
					path: 'diquxinxiDetail',
					component: diquxinxiDetail
				},
				{
					path: 'diquxinxiAdd',
					component: diquxinxiAdd
				},
				{
					path: 'mujuanxiangmu',
					component: mujuanxiangmuList
				},
				{
					path: 'mujuanxiangmuDetail',
					component: mujuanxiangmuDetail
				},
				{
					path: 'mujuanxiangmuAdd',
					component: mujuanxiangmuAdd
				},
				{
					path: 'juanzengjilu',
					component: juanzengjiluList
				},
				{
					path: 'juanzengjiluDetail',
					component: juanzengjiluDetail
				},
				{
					path: 'juanzengjiluAdd',
					component: juanzengjiluAdd
				},
				{
					path: 'xiangmujinzhan',
					component: xiangmujinzhanList
				},
				{
					path: 'xiangmujinzhanDetail',
					component: xiangmujinzhanDetail
				},
				{
					path: 'xiangmujinzhanAdd',
					component: xiangmujinzhanAdd
				},
				{
					path: 'gonggaoxinxi',
					component: gonggaoxinxiList
				},
				{
					path: 'gonggaoxinxiDetail',
					component: gonggaoxinxiDetail
				},
				{
					path: 'gonggaoxinxiAdd',
					component: gonggaoxinxiAdd
				},
				{
					path: 'chatmessage',
					component: chatmessageList
				},
				{
					path: 'chatmessageDetail',
					component: chatmessageDetail
				},
				{
					path: 'chatmessageAdd',
					component: chatmessageAdd
				},
				{
					path: 'friend',
					component: friendList
				},
				{
					path: 'friendDetail',
					component: friendDetail
				},
				{
					path: 'friendAdd',
					component: friendAdd
				},
				{
					path: 'syslog',
					component: syslogList
				},
				{
					path: 'syslogDetail',
					component: syslogDetail
				},
				{
					path: 'syslogAdd',
					component: syslogAdd
				},
				{
					path: 'discussmujuanxiangmu',
					component: discussmujuanxiangmuList
				},
				{
					path: 'discussmujuanxiangmuDetail',
					component: discussmujuanxiangmuDetail
				},
				{
					path: 'discussmujuanxiangmuAdd',
					component: discussmujuanxiangmuAdd
				},
				{
					path: 'discussxiangmujinzhan',
					component: discussxiangmujinzhanList
				},
				{
					path: 'discussxiangmujinzhanDetail',
					component: discussxiangmujinzhanDetail
				},
				{
					path: 'discussxiangmujinzhanAdd',
					component: discussxiangmujinzhanAdd
				},
				{
					path: 'merkleVerify',
					component: merkleVerify
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
