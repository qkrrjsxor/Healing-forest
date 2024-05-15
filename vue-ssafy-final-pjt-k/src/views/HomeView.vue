<template>
  <main id="background-image">
    <RouterLink to="/" id="home">Healing Forest</RouterLink>
    <a @click="navigate" id="login">여정을 시작하시겠습니까?</a>
  </main>
</template>

<script setup>
import { useRouter } from "vue-router";
const router = useRouter();

const navigate = () => {
  if (sessionStorage.getItem("loginUser") != null) {
    router.push({ name: "addictionList" });
  } else {
    router.push({ name: "login" });
  }
};
</script>

<style>
#background-image {
  position: relative;

  display: flex;
  flex-direction: column;

  height: 100vh;
  width: 100vw;

  background-image: url("@/assets/home.png");
  background-size: cover;
  background-position: center;
}

#home {
  position: absolute;
  right: 30px;
  top: 30px;

  color: #fdf876;
  font-weight: 800;
  font-size: xx-large;
}

#login {
  position: absolute;
  /* top: 18rem; */
  bottom: 6rem;
  left: 50%;
  transform: translateX(-50%);

  padding: 1rem 7rem;
  min-width: 25%;

  background-color: #f6e8da;
  border-radius: 50px;

  color: #352f26;
  text-align: center;
  font-weight: 800;
  font-size: large;
}

/* media query */
@media (max-width: 1024px) {
  #home {
    font-size: x-large;
  }

  #login {
    font-size: medium;
  }
}

@media (max-width: 768px) {
  #login {
    font-size: small;
    padding: 1rem 3rem;
  }
}
</style>
