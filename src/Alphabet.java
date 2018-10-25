
import java.util.HashSet;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class Alphabet.
 */
public class Alphabet {
	
	/** The alphabet. */
	Set<String> alphabet = new HashSet<String>();

	/**
	 * Instantiates a new alphabet.
	 *
	 * @param alphabet the alphabet
	 */
	Alphabet(String[] alphabet) {
		for (String symbol : alphabet) {
			this.alphabet.add(symbol);
		}
		this.alphabet.add(".");
	}

	/**
	 * Instantiates a new alphabet.
	 *
	 * @param alphabet the alphabet
	 */
	public Alphabet(Alphabet alphabet) {
		this.alphabet.addAll(alphabet.getAlphabet());
	}

	/**
	 * Gets the alphabet.
	 *
	 * @return the alphabet
	 */
	Set<String> getAlphabet() {
		return alphabet;
	}

	/**
	 * Contains.
	 *
	 * @param element the element
	 * @return true, if successful
	 */
	boolean contains(String element) {
		return alphabet.contains(element);
	}
}
