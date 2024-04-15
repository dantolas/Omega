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
  Popover,
  PopoverContent,
  PopoverTrigger,
} from '@/components/ui/popover'

const props = defineProps({
    x:Number,
    y:Number
});
const rows = ref(props.x);
const cols = ref(props.y);

const hold = ref(false);

const characterSelected = ref("#");
const start = {
    x:null,
    y:null,
};
const startPlaced = ():Boolean =>{
    if(!start.x || !start.y) return false;
    for(const cell of getAllCells()){
        if(cell.getAttribute("x") === start.x && cell.getAttribute("y") === start.y) return true;
    }
    return false;
}
const end= {
    x:null,
    y:null,
};
const endPlaced = ():Boolean =>{
    if(!end.x || !end.y) return false;
    for(const cell of getAllCells()){
        if(cell.getAttribute("x") === end.x && cell.getAttribute("y") === end.y) return true;
    };
    return false;
}
const switchMouseHold:void = (value,e) =>{
    e.preventDefault();
    hold.value = value;
    if(value == false) return;

    if(!characterSelected.value) characterSelected.value = "#";
    placeCharacter(e.target,characterSelected.value);
}
const pointerOverCell = (e)=>{
    e.preventDefault();
    if(hold.value){
        if(!characterSelected.value) characterSelected.value = "#";
        placeCharacter(e.target,characterSelected.value);
    }
}

const setCharacter:void = (element,text) =>{
    element.style.opacity = 0;
    element.style.backgroundColor = "hsl(var(--primary) / 0.9)";
        setTimeout(() => {
        element.innerText = text;
        element.style.opacity = 1;
        element.style.backgroundColor = "";
    }, 200);
}
const placeCharacter:void = (cell,value) =>{
    if(!value){
        value = "#";
    }
    if(cell.id !== "c") return;
    const cellX = cell.getAttribute("x");
    const cellY = cell.getAttribute("y");
    if(value === "S"){
        placeStart(cellX,cellY,cell)
        return;
    }
    if(value === "E"){
        placeEnd(cellX,cellY,cell);
        return;
    }

    if(cellX === start.x && cellY === start.y){
        start.x = null;
        start.y = null;
    }
    if(cellX === end.x && cellY === end.y){
        end.x = null;
        end.y = null;
    }
    setCharacter(cell,value);
}

const placeStart = (cellX,cellY,cell):void =>{
    if(start.x !=null && start.y!=null){
        const cells = getAllCells();
        for(let i = 0; i < cells.length; i++){
            if(cells[i].getAttribute("x")!==start.x||cells[i].getAttribute("y")!==start.y) continue;
            if(cells[i].getAttribute("x")===end.x&&cells[i].getAttribute("y")===end.y){
                setCharacter(cells[i],"E");
                start.placed = false;
                break;
            };
            setCharacter(cells[i],"#");
            start.placed = false;
            break;
        }
    }
    start.x = cellX;
    start.y = cellY;
    showAlert.value = false;
    if(cellX == end.x && cellY == end.y){
        setCharacter(cell,"S/E");
        return;
    }
    setCharacter(cell,"S");
    return;
}

const placeEnd = (cellX,cellY,cell):void =>{
    if(end.x != null && end.y != null){
        const cells = getAllCells();
        for(let i = 0; i < cells.length; i++){
            if(cells[i].getAttribute("x")!==end.x||cells[i].getAttribute("y")!==end.y) continue;
            if(cells[i].getAttribute("x")===start.x&&cells[i].getAttribute("y")===start.y){
                setCharacter(cells[i],"S");
                break;
            };
            setCharacter(cells[i],"#");
            break;
        }

    }
    end.x = cellX;
    end.y = cellY;
    showAlert.value = false;
    if(cellX == start.x && cellY == start.y){
        setCharacter(cell,"S/E");
        return;
    }
    setCharacter(cell,"E");
    return;
}

const emit = defineEmits(
    [
        "build"
    ]
);
const getAllCells = () =>{
    return document.querySelectorAll("#c");
}
const getRowsNodes = ():String[][]=>{
    const rows = document.querySelectorAll("#row");
    let nodeArray  = [];
    rows.forEach((node)=>{
        const nodeRow = Array.from(node.children).slice(1)
        nodeArray.push(nodeRow);
    })
    return nodeArray;
}
const getRows = ():String[][]=>{
    const rows = document.querySelectorAll("#row");
    let rowsArray = [];
    rows.forEach((node)=>{
        const charRow = [];
        const nodeRow = Array.from(node.children).slice(1)
        nodeRow.forEach((cell) =>{
            charRow.push(cell.innerText);
        });
        rowsArray.push(charRow);
    })
    return rowsArray;
}

const buildMatrix = ():void =>{
    hideAlert();
    if(!startPlaced()){
        popupAlert("Start not placed","Please place a start point on the grid");
        return;
    }
    if(!endPlaced()){
        popupAlert("End not placed","Please place an end point on the grid");
        return;
    }
    const rows = getRows();
    const build = {
        matrix:rows,
        startX:start.x,
        startY:start.y,
        endX:end.x,
        endY:end.y
    }
    console.log("Emmiting build");
    emit('build',build);
};

const  copyMatrix = async ()=>{
    await navigator.clipboard.writeText(JSON.stringify(getRows()));
}

const showPasteBox:Boolean = ref(false);
const pasteMatrix = async ()=>{
    const json = document.querySelector("#pasteInput").value;
    try {
        const matrix = JSON.parse(json);
        if(!(matrix instanceof Array) || !matrix.length || !(matrix[0] !instanceof Array)){
            throw new Error("error");
        }
        const pasterows = matrix.length;
        const pastecols = matrix[0].length;
        matrix.forEach((row) =>{
            if(row.length != pastecols){
                popupAlert("Provided JSON is not a Matrix","Not every row is the same length,"+
                    "we can only solve a Grid/Matrix for you.");
                return;
            }
        });

        setTimeout(() => {
            rows.value = pasterows;
            cols.value  = pastecols;
            
        }, 300);
        setTimeout(() => {
            const rowsArray = getRowsNodes();
            
            for(let i = 0; i < rows.value; i++){
                for(let j = 0; j < cols.value; j++){
                    placeCharacter(rowsArray[i][j],matrix[i][j]);
                }
            }

        }, 200);
    } catch (error) {
        console.log(error);
        showPasteBox.value = false;
        popupAlert("Invalid JSON","Please paste in a valid JSON representation of your"+
        " desired character matrix");
    }
}

const hideAlert = ()=>{
    showAlert.value = false;
}
const popupAlert = (title,description) =>{
    showAlert.value = true;
    alertTitle.value = title;
    alertDescription.value = description;
}
const alertTitle = ref("Default");
const alertDescription = ref("Default");
const showAlert = ref(false);
</script>

<template>
    <div class="w-full relative p-2 flex flex-row rounded border">
        <div id="tablewrapper" class="w-3/4">
            <transition>
                <Table  @pointerup="switchMouseHold(false,$event)">
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
                                    <TableCell :x="x-1" :y="y-1" id="c" v-for="y in cols" :key="y"  
                                        @pointerover="pointerOverCell" 
                                        @pointerdown="switchMouseHold(true,$event)" 
                                        class="hover:cursor-pointer hover:bg-muted border 
                                        border-zinc-50 text-center transition-[opacity,colors]
                                        duration-300"
                                    >
                                        #
                                    </TableCell>
                                </transition-group>
                            </TableRow>
                        </TransitionGroup>
                    </TableBody>
                </Table>   
            </transition>
        </div>

        <div id="controls" class="w-1/4 p-2 flex flex-col relative ">
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
            <div id="character">
                <HoverCard >
                    <HoverCardTrigger class="hover:cursor-help w-1/2">Character</HoverCardTrigger>
                    <HoverCardContent>
                        <ul>
                            Pick a character and start drawing.
                            <li class="text-left tracking-wide">
                                <pre class="inline">#</pre>
                                | Blocked path.
                            </li>
                            <li class="text-left tracking-wide">
                                <pre class="inline">x</pre>
                                | Point to visit.
                            </li>
                            <li class="text-left tracking-wide">
                                <pre class="inline">S</pre>
                                | Start of path.
                            </li>
                            <li class="text-left tracking-wide">
                                <pre class="inline">E</pre>
                                | End of path.
                            </li>
                            <li class="text-left tracking-wide">
                                <pre class="inline">.</pre>
                                | Path u can walk on.
                            </li>
                        </ul>
                    </HoverCardContent>
                </HoverCard>
                <div class="flex flex-row">
                    <ToggleGroup v-model="characterSelected" type="single">
                        <ToggleGroupItem  variant="outline" value="#">
                            #
                        </ToggleGroupItem>
                        <ToggleGroupItem  variant="outline" value="x">
                            x
                        </ToggleGroupItem>
                        <ToggleGroupItem variant="outline" value="S">
                            S
                        </ToggleGroupItem>
                        <ToggleGroupItem  variant="outline" value="E">
                            E
                        </ToggleGroupItem>
                        <ToggleGroupItem variant="outline" value=".">
                            .
                        </ToggleGroupItem>
                    </ToggleGroup>
                </div>
            </div>
            <div class="p-3 flex flex-col gap-2 items-center justify-center mt-3 ">
                <div class="flex flex-row gap-3">
                    <Popover>
                        <PopoverTrigger>
                            <Button @click="copyMatrix" class="font-bold ">Copy</Button>
                        </PopoverTrigger>
                        <PopoverContent>
                            Copied to clipboard
                        </PopoverContent>
                    </Popover>
                    <Popover :open="showPasteBox">
                        <PopoverTrigger>
                            <Button class="font-bold" @click="showPasteBox=!showPasteBox">Paste</Button>
                        </PopoverTrigger>
                        <PopoverContent>
                            <Input id="pasteInput" type="text"></Input>
                            <Label for="paste" class="text-wrap text-xs">Submit Character Matrix in JSON format</Label>
                            <Button name="paste" @click="(event)=>{showPasteBox=!showPasteBox;pasteMatrix()}"
                                class="mt-3 font-bold ">Paste</Button>
                        </PopoverContent>
                    </Popover>
                </div>
                <Button @click="buildMatrix" class="w-1/2 font-bold ">Build</Button>
            </div>
            <div class="">
                <Transition>
                    <Alert class="w-full" variant="destructive" v-show="showAlert">
                        <AlertCircle class="w-4 h-4" />
                        <AlertTitle >{{alertTitle}}</AlertTitle>
                        <AlertDescription>
                            {{alertDescription}}
                        </AlertDescription>
                    </Alert>
                </Transition>
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
