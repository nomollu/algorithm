// S5 줄 세우기
const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
});

let input = [];
rl.on('line', function (line) {
    input.push(line);
}).on('close', function (){
    solution(input);
    process.exit();
});

function solution(line) {
    let N = Number(line[0]);

    // input names
    let names = [];
    for(let i = 1; i <= N; i++) names.push(line[i].trim());

    let names_inc = Array.from(names);
    let names_dec = Array.from(names);

    // sorting
    names_inc.sort((a, b) => {
        if(a > b) return 1;
        else if(b > a) return -1;
        else return;
    });

    names_dec.sort((a, b) => {
        if(a > b) return -1;
        else if(b > a) return 1;
        else return;
    });

    function equal(list1, list2) {
        for(let i = 0; i < list1.length; i++) {
            if(list1[i] != list2[i]) return false;
        }
        return true;
    }

    if(equal(names, names_inc)) {
        console.log('INCREASING');
    } else if(equal(names, names_dec)) {
        console.log('DECREASING');
    } else {
        console.log('NEITHER');
    }
}



