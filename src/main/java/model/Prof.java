package model;

import java.sql.Timestamp;

public class Prof extends BaseEntity{

    private String firstName;
    private String lastName;
    private String workEmail;
    private String schoolEmail;
    private String company;
    private String schoolName;
    private String jobTitle;

    public Prof() {

    }


    public Prof(int id, Timestamp createdAt, Timestamp updatedAt, String firstName, String lastName, String workEmail, String schoolEmail, String company, String schoolName, String jobTitle) {
        super(id, createdAt, updatedAt);
        this.firstName = firstName;
        this.lastName = lastName;
        this.workEmail = workEmail;
        this.schoolEmail = schoolEmail;
        this.company = company;
        this.schoolName = schoolName;
        this.jobTitle = jobTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getSchoolEmail() {
        return schoolEmail;
    }

    public void setSchoolEmail(String schoolEmail) {
        this.schoolEmail = schoolEmail;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
