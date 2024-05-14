<template>
  <div id="background">
    <HeaderNav v-if="!route.meta.hideHeader" />
    <RouterView />
  </div>
</template>

<script setup>
import HeaderNav from "@/components/common/HeaderNav.vue";

import { useRoute } from "vue-router";
const route = useRoute();
</script>

<style>
#background {
  background-color: #eaeceb;
  height: 100vh;
}
</style>
