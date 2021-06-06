<template>
  <div @click="$emit('click')">
    <div class="title">
      <slot name="title">{{ title }}</slot>
    </div>
    <div class="content">{{ content }}</div>
  </div>
</template>

<script>
  export default {
    name: "DescriptionItem",
    props: ['title', 'content']
  }
</script>

<style scoped lang="scss">
  .title {
    color: $muted-color;
    font-size: 12px;
    margin-bottom: 6px;
  }
</style>
