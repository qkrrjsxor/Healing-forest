<template>
  <div id="item-container">
    <div id="item-box">
      <div id="addiction-item">
        <div>알코올</div>
        <div id="addiction-info">
          <div id="addiction-timer">
            <span class="text-color">금욕 시간 </span>
            <p>4일 1시간 13초</p>
            <span class="text-color">목표까지 남은 시간 </span>
            <p>2일 22시간 59분 47초</p>
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
import { useRoute } from "vue-router";
const route = useRoute();

const addictionId = route.params.id;
console.log(addictionId);
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
  justify-content: space-between;
  align-items: center;

  width: 80%;
  margin: 0 auto;
  padding: 2rem;
  background-color: #7a8579;
  border-radius: 1rem;
}

#addiction-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;

  width: 70%;
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
  }
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
@media (max-width: 800px) {
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

@media (max-width: 600px) {
  #addiction-timer {
    grid-template-columns: auto;
    width: 90%;
    p {
      justify-self: auto;
    }
  }
}
</style>
