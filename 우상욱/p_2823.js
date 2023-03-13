// S2 유턴 싫어
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
[R, C] = input[0].split(' ').map(ele => Number(ele));

// input map
let map = Array.from(Array(R), (v, k) => new Array(C));
for(let i = 1; i <= R; i++) {
    let line = input[i].trim();
    for(let j = 0; j < line.length; j++) map[i - 1][j] = line.charAt(j);
}


function isDead(i, j) {
    if(map[i][j] == 'X') return false;

    const dx = [0, 0, 1, -1];
    const dy = [-1, 1, 0, 0];

    let cnt = 0;
    for(let d = 0; d < 4; d++) {
        let next_x = i + dx[d];
        let next_y = j + dy[d];

        if(next_x < 0 || next_y < 0 || next_x >= R || next_y >= C) continue;
        if(map[next_x][next_y] == '.') cnt++;
    }

    if(cnt <= 1) return true;
    else return false;
}

for(let i = 0; i < R; i++) {
    for(let j = 0; j < C; j++) {
        if(isDead(i, j)) {
            console.log(1);
            process.exit();
        }
    }
}

console.log(0);