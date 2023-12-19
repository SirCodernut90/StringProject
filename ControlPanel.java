import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Represents a control panel for a craps "table"
public class ControlPanel extends JPanel implements ActionListener {


    // Constructor
    public ControlPanel()
    {
        JButton rollButton = new JButton("Roll");
        rollButton.addActionListener(this);
        add(rollButton);
    }

    // Called when the roll button is clicked
    public void actionPerformed(ActionEvent e)
    {

    }
}
