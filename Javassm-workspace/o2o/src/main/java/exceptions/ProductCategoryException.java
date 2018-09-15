package exceptions;
/**
* @author xf
* @version 2018年8月13日 下午5:57:21
*/
public class ProductCategoryException extends RuntimeException {
	private static final long serialVersionUID = -7773535427407954839L;

	/**
	 * 
	 * @ClassName: ProductCategoryException
	 * 
	 * @Description: 继承RuntimeException,便于异常时候的回滚。 保持所有的操作在一个事务中。
	 * 
	 *               这样在标注了@Transactional事务的方法中，出现了异常，才会回滚数据。
	 * 
	 *               默认情况下，如果在事务中抛出了未检查异常（继承自 RuntimeException 的异常）或者 Error，则 Spring
	 *               将回滚事务；除此之外，Spring 不会回滚事务。
	 * 
	 */
	public ProductCategoryException(String msg) {
		super(msg);
	}
}
