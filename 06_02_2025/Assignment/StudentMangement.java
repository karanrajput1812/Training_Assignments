import java.util.*;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Student {
    int rollNo;
    String name;
    int standard;
    int age;
    String school;
    String gender;
    float percentage;
    Fees fees;

    Student() {

    }

    public int getStandard() {
        return standard;
    }

    public String getGender() {
        return gender;
    }

    public Float getPercentage() {
        return percentage;
    }

    public String getSchool() {
        return school;
    }

    public int getAge() {
        return age;
    }

    public float getTotalFees() {
        return fees.getStudentTotalFees();
    }

    public float getPaidFees() {
        return fees.getStudentFeesPaid();
    }

    public float getUnpaidFees() {
        return fees.getStudentfeesPending();
    }

}

class Fees {
    float totalFees;
    float feesPaid;
    float feesPending;

    Fees(float totalFees, float feesPaid) {
        this.totalFees = totalFees;
        this.feesPaid = feesPaid;
        this.feesPending = totalFees - feesPaid;
    }

    public float getStudentTotalFees() {
        return totalFees;
    }

    public float getStudentFeesPaid() {
        return feesPaid;
    }

    public float getStudentfeesPending() {
        return feesPending;
    }
}

interface Operation {
    abstract void createStudent(List<Student> studentList);

    abstract void totalStudentStandardWise(List<Student> studentList);

    abstract void totalStudentZenderWise(List<Student> studentList);

    abstract void totalStudentPassAndFail(List<Student> studentList);

    abstract void topScorerUniversityWise(List<Student> studentList);

    abstract void topScorerSchoolWise(List<Student> studentList);

    abstract void totalFeesSchoolWise(List<Student> studentList);

    abstract void feesUnpaidSchoolWise(List<Student> studentList);
}

class MainMenu implements Operation {

    public void createStudent(List<Student> studentList) {
        Random random = new Random();
        String[] names = { "John", "Jane", "Alex", "Emily", "Michael", "Sarah", "David", "Laura", "Chris", "Anna" };
        String[] schools = { "PVM", "CRCE", "DPS", "VVMS", "KV" };
        String[] genders = { "Male", "Female" };

        for (int i = 0; i < 30; i++) {
            Student student = new Student();
            student.name = names[random.nextInt(names.length)];
            student.rollNo = random.nextInt(100);
            student.standard = random.nextInt(12) + 1;
            student.age = random.nextInt(8) + 12;
            student.school = schools[random.nextInt(schools.length)];
            student.gender = genders[random.nextInt(genders.length)];
            student.percentage = random.nextFloat() * 100;
            student.fees = new Fees(random.nextFloat() * 10000, random.nextFloat() * 5000);
            studentList.add(student);
        }

        System.out.println("....30 Students Added....");
    }

    public void totalStudentStandardWise(List<Student> studentList) {
        Map<Integer, Long> m1 = studentList.stream()
                .collect(Collectors.groupingBy(Student::getStandard, Collectors.counting()));
        m1.entrySet().stream()
                .forEach(e -> System.out.println("Total students in standard " + e.getKey() + " are " + e.getValue()));
    }

    public void totalStudentZenderWise(List<Student> studentList) {
        Map<String, Long> m1 = studentList.stream()
                .collect(Collectors.groupingBy(Student::getGender, Collectors.counting()));
        m1.entrySet().stream()
                .forEach(e -> System.out.println("Total " + e.getKey() + " students are " + e.getValue()));
    }

    public void totalStudentPassAndFail(List<Student> studentList) {
        int ch2 = 0;
        do {
            System.out.println("-------------------------");
            System.out.println("Enter your choice: ");
            System.out.println("1. School Wise Pass And Fail");
            System.out.println("2. All School Pass And Fail");
            System.out.println("3. Exit");
            System.out.println("-------------------------");
            ch2 = new Scanner(System.in).nextInt();
            switch (ch2) {
                case 1:
                    System.out.println("School Wise Pass Fail : ");
                    Map<String, Map<Boolean, Long>> m1 = studentList.stream()
                            .collect(Collectors.groupingBy(Student::getSchool,
                                    Collectors.partitioningBy(e -> e.getPercentage() >= 40, Collectors.counting())));
                    m1.forEach((school, map) -> {
                        System.out.println("School: " + school + ", Total Pass Students: " + map.get(true)
                                + ", Total Fail Students: " + map.get(false));
                    });
                    break;
                case 2:
                    System.out.println("All School Pass Fail : ");
                    Map<Boolean, Long> m2 = studentList.stream()
                            .collect(Collectors.partitioningBy(e -> e.getPercentage() >= 40, Collectors.counting()));
                    System.out.println("Total Pass Students: " + m2.get(true));
                    System.out.println("Total Fail Students: " + m2.get(false));
                    break;
                case 3: break;
                default:
                    ch2 = 3;
                    break;
            }

        } while (ch2 != 3);

    }

    public void topScorerUniversityWise(List<Student> studentList) {
        List<Student> lsort = studentList.stream().sorted(Comparator.comparing(Student::getPercentage).reversed())
                .limit(3).collect(Collectors.toList());
        lsort.forEach(student -> System.out.println("Name: " + student.name + ", Percentage: " + student.percentage));
    }

    public void topScorerSchoolWise(List<Student> studentList) {
        Map<String, List<Student>> m1 = studentList.stream().collect(Collectors.groupingBy(Student::getSchool));
        m1.forEach((school, students) -> {
            students.stream()
                    .sorted(Comparator.comparing(Student::getPercentage).reversed())
                    .limit(1)
                    .forEach(student -> System.out.println(
                            "School: " + school + ", Name: " + student.name + ", Percentage: " + student.percentage));
        });
    }

    public void averageOfMaleAndFemale(List<Student> studentList) {
        Map<String, Double> m1 = studentList.stream()
                .collect(Collectors.groupingBy(Student::getGender, Collectors.averagingInt(Student::getAge)));
        System.out.print("Average Age of Male Students : ");
        System.out.println(m1.get("Male"));
        System.out.print("Average Age of Female Students : ");
        System.out.println(m1.get("Female"));
    }

    public void totalFeesSchoolWise(List<Student> studentList) {
        Map<String, Double> m1 = studentList.stream()
                .collect(Collectors.groupingBy(Student::getSchool, Collectors.summingDouble(Student::getPaidFees)));
        m1.forEach((school, totalFees) -> System.out
                .println("Total fees collected from school " + school + " are " + totalFees));
    }

    public void feesUnpaidSchoolWise(List<Student> studentList) {
        Map<String, Double> m1 = studentList.stream()
                .collect(Collectors.groupingBy(Student::getSchool, Collectors.summingDouble(Student::getUnpaidFees)));
        m1.forEach((school, totalFees) -> System.out
                .println("Total fees pending from school " + school + " are " + totalFees));
    }
}

public class StudentMangement {
    public static void main(String[] args) {

        List<Student> studentList = new ArrayList<Student>();
        int ch1 = 0, ch2 = 0;
        Scanner sc = new Scanner(System.in);
        MainMenu m1 = new MainMenu();
        do {
            System.out.println("---------------------------------------------");
            System.out.println("Enter your choice: ");
            System.out.println("1. Create Student");
            System.out.println("2. How many students in each standard");
            System.out.println("3. How many students zender wise(Male And Female)");
            System.out.println("4. How many students have failed and pass (40%)");
            System.out.println("5. Top 3 students (Whole university)");
            System.out.println("6. Top scorer school wise");
            System.out.println("7. Average age of male & female students");
            System.out.println("8. Total fees collected school wise");
            System.out.println("9. Total fees pending school wise");
            System.out.println("10. Total number of students (University)");
            System.out.println("0. Exit");
            System.out.println("---------------------------------------------");
            ch1 = sc.nextInt();
            switch (ch1) {
                case 1:
                    m1.createStudent(studentList);
                    break;
                case 2:
                    m1.totalStudentStandardWise(studentList);
                    break;
                case 3:
                    m1.totalStudentZenderWise(studentList);
                    break;
                case 4:
                    m1.totalStudentPassAndFail(studentList);
                    break;
                case 5:
                    m1.topScorerUniversityWise(studentList);
                    break;
                case 6:
                    m1.topScorerSchoolWise(studentList);
                    break;
                case 7:
                    m1.averageOfMaleAndFemale(studentList);
                    break;
                case 8:
                    m1.totalFeesSchoolWise(studentList);
                    break;
                case 9:
                    m1.feesUnpaidSchoolWise(studentList);
                    break;
                case 10:
                    System.out.println("Total Number of students: " + studentList.size());
                    break;
                case 0:
                    ch1 = 0;
                    break;
            }
        } while (ch1 != 0);
    }
}
