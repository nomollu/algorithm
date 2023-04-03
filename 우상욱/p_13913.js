// G4 숨바꼭질4
var fs = require('fs');
var [N, K] = fs.readFileSync('input.txt').toString().trim().split(' ').map(ele => Number(ele));

let visited = new Array(100001).fill(-1);
let par = new Array(100001).fill(-1);

let queue = [];
visited[N] = 0;
queue.push(N);

let bfs = (function () {
    while(queue.length != 0) {
        let cur_node = queue.shift();
        let next_nodes = [cur_node + 1, cur_node -1, cur_node * 2];

        for(let next_node of next_nodes) {
            if(visited[next_node] != -1) continue;

            visited[next_node] = visited[cur_node] + 1;
            par[next_node] = cur_node;

            if(next_node == K) return;
            queue.push(next_node);
        }
    }
})

bfs();
console.log(visited[K]);

// find pass
let route = [];
function findRoute(node) {
    route.push(node);

    if(node == N) return;
    else findRoute(par[node]);
}

findRoute(K);

let ans = "";
for(let i = route.length - 1; i >= 0; i--) ans += `${route[i]} `;
console.log(ans.trim());