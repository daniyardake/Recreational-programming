public class Main {
    public static void main(String[] args) {
        HistoList<String> h = new HistoList<String>();
	    h.insert("123");
        h.insert("123");
        h.insert("456");
        h.insert("456");
        h.insert("123");
        h.insert("456");
        h.insert("123");
        h.insert("456");

        h.remove("123");
        h.remove("123");
        h.remove("456");
        h.remove("456");
        h.remove("456");
        h.remove("456");
        System.out.println(h);

    }
}
