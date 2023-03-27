// G4 사촌
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let ans = "";
let l = 0;

for(;;) {
    [N, K] = input[l++].split(' ').map(ele => Number(ele));

    // escape condition
    if(N == 0 && K == 0) break;

    // node input
    let nodes = input[l++].split(' ').map(ele => Number(ele));

    // find target index
    let target = -1;
    for(let i = 0; i < nodes.length;i++) if(K == nodes[i]) target = i;

    // define tree
    let par = new Array(N);
    par[0] = -1;

    // make tree
    let cur_par = 0;
    for(let i = 1; i < nodes.length; i++) {
        par[i] = cur_par;

        if(nodes[i] + 1 == nodes[i + 1]); // do nothing
        else cur_par++;
    }

    function Parent(n) {
        if(n == 0) return -1;
        return par[n];
    }

    function GrandParent(n) {
        if(n == 0 || par[n] == 0) return -1;
        return par[par[n]];
    }

    let target_par = Parent(target);
    let target_grand_par = GrandParent(target);
    if(target_par == -1 || target_grand_par == -1) {
        // exception handle
        ans += "0\n";
        continue;
    }

    // check all node
    let cnt = 0;
    for(let i = 0; i < N; i++) {
        if(Parent(i) != target_par && GrandParent(i) == target_grand_par) cnt++;
    }

    ans += `${cnt}\n`;
}

console.log(ans.trim());