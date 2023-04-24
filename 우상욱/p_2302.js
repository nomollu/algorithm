// S1 극장 좌석
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

let N = Number(input[0]);
let M = Number(input[1]);

// fivonachi
let dp = new Array(N + 1);
dp[0] = dp[1] = 1;
for(let i = 2; i <= N; i++) dp[i] = dp[i - 1] + dp[i - 2];

let ans = 1;
let last_seat = 1;
for(let i = 2; i <= M + 1; i++) {
    let VIP = Number(input[i]);

    ans *= dp[VIP - last_seat];
    last_seat = VIP + 1;
}

// cal rest seats
ans *= dp[N + 1 - last_seat];

console.log(ans);