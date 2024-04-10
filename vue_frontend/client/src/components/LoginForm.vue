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
import {login} from "@/util/auth"
    
const formSchema = toTypedSchema(z.object({
    login: z.string().min(4).max(30),
    password: z.string().min(4).max(30)
}))

const form = useForm({
  validationSchema: formSchema,
})

const emit = defineEmits(['loginResponse']);

const onSubmit =  form.handleSubmit(async (values) => {
    const response = await login(values.login,values.password);
    if(!response || !response.status) {
        emit('loginResponse',"general_error");
        return
    }
    if(response.status ==="ok"){
        emit('loginResponse',"ok");
        return
    }
    if(response.status === "failed"){
        emit('loginResponse','invalid_credentials');
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
