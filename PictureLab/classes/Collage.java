import java.awt.Color;
public class Collage
{
    public static void main (String[] args)
    {
        Picture canvas = new Picture(850, 1680);
        Pixel[][] pixels = canvas.getPixels2D();
        Picture tux = new Picture("tux.png");
        canvas.copy(tux, 0, 0); // 265X314 width x height
        canvas.mirrorVerticalOverPoint(266); //reflects over edge of tux's picture       
        Picture macBookPro = new Picture ("macbook.jpg"); // 550 x 550
        macBookPro.setTransparency(255);
        Picture windowsLogo = new Picture("windows.jpg");
        canvas.copy(macBookPro, 0, 266+265); // copy in the macbook to the right of tux and his reflection
        canvas.negate(0, 266, 314, 266+265); //negate tux's reflection        
        canvas.mirrorHorizontalOverPoint(pixels.length/2);
        canvas.mirrorVerticalOverPoint(pixels[0].length/2-1);
        canvas.copyAndMixColors(windowsLogo, pixels.length/2-113, pixels[0].length/2-113);
        canvas.sepia(0, 1410, 304, 1679); //sepia the top-right penguin
        canvas.keepOnlyBlue(536, 1413, 850, 1674); // keepOnlyBlue the bottom-right penguin
        canvas.posterizeCustomBinSize(537, 0, 850, 264, 3); // posterize bottom-left penguin with 3 bins
        canvas.explore();       
        canvas.write("MyCollage.jpg");
    }
}
