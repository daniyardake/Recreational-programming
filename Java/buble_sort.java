public class buble{

    public static void main(String[] args) {
      int a[] = new int []{10,1,5,4,2,4,6,7,-1};

      int temp;
      for(int i = 0; i< a.length; i++){
        for(int j = 0; j < i; j++){
          if(a[i]>a[j]){
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
          }
        }
      }
        for(int i = 0; i<a.length; i++){
          System.out.println(a[i]);
        }
    }

}
