package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Usuario;

public class Login implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		System.out.println("Logando " + login);
		
		//Verificando se o usu�rio existe
		Banco banco = new Banco();
		Usuario usuario = banco.existsUsuario(login, senha);
		
		if (usuario != null) {
			System.out.println("Usu�rio existe no banco de dados de usu�rio");
			
			//Pergar o JSESSIONID, o cookie, para sess�o do usu�rio...
			HttpSession sessao = request.getSession();
			sessao.setAttribute("usuarioLogado", usuario);
			return "redirect:entrada?acao=ListaEmpresas";
			
		} else {
			return "redirect:entrada?acao=LoginForm";
		}
		
		
		
	}

}
