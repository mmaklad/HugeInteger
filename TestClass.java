/**
 *
 * @author moham
 */
public class TestClass {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Test 1
        HugeInteger a, b;
        
        a = new HugeInteger("555");
        b = new HugeInteger("111");
        
        a.add(b).toString();//666
        System.out.println(" ..1");
        
        b.add(a).toString();//666
        System.out.println(" ..2");
        
        a.subtract(b).toString();//444
        System.out.println(" ..3");
        
        b.subtract(a).toString();//-444
        System.out.println(" ..4");
        
        a.toString();//555
        b.toString();//111
        System.out.println(" ..5");
        
        a.multiply(b).toString();//61605
        b.multiply(a).toString();//61605
        System.out.println(" ..6");
        
        a.divide(b).toString();//5
        b.divide(a).toString();//0
        System.out.println(" ..7");
        
        System.out.println(a.compareTo(b));//1
        System.out.println(b.compareTo(a));//-1
        System.out.println(" ..8");
        
        
        //Test 2
        HugeInteger c, d;
        
        c = new HugeInteger("-555");
        d = new HugeInteger("111");
        
        c.add(d).toString();//-444
        System.out.println(" ..9");
        
        d.add(c).toString();//-444
        System.out.println(" ..10");
        
        c.subtract(d).toString();//-666
        System.out.println(" ..11");
        
        d.subtract(c).toString();//666
        System.out.println(" ..12");
        
        c.toString();
        d.toString();
        System.out.println(" ..13");
        
        c.multiply(d).toString();
        d.multiply(c).toString();
        System.out.println(" ..14");
        
        c.divide(d).toString();
        d.divide(c).toString();
        System.out.println(" ..15");
        
        System.out.println(c.compareTo(d));
        System.out.println(d.compareTo(c));
        System.out.println(" ..16");
        
        
        //Test 3
        HugeInteger e, f;
        
        e = new HugeInteger("555");
        f = new HugeInteger("-111");
        
        e.add(f).toString();//444
        System.out.println(" ..17");
        
        f.add(e).toString();//444
        System.out.println(" ..18");
        
        e.subtract(f).toString();//666
        System.out.println(" ..19");
        
        f.subtract(e).toString();//-666
        System.out.println(" ..20");
        
        e.toString();
        f.toString();
        System.out.println(" ..21");
        
        e.multiply(f).toString();
        f.multiply(e).toString();
        System.out.println(" ..22");
        
        e.divide(f).toString();
        f.divide(e).toString();
        System.out.println(" ..23");
        
        System.out.println(e.compareTo(f));
        System.out.println(f.compareTo(e));
        System.out.println(" ..24");
        
        //Test 4
        HugeInteger g, h;
        
        g = new HugeInteger("-555");
        h = new HugeInteger("-111");
        
        g.add(h).toString();//-666
        System.out.println(" ..25");
        
        h.add(g).toString();//-666
        System.out.println(" ..26");
        
        g.subtract(h).toString();//-444
        System.out.println(" ..27");
        
        h.subtract(g).toString();//444
        System.out.println(" ..28");
        
        g.toString();
        h.toString();
        System.out.println(" ..29");
        
        g.multiply(h).toString();
        h.multiply(g).toString();
        System.out.println(" ..30");
        
        g.divide(h).toString();
        h.divide(g).toString();
        System.out.println(" ..31");
        
        System.out.println(g.compareTo(h));
        System.out.println(h.compareTo(g));
        System.out.println(" ..32");
        
        //Test 5
        HugeInteger i, j;
        
        i = new HugeInteger("12345");
        j = new HugeInteger("567");
        
        i.add(j).toString();//12912
        System.out.println(" ..33");
        
        j.add(i).toString();//12912
        System.out.println(" ..34");
        
        i.subtract(j).toString();//11778
        System.out.println(" ..35");
        
        j.subtract(i).toString();//-11778
        System.out.println(" ..36");
        
        //Test 6
        HugeInteger k, l;
        
        k = new HugeInteger(5);
        l = new HugeInteger(20);
        
        k.toString();
        l.toString();
        
        k.add(l).toString();
        System.out.println(" ..37");
        
        l.add(k).toString();
        System.out.println(" ..38");
        
        k.subtract(l).toString();
        System.out.println(" ..39");
        
        l.subtract(k).toString();
        System.out.println(" ..40");
        
        //Other Tests: Testing exceptions
        HugeInteger x, y;
        
        x = new HugeInteger("mfc");
        y = new HugeInteger("123");
        
        x.add(y).toString();
        System.out.println(" ..41");
        
        y.add(x).toString();
        System.out.println(" ..42");
        
        x.subtract(y).toString();
        System.out.println(" ..43");
        
        y.subtract(x).toString();
        System.out.println(" ..44");
        
        x.divide(y).toString();
    }
}
