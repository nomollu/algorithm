var fs = require('fs'); 
let input = fs.readFileSync('input.txt').toString().trim().split('\n');

let line1 = input[0].split(' ');
let N = line1[0], M = line1[1], K = line1[2];

// input beers
let beers = [];
for (let i = 1; i <= K; i++) {
    let line = input[i].split(' ');
    beers.push({favor : Number(line[0]), alco : Number(line[1])});
}

beers.sort((a, b)=> {
    return a.alco - b.alco;
});

/* --- heap define --- */
class MinHeap {
    constructor(){
    this.heap = [ null ];
    }

    size() {
        return this.heap.length -1;
    }

    getMin() {
        return this.heap[1] ? this.heap[1] : null;
    }

    swap(a, b) {
        [ this.heap[a], this.heap[b] ] = [ this.heap[b], this.heap[a] ];
    }

    h_push(value) {
        this.heap.push(value);
        let curIdx = this.heap.length - 1;
        let parIdx = (curIdx / 2) >> 0;

        while(curIdx > 1 && this.heap[parIdx] > this.heap[curIdx]) {
            this.swap(parIdx, curIdx);
            curIdx = parIdx;
            parIdx = (curIdx / 2) >> 0;
        }
    }

    h_pop() {
        const ret = this.heap[1];

        if(this.heap.length <= 2) this.heap = [ null ];
        else this.heap[1] = this.heap.pop();

        let curIdx = 1;
        let leftIdx = curIdx * 2;
        let rightIdx = curIdx * 2 + 1;

        if(!this.heap[leftIdx]) return ret;
        if(!this.heap[rightIdx]) {
            if(this.heap[leftIdx] < this.heap[curIdx]) {
                this.swap(leftIdx, curIdx);
            }
            return ret;
        }

        while(this.heap[leftIdx] < this.heap[curIdx] || this.heap[rightIdx] < this.heap[curIdx]) {
            const maxIdx = this.heap[leftIdx] > this.heap[rightIdx] ? rightIdx : leftIdx;
            this.swap(maxIdx, curIdx);
            curIdx = maxIdx;
            leftIdx = curIdx * 2;
            rightIdx = curIdx * 2 + 1;
        }
        return ret;
    }
}
/* --- define end --- */

let drinked = new MinHeap();
let favor_total = 0;

for(let beer of beers) {
    drinked.h_push(beer.favor);
    favor_total += beer.favor;
  
    if(drinked.size() == N) {
        if(favor_total >= M) {
        console.log(beer.alco);
        process.exit();
    } else {
        // not saticified
        favor_total -= drinked.h_pop();
    }
  }
}

console.log(-1);