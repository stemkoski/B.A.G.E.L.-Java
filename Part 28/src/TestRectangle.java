public class TestRectangle
{
    public static void main(String[] args)
    {
        Rectangle R1 = new Rectangle(0,0, 9,7);
        Rectangle R2 = new Rectangle(7,4, 11,6);
        Rectangle R3 = new Rectangle(15,2, 4,6);

        // true, true, false
        System.out.println( R1.overlaps(R2) );
        System.out.println( R2.overlaps(R3) );
        System.out.println( R3.overlaps(R1) );
    }
}
