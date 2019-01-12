package PreProcessing;

import Knn.KNNData;
import data.SurveyAnswer;
import kMean.DataPoint;
import patterns.ItemSet;

import java.util.*;

public class DataRepository {
    private ArrayList<SurveyAnswer> answers;

    // Loading the data
    public DataRepository(ArrayList<SurveyAnswer> answers) {
        this.answers = answers;
    }

    public void printNumberOfEntries() {
        System.out.println("Loaded " + answers.size() + " survey answers.");
    }

    // K-means cleaning
    public DataRepository cleanData() {
        ArrayList<SurveyAnswer> cleanedAnswers = new ArrayList<>(answers);

        for (SurveyAnswer answer : answers) {
            if (answer.getAge() < 20 || answer.getAge() > 40) cleanedAnswers.remove(answer);
        }




        DataRepository cleanedDatRepository = new DataRepository(cleanedAnswers);

        System.out.println("Removed " + (answers.size() - cleanedAnswers.size()) + " instances due to faulty data ");
        System.out.println();
        return cleanedDatRepository;
    }

    // Min-Max Normalization
    private float findHigh(List<Float> totalScores) {
        float dataHigh = -1;

        for (Float score : totalScores) {
            if (score > dataHigh) dataHigh = (float) score;
        }
        return dataHigh;
    }
    private float findLow(List<Float> totalScores) {
        float dataLow = 10000;

        for (Float score : totalScores) {
            if (score < dataLow) dataLow = (float) score;
        }
        return dataLow;
    }
    private float MinMaxNormalize(Float score, float dataHigh, float dataLow, float normalizedHigh, float normalizedLow) {
        if (dataHigh == dataLow) return (float) 1 / 2;
        else return ((score - dataLow) / (dataHigh - dataLow)) * (normalizedHigh - normalizedLow) + normalizedLow;
    }

    // Age pre-processing
    private List<Integer> getAllAges() {
        List<Integer> allAges = new ArrayList<Integer>();
        for (int i = 0; i < answers.size(); i++) {
            allAges.add(answers.get(i).getAge());
        }
        return allAges;
    }
    public ArrayList<Integer> getCleanedAges() {
        // Clean data using Interquartile Range
        ArrayList<Integer> ages = new ArrayList<Integer>(getAllAges());

        ArrayList<Integer> cleanedAges = new ArrayList<>(ages);
        Collections.sort(cleanedAges);

        int mid = cleanedAges.size() / 2;
        int midLow = mid /2 ;
        int midHi = mid + (cleanedAges.size() - mid)/2;
        float IQR = cleanedAges.get(midHi) - cleanedAges.get(midLow);

        float mean  = 0;
        for (int i: ages){
            mean = mean + i;
        }
        mean = mean / ages.size();

        ArrayList<Integer> cleanedAgesCopy = new ArrayList<Integer>(cleanedAges);
        for (Integer i: cleanedAges){
            if (Math.abs((float)i - mean) > 1.5 * IQR) cleanedAgesCopy.remove(i);
        }
        cleanedAges = cleanedAgesCopy;

        ArrayList<Integer> agesCopy = new ArrayList<Integer>(ages);
        for (Integer i : ages){
            if (!cleanedAges.contains(i)) agesCopy.remove(i);
        }

        System.out.println(ages.size() - agesCopy.size());
        ages = agesCopy;

        return ages;
    }
    private ArrayList<Float> getNormalizedAges() {
        ArrayList<Integer> ages = new ArrayList<Integer>(getAllAges());
        ArrayList<Float> normalizedAges = new ArrayList<Float>();

        List<Float> newAges = new ArrayList<>();
        for (Integer i : ages) {
            newAges.add((float) i);
        }
        float dataHigh = findHigh(newAges);
        float dataLow = findLow(newAges);
        float normalizedHigh = 1;
        float normalizedLow = 0;

        for (Integer noGames : ages) {
            float normalized = MinMaxNormalize((float) noGames, dataHigh, dataLow, normalizedHigh, normalizedLow);
            normalizedAges.add(normalized);
        }
        return normalizedAges;
    }

    // Degree pre-processing
    private List<String> getAllDegrees() {
        List<String> allDegrees = new ArrayList<String>();
        for (int i = 0; i < answers.size(); i++) {
            allDegrees.add(answers.get(i).getDegree());
        }
        return allDegrees;

    }


    // Games played pre-processing
    public List<List<String>> getAllGamesPlayed() {
        List<List<String>> allGamesPlayed = new ArrayList<List<String>>();
        for (int i = 0; i < answers.size(); i++) {
            List<String> gamesPlayed = new ArrayList<>();
            gamesPlayed.addAll(Arrays.asList(answers.get(i).getgamesPlayed().split(";")));
            allGamesPlayed.add(gamesPlayed);
        }

        return allGamesPlayed;
    }
    private List<Integer> getNumberOfGamesPlayed() {
        List<List<String>> gamesPlayed = new ArrayList<List<String>>(getAllGamesPlayed());
        List<Integer> nrGamesPlayed = new ArrayList<>();
        for (List<String> games : gamesPlayed) {
            List<String> exception = new ArrayList<String>(Arrays.asList("I have not played any of these games"));
            if (games.equals(exception)) nrGamesPlayed.add(0);
            else nrGamesPlayed.add(games.size());
        }
        return nrGamesPlayed;
    }
    private ArrayList<Float> getNormalizedNoGamesPlayed() {
        ArrayList<Float> noGamesPlayed = new ArrayList<>();
        List<Integer> gamesPlayed = new ArrayList<>(getNumberOfGamesPlayed());

        List<Float> newNoGamesPlayed = new ArrayList<>();
        for (Integer i : gamesPlayed) {
            newNoGamesPlayed.add((float) i);
        }
        float dataHigh = findHigh(newNoGamesPlayed);
        float dataLow = findLow(newNoGamesPlayed);
        float normalizedHigh = 1;
        float normalizedLow = 0;

        for (Integer noGames : gamesPlayed) {
            float normalized = MinMaxNormalize((float) noGames, dataHigh, dataLow, normalizedHigh, normalizedLow);
            noGamesPlayed.add(normalized);
        }

        return noGamesPlayed;
    }


    // Interest Score pre-processing
    private ArrayList<ArrayList<Integer>> getAllInterestsScores() {
        ArrayList<ArrayList<Integer>> interestScores = new ArrayList<ArrayList<Integer>>();

        Integer score1 = 0;
        for (SurveyAnswer tuple : answers) {
            List<String> interests = tuple.getInterests();
            ArrayList<Integer> floatInterests = new ArrayList<Integer>();

            for (String interest : interests) {
                switch (interest) {
                    case "Not interested":
                        score1 = 1;
                        break;
                    case "Meh":
                        score1 = 2;
                        break;
                    case "Sounds interesting":
                        score1 = 3;
                        break;
                    case "Very interesting":
                        score1 = 4;
                        break;
                }
                floatInterests.add(score1);
            }
            interestScores.add(floatInterests);
        }
        return interestScores;
    }
    private ArrayList<ArrayList<Float>> getNormalizedScoresMatrix() {
        List<List<Integer>> allInterestScores = new ArrayList<List<Integer>>(getAllInterestsScores());
        ArrayList<ArrayList<Float>> finalScores = new ArrayList<>();

        for (List<Integer> interestScores : allInterestScores) {
            ArrayList<Float> normalizedScoreEntry = new ArrayList<>();

            List<Float> newInterestScores = new ArrayList<>();
            for (Integer i : interestScores) {
                newInterestScores.add((float) i);
            }
            float dataHigh = findHigh(newInterestScores);
            float dataLow = findLow(newInterestScores);
            float normalizedHigh = 10;
            float normalizedLow = 1;

            for (Integer score : interestScores) {
                float normalizedScore = MinMaxNormalize((float) score, dataHigh, dataLow, normalizedHigh, normalizedLow);
                normalizedScoreEntry.add(normalizedScore);
            }

            finalScores.add(normalizedScoreEntry);
        }
        return finalScores;
    }
    private List<Float> getSummedInterestsScore() {
        List<List<Float>> allInterestScores = new ArrayList<List<Float>>(getNormalizedScoresMatrix());
        List<Float> finalScores = new ArrayList<>();

        for (List<Float> interestScores : allInterestScores) {
            Float totalScore = (float) 0;
            for (Float score : interestScores) {
                totalScore = totalScore + score;
            }
            finalScores.add(totalScore);
        }
        return finalScores;
    }
    private ArrayList<Float> getNormalizedTotalInterestScore() {
        ArrayList<Float> normalizedScores = new ArrayList<Float>();
        List<Float> totalScores = getSummedInterestsScore();

        float dataHigh = findHigh(totalScores);
        float dataLow = findLow(totalScores);
        float normalizedHigh = 1;
        float normalizedLow = 0;

        for (Float score : totalScores) {
            float normalizedScore = MinMaxNormalize(score, dataHigh, dataLow, normalizedHigh, normalizedLow);
            normalizedScores.add(normalizedScore);
        }
        return normalizedScores;
    }


    // Apriori
    public void printTheLongestSequences(Set<ItemSet> frequentItemSets) {
        System.out.println("The longest sequences: ");
        int max = -1;
        for (ItemSet item : frequentItemSets) {
            if (item.getSet().size() > max) max = item.getSet().size();
        }

        for (ItemSet item : frequentItemSets) {
            if (item.getSet().size() == max) {
                System.out.println(item.getSet());
            }
        }
    }

    // K-Means
    public List<DataPoint> getKMeanData() {
        ArrayList<Float> agesData = new ArrayList<Float>(getNormalizedAges());
        ArrayList<Float> interestsData = new ArrayList<>(getNormalizedTotalInterestScore());
        ArrayList<Float> gamesPlayedData = new ArrayList<>(getNormalizedNoGamesPlayed());

        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        for (int i = 0; i < interestsData.size(); i++) {
            String name = "P" + i;
            DataPoint dataPoint = new DataPoint(name, agesData.get(i), interestsData.get(i));
            dataPoints.add(dataPoint);
        }
        return dataPoints;
    }

    // K-NN
    public List<KNNData> getKNNData(){
        List<KNNData> knnDataPoints = new ArrayList<>();
        ArrayList<ArrayList<Float>> allinterestScores = new ArrayList<ArrayList<Float>>(getNormalizedScoresMatrix());
        ArrayList<String> labels = new ArrayList<>(getAllDegrees());

        for (int i= 0; i<labels.size(); i++){
            ArrayList<Float> interestScores = allinterestScores.get(i);
            String label = labels.get(i);
            String name = "P" +(i+1) ;

            KNNData kNNDataPoint = new KNNData(interestScores, label, name);
            knnDataPoints.add(kNNDataPoint);
        }
        return knnDataPoints;
    }
    public ArrayList<KNNData> getTestData(ArrayList<KNNData> kNNData, int testSize) {
        ArrayList<KNNData> toTestData = new ArrayList<KNNData>();


        for (int i = 0; i < testSize; i++) {
            ArrayList<KNNData> randomizedDataPoints = new ArrayList<KNNData>(getKNNData());

            for (int j = 0; j < 5; j++) {
                Collections.shuffle(randomizedDataPoints);
            }

            int len = randomizedDataPoints.size();
            int idx = new Random().nextInt(len);

            KNNData dataPoint = getKNNData().get(idx);

            if (toTestData.contains(dataPoint)) return getTestData(kNNData, testSize);
                else {
                    ArrayList<Float> interests = dataPoint.getInterests();
                    //String label = "Unknown";
                    String label = dataPoint.getLabel();
                    String name = dataPoint.getName();
                    KNNData knnData =  new KNNData(interests, label, name);

                    toTestData.add(knnData);
            }
        }
        return toTestData;
    }
    public ArrayList<KNNData> getTrainingData(ArrayList<KNNData> kNNData, ArrayList<KNNData> toTestData) {
        ArrayList<KNNData> knnData = new ArrayList<>(kNNData);

        ArrayList<KNNData> trainingData = new ArrayList<>();


        for (KNNData knnDataPoint: knnData){

            boolean found = false;
            for (KNNData toTestPoint: toTestData){
                if (knnDataPoint.getName().equals(toTestPoint.getName())) found = true;
            }

            if(!found) trainingData.add(knnDataPoint);
        }
        return trainingData;

    }

    // Support printing functions
    // ages
    public void printAllAges() {
        List<Integer> allAges = new ArrayList<Integer>(getAllAges());
        for (Integer age : allAges) {
            System.out.printf(" %d ", age);
        }
        System.out.println();
        System.out.println();
    }
    public void printNormalizedAges() {
        List<Float> normalizedAges = getNormalizedAges();
        System.out.println("Complete list of normalized ages: ");
        for (Float age : normalizedAges) {
            System.out.printf(" %.3f ", age);
        }
        System.out.println();
        System.out.println();

    }

    // degree
    private void printDegrees() {
        List<String> allDegrees = new ArrayList<String>(getAllDegrees());
        for (String degree : allDegrees) {
            System.out.printf(" %s ", degree);
        }
        System.out.println();
        System.out.println();
    }

    // games played
    public void printGamesPlayed() {
        System.out.println("Printing survey answers for games played: ");
        List<List<String>> allgamesPlayed = new ArrayList<List<String>>(getAllGamesPlayed());

        for (List<String> gamesPlayed : allgamesPlayed) {
            System.out.println(gamesPlayed);
        }
        System.out.println();
    }
    public void printNumberOfGamesPlayed() {
        System.out.println("Printing number of games played for each survey answer: ");
        List<Integer> gamesPlayed = new ArrayList<Integer>(getNumberOfGamesPlayed());

        for (Integer score : gamesPlayed) {
            System.out.printf(" %5d, ", score);
        }
        System.out.println();
        System.out.println();
    }
    public void printNormalizedNoOfGamesPlayed() {
        List<Float> normalizedTotalScores = getNormalizedNoGamesPlayed();
        System.out.println("Complete list of normalized number of games played: ");
        for (Float score : normalizedTotalScores) {
            System.out.printf(" %.3f , ", score);
        }
        System.out.println();
        System.out.println();
    }

    // interests
    public void printRawInterestData() {
        String output = "Printing survey answers for topic interests: \n";

        for (SurveyAnswer tuple : answers) {
            ArrayList<String> interests = tuple.getInterests();

            output = output + String.format("Interests 1: %s | 2: %s | 3: %s | 4: %s | 5: %s | 6: %s | 7: %s | 8: %s | 9: %s | 10: %s | 11: %s \n",
                    interests.get(0), interests.get(1), interests.get(2), interests.get(3), interests.get(4), interests.get(5),
                    interests.get(6), interests.get(7), interests.get(8), interests.get(9), interests.get(10));
        }
        System.out.println(output);
    }
    public void printInterestsScores() {
        ArrayList<ArrayList<Integer>> interestsScores = getAllInterestsScores();
        System.out.println("Printing interests score matrix: ");
        for (List<Integer> interests : interestsScores) {
            for (int i = 0; i < interests.size() - 1; i++) {
                System.out.print(interests.get(i) + ", ");
            }
            System.out.println(interests.get(interests.size() - 1));

//            int score = 0;
//            for (int i=0; i<interests.size()-1; i++){
//                score = score + interests.get(i);
//            }
//            System.out.println(" -> Total: " + score);
        }
        System.out.println();
    }
    public void printNormalizedScoreMatrix() {
        ArrayList<ArrayList<Float>> interestsScores = getNormalizedScoresMatrix();
        System.out.println("Printing normalized interests score matrix: ");
        for (List<Float> interests : interestsScores) {
            for (int i = 0; i < interests.size() - 1; i++) {
                System.out.printf("%.3f ", interests.get(i));
            }
            System.out.printf(" %.3f \n", interests.get(interests.size() - 1));
        }
        System.out.println();
    }
    public void printSummedScores() {
        List<Float> totalInterestsScore = getSummedInterestsScore();
        System.out.println("Complete list of summed topic interests scores: ");
        for (Float score : totalInterestsScore) {
            System.out.printf(" %.2f ", score);
        }
        System.out.println();
        System.out.println();
    }
    public void printNormalizedTotalScores() {
        List<Float> normalizedTotalScores = getNormalizedTotalInterestScore();
        System.out.println("Complete list of normalized interest scores: ");
        for (Float score : normalizedTotalScores) {
            System.out.printf(" %.3f ", score);
        }
        System.out.println();
        System.out.println();
    }

    // kNN predictions
    public void printKnnData() {
        System.out.println("Printing data points used for the K-NN algorithm: ");
        List<KNNData> knnData =  new ArrayList<>(getKNNData());

        for (KNNData knnDataPoint: knnData) {
            String output = "Name: " + knnDataPoint.getName() + " > Scors: ";
            for (Float i : knnDataPoint.getInterests()) {
                output += String.format(" %.2f ,", i);
            }
            output += " -> Known degree: " + knnDataPoint.getLabel();
            System.out.println(output);
        }
        System.out.println();
    }
    public void printPredictions(HashMap<KNNData, String> predictions) {
        System.out.println("Printing the known test set with corresponding predicted label: ");
        for (KNNData knnData: predictions.keySet()){

            String output = "Name: " + knnData.getName() + " > Scors: ";
            for (Float i: knnData.getInterests()){
                output += String.format(" %.2f ,",i);
            }
            output += " -> Known degree: " + knnData.getLabel();

            output += " Prediction: " + predictions.get(knnData);
            System.out.println(output);
        }

    }

}

