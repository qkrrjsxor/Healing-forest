<template>
  <div>
    <!-- <ul v-for="addiction in store.addictionList" :key="addiction.addictionId">
      <li>{{ addiction.title }}</li>
    </ul> -->
    <RouterLink :to="{ name: 'addictionCreate' }" id="addictionCreate">
      <img id="plusicon" src="@/assets/plus.png" alt="plusicon" />
    </RouterLink>
    <ul id="addiction-list">
      <AddictionListItem />
      <AddictionListItem />
      <AddictionListItem />
    </ul>
  </div>
</template>

<script setup>
import AddictionListItem from "./AddictionListItem.vue";
import { useAddictionStore } from "@/stores/addiction";
import { onMounted } from "vue";
const store = useAddictionStore();

onMounted(() => {
  store.getAddictionList();
});
</script>

<style scoped>
#addiction-list {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: blue;
  margin-top: 3rem;
  padding: 5rem;
}

#plusicon {
  width: 50px;
  margin-top: 3rem;
  margin-left: 1rem;
}
</style>
