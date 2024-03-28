<script setup lang="ts">
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs'
</script>

<template>
    <div class="flex items-center justify-center p-3 h-screen mx-auto">
        <div id="form-wrapper" class="h-1/2 p-3">
            <Tabs default-value="login" class="w-[400px] ">
                <TabsList class="relative flex p-2">
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
                            <div class="grid gap-2">
                                <Label for="email">Email/Username</Label>
                                <Input id="email" type="email" placeholder="user@mail.com" required />
                            </div>
                            <div class="grid gap-2">
                                <Label for="password">Password</Label>
                                <Input id="password" type="password" placeholder="****" />
                            </div>
                        </CardContent>
                        <CardFooter>
                            <Button class="w-full">
                                Log in
                            </Button>
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
                            <div class="grid gap-2">
                                <Label for="username">Username</Label>
                                <Input id="username" type="username" placeholder="Hugh Jazs" required />
                            </div>
                            <div class="grid gap-2">
                                <Label for="email">Email</Label>
                                <Input id="email" type="email" placeholder="user@email.com" required />
                            </div>
                            <div class="grid gap-2">
                                <Label for="password">Password</Label>
                                <Input id="password" type="password"placeholder="****"  required />
                            </div>
                            <div class="grid gap-2">
                                <Label for="confirm_password">Confirm password</Label>
                                <Input id="confirm_password" type="password"placeholder="****"  required />
                            </div>
                        </CardContent>
                        <CardFooter>
                            <Button class="w-full">
                                Signup
                            </Button>
                        </CardFooter>
                    </Card>
                </TabsContent>

            </Tabs>
        </div>
    </div>
</template>

<style >

input[type="radio"]{
    display: none;
}
.slide-controls{

    display: flex;
    height: 50px;
    width: 100%;
    margin: 30px 0 10px 0;
    justify-content: space-between;
    border-radius: 5px;
    overflow: hidden;
    position: relative;
}
#signup:checked ~ .slide-tab{
    left: 50%;
}

#signup:checked ~ .signup{
    color: #79e7c3;
}


#signup:checked ~ .login{
    color: white;
}
.slide-controls .slide-tab{
    position: absolute;
    height: 100%;
    width: 50%;
    left: 0;
    z-index: 0;
    background-color: #202c55;
    border-radius: 5px;

    transition: width 0.2s ease;
}
.slide-controls .signup{
    color: #f2f2f2;
}
.slide-controls .slide{
    height: 100%;
    width: 100%;
    font-size: 18px;
    font-weight: 550;
    text-align: center;
    line-height: 47px;
    cursor: pointer;
    z-index: 1;
    color: #79e7c3;
    transition: all 0.6s ease;
}
</style>
