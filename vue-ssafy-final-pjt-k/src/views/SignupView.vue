<template>
  <div id="container">
    <img v-if="showImage" id="login-image" src="@/assets/signup-login.jpg" alt="login-image" />
    <section id="login-section">
      <div id="login-form-box">
        <h2>Sign up</h2>
        <form id="login-form">
          <ul id="input-set">
            <li>
              <input type="text" placeholder="아이디를 입력해주세요." v-model="userId" @input="validateUserId" autofocus />
              <p v-if="!isValidateId" class="validateAlert">아이디는 4~12자의 영문 대소문자와 숫자로 이루어져야 합니다.</p>
            </li>
            <li>
              <input type="password" placeholder="비밀번호를 입력해주세요." v-model="password" @input="validatePassword" />
              <p v-if="!isValidatePassword" class="validateAlert">비밀번호는 8자 이상으로, 영문, 숫자, 특수문자를 모두 포함해야 합니다.</p>
            </li>
            <li>
              <input type="text" placeholder="닉네임을 입력해주세요." :value="nickname" @input="setNickname" />
              <p v-if="!isValidateNickname" class="validateAlert">닉네임은 2글자 이상이어야 합니다. </p>
            </li>
          </ul>
          <div id="button-set">
            <button @click="goHome">홈으로</button>
            <button type="button" @click.prevent="submitSignup">회원가입</button>
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
import { useUserStore } from "@/stores/user";

const showImage = ref(true);
const store = useUserStore();

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

// 정규식 조건
const userIdPattern = /^[a-zA-Z0-9]{4,12}$/;  // 4~12 글자 사이, 영어 소문자, 대문자, 숫자만 가능
const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/; //8글자 이상, 영대문자, 소문자, 숫자, 특수문자 포함
const nicknamePattern = /^.{2,}$/;

const userId = ref('');
const password = ref('');
const nickname = ref('');

const isValidateId = ref(true);
const isValidatePassword = ref(true);
const isValidateNickname = ref(true);

const validateUserId = () => {
  isValidateId.value = userIdPattern.test(userId.value) ? true : false;
  // console.log(isValidateId.value)
}
const validatePassword = () => {
  isValidatePassword.value = passwordPattern.test(password.value) ? true : false;
  // console.log(isValidatePassword.value)
};
const validateNickname = (nickname) => {

  isValidateNickname.value = nicknamePattern.test(nickname) ? true : false;
  // console.log(isValidateNickname.value)
  // console.log(nickname)
};

const setNickname = (event) => {
  nickname.value = event.target.value;
  validateNickname(nickname.value)
}

// 회원가입 요청 submit
const submitSignup = () => {
  if (isValidateId.value && isValidatePassword.value && isValidateNickname.value && userId.value.trim() !== '') {
    console.log('submit')
    store.submitSignup(userId.value, password.value, nickname.value);
  } else {
    console.log('submit fail')
  }
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

.validateAlert {
  font-size: small;
  font-weight: 700;
  color: red;
}

#alert {
  position: absolute;
  top: 0;
  left: 50%;
  transform: translate(-50%, -150%);
  width: 85%;
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
