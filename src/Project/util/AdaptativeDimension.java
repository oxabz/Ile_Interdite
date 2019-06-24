package Project.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class AdaptativeDimension {

    public interface calculatedInt {

        public int getResult();
    }

    private JFrame frame;
    private JComponent container;
    private double width, height;
    private calculatedInt calculatedWidth, calculatedHeight;
    private JComponent component;

    public AdaptativeDimension(JFrame frame, double width, double height, JComponent component) {
        this.frame = frame;
        this.width = width;
        this.height = height;
        this.component = component;

        Dimension frameSize = frame.getSize();
        component.setPreferredSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
        component.setMinimumSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
        component.setMaximumSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));

        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension frameSize = e.getComponent().getSize();
                component.setPreferredSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
                component.setMinimumSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
                component.setMaximumSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                Dimension frameSize = e.getComponent().getSize();
                component.setPreferredSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
                component.setMinimumSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
                component.setMaximumSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
            }

            @Override
            public void componentShown(ComponentEvent e) {
                Dimension frameSize = e.getComponent().getSize();
                component.setPreferredSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
                component.setMinimumSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
                component.setMaximumSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
    }

    public AdaptativeDimension(JFrame frame, calculatedInt calculatedWidth, calculatedInt calculatedHeight, JComponent component) {
        this.frame = frame;
        this.calculatedWidth = calculatedWidth;
        this.calculatedHeight = calculatedHeight;
        this.component = component;

        Dimension frameSize = frame.getSize();
        component.setPreferredSize(new Dimension((calculatedWidth == null ? component.getWidth() : calculatedWidth.getResult()), (calculatedHeight == null ? component.getWidth() : calculatedHeight.getResult())));
        component.setMinimumSize(new Dimension((calculatedWidth == null ? 0 : calculatedWidth.getResult()), (calculatedHeight == null ? 0 : calculatedHeight.getResult())));
        component.setMaximumSize(new Dimension((calculatedWidth == null ? frameSize.width : calculatedWidth.getResult()), (calculatedHeight == null ? frameSize.height : calculatedHeight.getResult())));

        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension frameSize = e.getComponent().getSize();
                component.setPreferredSize(new Dimension((calculatedWidth == null ? component.getWidth() : calculatedWidth.getResult()), (calculatedHeight == null ? component.getWidth() : calculatedHeight.getResult())));
                component.setMinimumSize(new Dimension((calculatedWidth == null ? 0 : calculatedWidth.getResult()), (calculatedHeight == null ? 0 : calculatedHeight.getResult())));
                component.setMaximumSize(new Dimension((calculatedWidth == null ? frameSize.width : calculatedWidth.getResult()), (calculatedHeight == null ? frameSize.height : calculatedHeight.getResult())));
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                Dimension frameSize = e.getComponent().getSize();
                component.setPreferredSize(new Dimension((calculatedWidth == null ? component.getWidth() : calculatedWidth.getResult()), (calculatedHeight == null ? component.getWidth() : calculatedHeight.getResult())));
                component.setMinimumSize(new Dimension((calculatedWidth == null ? 0 : calculatedWidth.getResult()), (calculatedHeight == null ? 0 : calculatedHeight.getResult())));
                component.setMaximumSize(new Dimension((calculatedWidth == null ? frameSize.width : calculatedWidth.getResult()), (calculatedHeight == null ? frameSize.height : calculatedHeight.getResult())));
            }

            @Override
            public void componentShown(ComponentEvent e) {
                Dimension frameSize = e.getComponent().getSize();
                component.setPreferredSize(new Dimension((calculatedWidth == null ? component.getWidth() : calculatedWidth.getResult()), (calculatedHeight == null ? component.getWidth() : calculatedHeight.getResult())));
                component.setMinimumSize(new Dimension((calculatedWidth == null ? 0 : calculatedWidth.getResult()), (calculatedHeight == null ? 0 : calculatedHeight.getResult())));
                component.setMaximumSize(new Dimension((calculatedWidth == null ? frameSize.width : calculatedWidth.getResult()), (calculatedHeight == null ? frameSize.height : calculatedHeight.getResult())));
            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
    }

    public AdaptativeDimension(JComponent container, double width, double height, JComponent component) {
        this.container = container;
        this.width = width;
        this.height = height;
        this.component = component;

        Dimension frameSize = container.getSize();
        component.setPreferredSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
        component.setMinimumSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
        component.setMaximumSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));

        container.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension frameSize = e.getComponent().getSize();
                component.setPreferredSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
                component.setMinimumSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
                component.setMaximumSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                Dimension frameSize = e.getComponent().getSize();
                component.setPreferredSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
                component.setMinimumSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
                component.setMaximumSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
            }

            @Override
            public void componentShown(ComponentEvent e) {
                Dimension frameSize = e.getComponent().getSize();
                component.setPreferredSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
                component.setMinimumSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
                component.setMaximumSize(new Dimension((int) (frameSize.width * width), (int) (frameSize.height * height)));
            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
    }

    public AdaptativeDimension(JComponent container, calculatedInt calculatedWidth, calculatedInt calculatedHeight, JComponent component) {
        this.container = container;
        this.calculatedWidth = calculatedWidth;
        this.calculatedHeight = calculatedHeight;
        this.component = component;

        Dimension frameSize = container.getSize();
        component.setPreferredSize(new Dimension((calculatedWidth == null ? component.getWidth() : calculatedWidth.getResult()), (calculatedHeight == null ? component.getWidth() : calculatedHeight.getResult())));
        component.setMinimumSize(new Dimension((calculatedWidth == null ? 0 : calculatedWidth.getResult()), (calculatedHeight == null ? 0 : calculatedHeight.getResult())));
        component.setMaximumSize(new Dimension((calculatedWidth == null ? frameSize.width : calculatedWidth.getResult()), (calculatedHeight == null ? frameSize.height : calculatedHeight.getResult())));

        container.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension frameSize = e.getComponent().getSize();
                component.setPreferredSize(new Dimension((calculatedWidth == null ? component.getWidth() : calculatedWidth.getResult()), (calculatedHeight == null ? component.getWidth() : calculatedHeight.getResult())));
                component.setMinimumSize(new Dimension((calculatedWidth == null ? 0 : calculatedWidth.getResult()), (calculatedHeight == null ? 0 : calculatedHeight.getResult())));
                component.setMaximumSize(new Dimension((calculatedWidth == null ? frameSize.width : calculatedWidth.getResult()), (calculatedHeight == null ? frameSize.height : calculatedHeight.getResult())));
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                Dimension frameSize = e.getComponent().getSize();
                component.setPreferredSize(new Dimension((calculatedWidth == null ? component.getWidth() : calculatedWidth.getResult()), (calculatedHeight == null ? component.getWidth() : calculatedHeight.getResult())));
                component.setMinimumSize(new Dimension((calculatedWidth == null ? 0 : calculatedWidth.getResult()), (calculatedHeight == null ? 0 : calculatedHeight.getResult())));
                component.setMaximumSize(new Dimension((calculatedWidth == null ? frameSize.width : calculatedWidth.getResult()), (calculatedHeight == null ? frameSize.height : calculatedHeight.getResult())));
            }

            @Override
            public void componentShown(ComponentEvent e) {
                Dimension frameSize = e.getComponent().getSize();
                component.setPreferredSize(new Dimension((calculatedWidth == null ? component.getWidth() : calculatedWidth.getResult()), (calculatedHeight == null ? component.getWidth() : calculatedHeight.getResult())));
                component.setMinimumSize(new Dimension((calculatedWidth == null ? 0 : calculatedWidth.getResult()), (calculatedHeight == null ? 0 : calculatedHeight.getResult())));
                component.setMaximumSize(new Dimension((calculatedWidth == null ? frameSize.width : calculatedWidth.getResult()), (calculatedHeight == null ? frameSize.height : calculatedHeight.getResult())));
            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
    }
}
