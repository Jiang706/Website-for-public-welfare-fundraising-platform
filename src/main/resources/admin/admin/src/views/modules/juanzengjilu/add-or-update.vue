<template>
	<div class="addEdit-block">
		<el-form
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
		>
			<template >
				<el-form-item class="input" v-if="type!='info'" label="捐赠编号" prop="juanzengbianhao" >
					<el-input v-model="ruleForm.juanzengbianhao" placeholder="捐赠编号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.juanzengbianhao" label="捐赠编号" prop="juanzengbianhao" >
					<el-input v-model="ruleForm.juanzengbianhao" placeholder="捐赠编号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="项目名称" prop="xiangmumingcheng" >
					<el-input v-model="ruleForm.xiangmumingcheng" placeholder="项目名称" clearable  :readonly="ro.xiangmumingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="项目名称" prop="xiangmumingcheng" >
					<el-input v-model="ruleForm.xiangmumingcheng" placeholder="项目名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="项目类型" prop="xiangmuleixing" >
					<el-input v-model="ruleForm.xiangmuleixing" placeholder="项目类型" clearable  :readonly="ro.xiangmuleixing"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="项目类型" prop="xiangmuleixing" >
					<el-input v-model="ruleForm.xiangmuleixing" placeholder="项目类型" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="地区" prop="diqu" >
					<el-input v-model="ruleForm.diqu" placeholder="地区" clearable  :readonly="ro.diqu"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="地区" prop="diqu" >
					<el-input v-model="ruleForm.diqu" placeholder="地区" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.tupian" label="图片" prop="tupian" >
					<file-upload
						tip="点击上传图片"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.tupian?ruleForm.tupian:''"
						@change="tupianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.tupian" label="图片" prop="tupian" >
					<img v-if="ruleForm.tupian.substring(0,4)=='http'&&ruleForm.tupian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.tupian" width="100" height="100">
					<img v-else-if="ruleForm.tupian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.tupian.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.tupian.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="捐赠金额" prop="yijuanjine" >
					<el-input-number v-model="ruleForm.yijuanjine" placeholder="捐赠金额" :disabled="ro.yijuanjine"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="捐赠金额" prop="yijuanjine" >
					<el-input v-model="ruleForm.yijuanjine" placeholder="捐赠金额" readonly></el-input>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="捐赠时间" prop="juanzengshijian" >
					<el-date-picker
						value-format="yyyy-MM-dd HH:mm:ss"
						v-model="ruleForm.juanzengshijian" 
						type="datetime"
						:readonly="ro.juanzengshijian"
						placeholder="捐赠时间"
					></el-date-picker>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.juanzengshijian" label="捐赠时间" prop="juanzengshijian" >
					<el-input v-model="ruleForm.juanzengshijian" placeholder="捐赠时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="捐赠账号" prop="juanzengzhanghao" >
					<el-input v-model="ruleForm.juanzengzhanghao" placeholder="捐赠账号" clearable  :readonly="ro.juanzengzhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="捐赠账号" prop="juanzengzhanghao" >
					<el-input v-model="ruleForm.juanzengzhanghao" placeholder="捐赠账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="捐赠姓名" prop="xingming" >
					<el-input v-model="ruleForm.xingming" placeholder="捐赠姓名" clearable  :readonly="ro.xingming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="捐赠姓名" prop="xingming" >
					<el-input v-model="ruleForm.xingming" placeholder="捐赠姓名" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="募捐者账号" prop="mujuanzhezhanghao" >
					<el-input v-model="ruleForm.mujuanzhezhanghao" placeholder="募捐者账号" clearable  :readonly="ro.mujuanzhezhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="募捐者账号" prop="mujuanzhezhanghao" >
					<el-input v-model="ruleForm.mujuanzhezhanghao" placeholder="募捐者账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="募捐者姓名" prop="mujuanzhexingming" >
					<el-input v-model="ruleForm.mujuanzhexingming" placeholder="募捐者姓名" clearable  :readonly="ro.mujuanzhexingming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="募捐者姓名" prop="mujuanzhexingming" >
					<el-input v-model="ruleForm.mujuanzhexingming" placeholder="募捐者姓名" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item class="textarea" v-if="type!='info'" label="捐赠详情" prop="juanzengxiangqing" >
				<el-input
					style="min-width: 200px; max-width: 600px;"
					type="textarea"
					:rows="8"
					placeholder="捐赠详情"
					v-model="ruleForm.juanzengxiangqing" >
				</el-input>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.juanzengxiangqing" label="捐赠详情" prop="juanzengxiangqing" >
				<span class="text">{{ruleForm.juanzengxiangqing}}</span>
			</el-form-item>
			<el-form-item class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-xihuan"></span>
					提交
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					取消
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					返回
				</el-button>
			</el-form-item>
		</el-form>
    

	</div>
</template>
<script>
	import { 
		isNumber,
	} from "@/utils/validate";
	export default {
		data() {
			var validateNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isNumber(value)) {
					callback(new Error("请输入数字"));
				} else {
					callback();
				}
			};
			return {
				id: '',
				type: '',
			
			
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
						{ validator: validateNumber, trigger: 'blur' },
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
			};
		},
		props: ["parent"],
		computed: {



		},
		components: {
		},
		created() {
			this.ruleForm.juanzengshijian = this.getCurDateTime()
		},
		methods: {
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(id,type) {
				if (id) {
					this.id = id;
					this.type = type;
				}
				if(this.type=='info'||this.type=='else'||this.type=='msg'){
					this.info(id);
				}else if(this.type=='logistics'){
					for(let x in this.ro) {
						this.ro[x] = true
					}
					this.logistics=false;
					this.info(id);
				}else if(this.type=='cross'){
					var obj = this.$storage.getObj('crossObj');
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
							this.ruleForm.tupian = obj[o];
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
					this.ruleForm.yijuanjine = 0
					this.ro.yijuanjine = false;
				}
				// 获取用户信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(this.$storage.get("role")!="管理员") {
							this.ro.juanzengshijian = true;
						}
						if(((json.juanzengzhanghao!=''&&json.juanzengzhanghao) || json.juanzengzhanghao==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.juanzengzhanghao = json.juanzengzhanghao
							this.ro.juanzengzhanghao = true;
						}
						if(this.$storage.get("role")!="管理员") {
							this.ro.juanzengzhanghao = true;
						}
						if(((json.xingming!=''&&json.xingming) || json.xingming==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.xingming = json.xingming
							this.ro.xingming = true;
						}
						if(this.$storage.get("role")!="管理员") {
							this.ro.xingming = true;
						}
						if(this.$storage.get("role")!="管理员") {
							this.ro.mujuanzhezhanghao = true;
						}
						if(this.$storage.get("role")!="管理员") {
							this.ro.mujuanzhexingming = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `juanzengjilu/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(!this.ruleForm.id) {
						this.ruleForm.ispay = '未支付'
					}
					if(this.ruleForm.juanzengbianhao) {
						this.ruleForm.juanzengbianhao = String(this.ruleForm.juanzengbianhao)
					}
					if(this.ruleForm.tupian!=null) {
						this.ruleForm.tupian = this.ruleForm.tupian.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
					var table = this.$storage.getObj('crossTable');
					if(objcross!=null) {
						if(!this.ruleForm.yijuanjine){
							this.$message.error("捐赠金额不能为空");
							return
						}
						objcross.yijuanjine = parseFloat(objcross.yijuanjine) + parseFloat(this.ruleForm.yijuanjine)
											}
					if(!this.ruleForm.id) {
						delete this.ruleForm.userid
					}
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							if(this.type=='cross'){
								var statusColumnName = this.$storage.get('statusColumnName');
								var statusColumnValue = this.$storage.get('statusColumnValue');
								if(statusColumnName!='') {
									var obj = this.$storage.getObj('crossObj');
									if(statusColumnName && !statusColumnName.startsWith("[")) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = this.$storage.get('crossTable');
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: obj
										}).then(({ data }) => {});
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: objcross
										}).then(({ data }) => {});
									}
								}
							}
							
							await this.$http({
								url: `juanzengjilu/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											if(this.isAuth('juanzengjilu','支付')&&this.type=='cross') {
												this.$confirm('是否跳转支付？').then(_ => {
													this.parent.showFlag = true;
													this.parent.addOrUpdateFlag = false;
													this.parent.juanzengjiluCrossAddOrUpdateFlag = false;
													this.$router.push('/juanzengjilu')
												}).catch(_ => {
													this.parent.showFlag = true;
													this.parent.addOrUpdateFlag = false;
													this.parent.juanzengjiluCrossAddOrUpdateFlag = false;
													this.parent.search();
													this.parent.contentStyleChange();
												});
											}else {
												this.parent.showFlag = true;
												this.parent.addOrUpdateFlag = false;
												this.parent.juanzengjiluCrossAddOrUpdateFlag = false;
												this.parent.search();
												this.parent.contentStyleChange();
											}
											
										}
									});
									var table = this.$storage.get('crossTable');
									await this.$http({
										url: `${table}/update`,
										method: "post",
										data: objcross
									}).then(({ data }) => {});
								} else {
									this.$message.error(data.msg);
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
				this.parent.showFlag = true;
				this.parent.addOrUpdateFlag = false;
				this.parent.juanzengjiluCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			tupianUploadChange(fileUrls) {
				this.ruleForm.tupian = fileUrls;
			},
		}
	};
</script>
<style lang="scss" scoped>
	.addEdit-block {
		padding: 30px;
	}
	.add-update-preview {
		padding: 40px 30px;
		background: #fff;
		border-color: #eee;
		border-width: 0px 0 0;
		border-style: solid;
	}
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	.add-update-preview /deep/ .el-form-item {
		border: 0px solid #eee;
		padding: 0;
		margin: 0 0 26px 0;
		display: inline-block;
		width: 49%;
	}
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #6e6e6e;
		font-weight: 500;
		width: 180px;
		font-size: 15px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: 180px;
	}
	.add-update-preview .el-form-item span.text {
		padding: 0 10px;
		color: #333;
		background: none;
		font-weight: 500;
		display: inline-block;
		font-size: 15px;
		line-height: 40px;
		min-width: 50%;
	}
	
	.add-update-preview .el-input {
		width: 100%;
	}
	.add-update-preview .el-input /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 15px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input /deep/ .el-input__inner[readonly="readonly"] {
		border: 0px solid #ccc;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 15px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .is-disabled .el-input__inner {
		text-align: left;
		border: 0px solid #ccc;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	.add-update-preview .el-select {
		width: 100%;
	}
	.add-update-preview .el-select /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 10px;
		color: #666;
		width: 100%;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-select /deep/ .is-disabled .el-input__inner {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 10px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 34px;
	}
	.add-update-preview .el-date-editor {
		width: 100%;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
		border: 0;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .viewBtn {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .downBtn {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .unBtn {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 0px;
		margin: 0 20px 0 0;
		outline: none;
		color: #999;
		background: none;
		width: auto;
		font-size: 15px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 34px;
		}
	}
	.add-update-preview .unBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	.add-update-preview /deep/ .el-upload__tip {
		color: #666;
		font-size: 15px;
	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 12px;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		min-width: 400px;
		height: 120px;
	}
	.add-update-preview .el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
				border: 0;
				cursor: not-allowed;
				border-radius: 0px;
				padding: 12px;
				color: #666;
				background: none;
				width: auto;
				font-size: 15px;
				min-width: 400px;
				height: auto;
			}
	.add-update-preview .el-form-item.btn {
		padding: 0;
		margin: 20px 0 0;
		.btn1 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #7841f0;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn1:hover {
			opacity: 0.8;
		}
		.btn2 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #39c9ee;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 34px;
			}
		}
		.btn2:hover {
			opacity: 0.8;
		}
		.btn3 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #9e46d1;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn3:hover {
			opacity: 0.8;
		}
		.btn4 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #70478e;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn4:hover {
			opacity: 0.8;
		}
		.btn5 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #b49be2;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn5:hover {
			opacity: 0.8;
		}
	}
</style>
