// G4 이분 그래프
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let T = Number(input[0]);

let l = 1;
for(let t = 0; t < T; t++) {
    [V, E] = input[l++].split(' ').map(ele => Number(ele));

    // input graph
    let graph = Array.from(Array(V + 1), (v, k) => []);
    for(let i = 0; i < E; i++) {
        [n1, n2] = input[l++].split(' ').map(ele => Number(ele));

        graph[n1].push(n2);
        graph[n2].push(n1);
    }

    let group = new Array(V + 1).fill(0); // -1 or 1

    function bfs(N) {
        let q = [N];
        let next_group = 1;
        
        while(q.length != 0) {
            let q_size = q.length;
            for(let i = 0; i < q_size; i++) {
                let cur_node = q[i];
                
                for(let j = 0; j < graph[cur_node].length; j++) {
                    let next_node = graph[cur_node][j];

                    // not bipartite
                    if(group[next_node] == next_group * -1) return false;
                    if(group[next_node] == next_group) continue;

                    group[next_node] = next_group;
                    q.push(next_node);
                }
            }

            q = q.slice(q_size);
            next_group *= -1; // change group
        }
        return true;
    }

    let isBipart = true;
    for(let i = 1; i <= V; i++) {
        // seak all graphs
        if(group[i] == 0) {

            group[i] = -1;
            isBipart = bfs(i);
            if(!isBipart) break;
        }
    }

    console.log(isBipart? 'YES' : 'NO');
}