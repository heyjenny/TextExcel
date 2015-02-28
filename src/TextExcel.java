
import java.util.Scanner;

import textexcel.*;

public class TextExcel extends Application{

	public TextExcel () {
        super();
    }
	
	public static void main(String[] args) {
		TextExcel textExcel = new TextExcel(); 
		Scanner input= new Scanner (System.in);



        System.out.println("Welcome to TextExcel!");
        CommandParser parser = new CommandParser(textExcel);

        do {
            System.out.print("Enter a command: ");
        } while(parser.parseInput(input.nextLine()));
	} 
}