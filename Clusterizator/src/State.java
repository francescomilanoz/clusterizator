import java.util.ArrayList;
import java.util.List;

public class State {

    String stateName;
    List<Double> stateValues;

    public State(String stateName, ArrayList<Double> values) {
        this.stateValues = values;
        this.stateName = stateName;
    }

    public Double getStatesDifference (State otherState, List<Double> weights) {
        Double statesDifference = 0.0;
        for (int i = 0; i < this.stateValues.size(); i++) {
            Double valState1 = this.stateValues.get(i);
            Double valState2 = otherState.stateValues.get(i);
            if (valState1 >= valState2)
                statesDifference += (valState1 - valState2) * weights.get(i);
            else
                statesDifference += (valState2 - valState1) * weights.get(i);
        }
        return statesDifference;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public List<Double> getStateValues() {
        return stateValues;
    }

    public void setStateValues(List<Double> stateValues) {
        this.stateValues = stateValues;
    }

    public static State generateStatesCluster(State state1, State state2) {
        State statesCluster = new State("", new ArrayList<Double>());
        statesCluster.setStateName(state1.getStateName()+", "+state2.getStateName());
        List<Double> valuesAverages = new ArrayList<>();
        for(int i = 0; i < state1.getStateValues().size(); i++) {
            valuesAverages.add((state1.getStateValues().get(i) + state2.getStateValues().get(i))/2);
        }
        statesCluster.setStateValues(valuesAverages);
        return statesCluster;
    }
}
