<template>
  <div id="rank-container">
    <h1>명예의 전당</h1>
    <ul id="rank-list" v-if="topThreeUsers.length > 0">
      <li class="rank-item">
        <img src="@/assets/community/rank/rank_2.png" alt="rank_2" />
        <p>
          {{ topThreeUsers[1].nickname }} 님 |
          {{ topThreeUsers[1].userScore }} 점
        </p>
      </li>
      <li class="rank-item">
        <img src="@/assets/community/rank/rank_1.png" alt="rank_1" />
        <p>
          {{ topThreeUsers[0].nickname }} 님 |
          {{ topThreeUsers[0].userScore }} 점
        </p>
      </li>
      <li class="rank-item">
        <img src="@/assets/community/rank/rank_3.png" alt="rank_3" />
        <p>
          {{ topThreeUsers[2].nickname }} 님 |
          {{ topThreeUsers[2].userScore }} 점
        </p>
      </li>
    </ul>
    <button id="my-rank" @click="showMyRank">
      나의 랭킹 보기
      <span v-if="showRank">▲</span>
      <span v-else>▼</span>
    </button>
    <div v-if="showRank" id="my-rank-result">
      <img
        id="my-rank-icon"
        src="@/assets/community/rank/rank_my.png"
        alt="my-rank-icon"
      />
      <p>{{ loginUserNickname }} 님, {{ myScore }}점으로 전체 {{ userCount }}명 중 {{ myRank }}위 입니다!</p>
    </div>
  </div>
</template>

<script setup>
import { useCommunityStore } from "@/stores/community";
import { ref, onMounted } from "vue";

const store = useCommunityStore();

const topThreeUsers = ref([]);

const showRank = ref(false);
const myScore = ref(0);
const myRank = ref(0);
const userCount = ref(0);
const loginUserNickname = ref("");

// 나의 랭킹 조회
const showMyRank = async () => {
  showRank.value = !showRank.value;
  await store.getMyRank();
  myScore.value = store.myScore;
  myRank.value = store.myRank;
  userCount.value = store.userCount;
};

onMounted(async () => {
  loginUserNickname.value = JSON.parse(
    sessionStorage.getItem("loginUser")
  ).nickname;
  await store.getTopRank();
  topThreeUsers.value = store.topThreeUsers;
});
</script>

<style scoped>
#rank-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;

  max-width: 1280px;
  margin: 0 auto;
  padding: 3rem 2rem;
  padding-top: 5rem;

  h1 {
    color: #352f26;
    margin: 0;
    margin-bottom: 3rem;
  }
}

#rank-list {
  display: flex;
  margin: 2rem auto;
  gap: 1rem;
}

#rank-list > li:nth-child(2) {
  transform: translateY(-3rem);
}

.rank-item {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;

  img {
    width: 60%;
  }

  p {
    text-align: center;
    font-size: medium;
    font-weight: 700;
    margin: 0;
  }
}

#my-rank {
  display: flex;
  justify-content: center;

  max-width: 800px;
  width: 80%;
  margin: 0 auto;
  padding: 0.5rem;

  background-color: #faec00;
  border: none;
  border-radius: 1rem;

  font-weight: 600;
  font-size: medium;
  cursor: pointer;

  span {
    margin-top: 0.2rem;
    margin-left: 0.5rem;
  }
}

#my-rank-result {
  display: flex;
  align-items: center;

  color: #352f26;
  font-weight: 700;

  p {
    margin: 0;
    padding-right: 2rem;
  }
}

#my-rank-icon {
  width: 9rem;
}

/* media query */
@media (max-width: 768px) {
  .rank-item {
    p {
      font-size: small;
    }
  }

  #my-rank {
    font-size: small;
  }
}
</style>
