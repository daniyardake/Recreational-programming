public class HistoList<E> extends LList<E> {
	HistoLink<E> hhead;
	HistoLink<E> htail;

	public HistoList(){
		super();
		hhead = new HistoLink<E>();
		htail = hhead;
	}
	public void insert(E newValue)
	{
		HistoLink<E> newLink = new HistoLink<E>(newValue, null);
		if (hhead.getNext() == null){
			hhead.setNext(newLink);
			super.size += 1;
			return ;

		}
		HistoLink<E> current = hhead.getNext();
		HistoLink<E> before = hhead;


		while(current != null && current.getData() != newValue){

			before = current;
			current = current.getNext();
		}
		if (current != null){
			current.setCount(current.getCount()+1);
		} else {
			super.size += 1;
			before.setNext(newLink);
		}

	}

	public E remove(E toRemove){
		if (hhead.getNext() == null){
			return null;
		}
		HistoLink<E> current = hhead.getNext();
		HistoLink<E> before = hhead;

		while(current != null && current.getData() != toRemove){
			before = current;
			current = current.getNext();
		}

		if (current != null){

			if (current.getCount() != 1){
				current.setCount(current.getCount()-1);
			} else {
				before.setNext(current.getNext());
				super.size -= 1;
			}
		} else {
			return null;
		}

		return toRemove;

	}
	public String toString(){
		HistoLink<E> current = new HistoLink<E>();
		String chain = "";
		current = hhead;

		for(int i = 0; i < size + 1; i++) {
			if (current != hhead){
				chain = chain + "\n" + current.toString()+ ": " + current.getCount();
			}
			current = current.getNext();
		}
		return chain;
	}
	//other methods not shown
}
