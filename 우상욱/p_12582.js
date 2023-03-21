// S1 1로 만들기 2
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let N = Number(input[0]);

let dp = new Array(N + 1).fill(Infinity);
dp[N] = 0;

for(let n = N - 1; n > 0; n--) {
    if(n * 3 <= N) {
        dp[n] = Math.min(dp[n], Math.min(dp[n * 3], dp[n * 2], dp[n + 1]) + 1);
    } else if(n * 2 <= N) {
        dp[n] = Math.min(dp[n], Math.min(dp[n * 2], dp[n + 1]) + 1);
    } else {
        dp[n] = Math.min(dp[n], dp[n + 1] + 1);
    }
}

// for print numbers
let ans = "", cur_ea = dp[1];
for(let n = 1; n <= N;) {
    ans += `${n} `;
    cur_ea--; // 남은 연산횟수

    if(n * 3 <= N && dp[n * 3] == cur_ea) n = n * 3;
    else if(n * 2 <= N && dp[n * 2] == cur_ea) n = n * 2;
    else n++;
}

// reverse string
console.log(dp[1]);
console.log(ans.split(' ').reverse().join(' ').trim());