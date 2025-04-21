// package fitnesstracker.view;

// import javax.swing.*;
// import java.awt.*;
// import java.util.HashMap;
// import java.util.Map;

// public class ThemeManager {
//     public static final String LIGHT_THEME = "Light";
//     public static final String DARK_THEME = "Dark";
    
//     private static String currentTheme = LIGHT_THEME;
//     private static Map<String, UITheme> themes = new HashMap<>();
    
//     static {
//         // Initialize themes
//         themes.put(LIGHT_THEME, new UITheme(
//             new Color(240, 240, 240),  // background
//             new Color(50, 50, 50),     // text
//             new Color(255, 255, 255),  // component background
//             new Color(230, 230, 230),  // component highlight
//             new Color(66, 139, 202)    // accent
//         ));
        
//         themes.put(DARK_THEME, new UITheme(
//             new Color(43, 43, 43),     // background
//             new Color(220, 220, 220),  // text
//             new Color(60, 60, 60),     // component background
//             new Color(80, 80, 80),     // component highlight
//             new Color(100, 149, 237)   // accent
//         ));
//     }
    
//     public static void setTheme(String themeName) {
//         if (themes.containsKey(themeName)) {
//             currentTheme = themeName;
//             UITheme theme = themes.get(themeName);
            
//             // Update UIManager properties
//             UIManager.put("Panel.background", theme.background);
//             UIManager.put("Label.foreground", theme.text);
//             UIManager.put("TextField.background", theme.componentBackground);
//             UIManager.put("TextField.foreground", theme.text);
//             UIManager.put("TextArea.background", theme.componentBackground);
//             UIManager.put("TextArea.foreground", theme.text);
//             UIManager.put("List.background", theme.componentBackground);
//             UIManager.put("List.foreground", theme.text);
//             UIManager.put("ComboBox.background", theme.componentBackground);
//             UIManager.put("ComboBox.foreground", theme.text);
//             UIManager.put("Button.background", theme.accent);
//             UIManager.put("Button.foreground", Color.WHITE);
//             UIManager.put("TabbedPane.background", theme.background);
//             UIManager.put("TabbedPane.foreground", theme.text);
//             UIManager.put("ScrollPane.background", theme.background);
//             UIManager.put("Table.background", theme.componentBackground);
//             UIManager.put("Table.foreground", theme.text);
//             UIManager.put("TableHeader.background", theme.componentHighlight);
//             UIManager.put("TableHeader.foreground", theme.text);
            
//             // Also update progress bar colors
//             UIManager.put("ProgressBar.background", theme.componentBackground);
//             UIManager.put("ProgressBar.foreground", theme.accent);
            
//             // Update UI for all components
//             for (Frame frame : Frame.getFrames()) {
//                 SwingUtilities.updateComponentTreeUI(frame);
//             }
//         }
//     }
    
//     public static UITheme getCurrentTheme() {
//         return themes.get(currentTheme);
//     }
    
//     public static String getCurrentThemeName() {
//         return currentTheme;
//     }
    
//     // Inner class to hold theme colors
//     public static class UITheme {
//         public final Color background;
//         public final Color text;
//         public final Color componentBackground;
//         public final Color componentHighlight;
//         public final Color accent;
        
//         public UITheme(Color background, Color text, Color componentBackground, 
//                       Color componentHighlight, Color accent) {
//             this.background = background;
//             this.text = text;
//             this.componentBackground = componentBackground;
//             this.componentHighlight = componentHighlight;
//             this.accent = accent;
//         }
//     }
// }


package fitnesstracker.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ThemeManager {
    public static final String LIGHT_THEME = "Light";
    public static final String DARK_THEME = "Dark";
    
    private static String currentTheme = LIGHT_THEME;
    private static Map<String, UITheme> themes = new HashMap<>();
    
    static {
        // Initialize themes
        themes.put(LIGHT_THEME, new UITheme(
            new Color(240, 240, 240),  // background
            new Color(50, 50, 50),     // text
            new Color(255, 255, 255),  // component background
            new Color(230, 230, 230),  // component highlight
            new Color(66, 139, 202)    // accent
        ));
        
        themes.put(DARK_THEME, new UITheme(
            new Color(43, 43, 43),     // background
            new Color(220, 220, 220),  // text
            new Color(60, 60, 60),     // component background
            new Color(80, 80, 80),     // component highlight
            new Color(100, 149, 237)   // accent
        ));
    }
    
    public static void setTheme(String themeName) {
        if (themes.containsKey(themeName)) {
            currentTheme = themeName;
            UITheme theme = themes.get(themeName);
            
            // Update UIManager properties
            UIManager.put("Panel.background", theme.background);
            UIManager.put("Label.foreground", theme.text);
            UIManager.put("TextField.background", theme.componentBackground);
            UIManager.put("TextField.foreground", theme.text);
            UIManager.put("TextArea.background", theme.componentBackground);
            UIManager.put("TextArea.foreground", theme.text);
            UIManager.put("List.background", theme.componentBackground);
            UIManager.put("List.foreground", theme.text);
            UIManager.put("ComboBox.background", theme.componentBackground);
            UIManager.put("ComboBox.foreground", theme.text);
            UIManager.put("Button.background", theme.accent);
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("TabbedPane.background", theme.background);
            UIManager.put("TabbedPane.foreground", theme.text);
            UIManager.put("ScrollPane.background", theme.background);
            UIManager.put("Table.background", theme.componentBackground);
            UIManager.put("Table.foreground", theme.text);
            UIManager.put("TableHeader.background", theme.componentHighlight);
            UIManager.put("TableHeader.foreground", theme.text);
            UIManager.put("ToggleButton.background", theme.accent);
            UIManager.put("ToggleButton.foreground", Color.WHITE);
            

            // Add these for dialogs and option panes
            UIManager.put("OptionPane.background", theme.background);
            UIManager.put("OptionPane.messageForeground", theme.text);
            UIManager.put("OptionPane.messageAreaBorder", BorderFactory.createEmptyBorder(10, 10, 10, 10));
            UIManager.put("OptionPane.buttonAreaBorder", BorderFactory.createEmptyBorder(0, 10, 10, 10));
            
            // Dialog properties
            UIManager.put("Dialog.background", theme.background);
            UIManager.put("Dialog.foreground", theme.text);
            
            // Dialog buttons and borders
            UIManager.put("OptionPane.buttonBackground", theme.accent);
            UIManager.put("OptionPane.buttonForeground", Color.WHITE);
            
            
            // Also update progress bar colors
            UIManager.put("ProgressBar.background", theme.componentBackground);
            UIManager.put("ProgressBar.foreground", theme.accent);
            
            // Update UI for all components
            try {
                // This is the more robust way to update all window UIs
                for (Window window : Window.getWindows()) {
                    SwingUtilities.updateComponentTreeUI(window);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static UITheme getCurrentTheme() {
        return themes.get(currentTheme);
    }
    
    public static String getCurrentThemeName() {
        return currentTheme;
    }
    
    // Inner class to hold theme colors
    public static class UITheme {
        public final Color background;
        public final Color text;
        public final Color componentBackground;
        public final Color componentHighlight;
        public final Color accent;
        
        public UITheme(Color background, Color text, Color componentBackground, 
                      Color componentHighlight, Color accent) {
            this.background = background;
            this.text = text;
            this.componentBackground = componentBackground;
            this.componentHighlight = componentHighlight;
            this.accent = accent;
        }
    }
}