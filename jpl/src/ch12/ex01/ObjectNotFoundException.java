package ch12.ex01;

public class ObjectNotFoundException extends Exception {
	public final Object element;

	public ObjectNotFoundException(Object element) {
		super("No element named \"" + element + "\" found");
		this.element = element;
	}
}
