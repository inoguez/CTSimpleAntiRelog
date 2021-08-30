package net.crafttorch.ctsimpleantirelog;

import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Arrow;
import org.bukkit.Material;
import org.bukkit.Bukkit;
import java.util.Objects;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.Listener;

public class CustomDeathMessages implements Listener {
    private final AR plugin;
    
    public CustomDeathMessages(final AR plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (this.plugin.getConfig().getBoolean("Custom-death-messages.Enable")) {
            e.setDeathMessage(null);
            LivingEntity entity = e.getEntity();
            Player player = entity.getKiller();
            EntityDamageEvent damageCause = entity.getLastDamageCause();
            if (damageCause instanceof EntityDamageByEntityEvent) {
                EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent) damageCause;
                String killer = entityDamageByEntityEvent.getDamager().getName();
                if (player != null) {
                    ItemStack weap = Objects.requireNonNull(player.getEquipment()).getItemInMainHand();
                    double dist = entity.getLocation().distance(player.getLocation());
                    if (weap.hasItemMeta()) {
                        Bukkit.broadcastMessage(Bar.format(player.getName() + " &ckilled: &f" + entity.getName() + " &cwith: " + Objects.requireNonNull(weap.getItemMeta()).getDisplayName() + " &cat: &f" + Math.round(dist) + " mts."));
                    } else {
                        if (weap.getType().equals(Material.AIR)) {
                            Bukkit.broadcastMessage(Bar.format(player.getName() + " &ckilled: &f" + entity.getName() + " &cwith: &bHIS HANDS &cat: &f" + Math.round(dist) + " mts."));
                            return;
                        }
                        Bukkit.broadcastMessage(Bar.format(player.getName() + " &ckilled: &f" + entity.getName() + " &cwith: &b" + weap.getType() + " &cat: &f" + Math.round(dist) + " mts."));
                    }
                    return;
                }
                if (entityDamageByEntityEvent.getDamager() instanceof Arrow) {
                    Arrow arrow = (Arrow) entityDamageByEntityEvent.getDamager();
                    if (arrow.getShooter() instanceof Skeleton) {
                        Bukkit.broadcastMessage(Bar.format(entity.getName() + " &cdied at the hands of: &fSkeleton"));
                        return;
                    }
                }
                Bukkit.broadcastMessage(Bar.format(entity.getName() + " &cdied at the hands of: &f" + killer));
            }else{
                assert damageCause != null;
                Bukkit.broadcastMessage(Bar.format(entity.getName() + " &cdied because of: &f" + damageCause.getCause()));
            }
        }
    }
}
