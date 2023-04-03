// G3 나만 안되는 연애
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

class DisjointSet {
    constructor(n) {
        this.parent = new Array(n);
        for (let i = 0; i < n; i++) {
            this.parent[i] = i;
        }
    }
  
    find(x) {
        if (this.parent[x] === x) {
            return x;
        }
        return this.parent[x] = this.find(this.parent[x]);
    }
  
    union(x, y) {
        let px = this.find(x);
        let py = this.find(y);
        this.parent[px] = py;
    }
}

class Edge {
    constructor(u, v, w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

function kruskal(n, edges, kind) {
    edges.sort((a, b) => a.w - b.w);
    let ds = new DisjointSet(n);
    let ans = 0;
    let cnt = 0;

    for(let edge of edges) {
        if(ds.find(edge.u) != ds.find(edge.v)
            && kind[edge.u] != kind[edge.v]) {
            ds.union(edge.u, edge.v);
            ans += edge.w;
            cnt++;
        }
    }

    return [cnt, ans];
}

let line = 0;
let [N, M] = input[line++].split(' ').map(ele => Number(ele));
let univ_kind = input[line++].trim().split(' ');

// input edges
let edges = [];
for(; line <= M + 1; line++) {
    let [u, v, w] = input[line].split(' ').map(ele => Number(ele));
    edges.push(new Edge(u - 1, v - 1, w));
}

let [nEdge, ans] = kruskal(N, edges, univ_kind);
console.log(nEdge != N - 1? -1 : ans);