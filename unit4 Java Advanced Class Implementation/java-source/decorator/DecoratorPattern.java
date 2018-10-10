
public class DecoratorPattern

{

       private Component door = new Door();//����һ������(����ת��)

       

       private Component lock = new Lock(door);//���һ����(����ת��)

       private Component knob = new Knob(lock);//�����һ������(����ת��)

       

       public void showDecorator()

       {
              knob.sampleOperation();

       }//end showDecorator()

       

       public static void main(String[] args)

       {

              System.out.println("Decorator Pattern!\n");         

              DecoratorPattern dp = new DecoratorPattern();

              dp.showDecorator();

       }//end main(...)

   

}//end class DecoratorPattern

