// S1 부등호
var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

let k = Number(input[0]);
let inequalities = input[1].split(' ');

let min = Infinity;
let max = -1;

function choose(n, visited, picked) {
    if(n == k + 1) {
        min = Math.min(min, Number(picked));
        max = Math.max(max, Number(picked));
        return;
    }

    for(let i = 0; i < 10; i++) {
        if(visited[i] == '1') continue;
        
        if(n > 0) {
            if(inequalities[n - 1] == '<') {
                if(Number(picked[n - 1]) > i) continue;
            } else {
                if(Number(picked[n - 1]) < i) continue;
            }
        }
            
        choose(n + 1, visited.substring(0, i) + '1' + visited.substring(i + 1), picked + i);
    }
}

choose(0, "0000000000", "");
console.log(String(max).padStart(k + 1, '0'));
console.log(String(min).padStart(k + 1, '0'));