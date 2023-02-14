var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let N = Number(input[0].split(' ')[0]), M = Number(input[0].split(' ')[1]);

let lect = [];
for(let i = 1; i <= N; i++) {
    // make lectangle
    let line = input[i], tmp = []
    for(let j = 0; j < line.length; j++) {
        if(line.charAt(j) != '\r') {
            tmp.push(Number(line.charAt(j)));
        }
    }

    lect.push(tmp);
}

let min_side = Math.min(N, M);
let ans = 1;

function findSquare(size) {
    // all elements of lect
    for(let i = 0; i < N; i++) {
        for(let j = 0; j < M; j++) {
            if(i + size >= N || j + size >= M) continue;
            if(lect[i][j] == lect[i][j] && lect[i][j] == lect[i + size][j] && lect[i][j] ==  lect[i][j + size] && lect[i][j] == lect[i + size][j + size]) {
                ans = (size + 1) * (size + 1);
                return;
            }
        }
    }
}

// check all size of lectangle
for(let n = 1; n < min_side; n++) findSquare(n);
console.log(ans);