import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";

const ADDICTION_REST_API = "http://localhost:8080/api-addiction";

export const useAddictionStore = defineStore("addiction", () => {
  // state
  const addictionList = ref([]);
  const addictionCreate = ref([]);

  // actions
  // 1. 전체 조회
  const getAddictionList = () => {
    axios
      .get(`${ADDICTION_REST_API}/addiction`, {
        withCredentials: true,
      })
      .then((res) => {
        console.log(res.data);
        addictionList.value = res.data;
      });
  };

  // 2. 중독 리스트 등록
  const submitAddiction = (addiction) => {
    console.log("test")
    axios({
      url: `${ADDICTION_REST_API}/addiction`,
      method: "POST",
      withCredentials: true,
      data: addiction
    })
      .then((res) => {
        console.log(res.data);
        addictionCreate.value = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return {
    addictionList,
    getAddictionList,
    submitAddiction,
  };
});
