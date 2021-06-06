<template>
  <div>
    <a-form-model @submit.prevent="handleSubmit" layout="inline" >

      <a-form-model-item label='创建时间' class="date-start">
        <a-date-picker v-model="enrollListForm.startCreatedDate" style="width: 120px"></a-date-picker>
      </a-form-model-item>

      <a-form-model-item label='-' :colon="false">
        <a-date-picker v-model="enrollListForm.endCreatedDate" style="width: 120px"></a-date-picker>
      </a-form-model-item>

      <a-form-model-item label='订单编号'>
        <a-input v-model="enrollListForm.orderNo" style="width: 120px"/>
      </a-form-model-item>

      <a-form-model-item label='类型'>
        <a-select v-model="enrollListForm.type"  style="width: 120px">
          <a-select-option value="">
            全部
          </a-select-option>
          <a-select-option :value="0">
            职场百分百
          </a-select-option>
          <a-select-option :value="1">
            就业直通车
          </a-select-option>
          <a-select-option :value="2">
            普通活动
          </a-select-option>
        </a-select>
      </a-form-model-item>

      <a-form-model-item label='状态'>
        <a-select v-model="enrollListForm.orderStatus" style="width: 120px" >
          <a-select-option value="">
            全部
          </a-select-option>
          <a-select-option :value="false">
            待支付
          </a-select-option>
          <a-select-option :value="true">
            已支付
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

      <template slot="phoneNumber" slot-scope="phoneNumber">
        <PhoneNumber :value="phoneNumber"/>
      </template>

      <template slot="orderStatus" slot-scope="orderStatus">
        <span v-if="orderStatus">已支付</span>
        <span v-else>待支付</span>
      </template>

      <template slot="type" slot-scope="type">
        <span v-if="type==0">职场百分百</span>
        <span v-if="type==1">就业直通车</span>
        <span v-if="type==2">普通活动</span>
      </template>


    </a-table>
  </div>
</template>

<script>
import {listEnroll} from "../../api/enroll";
import moment from "moment";
import PhoneNumber from "../user/PhoneNumber";

const columns = [
  {
    title: '创建时间',
    dataIndex: 'createdDate',
    sorter: true,
    defaultSortOrder: 'descend',
    scopedSlots: {customRender: 'createdDate'}
  },
  {
    title: '订单编号',
    dataIndex: 'orderNo'
  },
  {
    title: '类型',
    dataIndex: 'type',
    scopedSlots: {customRender: 'type'}
  },
  {
    title: '姓名',
    dataIndex: 'realName'
  },
  {
    title: '电话',
    dataIndex: 'phoneNumber',
    scopedSlots: {customRender: 'phoneNumber'}
  },
  {
    title: '订单状态',
    dataIndex: 'orderStatus',
    scopedSlots: {customRender: 'orderStatus'}
  }
];

export default {

  components: {
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
          return `共 ${total} 项`;
        }
      },
      loading: false,
      enrollListForm: {
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
      const {startCreatedDate,endCreatedDate} = this.enrollListForm
      this.enrollListForm = Object.assign({}, this.enrollListForm, {
        page: this.pagination.current,
        startCreatedDate: startCreatedDate && moment(startCreatedDate).format('YYYY-MM-DD 00:00:00'),
        endCreatedDate: endCreatedDate && moment(endCreatedDate).format('YYYY-MM-DD 23:59:59')
      })
      this.loading = true
      listEnroll(this.enrollListForm).then(({data, rows}) => {
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
