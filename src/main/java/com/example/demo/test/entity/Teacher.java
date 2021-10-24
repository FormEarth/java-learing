package com.example.demo.test.entity;

public class Teacher extends User implements Comparable<Teacher> {

    /**
     * 学科
     */
    private String subject;
    /**
     * 薪资
     */
    private int salary;

    public Teacher() {

    }

    public Teacher(String subject, int salary) {
        this.subject = subject;
        this.salary = salary;
    }

    public Teacher(String userId, String name, String birthday, String sex, String subject, int salary) {
        super(userId, name, birthday, sex);
        this.subject = subject;
        this.salary = salary;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher [" + super.toString() + "salary=" + salary + ", subject=" + subject + "]";
    }

    /**
     * 实现该类的比较方法,以年龄比较
     **/
    @Override
    public int compareTo(Teacher o) {
        if (this.getUserId().equals(o.getUserId())) {
            return super.getAge() - o.getAge();
        }
        return this.getUserId().compareTo(o.getUserId());
    }

}