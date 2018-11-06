package behavior;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VendingMachineUI extends JFrame{
	
    private static final long serialVersionUID = 1L;
    JPanel mainPanel;
    JTextField field;
    JButton [] buttons;
    // Model
    int qtyMoney;
    int qtyProducts;
    int currentState;
    
    public VendingMachineUI(int qtyProducts) {
        super();
        this.qtyProducts = qtyProducts;
        this.qtyMoney = 0;
        this.currentState = 0;
        
        this.setTitle("Vending Machine");
        this.setVisible(true);
        
        // Create main Panel
        this.mainPanel = new JPanel();
        this.mainPanel.setPreferredSize(new Dimension(400, 300));
        this.setResizable(false);
        
        // Create two panels
        JPanel inputContainer = new JPanel();
        inputContainer.setPreferredSize(new Dimension(230, 300));
        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setPreferredSize(new Dimension(100, 300));
        
        // Fill input Container JPanel
        this.field = new JTextField(15);
        inputContainer.add(this.field);
        
        // Fill buttonsContainer JPanel
        this.buttons = new JButton[3];
        this.buttons[0] = new JButton("Insert");
        this.buttons[1] = new JButton("Eject");
        this.buttons[2] = new JButton("Dispense");
        buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.PAGE_AXIS));
        for (int i = 0; i < this.buttons.length; i++){
            buttonsContainer.add(this.buttons[i]);
            this.buttons[i].addActionListener(new Controller(this));
        }
        
        // Add two JPanels to main JPanel
        this.mainPanel.add(inputContainer, BorderLayout.PAGE_START);
        this.mainPanel.add(buttonsContainer, BorderLayout.PAGE_END);
        // Add main JPanel to JFrame
        this.add(this.mainPanel);
        this.pack();
        
        // Default JFrame options
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    public void display(String text)
    {
    	this.field.setText(text);
    }
    
    static class Controller implements ActionListener{
        VendingMachineUI view;
        
        public Controller(VendingMachineUI view){
            this.view = view;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        	String sourceButton = ((JButton) e.getSource()).getText();
        	
        	switch(this.view.currentState)
        	{
        	case 0:	// Init State
        		if(sourceButton.equals("Insert"))
        		{
        			this.view.qtyMoney++;
        			this.view.display(Integer.toString(this.view.qtyMoney));
        			this.view.currentState = 1;
        		}
        		break;
        	case 1:	// Ready to Dispense State
        		if(sourceButton.equals("Insert"))
        		{
        			this.view.qtyMoney++;
        			this.view.display(Integer.toString(this.view.qtyMoney));
        		}
        		else if(sourceButton.equals("Eject"))
        		{
        			System.out.println("Returning money");
        			this.view.qtyMoney = 0;
        			this.view.display(Integer.toString(this.view.qtyMoney));
        			this.view.currentState = 0;
        		}
        		else if(sourceButton.equals("Dispense"))
        		{
        			if(this.view.qtyProducts > 1)
        			{
        				if(this.view.qtyMoney < this.view.qtyProducts)
        				{
        					System.out.println("Dispensing product(s)");
        					this.view.qtyProducts = this.view.qtyProducts - this.view.qtyMoney;
            				this.view.qtyMoney = 0;
            				this.view.display(Integer.toString(this.view.qtyMoney));
            				this.view.currentState = 0;
        				}
        				else
        				{
        					if(this.view.qtyMoney == this.view.qtyProducts)
        					{
        						System.out.println("Dispensing product(s)");
                				this.view.qtyProducts = 0;
                				this.view.qtyMoney = 0;
                				this.view.display("Out of Stock");
                				this.view.currentState = 2;
        					}
        					else
        					{
        						System.out.println("Dispensing product(s)");
        						System.out.println("Returning Money");
                				this.view.qtyProducts = 0;
                				this.view.qtyMoney = 0;
                				this.view.display("Out of Stock");
                				this.view.currentState = 2;
        					}
        				}
        			}
        			else
        			{
        				System.out.println("Dispensing product(s)");
        				this.view.qtyProducts = 0;
        				this.view.qtyMoney = 0;
        				this.view.display("Out of Stock");
        				this.view.currentState = 2;
        			}
        		}
        		break;
        	case 2:	// Out of Stock State
        		if(sourceButton.equals("Insert"))
        		{
        			System.out.println("Returning Money");
        			this.view.display("Out of Stock");
        		}
        		break;
        	}
            //this.view.display(((JButton) e.getSource()).getText());
        }
    }
    
    public static void main(String[] args) {
        new VendingMachineUI(3);
    }

}
