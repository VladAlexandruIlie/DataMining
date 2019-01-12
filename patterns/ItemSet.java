package patterns;

import java.util.*;

/***
 * The ItemSet class is used to store information concerning a single transaction.
 * Should not need any changes.
 *
 */
public class ItemSet {
	
	/***
	 * The PRIMES array is internally in the ItemSet-class' hashCode method
	 */
	private static final int[] PRIMES = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199  };
    final ArrayList<String> set;

    /***
     * Creates a new instance of the ItemSet class.
     * @param set Transaction content
     */
    public ItemSet( ArrayList<String> set ) {
        this.set = set;
        Collections.sort(this.set, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
    }

    @Override
    /**
     * hashCode functioned used internally in Hashtable
     */
    public int hashCode() {
        int code = 0;
        for (int i = 0; i < set.size(); i++) {
            code += set.get(i).hashCode() * PRIMES[i];
        }
        return code;
    }

    public boolean contains(String query ) {
        boolean ok = false;
        for (String s : set) {
            if (s.equals(query)) ok = true;
        }
        return ok;
    }

    @Override
    public String toString(){
        String output = "[ ";
        for (int i=0; i< set.size() - 1; i++) output = output +  String.format(" %20s ", set.get(i)) ;
        output = output + set.get(set.size()-1) + " ]";
        return output;
    }

    @Override
    /**
     * Used to determine whether two ItemSet objects are equal
     */
    public boolean equals( Object o ) {
        if (!(o instanceof ItemSet)) {
            return false;
        }
        ItemSet other = (ItemSet) o;
        if (other.set.size() != this.set.size()) {
            return false;
        }
        for (int i = 0; i < set.size(); i++) {
            if (!set.get(i).equals(other.set.get(i))) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> getSet() {
        return set;
    }
}

