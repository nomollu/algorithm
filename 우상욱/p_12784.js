// G3 인하니카 공화국
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let T = Number(input[0]);

class Edge {
    constructor(to, weight) {
        this.to = to;
        this.weight = weight
    }
}

let ans = "";
let line = 1;

while(T--) {
    [N, M] = input[line++].trim().split(' ').map(ele => Number(ele));

    // input tree
    let tree = Array.from(new Array(N + 1), (v, k) => []);
    for(let i = 0; i < M; i++) {
        [i1, i2, D] = input[line++].trim().split(' ').map(ele => Number(ele));
        tree[i1].push(new Edge(i2, D));
        tree[i2].push(new Edge(i1, D));
    }

    let visited = new Array(N + 1).fill(false);
    function dfs(n, prev_amount) {
        let tot_amount = 0;
        for(let node of tree[n]) {
            if(visited[node.to]) continue;

            visited[node.to] = true;
            tot_amount += dfs(node.to, node.weight);
        }

        if(tot_amount == 0) return prev_amount;
        else return prev_amount > tot_amount? tot_amount : prev_amount;
    }

    visited[1] = true;
    if(N == 1) ans += "0\n";
    else ans += `${dfs(1, Infinity)}\n`;
}

console.log(ans);