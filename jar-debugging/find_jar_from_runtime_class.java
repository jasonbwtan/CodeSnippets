public class find_jar_from_runtime_class.java{
   public static void main(String[] args){
        Object o = new String();    
	Class klass = String.class; //o.getClass();
	URL location = klass.getResource('/'+klass.getName().replace('.', '/')+".class");
	System.out.println(location);
   }
}
