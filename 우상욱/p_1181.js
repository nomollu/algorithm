// S5 단어 정렬
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let N = Number(input[0]);

let words = {};
for(let i = 1; i <= N; i++) words[input[i].trim()] = 0;

let ans = "";
for(let word of Object.keys(words).sort((a, b) => {
    if(a.length == b.length) return a > b ? 1 : -1;
    return a.length - b.length;
})) ans += `${word}\n`;
console.log(ans);