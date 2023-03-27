// S4 큐 2
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().trim().split('\n');

class Node {
    constructor(item) {
        this.item = item;
        this.next = null;
    }
}

class Queue {
    constructor() {
        this.front = null;
        this.rear = null;
        this.len = 0;
    }

    enqueue(item) {
        let node = new Node(item);

        if(this.len == 0) {
            this.front = node;
        } else {
            this.rear.next = node;
        }

        this.rear = node;
        this.len++;
    }

    dequeue() {
        if(this.len == 0) return -1;
        else {
            let ret = this.front.item;

            this.front = this.front.next;
            if(this.front == null) this.rear = null;
            this.len--;

            return ret;
        }
    }

    size() {
        return this.len;
    }

    isEmpty() {
        return this.len == 0? 1 : 0;
    }

    getFront() {
        return this.front == null? -1 : this.front.item;
    }

    getRear() {
        return this.rear == null? -1 : this.rear.item;
    }
}

let N = Number(input[0]);
let queue = new Queue();

let ans = "";
for(let i = 1; i <= N; i++) {
    let command = input[i].trim().split(' ');

    switch(command[0]) {
        case 'push':
            queue.enqueue(command[1]);
            break;
        case 'pop':
            ans += `${queue.dequeue()}\n`;
            break;
        case 'size':
            ans += `${queue.size()}\n`;
            break;
        case 'empty':
            ans += `${queue.isEmpty()}\n`;
            break;
        case 'front':
            ans += `${queue.getFront()}\n`;
            break;
        case 'back':
            ans += `${queue.getRear()}\n`;
            break;
    }
}
console.log(ans.trim());