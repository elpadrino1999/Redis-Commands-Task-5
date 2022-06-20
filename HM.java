package T5;


import java.util.*;

public class HM {


    static class HashMap<K, V> {
        private class HMNode {
            K key; //objective generic
            V value; //objective generic

            HMNode(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int size; // n
        private LinkedList<HMNode>[] map; // N = map.length

        public HashMap() {
            initmap(16);
            size = 0;
        }

        private void initmap(int N) {
            map = new LinkedList[N];
            for (int m = 0; m < map.length; m++) {
                map[m] = new LinkedList<>();
            }
        }




        private void rehash()   {
            LinkedList<HMNode>[] oba = map;
            initmap(oba.length*2);
            size = 0;
            for (int i = 0;i < oba.length; i++){
                for (HMNode node: oba[i]){
                    set(node.key,node.value);
                }
            }
        }
        private int hashfn(K key){
            int hc = key.hashCode();
            return Math.abs(hc) % map.length;
        }
        private int getIndexWithinMap(K key,int m){
            int c = 0;
            for (HMNode node:map[m]){
                if(node.key.equals(key)){
                    return c;
                }
                c++;
            }
            return -1;
        }

        public void set(K key, V value)   {
            int m = hashfn(key);
            int c = getIndexWithinMap(key,m);
            if(c != -1){
                HMNode node = map[m].get(c);
                node.value = value;
            }else {
                HMNode node = new HMNode(key,value);
                map[m].add(node);
                size++;
            }
            double lambda = size*1.0 / map.length;
            if(lambda>2.0){
                rehash();
            }
        }

        public V get(K key)   {
            int m = hashfn(key);
            int c = getIndexWithinMap(key,m);
            if(c != -1){
                HMNode node = map[m].get(c);
                return node.value;
            }else {
                return null;
            }
        }
        public V unset(K key)   {
            int m = hashfn(key);
            int c = getIndexWithinMap(key,m);
            if(c != -1){
                HMNode node = map[m].remove(c);
                size--;
                return node.value;
            }else {
                return null;
            }
        }

        public int size(){
            return size;
        }

        public void count(V gv) {
            int count = 0;
            System.out.println("The count of "+gv+" is");
            for (int m = 0; m < map.length; m++) {
                for (HMNode node : map[m]) {
                   if(gv.equals(node.value)){
                       count++;
                   }
                }
            }
           if(count > 0){
               System.out.println(count);
           }
           else {
               System.out.println("No such Values found");
           }

        }
        public void update(K key,V value)   {
            V val = get(key);
            if(val!=null){
                set(key, value);
            }
            else {
                System.out.println("No such Variable "+key+" is found");
            }
        }

        public boolean containsKey(K key) {
            int m = hashfn(key);
            int c = getIndexWithinMap(key,m);
            if(c != -1){
                return true;
            }else {
               return false;
            }
        }

            public ArrayList<K> Keyvariables()   {
            ArrayList<K> Keyvariables = new ArrayList<>();
           for(int i = 0; i < map.length;i++){
               for (HMNode node:map[i]){
                   Keyvariables.add(node.key);
               }
           }
           return Keyvariables;
        }
        public ArrayList<V> Values()   {
            ArrayList<V> Values = new ArrayList<>();
           for(int i = 0; i < map.length;i++){
               for (HMNode node:map[i]){
                   Values.add(node.value);
               }
           }
           return Values;
        }
        public void display() {
            for (int m = 0; m < map.length; m++) {
                for (HMNode node : map[m]) {
                    System.out.print( node.key + "-->" + node.value + " ");
                }
            }
        }
    }
}