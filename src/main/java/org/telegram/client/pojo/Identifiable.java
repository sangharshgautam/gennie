package org.telegram.client.pojo;

public abstract class Identifiable {
	public String getIdAsString() {
		return String.valueOf(getId());
	}

	public abstract int getId();
}
