<script setup lang="ts">
import {
  NavigationMenu,
  NavigationMenuContent,
  NavigationMenuItem,
  NavigationMenuLink,
  NavigationMenuList,
  NavigationMenuTrigger,
} from '@/components/ui/navigation-menu'

const navlinks: { title: string, href: string, description: string }[] = [
  {
    title: 'API Documentation',
    href: '/api',
    description:
      'Read Gridster API docs with usage information and http specifications.',
  },
  {
    title: 'Home',
    href: '/',
    description:
      'Visit the Gridster matrix builder and visualizer to try it yourself.',
  },
]

</script>


<template>
    <NavigationMenu class="m-1">
        <NavigationMenuList>
            <NavigationMenuItem>
                <NavigationMenuTrigger class="text-orange-400">About</NavigationMenuTrigger>
                <NavigationMenuContent class="">
                    <NavigationMenuLink class="">
                        <ul class="">
                            <li v-for="link in navlinks" class="gap-2 w-full" :key="link">
                                <router-link :to="link.href" :key="link.href"
                                    class="flex flex-col gap-1 
                                    w-full text-wrap p-1  hover:bg-muted"
                                >

                                    <h2 class="text-orange-400 p-1 w-max">{{link.title}}</h2>
                                    <span class="p-1 w-max">{{link.description}}</span>
                                </router-link>
                            </li>
                        </ul>
                    </NavigationMenuLink>
                </NavigationMenuContent>
            </NavigationMenuItem>
        </NavigationMenuList>
    </NavigationMenu>

    <div class="p-5 mx-auto w-2/3 text-wrap bg-muted">
        <h1 class="text-3xl text-orange-300">What is Gridster and why should I care?</h1>
        <p class="text-lg border bg-muted rounded border-orange-300 p-2">
            Great question. Gridster is a tool that provides 
            a way to help you with solving complex character mazes,
            educate about pathfinding algorhitms and understand them better,
            or to just entertain yourself a bit with it's visuals.
        </p>
        <h2 class="text-3xl">Mazes?</h2>
        <p class="text-lg border bg-muted rounded border-orange-300 p-2">
            The mazes we're talking about are essentially matrices containing characters, 
            such as this one:
        </p>
        <div class="grid items-center justify-center">

            <div><span>#</span><span>#</span><span>#</span><span>#</span><span>.</span><span>#</span><span>#</span><span>.</span><span>.</span><span>x</span></div>
            <div><span>#</span><span>#</span><span>#</span><span>x</span><span>.</span><span>.</span><span>.</span><span>.</span><span>.</span><span>#</span></div>
            <div><span>#</span><span>.</span><span>.</span><span>.</span><span>.</span><span>#</span><span>S</span><span>.</span><span>#</span><span>#</span></div>
            <div><span>#</span><span>.</span><span>#</span><span>#</span><span>.</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span></div>
            <div><span>#</span><span>.</span><span>#</span><span>#</span><span>.</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span></div>
            <div><span>#</span><span>.</span><span>#</span><span>#</span><span>.</span><span>.</span><span>.</span><span>E</span><span>#</span><span>#</span></div>
            <div><span>#</span><span>.</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span></div>
            <div><span>#</span><span>.</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span></div>
            <div><span>x</span><span>x</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span></div>
            <div><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span><span>#</span></div>
        </div>


        <h1 class="text-3xl text-orange-300">Who is Gridster for?</h1>
        <h2 class="text-2xl">Developers</h2>
        <p class="text-lg bg-muted rounded shadow shadow-orange-300 p-2">
            If u are a developer and u need to solve mazes in your
            applications, Gridster provides a REST API that can not only
            solve a Matrix for u, but also provide u with additional information,
            and letting u pick one of our pathfinding algoritms.
        </p>
        <h2 class="text-2xl">Students and Enthusiasts</h2>
        <p class="text-lg bg-muted rounded shadow shadow-orange-300 p-2">
            If u are a IT student or just interested in pathfinding algorhitms,
            Gridster provides a way for u to build your own Matrix, pick a 
            pathfinding algorhitm and see how it solves the Matrix for u.
            U can also see how the Matrix is searched and what the shortest path is,
            all with nice colorful animations that u can adjust.
        </p>

        <h1 class="text-3xl text-orange-300">Why was Gridster created?</h1>
        <p class="text-lg bg-muted rounded shadow shadow-orange-300 p-2">
            Gridster was created as a School graduation project for SPŠE Ječná in my 4th year
            of study there. It's supposed to make Pathfinding algorhitms in Graph theory
            more approachable and easier to understand.

        </p>
        <h1 class="text-3xl text-orange-300">How was it made?</h1>
        <p class="text-lg bg-muted rounded shadow shadow-orange-300 p-2">
            To not go too into detail, Gridster's backend API is built in Java 21
            using Spring Boot.
            The visual side was build using Vue 3.
        </p>
        <h1 class="text-3xl text-orange-300">Open source?</h1>
        <p class="text-lg bg-muted rounded shadow shadow-orange-300 p-2">
            Gridster is an open source project, and if you are interested
            u can visit the <a class="underline text-orange-300" href="https://github.com/dantolas/Omega">Github Repository</a>
            containing all the source code.
        </p>
    </div>
</template>


<style>
 .grid {
        line-height: 1.5; /* Adjust as needed */
    }
    .grid span {
        display: inline-block;
        width: 20px; /* Adjust as needed */
        text-align: center;
    }
</style>
