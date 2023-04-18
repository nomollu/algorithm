int solution(int [] order) {
  int answer = 0;
  Stack<Integer>sub = new Stack<>();
  int idx = 0;

  for(int i=1; i<=order.length; i++) {
    boolean flag = false;

    if(i == order[idx]) {
      idx++;
      answer++;
      flag = true;
    }
    while(!sub.isEmpty() && sub.peek() == order[idx]) {
      sub.pop();
      answer++;
      idx++;
      flag = true;
    }

    if(!flag) sub.add(i);
  }

  while(!sub.isEmpty() && sub.peek() == order[idx]) {
    sub.pop();
    idx++;
    answer++;
  }

  return answer;	
}
