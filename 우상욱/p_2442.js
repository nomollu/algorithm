// B3 별 찍기 - 5
var fs = require('fs');
var N = Number(fs.readFileSync('input.txt').toString());

let stars = Array.from(Array(N), (v, k) => new Array(2 * N - 1).fill('*'));

for(let i = 0; i < N; i++) {
    for(let j = 0; j < i; j++) {
        stars[i][0 + j] = ' ';
        stars[i][stars[i].length - 1 - j] = ' ';
    }
}

// print with format
let ans = "";
for(let i = stars.length - 1; i >= 0; i--) {
    for(let j = 0; j < stars[0].length; j++) {
        if(j > N - 1 && stars[i][j] == ' ') continue;
        ans += stars[i][j];        
    }   
    ans += '\n';
}
console.log(ans);
