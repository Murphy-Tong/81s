package util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Created by tongzyang on 2016/2/20.
 */
public class SoftReferenceMap<K, V> {
    private HashMap<K, SoftValue<V>> map;

    public SoftReferenceMap() {
//        map = new HashMap<K,SoftV<V>>();
        map = new HashMap();
        queue = new ReferenceQueue<>();
    }

    public void clear() {
        clearQueue();
        map.clear();
        queue = null;
        map = null;
        System.gc();
        System.runFinalization();

    }

    private ReferenceQueue<V> queue;

    public void put(K k, V v) {
        clearQueue();
        if (k == null || v == null) {
            return;
        }
        map.put(k, new SoftValue<V>(v, k, queue));
    }

    public V get(K k) {
        clearQueue();
        SoftValue<V> vSoftValue = map.get(k);
        return vSoftValue == null ? null : vSoftValue.get();
    }

    public void clearQueue() {
        SoftValue<V> poll = (SoftValue<V>) queue.poll();
        while (poll != null) {
            if (map.containsKey(poll.k)) {
                map.remove(poll.k);
            }
            poll = (SoftValue<V>) queue.poll();
        }

    }

    private class SoftValue<V> extends SoftReference<V> {

        public K k;

        public SoftValue(V r, K k, ReferenceQueue<? super V> q) {
            super(r, q);
            this.k = k;
        }
    }


}
