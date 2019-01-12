package data;
import java.util.ArrayList;

public class SurveyAnswer {
    private final String timeStamp;
    private final String age;
    private final String gender;
    private final String shoeSize;
    private final String height;
    private final String degree;
    private final String motivation;
    private final String programmingLanguages;
    private final String phoneOS;

    private final ArrayList<String> interests= new ArrayList<>();
    private final String interest1_efficientDatabases;
    private final String interest2_predicativeModels;
    private final String interest3_similarObjects;
    private final String interest4_dataVisualization;
    private final String interest5_setPatterns;
    private final String interest6_sequencePatterns;
    private final String interest7_graphPatterns;
    private final String interest8_textPatterns;
    private final String interest9_imagePatterns;
    private final String interest10_algorithms;
    private final String interest11_tools;

    private final String gamesPlayed;
    private final String commute;

    private final String checkpoint1;
    private final String checkpoint2;
    private final String checkpoint3;
    private final String checkpoint4;
    private final String checkpoint5;
    private final String checkpoint6;
    private final String checkpoint7;
    private final String checkpoint8;
    private final String checkpoint9;
    private final String checkpoint10;
    private final String checkpoint11;
    private final String checkpoint12;
    private final String checkpoint13;
    private final String checkpoint14;
    private final String checkpoint15;

    private final String randomNo;
    private final String wutIsThis;
    private final String pickedNo;

    private final String favFilm;
    private final String favTVShow;
    private final String favGame;

    private final String seatRow;
    private final String seatNo;


/*
    public SurveyAnswer(String timeStamp, String age, String gender, String shoeSize, String height, String degree)
    {
        this(timeStamp, age, gender, shoeSize, height, degree);
        // /this(sepal_length,sepal_width,petal_length,petal_width,ResolveIrisClass(iris_class));
    }
*/

    public SurveyAnswer(String timeStamp, String age, String gender, String shoeSize, String height, String degree, String motivation,
                        String programmingLanguages, String phoneOS, String interest1_efficientDatabases, String interest2_predicativeModels,
                        String interest3_similarObjects, String interest4_dataVisualization, String interest5_setPatterns, String interest6_sequencePatterns,
                        String interest7_graphPatterns, String interest8_textPatterns, String interest9_imagePatterns, String interest10_algorithms,
                        String interest11_tools, String gamesPlayed, String commute, String checkpoint1, String checkpoint2, String checkpoint3,
                        String checkpoint4, String checkpoint5, String checkpoint6, String checkpoint7, String checkpoint8, String checkpoint9,
                        String checkpoint10, String checkpoint11, String checkpoint12, String checkpoint13, String checkpoint14, String checkpoint15,
                        String randomNo, String wutIsThis, String pickedNo, String favFilm, String favTVShow, String favGame, String seatRow, String seatNo)
    {
        this.timeStamp = timeStamp;
        this.age= age;
        this.gender = gender;
        this.shoeSize = shoeSize;
        this.height = height;
        this.degree = degree;
        this.motivation = motivation;
        this.programmingLanguages = programmingLanguages;
        this.phoneOS = phoneOS;

        this.interest1_efficientDatabases = interest1_efficientDatabases;
        this.interest2_predicativeModels = interest2_predicativeModels;
        this.interest3_similarObjects = interest3_similarObjects;
        this.interest4_dataVisualization = interest4_dataVisualization;
        this.interest5_setPatterns = interest5_setPatterns;
        this.interest6_sequencePatterns = interest6_sequencePatterns;
        this.interest7_graphPatterns = interest7_graphPatterns;
        this.interest8_textPatterns = interest8_textPatterns;
        this.interest9_imagePatterns = interest9_imagePatterns;
        this.interest10_algorithms = interest10_algorithms;
        this.interest11_tools = interest11_tools;

        this.interests.add(this.interest1_efficientDatabases);
        this.interests.add(this.interest2_predicativeModels);
        this.interests.add(this.interest3_similarObjects);
        this.interests.add(this.interest4_dataVisualization);
        this.interests.add(this.interest5_setPatterns);
        this.interests.add(this.interest6_sequencePatterns);
        this.interests.add(this.interest7_graphPatterns);
        this.interests.add(this.interest8_textPatterns);
        this.interests.add(this.interest9_imagePatterns);
        this.interests.add(this.interest10_algorithms);
        this.interests.add(this.interest11_tools);


        this.gamesPlayed = gamesPlayed;
        this.commute = commute;
        this.checkpoint1 = checkpoint1;
        this.checkpoint2 = checkpoint2;
        this.checkpoint3 = checkpoint3;
        this.checkpoint4 = checkpoint4;
        this.checkpoint5 = checkpoint5;
        this.checkpoint6 = checkpoint6;
        this.checkpoint7 = checkpoint7;
        this.checkpoint8 = checkpoint8;
        this.checkpoint9 = checkpoint9;
        this.checkpoint10 = checkpoint10;
        this.checkpoint11 = checkpoint11;
        this.checkpoint12 = checkpoint12;
        this.checkpoint13 = checkpoint13;
        this.checkpoint14 = checkpoint14;
        this.checkpoint15 = checkpoint15;
        this.randomNo = randomNo;
        this.wutIsThis = wutIsThis;
        this.pickedNo = pickedNo;
        this.favFilm = favFilm;
        this.favTVShow = favTVShow;
        this.favGame = favGame;
        this.seatRow = seatRow;
        this.seatNo = seatNo;
    }

    public Integer getAge() {
        if (this.age.equals("Twenty-three")) return 23;
        else if (this.age.equals("28 years")) return 28;
        return Integer.parseInt(age);
    }

    public ArrayList<String> getInterests(){
        return interests;
    }
    public String getgamesPlayed() {
        return gamesPlayed;
    }
    public String getDegree() {
        return degree;
    }

    @Override
    public String toString() {
        String output = String.format("Time Stamp: %s | Age: %s | Gender %s | Shoe Size %s | Height %s | Degree %s | Motivation %s | Languages %s | PhoneOS %s \n" +
               "> Interests 1: %s | 2: %s | 3: %s | 4: %s | 5: %s | 6: %s | 7: %s | 8: %s | 9: %s | 10: %s | 11: %s \n" +
               ">> Games Played %s | Commute %s \n" +
               ">>> Checkpoints 1: %s -> 2: %s -> 3: %s -> 4: %s -> 5: %s -> 6: %s -> 7: %s -> 8: %s -> 9: %s -> 10: %s -> 11: %s -> 12: %s -> 13: %s -> 14: %s -> 15: %s \n" +
               ">>>> Random Numbers %s | Wut ?: %s | Number: %s | Favorite Film: %s | Favorite TVShow: %s | Favorite Game: %s \n" +
               ">>>>> Seat row: %s | Seat Number %s \n"
               , this.timeStamp, this.age, this.gender, this.shoeSize, this.height, this.degree, this.motivation, this.programmingLanguages, this.phoneOS,
                this.interest1_efficientDatabases, this.interest2_predicativeModels, this.interest3_similarObjects, this.interest4_dataVisualization, this.interest5_setPatterns, this.interest6_sequencePatterns, this.interest7_graphPatterns, this.interest8_textPatterns, this.interest9_imagePatterns, this.interest10_algorithms, this.interest11_tools,
                this.gamesPlayed, this.commute,
                this.checkpoint1, this.checkpoint2, this.checkpoint3, this.checkpoint4, this.checkpoint5, this.checkpoint6, this.checkpoint7, this.checkpoint8, this.checkpoint9, this.checkpoint10, this.checkpoint11, this.checkpoint12, this.checkpoint13, this.checkpoint14, this.checkpoint15,
                this.randomNo, this.wutIsThis, this.pickedNo, this.favFilm, this.favTVShow, this.favGame, this.seatRow, this.seatNo);

        //String output = " Time Stamp: "+ this.timeStamp + " | Age: "+ this.age +" | Gender = "+this.gender + " | Shoe Size = "+this.shoeSize + " | Height = "+this.height;

        return output;
    }


}
