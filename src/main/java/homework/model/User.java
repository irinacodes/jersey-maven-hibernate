package homework.model;
import homework.util.HibernateUtil;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * @author ibranovic
 */
@Entity
@Table(name = "KORISNIK")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "korisnickoime")
    private String username;
    @Column(name = "lozinka")
    private String password;
    @Column(name = "ime")
    private String name;
    @Column(name = "prezime")
    private String surname;
    @Column(name = "telefon")
    private String phone;
    @Column(name = "eposta")
    private String email;
    @Column(name = "godstudija")
    private Integer yearOfStudy;
    @Column(name = "prosek")
    private Double gpa;
    @Column(name = "admin")
    private Boolean admin = false;
    @Column(name = "aktivan")
    private Boolean active = false;
    //one-to-many veza
    @ManyToOne
    @JoinColumn(name = "zvanjeid")
    private Title title;
    //many-to-many veze
    //kada čuvamo korisnika, čuvamo i njegove predmete i odseke (cascade save - update)
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "KORISNIK_ODSEK",
            joinColumns = {
                    @JoinColumn(name = "korisnikid")},
            inverseJoinColumns = {
                    @JoinColumn(name = "odsekid")})
    private Set<Course> courses = new HashSet<Course>();
    public User() {
    }
    public Integer getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getYearOfStudy() {
        return yearOfStudy;
    }
    public void setYearOfStudy(Integer yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }
    public Double getGpa() {
        return gpa;
    }
    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }
    public Boolean isAdmin() {
        return admin;
    }
    public Boolean getAdmin() {
        return isAdmin();
    }
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
    public Boolean isActive() {
        return active;
    }
    public Boolean getActive() {
        return isActive();
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public boolean isInstructor() {
        return title.isInstructor();
    }
    public boolean getInstructor() {
        return isInstructor();
    }
    public boolean isDemonstrator() {
        return title.isDemonstrator();
    }
    public boolean getDemonstrator() {
        return isDemonstrator();
    }
    public Title getTitle() {
        return title;
    }
    public void setTitle(Title title) {
        this.title = title;
    }

    public Set<Course> getCourses() {
        return courses;
    }
    public void addCourse(Course course) {
        courses.add(course);
    }
    public void addCourse(Serializable courseId) {
        courses.add(HibernateUtil.load(Course.class, courseId));
    }
    public void clearCourses() {
        courses.clear();
    }

}