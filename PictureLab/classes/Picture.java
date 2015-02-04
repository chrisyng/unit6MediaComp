import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    /** Method to set the blue to 0 */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }

    public void keepOnlyBlue(int startRow, int startCol, int endRow, int endCol)
    {
        Pixel[][] pixels = this.getPixels2D();
        for (int row = startRow; row<endRow; row++)
        {
            for (int col = startCol; col < endCol; col++)
            {
                pixels[row][col].setGreen(0);
                pixels[row][col].setRed(0);
            }
        }
    }

    public void negate(int startRow, int startCol, int endRow, int endCol)
    {
        Pixel[][] pixels = this.getPixels2D();
        for (int row = startRow; row<endRow; row++)
        {
            for (int col = startCol; col < endCol; col++)
            {
                pixels[row][col].setBlue(255-pixels[row][col].getBlue());
                pixels[row][col].setRed(255-pixels[row][col].getRed());
                pixels[row][col].setGreen(255-pixels[row][col].getGreen());
            }
        }

    }

    public void posterizeCustomBinSize(int startRow, int startCol, int endRow, int endCol, int amountOfBins)
    {
        Pixel[][] pixels = this.getPixels2D();
        int binSize = 255/amountOfBins;
        
        for (int binNumber = 1; binNumber <= amountOfBins; binNumber++)
        {
            int lowerBinBound = binSize*(binNumber-1);
            int higherBinBound = binSize*binNumber;
            for (int row = startRow; row<endRow; row++)
            {
                for (int col = startCol; col < endCol; col++)
                {                 

                    if (pixels[row][col].getRed()<higherBinBound && pixels[row][col].getRed()>lowerBinBound)
                    {
                        pixels[row][col].setRed((higherBinBound + lowerBinBound)/2);                    
                    }
                    
                    if (pixels[row][col].getGreen()<higherBinBound && pixels[row][col].getGreen() > lowerBinBound)
                    {
                        pixels[row][col].setGreen((higherBinBound + lowerBinBound)/2);                       
                    }
                    
                    if (pixels[row][col].getBlue()<higherBinBound && pixels[row][col].getBlue() > lowerBinBound)
                    {
                        pixels[row][col].setBlue((higherBinBound + lowerBinBound)/2);                      
                    }

                }
            }
        }
    }

    public void sepia(int startRow, int startCol, int endRow, int endCol)
    {        
        Pixel[][] pixels = this.getPixels2D();
        for (int row = startRow; row<endRow; row++)
        {
            for (int col = startCol; col < endCol; col++)
            {
                int average = (pixels[row][col].getBlue() + pixels[row][col].getRed() + pixels[row][col].getGreen())/3;
                pixels[row][col].setBlue(average);
                pixels[row][col].setRed(average);
                pixels[row][col].setGreen(average);
                if(pixels[row][col].getRed() < 60)
                {
                    pixels[row][col].setBlue(pixels[row][col].getBlue()*9/10);
                    pixels[row][col].setRed(pixels[row][col].getRed()*9/10);
                    pixels[row][col].setGreen(pixels[row][col].getGreen()*9/10);                    
                }
                else if (pixels[row][col].getRed() < 190)
                {
                    pixels[row][col].setBlue(pixels[row][col].getBlue()*8/10);
                }
                else
                {
                    pixels[row][col].setBlue(pixels[row][col].getBlue()*9/10);
                }
            }
        }
    }

    public void fixUnderwater()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {                
                pixelObj.setRed(pixelObj.getRed()*3);
            }
        }
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    public void mirrorHorizontalOverPoint(int mirrorLine)
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;        
        for (int row = 0; row < mirrorLine; row++)
        {
            for (int col = 0; col < pixels[0].length; col++)
            {
                topPixel = pixels[row][col];
                int distanceFromMirror = mirrorLine-row;
                bottomPixel = pixels[mirrorLine + distanceFromMirror-1][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        } 
    }

    public void mirrorVerticalRightToLeft()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                leftPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    public void mirrorDiagonal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel abovePixel = null;
        Pixel belowPixel = null;
        int width = pixels[0].length;
        int height = pixels.length;
        double slope = height/width;
        int count = 0;
        for (int y = 0; y < pixels.length; y++)
        {
            for (int x = 0; x < width; x++)
            {               
                int xLine = count;
                int yLine = (int) (slope * count);
                while (x < xLine && y < yLine)
                {
                    int verticalDistance = yLine-y;
                    int horizontalDistance = xLine-x;
                    abovePixel = pixels[y][x];
                    belowPixel = pixels[yLine+verticalDistance][xLine+horizontalDistance];
                    belowPixel.setColor(abovePixel.getColor());
                }                
                count++;
            }
        }
    }

    /** Mirror just part of a picture of a temple */
    public void mirrorTemple()
    {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++)
            {

                leftPixel = pixels[row][col];      
                rightPixel = pixels[row]                       
                [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    public void mirrorVerticalOverPoint(int mirrorLine)
    {
        int mirrorPoint = mirrorLine;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();
        // loop through the rows
        for (int row = 0; row < pixels.length; row++)
        {
            // loop until befroe mirrorPoint
            for (int col = 0; col < mirrorPoint; col++)
            {

                leftPixel = pixels[row][col];      
                rightPixel = pixels[row]                       
                [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    public void setTransparency(int alphaLevel)
    {
        Pixel[][] pixels = this.getPixels2D();
        for (int row = 0; row < pixels.length; row++)
        {            
            for (int col = 0; col < pixels[0].length; col++)
            {  
                pixels[row][col].setAlpha(alphaLevel);               
            }
        }
    }

    public void mirrorArms()
    {
        int mirrorPoint = 207;
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();
        // loop through the rows
        for (int row = 162; row < mirrorPoint; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 96; col < 298; col++)
            {
                topPixel = pixels[row][col];      
                bottomPixel = pixels[mirrorPoint-row + mirrorPoint][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }

    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic, 
    int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow; 
        fromRow < fromPixels.length &&
        toRow < toPixels.length; 
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol; 
            fromCol < fromPixels[0].length &&
            toCol < toPixels[0].length;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }

    public void setBackground(int red, int green, int blue)
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] asdf : pixels)
        {
            for (Pixel pixelObj : asdf)
            {
                if (pixelObj.getRed()<=10 && pixelObj.getGreen() <= 10 && pixelObj.getBlue() <= 10)
                {
                    pixelObj.setRed(red);
                    pixelObj.setGreen(green);
                    pixelObj.setBlue(blue);
                }
            }
        }
    }

    public void copyAndMixColors(Picture fromPic, int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow; 
        fromRow < fromPixels.length &&
        toRow < toPixels.length; 
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol; 
            fromCol < fromPixels[0].length &&
            toCol < toPixels[0].length;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setRed((fromPixel.getRed()+toPixel.getRed())/2);
                toPixel.setGreen((fromPixel.getGreen()+toPixel.getGreen())/2);
                toPixel.setBlue((fromPixel.getBlue()+toPixel.getBlue())/2);
            }
        }   
    }

    /** Method to create a collage of several pictures */
    public void createCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");
        this.copy(flower1,0,0);
        this.copy(flower2,100,0);
        this.copy(flower1,200,0);
        Picture flowerNoBlue = new Picture(flower2);
        flowerNoBlue.zeroBlue();
        this.copy(flowerNoBlue,300,0);
        this.copy(flower1,400,0);
        this.copy(flower2,500,0);
        this.mirrorVertical();
        this.write("collage.jpg");
    }

    /** Method to show large changes in color 
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; 
            col < pixels[0].length-1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col+1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor) > 
                edgeDist)
                {
                    leftPixel.setColor(Color.BLACK);
                }
                else
                {
                    leftPixel.setColor(Color.WHITE);
                }
            }
        }
    }

    public void cropAndCopy(Picture sourcePicture, int startSourceRow, int endSourceRow, int startSourceCol, int endSourceCol, int startDestRow, int startDestCol)
    {
        Pixel[][] source = sourcePicture.getPixels2D();
        Pixel[][] current = this.getPixels2D();
        int height = endSourceRow-startSourceRow;
        int width = endSourceCol-startSourceCol;
        for (int sourceRow = startSourceRow; sourceRow<endSourceRow; sourceRow++)
        {
            for (int sourceCol = startSourceCol; sourceCol < endSourceCol; sourceCol++)
            {
                for (int destRow = startDestRow; destRow < startDestRow + height; destRow++)
                {
                    for (int destCol = startDestCol; destCol < startDestCol + width; destCol++)
                    {
                        current[destRow][destCol].setColor(source[sourceRow][sourceCol].getColor());
                    }
                }
            }
        }
    }

    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) 
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

} // this } is the end of class Picture, put all new methods before this
