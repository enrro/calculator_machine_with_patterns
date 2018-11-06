package behavior;

public class OutOfStockState implements State {
	public void insert(VendingMachineUI view)
	{
		System.out.println("Returning Money");
		view.display("Out of Stock");
	}
		
	public void eject(VendingMachineUI view)
	{
		
	}
	public void dispense(VendingMachineUI view)
	{
		
	}
}
