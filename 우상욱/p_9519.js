// G5 졸려
var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

let N = Number(input[0]);

let words = [], tmp = [];
for(let i = 0; i < input[1].length; i++) {
    words.push(input[1][i]);
    tmp.push(input[1][i]);
}
    

function reverse(words) {
    let tmp = [];
    
    let i = 0;
    for(; i < words.length; i += 2)
        tmp.push(words[i]);

    if(words.length % 2 == 0) i -= 1;
    else i -= 3;

    for(; i > 0; i -= 2)
        tmp.push(words[i]);

    return tmp;
}

function ArrToStr(arr) {
    let ret = "";
    for(let i = 0; i < arr.length; i++)
        ret += arr[i];

    return ret;
}

// find cycle
let cycle;
for(let i = 0;; i++) {
    tmp = reverse(tmp);

    if(ArrToStr(tmp) == ArrToStr(words)) {
        cycle = i + 1;
        break;
    }
}

// reverse calculate
for(let i = 0; i < N % cycle; i++)
    words = reverse(words);
console.log(ArrToStr(words));
