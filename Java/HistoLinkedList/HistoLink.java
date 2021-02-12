public class HistoLink<E> extends Link<E> {
	private int count;
	private HistoLink<E> hnext;

	public HistoLink(E value, HistoLink<E> nextLink){
		super(value, nextLink);
		hnext = nextLink;
		count = 1;
	}
	
	public HistoLink(E value) {
		super (value);
		hnext = null;
		count = 1;
	}

	public HistoLink() {
		super ();
		hnext = null;
		count = 1;
	}

	public int getCount(){
		return count;
	}
	public HistoLink<E> getNext(){ return hnext; }
	public void setCount(int newCount){
		count = newCount;
	}
	public void setNext(HistoLink<E> next){ hnext = next; }

}

