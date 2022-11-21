package net.sqm;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.AdvancementEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.Advancement;
import net.sqm.inits.ItemInit;


import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class FinalCoreGetEvent {
    @SubscribeEvent
    public static void onAdvancement(AdvancementEvent event) {
        execute(event, event.getEntity().level, event.getAdvancement(), event.getEntity());
    }

    private static void execute(@Nullable Event event, LevelAccessor world, Advancement advancement, Entity entity) {
        if (advancement == null || entity == null)
            return;
        if (world instanceof Level _lvl && _lvl.getServer() != null
                ? _lvl.getServer().getAdvancements().getAdvancement(new ResourceLocation("minecraft:end/dragon_egg")).equals(advancement)
                : false) {
            if (entity instanceof Player _player) {
                ItemStack _setstack = new ItemStack(ItemInit.DragonHeart.get());
                _setstack.setCount(1);
                ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
            }
        }
    }

}
