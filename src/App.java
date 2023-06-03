import br.univali.cc.m3.controllers.Bank;
import br.univali.cc.m3.views.AtmGUI;

public class App {

  public static void main(String[] args) {
    Bank bank = new Bank("Banco do Brasil", 1);
    AtmGUI atm = new AtmGUI(bank);

    atm.menu();
  }
}