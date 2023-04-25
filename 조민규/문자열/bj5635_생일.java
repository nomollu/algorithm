package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj5635_생일 {

    static class Person implements Comparable<Person> {
        String name;
        int day, month, year;

        public Person(String name, int day, int month, int year) {
            this.name = name;
            this.day = day;
            this.month = month;
            this.year = year;
        }

        @Override
        public int compareTo(Person o) {
            if(this.year == o.year){
                if(this.month == o.month){
                    return this.day - o.day;
                }
                return this.month - o.month;
            }
            return this.year - o.year;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Person[] people = new Person[N];
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            people[i] = new Person(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(people);

        System.out.println(people[N-1].name);
        System.out.println(people[0].name);
    }
}
