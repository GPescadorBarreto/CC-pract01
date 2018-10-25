import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class Stack.
 */
public class Stack {
	
	/** The stack alphabet. */
	private Alphabet stackAlphabet;
	
	/** The stack. */
	private Vector<String> stack = new Vector<String>();

	/**
	 * Instantiates a new stack.
	 */
	Stack() {
	}

	/**
	 * Instantiates a new stack.
	 *
	 * @param stack the stack
	 */
	public Stack(Stack stack) {
		this.stackAlphabet = new Alphabet(stack.getAlphabet());
		this.stack = new Vector<String>(stack.getStack());
	}

	/**
	 * Sets the up the alphabet.
	 *
	 * @param alphabet the new up alphabet
	 */
	void setupAlphabet(String[] alphabet) {
		stackAlphabet = new Alphabet(alphabet);
	}

	/**
	 * Adds a new symbol to the stack.
	 *
	 * @param symbol the symbol
	 */
	void add(String symbol) {
		if (isInAlphabet(symbol))
			stack.add(symbol);
		else
			throw new IllegalArgumentException("Illegal symbol added to the stack");
	}
	
	/**
	 * Adds a new array of symbols to the stack.
	 *
	 * @param symbols the symbols
	 */
	void add(String[] symbols) {
		for(String item : symbols)
			add(item);
	}

	/**
	 * Pops the next symbol out of the stack.
	 *
	 * @return the next symbol
	 */
	String getNext() {
		String firstSymbol = stack.firstElement();
		stack.remove(0);
		return firstSymbol;
	}

	/**
	 * See the next symbol.
	 *
	 * @return the string
	 */
	String seeNext() {
		return stack.firstElement();
	}

	/**
	 * Checks if the symbol is in the alphabet.
	 *
	 * @param symbol the symbol
	 * @return true, if is in alphabet
	 */
	boolean isInAlphabet(String symbol) {
		return stackAlphabet.contains(symbol);
	}
	
	/**
	 * Checks if the symbols are in the alphabet.
	 *
	 * @param symbols the symbols
	 * @return true, if successful
	 */
	boolean areInAlphabet(String[] symbols) {
		for (String item : symbols)
			if (!isInAlphabet(item))
				return false;
		return true;
	}

	/**
	 * Gets the alphabet.
	 *
	 * @return the alphabet
	 */
	Alphabet getAlphabet() {
		return stackAlphabet;
	}

	/**
	 * Gets the stack.
	 *
	 * @return the stack
	 */
	Vector<String> getStack() {
		return stack;
	}

	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	boolean isEmpty() {
		return stack.isEmpty();
	}
}
