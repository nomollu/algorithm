var fs = require('fs'); 
let input = fs.readFileSync('input.txt').toString().split('\n');

let N = Number(input[0].split(' ')[0]), K = Number(input[0].split(' ')[1]);

// input scores
let scores = [];
for(let i = 1; i <= N; i++) {
    scores.push(Number.parseFloat(input[i]));
}
scores.sort();

function average(arr) {
    let sum = 0;
    for(let item of arr) sum += item;
    return sum / arr.length;
}

for(let i = 0; i < K; i++) {
    scores[i] = scores[K];
    scores[scores.length - 1 - i] = scores[scores.length - 1 - K];
}
let corrected_average = average(scores);

for(let i = 0; i < K; i++) {
    scores.shift();
    scores.pop();
}
let cutted_average = average(scores);

console.log(cutted_average);
console.log(corrected_average);