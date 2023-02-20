// 잃어버린 괄호
var fs = require('fs');
var expression = fs.readFileSync('input.txt').toString().trim();
let tmp = expression.split('-');

// calculate sum
for(let i = 0; i < tmp.length; i++) {
    let sum = 0;
    tmp[i].split('+').map((ele) => { sum += Number(ele)});
    tmp[i] = sum;
}

let ans = tmp[0];
for(let i = 1; i < tmp.length; i++) ans -= tmp[i];

console.log(ans);