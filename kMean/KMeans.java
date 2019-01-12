package kMean;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;


public class KMeans {


    public static ArrayList<KMeanCluster> KMeansPartition(int k, ArrayList<DataPoint> data) {
        HashMap<DataPoint, Integer> labels = new HashMap<>();
        ArrayList<KMeanCluster> oldClusters = new ArrayList<>();
        ArrayList<KMeanCluster> clusters = new ArrayList<>(getKRandomCluster(data, labels, oldClusters, k));
        int iteration = 0;

        while(!stopCondition(clusters, oldClusters, iteration, k)){
            oldClusters = clusters;
            labels = getLabels(data, clusters);

            clusters = updateClusters(data, labels, oldClusters, k);

//            System.out.println(clusters);
//            System.out.println("Iteration: " + iteration );
//            System.out.println(labels);
//            System.out.println("Old " + oldClusters);
//            System.out.println("New " + clusters);

            iteration +=1;
        }
        System.out.println("Clustering converges at round " + iteration);
        System.out.println();

        return clusters;
    }

    public static void printData(ArrayList<DataPoint> data) {
        System.out.println("Normalized age data: ");
        for (DataPoint dataPoint: data){
            System.out.printf(" %.3f ", dataPoint.getAge());
        }
        System.out.println();

        System.out.println("Normalized interest score: ");
        for (DataPoint dataPoint: data){
            System.out.printf(" %.3f ", dataPoint.getInterestscore());
        }
        System.out.println();

        System.out.println("List of clustered data points: ");
        for (DataPoint dataPoint: data){
            System.out.print(String.format("%3s (%2.2f , %2.2f) ",dataPoint.getName() , dataPoint.getAge() , dataPoint.getInterestscore()));
            //System.out.println(dataPoint.getName() + " (" +  dataPoint.getAge() +" , " +  dataPoint.getInterestscore() +") ");
        }
        System.out.println();
    }

    private static ArrayList<KMeanCluster> updateClusters(
            ArrayList<DataPoint> data, HashMap<DataPoint, Integer> labels, ArrayList<KMeanCluster> oldClusters, int k) {
        ArrayList<KMeanCluster> clusters = new ArrayList<>();

        clusters = getKRandomCluster(data,labels, oldClusters, k);

        for (DataPoint dataPoint : data) {
            int clusterLabel = labels.get(dataPoint);
            KMeanCluster cluster = clusters.get(clusterLabel);
            if (!cluster.ClusterMembers.contains(dataPoint))
                clusters.get(clusterLabel).add(dataPoint);
        }

        return clusters;
    }

    private static ArrayList<KMeanCluster> createKEmptyClusters(int k) {
        ArrayList<KMeanCluster> clusteredDatapoints = new ArrayList<KMeanCluster>();

        for (int i = 0; i < k; i++) {
            ArrayList<DataPoint> randomcluster = new ArrayList<>();
            KMeanCluster emptyCluster = new KMeanCluster(randomcluster);
            clusteredDatapoints.add(emptyCluster);
        }
        return clusteredDatapoints;
    }

    private static ArrayList<KMeanCluster> getKRandomCluster(ArrayList<DataPoint> data, HashMap<DataPoint, Integer> labels, ArrayList<KMeanCluster> oldClusters, int k) {
        ArrayList<KMeanCluster> clusteredDatapoints = new ArrayList<KMeanCluster>();

        // Randomly select K elements from the data set as the new clusters
        for (int i = 0; i < k; i++) {
            ArrayList<DataPoint> randomizedDataPoints = new ArrayList<>(data);
            for (int j = 0; j < 5; j++) {
                Collections.shuffle(randomizedDataPoints);
            }
            int len = randomizedDataPoints.size();
            int idx = new Random().nextInt(len);

            DataPoint dataPoint = data.get(idx);

            boolean sw = false;
            for (KMeanCluster kMeanCluster: clusteredDatapoints)
                if (kMeanCluster.ClusterMembers.contains(dataPoint)) sw= true;

            if (!sw){
                ArrayList<DataPoint> randomcluster = new ArrayList<>();
                randomcluster.add(dataPoint);
                KMeanCluster randomCluster = new KMeanCluster(randomcluster);
                clusteredDatapoints.add(randomCluster);
                //System.out.print("Randomly chosen datapoint as cluster center: " + dataPoint);
            } else {
                return getKRandomCluster(data, labels, oldClusters, k);
            }

        }
        return clusteredDatapoints;
    }

    private static HashMap<DataPoint,Integer> getLabels(ArrayList<DataPoint> data, ArrayList<KMeanCluster> clusters) {
        HashMap<DataPoint,Integer> labels = new HashMap<>();

        for (DataPoint dataPoint: data){
            int value = -1;
            double minDistance = 1000;

            for (int i=0; i<clusters.size(); i++){
                KMeanCluster kMeanCluster = clusters.get(i);
                double distance = kMeanCluster.distance(dataPoint, kMeanCluster.centerPoint());

                if (minDistance > distance) {
                    value = i;
                    minDistance = distance;
                    //System.out.println( dataPoint.getName() + "-> C"+i +": " + distance);
                }
            }
            labels.put(dataPoint, value);
        }

        return labels;
    }

    private static boolean stopCondition(ArrayList<KMeanCluster> clusters, ArrayList<KMeanCluster> oldClusters, int i, int iteration) {
        if (iteration > 100000) return true;
        else if (sameClusterLists(clusters, oldClusters)) return true;
        else return false;
    }

    private static boolean sameClusterLists(ArrayList<KMeanCluster> clusters, ArrayList<KMeanCluster> oldClusters) {
        if (clusters.size()!= oldClusters.size()) return false;

        for (int i= 0; i< clusters.size() -1 ;i++){
            if (!clusters.get(i).ClusterMembers.equals(oldClusters.get(i).ClusterMembers)) return false;
        }
        return true;
    }

    public static void printClusters(ArrayList<KMeanCluster> clusters) {

        for (int i=0; i<clusters.size(); i++){
            System.out.printf("Cluster: %d with the center at: [%.2f , %.2f] \n" , (i+1),  clusters.get(i).centerPoint().getAge() , clusters.get(i).centerPoint().getInterestscore());
//            System.out.println(clusters.get(i));
        }
    }

}
