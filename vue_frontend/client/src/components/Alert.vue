<script setup lang="ts">
import { Alert, AlertDescription, AlertTitle } from '@/components/ui/alert'
const props = defineProps({
        alertTitle:String,
        alertDesc:String,
    })

</script>

<template>
  <Alert>
        <AlertTitle>{{props.alertTitle}}</AlertTitle>
    <AlertDescription>
            {{props.alertDesc}}
    </AlertDescription>
  </Alert>
</template>
