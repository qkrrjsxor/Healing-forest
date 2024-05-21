<template>
  <ul id="comment-list">
    <CommentListItem
      v-for="(comment, index) in allComment"
      :key="comment.commentId"
      :comment="comment"
      :index="index"
      :total="allComment.length"
    />
  </ul>
</template>

<script setup>
import { ref, onMounted } from "vue";
import CommentListItem from "./CommentListItem.vue";
import { useCommunityStore } from "@/stores/community";

const store = useCommunityStore();

const allComment = ref([]);

onMounted(async () => {
  allComment.value = await store.getAllComment();
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
