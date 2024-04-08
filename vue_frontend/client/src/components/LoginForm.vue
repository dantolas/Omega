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
import {onMounted, ref} from "vue"
import axios from 'axios';
import {useRouter,useRoute} from 'vue-router'
import {login} from "@/util/auth"
    
const router = useRouter();
    
const formSchema = toTypedSchema(z.object({
    login: z.string().min(2).max(50),
    password: z.string().min(4).max(50)
}))

const form = useForm({
  validationSchema: formSchema,
})

const onSubmit =  form.handleSubmit(async (values) => {
    console.log('Form submitted!', values)
    const response = await login(values.login,values.password);
    if(!response) {
        alert("Something went wrong:" + response.status);
        return
    }
    if(response.status ==="ok"){
        router.push("/home");
        return
    }
})
</script>

<template>
  <form @submit="onSubmit">
    <FormField v-slot="{ componentField }" name="login">
      <FormItem v-auto-animate>
        <FormLabel>Login</FormLabel>
        <FormControl>
          <Input type="text" placeholder="Email/Username" v-bind="componentField" />
        </FormControl>
        <FormDescription>
          Enter your username or email.
        </FormDescription>
        <FormMessage />
      </FormItem>
    </FormField>
    <FormField v-slot="{ componentField }" name="password">
      <FormItem v-auto-animate>
        <FormLabel>Password</FormLabel>
        <FormControl>
          <Input type="password" placeholder="********" v-bind="componentField" />
        </FormControl>
        <FormDescription>
                    Enter your password.
        </FormDescription>
        <FormMessage />
      </FormItem>
    </FormField>
    <Button type="submit" class="w-full">
      Login 
    </Button>
  </form>
</template>
