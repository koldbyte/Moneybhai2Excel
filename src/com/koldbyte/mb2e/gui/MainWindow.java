package com.koldbyte.mb2e.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.koldbyte.mb2e.extractors.OutputManager;
import com.koldbyte.mb2e.extractors.Processor;

public class MainWindow {

	private JFrame frmMbe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmMbe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMbe = new JFrame();
		frmMbe.setTitle("MB2E");
		frmMbe.setBounds(100, 100, 613, 462);
		frmMbe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMbe.getContentPane().setLayout(null);
		
		
		final JTextArea txtLog = new JTextArea();
		txtLog.setBounds(12, 33, 581, 378);
		frmMbe.getContentPane().add(txtLog);
		
		//Initialize the JtextArea to output the Stream from System.err
		System.setErr(new PrintStreamCapturer(txtLog, System.err));
		
		JButton btnJustDoIt = new JButton("Just Do It!");
		btnJustDoIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				justDoiIt();
			}

			private void justDoiIt() {
				/*
				//The Main functionality after getting username and password starts from here
				String username = txtUserName.getText();
				char[] passchar = txtPassword.getPassword();
				String password = new String(passchar);
				try {
					Site http = Site.run(username, password);
					System.err.println(http.GetPageContent("http://moneybhai.moneycontrol.com/portfoliomanager"));
				} catch (Exception e) {
					System.err.println("Cannot instantiate http!");
					e.printStackTrace();
				}
				*/
				
				String content = txtLog.getText();
				
				String output = new OutputManager().createOutput(new Processor().process(content));

				//txtLog.append(output);
				txtLog.setText(output);
			}
		});
		btnJustDoIt.setBounds(241, 3, 117, 25);
		frmMbe.getContentPane().add(btnJustDoIt);

	}
}
