import{_ as p,a as h,b as q,c as g,d,e as x}from"./index-DA_gmRZf.js";import{d as m,o as i,c as n,D as t,B as o,u as e,N as r,O as f,P as _,G as b,K as s,Q as v,M as l}from"./index-CWXc801o.js";import"./createLucideIcon-BBtHaN3i.js";const y={class:""},S={class:"gap-2 w-full"},w={class:"text-orange-400 p-1 w-max"},k={class:"p-1 w-max"},E=f('<h1 class="text-center text-3xl text-orange-300">API Documentation</h1><div class="w-1/2 mx-auto"><h2 class="text-left text-2xl text-orange-200">Endpoint 1: Test Endpoint</h2><ul><li><strong>URL:</strong> /api/test</li><li><strong>Method:</strong> GET</li><li><strong>Description:</strong> Tests the API endpoint by solving a predefined problem.</li><li><strong>Request:</strong>Simple get request</li><li><strong>Example:</strong> <code>curl -X GET http://samuelkuta.tech/api/test</code></li></ul></div><div class="w-1/2 mx-auto"><h2 class="text-left text-2xl text-orange-200">Endpoint 2: Solve Problem</h2><ul><li><strong>URL:</strong> /api/solve</li><li><strong>Method:</strong> POST</li><li><strong>Description:</strong> Solves a given grid problem using specified algorithm.</li><li><strong>Request:</strong><code> { &quot;matrixJson&quot;: &quot;[[\\&quot;#\\&quot;,\\&quot;#\\&quot;,\\&quot;#\\&quot;],[\\&quot;#\\&quot;,\\&quot;S\\&quot;,\\&quot;#\\&quot;],[\\&quot;#\\&quot;,\\&quot;E\\&quot;,\\&quot;#\\&quot;]]&quot;, &quot;start&quot;: &quot;S&quot;, &quot;end&quot;: &quot;E&quot;, &quot;algorhitm&quot;:&quot;BFS&quot; } </code></li><li><strong>Response:</strong><code> { &quot;path&quot;: [ {&quot;x&quot;: 1, &quot;y&quot;: 1}, {&quot;x&quot;: 2, &quot;y&quot;: 1}, {&quot;x&quot;: 2, &quot;y&quot;: 2} ], &quot;steps&quot;: 2 } </code></li><li><strong>Example:</strong> <code>curl -X POST -H &quot;Content-Type: application/json&quot; -d &#39;{&quot;matrixJson&quot;:&quot;[[\\&quot;#\\&quot;,\\&quot;#\\&quot;,\\&quot;#\\&quot;],[\\&quot;#\\&quot;,\\&quot;S\\&quot;,\\&quot;#\\&quot;],[\\&quot;#\\&quot;,\\&quot;E\\&quot;,\\&quot;#\\&quot;]]&quot;,&quot;start&quot;:&quot;S&quot;,&quot;end&quot;:&quot;E&quot;}&#39; http://samuelkuta.tech/api/solve</code></li></ul></div><h1 class="text-center text-3xl text-orange-300">Available algorhitms</h1><h2 class="text-center text-2xl text-orange-200">BFS</h2><p class="w-1/2 text-center mx-auto text-wrap"> BFS is a method for systematically exploring a maze or graph. It starts at a specific point and explores all nearby locations before moving on to further ones. By doing this level-by-level search, BFS ensures that if a path exists, it finds the shortest one. It&#39;s like exploring a maze by checking all nearby rooms before moving to distant ones, ensuring an efficient search for the exit. </p><h2 class="text-center text-2xl text-orange-200">DFS - Coming soon</h2><p class="w-1/2 text-center mx-auto text-wrap"> Depth-First Search (DFS) is another algorithm used for traversing or searching tree or graph structures. It starts at a specific point and explores as far as possible along each branch before backtracking. It&#39;s like exploring a maze by going as deep as you can into one path before trying another. DFS is not guaranteed to find the shortest path, but it&#39;s often used for tasks like maze solving or graph traversal where finding any solution is more important than finding the shortest one. </p><h2 class="text-center text-2xl text-orange-200">A* - Coming soon</h2><p class="w-1/2 text-center mx-auto text-wrap"> A* systematically explores possible paths from a starting point to a goal by evaluating neighboring positions. It combines the cost to reach each position from the starting point with an estimate of the cost to reach the goal from that position. Using this combined evaluation, it prioritizes exploring paths that appear closer to the goal, guided by a heuristic function. This approach enables A* to efficiently find the shortest path in navigation and robotics applications. </p>',10),z=m({__name:"Api",setup(A){const u=[{title:"About",href:"/about",description:"Learn more about Gridster and why it exists."},{title:"Home",href:"/",description:"Visit the Gridster matrix builder and visualizer to try it yourself."}];return(B,D)=>{const c=_("router-link");return i(),n(r,null,[t(e(x),{class:"m-1"},{default:o(()=>[t(e(p),null,{default:o(()=>[t(e(h),null,{default:o(()=>[t(e(q),{class:"text-orange-400"},{default:o(()=>[b("Api")]),_:1}),t(e(g),{class:""},{default:o(()=>[t(e(d),{class:""},{default:o(()=>[s("ul",y,[(i(),n(r,null,v(u,a=>s("li",S,[t(c,{to:a.href,class:"flex flex-col gap-1 w-full text-wrap p-1 hover:bg-muted"},{default:o(()=>[s("h2",w,l(a.title),1),s("span",k,l(a.description),1)]),_:2},1032,["to"])])),64))])]),_:1})]),_:1})]),_:1})]),_:1})]),_:1}),E],64)}}});export{z as default};
