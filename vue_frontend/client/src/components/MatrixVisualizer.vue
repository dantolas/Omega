<script setup lang="ts">
import { Alert, AlertDescription, AlertTitle } from '@/components/ui/alert'
import { AlertCircle } from 'lucide-vue-next'
import {
    Table,
    TableBody,
    TableCaption,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
} from '@/components/ui/table'
import {
    HoverCard,
    HoverCardContent,
    HoverCardTrigger,
} from '@/components/ui/hover-card'
import { ToggleGroup, ToggleGroupItem } from '@/components/ui/toggle-group'
import { Toggle } from '@/components/ui/toggle'
import { Slider } from '@/components/ui/slider'
import {ref} from 'vue'
import { Input } from '@/components/ui/input'
import { Button } from '@/components/ui/button'

const props = defineProps({

});
</script>

<template>
    <div class="w-full relative p-2 flex flex-row rounded border">
        <div id="tablewrapper" class="w-3/4">
            <transition>
                <Table>
                    <TableCaption>{{rows}} x {{cols}}</TableCaption>
                    <TableHeader>
                        <TableRow class="">
                            <TableHead/>
                            <TableHead >
                            </TableHead>
                        </TableRow>
                    </TableHeader>
                    <TableBody>
                        <TransitionGroup>
                            <TableRow >
                                <TableCell class="font-bold pl-0 text-lg">
                                </TableCell>
                                <transition-group>
                                    <TableCell   
                                    >
                                    </TableCell>
                                </transition-group>
                            </TableRow>
                        </TransitionGroup>
                    </TableBody>
                </Table>   
            </transition>
        </div>
    </div>
</template>

<style>
#app,main{
    width:100%;
}
 .v-enter-active,
.v-leave-active {
  transition: opacity 0.3s ease-in;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
}
</style>
