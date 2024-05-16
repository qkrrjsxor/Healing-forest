<template>
  <div id="item-container">
    <div id="item-box">
      <div id="addiction-item">
        <div id="addiction-main">
          <div id="icon-title">
            <img
              id="icon"
              src="@/assets/addiction/icons/alcohol.png"
              alt="alcohol"
            />
            <p>{{ addictionItem.addiction.title }}</p>
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
            <button>목표 변경하기</button>
            <button>도전 종료하기</button>
          </div>
        </div>
      </div>
      <div id="addiction-badge">
        <p>획득한 뱃지</p>
        <p>뱃지 이미지가 들어갑니다.</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from "vue";
import { useRoute } from "vue-router";
import { useAddictionStore } from "@/stores/addiction";
import dayjs from "dayjs";
import duration from "dayjs/plugin/duration";

// 플러그인 등록
dayjs.extend(duration);

const route = useRoute();
const store = useAddictionStore();

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
  currentToEnd.value = dayjs.duration(nowToEnd).asDays();
  restTime.value = formatDuration(nowToEnd);

  const targetTime = endTime.diff(startTime);
  startToEnd.value = dayjs.duration(targetTime).asDays();
};

// 시간 실시간으로 보여주기 위한 format
const formatDuration = (milliseconds) => {
  const duration = dayjs.duration(milliseconds);
  const days = duration.days();
  const hours = duration.hours();
  const minutes = duration.minutes();
  const seconds = duration.seconds();
  return `${days}일 ${hours}시간 ${minutes}분 ${seconds}초`;
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
</script>

<style scoped>
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
  gap: 1.5rem;

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
  /* background-color: #7a8579; */
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
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 0.3rem;

  width: 5rem;
  height: 5rem;
  padding: 2rem;
  border-radius: 50%;
  border: 20px solid #d1d3d2;
}

/* #icon {
  width: 2.2rem;
} */

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
  margin: 0 auto;
  padding: 2rem;
  background-color: #7a8579;
  border-radius: 1rem;

  p {
    margin: 0;
  }
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
    }
  }
}
</style>
