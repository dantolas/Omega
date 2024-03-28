import { createApp } from "vue";
import "./assets/index.css";
import App from "./App.vue";
import { createRouter, createWebHistory } from "vue-router";

const routes = [
  { path: "/", redirect: "/login" },
  { path: "/login", name:"Login", component: () => import('@/views/Login.vue')},
  { path: "/:pathMatch(.*)*", name:"NotFound", component: () => import('@/views/NotFound.vue') },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

var app = createApp(App);
app.use(router);
app.mount("#app");
