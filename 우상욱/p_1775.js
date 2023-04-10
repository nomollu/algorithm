// G3 우주신과의 교감
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

[N, M] = input[0].split(' ').map(ele => Number(ele));

class UnionFind {
    constructor(n) {
        this.parent = new Array(n).fill().map((v, i) => i);
        this.rank = new Array(n).fill(0);
    }

    find(x) {
        if(this.parent[x] == x) return x;
        else return this.find(this.parent[x]);
    }

    union(x, y) {
        let x_root = this.find(x);
        let y_root = this.find(y);

        if(x_root == y_root) return;

        if(this.rank[x_root] < this.rank[y_root]) {
            this.parent[x_root] = y_root;
        } else if(this.rank[x_root] > this.rank[y_root]) {
            this.parent[y_root] = x_root;
        } else {
            this.parent[y_root] = x_root;
            this.rank[x_root] += 1;
        }
    }
}

class Position {
    constructor(x, y) {
        this.x = x;
        this.y = y;
    }
}

function getDistance(pos1, pos2) {
    return Math.sqrt((pos1.x - pos2.x) * (pos1.x - pos2.x) + (pos1.y - pos2.y) * (pos1.y - pos2.y));
}

// input axis
let planets = [new Position(0, 0)];
for(let i = 1; i <= N; i++) {
    [x, y] = input[i].split(' ').map(ele => Number(ele));
    planets.push(new Position(x, y));
}

let distances = Array.from(new Array(N + 1), (v, k) => new Array(N + 1).fill(-1));
for(let i = 1; i <= N; i++) {
    for(let j = 1; j <= N; j++) {
        if(i == j) continue;
        distances[i][j] = distances[j][i] = getDistance(planets[i], planets[j]);
    }
}

// input edges
for(let i = N + 1; i <= N + M; i++) {
    [p1, p2] = input[i].split(' ').map(ele => Number(ele));
    distances[p1][p2] = distances[p2][p1] = 0;
}

class Edge {
    constructor(x, y, w) {
        this.from = x; 
        this.to = y;
        this.w = w;
    }
}

let edges = [];
for(let i = 1; i <= N; i++) {
    for(let j = 1; j <= N; j++) {
        if(i == j) continue;
        edges.push(new Edge(i, j, distances[i][j]));
    }
}
edges.sort((a, b) => a.w - b.w);

// kruskal
let uf = new UnionFind(N + 1);

let ans = 0;
for(let e of edges) {
    [u, v, w] = [e.from, e.to, e.w];

    if(uf.find(u) != uf.find(v)) {
        uf.union(u, v);
        ans += w;
    }
}

console.log(ans.toFixed(2));