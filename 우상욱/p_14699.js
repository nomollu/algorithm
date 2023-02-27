// 관악산 등반
var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
[N, M] = input[0].split(' ').map((ele) => Number(ele));
let tmp = input[1].split(' ').map((ele) => Number(ele));
let heights = [0, ...tmp];

// init graph
let graph = new Array(N + 1);
for(let i = 0; i < graph.length; i++) graph[i] = [];

// input adjs
for(let i = 2; i < M + 2; i++) {
    [n1, n2] = input[i].split(' ').map((ele) => Number(ele));

    graph[n1].push(n2);
    graph[n2].push(n1);
}

// sort with heights
let sorted_node = Array.from(Array(N), (_, idx) => idx + 1);
sorted_node.sort((a, b) => heights[b] - heights[a]);

// count maximum visited
let nVisited = new Array(N + 1).fill(1);
for(let node of sorted_node) {
    for(let next_node of graph[node]) {
        // never go down
        if(heights[node] > heights[next_node]) continue;

        nVisited[node] = (nVisited[node] < nVisited[next_node] + 1? nVisited[next_node] + 1 : nVisited[node]);
    }
}

let ans = "";
for(let i = 1; i < nVisited.length; i++) ans += `${nVisited[i]}\n`;

console.log(ans.trim());