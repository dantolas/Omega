<script setup lang="ts">
import { vAutoAnimate } from '@formkit/auto-animate/vue'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs'
import { Alert, AlertDescription, AlertTitle } from '@/components/ui/alert'
import { AlertCircle } from 'lucide-vue-next'
import {ref} from "vue"
import LoginForm from '@/components/LoginForm.vue'
import RegisterForm from '@/components/RegisterForm.vue'
import {useRouter,useRoute} from 'vue-router'

const alertTitle = ref("Some value");
const alertDescription = ref("Default alert description telling u tu muck of");
const showAlert = ref(false);
const alertVariant = ref("");


const router = useRouter();
const handleResponse = (response:String):void =>{
    if(response === "invalid_credentials"){
        alertTitle.value = "Invalid login credentials";
        alertDescription.value = "Make sure your login and password are correct";
        showAlert.value = true;
        return;
    }
    if(response === "general_error"){
        alertTitle.value = "Error";
        alertDescription.value = "A general error has occured. Ensure you are connected to the"+
        " internet, and try again later if the problem persists";
        showAlert.value = true;
        return;
    }
    showAlert.value = false;
    router.push("/home");
    return;
}
</script>

<template>
    <div class="flex flex-col items-center justify-center p-3 h-screen mx-auto">
        <Transition>
        <Alert class="w-1/2" variant="destructive" v-show="showAlert">
            <AlertCircle class="w-4 h-4" />
            <AlertTitle >{{alertTitle}}</AlertTitle>
            <AlertDescription>
                {{alertDescription}}
            </AlertDescription>
        </Alert>
        </Transition>
        <div id="form-wrapper" class="h-1/2 p-3">
            <Tabs default-value="login" class="w-[400px] ">
                <TabsList class="relative flex p-2" >
                    <TabsTrigger value="login" class="w-1/2 rounded transition ease-in-out delat-150" >
                        login
                    </TabsTrigger>
                    <TabsTrigger value="signup" class="w-1/2 rounded">
                        signup
                    </TabsTrigger>
                </TabsList>
                <TabsContent value="login" >
                    <Card class="m-auto p-2 ">
                        <CardHeader>
                            <CardTitle class="text-2xl">
                                Login
                            </CardTitle>
                            <CardDescription>
                                Enter your your credentials to log in to your account.
                            </CardDescription>
                        </CardHeader>
                        <CardContent class="grid gap-4" >
                            <LoginForm @loginResponse="handleResponse" />
                        </CardContent>
                        <CardFooter>
                            <CardDescription>Enjoy your stay :]</CardDescription>
                        </CardFooter>
                    </Card>
                </TabsContent>
                <TabsContent value="signup">
                    <Card class="m-auto p-2">
                        <CardHeader>
                            <CardTitle class="text-2xl">
                                Signup
                            </CardTitle>
                            <CardDescription>
                                Create an account.
                            </CardDescription>
                        </CardHeader>
                        <CardContent class="grid gap-4">
                            <RegisterForm/>
                        </CardContent>
                        <CardFooter>
                            <CardDescription>Enjoy your stay :]</CardDescription>
                        </CardFooter>
                    </Card>
                </TabsContent>

            </Tabs>
        </div>
    </div>
</template>

<style >
 .v-enter-active,
.v-leave-active {
  transition: opacity 0.5s ease;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
}
</style>
