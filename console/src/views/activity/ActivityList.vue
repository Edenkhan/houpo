<template>
  <div>
    <a-form-model @submit.prevent="handleSubmit" layout="inline" >

      <a-form-model-item label='创建时间：' class="date-start">
        <a-date-picker v-model="activityListForm.startCreatedDate" style="width: 120px"></a-date-picker>
      </a-form-model-item>

      <a-form-model-item label='-' :colon="false">
        <a-date-picker v-model="activityListForm.endCreatedDate" style="width: 120px"></a-date-picker>
      </a-form-model-item>

      <a-form-model-item label='活动名称'>
        <a-input v-model="activityListForm.title" style="width: 120px"/>
      </a-form-model-item>

      <a-form-model-item label='报名状态'>
        <a-select v-model="activityListForm.enrollStatus" style="width: 80px" >
          <a-select-option value="">
            全部
          </a-select-option>
          <a-select-option :value="1">
            开启
          </a-select-option>
          <a-select-option :value="0">
            关闭
          </a-select-option>
        </a-select>
      </a-form-model-item>

      <a-form-model-item label='发布状态'>
        <a-select v-model="activityListForm.releaseStatus" style="width: 80px" >
          <a-select-option value="">
            全部
          </a-select-option>
          <a-select-option :value="1">
            已发布
          </a-select-option>
          <a-select-option :value="0">
            未发布
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

      <template slot="lastModifiedDate" slot-scope="lastModifiedDate">
        {{lastModifiedDate | filterDate('YYYY-MM-DD HH:mm:ss')}}
      </template>

      <template slot="enrollStatus" slot-scope="enrollStatus">
        <a-tag color="#87d068" v-if="enrollStatus === 1">开启</a-tag>
        <a-tag color="#f5222d" v-else>关闭</a-tag>
      </template>

      <template slot="releaseStatus" slot-scope="releaseStatus">
        <a-tag color="#87d068" v-if="releaseStatus === 1">已发布</a-tag>
        <a-tag color="#f5222d" v-else>未发布</a-tag>
      </template>

      <template slot="recentEditor" slot-scope="recentId">
        {{recentId}}
      </template>

      <template slot="operation" slot-scope="record">
        <a href="javascript:" >查看</a>
        <a-divider type="vertical" />
        <router-link :to="`/activity/edit?id=${record.id}`">编辑</router-link>
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
    dataIndex: 'lastModifiedDate',
    sorter: true,
    defaultSortOrder: 'descend',
    scopedSlots: {customRender: 'lastModifiedDate'}
  },
  {
    title: '最近编辑人',
    dataIndex: 'recentId',
    scopedSlots: {customRender: 'recentEditor'}
  },
  {
    title: '活动标题',
    dataIndex: 'title'
  },
  {
    title: '报名人数',
    dataIndex: 'numberOfEntries'
  },
  {
    title: '报名状态',
    dataIndex: 'enrollStatus',
    scopedSlots: {customRender: 'enrollStatus'}
  },
  {
    title: '发布状态',
    dataIndex: 'releaseStatus',
    scopedSlots: {customRender: 'releaseStatus'}
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
