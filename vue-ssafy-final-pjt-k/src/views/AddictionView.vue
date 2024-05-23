<template>
  <div id="container">
    <RouterView />
  </div>
</template>

<script setup></script>

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
