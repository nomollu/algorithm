// S5 다리 놓기
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let T = Number(input[0]);

let dp = Array.from(Array(30), (v, k) => new Array(30).fill(-1));

function combination(n, r) {
    if(dp[n][r] > 0) return dp[n][r];

    if(n == r || r == 0) {
        return dp[n][r] = 1;
    } else {
        return dp[n][r] = combination(n - 1, r - 1) + combination(n - 1, r);
    }
}

for(let i = 0, line = 1; i < T; i++) {
    [r, n] = input[line++].split(' ').map(ele => Number(ele));
    console.log(combination(n, r));
}