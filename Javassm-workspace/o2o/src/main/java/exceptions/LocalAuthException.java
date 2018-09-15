package exceptions;

public class LocalAuthException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7326092280872182611L;

	/**
	 * 抛出的RuntimeException可以自己定义一个异常类ShopOperationException，
	 * 继承RuntimeException，这样看上去更加直观的知道是什么异常
	 */

	public LocalAuthException(String msg) {
		super(msg);
	}
}
