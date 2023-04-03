// S3 통계학
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let N = Number(input[0]);

let average = 0;
let frequency = {};
let nums = [];

for(let i = 1; i <= N; i++) {
    let n = Number(input[i]);

    average += n;
    nums.push(n);

    if(!frequency.hasOwnProperty(n)) frequency[n] = 0;
    else frequency[n] += 1;
}

nums.sort();

console.log(Math.round(average / N) == -0 ? 0 : Math.round(average / N)); // 1
console.log(nums[Math.floor(N / 2)]); // 2

// 3
let max_freq = 0;
let max_freq_list = [];

for(let num of Object.keys(frequency)) max_freq = Math.max(max_freq, frequency[num]);
for(let num of Object.keys(frequency)) if(frequency[num] == max_freq) max_freq_list.push(Number(num));
max_freq_list.sort((a, b) => a - b);

console.log(max_freq_list.length > 1? max_freq_list[1] : max_freq_list[0]);


console.log(Math.abs(nums[nums.length - 1] - nums[0])); // 4