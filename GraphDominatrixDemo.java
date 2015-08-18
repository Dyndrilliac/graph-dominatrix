/*
 * Title: GraphDominatrixDemo
 * Author: Matthew Boyette
 * Date: 11/28/2014
 * 
 * This application was written for the programming project assigned in COT 4560. It aims to determine the domination number
 * (smallest dominating set) of a graph as well as its dominating function.
 */

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import api.gui.swing.ApplicationWindow;
import api.gui.swing.RichTextPane;
import api.util.EventHandler;
import api.util.Support;

public class GraphDominatrixDemo
{
	public final static void main(final String[] args)
	{
		new GraphDominatrixDemo(args);
	}
	
	private boolean				isDebugging	= false;
	private RichTextPane		output		= null;
	private ApplicationWindow	window		= null;
	
	public GraphDominatrixDemo(final String[] args)
	{
		this.setDebugging(Support.promptDebugMode(this.getWindow()));
		
		// Define a self-contained ActionListener event handler.
		EventHandler<GraphDominatrixDemo> myActionPerformed = new EventHandler<GraphDominatrixDemo>(this)
		{
			private final static long	serialVersionUID	= 1L;
			
			@Override
			public final void run(final AWTEvent event)
			{
				ActionEvent actionEvent = (ActionEvent)event;
				GraphDominatrixDemo parent = this.getParent();
				
				if (parent.getOutput() != null)
				{
					/*
					 * JDK 7 allows string objects as the expression in a switch statement.
					 * This generally produces more efficient byte code compared to a chain of if statements.
					 * http://docs.oracle.com/javase/7/docs/technotes/guides/language/strings-switch.html
					 */
					switch (actionEvent.getActionCommand())
					{
						case "Clear":
							
							parent.getOutput().clear();
							break;
							
						case "Open":
							
							parent.getOutput().openOrSaveFile(true);
							break;
							
						case "Save":
							
							parent.getOutput().openOrSaveFile(false);
							break;
							
						default:
							
							break;
					}
				}
			}
		};
		
		// Define a self-contained interface construction event handler.
		EventHandler<GraphDominatrixDemo> myDrawGUI = new EventHandler<GraphDominatrixDemo>(this)
		{
			private final static long	serialVersionUID	= 1L;
			
			@Override
			public final void run(final ApplicationWindow window)
			{
				GraphDominatrixDemo parent = this.getParent();
				Container contentPane = window.getContentPane();
				JMenuBar menuBar = new JMenuBar();
				JMenu fileMenu = new JMenu("File");
				JMenuItem clearOption = new JMenuItem("Clear");
				JMenuItem openOption = new JMenuItem("Open");
				JMenuItem saveOption = new JMenuItem("Save");
				JPanel graphPanel = new JPanel();
				RichTextPane outputBox = new RichTextPane(window, true, window.isDebugging());
				JScrollPane outputPanel = new JScrollPane(outputBox);
				
				clearOption.setFont(Support.DEFAULT_TEXT_FONT);
				clearOption.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.Event.CTRL_MASK));
				clearOption.setMnemonic('C');
				clearOption.addActionListener(window);
				openOption.setFont(Support.DEFAULT_TEXT_FONT);
				openOption.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.Event.CTRL_MASK));
				openOption.setMnemonic('O');
				openOption.addActionListener(window);
				saveOption.setFont(Support.DEFAULT_TEXT_FONT);
				saveOption.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.Event.CTRL_MASK));
				saveOption.setMnemonic('S');
				saveOption.addActionListener(window);
				fileMenu.setFont(Support.DEFAULT_TEXT_FONT);
				fileMenu.setMnemonic('F');
				fileMenu.add(clearOption);
				fileMenu.add(openOption);
				fileMenu.add(saveOption);
				menuBar.setFont(Support.DEFAULT_TEXT_FONT);
				menuBar.add(fileMenu);
				window.setJMenuBar(menuBar);
				contentPane.setLayout(new BorderLayout());
				contentPane.add(graphPanel, BorderLayout.CENTER); 
				contentPane.add(outputPanel, BorderLayout.SOUTH);
				parent.setOutput(outputBox);
			}
		};
		
		this.setWindow(new ApplicationWindow(null, "Graph Dominatrix", new Dimension(800, 600), this.isDebugging(), false, 
			myActionPerformed, myDrawGUI));
	}
	
	public final RichTextPane getOutput()
	{
		return this.output;
	}
	
	public final ApplicationWindow getWindow()
	{
		return this.window;
	}
	
	public final boolean isDebugging()
	{
		return this.isDebugging;
	}
	
	protected final void setDebugging(final boolean isDebugging)
	{
		this.isDebugging = isDebugging;
	}
	
	protected final void setOutput(final RichTextPane output)
	{
		this.output = output;
	}
	
	protected final void setWindow(final ApplicationWindow window)
	{
		this.window = window;
	}
}