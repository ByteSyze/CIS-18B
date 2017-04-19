package template.algorithm;

/**
 * Some process that manipulates {@code data} when {@link Algorithm#algore(T data) algore()} is called.
 * */
public interface Algorithm<T>
{
	
	/**
	 * Algorithms can implement this function to perform their task.
	 * <p><i>"I invented the internet"</i> - Al Gore</p>
	 * 
	 * @return the data processed by the algorithm.
	 * */
	public T algore(T data, Object... metadata);

}
