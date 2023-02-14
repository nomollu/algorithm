var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let N = Number(input[0].split(' ')[0]), K = Number(input[0].split(' ')[1]);

let sequence = [];
let table = [];

// init table
for(let i = 1; i <= N; i++) table.push(i);

let idx = K - 1;
while(table.length != 0) {
    sequence.push(table[idx]);
    table.splice(idx, 1); // erase 
    
    idx = (K - 1 + idx) % table.length;
}

// for format
let ans = "<";
for(let i = 0; i < sequence.length - 1; i++) ans += sequence[i] + ", ";
ans += sequence[sequence.length - 1] + ">";

console.log(ans);
