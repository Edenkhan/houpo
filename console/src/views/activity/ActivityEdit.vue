<template>
  <div class="edit-container">
    <a-form-model @submit="handleSubmit" :loading="loading" :label-col="{ span: 4 }" :wrapper-col="{ span: 8 }">
      <a-form-model-item autocomplete="off" label="活动标题" >
        <a-input autofocus v-model="activityForm.eventTitle" />
      </a-form-model-item>

      <a-form-model-item label="活动图片">
        <a-upload
          name="file"
          list-type="picture-card"
          class="avatar-uploader"
          :show-upload-list="false"
          action="/api/activity/upload"
          :before-upload="beforeUpload"
          @change="handleChange"
        >
          <img v-if="activityForm.eventImage" :src="activityForm.eventImage" alt="picture" />
          <div v-else>
            <a-icon :type="loading ? 'loading' : 'plus'" />
            <div class="ant-upload-text">
              上传
            </div>
          </div>
        </a-upload>
      </a-form-model-item>

      <a-form-model-item autocomplete="off" label="活动内容">
        <div id="editor" style="height: 400px;width: 650px"></div>
      </a-form-model-item>

      <a-form-model-item label="是否开启报名" >
        <a-radio-group v-model="activityForm.enrollStatus">
          <a-radio-button :value="1">
            <a-icon type="unlock"/>
            开启
          </a-radio-button>
          <a-radio-button :value="0">
            <a-icon type="lock"/>
            关闭
          </a-radio-button>
        </a-radio-group>
      </a-form-model-item>

      <a-form-model-item label="是否发布" >
        <a-radio-group v-model="activityForm.releaseStatus">
          <a-radio-button :value="1">
            <a-icon type="unlock"/>
            发布
          </a-radio-button>
          <a-radio-button :value="0">
            <a-icon type="lock"/>
            不发布
          </a-radio-button>
        </a-radio-group>
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


import {addActivity, editActivity, getActivity} from "../../api/activity"
import E from 'wangeditor'

  export default {
    data() {
      return {
        id: this.$route.query.id,
        activityForm: {},
        loading: false,
        fetching: false,
        editor: {}
      }
    },

    created() {
      if (this.id != null) {
        this.fetchActivity()
      }
    },

    mounted() {
      this.editor = new E('#editor')
      // 创建一个富文本编辑器
      this.editor.create()
    },

    methods: {
      fetchActivity() {
        this.fetching = true
        getActivity(this.id)
          .then(data => {
            this.activityForm = data
            //回顯富文本
            this.editor.txt.html(data.eventContent)
            this.fetching = false
          })
          .catch(({message}) => {
            this.$message.error('获取失败。' + message)
          })
      },

      save(values) {
        this.loading = true

        if (this.id == null) {
          return addActivity(values)
            .then(() => {
              this.$message.success('添加成功')
              this.$router.push('/activity/list')
            })
            .catch(({message}) => {
              this.$message.error('添加失败。' + message)
            })
            .then(() => {
              this.loading = false
            })
        }

        return editActivity({
          id: this.id,
          ...values
        }).then(() => {
          this.$message.success('修改成功')
          this.$router.push('/activity/list')
        }).catch(({message}) => {
          this.$message.error('修改失败。' + message)
        }).then(() => {
          this.loading = false
        })
      },

      handleSubmit(e) {
        e.preventDefault()
        if(this.activityForm == null) {
          return
        }
        this.activityForm = Object.assign({},this.activityForm,{
          // 富文本内容
          eventContent: this.editor.txt.html()
        })
        this.save(this.activityForm)
      },

      handleChange(info) {
        if (info.file.status === 'done') {
          this.activityForm.eventImage = info.file.response.eventImage
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
      }



    }
    
  }
</script>
