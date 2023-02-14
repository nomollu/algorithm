var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let R = Number(input[0].split(' ')[0])
let C = Number(input[0].split(' ')[1])
let K = Number(input[0].split(' ')[2]);

// input map
let map = Array.from(Array(R), () => Array(C).fill(0));
for(let r = 1; r <= R; r++) {
    let line = input[r].trim();

    for(let i = 0; i < line.length; i++) {
        map[r - 1][i] = line.charAt(i);
    }
}

let visited = Array.from(Array(R), () => Array(C).fill(false));
let dx = [0, 0, -1, 1];
let dy = [-1, 1, 0, 0];
let ans = 0;

function backtrack(x, y, n) {
    if(x == 0 && y == C - 1 && n == K) {
        ans++;
        return;
    }

    for(let d = 0; d < 4; d++) {
        let nx = x + dx[d];
        let ny = y + dy[d];

        if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue; // out of bound
        if(map[nx][ny] != '.') continue; // block by wall
        if(visited[nx][ny]) continue; // already visited

        visited[nx][ny] = true;
        backtrack(nx, ny, n + 1);
        visited[nx][ny] = false;
    }
}

visited[R - 1][0] = true;
backtrack(R - 1, 0, 1);

console.log(ans);