<template>
	<div class="home-preview">




		<!-- 特价商品 -->
		<div id="animate_listmujuanxiangmu" class="lists animate__animated">
			<div class="list_title_box">
				<span class="list_title">募捐项目展示</span>
				<span class="list_subhead">{{'mujuanxiangmu'.toUpperCase()}} SHOW</span>
			</div>
			<div v-if="mujuanxiangmuList.length" class="list list11 index-pv1">
				<div class="list-body">
					<div class="item list-item animation-box" v-for="item,index in mujuanxiangmuList" :key="index" @click="toDetail('mujuanxiangmuDetail', item)">
						<img :name="item.id" v-if="preHttp(item.tupian)" :src="item.tupian.split(',')[0]" alt="" />
						<img :name="item.id" v-else :src="baseUrl + (item.tupian?item.tupian.split(',')[0]:'')" alt="" />
						<div class="name">{{item.xiangmumingcheng}}</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('mujuanxiangmu')">
				<span class="text">更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
	

		</div>
		<!-- 特价商品 -->
		<!-- 特价商品 -->
		<div id="animate_listxiangmujinzhan" class="lists animate__animated">
			<div class="list_title_box">
				<span class="list_title">项目进展展示</span>
				<span class="list_subhead">{{'xiangmujinzhan'.toUpperCase()}} SHOW</span>
			</div>
			<div v-if="xiangmujinzhanList.length" class="list list11 index-pv1">
				<div class="list-body">
					<div class="item list-item animation-box" v-for="item,index in xiangmujinzhanList" :key="index" @click="toDetail('xiangmujinzhanDetail', item)">
						<img :name="item.id" v-if="preHttp(item.tupian)" :src="item.tupian.split(',')[0]" alt="" />
						<img :name="item.id" v-else :src="baseUrl + (item.tupian?item.tupian.split(',')[0]:'')" alt="" />
						<div class="name">{{item.xiangmumingcheng}}</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('xiangmujinzhan')">
				<span class="text">更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
	

		</div>
		<!-- 特价商品 -->
		<!-- 特价商品 -->
		<div id="animate_listgonggaoxinxi" class="lists animate__animated">
			<div class="list_title_box">
				<span class="list_title">公告信息展示</span>
				<span class="list_subhead">{{'gonggaoxinxi'.toUpperCase()}} SHOW</span>
			</div>
			<div v-if="gonggaoxinxiList.length" class="list list11 index-pv1">
				<div class="list-body">
					<div class="item list-item animation-box" v-for="item,index in gonggaoxinxiList" :key="index" @click="toDetail('gonggaoxinxiDetail', item)">
						<img :name="item.id" v-if="preHttp(item.fengmian)" :src="item.fengmian.split(',')[0]" alt="" />
						<img :name="item.id" v-else :src="baseUrl + (item.fengmian?item.fengmian.split(',')[0]:'')" alt="" />
						<div class="name">{{item.biaoti}}</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('gonggaoxinxi')">
				<span class="text">更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
	

		</div>
		<!-- 特价商品 -->
	</div>
</template>

<script>
import 'animate.css'
import Swiper from "swiper";

	export default {
		//数据集合
		data() {
			return {
				baseUrl: '',
				newsList: [],

				mujuanxiangmuList: [],
				xiangmujinzhanList: [],
				gonggaoxinxiList: [],




			}
		},
		created() {
			this.baseUrl = this.$config.baseUrl;
			this.getList();
		},
		mounted() {
			window.addEventListener('scroll', this.handleScroll)
			setTimeout(()=>{
				this.handleScroll()
			},100)
			
			this.swiperChanges()
		},
		beforeDestroy() {
			window.removeEventListener('scroll', this.handleScroll)
		},
		//方法集合
		methods: {
			swiperChanges() {
				setTimeout(()=>{
				},750)
			},
			recommendIndexClick12(index, name) {
				this['recommendIndex12' + name] = index
				this.getList()
				
				document.querySelectorAll('.recommend .list12' + name + ' .list .item').forEach(el => {
					el.classList.remove("active")
				})
				setTimeout(() => {
					document.querySelectorAll('.recommend .list12' + name + ' .list .item').forEach(el => {
						el.classList.add("active")
					})
				}, 1);
			},

			listIndexClick11(index, name) {
				this['listIndex11' + name] = index[this['listColumn11' + name]]
				this.getList()
			},

			handleScroll() {
				let arr = [
					{id:'about',css:'animate__heartBeat'},
					{id:'system',css:'animate__bounceInRight'},
					{id:'animate_listmujuanxiangmu',css:'animate__bounceInLeft'},
					{id:'animate_listxiangmujinzhan',css:'animate__bounceInLeft'},
					{id:'animate_listgonggaoxinxi',css:'animate__bounceInLeft'},
				]
			
				for (let i in arr) {
					let doc = document.getElementById(arr[i].id)
					if (doc) {
						let top = doc.offsetTop
						let win_top = window.innerHeight + window.pageYOffset
						// console.log(top,win_top)
						if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
							// console.log(doc)
							doc.classList.add(arr[i].css)
						}
					}
				}
			},
			preHttp(str) {
				return str && str.substr(0,4)=='http';
			},
			preHttp2(str) {
				return str && str.split(',w').length>1;
			},
			getList() {
				let autoSortUrl = "";
				let data = {}
			
				data = {
					page: 1,
					limit: 8,
				}
				data['sfsh']= '是'

				this.$http.get('mujuanxiangmu/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.mujuanxiangmuList = res.data.data.list;
					}
				});
				data = {
					page: 1,
					limit: 8,
				}

				this.$http.get('xiangmujinzhan/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.xiangmujinzhanList = res.data.data.list;
					}
				});
				data = {
					page: 1,
					limit: 8,
				}

				this.$http.get('gonggaoxinxi/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.gonggaoxinxiList = res.data.data.list;
					}
				});
			},
			toDetail(path, item) {
				this.$router.push({path: '/index/' + path, query: {id: item.id}});
			},
			moreBtn(path) {
				this.$router.push({path: '/index/' + path});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.home-preview {
		margin: 10px auto;
		flex-direction: column;
		display: flex;
		width: 100%;
		.lists {
			padding: 30px 16% 50px;
			margin: 0 0 ;
			background: #F2F3F7;
			width: 100%;
			position: relative;
			order: 4;
			.list_title_box {
				box-shadow: 0 4px 8px rgba(0,0,0,.2);
				padding: 0 50px;
				margin: 10px auto 0;
				background: #fff;
				width: 100%;
				line-height: 70px;
				text-align: left;
				height: 70px;
				.list_title {
					color: #0AB8C1;
					font-weight: bold;
					font-size: 26px;
				}
				.list_subhead {
					margin: 0 0 10px;
					color: #999;
					display: none;
					width: 100%;
					font-size: 20px;
					line-height: 1.5;
					text-align: center;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1.1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list11 {
				padding: 0;
				margin: 30px 0 0;
				background: none;
				display: flex;
				width: 100%;
				justify-content: space-between;
				align-items: flex-start;
				height: auto;
				.tab_view {
					margin: 0px 30px 0 0;
					flex-direction: column;
					background: #fff;
					display: flex;
					width: 220px;
					height: auto;
					.tab {
						cursor: pointer;
						padding: 10px 30px;
						margin: 0;
						display: flex;
						width: 100%;
						border-color: #efefef;
						border-width: 0;
						align-items: center;
						border-style: solid;
						height: auto;
						.icon {
							margin: 0 5px 0 0;
							color: #333;
							display: none;
							font-size: 16px;
							line-height: 44px;
						}
						img {
							border-radius: 50%;
							margin: 0 10px 0 0;
							object-fit: cover;
							display: inline-block;
							width: 44px;
							height: 44px;
						}
						.text {
							color: #0AB8C1;
							font-size: 14px;
							line-height: 44px;
						}
					}
					.more_btn {
						cursor: pointer;
						padding: 0 20px;
						margin: 0;
						display: none;
						width: 100%;
						border-color: #efefef;
						border-width: 0 0 1px 0;
						border-style: solid;
						height: auto;
						.icon {
							margin: 0 5px 0 0;
							color: #333;
							font-size: 16px;
							line-height: 44px;
						}
						.text {
							color: #333;
							font-size: 16px;
							line-height: 44px;
						}
					}
				}
				.list-body {
					flex: 1;
					background: #fff;
					display: flex;
					justify-content: flex-start;
					flex-wrap: wrap;
					height: auto;
					.list-item {
						padding: 20px 10px 10px;
						width: 25%;
						position: relative;
						height: auto;
						img {
							object-fit: cover;
							display: block;
							width: 100%;
							height: 300px;
						}
						.name {
							padding: 0 10px;
							color: #000;
							font-size: 18px;
							line-height: 2;
						}
						.price {
							padding: 10px;
							color: #0AB8C1;
							font-size: 13px;
							line-height: 1.5;
						}
					}
				}
			}
			.moreBtn {
				border: 0;
				cursor: pointer;
				margin: 10px auto;
				top: 20px;
				background: none;
				display: block;
				width: auto;
				line-height: 70px;
				position: absolute;
				right: calc(16% + 50px);
				text-align: center;
				.text {
					color: #9E9E9E;
					font-size: 14px;
				}
				.icon {
					color: #9E9E9E;
					font-size: 14px;
				}
			}
		}
	}
</style>
