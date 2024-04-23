<script setup lang="ts">
import Toaster from '@/components/ui/toast/Toaster.vue'
import { useToast } from '@/components/ui/toast/use-toast'
import {
  NavigationMenu,
  NavigationMenuContent,
  NavigationMenuItem,
  NavigationMenuLink,
  NavigationMenuTrigger,
    NavigationMenuList
} from '@/components/ui/navigation-menu'
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs'
import {ref,h} from "vue"
import MatrixBuilder from '@/components/MatrixBuilder.vue'
import MatrixVisualizer from '@/components/MatrixVisualizer.vue'
import { ToastAction } from '@/components/ui/toast'
const { toast } = useToast()
const navlinks: { title: string, href: string, description: string }[] = [
  {
    title: 'API Documentation',
    href: '/api',
    description:
      'Read Gridster API docs with usage information and http specifications',
  },
  {
    title: 'About',
    href: '/about',
    description:
      'Learn more about Gridster and why it exists.',
  },
]

const defaultBuilderRows = ref(10);
const defaultBuilderCols = ref(10);

const builtMatrix = ref({
    grid:[[]],
    startX:Number,
    startY:Number,
    endX:Number,
    endY:Number
}
);

const builtMatrixJson=  ref("");

const caughtBuild = (build) =>{
    console.log("Event caught");
    toast({
        title:"Matrix built!",
        description: "Check it out in the visualizer.",
        action: h(ToastAction, {
            altText: '->',
        }, {
                default: () => '->',
        })
    });
    tabsValue.value="visualizer";
    builtMatrix.value.grid = build.matrix;
    builtMatrixJson.value= JSON.stringify(build.matrix);
}

const tabsValue = ref("builder");

</script>


<template>

    <Toaster :duration="3000" />
    <NavigationMenu class="m-1">
        <NavigationMenuList>
            <NavigationMenuItem>
                <NavigationMenuTrigger class="text-orange-400">Home</NavigationMenuTrigger>
                <NavigationMenuContent class="">
                    <NavigationMenuLink class="">
                        <ul class="">
                            <li v-for="link in navlinks" class="gap-2 w-full">
                                <a :href="link.href"
                                    class="flex flex-col gap-1 
                                    w-full text-wrap p-1  hover:bg-muted"
                                >
                                    <h2 class="text-orange-400 p-1 w-max">{{link.title}}</h2>
                                    <span class="p-1 w-max">{{link.description}}</span>
                                </a>
                            </li>
                        </ul>
                    </NavigationMenuLink>
                </NavigationMenuContent>
            </NavigationMenuItem>
        </NavigationMenuList>
    </NavigationMenu>

    <div>
        <h1 class="text-center text-3xl text-orange-300">Welcome to Gridster [#.xSE]</h1>
        <p class="text-center text-2xl text-orange-200">Go on and build yourself a Matrix.</p>
            <Tabs default-value="builder" orientation="horizontal" class="" v-model="tabsValue">
            <div class="w-fit mx-auto">
                <TabsList class="w-full">
                    <TabsTrigger value="builder" class="rounded w-1/2" >
                        builder
                    </TabsTrigger>
                    <TabsTrigger value="visualizer" class="rounded w-1/2">
                        visualizer
                    </TabsTrigger>
                </TabsList>
            </div>
                <TabsContent value="builder" >
                <MatrixBuilder @build="caughtBuild" :x="defaultBuilderRows" :y="defaultBuilderCols"
                    :lastMatrixJson="builtMatrixJson"
                />
                </TabsContent>
                <TabsContent value="visualizer">
                <MatrixVisualizer :matrix="builtMatrix" 
                    class="p-3"
                />
                </TabsContent>

            </Tabs>
    </div>
</template>


<style>
#app,main{
    height:100%;
}
body{
    height: 100vh;
}
 .v-enter-active,
.v-leave-active {
  transition: opacity 0.5s ease;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
}
</style>
