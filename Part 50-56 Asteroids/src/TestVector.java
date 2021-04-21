public class TestVector
{
    public static void main(String[] args)
    {
        System.out.println("Testing the Vector class...");

        Vector v = new Vector(2, 3);
        System.out.println(v);     //  < 2.0 , 3.0 >

        v.multiply(5);
        v.addValues(800, 900);
        System.out.println(v);     // < 810.0 , 915.0 >

        Vector w = new Vector(1, 1);
        System.out.println( w.getLength() );   // 1.414
        System.out.println( w.getAngle() );    // 45 (deg)

        v.setLength( 1.414 );
        v.setAngle( 45 );
        System.out.println( v );  //  approx. < 1.0 , 1.0 >
    }
}
