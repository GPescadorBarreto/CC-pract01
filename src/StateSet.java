import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class StateSet.
 */
public class StateSet {
	
	/** The state set. */
	private Set<State> stateSet = new HashSet<State>();
	
	/** The current state. */
	private State currentState;

	/**
	 * Instantiates a new state set.
	 *
	 * @param sets the sets
	 */
	StateSet(String sets) {
		String[] dummy = sets.split("\\s+");
		for (String set : dummy)
			stateSet.add(new State(set));

	}

	/**
	 * Instantiates a new state set.
	 *
	 * @param stateSet the state set
	 */
	public StateSet(StateSet stateSet) {
		this.stateSet.addAll(stateSet.getStateSet());
		currentState = stateSet.getCurrentState();
	}

	/**
	 * Sets the current state.
	 *
	 * @param name the new current state
	 */
	protected void setCurrentState(String name) {
		currentState = findState(name);
	}
	
	/**
	 * Sets the current state.
	 *
	 * @param state the new current state
	 */
	protected void setCurrentState(State state) {
		currentState = findState(state.getName());
	}

	/**
	 * Gets the current state.
	 *
	 * @return the current state
	 */
	State getCurrentState() {
		return currentState;
	}

	/**
	 * Sets the final state.
	 *
	 * @param name the new final state
	 */
	protected void setFinalState(String name) {
		State finale = findState(name);
		if (finale == null)
			throw new IllegalArgumentException("Final state " + name + " isn't part of the state set");
		else
			finale.setFinal();
	}

	/**
	 * Checks if is final state.
	 *
	 * @param name the name
	 * @return true, if is final state
	 */
	boolean isFinalState(String name) {
		return currentState.isFinal();
	}

	/**
	 * Find state.
	 *
	 * @param name the name
	 * @return the state
	 */
	private State findState(String name) {
		Iterator<State> i = stateSet.iterator();
		while (i.hasNext()) {
			State aux = i.next();
			if (aux.equals(new State(name))) {
				return aux;
			}
		}
		return null;
	}

	/**
	 * Contains.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	boolean contains(String name) {
		return stateSet.contains(new State(name));
	}

	/**
	 * Gets the state set.
	 *
	 * @return the state set
	 */
	protected Set<State> getStateSet() {
		return stateSet;
	}
}
