package dev.punchcafe.nrtool;

import dev.punchcafe.nrtool.card.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

public class RenderCard {

    private static int CARD_GRID_WIDTH = 10;
    private static int CARD_WIDTH = 400;
    private static int CARD_HEIGHT = 520;

    private static Font BIG_FONT = new Font("Monospaced", 3, 36);
    private static Font MEDIUM_FONT = new Font("Monospaced", 3, 24);
    private static Font SMALL_FONT = new Font("Monospaced", 3, 16);

    public static BufferedImage renderSystemCard(final SystemCard card) throws IOException {
        final var cardImage = ImageIO.read(new File(CardResources.SYSTEM_CARD_IMAGE));
        final var graphics = cardImage.getGraphics();
        graphics.setFont(BIG_FONT);
        graphics.drawString(card.systemType, 220, 190);
        graphics.drawString(card.subnetMask, 220, 245);
        graphics.setFont(SMALL_FONT);
        graphics.drawString(card.additionalEffect, 70, 345);
        return cardImage;
    }

    public static BufferedImage renderLightHackCard(final LightHackCard card) throws IOException {
        final var cardImage = ImageIO.read(new File(CardResources.L_HACK_CARD_IMAGE));
        final var graphics = cardImage.getGraphics();
        graphics.setFont(BIG_FONT);
        graphics.drawString(card.subnetMask, 220, 245);
        graphics.setFont(SMALL_FONT);
        graphics.drawString(card.additionalEffect, 70, 345);
        return cardImage;
    }

    public static BufferedImage renderDeepHackCard(final DeepHackCard card) throws IOException {
        final var cardImage = ImageIO.read(new File(CardResources.D_HACK_CARD_IMAGE));
        final var graphics = cardImage.getGraphics();
        graphics.setFont(BIG_FONT);
        graphics.drawString(card.systemType, 220, 190);
        graphics.setFont(SMALL_FONT);
        graphics.drawString(card.additionalEffect, 70, 345);
        return cardImage;
    }

    public static BufferedImage renderDeepProbeCard(final DeepProbeCard card) throws IOException {
        final var cardImage = ImageIO.read(new File(CardResources.D_PROBE_CARD_IMAGE));
        final var graphics = cardImage.getGraphics();
        graphics.setFont(MEDIUM_FONT);
        graphics.drawString(card.predicate, 70, 220);
        graphics.setFont(SMALL_FONT);
        graphics.drawString(card.additionalEffect, 70, 345);
        return cardImage;
    }

    public static BufferedImage renderLightProbeCard(final LightProbeCard card) throws IOException {
        final var cardImage = ImageIO.read(new File(CardResources.L_PROBE_CARD_IMAGE));
        final var graphics = cardImage.getGraphics();
        graphics.setFont(MEDIUM_FONT);
        graphics.drawString(card.predicate, 70, 220);
        graphics.setFont(SMALL_FONT);
        graphics.drawString(card.additionalEffect, 70, 345);
        return cardImage;
    }

    public static BufferedImage renderDeck(final List<Object> cards) throws IOException {

        final int rows = cards.size() / CARD_GRID_WIDTH + 1;
        final int canvasWidth = CARD_GRID_WIDTH * CARD_WIDTH;
        final int canvasHeight = CARD_HEIGHT * rows;

        final var bufferedImage = new BufferedImage(canvasWidth, canvasHeight, 1);
        final var graphics = bufferedImage.getGraphics();

        for (int i = 0; i < cards.size(); i++) {
            final Object card = cards.get(i);
            int y = (i / CARD_GRID_WIDTH) * CARD_HEIGHT;
            int x = (i % CARD_GRID_WIDTH) * CARD_WIDTH;
            System.out.println(x);
            System.out.println(y);
            if (card instanceof SystemCard) {
                graphics.drawImage(renderSystemCard((SystemCard) card), x, y, null);
            } else if (card instanceof DeepHackCard) {
                graphics.drawImage(renderDeepHackCard((DeepHackCard) card), x, y, null);
            } else if (card instanceof LightHackCard) {
                graphics.drawImage(renderLightHackCard((LightHackCard) card), x, y, null);
            } else if (card instanceof DeepProbeCard) {
                graphics.drawImage(renderDeepProbeCard((DeepProbeCard) card), x, y, null);
            } else if (card instanceof LightProbeCard) {
                graphics.drawImage(renderLightProbeCard((LightProbeCard) card), x, y, null);
            }
        }
        return bufferedImage;
    }

    ;

    public static void main(String[] args) throws IOException {

        final var filename = getFileName();
        System.out.println(filename);
        final var saveFileName = getSaveFileName();

        final var bufferedImage = renderDeck(List.of(
                new SystemCard("0x0", "/16", "You win!"),
                new DeepHackCard("0x0", "oH hI BEBE"),
                new LightHackCard("/16", "none"),
                new DeepProbeCard("/16", "none"),
                new LightProbeCard("/16", "none")));
        ImageIO.write(bufferedImage, "jpg", new File(saveFileName));
        JLabel picLabel = new JLabel(new ImageIcon(bufferedImage));
        JPanel jPanel = new JPanel();
        jPanel.add(picLabel);
        JFrame f = new JFrame();
        f.setSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));
        f.add(jPanel);
        f.setVisible(true);
    }

    public static String getFileName(){
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        dialog.setFilenameFilter((directory, fileName) -> fileName.endsWith(".jpg"));
        String file = dialog.getFile();
        String directory = dialog.getDirectory();
        System.out.println(directory);
        System.out.println(file + " chosen.");
        return file;
    }

    public static String getSaveFileName(){
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.SAVE);
        dialog.setVisible(true);
        dialog.setFilenameFilter((directory, fileName) -> fileName.endsWith(".jpg"));
        String file = dialog.getFile();
        String directory = dialog.getDirectory();
        System.out.println(directory);
        System.out.println(file + " chosen.");
        return directory + file;
    }

}
