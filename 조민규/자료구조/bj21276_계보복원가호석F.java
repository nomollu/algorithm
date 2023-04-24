package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @TODO 입력값을 map처럼 활용하면서, 중복된 key값도 저장이 되어야함. 현재는 중복을 허용하지 않아 일부 입력 누락
 * @TODO key들이 동일한 부모를 가리킬 때, key들 간 부모자식 관계 정립 필요
 */
public class bj21276_계보복원가호석F {
    static int N, M; // 사람 수, 기억하는 정보의 개수
    static String[] names;
    public static void main(String[] args) throws IOException {
        // 입력 1
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        names = br.readLine().split(" ");
        M = Integer.parseInt(br.readLine());

        // 입력 2
        Map<String, String> map = new HashMap<>(); // [child, name], 자식과 부모 관계 저장
        TreeMap<String, Set<String>> children = new TreeMap<>(); // [name, child list] 해당 name이 가진 자식들
        Set<String> sijo = new TreeSet<>(); // 시조들을 저장하는 Set
        for(int i = 0 ; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String name = st.nextToken();
            map.put(child, name);
        }
        for(int i = 0 ; i < N ; i++){
            children.put(names[i], new TreeSet<>());
        }

        // 입력받았던 모든 (child,name) 을 탐색한다.
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String child = entry.getKey(); // 시작 child 설정
            String name = entry.getValue(); // 시작 name 설정
            System.out.println("[child : " + child + "], [name : " + name + "]");
            while(true){

                // 현재 name의 children 목록에 추가
                children.get(name).add(child);

                // 시조일 경우
                if(!map.containsKey(name)){
                    sijo.add(name); // 시조 set에 추가
                    break; // while문 탈출
                }

                // 시조가 아닐 경우
                child = name;
                name = map.get(name); // 현재 name을 child로 하는 다음 name 설정
            }
        }

        StringBuilder sb = new StringBuilder();
        // 출력 1
        sb.append(sijo.size()).append("\n");
        // 출력 2
        List<String> list = new ArrayList<>(sijo);
        Collections.sort(list);
        for(String s : list){
            sb.append(s).append(" ");
        }
        sb.append("\n");
        // 출력 3
        for (Map.Entry<String, Set<String>> entry : children.entrySet()){
            String key = entry.getKey();
            Set<String> childrenList = entry.getValue();

            sb.append(key).append(" ").append(childrenList.size()).append(" ");
            for(String c : childrenList){
                sb.append(c).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
