/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
    /** Method to test zeroBlue */
    public static void testZeroBlue()
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

    /** Method to test mirrorVertical */
    public static void testMirrorVertical()
    {
        Picture caterpillar = new Picture("caterpillar.jpg");
        caterpillar.explore();
        caterpillar.mirrorVertical();
        caterpillar.explore();
    }

    public static void testMirrorDiagonal()
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.mirrorDiagonal();
        beach.explore();
    }
    
    public static void testPosterize()
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.posterizeCustomBinSize(0, 0, 240, 240, 4);
        beach.explore();
    }

    /** Method to test mirrorTemple */
    public static void testMirrorTemple()
    {
        Picture temple = new Picture("temple.jpg");
        temple.explore();
        temple.mirrorTemple();
        temple.explore();
    }

    /** Method to test the collage method */
    public static void testCollage()
    {
        Picture canvas = new Picture("640x480.jpg");
        canvas.createCollage();
        canvas.explore();
    }

    /** Method to test edgeDetection */
    public static void testEdgeDetection()
    {
        Picture swan = new Picture("swan.jpg");
        swan.edgeDetection(10);
        swan.explore();
    }
    /*

    public static void testMirrorArms()
    {
        Picture snowman = new Picture("snowman.jpg");
        snowman.mirrorArms();
        snowman.explore();
    }

    public static void testKeepBlue()
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.keepOnlyBlue();
        beach.explore();
    }
    
    public static void testNegate()
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.negate();
        beach.explore();
    }
    
    public static void testGrayscale()
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.grayscale();
        beach.explore();
    }
    
    public static void testSepia()
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.sepia();
        beach.explore();
    }
    
    public static void testFixUnderwater()
    {
        Picture underwater = new Picture("water.jpg");
        underwater.explore();
        underwater.fixUnderwater();
        underwater.explore();
    }
    
    public static void testCropAndCopy()
    {
        Picture beach = new Picture("beach.jpg");
        Picture canvas = new Picture(700, 500);
        beach.explore();
        canvas.cropAndCopy(beach, 340, 450, 60, 240, 15, 80);
        canvas.explore();
    }

    /** Main method for testing.  Every class can have a main
     * method in Java */

    public static void main(String[] args)
    {
        // uncomment a call here to run a test
        // and comment out the ones you don't want
        // to run
        testZeroBlue();
        testMirrorDiagonal();
        //testKeepOnlyBlue();
        //testKeepOnlyRed();
        //testKeepOnlyGreen();
        //testNegate();
        //testGrayscale();
        //testFixUnderwater();
        //testMirrorVertical();
        //testMirrorTemple();
        //testMirrorArms();
        //testMirrorGull();
        //testMirrorDiagonal();
        //testCollage();
        //testCopy();
        //testEdgeDetection();
        //testEdgeDetection2();
        //testChromakey();
        //testEncodeAndDecode();
        //testGetCountRedOverValue(250);
        //testSetRedToHalfValueInTopHalf();
        //testClearBlueOverValue(200);
        //testGetAverageForColumn(0);
    }
    
}