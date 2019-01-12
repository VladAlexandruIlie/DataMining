package kMean;
import java.util.ArrayList;
import java.util.Comparator;

public class KMeanCluster {
    public ArrayList<DataPoint> ClusterMembers =  new ArrayList<DataPoint>();
	
	public KMeanCluster(ArrayList<DataPoint> clusterMembers) {
		this.ClusterMembers = new ArrayList<DataPoint>(clusterMembers);
    }

    public void add(DataPoint dataPoint) {
        this.ClusterMembers.add(dataPoint);

        this.ClusterMembers.sort(new Comparator<DataPoint>() {
            @Override
            public int compare(DataPoint o1, DataPoint o2) {

                if (Integer.parseInt(o1.getName().substring(1)) > Integer.parseInt(o2.getName().substring(1))) return 1;
                    else if (Integer.parseInt(o1.getName().substring(1)) == Integer.parseInt(o2.getName().substring(1))) return 0;
                    else return -1;
            }
        });
    }

    public DataPoint centerPoint() {

        String name= "Center";
        double meanNoGames = (float) 0;
        double meanInterest = (float) 0;

        for (int j=0; j<ClusterMembers.size(); j++){
            meanNoGames = meanNoGames + ClusterMembers.get(j).getAge();
            meanInterest = meanInterest +  ClusterMembers.get(j).getInterestscore();
        }

        meanNoGames = meanNoGames / ClusterMembers.size();
        meanInterest = meanInterest / ClusterMembers.size();

        DataPoint center = new DataPoint(name, (float) meanNoGames, (float) meanInterest);
        return center;

    }

    public double distance(DataPoint dataPoint, DataPoint center) {
        double dis = 0;

        dis = Math.sqrt( (center.getInterestscore()- dataPoint.getInterestscore()) *
                         (center.getInterestscore()- dataPoint.getInterestscore()) +
                         (center.getAge() - dataPoint.getAge()) *
                         (center.getAge() - dataPoint.getAge())
                          );

        return  dis;
    }

    @Override
	public String toString() {
		String toPrintString = "CLUSTER START: \n";
		
		for(DataPoint i : this.ClusterMembers)
		{
			//toPrintString += i.getName() + " ";
            toPrintString += String.format("%3s (%2.3f , %2.3f) \n", i.getName() , i.getAge() , i.getInterestscore());
		}
		toPrintString += "\n" ;
		
		return toPrintString;
	}
}
