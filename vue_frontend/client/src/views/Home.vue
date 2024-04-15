<script setup lang="ts">
import {
  NavigationMenu,
  NavigationMenuContent,
  NavigationMenuIndicator,
  NavigationMenuItem,
  NavigationMenuLink,
  NavigationMenuList,
  NavigationMenuTrigger,
  NavigationMenuViewport,
} from '@/components/ui/navigation-menu'
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs'
import axios from 'axios';
import {onMounted, ref} from "vue"
import { RouterLink } from 'vue-router'
import MatrixBuilder from '@/components/MatrixBuilder.vue'
const components: { title: string, href: string, description: string }[] = [
  {
    title: 'API Documentation',
    href: '/api/docs',
    description:
      'Read Gridster API docs with usage information and http specifications',
  },
  {
    title: 'API Playground',
    href: '/api/playground',
    description:
      'Try the Gridster REST API yourself, to see how it works',
  },
]

const caughtBuild = (build) =>{
    console.log("Event caught");
    console.log(build);
}
</script>


<template>
    <NavigationMenu class="m-1">
        <NavigationMenuList>
            <NavigationMenuItem>
                <NavigationMenuTrigger class="text-orange-400">Home</NavigationMenuTrigger>
                <NavigationMenuContent class="">
                    <NavigationMenuLink class="">
                        <ul class="">
                            <li v-for="component in components" class="gap-2 w-full">
                                <a :href="component.href"
                                    class="flex flex-col gap-1 
                                    w-full text-wrap p-1  hover:bg-muted"
                                >
                                    <h2 class="text-orange-400 p-1 w-max">{{component.title}}</h2>
                                    <span class="p-1 w-max">{{component.description}}</span>
                                </a>
                            </li>
                        </ul>
                    </NavigationMenuLink>
                </NavigationMenuContent>
            </NavigationMenuItem>
        </NavigationMenuList>
    </NavigationMenu>

    <div>
            <Tabs default-value="builder" orientation="horizontal" class="flex-col justify-center items-center">
                <TabsList class=" w-1/2">
                    <TabsTrigger value="builder" class="rounded w-full" >
                        builder
                    </TabsTrigger>
                    <TabsTrigger value="visualizer" class="rounded w-full ">
                        visualizer
                    </TabsTrigger>
                </TabsList>
                <TabsContent value="builder" >
                <MatrixBuilder @build="caughtBuild" :x="10" :y="10"/>
                </TabsContent>
                <TabsContent value="visualizer">
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
