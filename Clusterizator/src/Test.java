import java.util.*;

public class Test {

    public static void main(String[] str) {
        Clusterizzator clusterizzator = new Clusterizzator();
        List<State> computedClusters = clusterizzator.computeClusters();
        int low = 0;
        int medium = 0;
        int high = 0;

        for(int i = 0; i < computedClusters.size(); i++) {
            System.out.println("\nCluster " + (i+1) +":\nMembers: " + computedClusters.get(i).getStateName() + "\nCluster properties: ");

            for(int j = 0; j < clusterizzator.factorsName.size(); j++) {
                String roughValue;
                Double preciseValue = computedClusters.get(i).getStateValues().get(j);
                if(preciseValue <= 0.25) {
                    roughValue = "L";
                    low++;
                }

                else if(preciseValue > 0.75) {
                    roughValue = "H";
                    high++;
                }

                else {
                    roughValue = "M";
                    medium++;
                }
                System.out.println("\t"+clusterizzator.factorsName.get(j) + "\t" + roughValue);
            }
        }

        System.out.println("\n\nNlow = " + low + " Nmedium = " + medium + " Nhigh = " + high);

    }
}
