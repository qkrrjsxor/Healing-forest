<template>
  <div>
    <div id="header-nav">
      <img @click="goHome" id="logo" src="@/assets/logo.png" alt="logo" />
      <div id="nav-menu">
        <RouterLink
          :class="{ active: isActiveList }"
          :to="{ name: 'addictionList' }"
          >LIST</RouterLink
        >
        <RouterLink
          :class="{ active: isActiveComm }"
          :to="{ name: 'community' }"
          >COMM</RouterLink
        >
      </div>
      <picture @click="submitLogout" id="profile-box">
        <img
          id="profile-img"
          src="@/assets/auth/profile-icon.png"
          alt="profile"
        />
      </picture>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { computed } from "vue";
import { useRoute, useRouter } from "vue-router";

const router = useRouter();
const route = useRoute();
const store = useUserStore();

// 활성화 탭 처리
const isActiveList = computed(() => {
  return route.name?.includes("addiction");
});
const isActiveComm = computed(() => {
  return route.name?.includes("community");
});

// 홈으로
const goHome = () => {
  router.push({ name: "home" });
};

// 로그아웃
const submitLogout = () => {
  store.submitLogout();
};
</script>

<style scoped>
#header-nav {
  display: flex;
  justify-content: space-between;
  align-items: center;

  background-color: #b3b381;
  height: 3.5rem;
}

#logo {
  width: 28px;
  height: 30px;
  margin-left: 1.5rem;
  cursor: pointer;
}

#nav-menu {
  display: flex;
  gap: 3rem;
  a {
    font-size: large;
    font-weight: 800;
  }
}

.active {
  color: #384427;
  font-size: small;
}

#profile-box {
  width: 30px;
  height: 30px;

  margin-right: 1.5rem;
  padding: 0.2rem;

  border-radius: 50%;
  background-color: #f6e8da;
  overflow: hidden;
  cursor: pointer;
}

#profile-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
