import java.util.Arrays;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class TFLibrary.
 */
public class TFLibrary {
	
	/** The library. */
	private Vector<TransitionFunction> library = new Vector<TransitionFunction>();

	/**
	 * Instantiates a new TF library.
	 */
	TFLibrary() {
	}
	
	/**
	 * Instantiates a new TF library.
	 *
	 * @param library the library
	 */
	TFLibrary(TFLibrary library){
		this.library.addAll(library.getLibrary());
	}

	/**
	 * Adds a new transition function.
	 *
	 * @param TFsymbols the function's symbols
	 */
	void add(String[] TFsymbols) {
		library.add(new TransitionFunction(new State(TFsymbols[0]), TFsymbols[1], TFsymbols[2], new State(TFsymbols[3]),
				Arrays.copyOfRange(TFsymbols, 4, TFsymbols.length)));
	}

	/**
	 * Viable functions.
	 *
	 * @param currentState the current state
	 * @param nextInputSymbol the next input symbol
	 * @param currentStackSymbol the current stack symbol
	 * @return the vector
	 */
	Vector<TransitionFunction> viableFunctions(State currentState, String nextInputSymbol, String currentStackSymbol) {
		Vector<TransitionFunction> result = new Vector<TransitionFunction>();
		for (TransitionFunction function : library) {
			if (function.matches(currentState, nextInputSymbol, currentStackSymbol)
					|| function.matches(currentState, ".", currentStackSymbol)) {
				TransitionFunction matchingFunction = new TransitionFunction(function);
				result.add(matchingFunction);
			}
		}
		return result;
	}
	
	/**
	 * Gets the library.
	 *
	 * @return the library
	 */
	protected Vector<TransitionFunction> getLibrary(){
		return library;
	}
}
