package homework.model;

import java.util.List;

/**
 * @author ibranovic
 */

//objekat koji modelira parametre za pretragu

public class ActivitySearch {
    private Double coefficientFrom;
    private Double coefficientTo;
    private List<String> names;

    public Double getCoefficientTo() {
        return coefficientTo;
    }

    public void setCoefficientTo(Double coefficientTo) {
        this.coefficientTo = coefficientTo;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }


    public Double getCoefficientFrom() {
        return coefficientFrom;
    }

    public void setCoefficientFrom(Double coefficientFrom) {
        this.coefficientFrom = coefficientFrom;
    }
}
