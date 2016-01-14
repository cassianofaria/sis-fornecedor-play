package util;

public class HttpException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private ErrorDTO error;
	
	public HttpException(String message) {
		this.setError(new ErrorDTO());
		this.getError().setMessage(message);
	}

	public ErrorDTO getError() {
		return error;
	}

	public void setError(ErrorDTO error) {
		this.error = error;
	}
	
}
