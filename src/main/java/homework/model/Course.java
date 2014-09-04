package homework.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ibranovic
 */
@Entity
@Table(name = "PREDMET")
@XmlRootElement
public class Course {
    @Id
    @GeneratedValue
    private Integer id;
    @Column (name="sifra")
    private String code;
    @Column (name="naziv")
    private String name;
    @Column (name="semestar")
    private String semester;
    @Column (name="skolskagod")
    private Integer schoolYear;

    public Integer getId() {
        return id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public Integer getSchoolYear() {
        return schoolYear;
    }
    public void setSchoolYear(Integer schoolYear) {
        this.schoolYear = schoolYear;
    }
    public Course() { }
}