function solution(new_id) {
    let answer = new_id
        .toLowerCase() // 1단계
        .replace(/[^a-z0-9\-\_\.]/g,"") // 2단계
        .replace(/\.+/g, ".") // 3단계
        .replace(/^\.|\.$/, ""); // 4단계
    if(answer.length === 0){ // 5단계
        answer += "a";
    }
    answer = answer.slice(0, 15).replace(/\.$/, ""); //6단계
    let len = answer.length;
    return answer.length <= 2 ? answer + answer.charAt(len - 1).repeat(3 - len) : answer;
}
