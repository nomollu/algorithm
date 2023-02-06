var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

let l = 0;
let T = Number(input[l++]);

while(T--) {
    let N = Number(input[l++]);
    let chapters = input[l++].split(' ').map((e) => Number(e));

    // calculate sum of cost
    let costs = Array.from(Array(N), () => Array(N).fill(0));
    for(let i = 0; i < N; i++) {
        for(let j = i; j < N; j++) {
            if(i == j) { 
                costs[i][j] = chapters[i];
                continue;
            }
            costs[i][j] = costs[i][j - 1] + chapters[j];
        }
    }
    
    let dp = Array.from(Array(N), () => Array(N).fill(Infinity));
    // only one chapters    
    for(let i = 0; i < N; i++) dp[i][i] = 0;
    
    for(let diff = 1; diff < N; diff++) {
        for(let i = 0; i < N; i++) {
            if(i + diff >= N) continue; // out of bound

            let j = i + diff;
            for(let d = 0; d < diff; d++) {
                let x1 = i, y1 = i + d;
                let x2 = i + d + 1, y2 = j;

                dp[i][j] = Math.min(dp[i][j], dp[x1][y1] + dp[x2][y2] + costs[x1][y1] + costs[x2][y2]);
            }
        }
    }

    console.log(dp[0][N - 1]);
}