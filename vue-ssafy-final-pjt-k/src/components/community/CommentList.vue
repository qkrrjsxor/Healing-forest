<template>
  <ul id="comment-list">
    <CommentListItem
      v-for="(comment, index) in store.allComment"
      :key="comment.commentId"
      :comment="comment"
      :index="index"
      :total="store.allComment.length"
      :loginUser="props.loginUser"
    />
  </ul>
</template>

<script setup>
import { useCommunityStore } from "@/stores/community";
import { onMounted } from "vue";
import CommentListItem from "./CommentListItem.vue";

const store = useCommunityStore();

const props = defineProps({
  loginUser: Object,
});

onMounted(async () => {
  await store.getAllComment();
});
</script>

<style scoped>
#comment-list {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  padding: 1rem;
}
</style>
