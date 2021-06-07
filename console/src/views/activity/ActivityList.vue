<template>
  <div>
    <a-form-model @submit.prevent="handleSubmit" layout="inline" >

      <a-form-model-item label='创建时间：' class="date-start">
        <a-date-picker v-model="activityListForm.startCreatedDate" style="width: 120px"></a-date-picker>
      </a-form-model-item>

      <a-form-model-item label='-' :colon="false">
        <a-date-picker v-model="activityListForm.endCreatedDate" style="width: 120px"></a-date-picker>
      </a-form-model-item>

      <a-form-model-item label='名称'>
        <a-input v-model="activityListForm.activityName" style="width: 180px"/>
      </a-form-model-item>

      <a-form-model-item label='状态'>
        <a-select v-model="activityListForm.status" style="width: 120px" >
          <a-select-option value="">
            全部
          </a-select-option>
          <a-select-option :value="1">
            启用
          </a-select-option>
          <a-select-option :value="0">
            停用
          </a-select-option>
        </a-select>
      </a-form-model-item>

      <a-form-model-item>
        <a-button type='primary' htmlType="submit">
          <a-icon type="search"/>
          查询
        </a-button>
      </a-form-model-item>
    </a-form-model>

    <div class="actions">
      <router-link to="/activity/add">
        <a-button type="primary">
          <a-icon type="plus-circle"/>
          添加活动
        </a-button>
      </router-link>
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

      <template slot="status" slot-scope="status">
        <a-tag color="#87d068" v-if="status === 1">启用</a-tag>
        <a-tag color="#f5222d" v-else>停用</a-tag>
      </template>

      <template slot="operation" slot-scope="record">
        <router-link :to="`/activity/edit?id=${record.id}`">修改</router-link>
        <a-divider type="vertical" />
        <a-popconfirm
          title="Are you sure？"
          ok-text="Yes"
          cancel-text="No"
          @confirm="changeStatus(record.id,record.status)"
        >
          <a href="javascript:" v-if="record.status===0" >启用</a>
          <a href="javascript:" v-else >停用</a>
        </a-popconfirm>
        <a-divider type="vertical" />
        <a href="javascript:" >删除</a>
      </template>



    </a-table>
  </div>
</template>

<script>
import {listActivity,editActivity} from "../../api/activity";
import moment from "moment";

const columns = [
  {
    title: '创建时间',
    dataIndex: 'createdDate',
    sorter: true,
    defaultSortOrder: 'descend',
    scopedSlots: {customRender: 'createdDate'}
  },
  {
    title: '最近编辑时间',
    dataIndex: 'lastModifiedDate'
  },
  {
    title: '最近编辑人',
    dataIndex: 'lastModifiedPerson'
  },
  {
    title: '活动标题',
    dataIndex: 'eventTitle'
  },
  {
    title: '报名人数',
    dataIndex: 'numberOfEntries'
  },
  {
    title: '发布状态',
    dataIndex: 'published'
  },
  {
    title: '操作',
    scopedSlots: {customRender: 'operation'}
  }
];

export default {

  data() {
    return {
      columns,
      data: [],
      pagination: {
        current: 1,
        pageSize: 10,
        total: 0,
        showTotal(total) {
          return `共 ${total} 项`;
        }
      },
      loading: false,
      activityListForm: {
        sortField: 'createdDate',
        sortOrder: 'descend'
      },
      filters: {},

      profileUserId: null,
      profileDrawerVisible: false
    }
  },

  created() {
    this.fetch();
  },

  methods: {
    handleSubmit() {
      this.pagination = Object.assign({}, this.pagination, {
        current: 1
      });
      this.fetch();
    },

    handleTableChange(pagination, filters, sorter) {
      this.pagination = Object.assign({}, this.pagination, {
        current: pagination.current
      });

      this.sortField = sorter.field;
      this.sortOrder = sorter.order;
      this.filters = filters;
      this.fetch();
    },

    fetch() {
      const {startCreatedDate,endCreatedDate} = this.activityListForm
      this.activityListForm = Object.assign({}, this.activityListForm, {
        page: this.pagination.current,
        startCreatedDate: startCreatedDate && moment(startCreatedDate).format('YYYY-MM-DD 00:00:00'),
        endCreatedDate: endCreatedDate && moment(endCreatedDate).format('YYYY-MM-DD 23:59:59')
      })
      this.loading = true
      listActivity(this.activityListForm).then(({data, rows}) => {
        this.data = data
        console.log(this.data)
        this.pagination = Object.assign({}, this.pagination, {
          total: rows
        });
      }).catch(({message}) => {
        this.$message.error(message)
      }).then(() => {
        this.loading = false
      });
    },

    changeStatus(id,status) {
      status = status===1?0:1
      editActivity({id: id, status: status})
        .then(() => {
          this.fetch()
        })
    }

  }
}
</script>
