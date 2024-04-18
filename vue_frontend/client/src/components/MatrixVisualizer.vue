<script setup lang="ts">
import { Label } from '@/components/ui/label'
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
import { Separator } from '@/components/ui/separator'
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
import {
  Tooltip,
  TooltipContent,
  TooltipProvider,
  TooltipTrigger
} from '@/components/ui/tooltip'
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from '@/components/ui/popover'
defineOptions({ inheritAttrs: false })
const props = defineProps({
    matrix:{
        grid:[[]],
        startX:Number,
        startY:Number,
        endX:Number,
        endY:Number
    }
});
const rows = ref(props.matrix.grid.length);
const cols = ref(props.matrix.grid[0].length);
const getId = (symbol:String):String =>{
    switch (symbol) {
        case "#":
            return "hash";
            break;

        case ".":
            return "path";
            break;
        case "S":
            return "start";
            break;
        case "E":
            return "end";
            break;
        case "S/E":
            return "startend"
            break
        default:
            return "hash";
            break;
    }
};

const colors = ref({
    block:{
        bg:"#000",
        fg:"#fff"
    },
    path:{
        bg:"#000",
        fg:"#fff"
    },
    stop:{
        bg:"#000",
        fg:"#fff"
    },
    start:{
        bg:"#000",
        fg:"#fff"
    },
    end:{
        bg:"#000",
        fg:"#fff"
    },
});

</script>

<template>
    <div id="controls">
        <div id="colors" class="mx-auto w-fit">
            <Popover>
                <PopoverTrigger>
                    <div class="flex">
                        <span class="text-red-600 font-bold p-[2px] bg-red-300">C</span>
                        <span class="text-green-600 font-bold p-[2px] bg-green-300">O</span>
                        <span class="text-blue-600 font-bold p-[2px] bg-blue-300">L</span>
                        <span class="text-yellow-600 p-[2px] font-bold bg-yellow-300">O</span>
                        <span class="text-pink-600 p-[2px] font-bold bg-pink-300">R</span>
                        <span class="text-purple-600 p-[2px] font-bold bg-purple-300">S</span>
                    </div>
                </PopoverTrigger>
                <PopoverContent>
                    <div id="blockColors">
                        <div class="flex" @click="console.log(colors)">
                            <pre class="">#|</pre>
                            <p class="">block</p>
                        </div>
                        <div class="inline-flex flex-col">
                            <Label for="blockbg" class="text-wrap text-xs">background</Label>
                            <input name ="blockbg" type="color" v-model="colors.block.bg">
                            <span class="text-sm ">{{colors.block.bg}}</span>
                        </div>
                        <div class="flex-col inline-flex ml-2">

                            <Label for="blockfg" class="text-wrap text-xs">text</Label>
                            <input name ="blockfg" type="color" v-model="colors.block.fg">
                            <span class="text-sm ">{{colors.block.fg}}</span>
                        </div>
                    </div>
                    <Separator class="my-1" :decorative="true" />
                    <div id="pathColors">
                        <div class="flex">
                            <pre class="">.|</pre>
                            <p class="">path</p>
                        </div>
                        <div class="inline-flex flex-col">
                            <Label for="pathbg" class="text-wrap text-xs">background</Label>
                            <input name ="pathbg" type="color" v-model="colors.path.bg">
                            <span class="text-sm ">{{colors.path.bg}}</span>
                        </div>
                        <div class="flex-col inline-flex ml-2">

                            <Label for="pathfg" class="text-wrap text-xs">text</Label>
                            <input name ="pathfg" type="color" v-model="colors.path.fg">
                            <span class="text-sm ">{{colors.path.fg}}</span>
                        </div>
                    </div>
                    <Separator class="my-1" :decorative="true" />
                    <div id="xColors">
                        <div class="flex">
                            <pre class="">x|</pre>
                            <p class="">stops</p>
                        </div>
                        <div class="inline-flex flex-col">
                            <Label for="stopsbg" class="text-wrap text-xs">background</Label>
                            <input name ="stopsbg" type="color" v-model="colors.stop.bg">
                            <span class="text-sm ">{{colors.stop.bg}}</span>
                        </div>
                        <div class="flex-col inline-flex ml-2">

                            <Label for="stopsfg" class="text-wrap text-xs">text</Label>
                            <input name ="stopsfg" type="color" v-model="colors.stop.fg">
                            <span class="text-sm ">{{colors.stop.fg}}</span>
                        </div>
                    </div>
                    <Separator class="my-1" :decorative="true" />
                    <div id="xColors">
                        <div class="flex">
                            <pre class="">S|</pre>
                            <p class="">Start</p>
                        </div>
                        <div class="inline-flex flex-col">
                            <Label for="startbg" class="text-wrap text-xs">background</Label>
                            <input name ="startbg" type="color" v-model="colors.start.bg">
                            <span class="text-sm ">{{colors.start.bg}}</span>
                        </div>
                        <div class="flex-col inline-flex ml-2">

                            <Label for="startfg" class="text-wrap text-xs">text</Label>
                            <input name ="startfg" type="color" v-model="colors.start.fg">
                            <span class="text-sm ">{{colors.start.fg}}</span>
                        </div>
                    </div>
                    <Separator class="my-1" :decorative="true" />
                    <div id="xColors">
                        <div class="flex">
                            <pre class="">E|</pre>
                            <p class="">End</p>
                        </div>
                        <div class="inline-flex flex-col">
                            <Label for="endbg" class="text-wrap text-xs">background</Label>
                            <input name ="endbg" type="color" v-model="colors.end.bg">
                            <span class="text-sm ">{{colors.end.bg}}</span>
                        </div>
                        <div class="flex-col inline-flex ml-2">

                            <Label for="endfg" class="text-wrap text-xs ">text</Label>
                            <input name ="endfg" type="color" v-model="colors.end.fg">
                            <span class="text-sm ">{{colors.end.fg}}</span>
                        </div>
                    </div>
                </PopoverContent>
            </Popover>    
        </div>
    </div>

    <div id="tablewrapper" class="w-full p-5">
        <transition>
            <Table key="1" v-if="rows > 0 && cols > 0">
                    <TableCaption>{{rows}} x {{cols}}</TableCaption>
                    <TableHeader>
                        <TableRow class="">
                            <TableHead/>
                            <TableHead class="text-center font-bold text-lg" v-for="n in cols">
                                {{n-1}}
                            </TableHead>
                        </TableRow>
                    </TableHeader>
                    <TableBody>
                        <TransitionGroup>
                            <TableRow id="row" v-for="x in rows" :key="x">
                                <TableCell class="font-bold pl-0 text-lg">
                                    {{x-1}}
                                </TableCell>
                                <transition-group>
                                    <TableCell  
                                    v-for="y in cols" :key="y"
                                    :id="getId(matrix.grid[x-1][y-1])"
                                    :innerText="matrix.grid[x-1][y-1]"
                                    :style="{backgroundColor:colors.stop.bg}"
                                    class="hover:bg-muted border 
                                    border-zinc-50 text-center transition-[opacity,colors]
                                    duration-300"
                                    >
                                    </TableCell>
                                </transition-group>
                            </TableRow>
                        </TransitionGroup>
                    </TableBody>
                </Table>   
        </transition>
        </div>
    <div v-if="rows <=0 || cols <=0" class="text-center hover:bg-muted w-fit mx-auto p-6 mt-16 rounder border">
        <TooltipProvider>
            <Tooltip>
                <TooltipTrigger class="hover:cursor-help text-center text-lg">Matrix not built. 
                    <span class="block underline text-sm text-muted-foreground">help</span>
                </TooltipTrigger>
                <TooltipContent class="text-md">
                    <p>No matrix has been built yet.</p>
                    <p>
                        Please use the builder to build your matrix, so that it can then be 
                        visualized here.
                        Just click on the builder button up there ^
                    </p>
                </TooltipContent>
            </Tooltip>
        </TooltipProvider>
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
