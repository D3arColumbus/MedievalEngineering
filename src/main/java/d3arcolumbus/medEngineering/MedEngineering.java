package d3arcolumbus.medEngineering;

import d3arcolumbus.medEngineering.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = MedEngineering.MODID, name = MedEngineering.MODNAME, version = MedEngineering.MODVERSION,/* dependencies = "required-after:forge@[11.16.0.1865,)",*/ useMetadata = true)
public class MedEngineering {

    public static final String MODID = "medengineering";
    public static final String MODNAME = "Medieval Engineering";
    public static final String MODVERSION= "0.0.1";

    @SidedProxy(clientSide = "d3arcolumbus.medEngineering.proxy.ClientProxy", serverSide = "d3arcolumbus.medEngineering.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static MedEngineering instance;
    //test
    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
