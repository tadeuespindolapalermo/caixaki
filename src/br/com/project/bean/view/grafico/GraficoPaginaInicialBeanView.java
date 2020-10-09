package br.com.project.bean.view.grafico;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.TituloController;

@Controller
@Scope(value = "request")
@ManagedBean(name = "graficoPaginaInicialBeanView")
public class GraficoPaginaInicialBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private StreamedContent model;

	private CartesianChartModel model2;

	private CartesianChartModel model3;

	@Resource
	private TituloController tituloController;

	@RequestMapping("**/gerarGraficoInicial") 
	public @ResponseBody String gerarGraficoInicial(@RequestParam(value = "dias") int dias) { 
		
		List<Map<String, Object>> titulosUltimosDias = tituloController.getTitulosUltimosDias(dias);
		
		int totalLinhas = titulosUltimosDias.size() + 1;
		
		boolean semDados = false;
		if (totalLinhas <= 1){
			totalLinhas++;
			semDados = true;  
		}
		
		String[] dados = new String[totalLinhas];
		int cont = 0;
		
		dados[cont] = "[\"" + "Tipo" +  "\"," + "\"" + "Quantidade " +  "\"," +  "\"" + "Media R$" +  "\"]";
		cont++;
		
		for (Map<String, Object> map : titulosUltimosDias) {
			dados[cont] = "[\"" +  (String) map.get("situacao") +  "\"," + "\"" +  map.get("quantidade") +  "\"," +  "\"" +  map.get("media_valor") +  "\"]";
			cont++;
		}
		
		if (semDados){
			dados[cont] = "[\"" +  "Sem Dados" +  "\"," + "\"" + 0  +  "\"," +  "\"" + 0 +  "\"]";
		}
		
		return Arrays.toString(dados);

	}

	/**
	 * Não está sendo usado
	 * @return
	 * @throws IOException
	 */
	public StreamedContent getModel() throws IOException {
		List<Map<String, Object>> mediaPorOrigem = tituloController
				.getMediaPorOrigem(7);

		DefaultPieDataset dataset = new DefaultPieDataset();
		for (Map<String, Object> map : mediaPorOrigem) {
			dataset.setValue((String) map.get("situacao"),
					Double.parseDouble(map.get("media_valor").toString()));
		}
		JFreeChart jfreechart = ChartFactory.createPieChart3D(
				"Média do títulos por origem nos últimos 7 dias", dataset,
				true, true, false);
		File chartFile = new File("dynamichart");
		ChartUtilities.saveChartAsPNG(chartFile, jfreechart, 375, 300);
		model = new DefaultStreamedContent(new FileInputStream(chartFile),
				"image/png");
		return model;
	}

	/**
	 * Não está sendo usado
	 * @return
	 * @throws IOException
	 */
	public CartesianChartModel getModel2() {
		model2 = new CartesianChartModel();
		List<Map<String, Object>> mediaMediaPorTipoAbertoFechado = tituloController
				.getMediaPorTipoAbertoFechado(7);

		for (Map<String, Object> map : mediaMediaPorTipoAbertoFechado) {
			BarChartSeries chartMediaPorOrigem = new BarChartSeries();
			chartMediaPorOrigem.setLabel((String) map.get("situacao")
					.toString().toUpperCase());
			chartMediaPorOrigem.set(map.get("quantidade"),
					(Number) map.get("media_valor"));
			model2.addSeries(chartMediaPorOrigem);
		}
		return model2;
	}

	/**
	 * Não está sendo usado
	 * @return
	 * @throws IOException
	 */
	public CartesianChartModel getModel3() {
		model3 = new CartesianChartModel();
		List<Map<String, Object>> mediaMediaPorTipoReceberPagar = tituloController
				.getMediaPorTipoReceberPagar(7);

		for (Map<String, Object> map : mediaMediaPorTipoReceberPagar) {
			BarChartSeries chartMediaPorOrigem = new BarChartSeries();
			chartMediaPorOrigem.setLabel((String) map.get("situacao")
					.toString().toUpperCase());
			chartMediaPorOrigem.set(map.get("quantidade"),
					(Number) map.get("media_valor"));
			model3.addSeries(chartMediaPorOrigem);
		}
		return model3;
	} 

	@Override
	protected Class<?> getClassImplement() {
		return null;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return null;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return null;
	}

}
