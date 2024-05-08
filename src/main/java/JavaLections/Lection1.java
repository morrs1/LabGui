package JavaLections;

public class Lection1 {
    class Base {
        private int b1;
        public int b2;
        private void f1() {}
        void f2(){} //package-private
        protected void f3(){}
        public void f4(){}

    }

    class Derived extends Base {

        public void f1(){

       }


    }

    public static void main(String[] args) {
    }

}
