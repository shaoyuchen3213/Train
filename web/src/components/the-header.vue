<template>
  <a-layout-header class="header" id = "components-layout-demo-top-side">
    <div class="logo" />
    <a-menu
        v-model:selectedKeys="selectedKeys1"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="1">nav 111</a-menu-item>
      <a-menu-item key="2">nav 2</a-menu-item>
      <a-menu-item key="3">nav 3</a-menu-item>
    </a-menu>
  </a-layout-header>

</template>

<script>
import {defineComponent, ref} from "vue";

export default defineComponent({
  name:"the-header-view",
  setup() {
    return {
      selectedKeys:ref(['2'])
    };
  }
})
</script>

<style scoped>

</style>