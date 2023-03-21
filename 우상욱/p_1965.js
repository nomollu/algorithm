// S2 상자넣기
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let N = Number(input[0]);
let boxes = input[1].split(' ').map(ele => Number(ele));

let dp = new Array(N).fill(0);
for(let i = 1; i < N; i++) {
    for(let j = 0; j < i; j++) {
        if(boxes[j] < boxes[i]) {
            dp[i] = Math.max(dp[i], dp[j] + 1);
        }
    }
}

// find max
let ans = 0;
dp.forEach(ele => { ans = Math.max(ans, ele) });
console.log(ans + 1);