

import java.util.ArrayList;

public class MMU {
	public static final int PhysMemb = 4;
	private ArrayList<Page> pageBlocks = new ArrayList<>(PhysMemb);
	private int query = 0;


	public MMU() {
		for(int i=0; i < PhysMemb; i++) {
			pageBlocks.add(i, null);
		}
	}

	public ArrayList<Page> getPageBlocks() {
		return pageBlocks;
	}
	public void setPageBlocks(ArrayList<Page> pageBlocks) {
		this.pageBlocks = pageBlocks;
	}
	
	public int emptyBlock() {
		for (int i = 0; i < PhysMemb; i++) {
			if (pageBlocks.get(i) == null) {
				return i;
			}
		}
		return -1;
	}

	public void addPage(int idPage, Page page) {
		pageBlocks.set(idPage, page);
	}
	public Page ReplacePage(Page pageIn) {
		Page pageOut = AlgoritmClock();
		pageBlocks.set(query, pageIn);
		return pageOut;
	}
	
	private Page AlgoritmFifo(){
		query++;
		if (query == PhysMemb) {
			query = 0;
		}
		Page pageOut = pageBlocks.get(query);
		pageOut.setR(false);
		return pageOut;
	}
	
	private Page AlgoritmSecondChange(){
		while(true){
			Page pageZero = pageBlocks.get(0);
			if(pageZero.isR()){
				pageZero.setR(false);
				for(Page p : pageBlocks){
					System.out.print(p.getId() + " ");
				}
				System.out.println();
				pageBlocks.add(pageZero);
				pageBlocks.remove(0);
				
			}else{
				return pageZero;
			}
		}
	}
	
	private Page AlgoritmClock(){
		while(true){
			Page pageOut = pageBlocks.get(query);
			
			if(!pageOut.isR()){
				return pageOut;
			}
			query++;
			if (query == PhysMemb) {
				query = 0;
			}
			pageOut.setR(false);		
		}
	}
}
