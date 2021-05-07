import java.util.*;

public class Clusterizzator {

    List<Double> weights;
    List<State> states;
    Map<Integer, List<State>> recordGlobalStates = new LinkedHashMap<>();
    List<State> computedStates = new ArrayList<>();
    public List<String> factorsName = new ArrayList<>(Arrays.asList("Democratic form of government", "High state of democracy", "Division of power into regions/states", "High level of corruption", "High level of perceived corruption", "Individualistic culture (based on Hofstede scoring)", "Sustainability - hygiene (based on EPI) ", "Income inequality (based on GINI index)", "Overpopulation", "Aging population (Young p.)", "Good healthcare infrastructure (unverdeveloped h.)", "Private healthcare", "High level of education (Low)", "Social welfare policies = Social spending (No help from government)", "The majority of the population is religious", "Buddhism", "Taoism", "Shinto", "Christianity", "Folk religion", "Hinduism", "Islam", "Unaffiliated", "Cyber security problems", "Collection and storage of biometrics data", "Digitally advanced", "Strong ICT sector", "Good innovation infrastructures and R&D", "Developing country", "R&D spending (high 1, low 0)", "High ICT Development (ICT Development index)", "Contact tracing app", "Lockdowns", "Big data for contact tracing"));

    public Clusterizzator() {
        //Initialize weights array
        weights = new ArrayList<Double>(Arrays.asList(5.0, 10.0, 3.0, 3.0, 3.0, 10.0, 10.0, 3.0, 10.0, 10.0, 10.0, 4.0, 2.0, 4.0, 3.0, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 1.0, 7.0, 3.0, 4.0, 4.0, 8.0, 2.0, 2.0, 10.0, 10.0, 10.0));

        //Initialize States
        State southKorea = new State("South Korea",     new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.5, 1.0, 0.0, 0.5, 0.5, 1.0, 1.0, 0.5, 0.5, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 1.0, 0.5, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0)));
        State japan = new State("Japan",                new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 0.0, 0.0, 0.5, 1.0, 0.5, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 0.0, 0.5, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.5, 0.0, 0.5, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.5, 0.0)));
        State uk = new State("UK",                      new ArrayList<Double>(Arrays.asList(1.0, 1.0, 0.5, 0.0, 0.0, 1.0, 1.0, 0.5, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0)));
        State singapore = new State("Singapore",        new ArrayList<Double>(Arrays.asList(1.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.5, 0.0, 1.0, 0.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.5, 0.5, 0.0, 0.5, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0)));
        State newZealand = new State("New Zealand",     new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.5, 0.5, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.5, 1.0, 0.5, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0)));
        State iceland = new State("Iceland",            new ArrayList<Double>(Arrays.asList(1.0, 1.0, 0.0, 0.0, 0.0, 0.5, 1.0, 0.5, 0.0, 0.5, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.5, 1.0, 1.0, 0.0, 0.5, 1.0, 1.0, 0.0, 0.5)));
        State australia = new State("Australia",        new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.5, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.5, 1.0, 1.0, 1.0, 0.0)));
        State china = new State("China",                new ArrayList<Double>(Arrays.asList(0.0, 0.0, 1.0, 1.0, 0.5, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 0.5, 1.0, 0.5, 1.0)));
        State vietnam = new State("Vietnam",            new ArrayList<Double>(Arrays.asList(0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.5, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.5, 0.5, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0)));
        State taiwan = new State("Taiwan",              new ArrayList<Double>(Arrays.asList(0.5, 1.0, 1.0, 0.5, 0.0, 0.0, 0.5, 0.5, 1.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.5, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.5, 1.0, 0.5, 1.0, 0.5, 0.5, 0.0, 0.5, 1.0, 0.0, 0.0, 1.0)));
        State usa = new State("USA",                    new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.5, 0.0, 0.5, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.5, 0.5, 0.0)));
        State brazil = new State("Brazil",              new ArrayList<Double>(Arrays.asList(1.0, 1.0, 0.0, 0.5, 1.0, 0.0, 0.5, 1.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.5, 0.0, 0.5, 0.0, 1.0, 0.0, 0.5, 1.0, 0.5, 0.0)));
        State brazil2 = new State("Brazil2",            new ArrayList<Double>(Arrays.asList(1.0, 1.0, 0.0, 0.5, 1.0, 0.0, 0.5, 1.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.5, 0.0, 0.5, 0.0, 1.0, 0.0, 0.5, 1.0, 0.5, 0.0)));
        State india = new State("India",                new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 0.5, 0.5, 0.5, 0.0, 1.0, 1.0, 0.0, 0.0, 0.5, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.5, 0.0, 1.0, 0.5, 0.0, 0.0, 0.0, 1.0, 0.5, 0.0, 1.0, 1.0, 0.0)));
        State russia = new State("Russia",              new ArrayList<Double>(Arrays.asList(0.5, 0.0, 1.0, 1.0, 1.0, 0.0, 0.5, 1.0, 0.0, 0.5, 0.0, 0.5, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.5, 0.5, 0.0, 0.0, 0.5, 0.0, 1.0, 0.5, 1.0, 0.5, 1.0, 0.5)));
        State italy = new State("Italy",                new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 0.0, 0.5, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 0.0, 1.0, 1.0, 0.5, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.5, 0.5, 0.0, 0.5, 1.0, 1.0, 1.0, 0.0)));




        //Populate states array
        states = new ArrayList<State>(Arrays.asList(southKorea, japan, uk, singapore, newZealand, iceland, australia, china, vietnam, taiwan, usa, brazil, brazil2, india, russia, italy));
    }

    /*
    public void clusterizeEqualStates() {
        List<State> newStates = new ArrayList<>();
        List<State> toBeRemoved = new ArrayList<>();

        for(State state1: states) {
            for(State state2: states) {
                if(state1.getStatesDifference(state2, weights) == 0.0 && !(state1.getStateName().equals(state2.getStateName()))) {
                    State state12 = State.generateStatesCluster(state1, state2);
                    newStates.add(state12);
                    toBeRemoved.add(state1);
                    toBeRemoved.add(state2);
                }
            }
        }
        for(State s: toBeRemoved) {
            if(states.contains(s))
                states.remove(s);
        }
        for(State s: newStates) {
            states.add(s);
        }
    }
    */

    public void generateNextClusters() {

        computedStates.clear();
        recordGlobalStates.clear();

        for (int i = 0; i < states.size() / 2; i++) {
            Double recordGlobalDifference = null;
            List<State> temporaryCluster = new ArrayList<>();

            for (State state1 : states) {

                if (computedStates.contains(state1))
                    continue;

                Double recordCurrentDifference = null;
                State recordCurrentState = null; //It contains the temporary best state found for state1
                for (State state2 : states) {

                    if (computedStates.contains(state2) || state1.equals(state2))
                        continue;

                    Double currentDifference = state1.getStatesDifference(state2, weights);

                    if (recordCurrentDifference == null || currentDifference < recordCurrentDifference) {
                        recordCurrentDifference = currentDifference;
                        recordCurrentState = state2;
                    }
                }
                if (recordGlobalDifference == null || recordCurrentDifference < recordGlobalDifference) {
                    recordGlobalDifference = recordCurrentDifference;
                    temporaryCluster.clear();
                    temporaryCluster.add(state1);
                    temporaryCluster.add(recordCurrentState);
                }
            }
            computedStates.addAll(temporaryCluster);
            recordGlobalStates.put(i, temporaryCluster);
        }

    }

    public List<State> computeClusters() {

        //First loop: find the pairs of states that are most similar to each other
        //Compare each state (skipping those already paired) with all the others finding the most similar, calculating the total difference between all the scores

        /*
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

 */

        generateNextClusters();

        states.clear();
        for (Integer k : recordGlobalStates.keySet()) {
            State state1 = recordGlobalStates.get(k).get(0);
            State state2 = recordGlobalStates.get(k).get(1);
            State state12 = State.generateStatesCluster(state1, state2);
            states.add(state12);
        }
        generateNextClusters();

        states.clear();
        for (Integer k : recordGlobalStates.keySet()) {
            State state1 = recordGlobalStates.get(k).get(0);
            State state2 = recordGlobalStates.get(k).get(1);
            State state12 = State.generateStatesCluster(state1, state2);
            states.add(state12);
        }

        return states;
    }


}
