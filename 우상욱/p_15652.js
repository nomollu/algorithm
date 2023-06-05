// S3 N과 M (4)
var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

[N, M] = input[0].split(' ').map(ele => Number(ele));

function choose(n, res) {
    if(n == M) {
        let tmp = ""
        for(let k of res)
            tmp += `${k} `;

        ans += `${tmp.trim()}\n`;
        return;
    }

    for(let i = 1; i <= N; i++) {
        let flag = false;
        for(let tmp of res) 
            if(i < tmp) flag = true;
        if(flag) continue;

        choose(n + 1, [...res, i]);
    }
}

let ans = "";
choose(0, []);
console.log(ans);