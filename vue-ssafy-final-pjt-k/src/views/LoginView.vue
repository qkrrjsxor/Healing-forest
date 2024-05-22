<template>
  <div id="container">
    <section id="login-section">
      <div id="login-form-box">
        <Alert id="alert" />
        <h2>Log In</h2>
        <form @submit.prevent="submitLogin" id="login-form">
          <div id="input-set">
            <input v-model="loginUser.userId" type="text" placeholder="아이디를 입력해주세요." autocomplete="username"
              autofocus />
            <input v-model="loginUser.password" type="password" placeholder="비밀번호를 입력해주세요."
              autocomplete="current-password" />
          </div>
          <div id="button-set">
            <button type="button" @click="goHome">홈으로</button>
            <button type="submit">로그인</button>
          </div>
          <RouterLink :to="{ name: 'signup' }">아직 회원가입을 하지 않았나요?</RouterLink>
        </form>
      </div>
    </section>
    <img v-if="showImage" id="login-image" src="@/assets/signup-login.jpg" alt="login-image" />
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { ref, watchEffect } from "vue";
import { useRouter } from "vue-router";
import Alert from "@/components/common/Alert.vue";

const router = useRouter();
const store = useUserStore();

const showImage = ref(true);

const loginUser = ref({
  userId: "",
  password: "",
});

// [반응형] 화면 리사이징 -> image 제거
watchEffect(() => {
  const checkWidth = () => {
    showImage.value = window.innerWidth > 1024;
  };

  window.addEventListener("resize", checkWidth);
  checkWidth();

  // 정리
  return () => {
    window.removeEventListener("resize", checkWidth);
  };
});

// 홈으로
const goHome = () => {
  router.push({ name: "home" });
};

// 로그인 요청
const submitLogin = () => {
  store.submitLogin(loginUser.value);
};
</script>

<style scoped>
#container {
  display: flex;
  height: 100vh;
  width: 100vw;
}

#login-section {
  display: flex;
  align-items: center;
  justify-content: center;

  flex: 1;

  h2 {
    color: #9d8e03;
    font-weight: 800;
    font-size: xx-large;
  }
}

#login-form-box {
  position: relative;

  display: flex;
  flex-direction: column;
  gap: 4rem;

  width: 50%;
  min-width: 400px;
  padding: 2rem;
}

#alert {
  position: absolute;
  top: 0;
  left: 50%;
  transform: translate(-50%, -150%);
  width: 85%;
}

#login-form {
  display: flex;
  flex-direction: column;
  gap: 3rem;
}

#input-set {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

input {
  width: 100%;
  padding: 1rem 1.5rem;
  border: 3px solid #9d8e03;
  border-radius: 20px;
  box-sizing: border-box;

  color: #352f26;
  font-size: medium;
}

#button-set {
  display: flex;
  justify-content: center;
  gap: 1rem;

  width: 60%;
  margin: 0 auto;
}

button {
  flex: 1;
  padding: 0.5rem 1rem;
  border-radius: 20px;
  font-weight: 700;
  font-size: medium;
  cursor: pointer;
}

button:first-child {
  border: 3px solid #9d8e03;
  background-color: transparent;
  color: #9d8e03;
}

button:last-child {
  border: none;
  background-color: #9d8e03;
  color: #f6e8da;
}

a {
  display: block;
  margin: 0 auto;
  color: #9d8e03;
  font-size: small;
  font-weight: 700;
}

/* media query */
@media (max-width: 1024px) {
  #login-form-box {
    width: 60%;
  }

  input {
    font-size: small;
  }

  button {
    font-size: small;
  }
}

@media (max-height: 768px) {
  #alert {
    transform: translate(-50%, -100%);
  }
}
</style>
