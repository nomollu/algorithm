// G4 키 순서
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

[N, M] = input[0].split(' ').map(ele => Number(ele));

let graph = Array.from(Array(N), (v, k) => new Array(N).fill(Infinity));
for(let i = 0; i < N; i++) graph[i][i] = 0;

for(let i = 1; i <= M; i++) {
    [a, b] = input[i].split(' ').map(ele => Number(ele));
    graph[a - 1][b - 1] = 1;
}

for(let k = 0; k < N; k++) {
    for(let i = 0; i < N; i++) {
        for(let j = 0; j < N; j++) {
            graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
        }
    }
}

// print result
let ans = 0;
for(let i = 0; i < N; i++) {
    let tmp = 0;
    for(let j = 0; j < N; j++) {
        if(graph[j][i] != Infinity || graph[i][j] != Infinity) tmp++;
    }
    if(tmp == N) ans++;
}

console.log(ans);