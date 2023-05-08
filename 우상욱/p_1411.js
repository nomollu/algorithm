// S2 비슷한 단어
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
let N = Number(input[0]);

function isSame(word1, word2) {
    let w1_keys = Object.keys(word1).sort();
    let w2_keys = Object.keys(word2).sort();
    if(w1_keys.length != w2_keys.length) return false;

    for(let i = 0; i < w1_keys.length; i++) {
        if(w1_keys[i] != w2_keys[i]) return false;
    }

    return true;
}


let words = [];
let ans = 0;
for(let n = 1; n <= N; n++) {
    let word = input[n].trim();

    let word_obj = {};
    for(let c = 97; c <= 122; c++) {
        const letter = String.fromCharCode(c);

        let tmp_key = "";
        for(let i = 0; i < word.length; i++) {
            if(letter == word.charAt(i)) tmp_key += `${i}_`;
        }
        word_obj[tmp_key] = true;
    }
    
    for(let i = 0; i < words.length; i++) {
        if(isSame(words[i], word_obj)) ans++;
    }
    words.push(word_obj);
}

console.log(ans);