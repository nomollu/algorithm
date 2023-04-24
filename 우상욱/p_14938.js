// G4 서강 그라운드
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

[N, M, R] = input[0].split(' ').map(ele => Number(ele));

let nItems = input[1].split(' ').map(ele => Number(ele));

let graph = Array.from(Array(N), (v, k) => new Array(N).fill(Infinity));
for(let i = 0; i < N; i++) graph[i][i] = 0;

for(let i = 2; i < R + 2; i++) {
    [a, b, l] = input[i].split(' ').map(ele => Number(ele));

    graph[a - 1][b - 1] = l;
    graph[b - 1][a - 1] = l;
}

// floyd
for(let k = 0; k < N; k++) {
    for(let i = 0; i < N; i++) {
        for(let j = 0; j < N; j++) {
            graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
        }
    }
}

let ans = 0;
for(let i = 0; i < N; i++) {
    let tmp_total = 0;
    for(let j = 0; j < N; j++) {
        if(graph[i][j] <= M) tmp_total += nItems[j];
    }

    ans = Math.max(ans, tmp_total);
}

console.log(ans);