<template>
  <div id="form-container">
    <div id="update-box">
      <form @submit.prevent="updateAddiction" id="update-form">
        <Alert id="alert" componentId="AddictionUpdate" />
        <div id="target-input">
          <h2>변경할 목표 일 수를 입력해주세요.</h2>
          <input
            type="number"
            v-model="targetTime"
            placeholder="1 ~ 100 까지의 숫자를 입력할 수 있습니다."
            @input="validateTargetTime"
            ref="targetTimeInputRef"
            autofocus
          />
        </div>

        <div id="button-set">
          <button type="button" @click="goBack">취소</button>
          <button type="submit">수정</button>
        </div>
      </form>

      <!-- 안내문구 -->
      <div id="info-box">
        <img src="@/assets/addiction/info.png" alt="info-icon" />
        <div id="info-ment">
          <p>{{ `시작일: ${formattedStartTime}` }}</p>
          <p>
            {{
              `* 현재 목표 일 수: ${addictionItem.addiction.targetTime}일
     
              `
            }}
          </p>
          <p>
            {{
              `* 현재보다 더 높은 목표만 가능합니다. (시작일로부터 최대 100일까지 설정 가능)`
            }}
          </p>
        </div>
      </div>
      <!--  -->
    </div>
  </div>
</template>

<script setup>
import { useAddictionStore } from "@/stores/addiction";
import { ref, nextTick, onMounted, computed, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import Alert from "../common/Alert.vue";
import { useAlertStore } from "@/stores/alert";

const route = useRoute();
const router = useRouter();

const store = useAddictionStore();
const alertStore = useAlertStore();

const targetTime = ref("");
const originalTargetTime = ref("");

const addictionId = route.params.id;

// 해당 addiction 시작일 정보 가져오기
let addictionItem = computed(() => store.addictionItem);
let formattedStartTime = ref(""); // formatDate 함수 이용해 시작일 담을 값

// 원하는 형식으로 변환하는 함수
function formatDate(date) {
  const options = {
    year: "numeric",
    month: "long",
    day: "numeric",
    hour: "numeric",
    minute: "numeric",
    second: "numeric",
    hour12: true,
  };
  return new Intl.DateTimeFormat("ko-KR", options).format(date);
}

// 일단 해당 addictionItem 정보 가져와
onMounted(async () => {
  await store.getAddictionItem(addictionId);
  targetTimeInputRef.value.focus();
  originalTargetTime.value = addictionItem.value.addiction.targetTime;
});

// addictionItem 변경 감지
watch(
  addictionItem,
  (newValue) => {
    if (newValue.addiction.startTime) {
      const startTime = new Date(addictionItem.value.addiction.startTime);
      startTime.setHours(startTime.getHours() - 9); // 9시간 빼기
      formattedStartTime.value = formatDate(startTime); // 시작일 형식으로 변환해서 값에 담기
    }
  },
  { immediate: true }
);

// 목표 일수 입력 제한
const validateTargetTime = (e) => {
  const value = parseInt(e.target.value, 10);
  if (value < originalTargetTime.value) {
    targetTime.value = originalTargetTime.value;
  } else if (value > 100) {
    targetTime.value = 100;
  } else {
    targetTime.value = value;
  }
};

// 취소
const goBack = () => {
  router.back();
};

const targetTimeInputRef = ref(null);

// 수정
const updateAddiction = async () => {
  if (!targetTime.value) {
    alertStore.setAlert(
      "목표 일수를 입력해주세요.",
      "addiction",
      "AddictionUpdate"
    );
    nextTick(() => {
      targetTimeInputRef.value.focus();
    });
    return;
  }
  await store.updateAddiction(addictionId, targetTime.value);
  router.push(`/addiction/${route.params.id}`);
};
</script>

<style scoped>
#form-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  max-width: 1280px;
  margin: 6rem auto;
}

#update-box {
  position: relative;

  display: flex;
  flex-direction: column;
  gap: 7rem;

  width: 60%;
  padding: 3rem 2rem;
}

#update-form {
  display: flex;
  flex-direction: column;
  gap: 1rem;

  padding-top: 0;
}

#alert {
  position: absolute;
  top: 2rem;
  right: 4rem;
}

#target-input {
  padding: 2rem;
  padding-left: 0;

  h2 {
    color: #384427;
    margin-top: 0;
  }

  input {
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
}

#info-box {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  gap: 2rem;

  padding: 2rem;
  background-color: #dddbd9;
  border-radius: 1rem;

  img {
    width: 1.1rem;
    height: 1.1rem;
  }

  p {
    margin: 0;
    color: #352f26;
    font-size: small;
    font-weight: 700;
  }
}

#info-ment {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

#info-ment p:first-child {
  margin-bottom: 1rem;
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

/* media query */
@media (max-width: 1111px) {
  #update-box {
    position: inherit;
  }

  #alert {
    position: inherit;
    margin: 0 2rem;
  }
}

@media (max-width: 1024px) {
  #update-box {
    width: 70%;
  }
}

@media (max-width: 800px) {
  #update-box {
    width: 75%;
    gap: 6rem;
  }

  #update-form {
    gap: 2rem;
  }

  #alert {
    margin: 1rem 0;
  }

  #target-input {
    padding: 0;

    input {
      padding: 0.8rem 1.5rem;
    }
  }

  #button-set {
    gap: 0.5rem;

    button {
      width: 50%;
      font-size: small;
    }
  }
}

@media (max-height: 600px) {
  #update-box {
    position: inherit;
  }

  #alert {
    position: inherit;
  }
}
</style>
