<template>
  <!-- <div></div> -->
  <div v-if="alertMessage" :class="['alert', alertType]" ref="alertRef">
    {{ alertMessage }}
  </div>
</template>

<script setup>
import { useAlertStore } from "@/stores/alert";
import { computed, nextTick, onMounted, ref } from "vue";

const store = useAlertStore();

const alertMessage = computed(() => store.message);
const alertType = computed(() => store.type);

const alertRef = ref(null);

// 해당 alert 위치로 scroll
const scrollToAlert = () => {
  if (alertRef.value) {
    alertRef.value.scrollIntoView({ behavior: "smooth" });
  }
};

onMounted(() => {
  store.$subscribe((_, state) => {
    // alertMessage 설정될 때 scroll
    if (state.message) {
      nextTick(scrollToAlert);
    }
  });
});
</script>

<style scoped>
.alert {
  padding: 1rem;
  border: none;
  border-radius: 0.5rem;
  font-size: medium;
}

.alert.warning {
  background-color: #7a8579;
  color: #eaeceb;
}

.alert.login {
  background-color: #9d8e03;
  color: #eaeceb;
}

.alert.signup {
  background-color: #9d8e03;
  color: #eaeceb;
}

@media (max-width: 768px) {
  .alert.warning {
    font-size: small;
  }
}
</style>
