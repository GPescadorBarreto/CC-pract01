import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class AutomatonTester.
 */
public class AutomatonTester {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		if(args.length != 4 && args.length != 5)
			throw new IllegalArgumentException("Incorrect program arguments, check documentation");
		boolean finalState;
		if(args[1].equals("y"))
			finalState = true;
		else if(args[1].equals("n"))
			finalState = false;
		else
			throw new IllegalArgumentException("Incorrect program arguments. For a final state automaton, write 'y', and 'n' for a stack emptying automaton. Argument used: " + args[1]);
		
		PushdownAutomaton automaton = new PushdownAutomaton(new File(args[0]), finalState);
		
		boolean verbose;
		if(args[2].equals("y"))
			verbose = true;
		else if(args[2].equals("n"))
			verbose = false;
		else
			throw new IllegalArgumentException("Incorrect program arguments. For a verbose execution, write 'y', and 'n' for a silent execution. Argument used: " + args[2]);
		
		
		String[] input = null;
		if(args[3].equals("y")) {
			System.out.println("Insert all your input tape, separated by spaces:");
			Scanner scan = new Scanner(System.in);
			String line = scan.nextLine();
			scan.close();
			input = line.split("\\s+");
		}
		else if(args[3].equals("n")) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(new File(args[4])));
				input = br.readLine().split("\\s+");
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else 
			throw new IllegalArgumentException("Incorrect program arguments. To setup input tape from console, write 'y', and 'n' if you want it to be read from a file. Argument used: " + args[3]);
		if(!input[0].isEmpty())
			automaton.SetupInput(input);
		if (automaton.Execute(verbose))
			System.out.println("The input string belongs to this automaton's language");
		else 
			System.out.println("The input string doesn't belong to this automaton's language");
	}

}
