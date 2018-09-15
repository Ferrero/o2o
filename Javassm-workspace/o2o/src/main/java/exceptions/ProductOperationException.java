package exceptions;
/**
* @author xf
* @version 2018年8月22日 下午4:37:37
*/
public class ProductOperationException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2127223650256950794L;

	/**
	 * 抛出的RuntimeException可以自己定义一个异常类ShopOperationException，
	 * 继承RuntimeException，这样看上去更加直观的知道是什么异常
	 */
	

	public ProductOperationException(String msg) {
		super(msg);
	}
}
