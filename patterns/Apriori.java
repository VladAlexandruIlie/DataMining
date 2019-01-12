package patterns;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Arrays.copyOf;
import static java.util.Arrays.stream;


public class Apriori {
    public static Set<ItemSet> apriori( List<List<String>> tuples, int supportThreshold ) {
        float startTime = System.nanoTime(); System.out.print("Searchign for items:...");
        Hashtable<ItemSet, Integer> frequentItemSets = generateFrequentItemSetsLevel1( tuples, supportThreshold );
        Set<ItemSet> allfrequentItemSets = new HashSet<>(frequentItemSets.keySet());

        System.out.printf("Found %5d itemsets of length %3d that appear at least %3d times in: %f seconds \n",frequentItemSets.size(), (1), supportThreshold, (float)((System.nanoTime() - startTime)/1000000000.0) );

        int k =1;
        while (!frequentItemSets.isEmpty()){
            startTime = System.nanoTime(); System.out.print("Searchign for items:...");
            frequentItemSets = generateFrequentItemSets( supportThreshold, tuples, frequentItemSets );

            allfrequentItemSets.addAll(frequentItemSets.keySet());

//            System.out.println( "Finding frequent itemsets of length " + (k + 1) + " found " + frequentItemSets.size() );
//            System.out.println(frequentItemSets);

            System.out.printf("Found %5d itemsets of length %3d that appear at least %3d times in: %f seconds \n",frequentItemSets.size(), (k+1), supportThreshold, (float)((System.nanoTime() - startTime)/1000000000.0) );
            k++;
        }
        System.out.println();
        return allfrequentItemSets;
    }

    protected static Hashtable<ItemSet, Integer> generateFrequentItemSets(
                    int supportThreshold, List<List<String>> tupels, Hashtable<ItemSet, Integer> lowerLevelItemSets ) {

        Hashtable<ItemSet, Integer> candidateItemSets = new Hashtable<>();
        List<ItemSet> lowerLevelItemSet = new ArrayList<>();
        lowerLevelItemSet.addAll(lowerLevelItemSets.keySet());

        // TODO: first generate candidate itemsets from the lower level itemsets
        for (int i=0; i< lowerLevelItemSets.keySet().size() -1; i++){
            for (int j=i+1; j< lowerLevelItemSets.keySet().size(); j++){
                ItemSet set1 = lowerLevelItemSet.get(i);
                ItemSet set2 = lowerLevelItemSet.get(j);

                if (joinable(set1, set2)) {
                    ItemSet joinedSet = null;
                    joinedSet = joinSets(set1, set2);
                    int joinedSetSupport = countSupport(joinedSet, tupels);

                    //: now check the support for all candidates and add only those that have enough support to the set
                    if (countSupport(joinedSet, tupels) >= supportThreshold && !found(joinedSet, candidateItemSets.keySet())) {
                        candidateItemSets.put(joinedSet, joinedSetSupport);
                    }
                }
            }
        }
        return candidateItemSets;
    }

    protected static Hashtable<ItemSet, Integer> generateFrequentItemSetsLevel1( List<List<String>> tupels, int supportThreshold ) {
        Hashtable<ItemSet, Integer> Level1 = new Hashtable<ItemSet, Integer>();

        for (List<String> gamesPlayed: tupels){
            for (String game: gamesPlayed){
                ArrayList<String> toAdd = new ArrayList<>();
                toAdd.add(game);
                ItemSet newItem = new ItemSet(toAdd);
                int support = countSupport(newItem, tupels);
                if (support >= supportThreshold) Level1.put(newItem, support);
            }
        }
        return Level1;
    }

    public static ItemSet joinSets(ItemSet first, ItemSet second) {
        ArrayList<String> joinedSet = new ArrayList<>(first.set);

        if (joinable(first, second)) {
            for (String newAttribute : second.set) {
                if (!joinedSet.contains(newAttribute)) {
                    joinedSet.add(newAttribute);
                }
            }
        }
        return new ItemSet(joinedSet);
    }

    protected static boolean joinable(ItemSet first, ItemSet second) {
        if (first.set.size() != second.set.size()) return false;
        for (int i=0; i< first.set.size()-1; i++)
            if (!first.set.get(i).equals(second.set.get(i))) return false;
        return true;
    }

    protected static boolean found(ItemSet joinedSet, Set<ItemSet> itemSets) {
        boolean found = false;

        for (ItemSet item: itemSets){
            boolean itemMatch = true;

            for (String s :item.set){
                if (!joinedSet.set.contains(s)) itemMatch=false;
            }
            if (item.set.isEmpty()) itemMatch = false;

            if (itemMatch) found = true;
        }
        return found;
    }

    protected static int countSupport( ItemSet itemSet, List<List<String>> tupels ) {
        // Assumes that items in ItemSets and transactions are both unique
        int itemSupport = 0;

        for (List<String> answer : tupels) {
            boolean found = true;
            for (String game : itemSet.set){
                boolean ok = false;
                for (String otherGame : answer){ if (game.equals(otherGame)) ok = true; }
                if (!ok) found =false;
            }
            if (found) itemSupport++;
        }
        return itemSupport;
    }

}
