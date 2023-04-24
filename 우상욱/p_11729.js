// S1 하노이 탑 이동순서
var fs = require('fs');
var K = Number(fs.readFileSync('input.txt').toString());

let ans = "", cnt = 0;

function hanoi(k, from, mid, to) {
    if(k == 1) {
        cnt++;
        ans += `${from} ${to}\n`;
        return;
    }

    hanoi(k - 1, from, to, mid);
    cnt++;
    ans += `${from} ${to}\n`;
    hanoi(k - 1, mid, from, to);
}

hanoi(K, 1, 2, 3);

console.log(cnt);
console.log(ans);
