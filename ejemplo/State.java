package behavior;

public interface State {
	public void insert(VendingMachineUI view);
	public void eject(VendingMachineUI view);
	public void dispense(VendingMachineUI view);
}
