<template>
  <div class="update">
    <Form ref="form">
      <Field class="cell"
             v-model="accounts.realName"
             name="realName"
             label="姓名"
             required
             input-align="right"
             placeholder="姓名"
             :rules="[{ required: true, message: '请填写姓名' }]"
      />
      <Field class="cell"
             v-model="accounts.age"
             name="age" type="number"
             label="年龄" input-align="right"
             placeholder="年龄"
      />

      <Cell title="性别" title-class="title-class" value-class="value-class" :value="sexName" @click="alertSex" is-link />

      <Field class="cell"
             v-model="accounts.major"
             name="major" label="所学专业"
             input-align="right"
             required
             placeholder="所学专业"
      />

      <Field class="cell"
             v-model="accounts.school"
             name="school" label="毕业院校"
             input-align="right"
             required
             placeholder="毕业院校"
      />

      <Cell title="学历" required title-class="title-class" value-class="value-class" :value="educationName" @click="alertEducation" is-link />

      <Field class="cell"
             v-model="accounts.job"
             name="job"
             required
             label="期望从事职业"
             input-align="right"
             placeholder="期望从事职业"
      />
        <Cell title="期望就业地区" required title-class="title-class" value-class="value-class" :value="areaName"   @click="alertArea" is-link />


      <ActionSheet
        v-model="showEducation"
        :actions="actionEducation"
        @close="onEducationClose"
        @select="onEducationSelect"
        cancel-text="取消"
      />
      <ActionSheet
        v-model="showSex"
        :actions="actionSex"
        @close="onSexClose"
        @select="onSexSelect"
        cancel-text="取消"
      />

      <Popup v-model="showArea" position="bottom">
        <Area
          :value="accounts.area"
          name="area"
          title="期望就业地区"
          :area-list="areaList"
          :columns-num="2"
          @confirm="loadArea"
          @cancel="closeArea"
        />
      </Popup>


      <div class="upload">
        <Button :loading="loading" block @click="updating" is-link>提交</Button>
      </div>
    </Form>
  </div>
</template>

<script>
    import {
        Form,
        Field,
        Button,
        Uploader,
        Area,
        Toast,
        Cell,
        CellGroup,
        Icon,
        Popup,
        ActionSheet
    } from "vant";

import { editUser, getUser } from "../../api/user";
import areaList from "../../utils/area";


export default {
  name: "",
  data() {
    return {
      loading: false,
      sex: { 男: 1, 女: 0 },
      getSex: { 1: "男", 0: "女" },
      sexName: null,
      education:{ 高中及以下: 0, 大专: 1, 本科: 2, 硕士: 3,博士及以上: 4 },
      getEducation:{ 0: "高中及以下", 1: "大专", 2: "本科", 3: "硕士",4: "博士及以上" },
      educationName: null,
      areaName: null,
      uploader: [],
      accounts: {},
      areaList: areaList,
      showEducation: false,
      showSex: false,
      showArea: false,
      actionEducation: [
          {
              name: '高中及以下',
          },
          {
              name: '大专',
          },
          {
              name: '本科',
          },
          {
              name: '硕士'
          },
          {
              name: '博士及以上'
          }
      ],
      actionSex: [
          {
              name: '男',
          },
          {
              name: '女',
          }
      ]
    };
  },

  components: {
    Form,
    Field,
    Button,
    Uploader,
    Area,
    Toast,
    Cell,
    CellGroup,
    Icon,
    Popup,
    ActionSheet
  },
  created() {
    getUser()
      .then(data => {
        this.accounts = data;
        if (data.gender != null){
            this.sexName = this.getSex[data.gender]
        }
        if (data.education != null){
            this.educationName = this.getEducation[data.education]
        }
        if (data.area != null) {
            let province = this.areaList.province_list[data.area.substring(0, 2) + "0000"];
            this.areaName = province + "-" + this.areaList.city_list[data.area]
        }
      })
      .catch(() => {
        Toast("系统繁忙，请稍后重试");
      });
  },
  methods: {
    updating() {
      console.log(this.accounts)
      if (!this.userVerify()){
          return;
      }
      this.loading = true;
      editUser(this.accounts)
        .then(data => {
          window.location.href = "/user/home.html";
        })
        .catch(({ message }) => {
          Toast(message);
        })
        .finally(() => {
          this.loading = false;
        });
    },
    userVerify(){
        if (!this.accounts.realName){
            Toast("姓名不能为空");
            return false;
        }
        if (this.accounts.realName.length>=20){
            Toast("姓名不合理");
            return false;
        }

        if (this.accounts.age && !/^\d{1,3}$/.test(this.accounts.age)){
            Toast("年龄不合理");
            return false;
        }

        if (this.accounts.gender==null){
            Toast("性别不能为空");
            return false;
        }


        if (!this.accounts.major&&this.accounts.education!=0){
            Toast("所学专业不能为空");
            return false;
        }
        if (this.accounts.major.length>=20){
            Toast("所学专业不合理");
            return false;
        }

        if (!this.accounts.school){
            Toast("毕业院校不能为空");
            return false;
        }
        if (this.accounts.school.length>=20){
            Toast("毕业院校不合理");
            return false;
        }
        if (this.accounts.education==null){
            Toast("学历不能为空");
            return false;
        }

        if (!this.accounts.job){
            Toast("期待从事职业不能为空");
            return false;
        }
        if (this.accounts.school.job>=20){
            Toast("期待从事职业不合理");
            return false;
        }

        if (this.accounts.area==null){
            Toast("期待就业地区不能为空");
            return false;
        }
        return true;

    },
    loadArea(attr) {
      this.accounts.area = attr[1].code;
      this.areaName = attr[0].name + "/" + attr[1].name;
      this.showArea = false;
    },
    onEducationClose() {
        this.showEducation= false ;
    },
    onSexClose() {
        this.showSex= false ;
    },

    onEducationSelect(event) {
        this.educationName = event.name;
        this.accounts.education = this.education[event.name];
        this.showEducation= false ;
    },
    onSexSelect(event) {
        this.sexName = event.name;
        this.accounts.gender = this.sex[event.name];
        this.showSex= false ;
    },
    alertEducation() {
      this.showEducation = true;
    },
    alertSex() {
        this.showSex = true;
    },
    alertArea() {
        this.showArea = true;
    },
    closeArea() {
        this.showArea = false;
    }
  }
};
</script>

<style lang="scss">
.upload {
  button {
    width: 6.9rem;
    height: 1rem;
    margin: 16px auto;
    color: #fe9791;
  }
}
.title-class{
  color: #b6b5b5;
}
.value-class{
  color: black;
}
.update {
  form {
    .cell {
      .van-cell__title {
        span {
          font-size: 0.28rem;
          color: #b6b5b5;
        }
      }
    }
  }
}
</style>
