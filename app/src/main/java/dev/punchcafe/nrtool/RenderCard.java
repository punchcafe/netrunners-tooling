package dev.punchcafe.nrtool;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RenderCard {
    public static BufferedImage renderCards(final Card card) throws IOException {
        // TODO: build single card renderer method, have this method invoke one at a time to fill up
        // the deck. Additionally, add a file selection
        final var bufferedImage = new BufferedImage(800,520,1);
        final var cardImage = ImageIO.read(new File(CardResources.SYSTEM_CARD_IMAGE));
        final var graphics = cardImage.getGraphics();
        graphics.drawString("Hi There", 60, 60);

        bufferedImage.getGraphics().drawImage(cardImage, 0, 0, null);
        bufferedImage.getGraphics().drawImage(cardImage, 400, 0, null);

        JLabel picLabel = new JLabel(new ImageIcon(bufferedImage));
        JPanel jPanel = new JPanel();
        jPanel.add(picLabel);
        JFrame f = new JFrame();
        f.setSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));
        f.add(jPanel);
        f.setVisible(true);
        return null;
    };

    public static void main(String[] args) throws IOException {
        renderCards(null);
    }

}
