package com.onlyex.naxtech.common.metatileentities.multi.electric;

import com.onlyex.naxtech.api.recipes.NTRecipeMaps;
import com.onlyex.naxtech.client.renderer.texture.NTTextures;
import com.onlyex.naxtech.common.block.NTMetaBlocks;
import com.onlyex.naxtech.common.block.blocks.BlockGlassCasing;
import com.onlyex.naxtech.common.block.blocks.BlockPackagingline;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiFluidHatch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class MetaTileEntityPackagingLine extends RecipeMapMultiblockController {

    public MetaTileEntityPackagingLine(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, NTRecipeMaps.PACKAGING_LINE_RECIPES);//8.30%
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityPackagingLine(metaTileEntityId);
    }


    @Nonnull
    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXXXXX", "PPPPPPP", "XXXXXXX","GGGGGGG", "#######")
                .aisle("XXXXXXX", "XCCCCCX", "XCCCCCX","AAAAAAA", "AAAAAAA")
                .aisle("XXXXXXX", "XCCCCCX", "XCCCCCX","AAAAAAA", "AAAAAAA")
                .aisle("XXXXXXX", "PPPPPPP", "XXXSXXX","GGGGGGG", "#######")
                .where('S', selfPredicate())
                .where('X',  states(getMCasingState())
                        .or(fluidInputPredicate())
                        .or(abilities(MultiblockAbility.INPUT_ENERGY)
                                .setMinGlobalLimited(1)
                                .setMaxGlobalLimited(3))
                        .or(autoAbilities(false, true, true, true, false, false, false)))
                .where('P',  states(getBWGlasState()))
                .where('C',  states(NTMetaBlocks.PACKAGING_LINE.getState(BlockPackagingline.MultiblockCasingType.PACKAGING_LINE_CASING)))
                .where('G',  states(MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)))
                .where('A',  states(getCCasingState()))
                .where('#', any())
                .build();
    }

    @Nonnull
    protected static IBlockState getMCasingState() {
        return NTMetaBlocks.PACKAGING_LINE.getState(BlockPackagingline.MultiblockCasingType.PACKAGING_LINE_MACHINE_CASING);
    }

    @Nonnull
    protected static IBlockState getCCasingState() {
        return NTMetaBlocks.PACKAGING_LINE.getState(BlockPackagingline.MultiblockCasingType.PACKAGING_LINE_CONTROL_CASING);
    }

    @Nonnull
    protected static IBlockState getBWGlasState() {
        return NTMetaBlocks.GLASS_CASING.getState(BlockGlassCasing.CasingType.TI_BORON_SILICATE_GLASS_BLOCK);
    }
    @Nonnull
    protected static TraceabilityPredicate fluidInputPredicate() {
            return metaTileEntities(MultiblockAbility.REGISTRY.get(MultiblockAbility.IMPORT_FLUIDS).stream()
                    .filter(mte -> !(mte instanceof MetaTileEntityMultiFluidHatch))
                    .toArray(MetaTileEntity[]::new))
                    .setMaxGlobalLimited(4);
    }
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
                return NTTextures.PACKAGING_LINE;
    }

}
