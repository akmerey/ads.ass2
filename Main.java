package com.company;

public class Main {
    class DoublyLinkedListNode {
        DoublyLinkedListNode next, prev;
        int data;
        DoublyLinkedListNode(int data)
        {
            this.data = data;
            next = null;
            prev = null;
        }
    }
    class HashTableChainingDoublyLinkedList {
        DoublyLinkedListNode[] hashTable;
        int size;

        HashTableChainingDoublyLinkedList(int hashTableSize)
        {
            hashTable = new DoublyLinkedListNode[hashTableSize];
            size = 0;
        }
        public boolean isEmpty() { return size == 0; }
        public void clear()
        {
            int len = hashTable.length;
            hashTable = new DoublyLinkedListNode[len];
            size = 0;
        }

        public int getSize() { return size; }
        public void insert(int value)
        {
            size++;

            int position = hash(value);
            DoublyLinkedListNode node
                    = new DoublyLinkedListNode(value);

            DoublyLinkedListNode start = hashTable[position];

            if (hashTable[position] == null)
                hashTable[position] = node;
            else {
                node.next = start;
                start.prev = node;
                hashTable[position] = node;
            }
        }
        public void remove(int value)
        {
            try {
                int position = hash(value);

                DoublyLinkedListNode start
                        = hashTable[position];

                DoublyLinkedListNode end = start;
                if (start.data == value) {
                    size--;
                    if (start.next == null) {
                        // removing the value
                        hashTable[position] = null;
                        return;
                    }

                    start = start.next;
                    start.prev = null;
                    hashTable[position] = start;

                    return;
                }
                while (end.next != null
                        && end.next.data != value)
                    end = end.next;
                if (end.next == null) {
                    System.out.println("\nElement not found\n");
                    return;
                }

                size--;

                if (end.next.next == null) {
                    end.next = null;
                    return;
                }

                end.next.next.prev = end;
                end.next = end.next.next;

                hashTable[position] = start;
            }
            catch (Exception e) {
                System.out.println("\nElement not found\n");
            }
        }
        private int hash(Integer x)
        {
            int hashValue = x.hashCode();

            hashValue %= hashTable.length;

            if (hashValue < 0)
                hashValue += hashTable.length;

            return hashValue;
        }
        public void printHashTable()
        {
            System.out.println();
            for (int i = 0; i < hashTable.length; i++) {
                System.out.print("At " + i + ":  ");

                DoublyLinkedListNode start = hashTable[i];

                while (start != null) {
                    System.out.print(start.data + " ");
                    start = start.next;
                }

                System.out.println();
            }
        }
        public static void main(String[] args)
        {
            HashTableChainingDoublyLinkedList hashTab
                    = new HashTableChainingDoublyLinkedList(5);

            hashTab.insert(99);
            hashTab.insert(23);
            hashTab.insert(36);
            hashTab.insert(47);
            hashTab.insert(80);

            hashTab.printHashTable();

            hashTab.insert(92);
            hashTab.insert(49);

            hashTab.printHashTable();

            hashTab.remove(99);

            hashTab.printHashTable();

            hashTab.clear();

            hashTab.printHashTable();

            System.out.println(hashTab.isEmpty());
        }
    }
}
