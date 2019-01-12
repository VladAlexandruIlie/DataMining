import Knn.KNNData;
import Knn.KNearestNeighbors;
import PreProcessing.DataRepository;
import data.DataLoader;
import kMean.DataPoint;
import kMean.KMeanCluster;
import kMean.KMeans;
import patterns.Apriori;
import patterns.ItemSet;
import java.util.*;

public class Main {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		//First step load the data
        DataRepository answers = new DataRepository(DataLoader.LoadData());
        answers.printNumberOfEntries();

        // Apriori
        // The Apriori algorithm finds all frequent item sets within a given database.
        // In this case, it finds all sets of games played that are frequent based on a support threshold
//        answers.printGamesPlayed();
        Set<ItemSet> frequentItemSets = Apriori.apriori(answers.getAllGamesPlayed(),3);
        // Apriori results
        answers.printTheLongestSequences(frequentItemSets);
        System.out.println();

        // K-mean
        // The K-means algorithm classifies an unlabeled set of data into K partitions
        // In this case, the algorithm uses age and total interest as parameters. both have been normalized to (0,1)
        ArrayList<KMeanCluster> clusters = new ArrayList<>();
        DataRepository answersCopy = answers.cleanData();
        ArrayList<DataPoint> kMeanData = new ArrayList<DataPoint>(answersCopy.getKMeanData());
        // K-mean
//        KMeans.printData(kMeanData);
        clusters = KMeans.KMeansPartition(4, kMeanData);
        KMeans.printClusters(clusters);
        System.out.println();

        // k-NN
        // The K-NN algorithm attempts to predicts a person's degree based on their interest in the course topics.
        // It first generates the data, then generates the test set, then generates the training set then make predictions
//        answers.printKnnData();
        int testSize = 5; int kNeighbors = 5;
        ArrayList<KNNData> knnData = new ArrayList<>(answers.getKNNData());
        ArrayList<KNNData> toTestData = new ArrayList<>(answers.getTestData(knnData, testSize));
        ArrayList<KNNData> trainingData = new ArrayList<>(answers.getTrainingData(knnData, toTestData));
        HashMap<KNNData, String> predictions = KNearestNeighbors.predict(knnData, toTestData, trainingData, kNeighbors);

        // k-NN results
        answers.printPredictions(predictions);
        System.out.printf("Accuracy: %.3f \n", KNearestNeighbors.getAccuracy(knnData,predictions));
        KNearestNeighbors.printAverageAccuracy(answers, knnData, testSize, kNeighbors,  100);

	}

}
