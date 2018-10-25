
// TODO: Auto-generated Javadoc
/**
 * The Class TransitionFunction.
 */
public class TransitionFunction {
	
	/** The current state. */
	private State currentState;
	
	/** The input symbol. */
	private String inputSymbol;
	
	/** The current stack symbol. */
	private String currentStackSymbol;
	
	/** The final state. */
	private State finalState;
	
	/** The final stack symbols. */
	private String[] finalStackSymbols;
	
	/**
	 * Instantiates a new transition function.
	 *
	 * @param currentState the current state
	 * @param inputSymbol the input symbol
	 * @param currentStackSymbol the current stack symbol
	 * @param finalState the final state
	 * @param finalStackSymbols the final stack symbols
	 */
	public TransitionFunction(State currentState, String inputSymbol, String currentStackSymbol, State finalState, String[] finalStackSymbols){
		this.currentState = currentState;
		this.inputSymbol = inputSymbol;
		this.currentStackSymbol = currentStackSymbol;
		this.finalState = finalState;
		this.finalStackSymbols = finalStackSymbols;
	}
	
	/**
	 * Instantiates a new transition function.
	 *
	 * @param function the function
	 */
	public TransitionFunction(TransitionFunction function) {
		this(function.getCurrentState(), function.getInputSymbol(), function.getCurrentStackSymbol(), function.resultState(), function.resultStackSymbols());
	}

	/**
	 * Gets the current stack symbol.
	 *
	 * @return the current stack symbol
	 */
	protected String getCurrentStackSymbol() {
		return currentStackSymbol;
	}

	/**
	 * Gets the input symbol.
	 *
	 * @return the input symbol
	 */
	protected String getInputSymbol() {
		return inputSymbol;
	}

	/**
	 * Gets the current state.
	 *
	 * @return the current state
	 */
	protected State getCurrentState() {
		return currentState;
	}

	/**
	 * Checks if the function matches the parameters.
	 *
	 * @param currentState the current state
	 * @param inputSymbol the input symbol
	 * @param nextStackSymbol the next stack symbol
	 * @return true, if successful
	 */
	boolean matches(State currentState, String inputSymbol, String nextStackSymbol) {
		if(this.currentState.equals(currentState) && this.inputSymbol.equals(inputSymbol) && this.currentStackSymbol.equals(nextStackSymbol))
			return true;
		return false;
	}
	
	/**
	 * Result state.
	 *
	 * @return the state
	 */
	State resultState() {
		return finalState;
	}
	
	/**
	 * Result stack symbols.
	 *
	 * @return the string[]
	 */
	String[] resultStackSymbols() {
		return finalStackSymbols;
	}
	
	/** 
	 * Represents the function as a string
	 * 
	 * @return the result
	 */
	public String toString() {
		String result = ""+currentState.getName()+" "+inputSymbol+" "+currentStackSymbol+" "+finalState.getName();
		String[] dummy = resultStackSymbols();
		for (String item : dummy) {
			result += " "+item;
		}
		return result;
	}
}
