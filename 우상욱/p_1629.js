var fs = require('fs'); 
var [A, B, C] = fs.readFileSync('input.txt').toString().trim().split(' ').map(BigInt);

function pow(x, y) {
    if(y == 0) return BigInt(1);


    let tmp = pow(x, BigInt(parseInt(y / BigInt(2))));

    if(y % BigInt(2) == 0) return tmp * tmp % C;
    else return tmp * tmp * x % C;
}

console.log(parseInt(pow(A, B)));
