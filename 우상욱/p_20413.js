var fs = require('fs'); 
let input = fs.readFileSync('input.txt').toString().trim().split('\n');
let N = Number(input[0]);

const boudaries = input[1].split(' ').map((e) => Number(e));
const cutlines = {
    B : boudaries[0] - 1,
    S : boudaries[1] - 1,
    G : boudaries[2] - 1,
    P : boudaries[3] - 1,
    D : boudaries[3]
}

const tiers = input[2];
let prev_cost = 0;
let black_cows_cost = 0;
for(let i = 0; i < tiers.length; i++) {
    if(tiers[i] === 'D')
       black_cows_cost += cutlines.D;
    else {
        // current month cost
        let cur_cost = cutlines[tiers[i]] - prev_cost;

        black_cows_cost += cur_cost;
        prev_cost = cur_cost;
    }
}

console.log(black_cows_cost);
