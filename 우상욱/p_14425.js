var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let N = Number(input[0].split(' ')[0]), M = Number(input[0].split(' ')[1]);

let set = {};
let i = 1;
let ans = 0;

// add to set
for(; i <= N; i++) set[input[i]] = "";

// check set
for(; i <= M + N; i++) if(set.hasOwnProperty(input[i])) ans++;

console.log(ans);
