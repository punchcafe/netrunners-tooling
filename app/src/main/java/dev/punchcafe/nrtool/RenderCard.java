package dev.punchcafe.nrtool;

import dev.punchcafe.nrtool.card.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static List<Object> getCardsFromFile(final String fileName) throws IOException {
        final var workBook = getExcelWorkbook(fileName);
        return Stream.of(parseSystemCards(workBook.getSheetAt(0)),
                parseDeepHackCards(workBook.getSheetAt(1)),
                parseLightHackCards(workBook.getSheetAt(2)),
                parseDeepProbeCards(workBook.getSheetAt(3)),
                parseLightProbeCards(workBook.getSheetAt(4)))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public static Workbook getExcelWorkbook(final String filename) throws IOException {
        return WorkbookFactory.create(new FileInputStream(filename));
    }

    public static List<SystemCard> parseSystemCards(final Sheet workSheet) {
        final List<SystemCard> cards = new ArrayList<>();
        for (int i = 1; i < workSheet.getPhysicalNumberOfRows(); i++) {
            cards.add(new SystemCard(getCellValueOrEmptyString(workSheet.getRow(i).getCell(0)),
                    getCellValueOrEmptyString(workSheet.getRow(i).getCell(1)),
                    getCellValueOrEmptyString(workSheet.getRow(i).getCell(2)),
                    getCellValueOrEmptyString(workSheet.getRow(i).getCell(3))));
        }
        return cards;
    }

    public static List<LightHackCard> parseLightHackCards(final Sheet workSheet) {
        final List<LightHackCard> cards = new ArrayList<>();
        for (int i = 1; i < workSheet.getPhysicalNumberOfRows(); i++) {
            cards.add(new LightHackCard(getCellValueOrEmptyString(workSheet.getRow(i).getCell(0)),
                    getCellValueOrEmptyString(workSheet.getRow(i).getCell(1)),
                    getCellValueOrEmptyString(workSheet.getRow(i).getCell(2))));
        }
        return cards;
    }

    public static List<DeepHackCard> parseDeepHackCards(final Sheet workSheet) {
        final List<DeepHackCard> cards = new ArrayList<>();
        for (int i = 1; i < workSheet.getPhysicalNumberOfRows(); i++) {
            cards.add(new DeepHackCard(getCellValueOrEmptyString(workSheet.getRow(i).getCell(0)),
                    getCellValueOrEmptyString(workSheet.getRow(i).getCell(1)),
                    getCellValueOrEmptyString(workSheet.getRow(i).getCell(2))));
        }
        return cards;
    }

    public static List<LightProbeCard> parseLightProbeCards(final Sheet workSheet) {
        final List<LightProbeCard> cards = new ArrayList<>();
        for (int i = 1; i < workSheet.getPhysicalNumberOfRows(); i++) {
            cards.add(new LightProbeCard(getCellValueOrEmptyString(workSheet.getRow(i).getCell(0)),
                    getCellValueOrEmptyString(workSheet.getRow(i).getCell(1)),
                    getCellValueOrEmptyString(workSheet.getRow(i).getCell(2))));
        }
        return cards;
    }

    public static List<DeepProbeCard> parseDeepProbeCards(final Sheet workSheet) {
        final List<DeepProbeCard> cards = new ArrayList<>();
        for (int i = 1; i < workSheet.getPhysicalNumberOfRows(); i++) {
            cards.add(new DeepProbeCard(getCellValueOrEmptyString(workSheet.getRow(i).getCell(0)),
                    getCellValueOrEmptyString(workSheet.getRow(i).getCell(1)),
                    getCellValueOrEmptyString(workSheet.getRow(i).getCell(2))));
        }
        return cards;
    }

    private static String getCellValueOrEmptyString(final Cell cell) {
        if (cell == null) {
            return "";
        }
        return cell.getStringCellValue();
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

        final var cards = getCardsFromFile(filename);
        final var bufferedImage = renderDeck(cards);

        ImageIO.write(bufferedImage, "jpg", new File(saveFileName));
        /*
        JLabel picLabel = new JLabel(new ImageIcon(bufferedImage));
        JPanel jPanel = new JPanel();
        jPanel.add(picLabel);
        JFrame f = new JFrame();
        f.setSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));
        f.add(jPanel);
        f.setVisible(true);
         */
    }

    public static String getFileName() {
        FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        dialog.setFilenameFilter((directory, fileName) -> fileName.endsWith(".jpg"));
        String file = dialog.getFile();
        String directory = dialog.getDirectory();
        System.out.println(directory);
        System.out.println(file + " chosen.");
        return directory + file;
    }

    public static String getSaveFileName() {
        FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
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
