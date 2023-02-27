// LCA
var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

let N = Number(input[0]);

// tree init
let adjs = new Array(N + 1);
let parent = new Array(N + 1);
let depth = new Array(N + 1);
for(let i = 0; i < adjs.length; i++) adjs[i] = [];


for(let i = 1; i < N; i++) {
    // input vertex
    [A, B] = input[i].split(' ').map((ele) => Number(ele));
    adjs[A].push(B);
    adjs[B].push(A);
}

let visited = new Array(N + 1).fill(0);
visited[1] = 1;

function defineDepth(node, dep) {
    // fill par array with dfs
    for(let nextNode of adjs[node]) {
        if(visited[nextNode] != 0) continue;
            
        parent[nextNode] = node;
        depth[nextNode] = dep + 1;
        visited[nextNode] = 1; // visit
        defineDepth(nextNode, depth[nextNode]);
    }
}

depth[1] = 0;
defineDepth(1, depth[1]);

// find LCA
let M = Number(input[N]);
let ans = "";

function findParent(n1, n2) {
    // match depth
    for(;depth[n1] != depth[n2];) n1 = parent[n1];

    if(n1 == n2) return n1;
    while(n1 != n2) {
        n1 = parent[n1];
        n2 = parent[n2];

        if(n1 == n2) return n1;
    }
}

for(let i = N + 1; i <= N + M; i++) {
    // input children
    [node1, node2] = input[i].split(' ').map((ele) => Number(ele));

    // make left node is deeper
    if(depth[node1] > depth[node2]) {
        ans += `${findParent(node1, node2)}\n`;
    } else {
        ans += `${findParent(node2, node1)}\n`;
    }
}

console.log(ans.trim());

