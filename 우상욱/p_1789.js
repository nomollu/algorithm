// S2 종이의 개수
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let N = Number(input[0]);

let papers = Array.from(new Array(N), (v, k) => []);
for(let i = 1; i <= N; i++) {
    papers[i - 1] = input[i].trim().split(' ').map(ele => Number(ele));
}

let counter = {
    "0": 0,
    "1": 0,
    "-1": 0,
}

function countPaper(x, y, size) {
    if(size == 1) {
        counter[papers[x][y]]++;
        return;
    }

    let tmp_cnt = {};
    for(let i = x; i < x + size; i++) {
        for(let j = y; j < y + size; j++) {
            tmp_cnt[papers[i][j]] = 0;
        }
    }
    if(Object.keys(tmp_cnt).length == 1) {
        counter[papers[x][y]]++;
        return;
    }

    let next_size = size / 3;

    let x1 = x + next_size;
    let x2 = x + next_size + next_size;

    let y1 = y + next_size;
    let y2 = y + next_size + next_size;
    
    countPaper(x, y, next_size);
    countPaper(x, y1, next_size);
    countPaper(x, y2, next_size);

    countPaper(x1, y, next_size);
    countPaper(x1, y1, next_size);
    countPaper(x1, y2, next_size);

    countPaper(x2, y, next_size);
    countPaper(x2, y1, next_size);
    countPaper(x2, y2, next_size);
}

countPaper(0, 0, N);

console.log(`${counter["-1"]}\n${counter["0"]}\n${counter["1"]}`);