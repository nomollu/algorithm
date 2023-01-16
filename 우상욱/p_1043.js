var fs = require('fs'); 
let input = fs.readFileSync('input.txt').toString().trim().split('\n');

let N = Number(input[0].split(' ')[0]);
let M = Number(input[0].split(' ')[1]);

// input true knowns
let people = new Array(N + 1);

let true_knowns = input[1].split(' ').map((e) => Number(e)).slice(1);
true_knowns.forEach((e) => { people[e] = 0 });

let parties = [];
for(let i = 2; i < M + 2; i++) {
    parties.push(input[i].split(' ').map((e) => Number(e)).slice(1));
}

function true_propagation(true_man) {
    for(let party of parties) {
        if(party.indexOf(true_man) == -1) continue;
            
        // find true man
        party.forEach((e) => {
            if(people[e] != 0) {
                people[e] = 0;
                true_propagation(e);
            }
        })
    }
}

true_knowns.forEach((e) => {
    true_propagation(e);
})

// find party to lie
let ans = 0;
for(let party of parties) {
    let canLie = true;
    party.forEach((e) => { if(people[e] == 0) canLie = false; })
    
    if(canLie) ans += 1;
}

console.log(ans);