package myanswers.standards;

import org.junit.Test;

public class LruCache2Test {
    @Test
    public void test(){
        LruCache2 cache2 = new LruCache2(3);
        cache2.put(1, 1);
        cache2.put(2, 2);
        cache2.put(3, 3);

        System.out.println(cache2.get(1));

        cache2.put(3, 3);
        System.out.println(cache2.get(2));
        cache2.put(4, 4);
        System.out.println(cache2.get(1));
        System.out.println(cache2.get(3));
        System.out.println(cache2.get(4));
    }
}
