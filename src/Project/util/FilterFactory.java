package Project.util;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.*;

public class FilterFactory {
    public static JLayer<JComponent> setFilter(Color color,JComponent comp) {
        // This custom layerUI will fill the layer with translucent green
        // and print out all mouseMotion events generated within its borders
        LayerUI<JComponent> layerUI = new LayerUI<JComponent>() {

            public void paint(Graphics g, JComponent c) {
                // paint the layer as is
                super.paint(g, c);
                // fill it with the translucent green
                g.setColor(color);
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }

            public void installUI(JComponent c) {
                super.installUI(c);
                // enable mouse motion events for the layer's subcomponents
                ((JLayer) c).setLayerEventMask(AWTEvent.MOUSE_MOTION_EVENT_MASK);
            }

            public void uninstallUI(JComponent c) {
                super.uninstallUI(c);
                // reset the layer event mask
                ((JLayer) c).setLayerEventMask(0);
            }

            // overridden method which catches MouseMotion events
            public void eventDispatched(AWTEvent e, JLayer<? extends JComponent> l) {
                System.out.println("AWTEvent detected: " + e);
            }
        };
        // create a component to be decorated with the layer

        // create the layer for the panel using our custom layerUI
        return new JLayer<JComponent>(comp, layerUI);
    }
}
