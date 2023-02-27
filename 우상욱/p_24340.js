// 트리 색칠하기
var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

let N = Number(input[0]);
let tmp = input[1].split(' ').map((ele) => Number(ele));
let target_color = [0, ...tmp]

// init tree
let tree = new Array(N + 1);
for(let i = 0; i < tree.length; i++) tree[i] = [];

// input adjs
for(let i = 2; i <= N; i++) {
    [n1, n2] = input[i].split(' ').map((ele) => Number(ele));

    tree[n1].push(n2);
    tree[n2].push(n1);
}

let visited = new Array(N + 1).fill(false);
let ans = 0;

// paint node
function paintNode(node, painting_color) {
    visited[node] = true;

    // need to paint over
    if(target_color[node] != painting_color) {
        painting_color = target_color[node];
        ans++;
    }

    for(let next_node of tree[node]) {
        if(visited[next_node]) continue;
        paintNode(next_node, painting_color);
    }
}

paintNode(1, 0);

console.log(ans);