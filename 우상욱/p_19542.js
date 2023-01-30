var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

var N = Number(input[0].split(' ')[0]);
var S = Number(input[0].split(' ')[1]);
var D = Number(input[0].split(' ')[2]);

let tree = [];
for(let i = 0; i <= N; i++) tree.push([]);

// tree input
for(let i = 1; i < N; i++) {
    let from = Number(input[i].split(' ')[0]);
    let to = Number(input[i].split(' ')[1]);

    tree[from].push(to);
    tree[to].push(from);
}

var visited = new Array(N + 1).fill(0);
var leaf_dis = new Array(N + 1).fill(0); // maximum distance to leaf

function dfs(cur) {
    for(let next of tree[cur]) {
        if(visited[next] == 1) continue;

        visited[next] = 1;
        leaf_dis[cur] = Math.max(leaf_dis[cur], dfs(next));
    }

    return leaf_dis[cur] + 1;
}

visited[S] = 1;
dfs(S);

// check distance
let ans = leaf_dis.reduce((arr, cur, idx) => (cur >= D && idx != 0 ? arr + 1 : arr));
console.log(Math.max(0, (ans - 1) * 2));
