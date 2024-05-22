<template>
  <div id="item-container">
    <div id="item-box">
      <div id="addiction-item">
        <div id="addiction-main">
          <div id="icon-title">
            <svg width="100" height="100" viewBox="0 0 100 100">
              <circle
                cx="50"
                cy="50"
                r="45"
                fill="transparent"
                stroke="#d1d3d2"
                stroke-width="10"
                stroke-dasharray="282.74"
                stroke-dashoffset="0"
                transform="rotate(-90 50 50)"
              />
              <circle
                cx="50"
                cy="50"
                r="45"
                fill="transparent"
                stroke="#84a063"
                stroke-width="10"
                stroke-dasharray="282.74"
                :stroke-dashoffset="
                  282.74 - 282.74 * Math.min(startToCurrent / startToEnd, 1)
                "
                transform="rotate(-90 50 50)"
              />
            </svg>
            <div
              id="icon-update"
              @click="openModal(`updateIcon/${addictionId}`)"
            >
              <img
                :src="getIconImage(addictionItem.addiction.iconPath)"
                alt="icon"
              />
              <p>
                {{ addictionItem.addiction.title }}
              </p>
            </div>
            <IconModal
              :id="`updateIcon/${addictionId}`"
              :addictionId="`${addictionId}`"
            >
            </IconModal>
          </div>
          <p>{{ ((startToCurrent / startToEnd) * 100).toFixed(1) }}%</p>
        </div>
        <div id="addiction-info">
          <div id="addiction-timer">
            <span class="text-color">금욕 시간 </span>
            <p>{{ enduredTime }}</p>
            <span class="text-color">목표까지 남은 시간 </span>
            <p>{{ restTime }}</p>
          </div>
          <div id="button-set">
            <button @click="updateTarget">목표 변경하기</button>
            <button @click="openModal(`deleteAddiction/${addictionId}`)">
              도전 종료하기
            </button>
            <DeleteModal
              :id="`deleteAddiction/${addictionId}`"
              modalType="addiction"
              @confirm="deleteAddiction"
            >
              <template #content>
                <div class="deleteModal">
                  <p>정말 도전을 종료하시겠습니까?</p>
                  <p>획득한 뱃지가 모두 회수됩니다.</p>
                </div>
              </template>
            </DeleteModal>
          </div>
        </div>
      </div>
      <div id="addiction-badge">
        <div id="badge-title">
          <h2>획득한 뱃지</h2>
          <span v-if="addictionItem.badges.length > 0" id="badge-num"
            >{{ addictionItem.badges.length }}
          </span>
        </div>
        <p id="no-badge" v-if="addictionItem.badges.length === 0">
          아직 획득한 뱃지가 없습니다.
        </p>
        <ul id="badge-list">
          <li v-for="badge in addictionItem.badges" :key="badge.id">
            <img
              :src="getBadgeImage(`badge_${badge.badgeDate}.png`)"
              alt="badge"
            />
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup>
import DeleteModal from "@/components/common/DeleteModal.vue";
import IconModal from "@/components/common/IconModal.vue";
import { useAddictionStore } from "@/stores/addiction";
import { useModalStore } from "@/stores/modal";
import dayjs from "dayjs";
import duration from "dayjs/plugin/duration";
import { onMounted, onUnmounted, ref, watch } from "vue";
import { useRoute, useRouter } from "vue-router";

// 플러그인 등록
dayjs.extend(duration);

const route = useRoute();
const router = useRouter();

const store = useAddictionStore();
const modalStore = useModalStore();

const addictionItem = ref({
  addiction: {},
  badges: [],
});

const addictionId = route.params.id;

const startToCurrent = ref("");
const currentToEnd = ref("");
const startToEnd = ref("");

const enduredTime = ref("");
const restTime = ref("");

// 현재 시간 갱신
const updateCurrentTime = () => {
  // 시작, 현재, 끝
  const startTime = dayjs(addictionItem.value.addiction.startTime).subtract(
    9,
    "hour"
  );
  const now = dayjs();
  const endTime = dayjs(addictionItem.value.addiction.endTime).subtract(
    9,
    "hour"
  );

  // 차이 계산
  const startToNow = now.diff(startTime);
  startToCurrent.value = dayjs.duration(startToNow).asDays();
  enduredTime.value = formatDuration(startToNow);

  const nowToEnd = endTime.diff(now);
  if (nowToEnd <= 0) {
    restTime.value = "목표 달성!";
  } else {
    currentToEnd.value = dayjs.duration(nowToEnd).asDays();
    restTime.value = formatDuration(nowToEnd);
  }

  const targetTime = endTime.diff(startTime);
  startToEnd.value = dayjs.duration(targetTime).asDays();
};

// 시간 실시간으로 보여주기 위한 format
const formatDuration = (milliseconds) => {
  const duration = dayjs.duration(milliseconds);
  const totalDays = Math.floor(duration.asDays()); // 총 일 수 계산
  const hours = duration.hours();
  const minutes = duration.minutes();
  const seconds = duration.seconds();
  return `${totalDays}일 ${hours}시간 ${minutes}분 ${seconds}초`;
};

// 이미지 동적 경로
// 아이콘
const iconImages = import.meta.glob("@/assets/addiction/icons/*.png", {
  eager: true,
});
const getIconImage = (filePath) => {
  return iconImages[filePath]?.default || "";
};

// 뱃지
const badgeImages = import.meta.glob("@/assets/addiction/badges/*.png", {
  eager: true,
});
const getBadgeImage = (fileName) => {
  const key = `/src/assets/addiction/badges/${fileName}`;
  if (badgeImages[key]) {
    return badgeImages[key].default;
  } else {
    console.error(`Image not found: ${fileName}`);
    return "";
  }
};

// 수정 - 목표 일수
const updateTarget = () => {
  router.push(`update/${addictionId}`);
};

// 삭제 모달
const openModal = (id) => {
  modalStore.showModal(id, addictionId);
};
const deleteAddiction = async () => {
  const addictionId = addictionItem.value.addiction.addictionId;
  await store.deleteAddiction(addictionId);
  router.push({ name: "addictionList" });
  modalStore.closeModal(`deleteAddiction/${addictionId}`);
};

let intervalId;

// 비동기 처리
onMounted(async () => {
  await store.getAddictionItem(addictionId);
  addictionItem.value = store.addictionItem;
  updateCurrentTime();
  intervalId = setInterval(updateCurrentTime, 1000); // 1초마다 현재 시간 갱신
});

onUnmounted(() => {
  clearInterval(intervalId);
});

// 상태 변화 감지
watch(
  () => store.addictionItem,
  (newItem) => {
    addictionItem.value = newItem;
  }
);
</script>

<style scoped>
.deleteModal {
  p {
    margin-top: 0;
    color: #f6e8da;
    font-weight: 700;
    font-size: medium;
  }
}

#item-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: calc(100vh - 3.5rem);
}

#item-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 2rem;

  margin: 3rem auto;

  width: 100%;
  height: 82vh;
  overflow: scroll;
}

#addiction-item {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 6rem;

  width: 80%;
  padding: 2rem;
  border-radius: 1rem;
}

#addiction-main {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-items: center;
  gap: 0;

  p {
    margin: 0 auto;
    color: #384427;
    font-weight: 700;
  }
}

#icon-title {
  position: relative;
  width: 12rem;
  height: 12rem;

  svg {
    position: absolute;
    top: 0;
    left: 0;

    width: 100%;
    height: 100%;
  }

  img {
    position: absolute;

    top: 45%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 27%;
  }

  p {
    position: absolute;
    top: 65%;
    left: 50%;
    transform: translate(-50%, -50%);

    width: 5rem;
    text-align: center;

    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
}

#icon-update {
  cursor: pointer;
}

#addiction-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;

  width: 60%;
  padding: 2rem;
  background-color: #dddbd9;
  border-radius: 1rem;
}

#addiction-timer {
  display: grid;
  grid-template-columns: auto auto;
  align-items: center;

  width: 70%;

  p {
    margin: 0.3rem 0;
    justify-self: flex-end;
    color: #352f26;
    font-weight: 700;
  }
}

.text-color {
  color: #573f2c;
  font-weight: 700;
}

#button-set {
  display: flex;
  justify-content: center;
  gap: 3rem;

  button {
    padding: 0.5rem 3rem;
    border: none;
    border-radius: 2rem;
    color: #dddbd9;
    font-weight: 700;
    font-size: medium;
    cursor: pointer;
  }
}

#button-set button:first-child {
  background-color: #84a063;
}

#button-set button:last-child {
  background-color: #384427;
}

#addiction-badge {
  display: flex;
  flex-direction: column;
  gap: 2rem;

  width: 80%;
  padding: 2rem;

  h2,
  p {
    margin: 0;
  }
}

#badge-title {
  display: flex;
  align-items: center;
  gap: 0.7rem;

  h2 {
    color: #352f26;
  }
}

#badge-num {
  display: flex;
  align-items: center;
  justify-content: center;

  width: 2.5rem;
  height: 2.5rem;
  background-color: #84a063;
  border-radius: 50%;

  color: white;
  font-weight: 700;
}

#no-badge {
  padding: 1rem;
  color: #573f2c;
}

#badge-list {
  display: flex;
  align-items: flex-end;
  flex-wrap: wrap;
  gap: 3rem;

  padding: 2rem 5rem;
}

/* 스크롤바 제거 */
#item-box::-webkit-scrollbar {
  display: none; /* Chrome, Edge, and Safari */
}
#item-box {
  scrollbar-width: none; /* Firefox */
}
#item-box {
  -ms-overflow-style: none; /* IE11 */
}

/* media query */
@media (max-width: 990px) {
  #addiction-item {
    flex-direction: column;
    gap: 2rem;
  }

  #addiction-info {
    align-items: center;
  }

  #addiction-timer {
    grid-template-columns: auto auto;
    align-items: center;
    width: 90%;

    p {
      justify-self: flex-end;
    }
  }

  #button-set {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-self: stretch;
    gap: 0.5rem;

    button {
      padding: 0.5rem 2rem;
      border-radius: 1rem;

      font-weight: 700;
      font-size: small;
    }
  }
}

@media (max-width: 700px) {
  #addiction-timer {
    grid-template-columns: auto;
    width: 90%;
    p {
      justify-self: auto;
      font-size: small;
    }
  }
}

@media (max-width: 468px) {
  #badge-list {
    justify-content: center;
  }

  .deleteModal {
    p {
      font-size: small;
    }
  }
}
</style>
