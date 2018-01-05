import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {
    public int x;
    public int y;
    public BufferedImage image;

    public Player(int x, int y, String url) {
        this.x = x;
        this.y = y;
        try {
            this.image = ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, this.x - 25, this.y - 50, null);
    }

}