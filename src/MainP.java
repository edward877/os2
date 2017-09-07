

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class MainP {

	
	private JFrame frame;
	private MMU mmu = new MMU();
	final static int quantum = 20;
	Virtual virtual = new Virtual();
	Swap swap = new Swap();
	Random r;
	private DefaultTableModel tableModel = new DefaultTableModel();
	JEditorPane editorPaneRight;
	JButton btnPause;
	JButton btnStart;
	JButton btnCreate;
	
	public MainP() {
		initialize();
		frame.setVisible(true);
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(507, 46, 438, 471);
		frame.getContentPane().add(scrollPane_2);
		table = new JTable();
		table.setEnabled(false);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setRowHeight(40);
		table.setModel(tableModel);
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setBackground(Color.WHITE);
		table.setBounds(474, 22, 242, 140);
		tableModel.setRowCount(0);
		tableModel.setColumnCount(0);
		for(int i=0; i < MMU.PhysMemb; i++){
			tableModel.addColumn((i+1) +"");
		}
		scrollPane_2.setViewportView(table);
		
		JLabel label = new JLabel("\u043F\u0440\u043E\u0446\u0435\u0441\u0441\u044B \u0438 \u043F\u043E\u0442\u043E\u043A\u0438");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(45, 11, 168, 23);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u0445\u043E\u0434 \u0432\u044B\u043F\u043E\u043B\u043D\u0435\u043D\u0438\u044F");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(270, 11, 150, 23);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u0424\u0438\u0437\u0438\u0447\u0435\u0441\u043A\u0430\u044F \u043F\u0430\u043C\u044F\u0442\u044C");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(645, 14, 207, 23);
		frame.getContentPane().add(label_2);
		
	    btnPause = new JButton("stop");
	    btnPause.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		btnPause.setEnabled(false);
	    		btnStart.setEnabled(true);
	    		timerAction.stop();
	    	}
	    });
		btnPause.setEnabled(false);
		btnPause.setBounds(584, 528, 96, 23);
		frame.getContentPane().add(btnPause);
		
		btnStart = new JButton("start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPause.setEnabled(true);
	    		btnStart.setEnabled(false);
	    		timerAction.start();
			}
		});
		btnStart.setEnabled(false);
		btnStart.setBounds(679, 528, 96, 23);
		frame.getContentPane().add(btnStart);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1063, 601);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 45, 202, 472);
		frame.getContentPane().add(scrollPane_1);

		JTextArea textAreaLeft = new JTextArea();
		scrollPane_1.setViewportView(textAreaLeft);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(233, 45, 251, 472);
		frame.getContentPane().add(scrollPane);
		
	    editorPaneRight = new JEditorPane("text/html", "");
		scrollPane.setViewportView(editorPaneRight);

		btnCreate = new JButton("\u043D\u0430\u0447\u0430\u0442\u044C");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editorText = "";
				editorPaneRight.setText(editorText);
				btnCreate.setEnabled(false);
				btnPause.setEnabled(true);
				table_process.clear();
				tableModel.setRowCount(0);
				createProcesses();
				textAreaLeft.setText( ShowProcess() );
				online_process = table_process.size();
				timerAction.start();
			}
		});
		btnCreate.setBounds(404, 528, 89, 23);
		frame.getContentPane().add(btnCreate);
	}
		
	String editorText;
	ActionListener listener = new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	ShowActing();
	        editorPaneRight.setText(editorText);
	      }
	 };

	ArrayList<Process> table_process = new ArrayList<>();
	private JTable table;;
	Timer timerAction = new Timer(500, listener);
	
	private void createProcesses() {
		r = new Random();
		int n = r.nextInt(2) + 3;
		for(int i = 0; i < n ; i++) {
			table_process.add(new Process(r,quantum, virtual));
		}

	}

	private String ShowProcess() {
		String text = "";
		for(int i = 0; i < table_process.size(); i++) {
			Process p = table_process.get(i);
			text += "процесс " + (i+1);
			text += ".  time = " + p.getTime_process() +  "  ";
			text += "priotity = " + p.getPriority() +  "\n";
			for(int j = 0; j < p.getTableStream().size(); j++) {
				Stream s = p.getTableStream().get(j);
				text += "\tПоток " + (j+1) + ".  time = " +s.getTime_stream() +  "\n";
			}
			text += "\n";
		}
		return text;
	}
	
	int online_process;
	private void ShowActing() {
		String text = "";
		if(online_process <= 0) {
			timerAction.stop();
			btnCreate.setEnabled(true);
			btnPause.setEnabled(false);
			return;
		}
		int maxP = 0;
		//первый процесс с оставшимся временем
		for(int l = 0; l < table_process.size(); l++) {
			if(table_process.get(l).getTime_process()!=0){
				maxP = l;
				break;
			}
		}
		//ищем более приоритетный процесс
		for(int l = 0; l < table_process.size(); l++) {
			if(table_process.get(l).getTime_process()!=0){
				if(table_process.get(l).getPriority() > table_process.get(maxP).getPriority()){
					maxP = l;
				}
			}
		}
		Process p = table_process.get(maxP);
		if(p.getTime_process()!=0) {
			p.setQuantum(quantum);
			text += "процесс " + (maxP+1) +  ".  ";
			text += "priotity = " + p.getPriority() +  "<br>";
			for(int i =0; i < r.nextInt(3)+1; i++){
				text +=mmu(p);			
			}
			for(int j = 0; j < p.getTableStream().size(); j++) {
				Stream s = p.getTableStream().get(j);
				if(s.getTime_stream()!=0){
					p.setTime_stream(j);
					text += "   Поток " + (j+1) + ". ";
					int time_stream = s.getTime_stream();
					if(time_stream==0){
						text +=   "закончился<br>";
					}else{
						text += "time = " + time_stream + "<br>";
					}
				}
			}
			p.setTime_process();

			if(p.getTime_process()==0){
				text += "ПРОЦЕСС закончился!<br>";
				online_process--;
			}
			text += "_______________________________<br>";
		}
		p.PriorityMinus();
		editorText+= text;
	}

	private String mmu(Process process) {
		String text = "";
		//получаем страницу процесса с случайным индексом
		ArrayList<Integer> array = process.getListPage();
		int randIndex = r.nextInt(array.size());
		Page page = virtual.getPageTables().get(array.get(randIndex));
		
		text += "<b>Страница "+ page.getId() + " </b><br>";
		// Если страницы еще нет в физ памяти, то 
		if(!mmu.getPageBlocks().contains(page)){
			int indexEmptyBlock = mmu.emptyBlock();
			// Если в физ памяти есть место, то добавляем туда страницу
			if (indexEmptyBlock >=0) {
				mmu.addPage(indexEmptyBlock, page);
			}else{ 
				//если  страница в свапе, то выгружаем оттуда
				if(swap.getSwap().contains(page)){
					swap.RemoveFromSwap(page);
				}
				//добавление в физ память на место старой выбранной страницы
				//и добавление старой страницы в свап
				swap.addPageSwap(mmu.ReplacePage(page));
			}
			//таблица физ памяти
			ArrayList<String> pageBlocks = new ArrayList<>();
			for (Page pageblock : mmu.getPageBlocks()) {
				if(pageblock!=null)
					pageBlocks.add(pageblock.getId() + " ");
			}
			tableModel.addRow(pageBlocks.toArray());
		}
		//бит присутствия делаем единицей
		page.setR(true);
		return text;
	}
}
