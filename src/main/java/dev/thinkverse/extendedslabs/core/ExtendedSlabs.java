package dev.thinkverse.extendedslabs.core;

import dev.thinkverse.extendedslabs.util.CreativeTab;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Optional;

@Mod(ExtendedSlabs.MODID)
public class ExtendedSlabs
{
    public static final String MODID = "extendedslabs";
    public static final String VERSION = "0.6.1-beta";

    public static CommonProxy PROXY = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new CommonProxy());

    public static final ItemGroup GROUP = new CreativeTab();

    public ExtendedSlabs() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::OnLoadComplete);
    }

    private void OnLoadComplete(final FMLLoadCompleteEvent event) {
        PROXY.init();
    }

    public static String getVersion(boolean correctInDev) {
        Optional<? extends ModContainer> o = ModList.get().getModContainerById(MODID);
        if (o.isPresent()) {
            String str = o.get().getModInfo().getVersion().toString();
            if (correctInDev && "NONE".equals(str))
                return VERSION;
            return str;
        }
        return "0.0.0";
    }

    public static boolean isDevBuild() {
        return "NONE".equals(getVersion(false));
    }

    public static ResourceLocation location(String name) {
        return new ResourceLocation(MODID, name);
    }
}
