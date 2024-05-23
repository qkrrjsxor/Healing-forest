<template>
  <div
    v-if="currentAlert && currentAlert.componentId === componentId"
    :class="['alert', currentAlert.alertType]"
    ref="alertRef"
  >
    {{ currentAlert.msg }}
  </div>
</template>

<script setup>
import { useAlertStore } from "@/stores/alert";
import { computed, nextTick, onMounted, ref, watchEffect } from "vue";

const props = defineProps({
  componentId: String,
});

const store = useAlertStore();
const currentAlert = computed(() => store.currentAlert);

const alertRef = ref(null);

// 해당 alert 위치로 scroll
const scrollToAlert = () => {
  if (alertRef.value && currentAlert.value.alertType === "addiction") {
    alertRef.value.scrollIntoView({ behavior: "smooth" });
  }
};
onMounted(() => {
  watchEffect(() => {
    // alertMessage 설정될 때 scroll
    if (currentAlert.value && currentAlert.value.alertType === "addiction") {
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

.alert.addiction {
  background-color: #7a8579;
  color: #eaeceb;
}

.alert.login {
  background-color: #9d8e03;
  color: #eaeceb;
}

.alert.logout {
  background-color: #7a8579;
  color: #eaeceb;
}

.alert.signup {
  background-color: #9d8e03;
  color: #eaeceb;
}

.alert.mypage {
  background-color: #7a8579;
  color: #eaeceb;
}

@media (max-width: 768px) {
  .alert.addiction {
    font-size: small;
  }
}
</style>
