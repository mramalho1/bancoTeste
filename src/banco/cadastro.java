package banco;
import javax.swing.JOptionPane;
import java.util.ArrayList;
class Item {
	String usuario;
	String senha;
	float saldo;
	Item(String a, String b, float c) { usuario = a; senha = b; saldo = c; }
}
public class cadastro {
	public static void main(String[] args) {
		ArrayList<Item> login = new ArrayList<>();
		Object[] lc = {"Login", "Cadastrar", "Sair"};
		int inicio = 9;
		boolean logado = false;
		
		// Guardamos o OBJETO da conta logada. Se for null, ninguém está logado.
		Item contaLogada = null;
		
		while(inicio != 2 && inicio != 3) {
			inicio = JOptionPane.showOptionDialog(null, "Selecione a opção desejada:", "Banco",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, lc, lc[0]);
		
			// Cadastrar
			if(inicio == 1) {
				String userNew = JOptionPane.showInputDialog("Insira o nome");
				String passNew = JOptionPane.showInputDialog("Crie uma senha");
				float saldoNew = 0;
				login.add(new Item(userNew, passNew, saldoNew));
				JOptionPane.showMessageDialog(null, "Conta criada!");
			}
			// Login
			else if(inicio == 0) {
				String user = JOptionPane.showInputDialog("Insira seu usuário");
				String senha = JOptionPane.showInputDialog("Insira a senha");
				boolean achou = false;
				
				for(int i = 0; i < login.size(); i++) {
					Item itemAtual = login.get(i);
					if(user.equals(itemAtual.usuario) && senha.equals(itemAtual.senha)) {
						logado = true;
						contaLogada = itemAtual; // Guardamos a referência direta da conta
						achou = true;
						JOptionPane.showMessageDialog(null, "Seja bem-vindo!");
						inicio = 3;
						break;
					}
				}
				if(!achou) {
					JOptionPane.showMessageDialog(null, "Usuário ou senha incorreto(s)");
				}
			}
			
			// Menu de Operações Bancárias
			if(inicio == 3) {
				int opcoes = 6;
				Object[] caixa = {"Depositar", "Sacar", "Saldo", "PIX", "Deslogar", "Sair"};
				
				while(opcoes != 4 && logado == true) {
					// Não precisa mais de laço 'for' aqui! Alteramos direto na contaLogada.
					opcoes = JOptionPane.showOptionDialog(null, "Selecione a opção desejada:", "Banco",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, caixa, caixa[0]);
					
					if(opcoes == 0) {
						String deposito = JOptionPane.showInputDialog("Quanto você deseja depositar?");
						if(deposito != null) {
							contaLogada.saldo += Float.parseFloat(deposito); // Atualiza direto na memória do objeto
							JOptionPane.showMessageDialog(null, "Seu saldo final: R$" + contaLogada.saldo);
						}
					} else if(opcoes == 1) {
						String saque = JOptionPane.showInputDialog("Quanto você deseja sacar?");
						if(saque != null) {
							contaLogada.saldo -= Float.parseFloat(saque); // Atualiza direto na memória do objeto
							JOptionPane.showMessageDialog(null, "Seu saldo final: R$" + contaLogada.saldo);
						}
					} else if(opcoes == 2){
						JOptionPane.showMessageDialog(null, "Seu saldo: R$" + contaLogada.saldo);
					}else if(opcoes == 3) {
						String userTransf = JOptionPane.showInputDialog("Para qual usuário você deseja transferir o PIX?");
						String saldoTrans = JOptionPane.showInputDialog("Qual a quantidade você deseja transferir?");
						boolean existe = false;
						for(int i = 0 ; i < login.size() ; i++) {
							Item itemPix = login.get(i);
							if(userTransf.equals(itemPix.usuario)) {
								contaLogada.saldo -=Float.parseFloat(saldoTrans);
								itemPix.saldo += Float.parseFloat(saldoTrans);
								existe = true;
								break;
							}
						}if(!existe) {
							JOptionPane.showMessageDialog(null, "O usuário que você está tentando transferir, não existe!");
						}
					}else if(opcoes == 4) {
						logado = false;
						contaLogada = null; // Limpa a sessão
						inicio = 9; // Volta para o menu principal (Login/Cadastro)
					} else if(opcoes == 5) {
						break; // Fecha o programa
					}
				}
			}
		}
	}
}



