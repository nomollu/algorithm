// 소수의 연속합
var fs = require('fs');
var N = Number(fs.readFileSync('input.txt').toString().trim());

const MAX = 4000000;
let isPrime = new Array(MAX + 1).fill(true);
let part_sum = [0]; // 소수들의 부분합

// find prime number
isPrime[0] = isPrime[1] = false;
for(let i = 2; i < Math.sqrt(MAX); i++) {
    if(!isPrime[i]) continue;
    for(let j = i + i; j <= MAX; j += i) isPrime[j] = false;
}

// calculate partial sum
let tmp_sum = 0;
for(let i = 2; i <= MAX; i++) {
    if(isPrime[i]) {
        tmp_sum += i;
        part_sum.push(tmp_sum);
    }
}

let ans = 0;
let left = 0, right = 0; // two pointer

while(right < part_sum.length) {
    let tmp = part_sum[right] - part_sum[left];

    if(tmp == N) {
        ans++; right++;
    } else if(tmp < N) {
        right++;
    } else {
        left++;
    }
}

console.log(ans);

