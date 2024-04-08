import { defineStore } from 'pinia';

export const useAuthStore = defineStore('authStore',{
    state: () => ({
        login:false,
    }),
    getters: {
        isLoggedIn:(state) =>{
            return state.loggedIn;
        }
    },
    actions: {
        setLoggedIn(){
            this.loggedIn = true
        },
        setLoggedOut(){
            this.loggedIn = false
        }
    }
});
