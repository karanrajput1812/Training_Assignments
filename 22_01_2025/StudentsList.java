import java.util.*;


// To use compareTo Student implements Comparable
class Student implements Comparable
{
    int rollNo;
    String name;
    int standard;

    public Student(int rn, String n, int s)
    {
        rollNo = rn;
        name = n;
        standard = s;
    }
    public String toString()
    {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Standard: " + standard);
        return "";
    }
    public boolean equals(Object obj)
    {
        Student s = (Student) obj;
        if ((this.rollNo == s.rollNo) && (this.standard == s.standard)) {
            return true;
        }

        return false;
    }
    public int hashCode()
    {
        return standard;
    }
    public int compareTo(Object obj)
    {
        Student s = (Student) obj;
        // if(s.rollNo > this.rollNo)
        //     return 1;
        // else if(s.rollNo < this.rollNo)
        //     return -1;
        // else 
        //     return 0;

        //      OR
        String rn = s.name;
        return rn.compareTo(name)*(-1);
    }
}
class NameSorter implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return s1.name.compareTo(s2.name);
    }
}

class RollNoSorter implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return new Integer(s1.rollNo).compareTo(s2.rollNo);
    }
}

class StandardNoSorter implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return new Integer(s1.standard).compareTo(s2.standard);
    }
}


public class StudentsList {
    public static void main(String[] args) {
        

        // override equals and hashCode methods to ensure only unique records are stored
        HashSet hs = new HashSet();
        hs.add(new Student(22, "Rinku", 6));
        hs.add(new Student(33, "Sanju", 5));
        hs.add(new Student(22, "Rinku", 6));
        hs.add(new Student(44, "Manju", 4));
        hs.add(new Student(55, "Rita", 3));
        hs.add(new Student(66, "Sita", 4));

        Iterator i = hs.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }

        System.out.println("----------------------------------------------");

        // compareTo method to ensure only unique records are stored
        TreeSet ts = new TreeSet();
        ts.add(new Student(22, "Rinku", 6));
        ts.add(new Student(33, "Sanju", 5));
        ts.add(new Student(22, "Rinku", 6));
        ts.add(new Student(44, "Manju", 4));
        ts.add(new Student(55, "Rita", 3));
        ts.add(new Student(66, "Sita", 4));

        Iterator j = ts.iterator();
        while (j.hasNext()) {
            System.out.println(j.next());
        }

        System.out.println("----------------------------------------------");

        // roll no based sorting
        TreeSet ts2 = new TreeSet(new RollNoSorter());
        ts2.add(new Student(22, "Rinku", 6));
        ts2.add(new Student(33, "Sanju", 5));
        ts2.add(new Student(22, "Rinku", 6));
        ts2.add(new Student(44, "Manju", 4));
        ts2.add(new Student(55, "Rita", 3));
        ts2.add(new Student(66, "Sita", 4));

        Iterator k = ts2.iterator();
        while (k.hasNext()) {
            System.out.println(k.next());
        }
    }
}
