import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import LoginView from "@/views/LoginView.vue";
import SignupView from "@/views/SignupView.vue";
import AddictionView from "@/views/AddictionView.vue";
import RankView from "@/views/RankView.vue";
import AddictionList from "@/components/addiction/AddictionList.vue";
import AddictionCreate from "@/components/addiction/AddictionCreate.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
      meta: { hideHeader: true },
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
      meta: { hideHeader: true },
    },
    {
      path: "/signup",
      name: "signup",
      component: SignupView,
      meta: { hideHeader: true },
    },
    {
      path: "/addiction",
      name: "addiction",
      component: AddictionView,
      children: [
        {
          path: "",
          name: "addictionList",
          component: AddictionList,
        },
        {
          path: "addictionCreate",
          name: "addictionCreate",
          component:AddictionCreate,
        },
      ],
    },
    {
      path: "/rank",
      name: "rank",
      component: RankView,
    },
  ],
});

export default router;
