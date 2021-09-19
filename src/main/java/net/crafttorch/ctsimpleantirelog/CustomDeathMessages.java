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
        if (!plugin.getConfig().getBoolean("Custom-death-messages.Enable")) return;
        if (Objects.equals(plugin.getConfig().getString("Custom-death-messages.Language"), "English")) {
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
            } else {
                if (damageCause != null){
                    Bukkit.broadcastMessage(Bar.format(entity.getName() + " &cdied because of: &f" + damageCause.getCause()));
                }else{
                    Bukkit.broadcastMessage(Bar.format(entity.getName() + " &cdied from disconnecting in PvP"));
                }
            }
        }else if(Objects.equals(plugin.getConfig().getString("Custom-death-messages.Language"), "Español")) {
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
                        Bukkit.broadcastMessage(Bar.format(player.getName() + " &casesinó a: &f" + entity.getName() + " &ccon: " + Objects.requireNonNull(weap.getItemMeta()).getDisplayName() + " &ca: &f" + Math.round(dist) + " mts."));
                    } else {
                        if (weap.getType().equals(Material.AIR)) {
                            Bukkit.broadcastMessage(Bar.format(player.getName() + " &casesinó a: &f" + entity.getName() + " &ccon: &bSus propias manos &ca: &f" + Math.round(dist) + " mts."));
                            return;
                        }
                        Bukkit.broadcastMessage(Bar.format(player.getName() + " &casesinó a: &f" + entity.getName() + " &ccon: &b" + weap.getType() + " &ca: &f" + Math.round(dist) + " mts."));
                    }
                    return;
                }
                if (entityDamageByEntityEvent.getDamager() instanceof Arrow) {
                    Arrow arrow = (Arrow) entityDamageByEntityEvent.getDamager();
                    if (arrow.getShooter() instanceof Skeleton) {
                        Bukkit.broadcastMessage(Bar.format(entity.getName() + " &cmurió a manos de: &fSkeleton"));
                        return;
                    }
                }
                Bukkit.broadcastMessage(Bar.format(entity.getName() + " &cmurió a manos de: &f" + killer));
            } else {
                if (damageCause != null){
                    Bukkit.broadcastMessage(Bar.format(entity.getName() + " &cmurió a causa de: &f" + damageCause.getCause()));
                }else{
                    Bukkit.broadcastMessage(Bar.format(entity.getName() + " &cMurió por desconectarse en PvP"));
                }
            }
        }else if(Objects.equals(plugin.getConfig().getString("Custom-death-messages.Language"), "Portugues")){
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
                        Bukkit.broadcastMessage(Bar.format(player.getName() + " &cassassinou: &f" + entity.getName() + " &ccom: " + Objects.requireNonNull(weap.getItemMeta()).getDisplayName() + " &ca: &f" + Math.round(dist) + " mts."));
                    } else {
                        if (weap.getType().equals(Material.AIR)) {
                            Bukkit.broadcastMessage(Bar.format(player.getName() + " &cassassinou: &f" + entity.getName() + " &ccom: &bAs próprias mãos &ca: &f" + Math.round(dist) + " mts."));
                            return;
                        }
                        Bukkit.broadcastMessage(Bar.format(player.getName() + " &cassassinou: &f" + entity.getName() + " &ccom: &b" + weap.getType() + " &ca: &f" + Math.round(dist) + " mts."));
                    }
                    return;
                }
                if (entityDamageByEntityEvent.getDamager() instanceof Arrow) {
                    Arrow arrow = (Arrow) entityDamageByEntityEvent.getDamager();
                    if (arrow.getShooter() instanceof Skeleton) {
                        Bukkit.broadcastMessage(Bar.format(entity.getName() + " &cmorreu nas mãos de: &fSkeleton"));
                        return;
                    }
                }
                Bukkit.broadcastMessage(Bar.format(entity.getName() + " &cmorreu nas mãos de: &f" + killer));
            } else {
                if (damageCause != null){
                    Bukkit.broadcastMessage(Bar.format(entity.getName() + " &cmorreu por causa de: &f" + damageCause.getCause()));
                }else{
                    Bukkit.broadcastMessage(Bar.format(entity.getName() + " &cMorreu por desconexão em PvP"));
                }
            }
        }
    }

}
