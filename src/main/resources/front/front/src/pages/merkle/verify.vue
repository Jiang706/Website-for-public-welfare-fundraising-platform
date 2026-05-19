<template>
  <div class="verify-page">
    <div class="breadcrumb-preview">
      <el-breadcrumb :separator="'-'">
        <el-breadcrumb-item to="/"><a>首页</a></el-breadcrumb-item>
        <el-breadcrumb-item><a>Merkle 验证</a></el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="verify-card">
      <el-form :model="form" label-width="140px">
        <el-form-item label="Donation ID">
          <el-input v-model="form.donationId" placeholder="请输入捐赠记录ID"></el-input>
        </el-form-item>
        <el-form-item label="Leaf Hash (可选)">
          <el-input v-model="form.leafHash" placeholder="可选：覆盖数据库中的leafHash"></el-input>
        </el-form-item>
        <el-form-item label="Root Hash (可选)">
          <el-input v-model="form.rootHash" placeholder="可选：覆盖数据库中的rootHash"></el-input>
        </el-form-item>
        <el-form-item label="Proof JSON (可选)">
          <el-input type="textarea" :rows="8" v-model="form.proof" placeholder="可选：覆盖数据库中的proof"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="verifyTap">开始验证</el-button>
          <el-button @click="loadProofTap">先加载数据库证明</el-button>
        </el-form-item>
      </el-form>

      <div v-if="result" class="result-box">
        <div class="status" :class="result.valid ? 'ok' : 'fail'">
          {{ result.valid ? '验证通过' : '验证失败' }}
        </div>
        <pre>{{ JSON.stringify(result, null, 2) }}</pre>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        donationId: '',
        leafHash: '',
        rootHash: '',
        proof: ''
      },
      result: null
    }
  },
  created() {
    if (this.$route.query && this.$route.query.donationId) {
      this.form.donationId = this.$route.query.donationId
      this.loadProofTap()
    }
  },
  methods: {
    verifyTap() {
      if (!this.form.donationId) {
        this.$message.error('donationId不能为空')
        return
      }
      const payload = {
        donationId: this.form.donationId
      }
      if (this.form.leafHash) payload.leafHash = this.form.leafHash
      if (this.form.rootHash) payload.rootHash = this.form.rootHash
      if (this.form.proof) payload.proof = this.form.proof

      this.$http.post('merkle/verify', payload).then(res => {
        if (res.data.code === 0) {
          this.result = res.data.data
        } else {
          this.$message.error(res.data.msg)
        }
      })
    },
    loadProofTap() {
      if (!this.form.donationId) {
        this.$message.error('donationId不能为空')
        return
      }
      this.$http.get(`merkle/proof/${this.form.donationId}`).then(res => {
        if (res.data.code === 0) {
          const data = res.data.data
          this.form.leafHash = data.leafHash || ''
          this.form.rootHash = data.rootHash || ''
          this.form.proof = data.proof || ''
          this.result = null
          this.$message.success('已加载证明数据')
        } else {
          this.$message.error(res.data.msg)
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.verify-page {
  padding: 20px 16%;
  background: #f2f3f7;
  min-height: 500px;
}
.verify-card {
  margin-top: 12px;
  background: #fff;
  padding: 20px;
}
.result-box {
  margin-top: 16px;
}
.status {
  font-size: 18px;
  margin-bottom: 10px;
}
.status.ok {
  color: #2ea44f;
}
.status.fail {
  color: #d1242f;
}
pre {
  background: #111827;
  color: #f8fafc;
  padding: 12px;
  overflow-x: auto;
}
</style>
