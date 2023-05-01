// G4 플로이드
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

let N = Number(input[0]);
let M = Number(input[1]);

let graph = Array.from(Array(N), (v, k) => new Array(N).fill(Infinity));
for(let i = 0; i < N; i++) graph[i][i] = 0;

for(let i = 2; i < M + 2; i++) {
    [a, b, c] = input[i].split(' ').map(ele => Number(ele));
    graph[a - 1][b - 1] = Math.min(graph[a - 1][b - 1], c);
}

// floyd
for(let k = 0; k < N; k++) {
    for(let i = 0; i < N; i++) {
        for(let j = 0; j < N; j++) {
            graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
        }
    }
}

// print result
let ans = "";
for(let i = 0; i < N; i++) {
    for(let j = 0; j < N; j++) {
        ans += `${graph[i][j] == Infinity? 0 : graph[i][j]} `;
    }
    ans += '\n';
}
console.log(ans.trim());