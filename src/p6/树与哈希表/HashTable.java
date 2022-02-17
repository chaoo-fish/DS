package p6.树与哈希表;

import p1.接口.List;
import p1.接口.Map;
import p1.接口.Set;
import p3.链式结构.LinkedList;
//底层由哈希表实现的映射
public class HashTable<K extends Comparable<K>, V> implements Map<K,V> {
    //哈希表要缩容和扩容 但不是简单的×2÷2 而是利用素数表
    private static final int[] capacity = {
            53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 768433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741
    };
    //哈希表中每个桶的上限和下限 超过上限需要扩容 小于下限需要缩容 避免极端高瘦或矮胖的情况
    //主要因为每个桶最多有N/M个元素
    private static final int upperTol = 10;
    private static final int lowerTol = 2;

    //素数表中的角标 用来标记当前哈希表的长度 扩容++ 缩容--
    private int capacityIndex = 0;
    //M当前哈希表中桶的个数
    private int M;
    //当前哈希表中元素的个数
    private int size;
    //桶的定义:平衡树的数组
    private AVLTreeMap<K,V>[] hashTable;

    public HashTable() {
        M = capacity[capacityIndex];
        hashTable = new AVLTreeMap[M];
        for (int i = 0; i < M; i++) {
            hashTable[i] = new AVLTreeMap<>();
        }
    }

    private int hash(K key) {
        //获取key的哈希值 然后纠正负数 再对M取余 得到要去的桶的角标
        return key.hashCode() & 0x7fffffff % M;
    }

    @Override
    public void put(K key, V value) {
        int index = hash(key);
        AVLTreeMap<K,V> map = hashTable[index];
        if (map.contains(key)) {
            //如果该桶中已经包含该键key所对应的键值对 - 修改
            map.put(key,value);
        } else {
            //在该桶中新增一个键值对
            map.put(key,value);
            size++;
            //新增元素之后 考虑扩容
            if (size > upperTol * M && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    @Override
    public V remove(K key) {
        int index = hash(key);
        AVLTreeMap<K,V> map = hashTable[index];
        V ret = null;
        if (map.contains(key)) {
            ret = map.remove(key);
            size--;
            //删除元素之后 考虑缩容
            if (size < lowerTol * M && capacityIndex - 1 >= 0) {
                capacityIndex--;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }

    private void resize(int newM) {
        AVLTreeMap<K,V>[] newHashTable = new AVLTreeMap[newM];
        for (int i = 0; i < newHashTable.length; i++) {
            newHashTable[i] = new AVLTreeMap<>();
        }
        M = newM;
        for (int i = 0; i < hashTable.length; i++) {
            AVLTreeMap<K,V> map = hashTable[i];
            for (K key : map.keySet()) {
                newHashTable[hash(key)].put(key,map.get(key));
            }
        }
        hashTable = newHashTable;
    }

    @Override
    public boolean contains(K key) {
        int index = hash(key);
        AVLTreeMap<K,V> map  = hashTable[index];
        return map.contains(key);
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        AVLTreeMap<K,V> map  = hashTable[index];
        return map.get(key);
    }

    @Override
    public void set(K key, V value) {
        int index = hash(key);
        AVLTreeMap<K,V> map  = hashTable[index];
        map.set(key,value);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Set<K> keySet() {
        TreeSet<K> set = new TreeSet<>();
        for (int i = 0; i < hashTable.length; i++) {
            AVLTreeMap<K,V> map = hashTable[i];
            for (K key : map.keySet()) {
                set.add(key);
            }
        }
        return set;
    }

    @Override
    public List<V> values() {
        LinkedList<V> list = new LinkedList<>();
        for (int i = 0; i < hashTable.length; i++) {
            AVLTreeMap<K,V> map = hashTable[i];
            for (V value : map.values()) {
                list.add(value);
            }
        }
        return list;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        TreeSet<Entry<K,V>> set = new TreeSet<>();
        for (int i = 0; i < hashTable.length; i++) {
            AVLTreeMap<K,V> map = hashTable[i];
            for (Entry entry : map.entrySet()) {
                set.add(entry);
            }
        }
        return set;
    }
}