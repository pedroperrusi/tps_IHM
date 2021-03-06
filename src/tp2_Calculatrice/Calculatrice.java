package tp2_Calculatrice;

/* Clean Calcuatrice class to handle math operations */
public class Calculatrice
{
	// Parameters
	float accum; // saved value in the calculator
	String operation; // string which represents the operation
	
	public Calculatrice() 
	{
		this.clear();
	}
	
	public void clear() 
	{
		this.accum = 0;
		this.operation = "";
	}
	
	public void setOperand(float firstOperand) 
	{
		this.accum = firstOperand;
	}
	
	public void setOperation(String ops) 
	{
		this.operation = ops;
	}
	
	public float division(float firstOp, float secondOp) throws InvalidUserEntry
	{
		if (secondOp == 0) throw new InvalidUserEntry("Division par zero!");
		float div = firstOp/secondOp;
		
		return div;
	}
	
	public float getResult(float secondOperand) throws InvalidUserEntry
	{
		// if operation was not defined, return accum
		if (operation == "+")
			return accum + secondOperand;
		else if (operation == "-")
			return accum - secondOperand;
		else if (operation == "x")
			return accum * secondOperand;
		else if (operation == "/")
			return division(accum, secondOperand);
		else
			return accum;
	}
	
	public class InvalidUserEntry extends Exception
	{
		InvalidUserEntry()
		{
			super();
		}
		
		InvalidUserEntry(String errorMsg)
		{
			super(errorMsg);
		}
	}
}
