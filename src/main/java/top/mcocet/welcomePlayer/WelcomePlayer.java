package top.mcocet.welcomePlayer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.concurrent.TimeUnit;

public final class WelcomePlayer extends JavaPlugin implements Listener {

    private FileConfiguration config;
    private LegacyComponentSerializer legacySerializer;

    @Override
    public void onEnable() {
        // 保存默认配置
        saveDefaultConfig();
        
        // 加载配置
        config = getConfig();
        config.options().copyDefaults(true);
        
        // 初始化Legacy组件序列化器
        legacySerializer = LegacyComponentSerializer.builder()
                .character('&')
                .hexColors()
                .useUnusualXRepeatedCharacterHexFormat()
                .build();
        
        // 注册事件监听器
        getServer().getPluginManager().registerEvents(this, this);
        
        getLogger().info("WelcomePlayer 插件已启用！");
    }

    @Override
    public void onDisable() {
        getLogger().info("WelcomePlayer 插件已禁用！");
    }
    
    /**
     * 重新加载配置
     */
    public void reloadConfigFile() {
        super.reloadConfig();
        config = getConfig();
    }
    
    /**
     * 将带有&颜色代码的字符串转换为Adventure Component
     */
    private Component translateColorCodes(String text) {
        return legacySerializer.deserialize(text);
    }
    
    /**
     * 发送欢迎消息给玩家
     */
    private void sendWelcomeMessage(Player player) {
        // 检查是否启用
        if (!config.getBoolean("enabled", true)) {
            return;
        }
        
        // 检查是否只在首次加入时发送
        boolean firstJoinOnly = config.getBoolean("first-join-only", false);
        if (firstJoinOnly && !player.hasPlayedBefore()) {
            return;
        }
        
        // 获取消息列表
        List<String> messages = config.getStringList("messages");
        if (messages.isEmpty()) {
            return;
        }
        
        // 获取延迟时间
        long delay = config.getLong("delay", 0);
        
        // 如果设置了延迟，使用Folia兼容的调度方式
        if (delay > 0) {
            // Folia支持区域化调度，对于全局任务使用GlobalRegionScheduler
            getServer().getGlobalRegionScheduler().runDelayed(
                this,
                (task) -> sendMessageToPlayer(player, messages),
                delay * 20L  // 将秒转换为tick（20 tick = 1秒）
            );
        } else {
            // 立即发送
            sendMessageToPlayer(player, messages);
        }
    }
    
    /**
     * 实际发送消息给玩家
     */
    private void sendMessageToPlayer(Player player, List<String> messages) {
        String playerName = player.getName();
        
        for (String message : messages) {
            // 替换玩家名字占位符
            String processedMessage = message.replace("%player_name%", playerName);
            
            // 转换颜色代码并发送
            Component component = translateColorCodes(processedMessage);
            player.sendMessage(component);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        sendWelcomeMessage(player);
    }
}
