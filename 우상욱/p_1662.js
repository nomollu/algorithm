var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');


function count_len(K, Q) {
    let len = 0;

    for(let i = 0; i < Q.length; i++) {
        let c = Q.charAt(i);
        
        if(i + 1 == Q.length || Q.charAt(i + 1) != '(') len++;
        else {
            // if find (
            let nBracket = 0; // count open bracket
            let last_idx = i + 2; // closed bracket index

            // find closed bracket index
            for(let j = i + 2; j < Q.length; j++) {
                let tmp_c = Q.charAt(j);

                if(tmp_c == '(') nBracket++;
                else if(tmp_c == ')') {
                    if(nBracket == 0) {
                        last_idx = j;
                        break;
                    }
                    else nBracket--;
                }
            }

            len += count_len(Number(c), Q.slice(i + 2, last_idx));
            i = last_idx; // skip index of ( )
        }
    }
    
    return K * len;
}

console.log(count_len(1, input[0]));
