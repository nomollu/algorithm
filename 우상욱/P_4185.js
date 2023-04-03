// G2 친구 네트워크
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let line = 0;

class disjointSet {
    constructor(size) {
        this.parent = new Array(size);
        this.rank = new Array(size);
        this.group = {}

        for(let i = 0; i < size; i++) {
            this.parent[i] = i;
            this.rank[i] = 0;
            this.group[i] = 1;
        }
    }

    union(x, y) {
        let rootX = this.find(x);
        let rootY = this.find(y);

        if(rootX == rootY) return this.group[rootY];

        // y must be got higher rank
        if(this.rank[rootX] > this.rank[rootY]) [rootX, rootY] = [rootY, rootX];
        this.parent[rootX] = rootY; 

        if(this.rank[rootX] === this.rank[rootY]) this.rank[rootY]++;

        // update groups
        this.group[rootY] += this.group[rootX];
        delete this.group[rootX];

        return this.group[rootY];
    }

    find(x) {
        if(this.parent[x] !== x) this.parent[x] = this.find(this.parent[x]);
        return this.parent[x];
    }
}

let ans = "";
let T = Number(input[line++]);
while(T--) {
    let F = Number(input[line++]);

    let friends = {};
    let tmp = 0, tmp_line = line;
    for(let i = 0; i < F; i++) {
        [f1, f2] = input[line++].trim().split(' ');

        if(!friends.hasOwnProperty(f1)) friends[f1] = tmp++;
        if(!friends.hasOwnProperty(f2)) friends[f2] = tmp++;
    }

    let disjoint_set = new disjointSet(tmp);

    line = tmp_line;
    for(let i = 0; i < F; i++) {
        [f1, f2] = input[line++].trim().split(' ');

        ans += `${disjoint_set.union(friends[f1], friends[f2])}\n`;
    }
}

console.log(ans);