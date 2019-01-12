package Knn;

import java.util.ArrayList;

public class KNNData {
    ArrayList<Float> interests = new ArrayList<>();
    String label;
    String name;

    public KNNData(ArrayList<Float> interests, String label, String name) {
        this.interests = interests;
        this.label = label;
        this.name = name;
    }

    public String toString(){

        String output = "Name: " + name + " > Scors: ";
        for (Float i: interests){
            output += String.format(" %.2f ,",i);
        }
//        output += " -> Degree: " + label +"\n";
        output += " -> Degree: " + label;
        return output;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Float> getInterests() {
        return interests;
    }

    public String getLabel() {
        return label;
    }
}
