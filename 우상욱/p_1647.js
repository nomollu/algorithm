// G4 도시 분할 계획
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
[ N, M ] = input[0].split(' ').map((ele) => Number(ele));

// input edges
let edges = [];
for(let i = 1; i <= M; i++) {
    [ n1, n2, w ] = input[i].split(' ').map((ele) => Number(ele));
    edges.push({"n1" : n1, "n2" : n2, "w" : w});
}

// for Union-find algo
let par = Array.from(new Array(N + 1), (v, idx) => idx);

function find(n) {
    if(n == par[n]) return n;
    return par[n] = find(par[n]);
}

function union(n1, n2) {
    let p1 = find(n1);
    let p2 = find(n2);

    if(p1 != p2) par[p2] = p1;
}

// sort by weight
edges.sort((a, b) => a.w - b.w);
let nRoad = 0, ans = 0;

// make MST
for(let edge of edges) {
    if(nRoad == N - 2) break;
    if(find(edge.n1) == find(edge.n2)) continue;
    
    // use road
    nRoad += 1; 
    ans += edge.w;

    // union
    union(edge.n1, edge.n2);
}

console.log(ans);
