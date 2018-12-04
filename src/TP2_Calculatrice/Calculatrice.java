package TP2_Calculatrice;

/* Clean Calcuatrice class to handle math operations */
public class Calculatrice 
{
	// Parametres
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
	
	public float getResult(float secondOperand) 
	{
		// if operation was not defined, return accum
		if (operation == "+")
			return accum + secondOperand;
		else if (operation == "-")
			return accum - secondOperand;
		else if (operation == "x")
			return accum * secondOperand;
		else if (operation == "/")
			return accum / secondOperand;
		else
			return accum;
	}
}
