package UnitTests;

import java.util.List;

import playerlib.core.ChangeListener;

class TestChangeListener<T> implements ChangeListener<T> {
	private int removed;
	private int added;
	private int changed;
	
	public TestChangeListener() {
		
	}

	public int removed() {
		return removed;
	}
	
	public int changed() {
		return changed;
	}
	
	public int added() {
		return added;
	}
	
	@Override
	public void changed(List<T> added, List<T> removed, List<T> changed) {
		this.removed += removed.size();
		this.added += added.size();
		this.changed += changed.size();
	}
}
