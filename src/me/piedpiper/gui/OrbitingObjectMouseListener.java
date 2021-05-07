package me.piedpiper.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OrbitingObjectMouseListener implements MouseListener {
    /**
     * Referencia a view objektumra
     */
    private View view;
    public OrbitingObjectMouseListener(View view) {
        this.view = view;
    }
    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().getClass() == AsteroidPanel.class) {
            view.SetSelectedObject(((AsteroidPanel)e.getSource()).GetAsteroid());
            view.ObjectClicked(((AsteroidPanel)e.getSource()).GetAsteroid());
        }
        if (e.getSource().getClass() == TeleportGatePanel.class) {
            view.SetSelectedObject(((TeleportGatePanel)e.getSource()).GetGate());
            view.ObjectClicked(((TeleportGatePanel)e.getSource()).GetGate());
        }
        if (e.getSource().getClass() == BaseAsteroidPanel.class) {
            view.SetSelectedObject(((BaseAsteroidPanel)e.getSource()).GetBaseAsteroid());
            view.ObjectClicked(((BaseAsteroidPanel)e.getSource()).GetBaseAsteroid());
        }
        view.repaint();
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
