package dev.elliotjarnit.ElliotEngine;

import dev.elliotjarnit.ElliotEngine.Graphics.EColor;
import dev.elliotjarnit.ElliotEngine.Objects.ECamera;
import dev.elliotjarnit.ElliotEngine.Objects.EObject;
import dev.elliotjarnit.ElliotEngine.Objects.EScene;
import dev.elliotjarnit.ElliotEngine.Graphics.RenderingEngine;
import dev.elliotjarnit.ElliotEngine.Overlay.EOImage;
import dev.elliotjarnit.ElliotEngine.Overlay.EOText;
import dev.elliotjarnit.ElliotEngine.Overlay.EOverlay;
import dev.elliotjarnit.ElliotEngine.Utils.Vector2;
import dev.elliotjarnit.ElliotEngine.Window.InputManager;
import dev.elliotjarnit.ElliotEngine.Window.WindowManager;

import java.util.HashMap;

public abstract class ElliotEngine {
    private boolean isSetup = false;
    private boolean running = false;
    private EScene currentScene;
    private EOverlay currentOverlay;
    public WindowManager windowManager;
    public InputManager inputManager;
    public RenderingEngine renderer;

    public abstract void optionSetup();

    public abstract void setup();

    public abstract void loop();

    private void update() {
        if (currentScene != null) {
            for (int i = 0; i < currentScene.getObjects().size(); i++) {
                currentScene.getObjects().get(i).update();
            }
        }

        if (currentOverlay != null) {
            try {
                for (int i = 0; i < currentOverlay.getComponents().size(); i++) {
                    currentOverlay.getComponents().get(i).update(this);
                }
            } catch (Exception ignored) {}
        }
    }

    public void run() {
        if (!isSetup) {
            this.optionSetup();

            // Setup renderer
            renderer = new RenderingEngine(this);
            // Setup window
            windowManager = new WindowManager(this);
            windowManager.setup();
            // Setup input
            inputManager = new InputManager(this);
            inputManager.setup();

            // Setup engine
            setup();

            isSetup = true;
        }
        windowManager.start();
        running = true;

        // Create ElliotEngine Overlay
        EScene startupScene = new EScene(false);
        startupScene.setCamera(new ECamera());

        EOverlay startupOverlay = new EOverlay();
        EOText elliotEngineText = new EOText(new Vector2((double) this.renderer.getWidth() / 2, 30), "Elliot Engine", 20, EColor.WHITE);
        EOImage startupImage = new EOImage(new Vector2((double) this.renderer.getWidth() / 2, (double) this.renderer.getHeight() / 2), "resources/EELogo.png");
        EOText loadingText = new EOText(new Vector2((double) this.renderer.getWidth() / 2, (double) this.renderer.getHeight() - 30), "Loading", 20, EColor.WHITE);
        startupOverlay.addComponent(elliotEngineText);
        startupOverlay.addComponent(startupImage);
        startupOverlay.addComponent(loadingText);

        // Updates 24 times per second
        double currentTime = System.nanoTime();
        double nextUpdate = System.nanoTime();
        double skipTicks = 1000000000.0 / 24.0;

        double startTime = System.nanoTime();

        while (running) {
            // First 7 seconds of the program
            if (System.nanoTime() - startTime < 7000000000.0) {
                double secondsPassed = (System.nanoTime() - startTime) / 1000000000.0;

                loadingText.setText("Loading " + (int) (secondsPassed / 7 * 100) + "%");

                renderer.renderScene(startupScene);
                renderer.renderOverlay(startupOverlay);
            } else {
                if (currentScene != null) {
                    for (EObject object : currentScene.getObjects()) {
                        if (object._toRemove) {
                            currentScene.removeObject(object);
                        }
                    }

                    renderer.renderScene(currentScene);
                } else {
                    renderer.renderScene(null);
                }

                if (currentOverlay != null) {
                    renderer.renderOverlay(currentOverlay);
                } else {
                    renderer.renderOverlay(null);
                }
            }

            currentTime = System.nanoTime();

            while (currentTime > nextUpdate) {
                update();
                loop();
                nextUpdate += skipTicks;
            }
        }
    }

    public void stop() {
        running = false;
        windowManager.stop();
        windowManager = null;
    }

    public void setScene(EScene scene) {
        currentScene = scene;
    }

    public void setOverlay(EOverlay overlay) {
        currentOverlay = overlay;
    }

    public Platform getPlatform() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) return Platform.WINDOWS;
        if (os.contains("mac")) return Platform.MAC;
        if (os.contains("nix") || os.contains("nux") || os.contains("aix")) return Platform.LINUX;
        return Platform.OTHER;
    }

    public void setOption(Options option, String value) {
        options.put(option, value);
    }

    public void setOption(AdvancedOptions option, String value) {
        advancedOptions.put(option, value);
    }

    public String getOption(Options option) {
        return options.get(option);
    }

    public String getOption(AdvancedOptions option) {
        return advancedOptions.get(option);
    }

    public String[] getOptions() {
        String[] options = new String[this.options.size()];
        int i = 0;
        for (Options option : this.options.keySet()) {
            options[i] = option.toString();
            i++;
        }
        return options;
    }

    public String[] getAdvancedOptions() {
        String[] options = new String[this.advancedOptions.size()];
        int i = 0;
        for (AdvancedOptions option : this.advancedOptions.keySet()) {
            options[i] = option.toString();
            i++;
        }
        return options;
    }

    public enum Platform {
        WINDOWS,
        MAC,
        LINUX,
        OTHER
    }

    public enum Options {
        NAME,
        VERSION,
        AUTHOR,
        DESCRIPTION,
        LICENSE,
        WINDOW_WIDTH,
        WINDOW_HEIGHT,
        WINDOW_FULLSCREEN,
    }

    public enum AdvancedOptions {
        MAX_CLIPPING_VERTEXES,
        WIRE_FRAME,
    }

    private final HashMap<Options, String> options = new HashMap<Options, String>() {{
        // Default options
        put(Options.NAME, "Game");
        put(Options.VERSION, "None");
        put(Options.AUTHOR, "None");
        put(Options.DESCRIPTION, "None");
        put(Options.LICENSE, "None");
        put(Options.WINDOW_WIDTH, "800");
        put(Options.WINDOW_HEIGHT, "600");
        put(Options.WINDOW_FULLSCREEN, "false");
    }};
    private final HashMap<AdvancedOptions, String> advancedOptions = new HashMap<AdvancedOptions, String>() {{
        // Default advanced options
        put(AdvancedOptions.MAX_CLIPPING_VERTEXES, "20");
        put(AdvancedOptions.WIRE_FRAME, "false");
    }};
}
