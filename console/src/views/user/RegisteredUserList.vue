<template>
  <div>
    <a-form-model @submit.prevent="handleSubmit" layout="inline" >
      <a-form-model-item label='姓名'>
        <a-input v-model="userListForm.realName"/>
      </a-form-model-item>
      <a-form-model-item label='注册时间' class="date-start">
        <a-date-picker v-model="userListForm.startCreatedDate" style="width: 120px"></a-date-picker>
      </a-form-model-item>
      <a-form-model-item label='-' :colon="false">
        <a-date-picker v-model="userListForm.endCreatedDate" style="width: 120px"></a-date-picker>
      </a-form-model-item>
      <a-form-model-item label='联系电话'>
        <a-input v-model="userListForm.phoneNumber" style="width: 150px"/>
      </a-form-model-item>
      <a-form-model-item label="是否锁定">
        <a-radio-group v-model="userListForm.locked">
          <a-radio-button :value="null">全部</a-radio-button>
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
      <a-form-model-item>
        <a-button type='primary' htmlType="submit">
          <a-icon type="search"/>
          查询
        </a-button>
      </a-form-model-item>
    </a-form-model>
    <div class="actions">
      <a-button type="primary">
        <a-icon type="download" />
        导出
      </a-button>
    </div>
    <a-table
      :columns="columns"
      rowKey="id"
      :dataSource="data"
      :pagination="pagination"
      :loading="loading"
      bordered
      @change="handleTableChange">
      <template slot="createdDate" slot-scope="createdDate">
        {{createdDate | filterDate('YYYY-MM-DD HH:mm:ss')}}
      </template>
      <template slot="gender" slot-scope="gender">
        <Gender :value="gender"/>
      </template>
      <template slot="idCardNumber" slot-scope="idCardNumber">
        <IdCardNumber :value="idCardNumber"/>
      </template>
      <template slot="phoneNumber" slot-scope="phoneNumber">
        <PhoneNumber :value="phoneNumber"/>
      </template>
      <template slot="type" slot-scope="type">
        <UserType :value="type"/>
      </template>
      <template slot="locked" slot-scope="locked">
        <a-tag color="#87d068" v-if="!locked">未锁定</a-tag>
        <a-tag color="#f5222d" v-if="locked">已锁定</a-tag>
      </template>
      <template slot="operation" slot-scope="record">
        <a-radio-group @click="changeLocked(record.id,value)">
          <a-radio-button :value="false">
            <a-icon type="unlock"/>
            启用
          </a-radio-button>
          <a-radio-button :value="true">
            <a-icon type="lock"/>
            停用
          </a-radio-button>
        </a-radio-group>
      </template>
    </a-table>
  </div>
</template>

<script>
  import {listUsers} from "../../api/user"
  import moment from "moment"
  import Gender from "./Gender"
  import PhoneNumber from "./PhoneNumber"

  const columns = [
    {
      title: '注册时间',
      dataIndex: 'createdDate',
      sorter: true,
      defaultSortOrder: 'descend',
      scopedSlots: {customRender: 'createdDate'}
    },
    {
      title: '基本信息',
      dataIndex: 'userBasicInfo',
      scopedSlots: {customRender: 'userBasicInfo'}
    },
    {
      title: '职业信息',
      dataIndex: 'userProfessionInfo',
      scopedSlots: {customRender: 'userProfessionInfo'}
    },
    {
      title: '状态',
      dataIndex: 'status',
      scopedSlots: {customRender: 'status'}
    },
    {
      title: '是否锁定',
      dataIndex: 'locked',
      scopedSlots: {customRender: 'locked'}
    },
    {
      title: '操作',
      scopedSlots: {customRender: 'operation'}
    }
  ]

  export default {
    components: {
      Gender,
      PhoneNumber
    },
    data() {
      return {
        columns,
        data: [],
        pagination: {
          current: 1,
          pageSize: 10,
          total: 0,
          showTotal(total) {
            return `共 ${total} 项`
          }
        },
        loading: false,
        userListForm: {
          sortField: 'createdDate',
          sortOrder: 'descend'
        },
        filters: {},

        profileUserId: null,
        profileDrawerVisible: false
      }
    },

    created() {
      this.fetch()
    },

    methods: {
      handleSubmit() {
        this.pagination = Object.assign({}, this.pagination, {
          current: 1
        })
        this.fetch()
      },

      handleTableChange(pagination, filters, sorter) {
        this.pagination = Object.assign({}, this.pagination, {
          current: pagination.current
        })

        this.sortField = sorter.field
        this.sortOrder = sorter.order
        this.filters = filters
        this.fetch()
      },

      fetch() {
        const {startCreatedDate,endCreatedDate} = this.userListForm
        this.userListForm = Object.assign({}, this.userListForm, {
          page: this.pagination.current,
          startCreatedDate: startCreatedDate && moment(startCreatedDate).format('YYYY-MM-DD 00:00:00'),
          endCreatedDate: endCreatedDate && moment(endCreatedDate).format('YYYY-MM-DD 23:59:59')
        })
        this.loading = true
        listUsers(this.userListForm).then(({data, rows}) => {
          this.data = data
          this.pagination = Object.assign({}, this.pagination, {
            total: rows
          })
        }).catch(({message}) => {
          this.$message.error(message)
        }).then(() => {
          this.loading = false
        })
      }
    }
  }
</script>
