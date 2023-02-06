var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let str1 = input[0].trim(), str2 = input[1].trim();

let LCS = [];
// init array
for(let i = 0; i <= str1.length; i++) {
    LCS.push(new Array(str2.length + 1).fill(0));
}

// compare strings
for(let i = 1; i < LCS.length; i++) {
    for(let j = 1; j < LCS[0].length; j++) {
        if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
            LCS[i][j] = LCS[i - 1][j - 1] + 1;
        } else {
            LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
        }
    }
}

console.log(LCS[str1.length][str2.length]);