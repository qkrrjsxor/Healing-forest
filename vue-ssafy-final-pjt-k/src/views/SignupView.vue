<template>
  <div id="container">
    <img
      v-if="showImage"
      id="login-image"
      src="@/assets/signup_login.jpg"
      alt="login-image"
    />
    <section id="login-section">
      <div id="login-form-box">
        <h2>Sign up</h2>
        <form id="login-form">
          <ul id="input-set">
            <li>
              <input type="text" placeholder="아이디를 입력해주세요." />
              <p></p>
            </li>
            <li>
              <input type="text" placeholder="비밀번호를 입력해주세요." />
              <p></p>
            </li>
            <li>
              <input type="text" placeholder="닉네임을 입력해주세요." />
              <p></p>
            </li>
          </ul>
          <div id="button-set">
            <button @click="goHome">홈으로</button>
            <button>회원가입</button>
          </div>
          <RouterLink :to="{ name: 'login' }">이미 회원이신가요?</RouterLink>
        </form>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref, watchEffect } from "vue";
import { useRouter } from "vue-router";

const showImage = ref(true);

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

const router = useRouter();
// 홈으로
const goHome = () => {
  router.push({ name: "home" });
};
</script>

<style scoped>
#container {
  display: flex;
  height: 100vh;
  width: 100vw;
  overflow: hidden;
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
  display: flex;
  flex-direction: column;
  gap: 4rem;

  width: 50%;
  min-width: 400px;
  padding: 2rem;
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
</style>
