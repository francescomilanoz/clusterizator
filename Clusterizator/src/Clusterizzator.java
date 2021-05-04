import java.util.*;

public class Clusterizzator {

    List<Integer> weights;
    List<State> states;

    public Clusterizzator() {
        //Initialize weights array
        weights = new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1));

        //Initialize States
        State italy = new State("Italy", new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 1.0)));
        State russia = new State("Russia", new ArrayList<Double>(Arrays.asList(0.5, 0.0, 1.0, 1.0)));
        State india = new State("India", new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 1.0)));
        State brazil = new State("Brazil", new ArrayList<Double>(Arrays.asList(1.0, 1.0, 0.0, 1.0)));
        State USA = new State("USA", new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 1.0)));
        State taiwan = new State("Taiwan", new ArrayList<Double>(Arrays.asList(0.5, 1.0, 1.0, 0.0)));
        State vietnam = new State("Vietnam", new ArrayList<Double>(Arrays.asList(0.0, 0.0, 0.0, 0.0)));
        State china = new State("China", new ArrayList<Double>(Arrays.asList(0.0, 0.0, 1.0, 0.0)));



        //Populate states array
        states = new ArrayList<State>(Arrays.asList(italy, russia, india, brazil, USA, taiwan, vietnam, china));
    }

    public Map<Integer, ArrayList<String>> computeClusters() {
        Map<Integer, ArrayList<String>> clusters = new LinkedHashMap<Integer, ArrayList<String>>();

        //First loop: find the pairs of states that are most similar to each other
        //Compare each state (skipping those already paired) with all the others finding the most similar, calculating the total difference between all the scores
        Map<Integer, ArrayList<String>> statePairs = new LinkedHashMap<Integer, ArrayList<String>>();
        List<String> computedStates = new ArrayList<String>();
        Double bestGlobalMinimumDistance = null; //L'idea è che raggiungere l'ottimo, deve minimizzare la distanza media tra gli stati, e quindi il risultato dipende anche dall'ordine in cui metto gli stati all'inizio
        //Eg: stato 1 ha una distanza minima di 5 con lo stato 5, e una distanza di 5.5 con lo stato 10. Mettiamo però che lo stato 5 abbia distanza 0 con lo stato
        //6 e distanza 10 con tutti gli altri stati -> dovrebbero stare insieme, ma se non provassi tutte le permutazioni non lo sarebbero dato che testo prima
        //stato 1 con stato 5 e li accoppio! Quindi avrei una coppia [1, 5] e una coppia [6, 7], per esempio, con distanza totale 5 + 10.
        //Ma potrei avere coppia [1, 10] e coppia [5, 6] con una distanza solo di 5.5!!!
        //Al tempo stesso, provare tutte le permutazioni è troppo esponenziale e non ci si può riuscire. Ne provo cento milioni di combinazioni, decisamente sufficiente per essere sicuri di avere l'ottimo
        //Ad ogni iterazione, ovviamente, shufflo l'ordine di states.

        for(int iterations = 0; iterations < 100000000; iterations++) {

            Collections.shuffle(states);
            Double bestMinimalDistance = null;
            Double currentGlobalMinimumDistance = null;

            for (State state : states) {
                String mostSimilarStateName = null;
                Double minimalDifference = null;


                if (!computedStates.contains(state.stateName))
                    computedStates.add(state.stateName);
                else
                    continue;
                for (State state2 : states) {
                    if (computedStates.contains(state2.stateName)) //If I'm comparing the state with itself or if I've already computed those second state, I skip it.
                        continue;
                    Double statesDifference = 0.0;
                    for (int i = 0; i < state.stateValues.size(); i++) {
                        Double valState1 = state.stateValues.get(i);
                        Double valState2 = state2.stateValues.get(i);
                        if (valState1 >= valState2)
                            statesDifference += (valState1 - valState2) * weights.get(i);
                        else
                            statesDifference += (valState2 - valState1) * weights.get(i);
                    }
                    if (minimalDifference == null || minimalDifference > statesDifference) { //New best state found!
                        minimalDifference = statesDifference;
                        mostSimilarStateName = state2.stateName;

                    }
                }
                //I've found the most similar state2 to the state1. Now, I can add the pair to the Map
                statePairs.put(statePairs.size(), new ArrayList<String>(Arrays.asList(state.stateName, mostSimilarStateName)));
                computedStates.add(mostSimilarStateName);
               // bestGlobalMinimumDistance +=
            }


           // if(bestGlobalMinimumDistance < )

            //System.out.println(statePairs.get(0) + ", " + statePairs.get(1) + ", " + statePairs.get(2) + ", " + statePairs.get(3));
        }

        System.out.println("done...");

        return clusters;
    }




}
