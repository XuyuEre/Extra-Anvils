package com.tfar.extraanvils.vanilla;

import com.tfar.extraanvils.ExtraAnvils;
import com.tfar.extraanvils.generic.BlockGenericAnvil;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VanillaAnvils {
  @GameRegistry.ObjectHolder("extraanvils:stone_anvil")
  public static BlockGenericAnvil blockStoneAnvil;

  @GameRegistry.ObjectHolder("extraanvils:stone_anvil_chipped")
  public static BlockGenericAnvil blockStoneAnvilChipped;

  @GameRegistry.ObjectHolder("extraanvils:stone_anvil_damaged")
  public static BlockGenericAnvil blockStoneAnvilDamaged;

  @GameRegistry.ObjectHolder("extraanvils:gold_anvil")
  public static BlockGenericAnvil blockGoldAnvil;

  @GameRegistry.ObjectHolder("extraanvils:gold_anvil_chipped")
  public static BlockGenericAnvil blockGoldAnvilChipped;

  @GameRegistry.ObjectHolder("extraanvils:gold_anvil_damaged")
  public static BlockGenericAnvil blockGoldAnvilDamaged;

  @GameRegistry.ObjectHolder(ExtraAnvils.MODID + ":diamond_anvil")
  public static BlockGenericAnvil blockDiamondAnvil;

  @GameRegistry.ObjectHolder(ExtraAnvils.MODID + ":diamond_anvil_chipped")
  public static BlockGenericAnvil blockDiamondAnvilChipped;

  @GameRegistry.ObjectHolder(ExtraAnvils.MODID + ":diamond_anvil_damaged")
  public static BlockGenericAnvil blockDiamondAnvilDamaged;
}
