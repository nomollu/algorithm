// G4 타임머신
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
[ N, M ] = input[0].split(' ').map((ele) => Number(ele));

// input edges
let edges = [];
for(let i = 1; i <= M; i++) {
    [ n1, n2, w ] = input[i].split(' ').map((ele) => Number(ele));
    edges.push({"from" : n1, "to" : n2, "cost" : w});
}

let distance = new Array(N + 1).fill(Infinity);

function update() {
    let result = false;
    for(let edge of edges) {
        if(distance[edge.from] == Infinity) continue;
        if(distance[edge.to] > distance[edge.from] + edge.cost) {
            distance[edge.to] = distance[edge.from] + edge.cost;
            result = true; // is updated
        }
    }
    return result;
}

distance[1] = 0;
for(let i = 0; i < N - 1; i++) update();

// copy distance array
let ans = Array.from(distance);

if(update()) {
    // has negetive cycle
    console.log(-1);
}
else {
    for(let i = 2; i < ans.length; i++) console.log(ans[i] == Infinity ? -1 : ans[i]);
}
