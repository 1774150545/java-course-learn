
public class Lock extends Decorator

{

       public Lock(Component component)

       {

              super(component);

       }//end ConcreteDecorator1(...)

 

       public void sampleOperation()

       {

              super.sampleOperation ();

              System.out.println("Add a lock(��)");

       }//end sampleOperation()

 

}//end class Lock

