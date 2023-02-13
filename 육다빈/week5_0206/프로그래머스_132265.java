class Solution {
    public int solution(int[] topping) {
        int[] visit_left = new int[10001];
        int[] visit_right = new int[10001];
        int cnt_left=0, cnt_right=0;
        
        int len = topping.length;
        for(int i=0; i<len; i++){
            if(visit_right[topping[i]]==0) cnt_right++;
            visit_right[topping[i]]++;
        }
        
        int answer = 0;
        for(int i=0; i<len; i++){
            if(++visit_left[topping[i]]==1) cnt_left++;
            if(--visit_right[topping[i]]==0) cnt_right--;
            if(cnt_left == cnt_right) answer++;
        }
        
        return answer;
    }
}
