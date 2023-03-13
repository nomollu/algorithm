// G4 전화번호 목록
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

let line = 0;
let T = Number(input[line++]);


let ans = "";
for(let t = 0; t < T; t++) {
    let N = Number(input[line++]);

    // phone number inputed
    let phonenumbers = [];
    for(let n = 0; n < N; n++) phonenumbers.push(input[line++].trim());

    phonenumbers.sort((a, b) => {
        if(a > b) return 1;
        else if (a < b ) return -1;
        else return 0;
    })
    
    let isNo = false;
    for(let i = 0; i < phonenumbers.length - 1; i++) {
        function prefixIncludes(str1, str2) {
            for(let i = 0; i < str1.length; i++) {
                if(str1.charAt(i) != str2.charAt(i)) return false;
            }

            return true;
        }

        isNo = prefixIncludes(phonenumbers[i], phonenumbers[i + 1]);
        if(isNo) break;
    }

    ans += isNo?'NO\n':'YES\n';
}

console.log(ans.trim());

