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
      <picture @click="toggleProfileModal" id="profile-box">
        <img
          id="profile-img"
          src="@/assets/auth/profile-icon.png"
          alt="profile"
        />
      </picture>
      <ProfileModal
        v-show="showProfileModal"
        id="profile"
        class="profile-modal"
        @close="toggleProfileModal"
      />
    </div>
  </div>
</template>

<script setup>
import { useModalStore } from "@/stores/modal";
import ProfileModal from "@/components/common/ProfileModal.vue";
import { ref, computed, watch } from "vue";
import { useRoute, useRouter } from "vue-router";

const router = useRouter();
const route = useRoute();

const modalStore = useModalStore();

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

// 프로필 모달
const showProfileModal = ref(false);

const toggleProfileModal = () => {
  showProfileModal.value = !showProfileModal.value;
};

const openModal = (id) => {
  modalStore.showModal(id, "profile");
  setTimeout(() => {
    showProfileModal.value = false; // 3초 후에는 자동으로 닫히도록
  }, 3000);
};
const closeModal = (id) => {
  modalStore.closeModal(id);
};

watch(showProfileModal, (newValue) => {
  if (newValue) {
    openModal("profile");
  } else {
    closeModal("profile");
  }
});
</script>

<style scoped>
.profile-modal {
  position: absolute;
  top: 5rem;
  right: 2rem;
}

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

@media (max-width: 768px) {
  .profile-modal {
    position: absolute;
    right: 1rem;
  }
}
</style>
