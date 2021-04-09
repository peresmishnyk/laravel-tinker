package nl.deschepers.laraveltinker.settings

import com.intellij.openapi.options.Configurable
import nl.deschepers.laraveltinker.Strings
import javax.swing.JComponent

class PluginSettingsConfigurable : Configurable {
    private var pluginSettingsWindow: PluginSettingsWindow? = null

    override fun createComponent(): JComponent {
        pluginSettingsWindow = PluginSettingsWindow()
        return pluginSettingsWindow!!.getPanel()
    }

    override fun isModified(): Boolean {
        val settings = PluginSettings.getInstance()
        return pluginSettingsWindow!!.showExecutionStarted != settings.showExecutionStarted ||
            pluginSettingsWindow!!.showExecutionEnded != settings.showExecutionEnded ||
            pluginSettingsWindow!!.useWordWrapping != settings.useWordWrapping
    }

    override fun apply() {
        val settings = PluginSettings.getInstance()
        settings.showExecutionStarted = pluginSettingsWindow!!.showExecutionStarted
        settings.showExecutionEnded = pluginSettingsWindow!!.showExecutionEnded
        settings.useWordWrapping = pluginSettingsWindow!!.useWordWrapping
    }

    override fun reset() {
        val settings = PluginSettings.getInstance()
        pluginSettingsWindow!!.showExecutionStarted = settings.showExecutionStarted
        pluginSettingsWindow!!.showExecutionEnded = settings.showExecutionEnded
        pluginSettingsWindow!!.useWordWrapping = settings.useWordWrapping
    }

    override fun getDisplayName(): String {
        return Strings.getMessage("name")
    }

    override fun disposeUIResources() {
        pluginSettingsWindow = null
    }
}
