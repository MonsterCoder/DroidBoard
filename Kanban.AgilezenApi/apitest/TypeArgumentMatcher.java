import org.mockito.ArgumentMatcher;

public class TypeArgumentMatcher<T> extends ArgumentMatcher<T> {

	private Class<T> clazz;
	
	public TypeArgumentMatcher(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public boolean matches(Object argument) {	
		return this.clazz.isInstance(argument) ;
	}


}