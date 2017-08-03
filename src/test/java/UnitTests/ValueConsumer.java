package UnitTests;

import playerlib.core.ValueListener;

public class ValueConsumer<D, C> implements ValueListener<C> {
	private D expectedValue;
	private boolean matched;
	private int changeCount;
	
	public ValueConsumer(D expectedValue) {
		this.expectedValue = expectedValue;
		changeCount = 0;
	}
	
	@Override
	public <T> void changed(C source, T value) {
		matched = expectedValue.equals(value);
		changeCount++;
	}
	
	public boolean matched() {
		return matched;
	}
	
	public int changeCount() {
		return changeCount;
	}
}
