package p6.树与哈希表;

// Tire树 - 字典数
public class Trie {
    private class Node {
        public boolean isWord;
        public int count; // 重复单词的个数
        public AVLTreeMap<Character,Node> childs;
        public Node() {
            this(false);
        }
        public Node (boolean isWord) {
            this.isWord = isWord;
            childs = new AVLTreeMap<>();
            count = 0;
        }
    }

    private Node root;
    private int size;   //添加进来的单词的个数

    public Trie() {
        root = new Node();
        size = 0;
    }
    public int size() {
        return size;
    }

    // 统计一个单词出现的次数
    public int count(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.childs.get(c) == null) {
                return 0;
            }
            cur = cur.childs.get(c);
        }
        if (cur.isWord) {
            return cur.count;
        } else {
            return 0;
        }
    }

    // 添加单词
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.childs.get(c) == null) {
                cur.childs.put(c,new Node());
            }
            cur = cur.childs.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
            cur.count = 1;
        } else {
            cur.count++;
        }
    }

    // 判断是否包含单词
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.childs.get(c) == null) {
                return false;
            }
            cur = cur.childs.get(c);
        }
        return cur.isWord;
    }

    // 是否有前缀
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.childs.get(c) == null) {
                return false;
            }
            cur = cur.childs.get(c);
        }
        return true;
    }
}