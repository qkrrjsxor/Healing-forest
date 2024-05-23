<template>
  <div id="container">
    <section id="login-section">
      <div id="login-form-box">
        <Alert id="alert" componentId="LoginView" />
        <h2>Log In</h2>
        <form id="login-form" @submit.prevent="submitLogin">
          <div id="input-set">
            <input
              v-model="loginUser.userId"
              type="text"
              placeholder="아이디를 입력해주세요."
              autocomplete="username"
              ref="userIdInputRef"
              autofocus
            />
            <input
              v-model="loginUser.password"
              type="password"
              placeholder="비밀번호를 입력해주세요."
              autocomplete="current-password"
            />
          </div>
          <div id="button-set">
            <button type="button" @click="goHome">홈으로</button>
            <button type="submit">로그인</button>
          </div>
          <RouterLink :to="{ name: 'signup' }"
            >아직 회원가입을 하지 않으셨나요?</RouterLink
          >
        </form>
      </div>
    </section>
    <img
      v-if="showImage"
      id="login-image"
      src="@/assets/auth/signup-login.jpg"
      alt="login-image"
    />
  </div>
</template>

<script setup>
import Alert from "@/components/common/Alert.vue";
import { useUserStore } from "@/stores/user";
import { onMounted, ref, watchEffect } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const store = useUserStore();

const showImage = ref(true);

const loginUser = ref({
  userId: "",
  password: "",
});

const userIdInputRef = ref("");

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

onMounted(() => {
  userIdInputRef.value.focus();
});
</script>

<style scoped>
#container {
  display: flex;
  height: 100vh;
}

#login-section {
  display: flex;
  align-items: center;
  justify-content: center;

  flex: 1;
  overflow: scroll;

  h2 {
    margin: 0;
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

  padding-top: 2rem;
  width: 60%;
}

#alert {
  position: absolute;
  top: 0;
  left: 50%;
  transform: translate(-50%, -150%);
  width: 90%;
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
  padding: 1rem 1.5rem;
  border: 3px solid #9d8e03;
  border-radius: 1rem;
  box-sizing: border-box;

  color: #352f26;
  font-size: medium;
}

#button-set {
  display: flex;
  gap: 1rem;

  width: 60%;
  margin: 0 auto;
}

button {
  flex: 1;
  padding: 0.5rem 1rem;
  border-radius: 1.2rem;

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

/* 스크롤바 제거 */
#login-section::-webkit-scrollbar {
  display: none; /* Chrome, Edge, and Safari */
}
#login-section {
  scrollbar-width: none; /* Firefox */
}
#login-section {
  -ms-overflow-style: none; /* IE11 */
}

/* media query */
@media (max-width: 1280px) {
  #login-form-box {
    width: 80%;
  }

  input {
    padding: 0.8rem 1rem;
  }

  #button-set {
    width: 70%;
  }
}

@media (max-width: 1024px) {
  #login-form-box {
    width: 60%;
    min-width: 20rem;
  }

  input {
    padding: 1rem 1.5rem;
  }

  #alert {
    font-size: small;
  }
}

@media (max-width: 768px) {
  input {
    font-size: small;
  }

  #button-set {
    width: 80%;

    button {
      font-size: small;
    }
  }
}

@media (max-height: 768px) {
  input {
    font-size: small;
  }

  button {
    font-size: small;
  }

  #alert {
    transform: translate(-50%, -100%);
    font-size: small;
  }
}
</style>
