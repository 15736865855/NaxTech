package com.onlyex.naxtech.common.pipelike.research.block;

import com.onlyex.naxtech.api.capability.NTTileCapabilities;
import com.onlyex.naxtech.client.renderer.pipe.ResearchPipeRenderer;
import com.onlyex.naxtech.common.CommonProxy;
import com.onlyex.naxtech.common.pipelike.research.ResearchPipeProperties;
import com.onlyex.naxtech.common.pipelike.research.ResearchPipeType;
import com.onlyex.naxtech.common.pipelike.research.item.ItemBlockCOResearchPipe;
import com.onlyex.naxtech.common.pipelike.research.net.WorldResearchPipeNet;

import com.onlyex.naxtech.common.pipelike.research.tile.TileEntityCOResearchPipe;
import gregtech.api.items.toolitem.ToolClasses;
import gregtech.api.items.toolitem.ToolHelper;
import gregtech.api.pipenet.block.BlockPipe;
import gregtech.api.pipenet.tile.IPipeTile;
import gregtech.api.pipenet.tile.TileEntityPipeBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockCOResearchPipe extends BlockPipe<ResearchPipeType, ResearchPipeProperties, WorldResearchPipeNet> {

    private final ResearchPipeType pipeType;
    private final ResearchPipeProperties properties;

    public BlockCOResearchPipe(@NotNull ResearchPipeType pipeType) {
        this.pipeType = pipeType;
        this.properties = ResearchPipeProperties.INSTANCE;
        setCreativeTab(CommonProxy.NAXTECH_TAB);
        setHarvestLevel(ToolClasses.WIRE_CUTTER, 1);
    }

    @Override
    protected Pair<TextureAtlasSprite, Integer> getParticleTexture(@NotNull World world, BlockPos blockPos) {
        return ResearchPipeRenderer.INSTANCE.getParticleTexture((TileEntityCOResearchPipe) world.getTileEntity(blockPos));
    }

    @Override
    public Class<ResearchPipeType> getPipeTypeClass() {
        return ResearchPipeType.class;
    }

    @Override
    public WorldResearchPipeNet getWorldPipeNet(World world) {
        return WorldResearchPipeNet.getWorldPipeNet(world);
    }

    @Override
    public TileEntityPipeBase<ResearchPipeType, ResearchPipeProperties> createNewTileEntity(boolean supportsTicking) {
        return new TileEntityCOResearchPipe();
    }

    @Override
    public ResearchPipeProperties createProperties(@NotNull IPipeTile<ResearchPipeType, ResearchPipeProperties> pipeTile) {
        ResearchPipeType pipeType = pipeTile.getPipeType();
        if (pipeType == null) return getFallbackType();
        return this.pipeType.modifyProperties(properties);
    }

    @Override
    public ResearchPipeProperties createItemProperties(@NotNull ItemStack itemStack) {
        if (itemStack.getItem() instanceof ItemBlockCOResearchPipe pipe) {
            return ((BlockCOResearchPipe) pipe.getBlock()).properties;
        }
        return null;
    }

    @Override
    public ItemStack getDropItem(IPipeTile<ResearchPipeType, ResearchPipeProperties> pipeTile) {
        return new ItemStack(this, 1, pipeType.ordinal());
    }

    @Override
    protected ResearchPipeProperties getFallbackType() {
        return ResearchPipeProperties.INSTANCE;
    }

    @Override
    public ResearchPipeType getItemPipeType(@NotNull ItemStack itemStack) {
        if (itemStack.getItem() instanceof ItemBlockCOResearchPipe pipe) {
            return ((BlockCOResearchPipe) pipe.getBlock()).pipeType;
        }
        return null;
    }

    @Override
    public void setTileEntityData(@NotNull TileEntityPipeBase<ResearchPipeType, ResearchPipeProperties> pipeTile,
                                  ItemStack itemStack) {
        pipeTile.setPipeData(this, pipeType);
    }

    @Override
    public void getSubBlocks(@NotNull CreativeTabs itemIn, @NotNull NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1, this.pipeType.ordinal()));
    }

    @Override
    protected boolean isPipeTool(@NotNull ItemStack stack) {
        return ToolHelper.isTool(stack, ToolClasses.WIRE_CUTTER);
    }

    @Override
    public boolean canPipesConnect(IPipeTile<ResearchPipeType, ResearchPipeProperties> selfTile, EnumFacing side,
                                   IPipeTile<ResearchPipeType, ResearchPipeProperties> sideTile) {
        return selfTile instanceof TileEntityCOResearchPipe && sideTile instanceof TileEntityCOResearchPipe;
    }

    @Override
    public boolean canPipeConnectToBlock(IPipeTile<ResearchPipeType, ResearchPipeProperties> selfTile, EnumFacing side,
                                         @Nullable TileEntity tile) {
        if (tile == null) return false;
        //if (tile.hasCapability(NTTileCapabilities.CAPABILITY_DATA_ACCESS, side.getOpposite())) return true;
        return tile.hasCapability(NTTileCapabilities.CABABILITY_RESEARCH_PROVIDER, side.getOpposite());
    }

    @Override
    public boolean isHoldingPipe(EntityPlayer player) {
        if (player == null) {
            return false;
        }
        ItemStack stack = player.getHeldItemMainhand();
        return stack != ItemStack.EMPTY && stack.getItem() instanceof ItemBlockCOResearchPipe;
    }

    @Override
    @NotNull
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("deprecation")
    public EnumBlockRenderType getRenderType(@NotNull IBlockState state) {
        return ResearchPipeRenderer.INSTANCE.getBlockRenderType();
    }
}
