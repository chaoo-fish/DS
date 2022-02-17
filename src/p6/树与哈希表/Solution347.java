package p6.树与哈希表;

import java.util.PriorityQueue;
import java.util.TreeMap;

public class Solution347 {
    public int[] topKFrequent(int[] nums, int k) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        //Java自带的优先队列是最小堆构建的 值越小越往上走
        PriorityQueue<Freq> queue = new PriorityQueue();
        for (int num : map.keySet()) {
            if (queue.size() < k) {
                queue.add(new Freq(num, map.get(num)));
            } else if (map.get(num) > queue.peek().count){
                queue.remove();
                queue.add(new Freq(num, map.get(num)));
            }
        }
        int[] temp = new int[k];
        for (int i = 0; i < k; i++) {
            temp[i] = queue.remove().num;
        }
        return temp;
    }
    class Freq implements Comparable<Freq> {
        int num;    //数字
        int count;  //数字出现的次数
        public Freq(int num, int count) {
            this.num = num;
            this.count = count;
        }
        @Override
        public int compareTo(Freq o) {
            return this.count - o.count;
        }
    }
}