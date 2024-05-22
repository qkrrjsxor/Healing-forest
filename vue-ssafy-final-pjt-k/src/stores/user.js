import router from "@/router";
import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";
import { useAlertStore } from "./alert";

const USER_REST_API = "http://localhost:8080/api-user";

export const useUserStore = defineStore("user", () => {
  // state
  const loginUser = ref(null);

  // actions
  // 1) 로그인 요청
  const submitLogin = async (user) => {
    const alertStore = useAlertStore();

    try {
      const res = await axios({
        url: `${USER_REST_API}/login`,
        method: "POST",
        withCredentials: true,
        data: user,
      });
      loginUser.value = {
        userId: res.data.userId,
        nickname: res.data.nickname,
      };
      sessionStorage.setItem("loginUser", JSON.stringify(loginUser.value));
      router.push({ name: "addictionList" });
    } catch (err) {
      if (err.response) {
        alertStore.setAlert(err.response.data, "login");
      } else {
        alertStore.setAlert("서버 연결이 끊어졌습니다.", "login");
      }
    }
  };

  // 2) 로그아웃 요청
  const submitLogout = async () => {
    const alertStore = useAlertStore();

    try {
      await axios({
        url: `${USER_REST_API}/logout`,
        method: "GET",
        withCredentials: true,
      });
      loginUser.value = null;
      sessionStorage.removeItem("loginUser");
      alertStore.setAlert("로그아웃 되었습니다.", "logout");
      setTimeout(() => {
        router.push({ name: "home" });
      }, 1000);
    } catch (err) {
      if (err.response) {
        alertStore.setAlert(err.response.data, "logout");
      } else {
        alertStore.setAlert("서버 연결이 끊어졌습니다.", "logout");
      }
    }
  };

  // 3) 회원가입 요청
  const submitSignup = async (userId, password, nickname) => {
    const alertStore = useAlertStore();

    const signupData = {
      userId,
      password,
      nickname,
    };

    try {
      const res = await axios({
        url: `${USER_REST_API}/signup`,
        method: "POST",
        withCredentials: true,
        data: signupData,
      });
      // 회원가입 성공 후 바로 로그인
      if (res.status === 200) {
        await submitLogin({ userId, password });
      } else {
        alertStore.setAlert("회원가입에 실패했습니다.", "signup");
      }
    } catch (err) {
      if (err.response) {
        alertStore.setAlert(err.response.data, "signup");
      } else {
        alertStore.setAlert("서버 연결이 끊어졌습니다.", "signup");
      }
    }
  };

  return {
    loginUser,
    submitLogin,
    submitLogout,
    submitSignup,
  };
});
