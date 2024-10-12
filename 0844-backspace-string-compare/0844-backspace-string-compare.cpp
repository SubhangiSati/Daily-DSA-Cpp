class Solution {
public:
    bool backspaceCompare(string s, string t) {
        int n = s.size(), m = t.size(), i=n-1,j=m-1, c1 = 0, c2 = 0;
        
        while(i>=0 || j>=0){
            // cout<<i<<" "<<j<<endl;
            if(i>=0 && c1 && s[i]!='#') {i--; c1--;}
            else if(j>=0 && c2 && t[j]!='#') {j--; c2--;}
            else if(i>=0 && s[i]=='#') {c1++; i--;}
            else if(j>=0 && t[j]=='#') {c2++; j--;}
            else if(i>=0 && j>=0 && s[i]!=t[j]) return false;
            else {i--; j--;}
            
        }
        return i==j;
    }
};