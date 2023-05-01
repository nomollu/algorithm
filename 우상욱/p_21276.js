// G3 계보 복원가 호석
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

class Info {
    constructor() {
        this.in_cnt = 0;
        this.children = {};
    }
}

let genealogy = {};

// input name
let N = Number(input[0]);
let people = input[1].trim().split(' ');
for(let person of people) genealogy[person] = new Info();

// input relation
let M = Number(input[2]);
for(let i = 3; i < 3 + M; i++) {
    [X, Y] = input[i].trim().split(' ');

    genealogy[X].in_cnt++;
    genealogy[Y].children[X] = genealogy[X];
}

let root_cnt = 0;
let roots = "";
let q = [];

// count root
for(let key of Object.keys(genealogy).sort()) {
    if(genealogy[key].in_cnt == 0) {
        root_cnt++;
        roots += `${key} `;

        q.push(genealogy[key]);
    }
}

console.log(root_cnt);
console.log(roots.trim());

// find children
while(q.length != 0) {
    let q_size = q.length;

    for(let i = 0; i < q_size; i++) {
        let node = q.shift();

        for(let c_key of Object.keys(node.children)) {
            genealogy[c_key].in_cnt--;

            if(genealogy[c_key].in_cnt == 0) q.push(genealogy[c_key]);    
            else delete node.children[c_key];
        }
    }
}

// print children
let ans = "";
for(let key of Object.keys(genealogy).sort()) {
    let node = genealogy[key];
    ans += `${key} `;

    let children_cnt = 0;
    let children_names = "";
    for(let c_key of Object.keys(node.children).sort()) {
        children_cnt++;
        children_names += `${c_key} `;
    }

    if(children_cnt == 0) ans += `${children_cnt}\n`;
    else ans += `${children_cnt} ${children_names}\n`;
}

console.log(ans.trim());
