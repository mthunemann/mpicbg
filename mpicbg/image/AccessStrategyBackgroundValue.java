package mpicbg.image;

public class AccessStrategyBackgroundValue extends AccessStrategyAbstract
{
	final ConstantCursor backgroundValueCursor; 
	final AccessStrategy directAccessStrategy;
	
	public AccessStrategyBackgroundValue(final Container container, final ConstantCursor backgroundValueCursor, final Cursor cursor)
	{
		super(container, (Cursor)cursor);
		this.backgroundValueCursor = backgroundValueCursor;
		directAccessStrategy = container.createDirectAccessStrategy();
		
		if (cursor != null)
			update();
	}	
	
	public AccessStrategyBackgroundValue(final Container container, final ConstantCursor backgroundValueCursor)
	{
		this(container, backgroundValueCursor, null);
	}

	@Override
	public AccessStrategy clone(final Cursor c)
	{
		return new AccessStrategyBackgroundValue(container, backgroundValueCursor, cursor);
	}	
	
	@Override
	protected void update()
	{
		if (cursor.isInside())
		{
			read = directAccessStrategy;
			write = directAccessStrategy;
			operator = directAccessStrategy;
		}
		else
		{
			read = backgroundValueCursor;
			write = backgroundValueCursor;
			operator = backgroundValueCursor;
		}
	}
}
