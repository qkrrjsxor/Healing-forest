import { defineStore } from "pinia";
import { ref, watch } from "vue";

export const useAlertStore = defineStore("alert", () => {
  // state
  const alertQueue = ref([]); // 알림 큐
  const currentAlert = ref(null); // 현재 알림

  // action
  const setAlert = (msg, alertType, componentId, duration = 1000) => {
    alertQueue.value.push({ msg, alertType, componentId, duration });
    if (!currentAlert.value) {
      showAlert();
    }
  };

  const clearAlert = () => {
    currentAlert.value = null;
    if (alertQueue.value.length > 0) {
      showAlert();
    }
  };

  const showAlert = () => {
    if (alertQueue.value.length > 0) {
      const nextAlert = alertQueue.value.shift();
      currentAlert.value = nextAlert;

      setTimeout(clearAlert, nextAlert.duration);
    }
  };

  watch(
    alertQueue,
    (newQueue, oldOueue) => {
      if (newQueue.length > 0 && !oldOueue.length) {
        showAlert();
      }
    },
    { deep: true }
  );

  return {
    currentAlert,
    setAlert,
    clearAlert,
  };
});
