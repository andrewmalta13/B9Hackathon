import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;


public class ImageStats {

	public static double getStats(String url)
	{
		URL imloc;
		FastRGB image = null;
		try {
			imloc = new URL(url);
			try {
				image = new FastRGB(ImageIO.read(imloc));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		int[] xlocs={90,150,210,270,330};
		int yloc=248;

		int total=0;
		int num=0;
		for(int i=1; i<=5;i++) {
			int	bg=0;
			int barheight=-1;
			while (bg <= 10) {
				barheight+=1;
				int[] rgb=image.getRGB(xlocs[i-1], yloc-barheight);
				bg=rgb[2]+rgb[3];
			}
			total+=barheight*i;
			num+=barheight;
		}
		return (total/(float)num);
	}
}
