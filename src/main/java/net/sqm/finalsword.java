package net.sqm;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(finalsword.MODID)
public class finalsword {
    public static final String MODID = "finalsword";

    public finalsword() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        ItemInit.ITEMS.register(modEventBus);
        EntityInit.REGISTRY.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
