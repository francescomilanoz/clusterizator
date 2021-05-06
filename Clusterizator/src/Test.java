import java.util.*;

public class Test {

    public static void main(String[] str) {
        Clusterizzator clusterizzator = new Clusterizzator();
        Map<Integer, List<State>> computedClusters = clusterizzator.computeClusters();

        for(Integer k: computedClusters.keySet()) {
            System.out.println("Cluster " + k + ": ");
            List<State> currentStates = computedClusters.get(k);
            for(State s: currentStates) {
                System.out.println(s.getStateName());
            }

        }
    }
}
