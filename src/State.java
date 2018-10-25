
// TODO: Auto-generated Javadoc
/**
 * The Class State.
 */
public class State {
	
	/** The final state. */
	private boolean finalState = false;
	
	/** The name. */
	private String name;

	/**
	 * Instantiates a new state.
	 *
	 * @param name the name
	 */
	State(String name) {
		this.name = name;
	}
	
	/**
	 * Instantiates a new state.
	 *
	 * @param state the state
	 */
	public State(State state) {
		this.name = new String(state.getName());
		finalState = state.isFinal();
	}

	/**
	 * Sets the final.
	 */
	protected void setFinal() {
		finalState = true;
	}

	/**
	 * Checks if is final.
	 *
	 * @return true, if is final
	 */
	boolean isFinal() {
		return finalState;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof State) {
			State toCompare = (State) obj;
			return this.name.equals(toCompare.name);
		}
		return false;
	}
}
