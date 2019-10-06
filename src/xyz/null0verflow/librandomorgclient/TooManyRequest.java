package xyz.null0verflow.librandomorgclient;

public class TooManyRequest extends Exception {
	private static final long serialVersionUID = 1L;

	public TooManyRequest(String error) {
		super(error);
	}
}
