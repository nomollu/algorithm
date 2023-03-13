class Solution {
    public int solution(String s) {
        
        Stack<Charactor> stack = new Stack<Charactor>();
        int cnt=0, size=s.length();
        for(int i=0; i<size; i++){
            char now = s.charAt(i);
            if(now=='[' || now=='(' || now=='{') stack.push(now);
            else {
                char tmp = stack.peek();
                if((now==']'&&tmp=='[') || (now==')'&&tmp=='(') || (now=='}' && tmp=='{') ){
                    tmp.poll();
                }else{
                    // 생각 막힘....
                }
            }
        }
        
        int answer = -1;
        
        return answer;
    }
}
