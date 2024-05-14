import router from "@/router";
import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useUserStore = defineStore("user", () => {
  // state
  const loginUser = ref(null);
  const USER_REST_API = "http://localhost:8080/api-user";

  // actions
  // 1) 로그인 요청
  const submitLogin = (user) => {
    axios({
      url: `${USER_REST_API}/login`,
      method: "POST",
      data: user,
    })
      .then((res) => {
        loginUser.value = res.data.nickname;
        sessionStorage.setItem("loginUser", res.data.nickname);
        router.push({ name: "addictionList" });
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return {
    loginUser,
    submitLogin,
  };
});
