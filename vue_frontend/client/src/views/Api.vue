
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
import axios from 'axios';
import {onMounted, ref} from "vue"

</script>


<template>
    <NavigationMenu>
        <NavigationMenuList>
            <NavigationMenuItem>
                <NavigationMenuTrigger>API</NavigationMenuTrigger>
                <NavigationMenuContent>
                    <NavigationMenuLink >
                        <router-link :to="/home">
                            <span>Home</span>
                        </router-link>
                    </NavigationMenuLink>
                </NavigationMenuContent>
            </NavigationMenuItem>
        </NavigationMenuList>
    </NavigationMenu>
</template>


<style></style>
