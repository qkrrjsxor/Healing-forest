import { defineStore } from "pinia";
import { reactive } from "vue";

export const useModalStore = defineStore("modal", () => {
  // state
  const modals = reactive({});

  // action
  const showModal = (id, content) => {
    if (!modals[id]) {
      modals[id] = {
        isVisible: false,
        content: null,
      };
    }
    modals[id].content = content;
    modals[id].isVisible = true;
  };

  const closeModal = (id) => {
    if (modals[id]) {
      modals[id].isVisible = false;
      modals[id].content = null;
    }
  };

  return {
    modals,
    showModal,
    closeModal,
  };
});
