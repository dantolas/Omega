import { createApp ,Vue} from "vue";
import "./assets/index.css";
import App from "./App.vue";
import { createRouter, createWebHistory } from "vue-router";
import {createPinia} from "pinia";
import VueCookies from 'vue-cookies'
import {isAuthenticated} from "@/util/auth"

var app = createApp(App);

const pinia = createPinia();
app.use(pinia);

const routes = [
    { path: "/", redirect: "/login" },
    { path: "/login", name:"Login", component: () => import('@/views/Login.vue')},
    { path: "/home", name:"Home", component: () => import('@/views/Home.vue')},
    { path: "/:pathMatch(.*)*", name:"NotFound", component: () => import('@/views/NotFound.vue') },
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
});
import {useAuthStore} from "@/store/authStore"

const publicPages = [
    "api"
]
router.beforeEach(async (to, from) => {
    if(publicPages.includes(to)) return to;
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
app.use(VueCookies,{expires:"35min"});
app.mount("#app");

