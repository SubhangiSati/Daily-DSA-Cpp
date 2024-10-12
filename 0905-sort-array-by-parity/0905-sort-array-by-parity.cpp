class Solution {
public:
    vector<int> sortArrayByParity(vector<int>& nums) {
        int i = 0;
        for(auto &el : nums){
            if(el%2==0){
                swap(el, nums[i]);
                i++;
            }
        }
        
        return nums;
    }
};