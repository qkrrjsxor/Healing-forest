<template>
  <li id="comment-item" :class="{ borderBottom: index + 1 !== total }">
    <div id="user-name">
      <picture id="profile-box">
        <img
          id="profile-img"
          src="@/assets/profile-icon.png"
          alt="profile-icon"
        />
      </picture>
      <p>{{ comment.userId }}</p>
    </div>
    <div id="user-comment">
      <div id="comment-div">
        <p>
          {{ comment.content }}
        </p>
        <p>1분 전</p>
      </div>
      <div id="button-div">
        <button>수정</button> |
        <button>삭제</button>
      </div>
    </div>
  </li>
</template>

<script setup>
const props = defineProps({
  comment: Object,
  index: Number,
  total: Number,
});
</script>

<style scoped>
#comment-item {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  gap: 1rem;
  padding: 1rem 2rem;
}

#user-name {
  display: flex;
  align-items: center;
  gap: 0.5rem;

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
  align-items: flex-start;
  justify-content: space-between;

  width: 85%;
}

#comment-div {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;

  width: 85%;

  p {
    margin: 0;
  }
}

#comment-div p:first-child {
  color: #352f26;
  font-size: medium;
  word-wrap: break-word;
  overflow-wrap: break-word;
}

#comment-div p:last-child {
  color: #b9b7b5;
  font-size: small;
}

#button-div {
  display: flex;
  color: #b9b7b5;

  button {
    width: 3rem;
    border: none;
    background-color: transparent;
    color: #b9b7b5;
    font-weight: 600;
    cursor: pointer;
  }
}

.borderBottom {
  /* border-bottom: 1px solid #eaeceb; */
  border-bottom: 1px solid #b6c2a9;
}

/* media query */
@media (max-width: 1124px) {
  #comment-item {
    flex-direction: column;
    gap: 0.5rem;
  }

  #user-comment {
    display: flex;
    width: 100%;
  }
}

@media (max-width: 1024px) {
  #comment-div {
    width: 80%;
  }
}

@media (max-width: 778px) {
  #profile-box {
    width: 1.5rem;
    height: 1.5rem;
  }

  #comment-item {
    padding: 0.5rem 0;
  }

  #user-comment {
    flex-direction: column;
  }

  #comment-div {
    width: 100%;
  }

  #button-div {
    align-self: flex-end;
  }
}
</style>
