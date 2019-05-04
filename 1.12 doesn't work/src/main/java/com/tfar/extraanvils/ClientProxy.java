package com.tfar.extraanvils;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT,modid = ExtraAnvils.MODID)
public class ClientProxy extends CommonProxy {

  @SubscribeEvent
  public static void registerModels(ModelRegistryEvent event) {
    ModAnvils.initModels();
  }
}
