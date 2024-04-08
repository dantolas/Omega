<script setup lang="ts">
import { useForm } from 'vee-validate'
import { toTypedSchema } from '@vee-validate/zod'
import * as z from 'zod'

import { Button } from '@/components/ui/button'
import {
  FormControl,
  FormDescription,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from '@/components/ui/form'
import { Input } from '@/components/ui/input'
import { vAutoAnimate } from '@formkit/auto-animate/vue'
import { signup } from '@/util/auth'
import {useRouter,useRoute} from 'vue-router'
import axios from 'axios';

const router = useRouter();
const formSchema = toTypedSchema(
    z.object({
        login: z.string().min(2).max(50),
        email: z.string().email(),
        password: z.string().min(8).max(50),
        retype_password:z.string().min(8).max(50)
    }).refine((values) => values.password === values.retype_password,{
            message:"Passwords don't match",
            path:["retype_password"]
        }))

const form = useForm({
  validationSchema: formSchema,
})

const onSubmit = form.handleSubmit(async (values) => {
    const response = await signup(values.login,values.email,values.password);
    console.log(response);
    if(!response.status){
        alert("Somethign went wrong");
    }
    if(response.status === "ok"){
        router.push("/home");
    }
    if(response.status === "failed"){
        const error = response.error;
        if(error ==="email_taken"){
            alert("Email already taken")
        }
    }
})
</script>

<template>
  <form @submit="onSubmit">
    <FormField v-slot="{ componentField }" name="login">
      <FormItem v-auto-animate>
        <FormLabel>Username</FormLabel>
        <FormControl>
          <Input type="text" placeholder="John_doe" v-bind="componentField" />
        </FormControl>
        <FormDescription>
          Enter your username.
        </FormDescription>
        <FormMessage />
      </FormItem>
    </FormField>
    <FormField v-slot="{ componentField }" name="email">
      <FormItem v-auto-animate>
        <FormLabel>Email</FormLabel>
        <FormControl>
          <Input type="email" placeholder="john.doe@email.com" v-bind="componentField" />
        </FormControl>
        <FormDescription>
                    Enter your email.
        </FormDescription>
        <FormMessage />
      </FormItem>
    </FormField>
    <FormField v-slot="{ componentField }" name="password">
      <FormItem v-auto-animate>
        <FormLabel>Password</FormLabel>
        <FormControl>
          <Input type="password" placeholder="*****" v-bind="componentField" />
        </FormControl>
        <FormDescription>
                    Enter your password.
        </FormDescription>
        <FormMessage/>
      </FormItem>
    </FormField>
    <FormField v-slot="{ componentField }" name="retype_password">
      <FormItem v-auto-animate>
        <FormLabel>Retype Password</FormLabel>
        <FormControl>
          <Input type="password" placeholder="*****" v-bind="componentField" />
        </FormControl>
        <FormDescription>
                    Enter your password again.
        </FormDescription>
        <FormMessage />
      </FormItem>
    </FormField>
    <Button type="submit" class="w-full">
            Signup
    </Button>
  </form>
</template>
