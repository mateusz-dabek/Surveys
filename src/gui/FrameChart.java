package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class FrameChart extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private Dimension screenSize;
	private String a1, a2, a3, a4, a5, a6, a7, a8, a9;

	public FrameChart(String id, String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8, String a9) throws IOException {
		super("Chart");
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		pack();
		setSize(screenSize.width/2,screenSize.height - 35);
		setLocation(screenSize.width/2, 0);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.a4 = a4;
		this.a5 = a5;
		this.a6 = a6;
		this.a7 = a7;
		this.a8 = a8;
		this.a9 = a9;
		chart = createChart();
		chartPanel = new ChartPanel(chart);
        getContentPane().add(chartPanel);
        pack();
        revalidate();
        repaint();
	}

	/**
	 * Creates a graph
	 * 
	 * @return chart
	 */
	private JFreeChart createChart() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(Integer.parseInt(a1), "Number of answers", "1a");
		dataset.setValue(Integer.parseInt(a2), "Number of answers", "1b");
		dataset.setValue(Integer.parseInt(a3), "Number of answers", "1c");
		dataset.setValue(Integer.parseInt(a4), "Number of answers", "2a");
		dataset.setValue(Integer.parseInt(a5), "Number of answers", "2b");
		dataset.setValue(Integer.parseInt(a6), "Number of answers", "2c");
		dataset.setValue(Integer.parseInt(a7), "Number of answers", "3a");
		dataset.setValue(Integer.parseInt(a8), "Number of answers", "3b");
		dataset.setValue(Integer.parseInt(a9), "Number of answers", "3c");
		
		JFreeChart chart = ChartFactory.createBarChart("Chart",
		"Answers", "Number of answers", dataset, PlotOrientation.VERTICAL,
		false, true, false);
		
		return chart;
	}
	
}
