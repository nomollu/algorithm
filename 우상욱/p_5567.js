// S2 결혼식
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let N = Number(input[0]), M = Number(input[1]);

// init graph
let graph = Array.from(Array(N + 1), (v, idx) => new Array(0));

// input node
for(let i = 2; i < M + 2;) {
    [n1, n2] = input[i++].split(' ').map((ele) => Number(ele));
    graph[n1].push(n2);
    graph[n2].push(n1);
}

let visited = new Array(N + 1).fill(false);

function dfs(node, n) {
    if(n == 3) return;

    for(let next_node of graph[node]) {
        visited[node] = true;
        dfs(next_node, n + 1);
    }
}

dfs(1, 0);

let ans = 0;
visited.forEach((v) => { if(v) ans++ });
console.log(ans == 0 ? ans : ans - 1);
