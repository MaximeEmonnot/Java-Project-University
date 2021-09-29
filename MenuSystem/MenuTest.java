package MenuSystem;

import java.util.HashMap;
import java.util.Map;
import java.awt.Rectangle;

public class MenuTest extends MenuDecorator {

    public MenuTest(IMenu _decoratedMenu) {
        super(_decoratedMenu);
        
        HashMap<Rectangle, Map.Entry<String, Lambda>> buttons = new HashMap<Rectangle, Map.Entry<String, Lambda>>();
        HashMap<Rectangle, String> textBoxes = new HashMap<Rectangle, String>();

        //Init buttons
        buttons.put(new Rectangle(150, 150, 50, 50), Map.entry("Button0", (Lambda)(()->{ System.out.println("Button0");})));
        buttons.put(new Rectangle(250, 150, 50, 50), Map.entry("Button1", (Lambda)(()->{ System.out.println("Button1");})));
        buttons.put(new Rectangle(350, 150, 50, 50), Map.entry("Button2", (Lambda)(()->{ System.out.println("Button2");})));

        //Init textboxes
        textBoxes.put(new Rectangle(500, 500, 50, 50),  "Blabla");

        InitializeButtonsAndTextBoxes(buttons, textBoxes);
    }
}
