import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";

const COMMUNITY_REST_API = "http://localhost:8080/api-community";

export const useCommunityStore = defineStore("community", () => {
  // state
  const topThreeUsers = ref([]);
  const myScore = ref(0);
  const myRank = ref(0);
  const userCount = ref(0);

  const allComment = ref([]);

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
      myScore.value = res.data.myScore;
      myRank.value = res.data.myRank;
      userCount.value = res.data.userCount;
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
      allComment.value = res.data;
      return res.data;
    } catch (err) {
      console.log("댓글 전체 조회 중 error 발생", err);
    }
  };

  // 4. 댓글 - 등록
  const addComment = async (comment) => {
    try {
      await axios({
        url: `${COMMUNITY_REST_API}/comment`,
        method: "POST",
        withCredentials: true,
        headers: { "Content-Type": "text/plain" }, // 단순 문자열 타입 설정
        data: comment,
      });
      await getAllComment(); // 댓글 추가 후 리스트 갱신
    } catch (err) {
      console.log(err);
    }
  };

  // 5. 댓글 - 수정
  const updateComment = async (commentId, content) => {
    try {
      await axios({
        url: `${COMMUNITY_REST_API}/comment/${commentId}`,
        method: "PUT",
        withCredentials: true,
        data: { content },
      });
      await getAllComment(); // 댓글 수정 후 리스트 갱신
    } catch (err) {
      console.error("댓글 수정 중 오류: ", err);
    }
  };

  return {
    topThreeUsers,
    myScore,
    allComment,
    myRank,
    userCount,
    getTopRank,
    getMyRank,
    getAllComment,
    addComment,
    updateComment,
  };
});
