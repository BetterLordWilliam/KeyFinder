package src.ui;

import javax.swing.JComponent;

/**
 * ColorApplier:	Applies Colors to foreground and background.
 */
public interface ComponentApplier<ComponenetType extends JComponent> {
	public void apply(ComponenetType input);
}
