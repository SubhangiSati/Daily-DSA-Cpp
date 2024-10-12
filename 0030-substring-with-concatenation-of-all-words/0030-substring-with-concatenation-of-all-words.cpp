class Solution {
public:
    // This function searches for all starting indices of substring(s) in 's' that is a concatenation of each word in 'words' exactly once and without any intervening characters.
    vector<int> findSubstring(string s, vector<string>& words) {
        // Count the frequency of each word in 'words'.
        unordered_map<string, int> wordCount;
        for (auto& word : words) {
            ++wordCount[word];
        }

        int stringSize = s.size(), wordCountSize = words.size(), wordSize = words[0].size();
        vector<int> substrIndices;

        // Iterate over the string 's'.
        for (int i = 0; i < wordSize; ++i) {
            unordered_map<string, int> windowCount;
            int left = i, right = i;
            int totalCount = 0;

            // Slide a window over the string 's'.
            while (right + wordSize <= stringSize) {
                string currentWord = s.substr(right, wordSize);
                right += wordSize;

                // Skip the current segment if the word is not in 'words'.
                if (!wordCount.count(currentWord)) {
                    windowCount.clear();
                    left = right;
                    totalCount = 0;
                    continue;
                }

                // Update the count for the current word in the window.
                ++windowCount[currentWord];
                ++totalCount;

                // If there are more occurrences of 'currentWord' in the window than in 'words', remove from the left.
                while (windowCount[currentWord] > wordCount[currentWord]) {
                    string wordToRemove = s.substr(left, wordSize);
                    left += wordSize;
                    --windowCount[wordToRemove];
                    --totalCount;
                }

                // If the total count of words match and all words frequencies are as expected, add to result.
                if (totalCount == wordCountSize) {
                    substrIndices.push_back(left);
                }
            }
        }

        return substrIndices;
    }
};
