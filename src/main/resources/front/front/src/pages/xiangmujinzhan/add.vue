<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="150px"
			>
			<el-form-item class="add-item" label="项目名称" prop="xiangmumingcheng">
				<el-input v-model="ruleForm.xiangmumingcheng" 
					placeholder="项目名称" clearable :disabled=" false  ||ro.xiangmumingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item"  label="项目类型" prop="xiangmuleixing">
				<el-select v-model="ruleForm.xiangmuleixing" placeholder="请选择项目类型" :disabled=" false  ||ro.xiangmuleixing" >
					<el-option
						v-for="(item,index) in xiangmuleixingOptions"
						:key="index"
						:label="item"
						:value="item">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item class="add-item"  label="地区" prop="diqu">
				<el-select v-model="ruleForm.diqu" placeholder="请选择地区" :disabled=" false  ||ro.diqu" >
					<el-option
						v-for="(item,index) in diquOptions"
						:key="index"
						:label="item"
						:value="item">
					</el-option>
				</el-select>
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
			<el-form-item class="add-item"  label="项目进展" prop="xiangmujinzhan">
				<el-select v-model="ruleForm.xiangmujinzhan" placeholder="请选择项目进展" :disabled=" false  ||ro.xiangmujinzhan" >
					<el-option
						v-for="(item,index) in xiangmujinzhanOptions"
						:key="index"
						:label="item"
						:value="item">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item class="add-item" label="登记时间" prop="dengjishijian">
				<el-date-picker
					:disabled=" false  ||ro.dengjishijian"
					value-format="yyyy-MM-dd HH:mm:ss"
					v-model="ruleForm.dengjishijian" 
					type="datetime"
					placeholder="登记时间">
				</el-date-picker>
			</el-form-item>
			<el-form-item class="add-item" label="募捐者账号" prop="mujuanzhezhanghao">
				<el-input v-model="ruleForm.mujuanzhezhanghao" 
					placeholder="募捐者账号" clearable :disabled=" false  ||ro.mujuanzhezhanghao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="募捐者姓名" prop="mujuanzhexingming">
				<el-input v-model="ruleForm.mujuanzhexingming" 
					placeholder="募捐者姓名" clearable :disabled=" false  ||ro.mujuanzhexingming"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="进展详情" prop="jinzhanxiangqing">
				<editor 
					v-model="ruleForm.jinzhanxiangqing" 
					class="editor" 
					myQuillEditor="jinzhanxiangqing"
					action="file/upload">
				</editor>
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
					xiangmumingcheng : false,
					xiangmuleixing : false,
					diqu : false,
					tupian : false,
					xiangmujinzhan : false,
					dengjishijian : false,
					jinzhanxiangqing : false,
					mujuanzhezhanghao : false,
					mujuanzhexingming : false,
					thumbsupnum : false,
					crazilynum : false,
					discussnum : false,
					storeupnum : false,
				},
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
				ruleForm: {
					xiangmumingcheng: '',
					xiangmuleixing: '',
					diqu: '',
					tupian: '',
					xiangmujinzhan: '',
					dengjishijian: '',
					jinzhanxiangqing: '',
					mujuanzhezhanghao: '',
					mujuanzhexingming: '',
					thumbsupnum: '',
					crazilynum: '',
					discussnum: '',
					storeupnum: '',
				},
				xiangmuleixingOptions: [],
				diquOptions: [],
				xiangmujinzhanOptions: [],


				rules: {
					xiangmumingcheng: [
					],
					xiangmuleixing: [
						{ required: true, message: '项目类型不能为空', trigger: 'blur' },
					],
					diqu: [
					],
					tupian: [
					],
					xiangmujinzhan: [
					],
					dengjishijian: [
					],
					jinzhanxiangqing: [
					],
					mujuanzhezhanghao: [
					],
					mujuanzhexingming: [
					],
					thumbsupnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					crazilynum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					discussnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					storeupnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
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
			this.ruleForm.dengjishijian = this.getCurDateTime()
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
						if(o=='xiangmujinzhan'){
							this.ruleForm.xiangmujinzhan = obj[o];
							this.ro.xiangmujinzhan = true;
							continue;
						}
						if(o=='dengjishijian'){
							this.ruleForm.dengjishijian = obj[o];
							this.ro.dengjishijian = true;
							continue;
						}
						if(o=='jinzhanxiangqing'){
							this.ruleForm.jinzhanxiangqing = obj[o];
							this.ro.jinzhanxiangqing = true;
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
						if(o=='thumbsupnum'){
							this.ruleForm.thumbsupnum = obj[o];
							this.ro.thumbsupnum = true;
							continue;
						}
						if(o=='crazilynum'){
							this.ruleForm.crazilynum = obj[o];
							this.ro.crazilynum = true;
							continue;
						}
						if(o=='discussnum'){
							this.ruleForm.discussnum = obj[o];
							this.ro.discussnum = true;
							continue;
						}
						if(o=='storeupnum'){
							this.ruleForm.storeupnum = obj[o];
							this.ro.storeupnum = true;
							continue;
						}
					}
				}else if(type=='edit'){
					this.info()
				}
				// 获取用户信息
				this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						var json = res.data.data;
					}
				});
				this.$http.get('option/xiangmuleixing/xiangmuleixing', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.xiangmuleixingOptions = res.data.data;
					}
				});
				this.$http.get('option/diquxinxi/diqu', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.diquOptions = res.data.data;
					}
				});
				this.xiangmujinzhanOptions = "已完成,进行中,待开始".split(',')

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
				this.$http.get(`xiangmujinzhan/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit() {
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


						await this.$http.post(`xiangmujinzhan/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
							if (res.data.code == 0) {
								this.$message({
									message: '操作成功',
									type: 'success',
									duration: 1500,
									onClose: () => {
										this.$router.go(-1);
										
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
