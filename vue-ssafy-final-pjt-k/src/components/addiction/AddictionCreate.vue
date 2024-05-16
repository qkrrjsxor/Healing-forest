<template>
  <div class="addiction-form">
    <div>
      <form @submit.prevent="submitAddiction" id="addiction-form">
        <div id="input-set">
          <h2>어떤 중독을 끊고 싶으신가요?</h2>
          <input type="text" v-model="addiction.title" placeholder="중독 입력" />
      
          <h2>목표 일 수를 입력해주세요.</h2>
          <input type="number" v-model="addiction.targetTime" placeholder="1 ~ 100 까지의 숫자를 입력할 수 있습니다." min="1" max="100"/>
      
          <h2>아이콘을 선택해주세요</h2>
          <select v-model="addiction.iconPath">
            <option disabled value="">아이콘을 선택해주세요</option>
            <option v-for="icon in icons" :key="icon" :value="icon">{{ icon }}</option>
          </select>
        </div>
    
        <div id="button-set">
          <button type="button" @click="goList">목록으로</button>
          <button type="submit">시작</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { useAddictionStore } from "@/stores/addiction";
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const store = useAddictionStore();

const addiction = ref({
  title: "",
  targetTime: "",
  iconPath: "",
})

const icons = ref(['Icon1', 'Icon2', 'Icon3']); // Replace with actual icons


// 목록으로
const goList = () => {
  router.push({ name: "addictionList" });
};

// submit
const submitAddiction = () => {
  // console.log('test')
  // console.log(addiction)
  store.submitAddiction(addiction.value);
};
</script>


<style scoped>
.addiction-form {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  width: 50%;
  min-width: 400px;
  padding: 2rem;
}


#input-set {
  display: flex;
  flex-direction: column;
  
}

input, select {
  width: 100%;
  padding: 1rem 1.5rem;
  border: 3px solid #B6C2A9;
  border-radius: 20px;
  box-sizing: border-box;

  color: #352f26;
  font-size: medium;
}

h2 {
  font-family: "Nanum Gothic", sans-serif;
  color: #384427;
  margin-top: 50px;
}

#button-set {
  display: flex;
  justify-content: flex-end;
  gap: 1rem;

  width: 60%;
  margin: 10px auto;
}

button {
  flex: 1;
  padding: 0.5rem 1rem;
  font-weight: 700;
  font-size: medium;
  cursor: pointer;
  width: 100px;
  color: white;
  border: #B6C2A9 solid;
  border-radius: 100px;
  margin: 30px 10px;
}

button:first-child {
  border: 3px solid #B6C2A9;
  background-color: transparent;
  color: #B6C2A9;
}

button:last-child {
  border: none;
  background-color: #B6C2A9;
  color: #ffffff;
}
</style>
