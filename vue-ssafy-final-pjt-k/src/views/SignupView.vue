<template>
  <div id="container">
    <img
      v-if="showImage"
      id="login-image"
      src="@/assets/signup-login.jpg"
      alt="login-image"
    />
    <section id="login-section">
      <div id="login-form-box">
        <Alert id="alert" />
        <h2>Sign up</h2>
        <form id="login-form" @submit.prevent="submitSignup">
          <ul id="input-set">
            <li>
              <input
                type="text"
                placeholder="아이디를 입력해주세요."
                v-model="userId"
                @input="validateUserId"
                maxlength="20"
                autofocus
              />
              <p v-if="!isValidateId" class="validateAlert">
                아이디는 영어 소문자(최소 4자 이상)와 숫자로 이루어져야 합니다.
              </p>
            </li>
            <li>
              <div class="passwordInputBox">
                <input
                  :type="passwordFieldType"
                  placeholder="비밀번호를 입력해주세요."
                  v-model="password"
                  @input="validatePassword"
                  maxlength="30"
                  class="passwordInput"
                />
                <span @click="togglePasswordVisibility" class="password-toggle">
                  <img
                    :src="passwordToggleIcon"
                    alt="password-eye"
                    width="24px"
                  />
                </span>
              </div>
              <p v-if="!isValidatePassword" class="validateAlert">
                비밀번호는 8자 이상으로, 영문, 숫자, 특수문자를 모두 포함해야
                합니다.
              </p>
            </li>
            <li>
              <input
                type="text"
                placeholder="닉네임을 입력해주세요."
                :value="nickname"
                @input="setNickname"
                maxlength="15"
              />
              <p v-if="!isValidateNickname" class="validateAlert">
                닉네임은 2~15자로 설정해야 합니다.
              </p>
            </li>
          </ul>
          <div id="button-set">
            <button type="button" @click="goHome">홈으로</button>
            <button type="submit">회원가입</button>
          </div>
          <RouterLink :to="{ name: 'login' }">이미 회원이신가요?</RouterLink>
        </form>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref, watchEffect, computed } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user";
import Alert from "@/components/common/Alert.vue";

const showImage = ref(true);
const store = useUserStore();
const router = useRouter();

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

// 정규식 조건
const userIdPattern = /^(?=(?:[^a-z]*[a-z]){4})[a-z0-9]{4,20}$/; // 4~20 글자 사이, 적어도 4자 이상의 영어 소문자, 숫자 가능
const passwordPattern =
  /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/; //8글자 이상, 영대문자, 소문자, 숫자, 특수문자 포함
const nicknamePattern = /^.{2,}$/;

const userId = ref("");
const password = ref("");
const nickname = ref("");

const isValidateId = ref(true);
const isValidatePassword = ref(true);
const isValidateNickname = ref(true);

const validateUserId = () => {
  isValidateId.value = userIdPattern.test(userId.value) ? true : false;
};
const validatePassword = () => {
  isValidatePassword.value = passwordPattern.test(password.value)
    ? true
    : false;
};
const validateNickname = (nickname) => {
  isValidateNickname.value = nicknamePattern.test(nickname) ? true : false;
};

const setNickname = (event) => {
  nickname.value = event.target.value;
  validateNickname(nickname.value);
};

// 회원가입 요청 submit
const submitSignup = () => {
  if (
    isValidateId.value &&
    isValidatePassword.value &&
    isValidateNickname.value &&
    userId.value.trim() !== "" &&
    password.value.trim() !== "" &&
    nickname.value.trim() !== ""
  ) {
    console.log("submit");
    store.submitSignup(userId.value, password.value, nickname.value);
  } else {
    console.log("submit fail");
  }
};

// 패스워드 input 태그 type 변경 기능
const passwordFieldType = ref("password");

const togglePasswordVisibility = () => {
  passwordFieldType.value =
    passwordFieldType.value === "password" ? "text" : "password";
};

const passwordToggleIcon = computed(() => {
  return passwordFieldType.value === "password"
    ? new URL("@/assets/visibility.png", import.meta.url).href
    : new URL("@/assets/visibility_off.png", import.meta.url).href;
});
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
  position: relative;
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

@media (max-height: 768px) {
  #alert {
    transform: translate(-50%, -100%);
  }
}

.passwordInputBox {
  position: relative;
}

.passwordInput {
  padding-right: 50px;
}

.password-toggle {
  position: absolute;
  top: 50%;
  right: 20px;
  transform: translateY(-44%);
  cursor: pointer;
  /* font-size: large; */
  /* color: #5a5a5a;
  font-weight: 700; */
}
</style>
