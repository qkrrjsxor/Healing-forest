import router from "@/router";
import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";

const USER_REST_API = "http://localhost:8080/api-user";

export const useUserStore = defineStore("user", () => {
  // state
  const loginUser = ref(null);

  // actions
  // 1) 로그인 요청
  const submitLogin = (user) => {
    axios({
      url: `${USER_REST_API}/login`,
      method: "POST",
      withCredentials: true,
      data: user,
    })
      .then((res) => {
        loginUser.value = {
          userId: res.data.userId,
          nickname: res.data.nickname,
        };
        sessionStorage.setItem(
          "loginUser",
          JSON.stringify({
            userId: res.data.userId,
            nickname: res.data.nickname,
          })
        );
        router.push({ name: "addictionList" });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // 2) 로그아웃 요청
  const submitLogout = () => {
    axios({
      url: `${USER_REST_API}/logout`,
      method: "GET",
      withCredentials: true,
    }).then(() => {
      loginUser.value = null;
      sessionStorage.removeItem("loginUser");
      router.push({ name: "home" });
    });
  };
  return {
    loginUser,
    submitLogin,
    submitLogout,
  };
});
