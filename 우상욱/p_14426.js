var fs = require('fs'); 
let input = fs.readFileSync('input.txt').toString().split('\n');
let N = Number(input[0].split(' ')[0]), M = Number(input[0].split(' ')[1]);

let strings = new Map();
for(let i = 1; i <= N; i++) {
    let string = input[i];

    // add all prefix
    let prefix = "";
    for(let j = 0; j < string.length; j++) {
        prefix += string[j];
        strings.set(prefix, "");
    }
}

let ans = 0;
for(let i = N + 1; i <= M + N; i++) {
    // check is prefix
    let checked = input[i];
    if(strings.has(checked)) ans += 1;
}
console.log(ans);