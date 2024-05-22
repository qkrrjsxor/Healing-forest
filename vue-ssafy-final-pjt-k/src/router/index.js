import AddictionCreate from "@/components/addiction/AddictionCreate.vue";
import AddictionDetail from "@/components/addiction/AddictionDetail.vue";
import AddictionList from "@/components/addiction/AddictionList.vue";
import AddictionUpdate from "@/components/addiction/AddictionUpdate.vue";
import AddictionView from "@/views/AddictionView.vue";
import HomeView from "@/views/HomeView.vue";
import LoginView from "@/views/LoginView.vue";
import SignupView from "@/views/SignupView.vue";
import { createRouter, createWebHistory } from "vue-router";
import CommView from "@/views/CommView.vue";

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
      meta: { requiresAuth: true },
    },
    {
      path: "/community",
      name: "community",
      component: CommView,
      meta: { requiresAuth: true },
    },
  ],
});

router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some((record) => record.meta.requiresAuth);
  const loginUser = sessionStorage.getItem("loginUser");

  if (requiresAuth && !loginUser) { // 로그인 되어있지 않은 상태로 addiction, community 접속 할 때 -> alert 후 로그인 페이지로
    alert("로그인 후 이용 가능합니다.");
    next({ name: "login" });
  } else if ((to.name === "login" || to.name === "signup") && loginUser) {  // 로그인 되어있는 상태로 login, signup 접속 할 때 -> home으로
    next({ name: "home" });
  } else {  
    next();
  }
});

export default router;
