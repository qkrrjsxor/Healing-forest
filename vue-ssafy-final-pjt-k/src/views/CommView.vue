<template>
  <div id="container">
    <Rank />
    <Comment />
  </div>
</template>

<script setup>
import Comment from "@/components/community/Comment.vue";
import Rank from "@/components/community/Rank.vue";
</script>

<style scoped>
#container {
  margin: 0 auto;
  background-color: #eaeceb;

  overflow: scroll;
}

/* 스크롤바 제거 */
#container::-webkit-scrollbar {
  display: none; /* Chrome, Edge, and Safari */
}
#container {
  scrollbar-width: none; /* Firefox */
}
#container {
  -ms-overflow-style: none; /* IE11 */
}
</style>
