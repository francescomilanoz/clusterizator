import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class State {

    String stateName;
    List<Double> stateValues;

    public State(String stateName, ArrayList<Double> values) {
        this.stateValues = values;
        this.stateName = stateName;
    }

}
