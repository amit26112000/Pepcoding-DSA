// Question 208
class Trie {
    public static class Node{
        private Node[] children = new Node[26];
        private boolean isEnd = false;
        
        public boolean contains(char ch){
            return (children[ch - 'a'] != null);
        }
        
        public Node get(char ch){
            return children[ch - 'a'];
        }
        
        public void set(char ch){
            children[ch - 'a'] = new Node();
        }
        
        public boolean getEnd(){
            return isEnd;
        }
        
        public void setEnd(){
            isEnd = true;
        }
    }
    
    Node root;
    public Trie() {
        root = new Node();
    }
    
    public void insert(String word) {
        Node curr = root;
        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);

            if(curr.contains(ch) == false)
                curr.set(ch);
        
            curr = curr.get(ch);
        }
        
        curr.setEnd();
    }
    
    public boolean search(String word) {
        Node curr = root;
        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);

            if(curr.contains(ch) == false)
                return false;
        
            curr = curr.get(ch);
        }
        
        return curr.getEnd();
    }
    
    public boolean startsWith(String word) {
        Node curr = root;
        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);

            if(curr.contains(ch) == false)
                return false;
        
            curr = curr.get(ch);
        }
        
        return true;
    }
}

// Leetcode 211
class WordDictionary {
    public static class Node{
        private Node[] children = new Node[26];
        private boolean isEnd = false;
        
        public Node get(char ch){
            return children[ch - 'a'];
        }
        
        public boolean getEnd(){
            return isEnd;    
        }
        
        public void setEnd(){
            isEnd = true;
        }
        
        public void add(char ch){
            children[ch - 'a'] = new Node();
        }
        
        public boolean contains(char ch){
            return (children[ch - 'a'] != null);
        }
    }
    
    Node root;
    public WordDictionary() {
        root = new Node();
    }
    
    public void addWord(String word) {
        Node curr = root;
        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            
            if(curr.contains(ch) == false)
                curr.add(ch);
            
            curr = curr.get(ch);
        }
        curr.setEnd();
    }
    
    public boolean search(String word, int idx, Node curr){
        if(idx == word.length()){
            return curr.getEnd();
        }
        
        char ch = word.charAt(idx);
        
        if(ch != '.'){
            if(curr.contains(ch) == false) return false;
            return search(word, idx + 1, curr.get(ch));
        }
        
        for(char chn = 'a'; chn <= 'z'; chn++){
            if(curr.contains(chn) == false) continue;
            
            if(search(word, idx + 1, curr.get(chn))) 
                return true;
        }
        
        return false;
    }
    
    public boolean search(String word) {
        return search(word, 0, root);
    }
}

// Leetcode 1804
public class Trie {
    public static class Node{
        private Node[] children = new Node[26];
        private int isEnd = 0;
        private int prefixCount = 0;
        
        public boolean contains(char ch){
            return (children[ch - 'a'] != null);
        }
        
        public Node get(char ch){
            return children[ch - 'a'];
        }
        
        public void set(char ch){
            children[ch - 'a'] = new Node();
        }
        
        public int getFreq(){
            return isEnd;
        }
        
        public int getPref(){
        	return prefixCount;    
        }
        
        public void increaseFreq(){
            isEnd++;
        }
        
        public void decreaseFreq(){
            isEnd--;
        }
        
        public void increasePref(){
            prefixCount++;
        }
        
        public void decreasePref(){
            prefixCount--;
        }
    }
    
    Node root;
    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node curr = root;
        
        for(int i=0; i<word.length(); i++){
            curr.increasePref();
            
            char ch = word.charAt(i);

            if(curr.contains(ch) == false)
                curr.set(ch);
        
            curr = curr.get(ch);
        }
        
        curr.increasePref();
        curr.increaseFreq();
    }

    public int countWordsEqualTo(String word) {
        Node curr = root;
        
        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);

            if(curr.contains(ch) == false)
                return 0;
        
            curr = curr.get(ch);
        }
        
        return curr.getFreq();
    }

    public int countWordsStartingWith(String word) {
        Node curr = root;
        
        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);

            if(curr.contains(ch) == false)
                return 0;
        
            curr = curr.get(ch);
        }
        
        return curr.getPref();
    }

    public void erase(String word) {
        if(countWordsEqualTo(word) == 0){
            return;
        }
        
        Node curr = root;
        
        for(int i=0; i<word.length(); i++){
            curr.decreasePref();
            char ch = word.charAt(i);
            curr = curr.get(ch);
        }
        
        curr.decreasePref();
        curr.decreaseFreq();
    }

}