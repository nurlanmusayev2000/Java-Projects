package mn.learning;

import java.util.Scanner;

interface StringOperation {
    public String modify(String s);

}

public class App
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter your first string");
        String userInput = scanner.nextLine();
        StringOperation toUpperCase = (s)-> s.toUpperCase();
        System.out.println(toUpperCase.modify(userInput));
        StringOperation reversed = (s) ->  new StringBuilder(s).reverse().toString();
        System.out.println("enter your scn string");
        String userInput1 = scanner.nextLine();
        System.out.println(reversed.modify(userInput1));
    }
}
