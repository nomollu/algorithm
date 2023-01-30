function solution(fees, records) {
    const answer = [];
    const stack = [];
    let time = {};
    
    // 차량 번호 순대로 정렬
    records.sort((a,b) => {
        const aa = a.split(" ");
        const bb = b.split(" ");
        return aa[1] - bb[1];
    })
    .forEach((record) => {
        if(!time[record.split(" ")[1]]){ //time 객체에 차량 번호 없을 시 누적 시간 0으로 초기화
            time[record.split(" ")[1]] = 0;
        }
        
        if(stack.length === 0){
            stack.push(record);
        }else{
            const inCar = stack.pop().split(" ");
            const outCar = record.split(" ");
            if(outCar[1] === inCar[1]){ // 차량 번호 같을 때 누적시간 계산
                const inTime = inCar[0].split(":").map((item) => Number(item));
                const outTime = outCar[0].split(":").map((item) => Number(item));
                time[outCar[1]] = time[outCar[1]] + (calculateFee(outTime)) - (calculateFee(inTime));
            }else{
                stack.push(inCar.join(" "));
                stack.push(record);
            }
        }
    });

    stack.forEach((item) => { // 출차 내역 없을 경우 23:59에 출차된 것
        const inCar = item.split(" ");
        const inTime = inCar[0].split(":").map((n) => Number(n));
        let sum = time[inCar[1]] + (23*60+59) - (calculateFee(inTime));
        time[inCar[1]] = sum;
    })
    
    const times = [];
    for(let number in time){ // 객체 배열로 바꾸기
        times.push([number, time[number]]);
    }
    times.sort((a,b) => a[0] - b[0]).forEach((item) => {
        if(item[1] <= fees[0]){ // 누적 시간이 기본 시간 이하면 기본 요금
            answer.push(fees[1]);
        }else{
            let over = (Math.ceil((item[1] - fees[0]) / fees[2])) * fees[3];
            answer.push(fees[1] + over);
        }
    });
    
    return answer;
}

function calculateFee(num){
    return num[0]*60+num[1];
}
