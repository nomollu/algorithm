var fs = require('fs'); 
let input = fs.readFileSync('input.txt').toString().trim().split('\n');

let N = Number(input[0].split(' ')[0]);
let wanted = input[1].split(' ').map((e) => Number(e));

let deck = [];
for(let i = 1; i <= N; i++) deck.push(i);

function getElement(deck) {
    // 1
    deck.shift();
}

function left_rotate(deck) {
    // 2
    deck.push(deck.shift());
}

function right_rotate(deck) {
    // 3
    deck.unshift(deck.pop());
}

let ans = 0;
for(let target of wanted) {
    let pos = deck.indexOf(target);

    if(pos < deck.length - pos) {
        for(let i = 0; i < pos; i++) left_rotate(deck);
        ans += pos;
    } else {
        for(let i = 0; i < deck.length - pos; i++) right_rotate(deck);
        ans += deck.length - pos;
    }

    // operation 1
    getElement(deck);
}

console.log(ans);