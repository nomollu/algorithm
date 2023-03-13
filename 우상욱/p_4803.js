// G4 트리
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let line = 0, tc = 1;
let ans = "";

for(;; tc++) {
    [N, M] = input[line++].split(' ').map(ele => Number(ele));
    if(N == 0 && M == 0) {
        console.log(ans.trim());
        process.exit();
    }

    let graph = Array.from(Array(N + 1), (v, k) => []);

    // input edges
    for(let i = 0; i < M; i++) {
        [n1, n2] = input[line++].split(' ').map(ele => Number(ele));
        graph[n1].push(n2);
        graph[n2].push(n1);
    }
    
    let nTree = 0, isTree = true; 
    let visited = new Array(N + 1).fill(false);
    
    function dfs(n, prev) {
        for(let i = 0; i < graph[n].length; i++) {
            let next_n = graph[n][i];

            if(next_n == prev) continue; // prevent inf loop
            if(visited[next_n]) {
                // find cycle
                isTree = false;
                continue;
            }

            visited[next_n] = true;
            dfs(next_n, n);
        }
    }

    // find tree within all nodes
    for(let i = 1; i <= N; i++) {
        if(visited[i]) continue;

        isTree = true;
        visited[i] = true;

        dfs(i, -1);
        if(isTree) nTree++;
    }

    // print format
    ans += `Case ${tc}: `;
    if(nTree > 1) ans +=`A forest of ${nTree} trees.\n`;
    else if(nTree == 1) ans += "There is one tree.\n";
    else ans += "No trees.\n";
}