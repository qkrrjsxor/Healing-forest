<template>
  <div class="modal-overlay" @click.self="closeModal">
    <Alert id="alert" />
    <div class="modal-content">
      <button @click="readyAlert">마이페이지</button>
      <button @click="logout">로그아웃</button>
    </div>
  </div>
</template>

<script setup>
import { useAlertStore } from "@/stores/alert";
import { useUserStore } from "@/stores/user";
import Alert from "../common/Alert.vue";

const store = useUserStore();
const alertStore = useAlertStore();

const emits = defineEmits(["close"]);

// 모달 닫기
const closeModal = () => {
  emits("close");
};

// 마이페이지 준비중
const readyAlert = () => {
  alertStore.setAlert("마이페이지 준비 중입니다.", "mypage");
};

// 로그아웃
const logout = () => {
  store.submitLogout();
};
</script>

<style scoped>
#alert {
  position: absolute;
  top: 8rem;
}

.modal-overlay {
  position: relative;

  display: flex;
  justify-content: flex-end;
  width: 100%;
  height: calc(100vh - 5rem);
}

.modal-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 1rem;

  height: 3rem;
  padding: 2rem;

  background-color: #84a063;
  border-radius: 1rem;

  button {
    border: none;
    background-color: transparent;
    height: 2rem;

    color: #eaeceb;
    font-size: medium;
    font-weight: 700;

    cursor: pointer;
  }
}

/* media query */
@media (max-width: 768px) {
  .modal-content {
    padding: 2rem 1rem;

    button {
      font-size: small;
    }
  }
}
</style>
