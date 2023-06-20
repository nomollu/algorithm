// G5 선발 명단
var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

let line = 0;
let C = Number(input[line++]);
for(let t = 0; t < C; t++) {
    let cases = Array.from(Array(11), (v, k) => []);

    for(let i = 1; i <= 11; i++) {
        cases[i - 1] = input[line++].split(' ').map((ele) => Number(ele));
    }

    let sum = 0, res = 0;

    function choose(n, picked) {
        if(n == 11) {
            res = Math.max(res, sum);
            return;
        }

        for(let i = 0; i < 11; i++) {
            if(picked[i] == '1') continue;
            if(cases[n][i] == 0) continue;

            sum += cases[n][i];

            choose(n + 1, picked.substring(0, i) + '1' + picked.substring(i + 1));
            
            sum -= cases[n][i];
        }
    }

    choose(0, "00000000000");
    console.log(res);
}
