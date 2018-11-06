package behavior;

public class InitState implements State {
	public void insert(VendingMachineUI view)
	{
		view.qtyMoney++;
		view.display(Integer.toString(view.qtyMoney));
		view.currentState = view.readyToDispenseState;
	}
		
	public void eject(VendingMachineUI view)
	{
		
	}
	public void dispense(VendingMachineUI view)
	{
		
	}
}
