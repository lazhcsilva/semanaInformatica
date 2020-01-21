package WEB2.ifpe.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import WEB2.ifpe.model.Participante;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
public class CertificadoController {

	
	
	@GetMapping("/view/{filename}")
	public void obterRelatorio(HttpSession session, HttpServletResponse response, @PathVariable String filename) throws JRException, IOException{
		// Define os parâmetros
		Map<String, Object> parametros = new HashMap<>();
		Participante participante = (Participante) session.getAttribute("usuarioLogado");

		parametros.put("nome", participante.getNome());
		parametros.put("descricao", "Descrição aqui...");
		parametros.put("duracao", "20 minutos");
		
		// inline: abre o arquivo no navegador, attachment: baixa o arquivo
		String acao = "inline";
			
		// ===== Compila o template quando ele ainda não está compilado (ou seja, com extensão .jrxml) ===== //
//		InputStream inputStream = this.getClass().getResourceAsStream("/jasper/movimentos.jrxml");
//		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		
		// ===== Usa o template que ja está compilado (ou seja, com extensão .jasper) ===== //
		InputStream inputStream = this.getClass().getResourceAsStream("/jasper/" + filename + ".jasper");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
		
		// Passa para o JasperPrint o relatório, os parâmetros e a fonte dos dados, no caso uma lista
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());
		
		// Configura a resposta para o tipo PDF
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", acao+"; filename=certificado.pdf");
		
		// Faz a exportação do relatório para o HttpServletResponse
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
	
	@GetMapping("/compile/{filename}")
	public String compilarRelatorio(@PathVariable String filename) {
		InputStream inputStream = this.getClass().getResourceAsStream("/jasper/" + filename + ".jrxml");
		OutputStream outputStream = null;
		
		try {
			outputStream = new FileOutputStream("src/main/resources/jasper/" + filename + ".jasper");
			JasperCompileManager.compileReportToStream(inputStream, outputStream);
			System.out.println("compilado");
			outputStream.close();
		} catch (Exception e) {
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					System.out.println("erro ao fechar");
				}
			}
		}
		
		return "index";
	}

}

