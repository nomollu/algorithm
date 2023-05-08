// S2 원숭이 매달기
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let T = Number(input[0]);

let ans = "";
for(let t = 1; t <= T; t++) {
    let str = input[t].trim();
    let res = 0, stk = [];

    for(let i = 0; i < str.length; i++) {
        if(str.charAt(i) == ']') stk.pop();
        else {
            stk.push('[');
            res = Math.max(res, stk.length);
        }
    }

    ans += `${Math.pow(2, res)}\n`;
}

console.log(ans.trim());