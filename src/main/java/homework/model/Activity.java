package homework.model;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ibranovic
 */
@Entity
@Table(name = "AKTIVNOST")
@XmlRootElement
public class Activity {
    @Id
    @GeneratedValue
    private Integer id;

    @Column (name="naziv")
    private String name;

    @Column (name="koeficijent")
    private Double coefficient;

    public Activity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getCoefficient() {
        return coefficient;
    }
    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

}