import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

public class GameCanvas extends JPanel {

    BufferedImage background;
    BufferedImage backBuffered;
    Graphics graphics;
    Player player;
    Vector<Enemy> squareEnemy;
    Vector<BulletPlayer> bulletPlayers;
    BulletPlayer bulletPlayer;
    Enemy enemy;

    int count = 0;

    public GameCanvas() {
        this.setSize(40, 40);
        this.setVisible(true);
        this.setupPlayer();
        this.setupBackground();
        this.setupBackBuffered();
        this.setupEnemy();
    }

    private void setupEnemy() {
        this.squareEnemy = new Vector<>();
    }


    private void setupBackground() {
        try {
            this.background = ImageIO.read(new File("resources/background/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupPlayer() {
        this.player = new Player(200,300 ,"resources/player/straight.png");
        this.bulletPlayers = new Vector<>();
    }

    private void setupBackBuffered() {
        this.backBuffered = new BufferedImage(400, 600, BufferedImage.TYPE_4BYTE_ABGR);
        this.graphics = this.backBuffered.getGraphics();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void renderAll() {
        this.graphics.drawImage(this.background, 0, 0, null);
        //draw bullet of Player
        for (BulletPlayer bullet: bulletPlayers) {
            bullet.render(this.graphics);
        }
        //Draw player
        player.render(this.graphics);
        //draw Enemy
        for (Enemy enemy: squareEnemy) {
            enemy.render(this.graphics);
        }
        this.repaint();
    }

    public void runAll() {

        if (count == 10) {
            //create random position for enemy
            Random rand = new Random();
            int randomNumber = rand.nextInt(380);
            //add bullets for player
            this.bulletPlayer = new BulletPlayer(player.x - 15, player.y - 50, 3, "resources/player/player_bullet.png");
            bulletPlayers.add(bulletPlayer);
            //add more enemy in random place
            this.enemy = new Enemy(randomNumber,0,"resources/square/enemy_square_small.png", 3);
            squareEnemy.add(enemy);
            count = 0;
        } else {
            this.count += 1;
        }
        //player is shooting
        for (BulletPlayer bullet: this.bulletPlayers) {
            bullet.run();
        }
        //Enemy is running
        for (Enemy enemy : squareEnemy) {
            enemy.run();
        }

    }
}