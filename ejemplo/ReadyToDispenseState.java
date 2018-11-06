package behavior;

public class ReadyToDispenseState implements State {
	public void insert(VendingMachineUI view)
	{
		view.qtyMoney++;
		view.display(Integer.toString(view.qtyMoney));
	}
		
	public void eject(VendingMachineUI view)
	{
		System.out.println("Returning money");
		view.qtyMoney = 0;
		view.display(Integer.toString(view.qtyMoney));
		view.currentState = view.initState;
	}
	public void dispense(VendingMachineUI view)
	{
		if(view.qtyProducts > 1)
		{
			if(view.qtyMoney < view.qtyProducts)
			{
				System.out.println("Dispensing product(s)");
				view.qtyProducts = view.qtyProducts - view.qtyMoney;
				view.qtyMoney = 0;
				view.display(Integer.toString(view.qtyMoney));
				view.currentState = view.initState;
			}
			else
			{
				if(view.qtyMoney == view.qtyProducts)
				{
					System.out.println("Dispensing product(s)");
    				view.qtyProducts = 0;
    				view.qtyMoney = 0;
    				view.display("Out of Stock");
    				view.currentState = view.outOfStockState;
				}
				else
				{
					System.out.println("Dispensing product(s)");
					System.out.println("Returning Money");
    				view.qtyProducts = 0;
    				view.qtyMoney = 0;
    				view.display("Out of Stock");
    				view.currentState = view.outOfStockState;;
				}
			}
		}
		else
		{
			System.out.println("Dispensing product(s)");
			view.qtyProducts = 0;
			view.qtyMoney = 0;
			view.display("Out of Stock");
			view.currentState = view.outOfStockState;;
		}
	}
}
