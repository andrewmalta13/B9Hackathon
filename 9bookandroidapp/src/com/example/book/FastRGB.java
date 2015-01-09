import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class FastRGB
{

    private int width;
    private int height;
    private boolean hasAlphaChannel;
    private int pixelLength;
    private byte[] pixels;

    FastRGB(BufferedImage image)
    {

        pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        width = image.getWidth();
        height = image.getHeight();
        hasAlphaChannel = image.getAlphaRaster() != null;
        pixelLength = 3;
        if (hasAlphaChannel)
        {
            pixelLength = 4;
        }

    }

    int[] getRGB(int x, int y)
    {
        int pos = (y * pixelLength * width) + (x * pixelLength);

        int[] argb = {0,0,0,0}; // 255 alpha
        if (hasAlphaChannel)
        {
            argb[0] = ((int) pixels[pos++]); // alpha
        }

        argb[3] = ((int) pixels[pos++] & 0xff); // blue
        argb[2] = ((int) pixels[pos++] & 0xff); // green
        argb[1] = ((int) pixels[pos++] & 0xff); // red
        return argb;
    }
}