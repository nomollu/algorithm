function solution(k, tangerine) {
    let answer = 0;
    let map = new Map();
    tangerine.forEach((item) => {
        if(map.has(item)){
            map.set(item, map.get(item)+1);
        }else{
            map.set(item, 1);
        }
    });
    let array = [...map].sort((a,b) => b[1] - a[1]);
    for(const item of array){
        k = k - item[1];
        answer++;
        if(k <= 0){
            break;
        }
    }
    return answer;
}
