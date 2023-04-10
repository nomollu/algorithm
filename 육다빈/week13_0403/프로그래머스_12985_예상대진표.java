class Solution
{
    public int solution(int n, int a, int b)
    {
        int me=a, you=b;
        int answer = 1;
        
        while(true){
            if(Math.abs(me-you)==1 && (me+1)/2==(you+1)/2) break;
            me = (me+1)/2;
            you = (you+1)/2;
            answer++;
        }

        return answer;
    }
}
