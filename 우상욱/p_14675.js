var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
var N = Number(input[0]);

let graph = new Array(N + 1).fill(0);

// graph info input
for(let i = 1; i < N; i++) {
    let from = Number(input[i].split(' ')[0]);
    let to = Number(input[i].split(' ')[1]);

    graph[from]++;
    graph[to]++;
}

let ans = "";

// question input
let q = Number(input[N]);
for(let i = N + 1; i <= N + q; i++) {
    let t = Number(input[i].split(' ')[0]);
    let k = Number(input[i].split(' ')[1]);

    if(t == 2) {
        ans += "yes\n"
        continue;
    }

    if(graph[k] >= 2) ans += "yes\n"
    else ans += "no\n"
}

console.log(ans);