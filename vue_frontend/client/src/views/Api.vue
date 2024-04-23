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
    title: 'About',
    href: '/about',
    description:
      'Learn more about Gridster and why it exists.',
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
                <NavigationMenuTrigger class="text-orange-400">ApiDocs</NavigationMenuTrigger>
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
    <h1 class="text-center text-3xl text-orange-300">API Documentation</h1>

<div class="w-1/2 mx-auto">
    <h2 class="text-left text-2xl text-orange-200">Endpoint 1: Test Endpoint</h2>
    <ul>
        <li><strong>URL:</strong> /api/test</li>
        <li><strong>Method:</strong> GET</li>
        <li><strong>Description:</strong> Tests the API endpoint by solving a predefined problem.</li>
        <li><strong>Request:</strong>Simple get request</li>
        <li><strong>Example:</strong> <code>curl -X GET http://samuelkuta.tech/api/test</code></li>
    </ul>
</div>

<div class="w-1/2 mx-auto">
    <h2 class="text-left text-2xl text-orange-200">Endpoint 2: Solve Problem</h2>
    <ul>
        <li><strong>URL:</strong> /api/solve</li>
        <li><strong>Method:</strong> POST</li>
        <li><strong>Description:</strong> Solves a given grid problem using specified algorithm.</li>
        <li><strong>Request:</strong>
            <code>
                {
                    "matrixJson": "[[\"#\",\"#\",\"#\"],[\"#\",\"S\",\"#\"],[\"#\",\"E\",\"#\"]]",
                    "start": "S",
                    "end": "E",
                    "algorhitm":"BFS"
                }
            </code>
        </li>
        <li><strong>Response:</strong>
            <code>
                {
                    "path": [
                        {"x": 1, "y": 1},
                        {"x": 2, "y": 1},
                        {"x": 2, "y": 2}
                    ],
                    "steps": 2
                }
            </code>
        </li>
        <li><strong>Example:</strong> <code>curl -X POST -H "Content-Type: application/json" -d '{"matrixJson":"[[\"#\",\"#\",\"#\"],[\"#\",\"S\",\"#\"],[\"#\",\"E\",\"#\"]]","start":"S","end":"E"}' http://samuelkuta.tech/api/solve</code></li>
    </ul>
</div>
    <h1 class="text-center text-3xl text-orange-300">Available algorhitms</h1>
    <h2 class="text-center text-2xl text-orange-200">BFS</h2>
    <p class="w-1/2 text-center mx-auto text-wrap">
        BFS is a method for systematically exploring a maze or graph. It starts at a specific point and explores all nearby locations before moving on to further ones. By doing this level-by-level search, BFS ensures that if a path exists, it finds the shortest one. It's like exploring a maze by checking all nearby rooms before moving to distant ones, ensuring an efficient search for the exit.
    </p>
    <h2 class="text-center text-2xl text-orange-200">DFS - Coming soon</h2>
    <p class="w-1/2 text-center mx-auto text-wrap">
Depth-First Search (DFS) is another algorithm used for traversing or searching tree or graph structures. It starts at a specific point and explores as far as possible along each branch before backtracking. It's like exploring a maze by going as deep as you can into one path before trying another. DFS is not guaranteed to find the shortest path, but it's often used for tasks like maze solving or graph traversal where finding any solution is more important than finding the shortest one.
    </p>

    <h2 class="text-center text-2xl text-orange-200">A* - Coming soon</h2>
    <p class="w-1/2 text-center mx-auto text-wrap">
A* systematically explores possible paths from a starting point to a goal by evaluating neighboring positions. It combines the cost to reach each position from the starting point with an estimate of the cost to reach the goal from that position. Using this combined evaluation, it prioritizes exploring paths that appear closer to the goal, guided by a heuristic function. This approach enables A* to efficiently find the shortest path in navigation and robotics applications.
    </p>
</template>


<style></style>
