
      
public class Student {

        private String name;
        private int id;
        private double gpa;

        public Student(String name, int id, double gpa) {
            this.name = "";
            this.id = 000;
            this.gpa = 0.0;
        }

        public String getName(){
            return name;
        }
        public int getId(){
            return id;
        }
        public double getGPA(){
            return gpa;
        }

    public static void main(String[] args) {

        studentMaker("Steven", 115, 3.8);

        // Student Steven = new Student("Steven", 115, 3.8);
        // Student Joe = new Student("Joseph", 169, 3.1);
        // Student Brady = new Student("Brady", 483, 1.0);
        // System.out.println(Steven);
        // System.out.println(Joe);
        // System.out.println(Brady);
    }
    public static Student studentMaker(String name, int id, double gpa){
        Student s = new Student(name, id, gpa);
        // s.setName(name);
        System.out.println(s.name); //, s.id, s.gpa);

        return(s);
    }
}
