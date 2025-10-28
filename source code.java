import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RadioButtonDemo extends JFrame {

    private JLabel pictureLabel;
    private JRadioButton birdButton, catButton, dogButton, rabbitButton, pigButton;

    public RadioButtonDemo() {
        super("Pet Selector 🐾");
        setLayout(new BorderLayout(10, 10));

        // Create radio buttons
        birdButton = new JRadioButton("Bird");
        catButton = new JRadioButton("Cat");
        dogButton = new JRadioButton("Dog");
        rabbitButton = new JRadioButton("Rabbit");
        pigButton = new JRadioButton("Pig", true);

        // Group the buttons so only one is selected
        ButtonGroup group = new ButtonGroup();
        group.add(birdButton);
        group.add(catButton);
        group.add(dogButton);
        group.add(rabbitButton);
        group.add(pigButton);

        // Add them to a panel
        JPanel radioPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        radioPanel.add(birdButton);
        radioPanel.add(catButton);
        radioPanel.add(dogButton);
        radioPanel.add(rabbitButton);
        radioPanel.add(pigButton);

        // Image area
        pictureLabel = new JLabel();
        pictureLabel.setHorizontalAlignment(JLabel.CENTER);
        pictureLabel.setIcon(loadIcon("/pig.gif")); // default image

        // Add to frame
        add(radioPanel, BorderLayout.WEST);
        add(pictureLabel, BorderLayout.CENTER);

        // Button actions (when clicked)
        birdButton.addActionListener(e -> updateImage("bird"));
        catButton.addActionListener(e -> updateImage("cat"));
        dogButton.addActionListener(e -> updateImage("dog"));
        rabbitButton.addActionListener(e -> updateImage("rabbit"));
        pigButton.addActionListener(e -> updateImage("pig"));

        // Frame setup
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Updates the image when a radio button is clicked
    private void updateImage(String petName) {
        pictureLabel.setIcon(loadIcon("/" + petName + ".gif"));
    }

    // Loads image from resources (src folder)
    private ImageIcon loadIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image scaled = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } else {
            System.out.println("⚠️ Image not found: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        new RadioButtonDemo();
    }
}
