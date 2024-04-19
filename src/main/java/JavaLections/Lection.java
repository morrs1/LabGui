package JavaLections;

public class Lection {

    public class List {
        private static class Node {
            public int value;
            public Node next;

            public Node(int value) {
                this.value = value;
                this.next = null;
            }
        }

        private Node first;

        public List() {
            first = null;
        }

        void test() {
            Node n = new Node(1);

        }
    }

    class Outher {
        private int a;

        public void test() {
             int x = 5, y = 5;
            final int z = 0;
            class Inner {
                public void test() {
                    a = 100;
//                    x = 1;
//                    y = 1;//доступ есть, но менять их мы не можем
                    System.out.println(x + " "+ y);
                }
            }
        }
    }

}
