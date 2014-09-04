package homework.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.Query;
/**
 * @author ibranovic
 */
@Entity
@Table(name = "ZVANJE")
@XmlRootElement
public class Title {
    public static final String DEMONSTRATOR = "demonstrator";
    public static final String TEACHING_ASSISTANT = "asistent";
    public static final String ASSISTANT_PROF = "docent";
    public static final String ASSOCIATED_PROF = "vanredni profesor";
    public static final String FULL_PROF = "redovni profesor";
    @Id
    @GeneratedValue
    private Integer id;
    @Column (name="naziv")
    private String name;
    public Title() {
    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isDemonstrator() {
        return DEMONSTRATOR.equals(name);
    }
    public boolean isInstructor() {
        return !isDemonstrator();
    }
}