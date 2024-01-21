package com.onlyex.naxtech.common.metatileentities.research;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.onlyex.naxtech.api.capability.research.rwu.*;
import com.onlyex.naxtech.api.capability.research.IResearchHatch;

import gregtech.api.GTValues;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockPart;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetaTileEntityResearch  extends MetaTileEntityMultiblockPart
        implements IMultiblockAbilityPart<IResearchHatch>, IResearchHatch {


    public MetaTileEntityResearch(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTValues.UEV);
    }

    public SimpleOverlayRenderer getFrontOverlay() {
        return null;
    }

    public SimpleOverlayRenderer getFrontActiveOverlay() {
        return getFrontOverlay();
    }
    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {

    }
    @Override
    public MultiblockAbility<IResearchHatch> getAbility() {
        return null;
    }//TODO


    @Override
    public ICubeRenderer getBaseTexture() {
        return null;
    }//TODO

    @Override
    public int getDefaultPaintingColor() {
        return 0xFFFFFF;
    }

    @Override
    protected boolean openGUIOnRightClick() {
        return false;
    }//右键单击打开GUI TODO

    @Override
    public boolean canPartShare() {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, @NotNull List<String> tooltip,
                               boolean advanced) {
        if (isBridge()) {
            tooltip.add(I18n.format("naxtech.machine.research.bridge"));
        }

        final int upkeepEUt = getUpkeepEUt();
        final int maxEUt = getMaxEUt();
        if (upkeepEUt != 0 && upkeepEUt != maxEUt) {
            tooltip.add(I18n.format("naxtech.machine.research.upkeep_eut", upkeepEUt));
        }
        if (maxEUt != 0) {
            tooltip.add(I18n.format("naxtech.machine.research.max_eut", maxEUt));
        }

        if (this instanceof IRWUProvider provider) {
            tooltip.add(I18n.format("naxtech.machine.research.rwut", provider.getRWUPerTick()));
        }

        if (this instanceof IGORWUProvider provider) {
            tooltip.add(I18n.format("naxtech.machine.gooware.research.go_rwut", provider.getGORWUPerTick()));
        }

        if (this instanceof IOPRWUProvider provider) {
            tooltip.add(I18n.format("naxtech.machine.optical.research.op_rwut", provider.getOPRWUPerTick()));
        }

        if (this instanceof ISPRWUProvider provider) {
            tooltip.add(I18n.format("naxtech.machine.spintronic.research.sp_rwut", provider.getSPRWUPerTick()));
        }

        if (this instanceof ICORWUProvider provider) {
            tooltip.add(I18n.format("naxtech.machine.cosmic.research.co_rwut", provider.getCORWUPerTick()));
        }

        if (this instanceof ISCARWUProvider provider) {
            tooltip.add(I18n.format("naxtech.machine.supra_causal.research.sca_rwut", provider.getSCARWUPerTick()));
        }

        if (this instanceof ISCHRWUProvider provider) {
            tooltip.add(I18n.format("naxtech.machine.supra_chronal.research.sch_rwut", provider.getSCHRWUPerTick()));
        }

        if (this instanceof ISDIRWUProvider provider) {
            tooltip.add(I18n.format("naxtech.machine.supra_dimension.research.sdi_rwut", provider.getSDIRWUPerTick()));
        }
    }

    @Override
    public boolean showToolUsages() {
        return false;
    }


    @Override
    public int getUpkeepEUt() {
        return 0;
    }//TODO

    @Override
    public boolean isBridge() {
        return doesAllowBridging();
    }

    private boolean doesAllowBridging() {
        return false;
    }

    @Override
    public TextureArea getComponentIcon() {
        return null;
    }//TODO?

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return null;
    }//TODO?

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> dropsList, @Nullable EntityPlayer harvester){
//项目变体 TODO
    }
    @Override
    public void registerAbilities(List<IResearchHatch> abilityList) {
        abilityList.add(this);
    }
}
