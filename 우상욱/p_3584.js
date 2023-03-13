// G4 가장 가까운 공통 조상
var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

let line = 0;
const T = Number(input[line++]);

for(let t = 0; t < T; t++) {
    let N = Number(input[line++]);
    let tree = new Array(N + 1);

    // input vertex
    for(let i = 0; i < N - 1; i++) {
        [A, B] = input[line++].split(' ').map((ele) => Number(ele));
        tree[B] = A;
    }

    // child to find parent
    [node1, node2] = input[line++].split(' ').map((ele) => Number(ele));

    let par_candidates = {};
    
    // first node
    let cur_node = node1;
    par_candidates[cur_node] = 0;
    while(tree[cur_node]) {
        cur_node = tree[cur_node];
        par_candidates[cur_node] = 0;
    }
    
    // second node
    cur_node = node2;
    if(par_candidates[cur_node] == 0) {
        console.log(cur_node);
        continue;
    }

    while(tree[cur_node]) {
        cur_node = tree[cur_node];
        if(par_candidates[cur_node] == 0) {
            console.log(cur_node);
            break;
        }
    }
}
