var fs = require('fs'); 
let input = fs.readFileSync('input.txt').toString().split('\n');

let N = Number(input[0]);

// make empty graph
let rooms = [];
for(let i = 0; i <= N; i++) rooms.push([]);

// add edges
for(let i = 1; i <= N - 1; i++) {
    let line = input[i].split(' ');
    let A = Number(line[0]), B = Number(line[1]), C = Number(line[2]);

    rooms[A].push({to : B, weight : C});
    rooms[B].push({to : A, weight : C});
}

let visited = new Array(N + 1).fill(-1);
let queue = [ 1 ];
visited[1] = 0;

// BFS
while(queue.length != 0) {
    let popped = queue.shift();

    for(let room of rooms[popped]) {
        let next = room.to;

        if(visited[next] == -1) {
            visited[next] = visited[popped] + room.weight;
            queue.push(next);
        }
    }
}

let ans = 0;
for(let tmp of visited) ans = tmp > ans ? tmp : ans;
console.log(ans);
