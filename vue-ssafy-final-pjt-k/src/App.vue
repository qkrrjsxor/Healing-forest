<template>
  <HeaderNav v-if="!route.meta.hideHeader" />
  <RouterView />
</template>

<script setup>
import HeaderNav from "@/components/common/HeaderNav.vue";

import { useRoute } from "vue-router";
const route = useRoute();
</script>

<style></style>
