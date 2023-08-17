package guru.qa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentModel {

    @JsonProperty("id")
    private String id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("subject")
    private SubjectModel subject;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public SubjectModel getSubject() {
        return subject;
    }

    public void setSubject(SubjectModel subject) {
        this.subject = subject;
    }

    public static class SubjectModel {

        @JsonProperty("name_subject")
        private String nameSubject;
        @JsonProperty("exam_point")
        private String examPoint;

        public String getNameSubject() {
            return nameSubject;
        }

        public void setNameSubject(String nameSubject) {
            this.nameSubject = nameSubject;
        }

        public String getExamPoint() {
            return examPoint;
        }

        public void setExamPoint(String examPoint) {
            this.examPoint = examPoint;
        }
    }
}



