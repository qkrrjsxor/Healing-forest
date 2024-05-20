<template>
  <div id="form-container">
    <form @submit.prevent="submitAddiction" id="addiction-form">
      <Alert id="alert" />
      <div id="input-set">
        <div>
          <h2>어떤 중독을 끊고 싶으신가요?</h2>
          <input
            type="text"
            v-model="addiction.title"
            placeholder="목표 항목을 입력해주세요."
            ref="titleInputRef"
          />
        </div>

        <div>
          <h2>목표 일 수를 입력해주세요.</h2>
          <input
            type="number"
            v-model="addiction.targetTime"
            placeholder="1 ~ 100 까지의 숫자를 입력할 수 있습니다."
            min="1"
            max="100"
            ref="targetTimeInputRef"
            @input="validateTargetTime"
          />
        </div>

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
      </div>

      <div id="button-set">
        <button type="button" @click="goList">목록으로</button>
        <button type="submit">시작</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { useAddictionStore } from "@/stores/addiction";
import { useAlertStore } from "@/stores/alert";
import { nextTick, onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import Alert from "../common/Alert.vue";

const router = useRouter();
const store = useAddictionStore();
const alertStore = useAlertStore();

const addiction = ref({
  title: "",
  targetTime: "",
  iconPath: "",
});

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
  addiction.value.iconPath = icon.src;
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

// 목표 일수 입력 제한 (1 ~ 100일)
const validateTargetTime = (e) => {
  const value = parseInt(e.target.value, 10);

  if (value < 1) {
    addiction.value.targetTime = 1;
  } else if (value > 100) {
    addiction.value.targetTime = 100;
  } else {
    addiction.value.targetTime = value;
  }
};

// 목록으로
const goList = () => {
  router.push({ name: "addictionList" });
};

const titleInputRef = ref(null);
const targetTimeInputRef = ref(null);

// 등록
const submitAddiction = () => {
  // 모든 항목 필수 선택하도록
  if (!addiction.value.title) {
    alertStore.setAlert("목표 항목을 입력해주세요.", "warning");
    nextTick(() => {
      titleInputRef.value.focus();
    });
    return;
  }
  if (!addiction.value.targetTime) {
    alertStore.setAlert("목표 일수를 입력해주세요.", "warning");
    nextTick(() => {
      targetTimeInputRef.value.focus();
    });
    return;
  }
  if (!selectedIcon.value) {
    alertStore.setAlert("아이콘을 선택해주세요.", "warning");
    return;
  }
  store.submitAddiction(addiction.value);
  router.push({ name: "addictionList" });
};

onMounted(() => {
  titleInputRef.value.focus();
});
</script>

<style scoped>
#form-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: calc(100vh - 3.5rem);
}

#addiction-form {
  position: relative;

  display: flex;
  flex-direction: column;
  gap: 3rem;

  width: 60%;
  padding: 3rem 2rem;
  overflow: scroll;
}

#alert {
  position: absolute;
  top: 2rem;
  right: 4rem;
}

#input-set {
  display: flex;
  flex-direction: column;
  gap: 2.5rem;

  padding: 2rem;

  h2 {
    color: #384427;
    margin-top: 0;
  }
}

input,
#select-div {
  box-sizing: border-box;

  width: 100%;
  padding: 1rem 1.5rem;
  border: 3px solid #b6c2a9;
  border-radius: 1rem;

  color: #352f26;
  font-size: small;
}

input::placeholder {
  color: #939494;
}

#select-div {
  display: flex;
  flex-direction: column;
  cursor: pointer;
}

#selected-icon {
  display: flex;
  justify-content: space-between;
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

#button-set {
  display: flex;
  justify-content: flex-end;
  gap: 1rem;

  button {
    width: 25%;
    padding: 0.5rem 2rem;
    border-radius: 2rem;

    font-size: medium;
    font-weight: 700;
    cursor: pointer;
  }
}

button:first-child {
  border: 3px solid #b6c2a9;
  color: #b6c2a9;
}

button:last-child {
  border: none;
  background-color: #b6c2a9;
  color: #eaeceb;
}

/* 스크롤바 제거 */
#addiction-form::-webkit-scrollbar {
  display: none; /* Chrome, Edge, and Safari */
}
#addiction-form {
  scrollbar-width: none; /* Firefox */
}
#addiction-form {
  -ms-overflow-style: none; /* IE11 */
}

/* media query */
@media (max-width: 1024px) {
  #addiction-form {
    width: 70%;
  }
}

@media (max-width: 870px) {
  #addiction-form {
    position: inherit;
  }

  #alert {
    top: 10rem;
  }
}

@media (max-width: 768px) {
  #addiction-form {
    width: 75%;
  }

  #alert {
    position: inherit;
  }

  #input-set {
    padding: 0;

    h2 {
      font-size: large;
    }

    input {
      padding: 0.8rem 1.5rem;
    }
  }

  #button-set {
    flex-direction: column;

    button {
      width: 100%;
      margin: 0 auto;
      font-size: small;
    }
  }
}

@media (max-height: 870px) {
  #addiction-form {
    position: inherit;
  }

  #alert {
    top: 6rem;
  }
}

@media (max-height: 730px) {
  #alert {
    position: inherit;
  }
}
</style>
