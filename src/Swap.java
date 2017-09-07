

import java.util.ArrayList;

public class Swap {
	private ArrayList<Page> swap = new ArrayList<>();
	
	public ArrayList<Page> getSwap() {
		return swap;
	}
	
	public void addPageSwap(Page page) {
		swap.add(page);
	}
	
	public void RemoveFromSwap(Page page){
		swap.remove(page);
	}
}
