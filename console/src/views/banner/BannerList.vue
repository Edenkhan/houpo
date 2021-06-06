<template>
  <div>
    <a-form-model @submit.prevent="handleSubmit" layout="inline" >

      <a-form-model-item label='创建时间：' class="date-start">
        <a-date-picker v-model="bannerListForm.startCreatedDate" style="width: 120px"></a-date-picker>
      </a-form-model-item>

      <a-form-model-item label='-' :colon="false">
        <a-date-picker v-model="bannerListForm.endCreatedDate" style="width: 120px"></a-date-picker>
      </a-form-model-item>

      <a-form-model-item label='名称'>
        <a-input v-model="bannerListForm.bannerName" style="width: 180px"/>
      </a-form-model-item>

      <a-form-model-item label='状态'>
        <a-select v-model="bannerListForm.status" style="width: 120px" >
          <a-select-option value="">
            全部
          </a-select-option>
          <a-select-option :value="true">
            启用
          </a-select-option>
          <a-select-option :value="false">
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
      <router-link to="/banner/add">
        <a-button type="primary">
          <a-icon type="plus-circle"/>
          添加轮播图
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
        <a-tag color="#87d068" v-if="status">启用</a-tag>
        <a-tag color="#f5222d" v-else>停用</a-tag>
      </template>

      <template slot="operation" slot-scope="record">
        <router-link :to="`/banner/edit?id=${record.id}`">修改</router-link>
        <a-divider type="vertical" />
        <a href="javascript:" >启用/停用</a>
        <a-divider type="vertical" />
        <a href="javascript:" >删除</a>
      </template>



    </a-table>
  </div>
</template>

<script>
import {listBanner} from "../../api/banner";
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
    title: '轮播图名称',
    dataIndex: 'bannerName'
  },
  {
    title: '缩略图',
    dataIndex: 'image',
    scopedSlots: {customRender: 'image'}
  },
  {
    title: '链接',
    dataIndex: 'linkUrl'
  },
  {
    title: '序号',
    dataIndex: 'no'
  },
  {
    title: '状态',
    dataIndex: 'status',
    scopedSlots: {customRender: 'status'}
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
      bannerListForm: {
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
      const {startCreatedDate,endCreatedDate} = this.bannerListForm
      this.bannerListForm = Object.assign({}, this.bannerListForm, {
        page: this.pagination.current,
        startCreatedDate: startCreatedDate && moment(startCreatedDate).format('YYYY-MM-DD 00:00:00'),
        endCreatedDate: endCreatedDate && moment(endCreatedDate).format('YYYY-MM-DD 23:59:59')
      })
      this.loading = true
      listBanner(this.bannerListForm).then(({data, rows}) => {
        this.data = data
        this.pagination = Object.assign({}, this.pagination, {
          total: rows
        });
      }).catch(({message}) => {
        this.$message.error(message)
      }).then(() => {
        this.loading = false
      });
    }
  }
}
</script>
