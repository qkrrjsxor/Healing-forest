import router from "@/router";
import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";

const ADDICTION_REST_API = "http://localhost:8080/api-addiction";

export const useAddictionStore = defineStore("addiction", () => {
  // state
  const addictionList = ref([]);

  // actions
  // 1. 전체 조회
  const getAddictionList = () => {
    axios.get(`${ADDICTION_REST_API}/addiction`).then((res) => {
      addictionList.value = res.data;
    });
  };

  return {
    addictionList,
    getAddictionList,
  };
});
