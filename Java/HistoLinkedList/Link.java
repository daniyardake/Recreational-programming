public class Link<E> {
	private E data;
	private Link<E> next;
	
	public Link(){
		data = null;
		next = null;
	}
	public Link(E d) {
		data = d;
		next = null;
	}
	
	public Link(E d, Link<E> n) {
		data = d;
		next = n;
	}
	
	public void setData (E d) {
		data = d;
	}
	
	public E getData() {
		return data;
	}
	
	public void setNext(Link<E> n) {
		next = n;
	}
	
	public Link<E> getNext(){
		return next;
	}

	public String toString() {
		return ("" + data);
	}
	
	public void remove(Link<E> n, String s) {
		 Link<E> before = new Link<E>(null);
		while(n.getData() != s) {
			before = n; 
			n = n.getNext();
		}
		before.setNext(n.getNext());
	}
	
}
