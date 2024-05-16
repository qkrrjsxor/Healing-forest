<template>
  <li id="addiction-item">
    <div id="item-top">
      <div id="item-title">
        <img src="@/assets/addiction/icons/alcohol.png" alt="alcohol" />
        <h3>{{ addiction.title }}</h3>
      </div>
      <div id="item-progress">
        <div id="progress-num">
          <span class="text-color">목표 달성률</span>
          <span class="text-color"
            >{{ ((startToCurrent / startToEnd) * 100).toFixed(1) }}%</span
          >
        </div>
        <div id="progress-container">
          <div id="progress-bar" :style="{ width: progressWidth }"></div>
        </div>
      </div>
    </div>

    <div id="item-bottom">
      <span class="text-color">금욕 시간 </span>
      <p>{{ enduredTime }}</p>
      <span class="text-color">현재 목표 </span>
      <p>{{ addiction.targetTime }}일</p>
    </div>
  </li>
</template>

<script setup>
import dayjs from "dayjs";
import duration from "dayjs/plugin/duration";
import { computed, onMounted, onUnmounted, ref } from "vue";

// 플러그인 등록
dayjs.extend(duration);

const props = defineProps({
  addiction: Object,
});

const startToCurrent = ref("");
const startToEnd = ref("");
const enduredTime = ref("");

// 현재 시간 갱신
const updateCurrentTime = () => {
  // 시작, 현재, 끝
  const startTime = dayjs(props.addiction.startTime).subtract(9, "hour");
  const now = dayjs();
  const endTime = dayjs(props.addiction.endTime).subtract(9, "hour");

  // 차이 계산
  const startToNow = now.diff(startTime);
  startToCurrent.value = dayjs.duration(startToNow).asDays();
  enduredTime.value = formatDuration(startToNow);

  const targetTime = endTime.diff(startTime);
  startToEnd.value = dayjs.duration(targetTime).asDays();
};

// 금욕 시간 실시간으로 보여주기 위한 format
const formatDuration = (milliseconds) => {
  const duration = dayjs.duration(milliseconds);
  const days = duration.days();
  const hours = duration.hours();
  const minutes = duration.minutes();
  const seconds = duration.seconds();
  return `${days}일 ${hours}시간 ${minutes}분 ${seconds}초`;
};

// progress-bar 넓이 계산
const progressWidth = computed(() => {
  const percentage = ((startToCurrent.value / startToEnd.value) * 100).toFixed(
    1
  );
  return `${percentage}%`;
});

// 디테일 페이지로 이동
const showDetail = () => {};

let intervalId;

onMounted(() => {
  updateCurrentTime();
  intervalId = setInterval(updateCurrentTime, 1000); // 1초마다 현재 시간 갱신
});

onUnmounted(() => {
  clearInterval(intervalId);
});
</script>

<style scoped>
#addiction-item {
  display: flex;
  flex-direction: column;

  padding: 2rem 4rem;
  width: 80%;
  background-color: #b6c2a9;
  border-radius: 1.5rem;

  color: #384427;
}

#item-top {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

#item-title {
  display: flex;
  align-items: center;
  gap: 0.5rem;

  img {
    height: 2rem;
  }
}

#item-progress {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  width: 70%;
}

#progress-num {
  display: flex;
  justify-content: space-between;
}

#progress-container {
  height: 0.7rem;
  background-color: #eaeceb;
  border-radius: 0.5rem;
}

#progress-bar {
  height: 0.7rem;
  background-color: #f4ef26;
  border-radius: 0.5rem;
  min-width: 0.5rem;
}

#item-bottom {
  display: grid;
  grid-template-columns: auto auto;
  column-gap: 1rem;
  align-self: flex-end;
  align-items: center;
  justify-items: flex-end;

  row-gap: 0;
  p {
    margin: 0.3rem 0;
  }
}

.text-color {
  color: #eaeceb;
}

/* media query */
@media (max-width: 768px) {
  #addiction-item {
    font-size: small;
  }

  #item-progress {
    width: 60%;
  }

  #progress-container,
  #progress-bar {
    height: 0.5rem;
  }
}
</style>
