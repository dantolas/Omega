import { createApp} from "vue";
import "./assets/index.css";
import App from "./App.vue";
import { createRouter, createWebHistory,RouteRecordRaw } from "vue-router";
import {isAuthenticated} from "@/util/auth"

var app = createApp(App);

const routes:Readonly<RouteRecordRaw[]> = [
    { path: "/", redirect: "/login" },
    { path: "/login", name:"Login", component: () => import('@/views/Login.vue')},
    { path: "/api", name: "ApiDocs", component: () => import('@/views/Api.vue') },
    { path: "/about", name: "About", component: () => import('@/views/About.vue') },
    { path: "/home", name:"Home", component: () => import('@/views/Home.vue')},
    { path: "/:pathMatch(.*)*", name:"NotFound", component: () => import('@/views/NotFound.vue') },
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
});

const publicPages = [
    "ApiDocs",
    "About",

]
router.beforeEach(async (to,from) => {
    if(publicPages.includes(to)) {
        return to.name;
    }
    let logged:boolean = await isAuthenticated();
    if ( !logged && to.name !== 'Login') {
        return { name: 'Login' }
    }
    if(to.name === 'Login' && logged){
        console.log("Not working");
        return { name:"Home"}
    }
})
app.use(router);
app.mount("#app");

