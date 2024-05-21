<template>
  <div id="add-container">
    <div id="user-name">
      <picture id="profile-box">
        <img
          id="profile-img"
          src="@/assets/profile-icon.png"
          alt="profile-icon"
        />
      </picture>
      <p>ssafy</p>
    </div>
    <form id="user-comment" @submit.prevent="addComment">
      <input type="text" :value="content" @input="setContent" />
      <button type="submit">등록</button>
    </form>
  </div>
</template>

<script setup>
import { useCommunityStore } from "@/stores/community";
import { ref } from "vue";

const store = useCommunityStore();

const content = ref("");
const setContent = (e) => {
  content.value = e.target.value;
};

const addComment = () => {
  store.addComment(content.value);
};
</script>

<style scoped>
#add-container {
  display: flex;
  justify-content: space-between;
  align-items: center;

  /* background-color: aliceblue; */
  padding: 1rem;
}

#user-name {
  display: flex;
  align-items: center;
  gap: 0.7rem;

  p {
    margin: 0;
    color: #573f2c;
    font-size: medium;
    font-weight: 600;
  }
}

#profile-box {
  display: flex;

  width: 2rem;
  height: 2rem;

  border: 2px solid #573f2c;
  border-radius: 50%;
  overflow: hidden;
}

#profile-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

#user-comment {
  display: flex;
  gap: 2rem;
  width: 85%;

  input {
    width: 90%;
    padding: 0.5rem 1rem;

    border: 2px solid #573f2c;
    border-radius: 0.7rem;

    color: #352f26;
  }
  button {
    width: 10%;
    padding: 0.5rem 1rem;

    border: none;
    border-radius: 0.7rem;
    background-color: #573f2c;

    color: white;
    font-weight: 600;
    cursor: pointer;
  }
}

/* media query */
@media (max-width: 1124px) {
  #user-comment {
    width: 80%;
    gap: 1rem;

    input {
      width: 85%;
    }

    button {
      width: 15%;
    }
  }
}
@media (max-width: 778px) {
  #add-container {
    flex-direction: column;
    align-items: flex-start;
    gap: 1rem;
  }

  #profile-box {
    width: 1.5rem;
    height: 1.5rem;
  }

  #user-comment {
    width: 100%;
    gap: 0.5rem;

    input {
      width: 75%;
    }

    button {
      width: 25%;
      font-size: small;
    }
  }
}
</style>
