// G5 호석이 두 마리 치킨
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

[N, M] = input[0].split(' ').map(ele => Number(ele));

// init graph
let graph = Array.from(Array(N), (v, k) => new Array(N).fill(Infinity));
for(let i = 0; i < N; i++) graph[i][i] = 0;

// input edges
for(let i = 1; i <= M; i++) {
    [A, B] = input[i].split(' ').map(ele => Number(ele));

    graph[A - 1][B - 1] = 1;
    graph[B - 1][A - 1] = 1;
}

// floyd
for(let k = 0; k < N; k++) {
    for(let i = 0; i < N; i++) {
        for(let j = 0; j < N; j++) {
            graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
        }
    }            
}

// find min
let min_time = Infinity;
for(let i = 0; i < N - 1; i++) {
    for(let j = i; j < N; j++) {
        let tmp_total = 0;
        for(let k = 0; k < N; k++) {
            tmp_total += Math.min(graph[i][k] * 2, graph[j][k] * 2);
        }

        min_time = Math.min(min_time, tmp_total);
    }
}

// find chiken
for(let i = 0; i < N - 1; i++) {
    for(let j = i; j < N; j++) {
        let tmp_total = 0;
        for(let k = 0; k < N; k++) {
            tmp_total += Math.min(graph[i][k] * 2, graph[j][k] * 2);
        }

        if(tmp_total == min_time) {
            console.log(i + 1, j + 1, tmp_total);
            process.exit();
        }
    }
}