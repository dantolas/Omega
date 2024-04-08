<script setup lang="ts">
import Form from "@/components/Form.vue"
import ThemeChanger from "@/components/ThemeChanger.vue"
import Alert from "@/components/Alert.vue"
import Button from "@/components/Button.vue"
import {ref} from "vue"
import {piniaStore} from "@/store/piniaStore"

</script>

<template>
    <main>
        <router-view/>
    </main>
    <ThemeChanger
    />    
</template>

<style >
</style>
