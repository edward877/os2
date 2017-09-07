

import java.util.ArrayList;
import java.util.Random;

public class Process {
	
	private int time_process;
	private ArrayList<Stream> tableStream = new ArrayList<>();
	private ArrayList<Integer> pageList = new ArrayList<>();
	private int  quantum;
	private int priority;
	private int numProcessPage = 8;

	Process(Random r,int quantum, Virtual virtual) {
		for(int i = 0; i < r.nextInt(2) + 2; i++) {
			tableStream.add(new Stream(r));
		}
		priority = (r.nextInt(8)+2) * 10;
		setQuantum(quantum);
		setTime_process();
		
		int n = virtual.getPageTables().size();
		for (int id = n; id < n+numProcessPage; id++) {
			Page page = new Page(id);
			pageList.add(id);
			virtual.getPageTables().add(page);
		}
	}

	public ArrayList<Integer> getListPage() {
		return pageList;
	}

	public ArrayList<Stream> getTableStream() {
		return tableStream;
	}

	public int getTime_process() {
		return time_process;
	}

	public void setTime_process() {
		time_process = 0;
		for(int i = 0; i < tableStream.size(); i++) {
			time_process += tableStream.get(i).getTime_stream();
		}
	}

	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}

	public void setTime_stream(int j){
		quantum = tableStream.get(j).setTime_stream(quantum);
	}

	public void PriorityMinus(){
		if(priority > 10){
			priority-=10;
		}
	}

	public int getPriority(){
		return priority;
	}

}
