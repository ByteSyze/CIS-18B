package template.algorithm;

/**
 * Some process that manipulates {@code data} when {@link Algorithm#algore(T data) algore()} is called.
 * */
public abstract class Algorithm<T>
{
	@SuppressWarnings("unused")
	private T data;
	
	public void setData(T data)
	{
		this.data = data;
	}
	
	/**
	 * Algorithms can implement this function to perform their task.
	 * 
	 * @return the data processed by the algorithm.
	 * 
	 * "I invented the internet" - Al Gore
	 * */
	public abstract T algore(T data);

}
