/**
 * InitState
 */
public class InitState implements State {
    public void firstInput(CalculadoraGUI view){
        clear(view);
        view.operand1 = this.view.operand1 + "1";
        view.setDisplay(this.view.operand1);
        view.currentState = 1;
    }

	public void secondInput(CalculadoraGUI view){

    }

	public void Clear(CalculadoraGUI view){
        view.operand1 = "";
        view.operand2 = "";
        view.currentState = 0;
    }
}