package patterns;
import org.junit.Test;
import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * Unit testing for the Apriori algorithm
 */
public class AprioriTest {

    @Test
    public void apriori() {
        List<List<String>> gamesPlayedDB;
        List<List<String>> gamesPlayedDB2;

        gamesPlayedDB = asList( asList("A", "B", "C", "E", "F", "D"),
                                asList("A", "B", "C", "F"),
                                asList("A", "B", "C", "E"),
                                asList("D", "F"),
                                asList("C", "D"),
                                asList("A", "B", "C", "E", "F"),
                                asList("B", "F", "H"),
                                asList("D", "E", "H"),
                                asList("C", "H") );

        gamesPlayedDB2 = asList( asList("A", "C", "D"),
                                 asList("B", "C"),
                                 asList("A", "B", "C", "E"),
                                 asList("B", "E") );

        ArrayList<String> testset11 =  new ArrayList<>(asList("A"));
        ItemSet test11 = new ItemSet(testset11);
        ArrayList<String> testset12 =  new ArrayList<>(asList("B"));
        ItemSet test12 = new ItemSet(testset12);
        ArrayList<String> testset13 =  new ArrayList<>(asList("C"));
        ItemSet test13 = new ItemSet(testset13);
        ArrayList<String> testset14 =  new ArrayList<>(asList("E"));
        ItemSet test14 = new ItemSet(testset14);
        ArrayList<String> testset21 =  new ArrayList<>(asList("B", "E"));
        ItemSet test21 = new ItemSet(testset21);
        ArrayList<String> testset22=  new ArrayList<>(asList("B", "C"));
        ItemSet test22 = new ItemSet(testset22);
        ArrayList<String> testset23 =  new ArrayList<>(asList("A", "C"));
        ItemSet test23 = new ItemSet(testset23);
        Set<ItemSet> expectedOutput = new HashSet<>();
        expectedOutput.add(test11);expectedOutput.add(test12);expectedOutput.add(test13);expectedOutput.add(test14);
        expectedOutput.add(test21);expectedOutput.add(test22);expectedOutput.add(test23);

        assertEquals(expectedOutput, Apriori.apriori( gamesPlayedDB2, 2 ) );
        print( Apriori.apriori( gamesPlayedDB2, 2 ), gamesPlayedDB2);
    }

    @Test
    public void generateFrequentItemSetsLevel1() {
        List<List<String>> gamesPlayedDB2;
        gamesPlayedDB2 = asList( asList("A", "C", "D"),
                asList("B", "C"),
                asList("A", "B", "C", "E"),
                asList("B", "E") );

        ArrayList<String> testset11 =  new ArrayList<>(asList("A"));
        ItemSet test11 = new ItemSet(testset11);
        ArrayList<String> testset12 =  new ArrayList<>(asList("B"));
        ItemSet test12 = new ItemSet(testset12);
        ArrayList<String> testset13 =  new ArrayList<>(asList("C"));
        ItemSet test13 = new ItemSet(testset13);
        ArrayList<String> testset14 =  new ArrayList<>(asList("E"));
        ItemSet test14 = new ItemSet(testset14);
        Hashtable<ItemSet, Integer> expectedOutput = new Hashtable<>();
        expectedOutput.put(test11, 2);
        expectedOutput.put(test12, 3);
        expectedOutput.put(test13, 3);
        expectedOutput.put(test14, 2);

        assertEquals(expectedOutput, Apriori.generateFrequentItemSetsLevel1(gamesPlayedDB2 , 2 ));
        //System.out.println(expectedOutput);
    }

    @Test
    public void generateFrequentItemSets() {
        List<List<String>> gamesPlayedDB2;
        gamesPlayedDB2 = asList( asList("A", "C", "D"),
                asList("B", "C"),
                asList("A", "B", "C", "E"),
                asList("B", "E") );

        ArrayList<String> testset11 =  new ArrayList<>(asList("A"));
        ItemSet test11 = new ItemSet(testset11);
        ArrayList<String> testset12 =  new ArrayList<>(asList("B"));
        ItemSet test12 = new ItemSet(testset12);
        ArrayList<String> testset13 =  new ArrayList<>(asList("C"));
        ItemSet test13 = new ItemSet(testset13);
        ArrayList<String> testset14 =  new ArrayList<>(asList("E"));
        ItemSet test14 = new ItemSet(testset14);
        Hashtable<ItemSet, Integer> lowerLevelOutput = new Hashtable<>();
        lowerLevelOutput.put(test11, 2);
        lowerLevelOutput.put(test12, 3);
        lowerLevelOutput.put(test13, 3);
        lowerLevelOutput.put(test14, 2);

        ArrayList<String> testset21 =  new ArrayList<>(asList("B", "E"));
        ItemSet test21 = new ItemSet(testset21);
        ArrayList<String> testset22=  new ArrayList<>(asList("B", "C"));
        ItemSet test22 = new ItemSet(testset22);
        ArrayList<String> testset23 =  new ArrayList<>(asList("A", "C"));
        ItemSet test23 = new ItemSet(testset23);

        Hashtable<ItemSet, Integer> expectedOutput = new Hashtable<>();
        expectedOutput.put(test21, 2);
        expectedOutput.put(test22, 2);
        expectedOutput.put(test23, 2);

        assertEquals(expectedOutput, Apriori.generateFrequentItemSets(2, gamesPlayedDB2, lowerLevelOutput ));
        //System.out.println(expectedOutput);

    }

    @Test
    public void joinSets() {
        ArrayList<String> testset1 =  new ArrayList<>(asList("A", "B", "C"));
        ArrayList<String> testset2 =  new ArrayList<>(asList("A", "B", "E"));
        ItemSet test1 = new ItemSet(testset1);
        ItemSet test2 = new ItemSet(testset2);
        ArrayList<String> joinedSet = new ArrayList<String>();
        joinedSet.add("A"); joinedSet.add("B"); joinedSet.add("C"); joinedSet.add("E");
        ItemSet newitem = new ItemSet(joinedSet);
        assertEquals(newitem.getSet() ,  Apriori.joinSets(test1,test2 ).getSet() );
    }

    @Test
    public void foundTestFalse() {
        ArrayList<String> testset21 =  new ArrayList<>(asList("A", "B", "C"));
        ArrayList<String> testset22 =  new ArrayList<>(asList("A", "B", "C"));
        ArrayList<String> testset23 =  new ArrayList<>(asList("E", "D", "B"));
        ItemSet test21 = new ItemSet(testset21);
        ItemSet test22 = new ItemSet(testset22);
        ItemSet test23 = new ItemSet(testset23);
        Set<ItemSet> set2 = new HashSet<>();
        set2.add(test22);
        set2.add(test23);
        assertEquals(true, Apriori.found(test21,set2));
    }

    @Test
    public void foundTestTrue() {
        ArrayList<String> testset21 =  new ArrayList<>(asList("A", "B", "C"));
        ArrayList<String> testset22 =  new ArrayList<>(asList("A", "B", "E"));
        ArrayList<String> testset23 =  new ArrayList<>(asList("E", "D", "B"));
        ItemSet test21 = new ItemSet(testset21);
        ItemSet test22 = new ItemSet(testset22);
        ItemSet test23 = new ItemSet(testset23);
        Set<ItemSet> set2 = new HashSet<>();
        set2.add(test22);
        set2.add(test23);
        assertEquals(false, Apriori.found(test21,set2));
    }

    @Test
    public void joinableTrue() {
        ArrayList<String> testset1 =  new ArrayList<>(asList("A", "B", "C"));
        ArrayList<String> testset2 =  new ArrayList<>(asList("A", "B", "E"));
        ItemSet test1 = new ItemSet(testset1);
        ItemSet test2 = new ItemSet(testset2);

        assertEquals(true, Apriori.joinable(test1,test2));
    }

    @Test
    public void joinableFalse() {
        ArrayList<String> testset1 =  new ArrayList<>(asList("A", "B", "C"));
        ArrayList<String> testset2 =  new ArrayList<>(asList("A", "D", "E"));
        ItemSet test1 = new ItemSet(testset1);
        ItemSet test2 = new ItemSet(testset2);

        assertEquals(false, Apriori.joinable(test1,test2));
    }

    private static void print(Set<ItemSet> frequentItemSets, List<List<String>> tupels) {
        for (ItemSet item: frequentItemSets){
            float support = Apriori.countSupport(item, tupels);
            System.out.println(item.set + "<-> " + support);
        }
    }
}