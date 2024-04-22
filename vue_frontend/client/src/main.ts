import { createApp} from "vue";
import "./assets/index.css";
import App from "./App.vue";
import { createRouter, createWebHistory,RouteRecordRaw } from "vue-router";
import {isAuthenticated} from "@/util/auth"

var app = createApp(App);

const routes:Readonly<RouteRecordRaw[]> = [
    { path: "/", redirect: "/login" },
    { path: "/login", name:"Login", component: () => import('@/views/Login.vue')},
    { path: "/home", name:"Home", component: () => import('@/views/Home.vue')},
    { path: "/:pathMatch(.*)*", name:"NotFound", component: () => import('@/views/NotFound.vue') },
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
});

const publicPages = [
    "api"
]
router.beforeEach(async (to,) => {
    if(publicPages.includes(to.toString())) return to;
    let logged:boolean = await isAuthenticated();
    if(to.name === 'Login' && logged){
        return { name:"Home"}
    }
    if ( !logged && to.name !== 'Login') {
        // redirect the user to the login page
        return { name: 'Login' }
    }
})
app.use(router);
app.mount("#app");

