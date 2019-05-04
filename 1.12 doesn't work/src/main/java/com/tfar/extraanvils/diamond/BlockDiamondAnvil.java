package com.tfar.extraanvils.diamond;

import com.tfar.extraanvils.ExtraAnvils;
import com.tfar.extraanvils.ModAnvils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

public class BlockDiamondAnvil extends BlockFalling{

  public static final PropertyDirection FACING = BlockHorizontal.FACING;
  protected static final AxisAlignedBB X_AXIS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.125D, 1.0D, 1.0D, 0.875D);
  protected static final AxisAlignedBB Z_AXIS_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.0D, 0.875D, 1.0D, 1.0D);
  protected static final Logger LOGGER = LogManager.getLogger();

  public BlockDiamondAnvil(String name) {
    super(Material.ANVIL);
    setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    setLightOpacity(0);
    setCreativeTab(ExtraAnvils.creativeTab);
    setHardness(5.0F);
    setSoundType(SoundType.ANVIL);
    setResistance(2000.0F);
    setRegistryName(name);
    setTranslationKey(ExtraAnvils.MODID +":"+name);
  }

  @Override
  public boolean isFullCube(IBlockState state)
  {
    return false;
  }


  @Override
  public void onEndFalling(World worldIn, BlockPos pos, IBlockState p_176502_3_, IBlockState p_176502_4_) {
    worldIn.playEvent(1031, pos, 0);
  }

  /**
   * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
   * IBlockstate
   */
  @Override
  public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
  {
    EnumFacing enumfacing = placer.getHorizontalFacing().rotateY();

    try
    {
      return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(FACING, enumfacing);
    }
    catch (IllegalArgumentException var11)
    {
      if (!worldIn.isRemote)
      {
        LOGGER.warn(String.format("Invalid damage property for anvil at %s. Found %d, must be in [0, 1, 2]", pos, meta >> 2));

        if (placer instanceof EntityPlayer)
        {
          placer.sendMessage(new TextComponentTranslation("Invalid damage property. Please pick in [0, 1, 2]"));
        }
      }

      return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, 0, placer).withProperty(FACING, enumfacing);
    }
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
  {
    EnumFacing enumfacing = state.getValue(FACING);
    return enumfacing.getAxis() == EnumFacing.Axis.X ? X_AXIS_AABB : Z_AXIS_AABB;
  }

  /**
   * Called when the block is right clicked by a player.
   */
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
  {
    if (!worldIn.isRemote) {
      playerIn.openGui(ExtraAnvils.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
    }
    return true;
  }

  public boolean isOpaqueCube(IBlockState state)
  {
    return false;
  }


  @Nullable
  public static IBlockState damage(IBlockState state) {
    Block block = state.getBlock();
    if (block == ModAnvils.blockDiamondAnvil) {
      return ModAnvils.blockDiamondAnvilChipped.getDefaultState().withProperty(FACING, state.getValue(FACING));
    } else {
      return block == ModAnvils.blockDiamondAnvilChipped ? ModAnvils.blockDiamondAnvilDamaged.getDefaultState().withProperty(FACING, state.getValue(FACING)) : null;
    }
  }

  /**
   * Convert the given metadata into a BlockState for this Block
   */
  @Override
  public IBlockState getStateFromMeta(int meta) {
    return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta & 3));
  }

  /**
   * Convert the BlockState into the correct metadata value
   */
  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(FACING).getHorizontalIndex();
  }



  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, FACING);
  }

  @SideOnly(Side.CLIENT)
  public void registerModel() {
    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
  }
}