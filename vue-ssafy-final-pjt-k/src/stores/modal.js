import { defineStore } from "pinia";
import { ref } from "vue";

export const useModalStore = defineStore("modal", () => {
  // state
  const isVisible = ref(false);
  const modalContent = ref(null);

  // action
  const showModal = (content) => {
    modalContent.value = content;
    isVisible.value = true;
  };

  const closeModal = () => {
    modalContent.value = null;
    isVisible.value = false;
  };

  return {
    isVisible,
    modalContent,
    modalContent,
    isVisible,
  };
});
