<template>
  <ul id="comment-list">
    <CommentListItem />
    <CommentListItem />
    <CommentListItem />
  </ul>
</template>

<script setup>
import CommentListItem from "./CommentListItem.vue";
</script>

<style scoped>
#comment-list {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;

  /* background-color: aquamarine; */
  padding: 1rem;
}
</style>
