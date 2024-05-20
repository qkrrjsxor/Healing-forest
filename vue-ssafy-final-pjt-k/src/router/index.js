import AddictionCreate from "@/components/addiction/AddictionCreate.vue";
import AddictionDetail from "@/components/addiction/AddictionDetail.vue";
import AddictionList from "@/components/addiction/AddictionList.vue";
import AddictionUpdate from "@/components/addiction/AddictionUpdate.vue";
import AddictionView from "@/views/AddictionView.vue";
import HomeView from "@/views/HomeView.vue";
import LoginView from "@/views/LoginView.vue";
import RankView from "@/views/RankView.vue";
import SignupView from "@/views/SignupView.vue";
import { createRouter, createWebHistory } from "vue-router";

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
          path: "create",
          name: "addictionCreate",
          component: AddictionCreate,
        },
        {
          path: ":id",
          name: "addictionDetail",
          component: AddictionDetail,
        },
        {
          path: "update/:id",
          name: "addictionUpdate",
          component: AddictionUpdate,
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
