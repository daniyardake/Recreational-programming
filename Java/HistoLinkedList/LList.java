public class LList<E>
{
  //attributes
  //head must always refer (point) to the first link in the list.
  protected Link<E> head;
  private Link<E> after;
  protected Link<E> tail;
  protected int size;
  //other attributes coming soon...

  //constructor
  public LList() {
	  Link<E> hhead = new Link();
	  head = hhead;
	  tail = head;
	  size = 0;
  }
  //add a new Link that contains newData to the end of this list
  public void append(E newData)
  {
	  Link<E> after = new Link(newData);
	  Link<E> current = new Link();
	  current = tail;
	  current.setNext(after);
	  tail = current.getNext();
	  size++;
  }

  public void prepend(E newData)
  {
	  Link<E> brain = new Link(newData);
	  brain.setNext(head.getNext());
	  head.setNext(brain);
	  size++;
	 
  }
  
  public void insert(int index, E value)
  {
	  Link<E> behind = new Link();
	  Link<E> current = new Link();
	  Link<E> after = new Link(value);
	  current = head;
	  if(size > index) {
		  for(int i = 0; i < index; i++) {
			  current = current.getNext();
		  }
		  behind = current.getNext();
		  current.setNext(after);
		  current.getNext().setNext(behind);
	  }
	  size++;
	  
  }
  
  public E remove(int index)
  {
	  Link<E> current = new Link();
	  Link<E> after = new Link();
	  current = head;
	  if(size < index) {
		  return null;
	  }
	  else {
		  for(int i = 0; i < index; i++) {
			  current = current.getNext();
		  }
		  E ddata = current.getNext().getData();
		  after = current.getNext().getNext();
		  current.setNext(after);
		  size--;
		  return ddata;  
	  }
  }

  public E get(int index) {
	  Link<E> current = new Link();
	  current = head;
	  if(size < index) {
		  return null;
	  }
	  else {
		  for(int i = 0; i < index; i++) {
			  current = current.getNext();
		  }
		  E ddata = current.getNext().getData();
		  return ddata;
	  }
  }
  
  public E set(int index, E value) {
	  Link<E> current = new Link();
	  current = head;
	  if(size < index) {
		  return null;
	  }
	  else {
		  for(int i = 0; i < index; i++) {
			  current = current.getNext();
		  }
		  E ddata = current.getNext().getData();
		  current.getNext().setData(value);
		  return ddata;
	  }
  }
  
  public int size() {
	  return size;
  }
  
  public String toString()
  {
    Link<E> current = new Link();
    String chain = "";
	current = head;

    for(int i = 0; i < size + 1; i++) {
    	chain = chain + "\n" + current.toString();
    	current = current.getNext();
    }
    return chain;
  }

  //other methods coming soon...
}