package com.tienda.utilities;

import java.awt.*;
import javax.swing.ImageIcon;

/**
 *
 * @author isai_
 */
public class CursorClass {

    private static CursorClass instance;

    private final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private ImageIcon cursorImageNormalSelect = new ImageIcon(getClass().getResource("/cursors/normal_select.png"));
    private Cursor customCursorNormalSelect = toolkit.createCustomCursor(cursorImageNormalSelect.getImage(), new Point(0, 0), "CustomCursorNormalSelect");

    private CursorClass() {}

    @SuppressWarnings("DoubleCheckedLocking")
    public static CursorClass getInstance() {
        if (instance == null) {
            synchronized (CursorClass.class) {
                if (instance == null) {
                    instance = new CursorClass();
                    return instance;
                }
            }
        }
        return instance;
    }

    public Cursor getCustomCursorNormalSelect() {
        return customCursorNormalSelect;
    }
    
    
}
