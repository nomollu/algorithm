// G3 택배
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

[N, M] = input[0].split(' ').map(ele => Number(ele));

let graph = Array.from(Array(N + 1), (v, k) => new Array(N + 1).fill(Infinity));
for(let i = 0; i < N + 1; i++) graph[i][i] = 0;

let ans = Array.from(Array(N + 1), (v, k) => new Array(N + 1).fill(-1));
for(let i = 0; i < N + 1; i++) ans[i][i] = '-';

for(let i = 1; i <= M; i++) {
    [a, b, w] = input[i].split(' ').map(ele => Number(ele));
    graph[a][b] = graph[b][a] = w;
    ans[a][b] = b;
    ans[b][a] = a;
}

// floyd warshall
for(let k = 1; k <= N; k++) {
    for(let i = 1; i <= N; i++) {
        for(let j = 1; j <= N; j++) {
            if(graph[i][j] > graph[i][k] + graph[k][j]) {
                graph[i][j] = graph[i][k] + graph[k][j];
                ans[i][j] = ans[i][k];
            }
        }
    }
}

// print answer
let tmp = "";
for(let i = 1; i < N + 1; i++) {
    for(let j = 1; j < N + 1; j++) {
        tmp += `${ans[i][j]} `;
    }
    tmp += '\n';
}
console.log(tmp);