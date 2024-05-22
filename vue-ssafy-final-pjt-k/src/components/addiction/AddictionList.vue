<template>
  <div id="list-container">
    <RouterLink :to="{ name: 'addictionCreate' }" id="addictionCreate">
      <img
        id="add-addiction"
        src="@/assets/addiction/create-addiction-btn.png"
        alt="add-addiction"
      />
    </RouterLink>

    <ul id="addiction-list">
      <AddictionListItem
        v-for="addiction in store.addictionList"
        :key="addiction.addictionId"
        :addiction="addiction"
      />
    </ul>
  </div>
</template>

<script setup>
import AddictionListItem from "@/components/addiction/AddictionListItem.vue";
import { useAddictionStore } from "@/stores/addiction";
import { onMounted } from "vue";
const store = useAddictionStore();

onMounted(() => {
  store.getAddictionList();
});
</script>

<style scoped>
#list-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 5rem;

  max-width: 1280px;
  margin: 0 auto;

  background-color: #eaeceb;
}

#add-addiction {
  margin-top: 5rem;
}

#addiction-list {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1.5rem;

  padding: 0 5rem;
  margin-bottom: 5rem;
  width: 80%;
}

/* media query */
@media (max-width: 768px) {
  #addiction-list {
    width: 70%;
  }
}
</style>
