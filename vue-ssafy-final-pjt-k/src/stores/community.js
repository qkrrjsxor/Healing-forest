import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";

const COMMUNITY_REST_API = "http://localhost:8080/api-community";

export const useCommunityStore = defineStore("community", () => {
  // state
  const topThreeUsers = ref([]);
  const myScore = ref(0);

  // action
  // 1. 명예의 전당 (top 3 조회)
  const getTopRank = async () => {
    try {
      const res = await axios.get(`${COMMUNITY_REST_API}/rank`, {
        withCredentials: true,
      });
      topThreeUsers.value = res.data;
    } catch (err) {
      console.error("Top 3 랭킹 조회 중 오류: ", err);
    }
  };

  // 2. 나의 점수 조회
  const getMyRank = async () => {
    try {
      const res = await axios.get(`${COMMUNITY_REST_API}/myscore`, {
        withCredentials: true,
      });
      myScore.value = res.data;
    } catch (err) {
      console.err("My Rank 조회 중 오류: ", err);
    }
  };

  // 3. 댓글 - 전체 조회
  const getAllComment = async () => {
    try {
      const res = await axios.get(`${COMMUNITY_REST_API}/comment`, {
        withCredentials: true,
      });
      return res.data;
    } catch (err) {
      console.log("댓글 전체 조회 중 error 발생", err);
    }
  };

  return {
    topThreeUsers,
    myScore,
    getTopRank,
    getMyRank,
    getAllComment,
  };
});
