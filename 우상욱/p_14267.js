// G4 회사 문화
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
[ N, M ] = input[0].split(' ').map((ele) => Number(ele));

// parent info
let superior = input[1].split(' ').map((ele) => Number(ele));
superior.unshift(0);

// make tree
let tree = Array.from(new Array(N + 1), (v, idx) => []);
for(let i = 2; i < superior.length; i++) tree[superior[i]].push(i);

// compliments input
let weights = new Array(N + 1).fill(0);
for(let i = 2; i < M + 2; i++) {
    [N, W] = input[i].split(' ').map((ele) => Number(ele));
    weights[N] += W;
}

let tot_weights = new Array(N + 1).fill(0);
function dfs(node, tot_w) {
    tot_weights[node] = tot_w;

    for(let next_node of tree[node]) dfs(next_node, tot_w + weights[next_node]);
}

dfs(1, 0);

// print with format
let ans = "";
for(let i = 1; i < tot_weights.length; i++) ans += `${tot_weights[i]} `;
console.log(ans.trim());