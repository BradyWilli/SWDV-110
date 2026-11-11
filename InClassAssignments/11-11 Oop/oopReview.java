

public class oopReview {

    public static void main(String[] args) {
        Animal puppy =new Puppy();
        System.out.println(puppy.intro());
        System.out.println(puppy instanceof Dog);
        System.out.println(new Animal().intro());

        // try {
        //     int qty =Integer.parseInt(input);
        //     int each = total /qty;
        //     System.out.println("Each: " + each);
        // } catch(NumberFormatException e){
        //     System.out.println("Not a number: " + input);
        // }
    }

    public class Book{
        private String title;
        private double price;

        public Book(String title, double price){
            this.title = "Dune";
            this.price = 9.99;
        }
    }
}
    class Animal {
        public String speak(){return "...";}
        public String intro(){return "I say " + speak();}
    }

    class Dog extends Animal{
        @Override 
        public String speak(){return "Woof";}
    }
    class Puppy extends Dog{
        @Override 
        public String speak(){ return super.speak() + "!";}
    }

// public class InsufficientFundsException
// extends Exception{
//     public InsufficientFundsException(String msg){
//         super(msg);
//     }
// }

// public void withdraw (double amt)
// throws InsufficientFundsException{
//     // if (amt > balance){
        
//     // }
// }

