package com.example.structure;

import com.example.data.HashNode;

import java.util.ArrayList;
import java.util.Objects;

public class HashMap<K, V> {
    private ArrayList<HashNode<K, V>> bucketArray;
    private int buckets;
    private int size;

    public HashMap() {
        bucketArray = new ArrayList<>();
        buckets = 10;

        // init bucket array to prevent index out of
        // bounds exception for first time insert / retrieval
        for (int index = 0; index < buckets; index++) {
            bucketArray.add(null);
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int hashCode(K key) {
        return Objects.hashCode(key);
    }

    /*
    This is the hashing logic which produces\
    a unique array index to do inserts and
    lookups
     */
    private int getBucketIndex(K key) {
        int hashCode = hashCode(key);
        int arrayIndex = hashCode % buckets;

        arrayIndex = arrayIndex < 0 ? arrayIndex * -1 : arrayIndex;

//        if (arrayIndex < 0) {
//            arrayIndex = arrayIndex * -1;
//        }

        System.out.println("The index of => " + key + " is => " + arrayIndex);

        return arrayIndex;
    }

    public void put(K key, V value) {
        // find the index of where we should put the value in
        // the bucket array
        int index = getBucketIndex(key);
        int hashcode = hashCode(key);

        // create head and make it point to where the index is
        HashNode<K, V> head = bucketArray.get(index);

        HashNode<K, V> newNode = new HashNode<>(key, value, hashcode);

        // head could be pointing to a list, so we need
        // to loop over the potential list and insert a
        // new node at the end.
        if (head == null) {
            bucketArray.set(index, newNode);
            size++;
        } else { // TODO: 5/11/22 We need to check for duplicate keys
            // this is logic from class mate
            newNode.setNext(head.getNext());
            head.setNext(newNode);
            size++;
        }

        // If load factor goes beyond threshold, then
        // double hash table size
        if ((1.0 * size) / buckets >= 0.7) {
            ArrayList<HashNode<K, V> > temp = bucketArray;
            bucketArray = new ArrayList<>();
            buckets = 2 * buckets;
            size = 0;
            for (int i = 0; i < buckets; i++)
                bucketArray.add(null);

            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    put(headNode.getKey(), headNode.getValue());
                    headNode = headNode.getNext();
                }
            }
        }
    }
}
