function solution(want, number, discount) {
    const calculate = (d) => {
        const wantMap = new Map();
        // 해당 item이 있다면 그 값을 가져와서 +1, 없다면 0+1
        d.forEach((item) => wantMap.set(item, (wantMap.get(item) || 0) + 1));
        for(let i=0; i<want.length; i++){
            // 개수가 하나라도 안 맞는게 있다면 false
            if(wantMap.get(want[i]) !== number[i]) return false;
        }
        return true;
    }
    let answer = 0;
    for(let i=0; i<discount.length-9; i++){
        const d = discount.slice(i, i+10);
        if(calculate(d)){
            answer++;
        }
    }
    return answer;
}
