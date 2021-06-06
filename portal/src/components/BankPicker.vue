<template>
  <div>
    <van-popup v-model="popupVisible" position="bottom" @close="$emit('close')">
      <van-search v-model="searchKeyword" placeholder="请输入搜索关键词"/>
      <van-radio-group v-model="bankAbbr">
        <van-cell-group>
          <van-cell v-for="bank in visibleBanks" :key="bank.key" clickable @click="pickBank(bank)">
            <template #title>
              <img v-if="bank.hot" :src="`https://apimg.alipay.com/combo.png?d=cashier&t=${bank.key}`"
                   :alt="bank.name"/>
              <span v-else>{{ bank.name }}</span>
            </template>
            <template #right-icon>
              <van-radio :name="bank.key"/>
            </template>
          </van-cell>
        </van-cell-group>
      </van-radio-group>
    </van-popup>
  </div>
</template>
<style lang="scss" scoped>
  .van-popup {
    max-height: 80%;
    min-height: 80%;
  }
</style>
<script>
  import {getBanks} from "../utils/bank";

  export default {
    name: 'BankPicker',
    props: {
      visible: {
        type: Boolean,
        default: false
      }
    },
    watch: {
      visible: {
        immediate: true,
        handler(visible) {
          this.popupVisible = visible;
        }
      }
    },
    computed: {
      visibleBanks() {
        const searchKeyword = this.searchKeyword.trim();
        if (searchKeyword.length === 0) {
          return this.banks;
        }
        const keywords = searchKeyword.split(/\s+/);
        return this.banks.filter(({name}) => keywords.every(keyword => name.indexOf(keyword) !== -1));
      }
    },
    data() {
      return {
        banks: getBanks(),
        bankAbbr: null,
        searchKeyword: '',
        popupVisible: false
      }
    },
    methods: {
      pickBank(bank) {
        this.bankAbbr = bank.key;
        this.$emit('change', bank);
      }
    }
  }
</script>
