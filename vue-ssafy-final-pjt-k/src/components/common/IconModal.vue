<template>
  <div v-if="modal.isVisible" class="modal-overlay" @click.self="closeModal">
    <form @submit.prevent="updateIcon" class="modal-content">
      <Alert id="alert" />
      <div>
        <h2>아이콘을 선택해주세요.</h2>
        <div id="select-div" @click="toggleDropdown">
          <div id="selected-icon">
            {{ selectedIcon ? selectedIcon.alt : "아이콘 선택" }}
            <div>
              <span v-if="showIcons">▲</span>
              <span v-else>▼</span>
            </div>
          </div>

          <ul v-if="showIcons" id="select-icons">
            <li
              v-for="icon in icons"
              :key="icon.id"
              id="icon-box"
              @click.stop="selectIcon(icon)"
            >
              <img
                id="icon-img"
                :src="getIconImage(icon.src)"
                :alt="icon.alt"
              />
              {{ icon.alt }}
            </li>
          </ul>
        </div>
      </div>

      <div id="button-div">
        <button type="button" @click="closeModal">취소</button>
        <button type="submit">변경</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { useAddictionStore } from "@/stores/addiction";
import { useAlertStore } from "@/stores/alert";
import { useModalStore } from "@/stores/modal";
import { computed, ref } from "vue";
import Alert from "./Alert.vue";

const store = useAddictionStore();
const modalStore = useModalStore();
const alertStore = useAlertStore();

const addictionIconPath = ref("");

const props = defineProps({
  id: {
    type: String,
    required: true,
  },
  addictionId: {
    type: String,
    required: true,
  },
});

const modal = computed(
  () => modalStore.modals[props.id] || { isVisible: false, content: null }
);

const icons = ref([
  { id: 1, src: "/src/assets/addiction/icons/Alcohol.png", alt: "Alcohol" },
  { id: 2, src: "/src/assets/addiction/icons/Coffee.png", alt: "Coffee" },
  { id: 3, src: "/src/assets/addiction/icons/Drug.png", alt: "Drug" },
  { id: 4, src: "/src/assets/addiction/icons/Fastfood.png", alt: "Fastfood" },
  { id: 5, src: "/src/assets/addiction/icons/Gamble.png", alt: "Gamble" },
  { id: 6, src: "/src/assets/addiction/icons/Game.png", alt: "Game" },
  { id: 7, src: "/src/assets/addiction/icons/Instagram.png", alt: "Instagram" },
  { id: 8, src: "/src/assets/addiction/icons/Phone.png", alt: "Phone" },
  { id: 9, src: "/src/assets/addiction/icons/Porn.png", alt: "Porn" },
  { id: 10, src: "/src/assets/addiction/icons/Shopping.png", alt: "Shopping" },
  { id: 11, src: "/src/assets/addiction/icons/Shorts.png", alt: "Shorts" },
  { id: 12, src: "/src/assets/addiction/icons/Smoke.png", alt: "Smoke" },
  { id: 13, src: "/src/assets/addiction/icons/Tiktok.png", alt: "Tiktok" },
  { id: 14, src: "/src/assets/addiction/icons/TV.png", alt: "TV" },
  { id: 15, src: "/src/assets/addiction/icons/Youtube.png", alt: "Youtube" },
]);

// 아이콘 dropdown 메뉴
const showIcons = ref(false);
const selectedIcon = ref(null);

const toggleDropdown = () => {
  showIcons.value = !showIcons.value;
};

const selectIcon = (icon) => {
  selectedIcon.value = icon;
  addictionIconPath.value = icon.src;
  showIcons.value = false;
};

// 이미지 동적 경로
// 이미지 파일 로드 (모든 파일 미리 가져오도록 설정)
const images = import.meta.glob("@/assets/addiction/icons/*.png", {
  eager: true,
});

// 이미지 경로 가져오기
const getIconImage = (filePath) => {
  return images[filePath]?.default || "";
};

// 취소
const closeModal = () => {
  selectedIcon.value = null;
  modalStore.closeModal(props.id);
};

// 아이콘 변경
const updateIcon = async () => {
  if (!addictionIconPath.value) {
    alertStore.setAlert("아이콘을 선택해주세요.", "addiction");
    return;
  }
  await store.updateIcon(props.addictionId, addictionIconPath.value);
  selectedIcon.value = null; // 초기화
  addictionIconPath.value = ""; // 초기화
  closeModal();
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
  background-color: rgba(0, 0, 0, 0.3);

  overflow: scroll;
}

.modal-content {
  display: flex;
  flex-direction: column;
  gap: 2rem;

  width: 40rem;
  max-width: 70%;

  padding: 3rem;

  background-color: #b6c2a9;
  border: none;
  border-radius: 1rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);

  h2 {
    color: #384427;
    margin-top: 0;
  }
}

#select-div {
  box-sizing: border-box;

  display: flex;
  flex-direction: column;
  cursor: pointer;

  width: 100%;
  padding: 1rem 1.5rem;
  border: 3px solid #84a063;
  border-radius: 1rem;

  color: #352f26;
  font-size: small;
}

#selected-icon {
  display: flex;
  justify-content: space-between;
  font-weight: 700;
}

#select-icons {
  display: flex;
  gap: 2rem;
  flex-wrap: wrap;
  padding: 2rem;
}

#icon-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 30px;
  height: 30px;
}

#icon-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

#button-div {
  display: flex;
  gap: 1rem;
  margin: 0 auto;
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
    background-color: #84a063;
  }
}

/* 스크롤바 제거 */
.modal-overlay::-webkit-scrollbar {
  display: none; /* Chrome, Edge, and Safari */
}
.modal-overlay {
  scrollbar-width: none; /* Firefox */
}
.modal-overlay {
  -ms-overflow-style: none; /* IE11 */
}

/* media query */
@media (max-width: 768px) {
  /* .modal-content {
    height: 19%;
  } */

  #button-div {
    width: 80%;
  }
}
</style>
