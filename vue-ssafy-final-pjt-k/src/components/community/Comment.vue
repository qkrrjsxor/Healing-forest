<template>
  <div id="comment-container">
    <div id="comment-box">
      <CommentAdd />
      <div id="line" />
      <CommentList />
    </div>
  </div>
</template>

<script setup>
import CommentAdd from "./CommentAdd.vue";
import CommentList from "./CommentList.vue";
</script>

<style scoped>
#comment-container {
  display: flex;
  flex-direction: column;
  align-items: center;

  max-width: 1280px;
  margin: 0 auto;
  padding: 2rem;
  padding-bottom: 5rem;

  /* background-color: aqua; */
}

#comment-box {
  display: flex;
  flex-direction: column;

  width: 80%;
  padding: 2rem;

  border-radius: 1rem;
  background-color: #fefffe;
  background-color: beige;
}

#line {
  /* border: 1px solid #b9b7b5; */
  border: 1px solid #7a8579;
  margin-top: 2rem;
  margin-bottom: 1rem;
}
</style>
