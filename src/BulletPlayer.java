
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BulletPlayer {
    public int x;
    public int y;
    public BufferedImage image;
    public int speed;

    public BulletPlayer(int x, int y, int speed, String url) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        try {
            this.image = ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.y -= speed;
    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, this.x, this.y, null);
    }
}