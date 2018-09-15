package exceptions;

public class ShopOperationException extends RuntimeException {
	/**
	 * 抛出的RuntimeException可以自己定义一个异常类ShopOperationException，
	 * 继承RuntimeException，这样看上去更加直观的知道是什么异常
	 */
	private static final long serialVersionUID = 5415304283763828743L;

	public ShopOperationException(String msg) {
		super(msg);
	}
}