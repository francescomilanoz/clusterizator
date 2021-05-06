import java.util.*;

public class Test {

    public static void main(String[] str) {
        Clusterizzator clusterizzator = new Clusterizzator();
        Map<Integer, List<State>> computedClusters = clusterizzator.computeClusters();

        for(Integer k: computedClusters.keySet()) {
            System.out.println("\nCluster " + k + ": ");
            List<State> currentStates = computedClusters.get(k);
            for(State s: currentStates) {
                System.out.println(s.getStateName());
            }
        }

        //AGGIUNGERE ARRAY DEI PESI CON IL NOME (QUINDI MAGARI TRASFORMARLA IN MAPPA) PER POTER CAPIRE COSE'E' CHE ACCOMUNA UN CLUSTER O UN ALTRO [EG ALTO LIV DI DEMOCRAZIA]

    }
}
