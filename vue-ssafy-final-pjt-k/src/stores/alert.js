import { defineStore } from "pinia";
import { ref } from "vue";

export const useAlertStore = defineStore("alert", () => {
  // state
  const message = ref("");
  const type = ref("");

  // action
  const setAlert = (msg, alertType, duration = 1000) => {
    message.value = msg;
    type.value = alertType;

    setTimeout(() => {
      clearAlert();
    }, duration);
  };

  const clearAlert = () => {
    message.value = "";
    type.value = "";
  };

  return {
    message,
    type,
    setAlert,
    clearAlert,
  };
});
