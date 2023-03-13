// S3 빈도 정렬
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
[N, C] = input[0].split(' ').map(ele => Number(ele));

let nSerial = {}; // 몇번 나왔는지
let nOrder = {}; // 몇번째로 나왔는지

let inputed_nums = input[1].split(' ').map(ele => Number(ele));

// count n
for(let i = 0; i < inputed_nums.length; i++) {
    if(nSerial.hasOwnProperty(inputed_nums[i])) nSerial[inputed_nums[i]]++;
    else nSerial[inputed_nums[i]] = 1;

    if(!nOrder.hasOwnProperty(inputed_nums[i])) nOrder[inputed_nums[i]] = i;
}

// sort with startndards
inputed_nums.sort((a, b) => {
    if(nSerial[a] == nSerial[b]) return nOrder[a] - nOrder[b];
    else return nSerial[b] - nSerial[a];
})

let ans = "";
for(let n of inputed_nums) ans += `${n} `;
console.log(ans.trim());