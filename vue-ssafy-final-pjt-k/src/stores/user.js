import { useRouter } from "vue-router";
import { useAlertStore } from "./alert";
import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";

const USER_REST_API = "http://localhost:8080/api-user";

export const useUserStore = defineStore("user", () => {
  const router = useRouter();

  // state
  const loginUser = ref(null);

  // actions
  // 1) 로그인 요청
  const submitLogin = async (user, fromSignup = false) => {
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
      if (fromSignup) {
        alertStore.setAlert(
          "힐링 포레스트에 오신 것을 환영합니다.",
          "signup",
          "SignupView"
        );
      } else {
        alertStore.setAlert(
          "힐링 포레스트에 오신 것을 환영합니다.",
          "login",
          "LoginView"
        );
      }
      setTimeout(() => {
        router.push({ name: "addictionList" });
      }, 1000);
    } catch (err) {
      const viewName = fromSignup ? "SignupView" : "LoginView";
      if (err.response) {
        alertStore.setAlert(err.response.data, "login", viewName);
      } else {
        alertStore.setAlert("네트워크 오류가 발생했습니다.", "login", viewName);
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
      alertStore.setAlert("로그아웃 되었습니다.", "logout", "ProfileModal");
      setTimeout(() => {
        router.push({ name: "home" });
      }, 1000);
    } catch (err) {
      if (err.response) {
        alertStore.setAlert(err.response.data, "logout", "ProfileModal");
      } else {
        alertStore.setAlert(
          "네트워크 오류가 발생했습니다.",
          "logout",
          "ProfileModal"
        );
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
        await submitLogin({ userId, password }, true); // fromSignup을 true로 설정
      } else {
        alertStore.setAlert("회원가입에 실패했습니다.", "signup", "SignupView");
      }
    } catch (err) {
      if (err.response) {
        alertStore.setAlert(err.response.data, "signup", "SignupView");
      } else {
        alertStore.setAlert(
          "네트워크 오류가 발생했습니다.",
          "signup",
          "SignupView"
        );
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
