// G4 불!
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
[ R, C ] = input[0].split(' ').map((ele) => Number(ele));

// implement queue
class Queue {
    constructor() {
        this.storage = {};
        this.head = 0;
        this.tail = 0;
    }

    push(ele) {
        this.storage[this.tail] = ele;
        this.tail++;
    }

    pop() {
        let removed = this.storage[this.head]
        delete this.storage[this.head]
        this.head++;
        return removed;
    }

    isEmpty() {
        if(this.tail == this.head) return true;
        return false;
    }
}

// init map
let map = Array.from(new Array(R), (v, k) => new Array(C).fill(0));
let visited = Array.from(new Array(R), (v, k) => new Array(C).fill(false));

let fires_q_x = [], fires_q_y = [], fires_q_t = [];
let jihun_q_x = [], jihun_q_y = [], jihun_q_t = [];

// input blocks
for(let i = 1; i <= R; i++) {
    let line = input[i].trim();
    for(let j = 0; j < line.length; j++) {
        let block = line.charAt(j);

        if(block == 'J') {
            // start point
            visited[i - 1][j] = true; 
            jihun_q_x.push(i - 1);
            jihun_q_y.push(j);
            jihun_q_t.push(0);

            block = Infinity;
        } else if(block == 'F') {
            // fired point
            fires_q_x.push(i - 1);
            fires_q_y.push(j);
            fires_q_t.push(0);

            block = '#';
        } else if(block == '.') {
            // normal path
            block = Infinity;
        }

        map[i - 1][j] = block;
    }
}

const dx = [0, 0, -1, 1];
const dy = [-1, 1, 0, 0];

// spread fire
while(fires_q_x.length) {
    // let tmp_fire = fires_q.pop();
    let x = fires_q_x.shift();
    let y = fires_q_y.shift();
    let t = fires_q_t.shift();

    for(let d = 0; d < 4; d++) {
        let nx = x + dx[d];
        let ny = y + dy[d];

        if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue; // out of range
        if(map[nx][ny] == '#') continue; // meet wall
        if(map[nx][ny] <= t) continue; // visited

        map[nx][ny] = t + 1;
        fires_q_x.push(nx);
        fires_q_y.push(ny);
        fires_q_t.push(t + 1);
    }
}

// escape
while(jihun_q_x.length) {
    // let tmp_jihun = jihun_q.pop();
    let x = jihun_q_x.shift();
    let y = jihun_q_y.shift();
    let t = jihun_q_t.shift();

    for(let d = 0; d < 4; d++) {
        let nx = x + dx[d];
        let ny = y + dy[d];

        if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
            // escape success
            console.log(t + 1);
            process.exit();
        }
        if(map[nx][ny] == '#') continue;
        if(visited[nx][ny]) continue;
        if(map[nx][ny] <= t + 1) continue;
        

        visited[nx][ny] = true;
        jihun_q_x.push(nx);
        jihun_q_y.push(ny);
        jihun_q_t.push(t + 1);

    }
}

console.log('IMPOSSIBLE');