<template>
  <li id="comment-item" :class="{ borderBottom: index + 1 !== total }">
    <div id="user-name">
      <picture id="profile-box">
        <img
          id="profile-img"
          src="@/assets/auth/profile-icon.png"
          alt="profile-icon"
        />
      </picture>
      <p>{{ comment.nickname }}</p>
    </div>
    <div id="user-comment">
      <div id="comment-div">
        <p v-if="isOriginalComment">
          {{ comment.content }}
        </p>
        <input
          v-else
          v-model="editedComment"
          @keyup.enter="showEditDiv"
          maxlength="100"
          ref="updateInputRef"
        />
        <p>{{ timeSincePosted }}</p>
      </div>
      <div id="button-div" v-if="props.loginUser.userId === comment.userId">
        <button @click="showEditDiv">
          {{ isOriginalComment ? "수정" : "완료" }}
        </button>
        |
        <button @click="openModal(`deleteComment/${comment.commentId}`)">
          삭제
        </button>
        <DeleteModal
          :id="`deleteComment/${comment.commentId}`"
          modalType="comment"
          @confirm="deleteComment"
        >
          <template #content>
            <div class="deleteModal">
              <p>해당 댓글을 정말 삭제하시겠습니까?</p>
            </div>
          </template>
        </DeleteModal>
      </div>
    </div>
  </li>
</template>

<script setup>
import DeleteModal from "@/components/common/DeleteModal.vue";
import { useCommunityStore } from "@/stores/community";
import { computed, nextTick, onMounted, onUnmounted, ref } from "vue";
import { useModalStore } from "@/stores/modal";

const props = defineProps({
  comment: Object,
  index: Number,
  total: Number,
  loginUser: Object,
});

const store = useCommunityStore();
const modalStore = useModalStore();

const isOriginalComment = ref(true);
const editedComment = ref(props.comment.content);
const updateInputRef = ref(null);

// 댓글 수정
const showEditDiv = async () => {
  // 편집 모드 일 때만 댓글을 수정하도록
  if (!isOriginalComment.value) {
    const trimmedComment = editedComment.value.trim();
    if (trimmedComment !== "") {
      await store.updateComment(props.comment.commentId, trimmedComment);
      props.comment.content = trimmedComment;
    } else {
      editedComment.value = props.comment.content; // 빈 값으로 수정한 경우, 기존 값 그대로 유지
    }
    isOriginalComment.value = !isOriginalComment.value;
  } else {
    // 편집 모드로 들어갈 때, autofocus
    isOriginalComment.value = !isOriginalComment.value;

    await nextTick(() => {
      if (updateInputRef.value) {
        updateInputRef.value.focus();
      }
    });
  }
};

// 현재 시간
const now = ref(new Date());

const updateInterval = 60000; // 1분 마다 업데이트

// comment의 createDate와 현재 시간과의 차이 계산
const timeSincePosted = computed(() => {
  const seconds = Math.floor(
    (now.value - new Date(props.comment.createDate)) / 1000
  );
  const minutes = Math.floor(seconds / 60);
  if (minutes < 1) {
    return "방금 전";
  } else if (minutes < 60) {
    return `${minutes}분 전`;
  } else {
    const hours = Math.floor(minutes / 60);
    if (hours < 24) {
      return `${hours}시간 전`;
    } else {
      const days = Math.floor(hours / 24);
      return `${days}일 전`;
    }
  }
});

// 삭제 모달
const openModal = (id) => {
  const commentId = props.comment.commentId;
  modalStore.showModal(id, commentId);
};
const deleteComment = async () => {
  const commentId = props.comment.commentId;
  await store.deleteComment(commentId);
  modalStore.closeModal(`deleteComment/${commentId}`);
};

onMounted(() => {
  setInterval(() => {
    now.value = new Date();
  }, updateInterval);
});

onUnmounted(() => {
  clearInterval(updateInterval);
});
</script>

<style scoped>
.deleteModal {
  p {
    margin-top: 0;
    color: #f6e8da;
    font-weight: 700;
    font-size: medium;
  }
}

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
    width: 10rem;
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

  width: 70%;
}

#comment-div {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;

  width: 85%;

  p {
    margin: 0;
  }

  input {
    border: none;
    border-radius: 0.5rem;
    padding: 0.5rem 1rem;
    background-color: #eee8aa;
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
@media (max-width: 1370px) {
  #user-name {
    p {
      width: 8rem;
    }
  }

  #user-comment {
    width: 75%;
  }
}
@media (max-width: 1124px) {
  #comment-item {
    flex-direction: column;
    gap: 0.5rem;
  }

  #user-name {
    p {
      width: auto;
    }
  }

  #user-comment {
    position: relative;
    display: flex;
    width: 100%;
  }

  #comment-div {
    width: 100%;
  }

  #button-div {
    position: absolute;
    bottom: 0;
    right: 0;
  }
}

@media (max-width: 778px) {
  #profile-box {
    width: 1.5rem;
    height: 1.5rem;
  }

  #comment-item {
    padding: 1rem 0;
  }

  #comment-div {
    p {
      font-size: small;
    }
  }
}

@media (max-width: 468px) {
  .deleteModal {
    p {
      font-size: small;
    }
  }
}
</style>
