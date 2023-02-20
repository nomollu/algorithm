var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let N = Number(input[0].split(' ')[0]), K = Number(input[0].split(' ')[1]);


let nNumber = 9; // 해당 자릿수에 해당하는 숫자 갯수들
let digit = 1; // 자릿수
let ans = 0;

for(;K > nNumber * digit; digit++, nNumber *= 10) {
    K -= nNumber * digit
    ans += nNumber;
}

ans += Math.ceil(K / digit);

if(ans > N) console.log(-1);
else console.log(String(ans).charAt((K - 1) % digit));
