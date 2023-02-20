// 주유소
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split("\n");

let distances = input[1].split(' ').map((ele) => BigInt(ele));
let gas_costs = input[2].split(' ').map((ele) => BigInt(ele));

let ans = BigInt(0);
let min_gas_cost = gas_costs[0];

for(let cur_pos = 0; cur_pos < gas_costs.length - 1; cur_pos++) {
    // move gas station
    ans += BigInt(min_gas_cost) * BigInt(distances[cur_pos]);

    min_gas_cost = gas_costs[cur_pos + 1] < min_gas_cost? gas_costs[cur_pos + 1] : min_gas_cost;
}

console.log(String(ans));
