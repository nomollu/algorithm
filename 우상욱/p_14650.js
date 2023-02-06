var fs = require('fs'); 
let input = fs.readFileSync('input.txt').toString().trim().split('\n');
var N = Number(input[0]);

var ans = 0;
function comb(n, one, two) {
    if(n == N) {
        // multiply of 3
        if((one + two * 2) % 3 == 0) ans += 1;
        return;
    }

    comb(n + 1, one + 1, two);
    comb(n + 1, one, two + 1);
    if(n + 1 != N) comb(n + 1, one, two);
}

comb(0, 0, 0);

console.log(ans);