function solution(n)
{
    let ans = 0;
    while(n !== 0){
        if(n % 2 === 1){ // 홀수일 경우에는 점프
            n -= 1;
            ans++;
        }else{ // 짝수는 순간이동
            n /= 2;
        }
    }

    return ans;
}
