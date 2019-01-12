package kMean;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Class used to run the K-means algorithm in a controlled environment.
 */
public class KMeansTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void KMeansPartition() {
        ArrayList<DataPoint> datapoints = new ArrayList<>();
        DataPoint P1 = new DataPoint("P1", 1, 1);
        DataPoint P2 = new DataPoint("P2", 1, (float) 2 );
        DataPoint P3 = new DataPoint("P3", (float) 1.5, (float) 1.5);
        DataPoint P4 = new DataPoint("P4", 2, (float) 2.5);
        DataPoint P5 = new DataPoint("P5", 1, 4);
        DataPoint P51 = new DataPoint("P51", (float) 1.5, 3);

        DataPoint P6 = new DataPoint("P6", (float) 3.5, 6);
        DataPoint P7 = new DataPoint("P7", (float) 4.5, 6);
        DataPoint P8 = new DataPoint("P8", 4, 7);
        DataPoint P9 = new DataPoint("P9", 4, 8);
        DataPoint P10 = new DataPoint("P10", (float) 3.5, 9);
        DataPoint P52 = new DataPoint("P52", (float) 4.5, 9);

        DataPoint P11 = new DataPoint("P11", (float) 7.5, 5);
        DataPoint P12 = new DataPoint("P12", 8, 6);
        DataPoint P13 = new DataPoint("P13", (float) 8.5, 7);
        DataPoint P14 = new DataPoint("P14", 9, 8);
        DataPoint P15 = new DataPoint("P15", 8, 9);
        DataPoint P53 = new DataPoint("P53", (float) 9.5, 6);

//        DataPoint P16 = new DataPoint("P16", 5, 9);
//        DataPoint P17 = new DataPoint("P17", 16, 16);
//        DataPoint P18 = new DataPoint("P18", (float)11.5, 8);
//        DataPoint P19 = new DataPoint("P19", 13, 10);
//        DataPoint P20 = new DataPoint("P20", 12, 13);
//        DataPoint P21 = new DataPoint("P21", 14, (float) 12.5);
//        DataPoint P22 = new DataPoint("P22", (float) 14.5, (float) 12.5);
//        DataPoint P23 = new DataPoint("P23", (float) 14.5, (float) 11.5);
//        DataPoint P24 = new DataPoint("P24", 15, (float) 10.5);
//        DataPoint P25 = new DataPoint("P25", 15, (float) 9.5);
//        DataPoint P26 = new DataPoint("P26", 12, (float) 9.5);
//        DataPoint P27 = new DataPoint("P27", (float) 10.5, 11);
//        DataPoint P28 = new DataPoint("P28", 10, (float) 10.5);
//        DataPoint P29 = new DataPoint("P29", 9, 3);
//        DataPoint P30 = new DataPoint("P30", 9, 4);
//        DataPoint P31 = new DataPoint("P31", 9, 5);

        datapoints.add(P1); datapoints.add(P2); datapoints.add(P3); datapoints.add(P4); datapoints.add(P5);
        datapoints.add(P6); datapoints.add(P7); datapoints.add(P8); datapoints.add(P9); datapoints.add(P10);
        datapoints.add(P11); datapoints.add(P12); datapoints.add(P13); datapoints.add(P14); datapoints.add(P15);
        datapoints.add(P51); datapoints.add(P52); datapoints.add(P53);

//        datapoints.add(P16); datapoints.add(P17); datapoints.add(P18); datapoints.add(P19); datapoints.add(P20);
//        datapoints.add(P21); datapoints.add(P22); datapoints.add(P23); datapoints.add(P24); datapoints.add(P25);
//        datapoints.add(P26); datapoints.add(P27); datapoints.add(P28); datapoints.add(P29); datapoints.add(P30);
//        datapoints.add(P31);

        System.out.println(datapoints);

        ArrayList<KMeanCluster> clusters = new ArrayList<>();
        clusters = KMeans.KMeansPartition(3, datapoints);

        KMeans.printClusters(clusters);
    }

}