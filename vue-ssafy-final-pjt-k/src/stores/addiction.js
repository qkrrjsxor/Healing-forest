import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";

const ADDICTION_REST_API = "http://localhost:8080/api-addiction";

export const useAddictionStore = defineStore("addiction", () => {
  // state
  const addictionList = ref([]);
  const addictionItem = ref({
    addiction: {},
    badges: [],
  });
  const addictionCreate = ref([]);

  const isLoading = ref(false);

  // actions
  // 1. 전체 조회
  const getAddictionList = async () => {
    try {
      const res = await axios.get(`${ADDICTION_REST_API}/addiction`, {
        withCredentials: true,
      });
      addictionList.value = res.data;
    } catch (err) {
      console.error("addictionList 전체 조회 중 오류: ", err);
    }
  };

  // 2. 중독 리스트 등록
  const submitAddiction = async (addiction) => {
    try {
      const res = await axios({
        url: `${ADDICTION_REST_API}/addiction`,
        method: "POST",
        withCredentials: true,
        data: addiction,
      });
      addictionCreate.value = res.data;
      await getAddictionList(); // 항목 추가하면, 최신 리스트 가져오기
    } catch (error) {
      console.log(err);
    }
  };

  // 3. 상세 조회 - 비동기 처리
  const getAddictionItem = async (id) => {
    isLoading.value = true;
    try {
      const res = await axios.get(`${ADDICTION_REST_API}/addiction/${id}`, {
        withCredentials: true,
      });
      if (res.data && res.data.addiction && Array.isArray(res.data.badges)) {
        addictionItem.value = res.data;
      } else {
        console.error("부적합한 데이터 구조: ", res.data);
      }
    } catch (err) {
      console.error("addicionItem 가져오는 과정에서 오류 발생: ", err);
    } finally {
      isLoading.value = false;
    }
  };

  // 4. 수정
  const updateAddiction = async (id, targetTime) => {
    try {
      const res = await axios({
        url: `${ADDICTION_REST_API}/addiction/${id}`,
        method: "PUT",
        withCredentials: true,
        data: { targetTime },
      });
      if (res.data) {
        await getAddictionItem(id); // 상세 페이지 갱신
        await getAddictionList(); // 전체 조회 페이지 갱신
      }
    } catch (err) {
      console.error("addiction 업데이트 중 오류 발생: ", err);
    }
  };

  // 5. 삭제
  const deleteAddiction = async (addictionId) => {
    try {
      const res = await axios({
        url: `${ADDICTION_REST_API}/addiction/${addictionId}`,
        method: "DELETE",
        withCredentials: true,
      });
      if (res.data) {
        await getAddictionList();
      }
    } catch (err) {
      console.log("삭제 과정 중 error 발생: ", err);
    }
  };

  // 6. 아이콘 수정
  const updateIcon = async (id, iconPath) => {
    try {
      const res = await axios({
        url: `${ADDICTION_REST_API}/addiction/icon/${id}`,
        method: "PUT",
        withCredentials: true,
        data: { iconPath },
      });
      if (res.data) {
        addictionItem.value.addiction.iconPath = iconPath;
        await getAddictionItem(id); // 상세 페이지 갱신
        await getAddictionList(); // 전체 조회 페이지 갱신
      }
    } catch (err) {
      console.error("addiction 아이콘 업데이트 중 오류 발생: ", err);
    }
  };

  return {
    addictionList,
    addictionItem,
    getAddictionList,
    submitAddiction,
    getAddictionItem,
    updateAddiction,
    deleteAddiction,
    updateIcon,
  };
});
