<script setup lang="ts">
import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectLabel,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select'
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
import {test,sendToSolve} from '@/util/api'
defineOptions({ inheritAttrs: false })
const props = defineProps({
    matrix: {
      type: Object as PropType<{ grid: any[][] }>,
      default: () => ({ grid: [[]] }),
    },
  setup(props) {
  }
});
const rows = ref(props.matrix.grid.length);
const cols = ref(props.matrix.grid[0].length);
const buttonsDisabled = ref(false);
const animationSpeed = ref(300);
let animationResolver;


const colors = ref({
    block:{
        bg:"",
        fg:""
    },
    path:{
        bg:"#2ec27e",
        fg:""
    },
    stop:{
        bg:"#f5c211",
        fg:""
    },
    start:{
        bg:"#c64600",
        fg:""
    },
    end:{
        bg:"#ff7800",
        fg:""
    },
});
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
        case "x":
            return "stop";
            break;
        case "S/E":
            return "startend"
            break
        default:
            return "hash";
            break;
    }
};

const getStyle = (symbol:string):Object =>{
    const style = {
        fontSize:"",
        backgroundColor:"",
        color:"",
        border:""
    };
    style.color = getFg(symbol);
    style.backgroundColor = getBg(symbol);
    switch (symbol) {
        case ".":
            style.fontSize = "1.5em";
            break;
        case "S":
            break;
        case "E":
            break;
        case "x":
            break;
        case "S/E":
            break
        default:
            break;
    }
    return style;
}

const getFg = (symbol:string):string =>{
    switch (symbol) {
        case "#":
            return colors.value.block.fg;
            break;

        case ".":
            return colors.value.path.fg;
            break;
        case "S":
            return colors.value.start.fg;
            break;
        case "E":
            return colors.value.end.fg;
            break;
        case "x":
            return colors.value.stop.fg;
            break;
        case "S/E":
            return colors.value.start.fg;
            break
        default:
            return "inherit";
            break;
    }
};
const getBg = (symbol:string):string =>{
    switch (symbol) {
        case "#":
            return colors.value.block.bg;
            break;

        case ".":
            return colors.value.path.bg;
            break;
        case "S":
            return colors.value.start.bg;
            break;
        case "E":
            return colors.value.end.bg;
            break;
        case "x":
            return colors.value.stop.bg;
            break;
        case "S/E":
            return colors.value.start.bg;
            break
        default:
            return "inherit";
            break;
    }
};

const solve = async ():Promise<[]> =>{
    console.log(algorhitm);
    if(!props.matrix.grid || props.matrix.grid.length == 0 || props.matrix.grid[0].length == 0) return;
    const matrixJson = JSON.stringify(props.matrix.grid);
    const response = await sendToSolve(matrixJson,"S","E","BFS");
    solved.value = true;
    pathFound.value = response.pathFound;
    stopsFound.value = response.stopsFound;
    of.value = response.of;
    time.value = response.time;
    searchPath.value = response.searchPath;
    path.value = response.path;
    stopsNotFound.value = response.stopsNotFound;
    return null;
};

const getAllCells = () =>{
    return document.querySelectorAll("#c");
}
const gatherCellsByCoords = (coords:[{}])=>{
    const cells = getAllCells();
    const arr = [];
    for(const coord of coords){
        const x = coord.x;
        const y = coord.y;
        for(const cell of cells){
            if(cell.getAttribute("x")==x&&cell.getAttribute("y")==y){
                arr.push(cell);
                break;
            };
        }
    }
    return arr;
};

const scrollToStart = () =>{
    const startCell = document.querySelector("#start");
    const tablewrapper = document.querySelector("#table");
    if(startCell){
        tablewrapper.scrollTop = startCell.offsetTop - tablewrapper.clientHeight / 2;
        tablewrapper.scrollTop = startCell.offsetLeft - tablewrapper.clientWidth / 2;
    }
};

const visualizePath = async (path:[]) =>{
    scrollToStart();
    const cells = gatherCellsByCoords(path);
    let counter = 0;
    const animate = async () =>{
        await  new Promise((resolve) => { 
            animationResolver = resolve;
            buttonsDisabled.value = true;
            let counter = 0;
            for (let i = 0; i < cells.length; i++) {
                const cell = cells[i];
                setTimeout(() => {
                    if(!animationResolver) return;
                    if(cell.style.opacity == "0.3"){
                        cell.style.opacity = "0.7";
                    }
                    if(cell.style.opacity == "0.7"){
                        cell.style.opacity = "0.5";
                    }
                    else cell.style.opacity = "0.3";
                    if (i === cells.length - 1) resolve(); 
                }, animationSpeed.value * counter);
                counter++;
            }
        });    
    }
    await animate();
    buttonsDisabled.value = false;
    for (let i = 0; i < cells.length; i++) {
        const cell = cells[i];
        cell.style.opacity = "1";
    }
};
const visualizeSearchPath = async (path:[]) =>{
    const cells = gatherCellsByCoords(path);
    let counter = 0;
    const animate = async () =>{
        await  new Promise((resolve) => { 
            animationResolver = resolve;
            buttonsDisabled.value = true;
            let counter = 0;
            for (let i = 0; i < cells.length; i++) {
                const cell = cells[i];
                setTimeout(() => {
                    if(!animationResolver) return;
                    if(cell.style.opacity == "0.3"){
                        cell.style.opacity = "0.7";
                    }
                    if(cell.style.opacity == "0.7"){
                        cell.style.opacity = "0.5";
                    }
                    else cell.style.opacity = "0.3";
                    if (i === cells.length - 1) resolve(); 
                }, animationSpeed.value * counter);
                counter++;
            }
        });    
    }
    await animate();
    buttonsDisabled.value = false;
    for (let i = 0; i < cells.length; i++) {
        const cell = cells[i];
        cell.style.opacity = "1";
    }
};

const endAnimation = () =>{
    if(animationResolver){
        animationResolver();
        animationResolver = null;
        const cells = getAllCells();
        for (let i = 0; i < cells.length; i++) {
            const cell = cells[i];
            setTimeout(() => {
                cell.style.opacity = "1";
            }, animationSpeed.value);
        }
    }
}

const algorhitm = ref("BFS");

const solved = ref(false);
const pathFound = ref(false);
const stopsFound = ref(0);
const of = ref(0);
const stopsNotFound = ref([]);
const path = ref([]);
const searchPath = ref([]);
const time = ref(0);
</script>

<template>
    <div v-if="matrix.grid[0].length > 0" id="controls" class="flex flex-row justify-center items-center gap-2">
        <div id="colors" class=" w-fit">
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
                    <div id="sColors">
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
                    <div id="eColors">
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
        <div id="speed">
                <div class="flex flex-row p-1 w-fit">
                    <HoverCard >
                        <HoverCardTrigger class="flex gap-2 hover:cursor-help w-1/2">
                        <span class="text-xl text-center w-fit h-fit mx-auto mt-1">
                            Animation Speed
                        </span>
                        </HoverCardTrigger>
                        <HoverCardContent>
                        Speed of path animation in milliseconds.
                        </HoverCardContent>
                    </HoverCard>
                    <Input type="number" v-model="animationSpeed" class="w-1/3 text-lg"></Input>
                </div>
        </div>
        <div id="selectAlgo">

                <Select v-model="algorhitm" >
                    <SelectTrigger>
                        <SelectValue  class="text-md text-orange-300" placeholder="Pick an algorhitm" />
                    </SelectTrigger>
                <SelectContent >
                        <SelectGroup>
                            <SelectLabel>Algorhitm</SelectLabel>
                            <SelectItem value="BFS">
                            Breadth First Search
                            </SelectItem>
                            <SelectItem value="DFS">
                            Depth First Search
                            </SelectItem>
                        </SelectGroup>
                    </SelectContent>
                </Select>
        </div>
        <div>
            <Button class="" @click="solve">Solve Matrix</Button>
        </div>
    </div>
    <transition>
    
        <div id="solved" v-if="solved" class="flex flex-wrap flex-row justify-center gap-1 pt-2 w-max mx-auto relative">
            <div>
                <div v-if="pathFound" class="text-xl p-3 border rounder border-green-300 text-green-300">
                    Path was found.
                </div>
                <div v-else class="text-xl p-3 border rounder border-red-300 text-red-300">
                    No path was not found.
                </div>
            </div>
            <div class="text-xl p-3 border rounded">
                Stops visited: {{stopsFound}}/{{of}}
            </div>
            <div class="text-xl p-3 border rounded">
                Time taken: {{time}}ms | {{time/1000}}s
            </div>
            <div v-if="pathFound" class="flex flex-row gap-1 w-full justify-center">
                <Button :disabled="buttonsDisabled"  @click="visualizePath(path)" class="w-5/12">Visualize Path</Button>
                <Button :disabled="buttonsDisabled"  @click="visualizeSearchPath(searchPath)" class="w-5/12">Visualize Search Path</Button>
            </div>
            <Button @click="endAnimation" v-if="buttonsDisabled" class="w-5/12">Stop</Button>
        </div>
    </transition>

    <div id="tablewrapper" class="w-full p-5">
        <transition>
            <Table key="1" v-if="rows > 0 && cols > 0" id="table">
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
                                    :x="x-1" :y="y-1"
                                    id="c"
                                    :name="getId(matrix.grid[x-1][y-1])"
                                    :innerText="matrix.grid[x-1][y-1]"
                                    :style="getStyle(matrix.grid[x-1][y-1])"
                                    class="border 
                                    border-zinc-50 text-center 
                                    transition-all duration-1000 font-bold text-md">
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
