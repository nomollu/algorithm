// G3 시루의 백화점 구경
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

class Queue {
    constructor() {
        this.data = {};
        this.head = 0;
        this.tail = 0;
    }

    enqueue(ele) {
        this.data[this.tail] = ele;
        this.tail++;
    }

    dequeue() {
        let removed = this.data[this.head];
        delete this.data[this.head];
        this.head++;
        return removed;
    }

    size() {
        return Object.keys(this.data).length;
    }
}

[N, M, K] = input[0].trim().split(' ').map(ele => Number(ele));

let start_point = 0;
let mannequin = new Queue();

let map = new Array(N);
for(let i = 1; i <= N; i++) {
    map[i - 1] = input[i].trim().split(' ').map((ele, idx) => {
        let num = Number(ele);
        if(num === 3) mannequin.enqueue([i - 1, idx, K]);
        if(num === 4) start_point = [i - 1, idx];
        return num;
    });
}

function isValid(x, y) {
    if(x < 0 || y < 0 || x >= N || y >= M) return false;
    else return true;
}

const dx = [0, 0, 1, -1];
const dy = [-1, 1, 0, 0];

while(mannequin.size() != 0) {
    [x, y, k] = mannequin.dequeue();
    if(--k < 0) continue;

    for(let d = 0; d < 4; d++) {
        let nx = x + dx[d];
        let ny = y + dy[d];

        if(!isValid(nx, ny)) continue;
        if(map[nx][ny] == 3 || map[nx][ny] == 1) continue;

        map[nx][ny] = 3;
        mannequin.enqueue([nx, ny, k]);
    }
}

let queue = new Queue();
queue.enqueue([start_point[0], start_point[1], 0]);

while(queue.size() != 0) {
    [x, y, k] = queue.dequeue();

    for(let d = 0; d < 4; d++) {
        let nx = x + dx[d];
        let ny = y + dy[d];

        if(!isValid(nx, ny)) continue;
        if(k == 0 && map[nx][ny] == 1 || map[nx][ny] == -1) continue;
        if(k != 0 && map[nx][ny] == 3 || map[nx][ny] == 1 || map[nx][ny] == -1) continue;
        if(map[nx][ny] == 2) {
            console.log(k + 1);
            process.exit();
        }

        map[nx][ny] = -1;
        queue.enqueue([nx, ny, k + 1]);
    }
}

console.log(-1);