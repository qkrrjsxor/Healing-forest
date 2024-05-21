<template>
  <div id="rank-container">
    <h1>명예의 전당</h1>
    <ul id="rank-list">
      <li class="rank-item">
        <img src="@/assets/community/rank/rank_2.png" alt="rank_2" />
        <p>ssafy2 님 | 2900 점</p>
      </li>
      <li class="rank-item">
        <img src="@/assets/community/rank/rank_1.png" alt="rank_1" />
        <p>ssafy 님 | 3900 점</p>
      </li>
      <li class="rank-item">
        <img src="@/assets/community/rank/rank_3.png" alt="rank_3" />
        <p>ssafy3 님 | 2800 점</p>
      </li>
    </ul>
    <button id="my-rank">나의 랭킹 보기</button>
  </div>
</template>

<script setup></script>

<style scoped>
#rank-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 3rem;

  max-width: 1280px;
  margin: 0 auto;
  padding: 3rem 2rem;
  padding-top: 5rem;

  /* background-color: antiquewhite; */

  h1 {
    color: #352f26;
    margin: 0;
  }
}

#rank-list {
  display: flex;
  padding-top: 2rem;
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
