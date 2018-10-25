import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class PushdownAutomaton.
 */
public class PushdownAutomaton {
	
	/** The automaton stack. */
	Stack automatonStack = new Stack();
	
	/** The input tape. */
	Stack inputTape = new Stack();
	
	/** The state set. */
	StateSet stateSet;
	
	/** The transition functions. */
	TFLibrary transitionFunctions = new TFLibrary();
	
	/** The final state. */
	boolean finalState;

	/**
	 * Instantiates a new pushdown automaton.
	 *
	 * @param configPath the config path
	 * @param finalState the final state
	 */
	PushdownAutomaton(File configPath, boolean finalState) {
		this.finalState = finalState;
		try (BufferedReader br = new BufferedReader(new FileReader(configPath))) {
			String line = br.readLine();
			String[] symbols;
			while (line.startsWith("#"))
				line = br.readLine();
			stateSet = new StateSet(line);
			line = br.readLine();
			symbols = line.split("\\s+");
			inputTape.setupAlphabet(symbols);
			line = br.readLine();
			symbols = line.split("\\s+");
			automatonStack.setupAlphabet(symbols);
			line = br.readLine();
			symbols = line.split("\\s+");
			if (symbols.length > 1)
				throw new IllegalArgumentException("There's more than one initial state");
			else if (stateSet.contains(symbols[0]))
				stateSet.setCurrentState(symbols[0]);
			else
				throw new IllegalArgumentException("Initial state " + symbols[0] + " isn't part of the state set");
			line = br.readLine();
			symbols = line.split("\\s+");
			if (symbols.length > 1)
				throw new IllegalArgumentException("There's more than one initial stack symbol");
			else
				automatonStack.add(symbols[0]);
			if (finalState) {
				line = br.readLine();
				symbols = line.split("\\s+");
				for (String symbol : symbols) {
					stateSet.setFinalState(symbol);
				}
			}
			while ((line = br.readLine()) != null) {
				symbols = line.split("\\s+");
				if (symbols.length < 5)
					throw new IllegalArgumentException("Incorrect number of transition function elements");
				else if (stateSet.contains(symbols[0]) && inputTape.isInAlphabet(symbols[1])
						&& automatonStack.isInAlphabet(symbols[2]) && stateSet.contains(symbols[3])
						&& automatonStack.areInAlphabet(Arrays.copyOfRange(symbols, 4, symbols.length)))
					transitionFunctions.add(symbols);
				else
					throw new IllegalArgumentException("Incorrect symbols in transition functions");
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.printf("File not found, check path.");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.printf("Config file not formatted correctly");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Instantiates a new pushdown automaton.
	 *
	 * @param automaton the automaton
	 */
	PushdownAutomaton(PushdownAutomaton automaton) {
		automatonStack = new Stack(automaton.getAutomatonStack());
		inputTape = new Stack(automaton.getInputTape());
		stateSet = new StateSet(automaton.getStateSet());
		transitionFunctions = new TFLibrary(automaton.getTFLibrary());
		finalState = isFinalStateType();
	}

	/**
	 * Restore.
	 *
	 * @param automaton the automaton
	 */
	private void restore(PushdownAutomaton automaton) {
		automatonStack.restoreStack(automaton.getAutomatonStack());
		inputTape.restoreStack(automaton.getInputTape());
		stateSet.setCurrentState(automaton.getStateSet().getCurrentState());
	}

	/**
	 * Checks if is final state type.
	 *
	 * @return true, if is final state type
	 */
	private boolean isFinalStateType() {
		return finalState;
	}

	/**
	 * Gets the TF library.
	 *
	 * @return the TF library
	 */
	private TFLibrary getTFLibrary() {
		return transitionFunctions;
	}

	/**
	 * Gets the state set.
	 *
	 * @return the state set
	 */
	private StateSet getStateSet() {
		return stateSet;
	}

	/**
	 * Gets the input tape.
	 *
	 * @return the input tape
	 */
	private Stack getInputTape() {
		return inputTape;
	}

	/**
	 * Gets the automaton stack.
	 *
	 * @return the automaton stack
	 */
	protected Stack getAutomatonStack() {
		return automatonStack;
	}

	/**
	 * Setup input.
	 *
	 * @param input the input
	 */
	public void SetupInput(String[] input) {
		for (String symbol : input)
			inputTape.add(symbol);
	}

	/**
	 * Execute.
	 *
	 * @param verbose the verbose
	 * @return true, if successful
	 */
	public boolean Execute(boolean verbose) {
		Vector<TransitionFunction> viableFunctions = new Vector<TransitionFunction>();
		while (!inputTape.isEmpty()) {
			viableFunctions = transitionFunctions.viableFunctions(stateSet.getCurrentState(), inputTape.seeNext(),
					automatonStack.seeNext());
			if (viableFunctions.size() == 0)
				return checkIfDone();
			else if (viableFunctions.size() > 1) {
				PushdownAutomaton backup = new PushdownAutomaton(this);
				while (viableFunctions.size() > 1) {
					if(verbose){
						System.out.println("More than one viable function:");
						for(TransitionFunction tf : viableFunctions)
							System.out.println(tf.toString());
						System.out.println("Executing...");
					}
					performFunction(viableFunctions.firstElement(), verbose);
					if (Execute(verbose))
						return true;
					if (verbose)
						System.out.println("Incorrect path, going back for another function...");
					restore(backup);
					viableFunctions.remove(0);
					if (verbose){
						print();
						System.out.println("Resuming...");
					}
				}
			}
			performFunction(viableFunctions.firstElement(), verbose);
		}
		if (checkIfDone())
			return true;
		while ((viableFunctions = transitionFunctions.viableFunctions(stateSet.getCurrentState(), ".",
				automatonStack.seeNext())).size() > 0) {
			if (viableFunctions.size() > 1) {
				PushdownAutomaton backup = new PushdownAutomaton(this);
				while (viableFunctions.size() > 1) {
					performFunction(viableFunctions.firstElement(), verbose);
					if (Execute(verbose))
						return true;
					if (verbose)
						System.out.println("Incorrect path, going back for another function...");
					restore(backup);
					viableFunctions.remove(0);
				}
			}
			performFunction(viableFunctions.firstElement(), verbose);
			if (checkIfDone())
				return true;
		}

		return false;
	}

	/**
	 * Check if done.
	 *
	 * @return true, if successful
	 */
	private boolean checkIfDone() {
		if (finalState && stateSet.getCurrentState().isFinal() && inputTape.isEmpty())
			return true;
		else if (!finalState && automatonStack.isEmpty() && inputTape.isEmpty())
			return true;
		return false;
	}

	/**
	 * Perform function.
	 *
	 * @param function the transition function
	 */
	private void performFunction(TransitionFunction function) {
		System.out.println(function.toString());
		if (!function.getInputSymbol().equals("."))
			inputTape.getNext();
		automatonStack.getNext();
		stateSet.setCurrentState(function.resultState());
		if (!function.resultStackSymbols()[0].equals("."))
			automatonStack.add(function.resultStackSymbols());
	}

	/**
	 * Performs a transition function.
	 *
	 * @param function the transition function
	 * @param verbose the verbose parameter
	 */
	private void performFunction(TransitionFunction function, boolean verbose) {
		performFunction(function);
		if (verbose){
			print();
			System.out.println();
		}
	}

	/**
	 * Prints the automaton's current situation.
	 */
	public void print() {
		System.out.println("Current state: " + getStateSet().getCurrentState().getName());
		System.out.println("Input tape: " + inputTape.getStack().toString());
		System.out.println("Stack: " + automatonStack.getStack().toString());
	}
}
