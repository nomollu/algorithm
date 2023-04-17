// G4 운동
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

[V, E] = input[0].split(' ').map(ele => Number(ele));

let graph = Array.from(new Array(V), (v, k) => new Array(V).fill(Infinity));
// input graph
for(let i = 1; i <= E; i++) {
    [a, b, w] = input[i].split(' ').map(ele => Number(ele));
    graph[a - 1][b - 1] = w;
}

// calculate distance
for(let k = 0; k < V; k++) {
    for(let i = 0; i < V; i++) {
        for(let j = 0; j < V; j++) {
            graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
        }
    }
}

// find answer
let ans = Infinity;
for(let i = 0; i < V; i++) {
    for(let j = 0; j < V; j++) {
        if(graph[i][j] != Infinity && graph[j][i] != Infinity) {
            ans = Math.min(ans, graph[i][j] + graph[j][i]);
        }
    }
}

console.log(ans == Infinity? -1 : ans);
