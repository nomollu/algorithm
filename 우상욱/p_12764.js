// G3 싸지방에 간 준하
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

let N = Number(input[0]);
let use_times = [];

class Time {
    constructor(start, end) {
        this.start = start;
        this.end = end;
        this.seat_num = null;
    }
}

for(let i = 1; i <= N; i++) {
    [start, end] = input[i].split(' ').map(ele => Number(ele));
    use_times.push(new Time(start, end));
}
use_times.sort((a, b) => a.start - b.start);

class Heap {
    constructor() {
        this.heap = [];
    }

    push(value) {
        this.heap.push(value);
        this.bubbleUp();
    }

    pop() {
        const min = this.heap[0];
        const end = this.heap.pop();

        if(this.heap.length > 0) {
            this.heap[0] = end;
            this.sinkDown();
        }
        return min;
    }

    peak() {
        return this.heap[0];
    }

    isEmpty() {
        return this.heap.length == 0;
    }

    length() {
        return this.heap.length;
    }

    bubbleUp() {
        let index = this.heap.length - 1;
        const node = this.heap[index];

        while(index > 0) {
            const parentIdx = Math.floor((index - 1) / 2);
            const parent = this.heap[parentIdx];

            if(node.end > parent.end) break;

            this.heap[parentIdx] = node;
            this.heap[index] = parent;
            index = parentIdx;
        }
    }

    sinkDown() {
        let index = 0;
        const length = this.heap.length;
        const node = this.heap[0];

        while(true) {
            const leftChildIndex = 2 * index + 1;
            const rightChildIndex = 2 * index + 2;

            let swapIndex = null;
            let leftChild, rightChild;

            if(leftChildIndex < length) {
                leftChild = this.heap[leftChildIndex];
                if(leftChild.end < node.end) swapIndex = leftChildIndex;
            }

            if(rightChildIndex < length) {
                rightChild = this.heap[rightChildIndex];
                if(
                    (swapIndex == null && rightChild.end < node.end) ||
                    swapIndex != null && rightChild.end < leftChild.end
                ) {
                    swapIndex = rightChildIndex;
                }
            }

            if(swapIndex == null) break;

            this.heap[index] = this.heap[swapIndex];
            this.heap[swapIndex] = node;
            index = swapIndex;
        }
    }
}

let seats = []; // number of seat used
let user_pq = new Heap(); // computer user
let seat_pq = new Heap(); // rest computer seats

for(let i = 0; i < use_times.length; i++) {
    // some member is out
    while(!user_pq.isEmpty() && user_pq.peak().end < use_times[i].start) {
        let popped = user_pq.pop();
        seat_pq.push(new Time(0, popped.seat_num));
    }

    if(seats.length == user_pq.length()) {
        seats.push(1);
        use_times[i].seat_num = seats.length - 1;
        user_pq.push(use_times[i]);
    } else {
        let next_com_num = seat_pq.pop().end;

        seats[next_com_num]++;
        use_times[i].seat_num = next_com_num;
        user_pq.push(use_times[i]);
    }
}

console.log(seats.length);

let ans = "";
for(let i = 0; i < seats.length; i++) ans += `${seats[i]} `;
console.log(ans.trim());