var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let N = Number(input[0]);

// initial
let dp = Array.from(Array(10), () => Array(N + 1).fill(0));
for(let i = 0; i < 10; i++) dp[i][1] = 1;

const MOD = 10007;
for(let j = 1; j < N; j++) {
    for(let i = 0; i < 10; i++) {
        for(let k = i; k < 10; k++) {
            dp[k][j + 1] = (dp[k][j + 1] + dp[i][j]) % MOD;
        }
    }
}

// print ans
let ans = 0;
for(let i = 0; i < 10; i++) ans = (ans + dp[i][N]) % MOD;

console.log(ans);