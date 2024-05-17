package com.infjz.prm392.slot4;

public class Slot41Student {
    private String studentName;
    private String studentAge;
    private int studentImage;

    public Slot41Student(String studentName, String studentAge, int studentImage) {
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentImage = studentImage;
    }

    public Slot41Student() {

    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(String studentAge) {
        this.studentAge = studentAge;
    }

    public int getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(int studentImage) {
        this.studentImage = studentImage;
    }
}
