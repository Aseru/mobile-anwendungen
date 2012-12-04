package edu.hm.mineandroidsweeper.difficulties;

import edu.hm.mineandroidsweeper.application.App;

/**
 * TODO: Document type InvalidConfigException.
 */
public class InvalidConfigException extends Exception {
    
    private static final long serialVersionUID = 6932575118403127343L;
    
    private final String toastText;
    
    /**
     * Creates a new instance of {@link InvalidConfigException}.
     * 
     * @param message
     *            the exception message
     * @param resID
     *            resource id for the toast text
     * @param formatArgs
     *            args for the string formatter
     */
    public InvalidConfigException(final String message, final int resID, final Object... formatArgs) {
        super(message);
        toastText = App.getContext().getString(resID, formatArgs);
    }
    
    /**
     * Getter for the toastText.
     * 
     * @return the tex for the Toast
     */
    public String getToastText() {
        return toastText;
    }
    
}
