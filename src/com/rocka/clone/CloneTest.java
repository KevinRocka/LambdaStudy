package com.rocka.clone;

/**
 * 克隆常规方法测试使用类
 */
public class CloneTest {

    public static void main(String[] args) {
        Student student = new Student();
        student.setAge(1);
        student.setName("Rocka");
        student.setStudentNum(10000);
        System.out.println(student);
        try {
            Student studentClone = (Student)student.clone();

            System.out.println(studentClone.toString());

            System.out.println(studentClone.equals(student));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
