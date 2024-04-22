
<script setup lang="ts">
import {
  NavigationMenu,
  NavigationMenuContent,
  NavigationMenuItem,
  NavigationMenuLink,
  NavigationMenuList,
  NavigationMenuTrigger,
} from '@/components/ui/navigation-menu'
</script>


<template>
    <NavigationMenu>
        <NavigationMenuList>
            <NavigationMenuItem>
                <NavigationMenuTrigger>API</NavigationMenuTrigger>
                <NavigationMenuContent>
                    <NavigationMenuLink >
                        <router-link :to="'/home'">
                            <span>Home</span>
                        </router-link>
                    </NavigationMenuLink>
                </NavigationMenuContent>
            </NavigationMenuItem>
        </NavigationMenuList>
    </NavigationMenu>
</template>


<style></style>
