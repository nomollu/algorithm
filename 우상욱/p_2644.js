// S2 촌수계산
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

let N = Number(input[0]);
let [A, B] = input[1].split(' ').map(ele => Number(ele));
let M = Number(input[2]);

let dist = Array.from(Array(N + 1), (v, k) => new Array(N + 1).fill(Infinity));
for(let i = 1; i <= N; i++) dist[i][i] = 0;

for(let i = 3; i < 3 + M; i++) {
    [X, Y] = input[i].split(' ').map(ele => Number(ele));

    dist[X][Y] = 1;
    dist[Y][X] = 1;
}

for(let k = 1; k <= N; k++) {
    for(let i = 1; i <= N; i++) {
        for(let j = 1; j <= N; j++) {
            dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        }
    }
}

console.log(dist[A][B] == Infinity? -1 : dist[A][B]);