var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
var N = Number(input[0].split(' ')[0]), M = Number(input[0].split(' ')[1]);

let coins = [0];

// board input
var board = Array.from(Array(N), () => new Array(M));
for(let i = 1; i <= N; i++) {
    let line = input[i];

    for(let j = 0; j < line.length; j++) {
        let block = line.charAt(j);

        if(block == '\r') continue;
        if(block == 'o') coins.push({x : i - 1, y : j});
        board[i - 1][j] = (block == 'o')? '.': block;
    }
}

function isInBoard(coin) {
    return (coin.x < 0 || coin.y < 0 || coin.x >= N || coin.y >= M)? false : true;
}

var visited = {};
function mark_visited(coin_pair) {
    let str = coin_pair[0].x + "_" + coin_pair[0].y + "_" + coin_pair[1].x + "_" + coin_pair[1].y;
    // already visited
    if(visited.hasOwnProperty(str)) {
        return false;
    } else {
        visited[str] = 0;
        return true;
    }
}

const dx = [0, 0, -1, 1];
const dy = [-1, 1, 0, 0];

// BFS
let queue = [coins];
mark_visited([coins[1], coins[2]]);

while(queue.length != 0) {
    let cur_coins = queue.shift();
    let nMovings = cur_coins[0];

    for(let d = 0; d < 4; d++) {
        let coin1_next = {x : cur_coins[1].x + dx[d], y : cur_coins[1].y + dy[d]};
        let coin2_next = {x : cur_coins[2].x + dx[d], y : cur_coins[2].y + dy[d]};

        // block by #
        if(isInBoard(coin1_next) && board[coin1_next.x][coin1_next.y] == '#') coin1_next = cur_coins[1];
        if(isInBoard(coin2_next) && board[coin2_next.x][coin2_next.y] == '#') coin2_next = cur_coins[2];

        // both coin is out
        if(!isInBoard(coin1_next) && !isInBoard(coin2_next)) continue;

        // one of the two is out
        if(isInBoard(coin1_next) !== isInBoard(coin2_next)) {
            console.log(nMovings + 1);
            process.exit();
        }
        // check visited
        if(mark_visited([coin1_next, coin2_next])) queue.push([nMovings + 1, coin1_next, coin2_next]);
    }
}

console.log(-1);