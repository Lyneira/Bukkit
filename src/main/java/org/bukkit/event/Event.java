package org.bukkit.event;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Represents an event
 */
public abstract class Event {
    private String name;
    protected JavaPlugin sourcePlugin = null;

    /**
     * @return Name of this event
     */
    public String getEventName() {
        if (name == null) {
            name = getClass().getSimpleName();
        }
        return name;
    }

    public abstract HandlerList getHandlers();

    /**
     * Get the plugin this event originates from. Default is null, meaning it does not come from a plugin.
     * <p />
     * Note:
     * Setting the source plugin is not enforced in any way.
     * 
     * @return The plugin this event originates from.
     */
    public JavaPlugin getSourcePlugin() {
        return sourcePlugin;
    }

    /**
     * Sets the plugin this event originates from. If not set, the default is null, meaning it does not come from a plugin.
     * <p />
     * Note:
     * Plugins that create events should set themselves as the source using this method.
     * Other plugins can then distinguish between naturally generated events and artificial events.
     *
     * @param sourcePlugin Source plugin of this event. A null value means it does not come from a plugin.
     */
    public void setSourcePlugin(JavaPlugin sourcePlugin) {
        this.sourcePlugin = sourcePlugin;
    }

    public enum Result {

        /**
         * Deny the event.
         * Depending on the event, the action indicated by the event will either not take place or will be reverted.
         * Some actions may not be denied.
         */
        DENY,
        /**
         * Neither deny nor allow the event.
         * The server will proceed with its normal handling.
         */
        DEFAULT,
        /**
         * Allow / Force the event.
         * The action indicated by the event will take place if possible, even if the server would not normally allow the action.
         * Some actions may not be allowed.
         */
        ALLOW;
    }
}
