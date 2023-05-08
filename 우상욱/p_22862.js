// G5 가장 긴 짝수 연속한 부분 수열 (large)
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
[N, K] = input[0].split(' ').map((ele) => Number(ele));

let numbers = input[1].split(' ').map(ele => Number(ele));

let ans = 0;
let left = 0, right = 0;
let cnt_odds = numbers[0] % 2? 1 : 0; // erasable odd count

for(;;) {
    // move right
    while(right < N - 1) {
        if(numbers[right + 1] % 2 == 1 && K <= cnt_odds) break;
        else if (numbers[right + 1] % 2 == 1 && cnt_odds < K) cnt_odds++;

        right++;
    }

    // update answer
    ans = Math.max(ans, right - left + 1 - cnt_odds)
    if(left >= N || right >= N) break;

    // move left
    if(numbers[left] % 2 == 1) cnt_odds--;
    left++;
}

console.log(ans);