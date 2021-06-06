<template>
  <div class="edit-container">
    <a-form-model @submit="handleSubmit" :loading="loading" :label-col="{ span: 4 }" :wrapper-col="{ span: 8 }">
      <a-form-model-item autocomplete="off" label="名称" >
        <a-input autofocus v-model="bannerForm.bannerName" />
      </a-form-model-item>
      <a-form-model-item autocomplete="off" label="链接地址">
        <a-input v-model="bannerForm.linkUrl"/>
      </a-form-model-item>
      <a-form-model-item label="是否启用" >
        <a-radio-group v-model="bannerForm.status">
          <a-radio-button :value="false">
            <a-icon type="unlock"/>
            启用
          </a-radio-button>
          <a-radio-button :value="true">
            <a-icon type="lock"/>
            停用
          </a-radio-button>
        </a-radio-group>
      </a-form-model-item>
      <a-form-model-item label="上传轮播图">
        <a-upload
          name="avatar"
          list-type="picture-card"
          class="avatar-uploader"
          :show-upload-list="false"
          action=""
          :before-upload="beforeUpload"
          @change="handleChange"
        >
          <img v-if="this.bannerForm.imageUrl" :src="this.bannerForm.imageUrl" alt="avatar" />
          <div v-else>
            <a-icon :type="loading ? 'loading' : 'plus'" />
            <div class="ant-upload-text">
              上传
            </div>
          </div>
        </a-upload>
      </a-form-model-item>
      <a-form-model-item :wrapper-col="{ offset: 4, span: 8 }">
        <a-button html-type="submit" type="primary" :disabled="fetching" :loading="loading">保存</a-button>
      </a-form-model-item>
    </a-form-model>
  </div>
</template>

<style scoped lang="scss">
</style>


<script>
function getBase64(img, callback) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result));
  reader.readAsDataURL(img);
}


  import {addBanner, editBanner, getBanner} from "../../api/banner.js"

  export default {
    data() {
      return {
        id: this.$route.query.id,
        bannerForm: {},
        loading: false,
        fetching: false
      }
    },

    created() {
      if (this.id != null) {
        this.fetchBanner()
      }
    },

    methods: {
      fetchBanner() {
        this.fetching = true
        getBanner({id: this.id})
          .then(data => {
            this.bannerForm = data
            this.fetching = false
          })
          .catch(({message}) => {
            this.$message.error('获取失败。' + message)
          })
      },

      save(values) {
        this.loading = true

        if (this.id == null) {
          return addBanner(values)
            .then(() => {
              this.$message.success('添加成功')
              this.$router.push('/banner/list')
            })
            .catch(({message}) => {
              this.$message.error('添加失败。' + message)
            })
            .then(() => {
              this.loading = false
            })
        }

        return editBanner({
          id: this.id,
          ...values
        }).then(() => {
          this.$message.success('修改成功')
          this.$router.push('/banner/list')
        }).catch(({message}) => {
          this.$message.error('修改失败。' + message)
        }).then(() => {
          this.loading = false
        })
      },

      handleSubmit(e) {
        e.preventDefault()
        if(this.bannerForm == null) {
          return
        }
        this.save(this.bannerForm)
      },

      handleChange(info) {
        if (info.file.status === 'uploading') {
          this.loading = true
          return
        }
        if (info.file.status === 'done') {
          // Get this url from response in real world.
          getBase64(info.file.originFileObj, imageUrl => {
            this.bannerForm.imageUrl = imageUrl
            this.loading = false
          })
        }
      },
      beforeUpload(file) {
        const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png'
        if (!isJpgOrPng) {
          this.$message.error('You can only upload JPG file!')
        }
        const isLt2M = file.size / 1024 / 1024 < 2
        if (!isLt2M) {
          this.$message.error('Image must smaller than 2MB!')
        }
        return isJpgOrPng && isLt2M
      },
    }
    
  }
</script>
