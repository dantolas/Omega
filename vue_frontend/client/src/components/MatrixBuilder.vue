<script setup lang="ts">
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

const props = defineProps({
    x:Number,
    y:Number
});
const rows = ref(props.x);
const cols = ref(props.y);

const hold = ref(false);

const characterSelected = ref("#");
let startPlaced = false;
let endPlaced = false;

const switchHold = (value,e) =>{
    e.preventDefault();
    hold.value = value;
    if(value == false) return;


    if(e.target.id === "c"){
        if(characterSelected.value === "S"){
            if(startPlaced){
                console.log("Start already placed");
                return;
            }

            startPlaced = true;
            if(e.target.innerText === "E"){
                e.target.innerText = "S/E";
                return;
            }
            e.target.innerText = characterSelected.value;
            return;
        }

        if(characterSelected.value === "E"){

        }
        e.target.innerText = characterSelected.value;
    }
}

const pointerOver = (e)=>{
    e.preventDefault();
    if(hold.value && e.target.id === "c"){
        e.target.innerText = characterSelected.value;
    }
}


const valueChange = () =>{
    console.log("Value has changed");
}
</script>

<template>
    <div class="w-full relative p-2 flex flex-row">
        <div id="tablewrapper" class="w-3/4">
            <transition>
                <Table  @pointerup="switchHold(false,$event)">
                    <TableCaption>{{rows}} x {{cols}}</TableCaption>
                    <TableHeader>
                        <TableRow class="">
                            <TableHead/>
                            <TableHead class="" v-for="n in cols">
                                {{n-1}}
                            </TableHead>
                        </TableRow>
                    </TableHeader>
                    <TableBody>
                        <TransitionGroup>
                        
                            <TableRow v-for="n in rows" :key="n">
                                <TableCell class="font-medium pl-0">
                                    {{n-1}}
                                </TableCell>
                                <TableCell id="c" v-for="n in cols" @pointerover="pointerOver" @pointerdown="switchHold(true,$event)" class="hover:cursor-pointer hover:bg-muted border border-zinc-50 text-center">
                                    #
                                </TableCell>
                            </TableRow>
                        </TransitionGroup>
                    </TableBody>
                </Table>   
            </transition>
        </div>

        <div id="controls" class="w-1/4 p-2">
            <div id="sliders">
                <div class="relative w-full">
                    <div class="flex flex-row p-1">
                        <HoverCard >
                            <HoverCardTrigger class="hover:cursor-help w-1/2">Rows</HoverCardTrigger>
                            <HoverCardContent>
                                Amount of rows on the Y axis.
                            </HoverCardContent>
                        </HoverCard>
                        <Input type="number" v-model="rows" class="w-1/2"></Input>
                    </div>
                </div>
                <hr class="p-2 my-1">
                <div class="relative w-full">
                    <div class="flex flex-row p-1">
                        <HoverCard >
                            <HoverCardTrigger class="hover:cursor-help w-1/2">Cols</HoverCardTrigger>
                            <HoverCardContent>
                                Amount of columns on the X axis.
                            </HoverCardContent>
                        </HoverCard>
                        <Input type="number" v-model="cols" class="w-1/2"></Input>
                    </div>
                </div>
                <div>
                    <HoverCard >
                        <HoverCardTrigger class="hover:cursor-help w-1/2">Character</HoverCardTrigger>
                        <HoverCardContent>
                            <ul>
                                Pick a character and start drawing.
                                <li>
                                    # = Blocked path.
                                </li>
                                <li>
                                    x = Point to visit.
                                </li>
                                <li>
                                    S = Start of path.
                                </li>
                                <li>
                                    E = End of path.
                                </li>
                                <li>
                                    . = Path u can walk on.
                                </li>
                            </ul>
                        </HoverCardContent>
                    </HoverCard>
                    <div class="flex flex-row">
                        <ToggleGroup v-model="characterSelected" type="single">
                            <ToggleGroupItem  value="#">
                                #
                            </ToggleGroupItem>
                            <ToggleGroupItem  value="x">
                                x
                            </ToggleGroupItem>
                            <ToggleGroupItem  value="S">
                                S
                            </ToggleGroupItem>
                            <ToggleGroupItem  value="E">
                                E
                            </ToggleGroupItem>
                            <ToggleGroupItem  value=".">
                                .
                            </ToggleGroupItem>
                        </ToggleGroup>
                    </div>
                </div>
            </div>
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
