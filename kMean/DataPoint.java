package kMean;

public class DataPoint {
    private String name;
    private Float age;
    private Float interestScore;

    public DataPoint(String name, float noGamesPLayed, float interestscore){
        this.name = name;
        this.age = noGamesPLayed;
        this.interestScore= interestscore;
    }

    public Float getAge() {
        return age;
    }

    public Float getInterestscore() {
        return interestScore;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        //return String.format("%3s (%2.2f , %2.2f) ",name , interestScore , noGamesPLayed);
        return String.format("%3s ",name);
    }

}
