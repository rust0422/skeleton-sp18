public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            d.addLast(c);
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        //Any zero or 1 character word is considered a palindrome
        if (word.length() <= 1) {
            return true;
        }

        String reverse = "";
        Deque<Character> d = wordToDeque(word);
        for (int i = 0; i < word.length(); i++) {
            reverse += d.removeLast();
        }
        return (word.equals(reverse));
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        //Any zero or 1 character word is considered a palindrome
        if (word.length() <= 1) {
            return true;
        }

        // In order
        Deque<Character> d1 = wordToDeque(word);

        String reverse = "";
        for (int i = 0; i < word.length(); i++) {
            reverse += d1.removeLast();
        }

        boolean bool = true;
        boolean odd = word.length() % 2 != 0;
        for (int i = 0; i < word.length(); i++) {
            if (odd & i == word.length() / 2) {
                continue;
            }
            if (!cc.equalChars(word.charAt(i), reverse.charAt(i))) {
                bool = false;
            }
        }

        return bool;
    }
}
