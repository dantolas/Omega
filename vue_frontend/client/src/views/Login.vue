<script setup lang="ts">
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

const something = ref(false);
</script>

<template>
    <div class="flex flex-col items-center justify-center p-3 h-screen mx-auto">
        <Alert class="w-1/2" variant="destructive" v-if="something">
            <AlertCircle class="w-4 h-4" />
            <AlertTitle >Placeholder</AlertTitle>
            <AlertDescription>
                Some error has clearly occured
            </AlertDescription>
        </Alert>
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
                <TabsContent value="login">
                    <Card class="m-auto p-2 ">
                        <CardHeader>
                            <CardTitle class="text-2xl">
                                Login
                            </CardTitle>
                            <CardDescription>
                                Enter your your credentials to log in to your account.
                            </CardDescription>
                        </CardHeader>
                        <CardContent class="grid gap-4">
                            <LoginForm/>
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
</style>
