string get(vector<int> V) {
    string S;
    for (int i = 0; i < V[7]; i++) S += "7";
    for (int i = 0; i < V[5]; i++) S += "5";
    for (int i = 0; i < V[3] / 2; i++) S += "9";
    for (int i = 0; i < V[2] / 3; i++) S += "8";
    V[3] %= 2;
    V[2] %= 3;
    if (V[3] > 0 && V[2] > 0) S += "6", V[3]--, V[2]--;
    if (V[3] == 1) S += "3", V[3]--;
    if (V[2] == 2) S += "4", V[2] -= 2;
    if (V[2] == 1) S += "2", V[2] -= 1;
    return S;
}

class Solution {
public:
    string smallestNumber(string num, long long t) {
        vector<int> D[10];
        vector<int> C(10);
        int i, j, x;

        // Precomputing prime factors for digits 2 to 9
        for (i = 2; i <= 9; i++) {
            D[i].clear();
            j = i;
            for (x = 2; x <= i; x++) {
                while (j % x == 0) {
                    D[i].push_back(x);
                    j /= x;
                }
            }
        }

        // Decomposing t into prime factors
        for (i = 2; i <= 9; i++) {
            while (t % i == 0) {
                C[i]++;
                t /= i;
            }
        }
        if (t > 1) return "-1"; // If t has factors greater than 9, return -1

        // Adjusting num to remove invalid trailing zeroes
        for (i = 0; i < num.size(); i++) {
            if (num[i] == '0') {
                for (; i < num.size(); i++) {
                    num[i] = '1';
                }
                break;
            }
        }

        // Decreasing the count of prime factors based on digits in `num`
        for (char c : num) {
            for (int a : D[c - '0']) {
                C[a]--;
            }
        }

        // If `get(C)` is empty, the current `num` is valid
        if (get(C) == "") return num;

        reverse(num.begin(), num.end());
        num += string(40, '0'); // Extend `num` to handle overflow cases
        int N = num.size();

        // Attempt to generate the smallest valid number
        for (i = 0; i < N; i++) {
            for (int a : D[num[i] - '0']) {
                C[a]++;
            }
            for (x = num[i] - '0' + 1; x <= 9; x++) {
                for (int a : D[x]) {
                    C[a]--;
                }
                string T = get(C);
                if (T.size() <= i) {
                    while (T.size() < i) T += "1";
                    sort(T.begin(), T.end());
                    reverse(T.begin(), T.end());
                    num[i] = '0' + x;
                    for (int k = 0; k < i; k++) num[k] = T[k];
                    while (num.back() == '0') num.pop_back();
                    reverse(num.begin(), num.end());
                    return num;
                }
                for (int a : D[x]) {
                    C[a]++;
                }
            }
        }
        return "";
    }
};