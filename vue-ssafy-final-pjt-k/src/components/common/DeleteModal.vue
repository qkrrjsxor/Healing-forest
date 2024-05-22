<template>
  <div v-if="modal.isVisible" class="modal-overlay" @click.self="closeModal">
    <div class="modal-content">
      <slot name="content"></slot>
      <div id="button-div">
        <button @click="closeModal">{{ buttonText.cancle }}</button>
        <button @click="$emit('confirm')">{{ buttonText.confirm }}</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed } from "vue";
import { useModalStore } from "@/stores/modal";

const modalStore = useModalStore();

const props = defineProps({
  id: {
    type: String,
    required: true,
  },
  modalType: {
    type: String,
    required: true,
  },
});

const modal = computed(
  () => modalStore.modals[props.id] || { isVisible: false, content: null }
);

// modalType에 따라 button text 분기
const buttonText = computed(() => {
  switch (props.modalType) {
    case "addiction":
      return { cancle: "취소", confirm: "종료" };
    case "comment":
      return { cancle: "아니오", confirm: "예" };
    default:
      return { cancle: "취소", confirm: "삭제" };
  }
});

// 취소
const closeModal = () => {
  modalStore.closeModal(props.id);
};
</script>

<style scoped>
.modal-overlay {
  z-index: 9;

  position: fixed;
  top: 0;
  left: 0;

  display: flex;
  justify-content: center;
  align-items: center;

  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
  box-sizing: border-box;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 1rem;

  width: 40rem;
  max-width: 80%;
  height: 20%;
  min-height: 10rem;

  padding: 2rem;

  background: #384427;
  border-radius: 1rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

#button-div {
  display: flex;
  gap: 1rem;
  width: 60%;

  button {
    width: 50%;
    padding: 0.5rem;
    border-radius: 0.5rem;
    border: none;

    font-weight: 700;
    cursor: pointer;
  }

  button:first-child {
    background-color: #eaeceb;
  }

  button:last-child {
    background-color: #b6c2a9;
  }
}

/* media query */
@media (max-width: 768px) {
  .modal-content {
    height: 19%;
  }

  #button-div {
    width: 80%;
  }
}
</style>
