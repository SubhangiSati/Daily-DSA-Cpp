class Solution {
private:
    vector<string> getNext(string& word, unordered_map<string, int>& words_distance, unordered_set<string>& visited) {
        vector<string> res;
        string alphabet = "abcdefghijklmnopqrstuvwxyz";
        
        for (auto i = 0; i < word.size(); ++i) {
            for (auto l: alphabet) {
                if (word[i] == l) {
                    continue;
                }
                
                string candidate = word;
                candidate[i] = l;
                
                if (words_distance.count(candidate) == 0 || visited.count(candidate) > 0) {
                    continue;
                }
                
                res.push_back(candidate);
            }
        }
        
        return res;
    }
    
    void bfs(string& endWord, unordered_map<string, int>& words_distance) {
        
        queue<string> q;
        unordered_set<string> visited;
        
        q.push(endWord);
        visited.insert(endWord);
        int distance = 0;
        
        while(!q.empty()) {
            int count = q.size();
            for (auto i = 0; i < count; ++i) {
                string word = q.front();
                q.pop();
                
                words_distance[word] = distance;
                for (auto neighbor: getNext(word, words_distance, visited)) {
                    visited.insert(neighbor);
                    q.push(neighbor);
                }
            }
            
            distance++;
        }
    }
    
    void dfs(unordered_map<string, int>& words_distance, string &endWord, unordered_set<string>& visited, vector<string>& tmp, vector<vector<string>>& res) {
        string word = tmp.back();
        if (word == endWord) {
            res.push_back(tmp);
            return;
        }
        
        int distance = words_distance.count(word) > 0 ? words_distance[word] : INT_MAX;
        
        for (auto neighbor: getNext(word, words_distance, visited)) {
            if (words_distance[neighbor] >= distance) {
                continue;
            }
            visited.insert(neighbor);
            tmp.push_back(neighbor);
            
            dfs(words_distance, endWord, visited, tmp, res);
            
            tmp.pop_back();
            visited.erase(neighbor);
        }
    }
    
public:
    vector<vector<string>> findLadders(string beginWord, string endWord, vector<string>& wordList) {
        unordered_map<string, int> words_distance;
        
        for (auto word: wordList) {
            words_distance[word] = 0;
        }
        
        if (words_distance.count(endWord) == 0) {
            return vector<vector<string>>{};
        }
        
        bfs(endWord, words_distance);
        
        unordered_set<string> visited;
        vector<vector<string>> res;
        vector<string> tmp;
        
        tmp.push_back(beginWord);
        visited.insert(beginWord);
        
        dfs(words_distance, endWord, visited, tmp, res);
        
        return res;
    }
};