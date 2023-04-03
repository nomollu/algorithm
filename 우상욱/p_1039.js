// G3 교환
var fs = require('fs');
[N, K] = fs.readFileSync('input.txt').toString().trim().split(' ');

let numbers = {};
numbers[N] = 0; // '숫자' : 바꾼 횟수

function swapNum(num, i, j) {
    let arr = num.split("");

    arr[i] = num.charAt(j);
    arr[j] = num.charAt(i);

    return arr.join("");
}

class Num {
    constructor(n, k) {
        this.n = n;
        this.k = k;
    }
}

for(let k = 0; k < K; k++) {
    // saving 'k' step numbers to swap
    let tmp_nums = [];
    for(let num in numbers) {
        if(numbers[num] == k) {
            tmp_nums.push(new Num(num, numbers[num]));
        }
    }

    // swap all number
    for(let num of tmp_nums) {
        for(let i = 0; i < num.n.length - 1; i++) {
            for(let j = i + 1; j < num.n.length; j++) {
                let swapped = swapNum(num.n, i, j);
                if(swapped.charAt(0) == '0') continue;

                numbers[swapped] = k + 1;
            }
        }
    }
}

// for sorting
let ans = [];
for(let num in numbers) {
    if(numbers[num] == K) {
        ans.push(num);
    }
}

console.log(ans.length == 0? -1 : ans[ans.length - 1]);