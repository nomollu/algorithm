function solution(record) {
    var answer = [];
    let log = {};
    for(const r of record){
        const arr = r.split(" "); //공백으로 분리
        let id = arr[1];
        let name = arr[2]; //Leave일 경우에는 없음
        if(name){ //Enter, Change인 경우에만 이름 저장
            log[id] = name;
        }
    }
    for(const r of record){
        const arr = r.split(" "); //공백으로 분리
        let name = log[arr[1]];
        if(arr[0] === "Enter"){
            answer.push(name+"님이 들어왔습니다.");
        }else if(arr[0] === "Leave"){
            answer.push(name+"님이 나갔습니다.");
        }
    }
    return answer;
}
