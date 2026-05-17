<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="150px"
			>
			<el-form-item class="add-item" label="捐赠编号" prop="juanzengbianhao">
				<el-input v-model="ruleForm.juanzengbianhao" placeholder="捐赠编号" disabled></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="项目名称" prop="xiangmumingcheng">
				<el-input v-model="ruleForm.xiangmumingcheng" 
					placeholder="项目名称" clearable :disabled=" false  ||ro.xiangmumingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="项目类型" prop="xiangmuleixing">
				<el-input v-model="ruleForm.xiangmuleixing" 
					placeholder="项目类型" clearable :disabled=" false  ||ro.xiangmuleixing"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="地区" prop="diqu">
				<el-input v-model="ruleForm.diqu" 
					placeholder="地区" clearable :disabled=" false  ||ro.diqu"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="图片" v-if="type!='cross' || (type=='cross' && !ro.tupian)" prop="tupian">
				<file-upload
					tip="点击上传图片"
					action="file/upload"
					:limit="3"
					:multiple="true"
					:fileUrls="ruleForm.tupian?ruleForm.tupian:''"
					@change="tupianUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="图片" prop="tupian">
				<img v-if="ruleForm.tupian.substring(0,4)=='http'" class="upload-img" v-bind:key="index" :src="ruleForm.tupian.split(',')[0]">
				<img v-else class="upload-img" v-bind:key="index" v-for="(item,index) in ruleForm.tupian.split(',')" :src="baseUrl+item">
			</el-form-item>
			<el-form-item class="add-item" label="捐赠金额" prop="yijuanjine">
				<el-input-number v-model="ruleForm.yijuanjine" placeholder="捐赠金额" :disabled=" false ||ro.yijuanjine"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="捐赠时间" prop="juanzengshijian">
				<el-date-picker
					:disabled="true  ||ro.juanzengshijian"
					value-format="yyyy-MM-dd HH:mm:ss"
					v-model="ruleForm.juanzengshijian" 
					type="datetime"
					placeholder="捐赠时间">
				</el-date-picker>
			</el-form-item>
			<el-form-item class="add-item" label="捐赠账号" prop="juanzengzhanghao">
				<el-input v-model="ruleForm.juanzengzhanghao" 
					placeholder="捐赠账号" clearable :disabled="true  ||ro.juanzengzhanghao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="捐赠姓名" prop="xingming">
				<el-input v-model="ruleForm.xingming" 
					placeholder="捐赠姓名" clearable :disabled="true  ||ro.xingming"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="募捐者账号" prop="mujuanzhezhanghao">
				<el-input v-model="ruleForm.mujuanzhezhanghao" 
					placeholder="募捐者账号" clearable :disabled="true  ||ro.mujuanzhezhanghao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="募捐者姓名" prop="mujuanzhexingming">
				<el-input v-model="ruleForm.mujuanzhexingming" 
					placeholder="募捐者姓名" clearable :disabled="true  ||ro.mujuanzhexingming"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="捐赠详情" prop="juanzengxiangqing">
				<el-input
					type="textarea"
					:rows="8"
					:disabled=" false  ||ro.juanzengxiangqing"
					placeholder="捐赠详情"
					v-model="ruleForm.juanzengxiangqing">
					</el-input>
			</el-form-item>

			<el-form-item class="add-btn-item">
				<el-button class="submitBtn"  type="primary" @click="onSubmit">
					<span class="icon iconfont icon-kaitongfuwu"></span>
					<span class="text">确定</span>
				</el-button>
				<el-button class="closeBtn" @click="back()">
					<span class="icon iconfont icon-shanchu1"></span>
					<span class="text">取消</span>
				</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				id: '',
				baseUrl: '',
				ro:{
					juanzengbianhao : false,
					xiangmumingcheng : false,
					xiangmuleixing : false,
					diqu : false,
					tupian : false,
					yijuanjine : false,
					juanzengxiangqing : false,
					juanzengshijian : false,
					juanzengzhanghao : false,
					xingming : false,
					mujuanzhezhanghao : false,
					mujuanzhexingming : false,
					ispay : false,
				},
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
				ruleForm: {
					juanzengbianhao: this.getUUID(),
					xiangmumingcheng: '',
					xiangmuleixing: '',
					diqu: '',
					tupian: '',
					yijuanjine: '',
					juanzengxiangqing: '',
					juanzengshijian: '',
					juanzengzhanghao: '',
					xingming: '',
					mujuanzhezhanghao: '',
					mujuanzhexingming: '',
				},


				rules: {
					juanzengbianhao: [
					],
					xiangmumingcheng: [
					],
					xiangmuleixing: [
					],
					diqu: [
					],
					tupian: [
					],
					yijuanjine: [
						{ required: true, message: '捐赠金额不能为空', trigger: 'blur' },
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					juanzengxiangqing: [
					],
					juanzengshijian: [
					],
					juanzengzhanghao: [
						{ required: true, message: '捐赠账号不能为空', trigger: 'blur' },
					],
					xingming: [
						{ required: true, message: '捐赠姓名不能为空', trigger: 'blur' },
					],
					mujuanzhezhanghao: [
						{ required: true, message: '募捐者账号不能为空', trigger: 'blur' },
					],
					mujuanzhexingming: [
						{ required: true, message: '募捐者姓名不能为空', trigger: 'blur' },
					],
					ispay: [
					],
				},
				centerType: false,
			};
		},
		computed: {



		},
		components: {
		},
		created() {
			if(this.$route.query.centerType){
				this.centerType = true
			}
			//this.bg();
			let type = this.$route.query.type ? this.$route.query.type : '';
			this.init(type);
			this.baseUrl = this.$config.baseUrl;
			this.ruleForm.juanzengshijian = this.getCurDateTime()
		},
		methods: {
			getMakeZero(s) {
				return s < 10 ? '0' + s : s;
			},
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(type) {
				this.type = type;
				if(type=='cross'){
					var obj = JSON.parse(localStorage.getItem('crossObj'));
					for (var o in obj){
						if(o=='juanzengbianhao'){
							this.ruleForm.juanzengbianhao = obj[o];
							this.ro.juanzengbianhao = true;
							continue;
						}
						if(o=='xiangmumingcheng'){
							this.ruleForm.xiangmumingcheng = obj[o];
							this.ro.xiangmumingcheng = true;
							continue;
						}
						if(o=='xiangmuleixing'){
							this.ruleForm.xiangmuleixing = obj[o];
							this.ro.xiangmuleixing = true;
							continue;
						}
						if(o=='diqu'){
							this.ruleForm.diqu = obj[o];
							this.ro.diqu = true;
							continue;
						}
						if(o=='tupian'){
							this.ruleForm.tupian = obj[o]?obj[o].split(",")[0]:'';
							this.ro.tupian = true;
							continue;
						}
						if(o=='yijuanjine'){
							this.ruleForm.yijuanjine = obj[o];
							this.ro.yijuanjine = true;
							continue;
						}
						if(o=='juanzengxiangqing'){
							this.ruleForm.juanzengxiangqing = obj[o];
							this.ro.juanzengxiangqing = true;
							continue;
						}
						if(o=='juanzengshijian'){
							this.ruleForm.juanzengshijian = obj[o];
							this.ro.juanzengshijian = true;
							continue;
						}
						if(o=='juanzengzhanghao'){
							this.ruleForm.juanzengzhanghao = obj[o];
							this.ro.juanzengzhanghao = true;
							continue;
						}
						if(o=='xingming'){
							this.ruleForm.xingming = obj[o];
							this.ro.xingming = true;
							continue;
						}
						if(o=='mujuanzhezhanghao'){
							this.ruleForm.mujuanzhezhanghao = obj[o];
							this.ro.mujuanzhezhanghao = true;
							continue;
						}
						if(o=='mujuanzhexingming'){
							this.ruleForm.mujuanzhexingming = obj[o];
							this.ro.mujuanzhexingming = true;
							continue;
						}
					}
					this.ruleForm.yijuanjine = 0;
					this.ro.yijuanjine = false;
				}else if(type=='edit'){
					this.info()
				}
				// 获取用户信息
				this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						var json = res.data.data;
						if((json.juanzengzhanghao!=''&&json.juanzengzhanghao) || json.juanzengzhanghao==0){
							this.ruleForm.juanzengzhanghao = json.juanzengzhanghao;
							this.ro.juanzengzhanghao = true;
						}
						if((json.xingming!=''&&json.xingming) || json.xingming==0){
							this.ruleForm.xingming = json.xingming;
							this.ro.xingming = true;
						}
					}
				});

				if (localStorage.getItem('raffleType') && localStorage.getItem('raffleType') != null) {
					localStorage.removeItem('raffleType')
					setTimeout(() => {
						this.onSubmit()
					}, 300)
				}
			},

			// 多级联动参数
			// 多级联动参数
			info() {
				this.$http.get(`juanzengjilu/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit() {
				if(this.ruleForm.juanzengbianhao){
					this.ruleForm.juanzengbianhao = String(this.ruleForm.juanzengbianhao)
				}
				if(!this.ruleForm.yijuanjine&&this.ruleForm.yijuanjine!=0){
					this.$message.error("捐赠金额不能为空");
					return
				}
				var obj = JSON.parse(localStorage.getItem('crossObj'));
				var table = localStorage.getItem('crossTable');
				obj.yijuanjine = Number((parseFloat(obj.yijuanjine) + parseFloat(this.ruleForm.yijuanjine)).toFixed(2))
				if(!this.ruleForm.id) {
					delete this.ruleForm.userid
				}
				await this.$refs["ruleForm"].validate(async valid => {
					if(valid) {
						if(this.type=='cross'){
							var statusColumnName = localStorage.getItem('statusColumnName');
							var statusColumnValue = localStorage.getItem('statusColumnValue');
							if(statusColumnName && statusColumnName!='') {
								var obj = JSON.parse(localStorage.getItem('crossObj'));
								if(!statusColumnName.startsWith("[")) {
									for (var o in obj){
										if(o==statusColumnName){
											obj[o] = statusColumnValue;
										}
									}
									var table = localStorage.getItem('crossTable');
									await this.$http.post(table+'/update', obj).then(res => {});
								}
							}
						}
						// 跨表计算
						var obj = JSON.parse(localStorage.getItem('crossObj'));
						var table = localStorage.getItem('crossTable');

						obj.yijuanjine = Number((parseFloat(obj.yijuanjine) + parseFloat(this.ruleForm.yijuanjine)).toFixed(2))

						await this.$http.post(table+`/update`,obj).then(res => {});
						await this.$http.post(`juanzengjilu/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
							if (res.data.code == 0) {
								this.$message({
									message: '操作成功',
									type: 'success',
									duration: 1500,
									onClose: () => {
										if(this.isBackAuth('juanzengjilu','支付')&&this.type=='cross'){
											this.$confirm('是否跳转支付？') .then(_ => {
												let jumpParams = {
													id: res.data.data,
													centerType: 1
												}
												this.$router.push({path: '/index/juanzengjiluDetail', query: jumpParams});
											}).catch(_ => {
												this.$router.go(-1);
											});
										}else {
											this.$router.go(-1);
										}
										
										
									}
								});
							} else {
								this.$message({
									message: res.data.msg,
									type: 'error',
									duration: 1500
								});
							}
						});
					}
				});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.$router.go(-1);
			},
			tupianUploadChange(fileUrls) {
				this.ruleForm.tupian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
			},
		}
	};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.add-update-preview {
		padding: 20px 16%;
		margin: 0px auto;
		background: #F2F3F7;
		width: 100%;
		position: relative;
		.add-update-form {
			width: 100%;
			position: relative;
			.add-item.el-form-item {
				padding: 10px;
				margin: 0 0 10px;
				background: none;
				display: inline-block;
				width: 48%;
				/deep/ .el-form-item__label {
					padding: 0 10px 0 0;
					color: #666;
					font-weight: 500;
					width: 150px;
					font-size: 14px;
					line-height: 40px;
					text-align: center;
				}
				/deep/ .el-form-item__content {
					margin-left: 150px;
				}
				.el-input {
					width: 100%;
				}
				.el-input /deep/ .el-input__inner {
					border: 1px solid #E2E3E5;
					border-radius: 0;
					padding: 0 12px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					width: 80%;
					font-size: 15px;
					height: 40px;
				}
				.el-input /deep/ .el-input__inner[readonly="readonly"] {
					border: 1px solid #E2E3E5;
					cursor: not-allowed;
					border-radius: 0;
					padding: 0 12px;
					box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
					outline: none;
					color: #000;
					background: #f8f8f8;
					width: 80%;
					font-size: 15px;
					height: 40px;
				}
				.el-input-number /deep/ .el-input__inner {
					text-align: left;
					border: 1px solid #E2E3E5;
					border-radius: 0;
					padding: 0 12px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					width: 80%;
					font-size: 15px;
					height: 40px;
				}
				.el-input-number /deep/ .is-disabled .el-input__inner {
					text-align: left;
					border: 1px solid #E2E3E5;
					cursor: not-allowed;
					border-radius: 0;
					padding: 0 12px;
					box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
					outline: none;
					color: #000;
					background: #f8f8f8;
					width: 80%;
					font-size: 15px;
					height: 40px;
				}
				.el-input-number /deep/ .el-input-number__decrease {
					display: none;
				}
				.el-input-number /deep/ .el-input-number__increase {
					display: none;
				}
				.el-select {
					width: 80%;
				}
				.el-select /deep/ .el-input__inner {
					border: 1px solid #E2E3E5;
					border-radius: 0;
					padding: 0 10px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					width: 100%;
					font-size: 15px;
					height: 40px;
				}
				.el-select /deep/ .is-disabled .el-input__inner {
					border: 1px solid #E2E3E5;
					cursor: not-allowed;
					border-radius: 0;
					padding: 0 10px;
					box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
					outline: none;
					color: #000;
					background: #f8f8f8;
					width: 100%;
					font-size: 15px;
					height: 40px;
				}
				.el-date-editor {
					width: 80%;
				}
				.el-date-editor /deep/ .el-input__inner {
					border: 1px solid #E2E3E5;
					border-radius: 0;
					padding: 0 10px 0 30px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					width: 100%;
					font-size: 15px;
					height: 40px;
				}
				.el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
					border: 1px solid #E2E3E5;
					cursor: not-allowed;
					border-radius: 0;
					padding: 0 10px 0 30px;
					box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
					outline: none;
					color: #000;
					background: #f8f8f8;
					width: 100%;
					font-size: 15px;
					height: 40px;
				}
				/deep/ .el-upload--picture-card {
					background: transparent;
					border: 0;
					border-radius: 0;
					width: auto;
					height: auto;
					line-height: initial;
					vertical-align: middle;
				}
				/deep/ .upload .upload-img {
					border: 1px dashed #999;
					cursor: pointer;
					border-radius: 6px;
					color: #999;
					width: 100px;
					font-size: 32px;
					line-height: 100px;
					text-align: center;
					height: 100px;
				}
				/deep/ .el-upload-list .el-upload-list__item {
					border: 1px dashed #999;
					cursor: pointer;
					border-radius: 6px;
					color: #999;
					width: 100px;
					font-size: 32px;
					line-height: 100px;
					text-align: center;
					height: 100px;
					font-size: 14px;
					line-height: 1.8;
				}
				/deep/ .el-upload .el-icon-plus {
					border: 1px dashed #999;
					cursor: pointer;
					border-radius: 6px;
					color: #999;
					width: 100px;
					font-size: 32px;
					line-height: 100px;
					text-align: center;
					height: 100px;
				}
				/deep/ .el-upload__tip {
					color: #838fa1;
					display: none;
				}
				.el-textarea /deep/ .el-textarea__inner {
					border: 1px solid #E2E3E5;
					border-radius: 0;
					padding: 12px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					width: 80%;
					font-size: 14px;
					height: 120px;
				}
				.el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
					border: 1px solid #E2E3E5;
					cursor: not-allowed;
					border-radius: 4px;
					padding: 12px;
					box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
					outline: none;
					color: #000;
					background: #f8f8f8;
					width: 80%;
					font-size: 14px;
					height: 120px;
				}
				/deep/ .el-input__inner::placeholder {
					color: #999;
					font-size: 14px;
				}
				/deep/ textarea::placeholder {
					color: #999;
					font-size: 14px;
				}
				.editor {
					background-color: #fff;
					border-radius: 0;
					padding: 0;
					box-shadow: 0 0 0px rgba(75,223,201,.5);
					margin: 0;
					width: 80%;
					border-color: #ccc;
					border-width: 0;
					border-style: solid;
					height: auto;
				}
				.upload-img {
					width: 100px;
					height: 100px;
				}
				.viewBtn {
					border: 0;
					cursor: pointer;
					padding: 0 10px;
					margin: 0;
					color: #fff;
					display: inline-block;
					font-size: 14px;
					line-height: 30px;
					border-radius: 4px;
					outline: none;
					background: #5AC3B2;
					width: auto;
					height: 30px;
				}
				.viewBtn:hover {
					color: #fff;
					background: #5AC3B290;
				}
				.unviewBtn {
					border: 0;
					cursor: not-allowed;
					padding: 0 10px;
					margin: 0;
					color: #ccc;
					display: inline-block;
					font-size: 14px;
					line-height: 30px;
					border-radius: 4px;
					outline: none;
					background: #858585;
					width: auto;
					height: 30px;
				}
				.unviewBtn:hover {
					color: #ccc;
					background: #858585;
				}
			}
			.add-btn-item {
				padding: 0;
				margin: 0;
				.submitBtn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 15px;
					margin: 0 20px 0 0;
					outline: none;
					background: #5AC3B2;
					display: inline-block;
					width: auto;
					font-size: 14px;
					line-height: 30px;
					height: 30px;
					.icon {
						color: rgba(255, 255, 255, 1);
						display: none;
					}
					.text {
						color: rgba(255, 255, 255, 1);
					}
				}
				.submitBtn:hover {
					background: #5AC3B290;
					.icon {
						color: #fff;
					}
					.text {
						color: #fff;
					}
				}
				.closeBtn {
					border: 0px solid rgba(64, 158, 255, 1);
					cursor: pointer;
					padding: 0 15px;
					margin: 0 20px 0 0;
					display: inline-block;
					font-size: 14px;
					line-height: 30px;
					border-radius: 4px;
					outline: none;
					background: #858585;
					width: auto;
					text-align: center;
					min-width: 100px;
					height: 30px;
					.icon {
						color: #fff;
						display: none;
					}
					.text {
						color: #fff;
					}
				}
				.closeBtn:hover {
					background: #85858590;
					.icon {
						color: #fff;
					}
					.text {
						color: #fff;
					}
				}
			}
		}
	}
	.el-date-editor.el-input {
		width: auto;
	}
</style>
