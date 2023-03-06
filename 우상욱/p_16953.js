// A -> B
var fs = require('fs'); 
var input = fs.readFileSync('input.txt').toString().trim().split('\n');
[A, B] = input[0].split(' ').map((ele) => Number(ele));

function add_1(num) {
    return Number(String(num) + '1');
}

let queue = [{num : A, nOperation : 1}];
while(queue.length != 0) {
    let popped = queue.shift();
    let next_num;

    // * 2
    next_num = popped.num * 2;

    if(next_num == B) {
        console.log(popped.nOperation + 1);
        process.exit();
    } else if(next_num < B) {
        queue.push({num : next_num, nOperation : popped.nOperation + 1});
    }

    // add 1
    next_num = add_1(popped.num);

    if(next_num == B) {
        console.log(popped.nOperation + 1);
        process.exit();
    } else if(next_num < B) {
        queue.push({num : next_num, nOperation : popped.nOperation + 1});
    }
}

console.log(-1);
