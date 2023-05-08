#include <string>
#include <vector>
#include <iostream>

using namespace std;

vector<int> solution(vector<string> name, vector<int> yearning, vector<vector<string>> photo) {
    vector<int> answer;

    // 모든 입력에 대해 반복 진행
    for(auto inputArr : photo){
        int sum = 0;
        for(auto input : inputArr){
            // 포함되었는지 확인
            for(int i = 0 ; i < name.size() ; i++){
                if(input == name[i]){
                    sum += yearning[i];
                    break;
                }
            }
        }
        answer.push_back(sum);
    }

    return answer;
}