package com.tienda.utilities;

import java.awt.*;

/**
 *
 * @author isai_
 */
public class CursorClass {

    private static CursorClass instance;

    private final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Image cursorImageNormalSelect = toolkit.getImage(getClass().getResource("/cursors/normal_select.cur"));
    private Cursor customCursorNormalSelect = toolkit.createCustomCursor(cursorImageNormalSelect, new Point(0, 0), "CustomCursorNormalSelect");

    private CursorClass() {}

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
