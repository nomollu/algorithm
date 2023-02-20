// 0 만들기
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split("\n");

let T = Number(input[0]);
let N = 0;
let zeros;
let ans = "";

for(let t = 1; t <= T; t++) {
    zeros = [];
    N = Number(input[t]);
    
    make_0("1", 1, 1);

    // for format
    zeros.sort();
    zeros.forEach((ele) => {ans += `${ele}\n`});
    ans += "\n";
}

console.log(ans.trim());

function make_0(proc = "", num, res) {
    // escape condition
    if(num == N) {
        if(res == 0) zeros.push(proc);
        return;
    }

    // plus
    make_0(proc + `+${num + 1}`, num + 1, res + (num + 1));
    // minus
    make_0(proc + `-${num + 1}`, num + 1, res - (num + 1));
    // space
    make_0(proc + ` ${num + 1}`, num + 1, calculate_space(proc, num + 1));
}

function calculate_space(proc, num) {
    let space_removed = proc.replace(/\s/gi, "") + String(num); // remove space
    let nums = space_removed.split(/\-|\+/).map((ele) => Number(ele));
    let signs = space_removed.split(/\d+/);

    for(let i = 0; i < nums.length - 1; i++) {
        if(signs[i + 1] == "-") nums[i + 1] = nums[i] - nums[i + 1];
        else nums[i + 1] = nums[i] + nums[i + 1];
    }

    return nums[nums.length - 1];
}