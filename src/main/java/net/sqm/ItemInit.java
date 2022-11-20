package net.sqm;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.sqm.finalsword.MODID;

public class ItemInit {
    public static final CreativeModeTab finalswordTab = new CreativeModeTab(MODID) {
        @Override
        public ItemStack makeIcon() {
            return FinalSwordRfinal.get().getDefaultInstance();
        }};
    public static final DeferredRegister<net.minecraft.world.item.Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<net.minecraft.world.item.Item> DragonHeart = ITEMS.register("finalcore", () -> new Item(new Item.Properties().tab(finalswordTab).stacksTo(1).fireResistant()));
    public static final RegistryObject<net.minecraft.world.item.Item> FinalSwordR1 = finalSwordRegister("finalsword_mk1", power(8), "§eRank: " + "I");
    public static final RegistryObject<net.minecraft.world.item.Item> FinalSwordR2 = finalSwordRegister("finalsword_mk2", power(10), "§eRank: " + "II");
    public static final RegistryObject<net.minecraft.world.item.Item> FinalSwordR3 = finalSwordRegister("finalsword_mk3", power(15), "§eRank: " + "III");
    public static final RegistryObject<net.minecraft.world.item.Item> FinalSwordR4 = finalSwordRegister("finalsword_mk4", power(20), "§eRank: " + "IV");
    public static final RegistryObject<net.minecraft.world.item.Item> FinalSwordR5 = finalSwordRegister("finalsword_mk5", power(31), "§eRank: " + "V");
    public static final RegistryObject<net.minecraft.world.item.Item> FinalSwordR6 = finalSwordRegister("finalsword_mk6", power(44), "§eRank: " + "VI");
    public static final RegistryObject<net.minecraft.world.item.Item> FinalSwordR7 = finalSwordRegister("finalsword_mk7", power(63), "§eRank: " + "VII");
    public static final RegistryObject<net.minecraft.world.item.Item> FinalSwordR8 = finalSwordRegister("finalsword_mk8", power(127), "§eRank: " + "VIII");
    public static final RegistryObject<net.minecraft.world.item.Item> FinalSwordR9 = finalSwordRegister("finalsword_mk9", power(511), "§eRank: " + "IX");
    public static final RegistryObject<net.minecraft.world.item.Item> FinalSwordRfinal = finalSwordRegister("finalsword_final", Double.MAX_VALUE, "§eRank: " + "§lFinal");

    private static RegistryObject<net.minecraft.world.item.Item> finalSwordRegister(String ID, double damage, String loreText) {
        return ITEMS.register(ID, () -> new finalSwordItem(damage, loreText));
    }
    private static double power(int power) {return Math.pow(2, power) - 2; }
}
