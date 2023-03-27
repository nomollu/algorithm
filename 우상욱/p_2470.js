// G5 두 용액
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let N = Number(input[0]);
let liquids = input[1].split(' ').map((ele) => Number(ele));

liquids.sort((a, b) => a - b);

let left = 0, right = liquids.length - 1;
let min_sum = Infinity;
let ans_idx = [left, right];

function abs(n) {
    return n < 0? n * -1 : n;
}

while(left < right) {
    let sum = liquids[left] + liquids[right];
    
    // if find better combination
    if(abs(sum) < abs(min_sum)) {
        min_sum = sum;
        ans_idx = [left, right];
    }

    if(sum > 0) right--;
    else left++;
}

console.log(liquids[ans_idx[0]], liquids[ans_idx[1]]);