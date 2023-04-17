var input = [];
var reader = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
});

reader.on('line', (line) => {
    input.push(line);
});

function getDistance(x1, y1, x2, y2) {
    return Math.abs(x1 - x2) + Math.abs(y1 - y2);
}

class Runner {
    constructor(x, y) {
        this.x = x;
        this.y = y;
        this.arrived = false;
        this.rotated_x = -1;
        this.rotated_y = -1;
    }

    setPos(x, y) {
        this.x = x;
        this.y = y;
    }

    moveToExit(map, exit) {
        let [prev_x, prev_y] = [this.x, this.y];

        // up - down first
        if(this.x < exit.x && map[this.x + 1][this.y] <= 0) this.x++;
        else if(this.x > exit.x && map[this.x - 1][this.y] <= 0) this.x--;
        else if(this.y < exit.y && map[this.x][this.y + 1] <= 0) this.y++;
        else if(this.y > exit.y && map[this.x][this.y - 1] <= 0) this.y--;
        else return false; 

        // update map
        map[prev_x][prev_y]++;

        if(this.x == exit.x && this.y == exit.y) this.arrived = true;
        else map[this.x][this.y]--;  

        return true;
    }
}

class Exit {
    constructor(x, y) {
        this.x = x;
        this.y = y;
        this.rotated_x = -1;
        this.rotated_y = -1;
    }

    setPos(x, y) {
        this.x = x;
        this.y = y;
    }

    findSqureToRotate(runners) {
        for(let size = 1; size <= 100; size++) {
            for(let i = this.x - size; i <= this.x; i++) {
                for(let j = this.y - size; j <= this.y; j++) {
                    if(i < 0 || j < 0 || i >= map.length || j >= map.length) continue;
    
                    // check runners
                    for(let k = 0; k < runners.length; k++) {
                        let runner = runners[k];
    
                        if(i <= runner.x && runner.x <= i + size
                            && j <= runner.y && runner.y <= j + size) {
                            return [i, j, size];
                        }
                    }
                }
            }
        }
    }
}

function rotate(arr, x, y, size) {
    let copy_arr = Array.from(Array(arr.length), (v, k) => []);
    let rotated_arr = Array.from(Array(size), (v, k) => []);

    for(let i = 0; i < arr.length; i++) {
        for(let j = 0; j < arr[0].length; j++) {
            copy_arr[i][j] = arr[i][j];
        }
    }

    for(let i = 0; i < size; i++) {
        for(let j = 0; j < size; j++) {
            rotated_arr[j][size - i - 1] = [i + x, j + y];
        }
    }

    for(let i = x; i < x + size; i++) {
        for(let j = y; j < y + size; j++) {
            let next_idx_x = rotated_arr[i - x][j - y][0];
            let next_idx_y = rotated_arr[i - x][j - y][1];

            // rotate exit
            if(next_idx_x == exit.x && next_idx_y == exit.y) {
                exit.rotated_x = i;
                exit.rotated_y = j;
            }

            // rotate runner
            for(let k = 0; k < runners.length; k++) {
                if(next_idx_x == runners[k].x && next_idx_y == runners[k].y) {
                    runners[k].rotated_x = i;
                    runners[k].rotated_y = j;
                }
            }
            
            arr[i][j] = copy_arr[next_idx_x][next_idx_y];
            if(arr[i][j] > 0) arr[i][j]--;
        }
    }

    // update exit
    if(exit.rotated_x != -1 || exit.rotated_y != -1) {
        exit.x = exit.rotated_x;
        exit.y = exit.rotated_y;

        exit.rotated_x = -1;
        exit.rotated_y = -1;
    }

    // update runner
    for(let i = 0; i < runners.length; i++) {
        if(runners[i].rotated_x != -1 || runners[i].rotated_y != -1) {
            runners[i].x = runners[i].rotated_x;
            runners[i].y = runners[i].rotated_y;

            runners[i].rotated_x = -1;
            runners[i].rotated_y = -1;
        }
    }
}

var map = [];
var runners = [];
var exit;

reader.on('close', () => {
    [N, M, K] = input[0].split(' ').map(ele => Number(ele));

    // input map
    map = Array.from(Array(N), (v, k) => []);
    for(let i = 1; i <= N; i++) {
        input[i].split(' ').map((ele) => {map[i - 1].push(Number(ele));})
    }

    // input runners
    for(let i = N + 1; i <= N + M; i++) {
        [x, y] = input[i].split(' ').map(ele => Number(ele));
        runners.push(new Runner(x - 1, y - 1));
        map[x - 1][y - 1]--;
    }

    // input exit
    [x, y] = input[N + M + 1].split(' ').map(ele => Number(ele));
    exit = new Exit(x - 1, y - 1);

    let move_cnt = 0;
    while(K--) {
        // move runner
        for(let i = 0; i < runners.length; i++) {
            if(runners[i].moveToExit(map, exit)) move_cnt++;
        }

        // erase arrived runner
        let tmp_runner = [];
        for(let i = 0; i < runners.length; i++) {
            if(!runners[i].arrived) tmp_runner.push(runners[i]);
        }
        runners = tmp_runner;

        // all escaped
        if(runners.length == 0) break;

        // find squre to Rotate
        [rs_x, rs_y, size] = exit.findSqureToRotate(runners);

        // rotate
        rotate(map, rs_x, rs_y, size + 1);
    }

    console.log(move_cnt);
    console.log(exit.x + 1, exit.y + 1);

    reader.close();
    process.exit();
});