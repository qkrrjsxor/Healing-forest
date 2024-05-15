<template>
<<<<<<< HEAD
  <div>
    <!-- <ul v-for="addiction in store.addictionList" :key="addiction.addictionId">
      <li>{{ addiction.title }}</li>
    </ul> -->
    <RouterLink :to="{ name: 'addictionCreate' }" id="addictionCreate">
      <img id="plusicon" src="@/assets/plus.png" alt="plusicon" />
    </RouterLink>
=======
  <div id="list-container">
    <img
      id="add-addiction"
      src="@/assets/addiction/create-addiction-btn.png"
      alt="add-addiction"
    />
>>>>>>> 9bedebc804f22f55d11000f92a07ebd5e54dc61a
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
  height: calc(100vh - 3.5rem);
}

#add-addiction {
  margin-top: 3rem;
}

#addiction-list {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1.5rem;

  margin: 3rem auto;
  padding: 0 5rem;

  width: 80%;
  height: 82vh;
  overflow: scroll;
}

/* 스크롤바 제거 */
#addiction-list::-webkit-scrollbar {
  display: none; /* Chrome, Edge, and Safari */
}
#addiction-list {
  scrollbar-width: none; /* Firefox */
}
#addiction-list {
  -ms-overflow-style: none; /* IE11 */
}

/* media query */
@media (max-width: 1024px) {
  #addiction-list {
    width: 75%;
  }
}

@media (max-width: 1768px) {
  #addiction-list {
    width: 65%;
  }
}

#plusicon {
  width: 50px;
  margin-top: 3rem;
  margin-left: 1rem;
}
</style>
