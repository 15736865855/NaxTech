package com.onlyex.naxtech.common.metatileentities.hatch.research;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.onlyex.naxtech.api.capability.NTTileCapabilities;
import com.onlyex.naxtech.api.capability.hatch.research.cosmic.ICOResearchComputationHatch;
import com.onlyex.naxtech.api.capability.hatch.research.cosmic.ICOResearchComputationProvider;
import com.onlyex.naxtech.api.metatileentity.multiblock.NTMultiblockAbility;
import com.onlyex.naxtech.api.utils.NTLog;
import com.onlyex.naxtech.client.renderer.texture.NTTextures;

import com.onlyex.naxtech.common.pipelike.research.tile.TileEntityCOResearchPipe;
import gregtech.api.GTValues;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class MetaTileEntityCOResearchComputationHatch extends MetaTileEntityMultiblockPart implements
                                            IMultiblockAbilityPart<ICOResearchComputationHatch>, ICOResearchComputationHatch {

    private final boolean isTransmitter;

    public MetaTileEntityCOResearchComputationHatch(ResourceLocation metaTileEntityId, boolean isTransmitter) {
        super(metaTileEntityId, GTValues.ZPM);
        this.isTransmitter = isTransmitter;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityCOResearchComputationHatch(metaTileEntityId, isTransmitter);
    }

    @Override
    public boolean isTransmitter() {
        return this.isTransmitter;
    }

    @Override
    public int requestCORWUt(int corwut, boolean simulate, @NotNull Collection<ICOResearchComputationProvider> seenProviders) {
        seenProviders.add(this);
        var multiblockController = getController();

        if (multiblockController == null || !multiblockController.isStructureFormed()) {
            return 0; // 如果控制器为空或者未形成结构，则返回0
        }

        if (isTransmitter()) {
            // 向多方块控制器发送请求RWUt值的请求
            if (multiblockController instanceof ICOResearchComputationProvider computationProvider) {
                return computationProvider.requestCORWUt(corwut, simulate, seenProviders);
            } else {
                NTLog.logger.error("Computation Transmission Hatch could not request CO-RWU/t from its controller!");
                return 0;
            }
        } else {
            // 尝试获取连接的发送器对象，如果存在则调用其方法
            ICOResearchComputationProvider computationProvider = getResearchNetProvider();
            if (computationProvider != null) {
                return computationProvider.requestCORWUt(corwut, simulate, seenProviders);
            }
        }

        return 0; // 默认返回0
    }

    @Override
    public int getMaxCORWUt(@NotNull Collection<ICOResearchComputationProvider> seenProviders) {
        seenProviders.add(this);
        var multiblockController = getController();

        if (multiblockController == null || !multiblockController.isStructureFormed()) {
            return 0; // 如果控制器为空或者未形成结构，则返回0
        }

        if (isTransmitter()) {
            // 向多方块控制器发送获取最大RWUt值的请求
            if (multiblockController instanceof ICOResearchComputationProvider computationProvider) {
                return computationProvider.getMaxCORWUt(seenProviders);
            } else {
                NTLog.logger.error("Computation Transmission Hatch could not get maximum CO-RWU/t from its controller!");
                return 0;
            }
        } else {
            // 尝试获取连接的发送器对象，如果存在则调用其方法
            ICOResearchComputationProvider computationProvider = getResearchNetProvider();
            if (computationProvider != null) {
                return computationProvider.getMaxCORWUt(seenProviders);
            }
        }

        return 0; // 默认返回0
    }

    @Override
    public boolean canBridge(@NotNull Collection<ICOResearchComputationProvider> seen) {
        seen.add(this);

        var controller = getController();
        if (controller == null || !controller.isStructureFormed()) {
            // If the controller is not formed, return true to avoid problems in multi-structures
            return true;
        }

        if (isTransmitter()) {
            // If the hatch is a transmitter, ask the Multiblock controller to bridge
            if (controller instanceof ICOResearchComputationProvider provider) {
                return provider.canBridge(seen);
            } else {
                NTLog.logger.error("Computation Transmission Hatch could not test bridge status of its controller!");
                return false;
            }
        } else {
            // If the hatch is not a transmitter, ask the attached Transmitter hatch (if it exists)
            ICOResearchComputationProvider provider = getResearchNetProvider();
            if (provider == null) {
                // If no provider is found, don't report a problem and just pass quietly
                return true;
            }
            return provider.canBridge(seen);
        }
    }

    @Nullable
    private ICOResearchComputationProvider getResearchNetProvider() {
        TileEntity tileEntity = getNeighbor(getFrontFacing());
        if (tileEntity == null) return null;

        if (tileEntity instanceof TileEntityCOResearchPipe) {
            return tileEntity.getCapability(NTTileCapabilities.CO_CABABILITY_RESEARCH_PROVIDER,
                    getFrontFacing().getOpposite());
        }
        return null;
    }

    @Override
    protected boolean openGUIOnRightClick() {
        return false;
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public boolean canPartShare() {
        return false;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        if (shouldRenderOverlay()) {
            // TODO 纹理
            NTTextures.RESEARCH_DATA_ACCESS_HATCH.renderSided(getFrontFacing(), renderState, translation, pipeline);
        }
    }

    @Override
    public MultiblockAbility<ICOResearchComputationHatch> getAbility() {
        return isTransmitter() ? NTMultiblockAbility.CO_RESEARCH_COMPUTATION_DATA_TRANSMISSION :
                NTMultiblockAbility.CO_RESEARCH_COMPUTATION_DATA_RECEPTION;
    }

    @Override
    public void registerAbilities(List<ICOResearchComputationHatch> abilityList) {
        abilityList.add(this);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (side == getFrontFacing() && capability == NTTileCapabilities.CO_CABABILITY_RESEARCH_PROVIDER) {
            return NTTileCapabilities.CO_CABABILITY_RESEARCH_PROVIDER.cast(this);
        }
        return super.getCapability(capability, side);
    }
}
