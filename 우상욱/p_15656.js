// S3 N과 M (7)
var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

[N, M] = input[0].split(' ').map(ele => Number(ele));
let numbers = input[1].split(' ').map(ele => Number(ele));
numbers.sort((a, b) => a - b);

function choose(n, res) {
    if(n == M) {
        ans += `${res.trim()}\n`;
        return;
    }

    for(let i = 0; i < numbers.length; i++) {
        choose(n + 1, res + " " + numbers[i]);
    }
}

let ans = "";
choose(0, "");
console.log(ans.trim());