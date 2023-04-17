// S4 햄버거 사랑
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

[N, M, T] = input[0].split(' ').map(ele => Number(ele));

let ans_ham = 0;
let ans_coke = Infinity;

for(let i = 0; N * i <= T; i++) {
    for(let j = 0; M * j <= T - N * i; j++) {
        let coke_time = T - (N * i + M * j);

        if(coke_time < ans_coke) {
            ans_coke = coke_time;
            ans_ham = i + j;
        } else if(coke_time == ans_coke) {
            ans_ham = Math.max(ans_ham, i + j);
        }
    }
}

console.log(ans_ham, ans_coke);