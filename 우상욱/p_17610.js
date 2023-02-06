var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

var k = Number(input[0]);
const weights = input[1].split(' ').map((e) => Number(e)).sort();
const sum = weights.reduce((acc, cur) => acc + cur);

let cases = {}; // save possible weight combination
function comb(cur_val, cur_idx) {
    cases[cur_val] = 0;

    if(cur_idx + 1 >= k) return;
    
    comb(cur_val + weights[cur_idx + 1], cur_idx + 1);
    comb(Math.abs(cur_val - weights[cur_idx + 1]), cur_idx + 1);
    comb(cur_val, cur_idx + 1);
}

comb(0, -1);
console.log(sum - Object.keys(cases).length + 1);