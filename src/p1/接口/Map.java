package p1.接口;

public interface Map<K,V>{
    //将键值对key-value加入映射 如果已存在则为修改
    public void put(K key, V value);
    //删除指定key的键值对 并返回对应的值value
    public V remove(K key);
    //查找指定key对应的键值对是否存在
    public boolean contains(K key);
    //获取指定key对应的值value
    public V get(K key);
    //修改指定key处的值为新的value
    public void set(K key, V value);
    public int size();
    public boolean isEmpty();
    //获取所有键的Set
    public Set<K> keySet();
    //获取所有值的List
    public List<V> values();
    //获取所有键值对的Set
    public Set<Entry<K,V>> entrySet();
    public interface Entry<K,V> extends Comparable<Entry<K,V>> {
        public K getKey();
        public V getValue();
    }
}