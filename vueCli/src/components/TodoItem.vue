<template>
  <!-- 3.在标签中可以直接使用接收到的值 -->
  <li @click="deleteHandle(index)">{{content}}</li>
</template>

<script>
export default {
  // 2.子组件用props方法来接收父组件传过来的值
  props: ['content', 'index'],
  methods: {
    deleteHandle (index) {
      this.$emit('delete', index)
    }
  }
}
</script>

<style scoped>

</style>
