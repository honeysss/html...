<template>
  <div>
    <div>
      <input type="text" name="things" v-model="inputValue" />
      <button @click="handleSubmit(inputValue)">提交</button>
    </div>
    <ul>
        <!-- 1.这里定义一个content变量对子组件传值 -->
        <todo-item v-for="(item, index) of list" :key="index"
        :content="item"
        :index="index"
        @delete="deleteHandle(index)"
        ></todo-item>
    </ul>
  </div>
</template>

<script>
import TodoItem from './components/TodoItem.vue'
export default {
  components: {
    'todo-item': TodoItem
  },
  data: function () {
    return {
      inputValue: '',
      list: []
    }
  },
  methods: {
    handleSubmit () {
      if (this.inputValue !== '') {
        this.list.push(this.inputValue)
        this.inputValue = ''
      }
    },
    deleteHandle (index) {
      this.list.splice(index, 1)
    }
  }
}
</script>

<style>

</style>
