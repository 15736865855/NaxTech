package com.onlyex.naxtech.common.metatileentities.hatch.research;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.onlyex.naxtech.api.capability.NTTileCapabilities;

import com.onlyex.naxtech.api.capability.hatch.research.suprachronal.ISCHResearchComputationHatch;
import com.onlyex.naxtech.api.capability.hatch.research.suprachronal.ISCHResearchComputationProvider;
import com.onlyex.naxtech.api.metatileentity.multiblock.NTMultiblockAbility;
import com.onlyex.naxtech.api.utils.NTLog;
import com.onlyex.naxtech.client.renderer.texture.NTTextures;

import com.onlyex.naxtech.common.pipelike.research.tile.TileEntitySCHResearchPipe;
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

public class MetaTileEntitySCHResearchComputationHatch extends MetaTileEntityMultiblockPart implements
                                            IMultiblockAbilityPart<ISCHResearchComputationHatch>, ISCHResearchComputationHatch {

    private final boolean isTransmitter;

    public MetaTileEntitySCHResearchComputationHatch(ResourceLocation metaTileEntityId, boolean isTransmitter) {
        super(metaTileEntityId, GTValues.ZPM);
        this.isTransmitter = isTransmitter;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntitySCHResearchComputationHatch(metaTileEntityId, isTransmitter);
    }

    @Override
    public boolean isTransmitter() {
        return this.isTransmitter;
    }

    @Override
    public int requestSCHRWUt(int schrwut, boolean simulate, @NotNull Collection<ISCHResearchComputationProvider> seenProviders) {
        seenProviders.add(this);
        var multiblockController = getController();

        if (multiblockController == null || !multiblockController.isStructureFormed()) {
            return 0; // 如果控制器为空或者未形成结构，则返回0
        }

        if (isTransmitter()) {
            // 向多方块控制器发送请求RWUt值的请求
            if (multiblockController instanceof ISCHResearchComputationProvider computationProvider) {
                return computationProvider.requestSCHRWUt(schrwut, simulate, seenProviders);
            } else {
                NTLog.logger.error("Computation Transmission Hatch could not request SCH-RWU/t from its controller!");
                return 0;
            }
        } else {
            // 尝试获取连接的发送器对象，如果存在则调用其方法
            ISCHResearchComputationProvider computationProvider = getResearchNetProvider();
            if (computationProvider != null) {
                return computationProvider.requestSCHRWUt(schrwut, simulate, seenProviders);
            }
        }

        return 0; // 默认返回0
    }

    @Override
    public int getMaxSCHRWUt(@NotNull Collection<ISCHResearchComputationProvider> seenProviders) {
        seenProviders.add(this);
        var multiblockController = getController();

        if (multiblockController == null || !multiblockController.isStructureFormed()) {
            return 0; // 如果控制器为空或者未形成结构，则返回0
        }

        if (isTransmitter()) {
            // 向多方块控制器发送获取最大RWUt值的请求
            if (multiblockController instanceof ISCHResearchComputationProvider computationProvider) {
                return computationProvider.getMaxSCHRWUt(seenProviders);
            } else {
                NTLog.logger.error("Computation Transmission Hatch could not get maximum SCH-RWU/t from its controller!");
                return 0;
            }
        } else {
            // 尝试获取连接的发送器对象，如果存在则调用其方法
            ISCHResearchComputationProvider computationProvider = getResearchNetProvider();
            if (computationProvider != null) {
                return computationProvider.getMaxSCHRWUt(seenProviders);
            }
        }

        return 0; // 默认返回0
    }

    @Override
    public boolean canBridge(@NotNull Collection<ISCHResearchComputationProvider> seen) {
        seen.add(this);

        var controller = getController();
        if (controller == null || !controller.isStructureFormed()) {
            // If the controller is not formed, return true to avoid problems in multi-structures
            return true;
        }

        if (isTransmitter()) {
            // If the hatch is a transmitter, ask the Multiblock controller to bridge
            if (controller instanceof ISCHResearchComputationProvider provider) {
                return provider.canBridge(seen);
            } else {
                NTLog.logger.error("Computation Transmission Hatch could not test bridge status of its controller!");
                return false;
            }
        } else {
            // If the hatch is not a transmitter, ask the attached Transmitter hatch (if it exists)
            ISCHResearchComputationProvider provider = getResearchNetProvider();
            if (provider == null) {
                // If no provider is found, don't report a problem and just pass quietly
                return true;
            }
            return provider.canBridge(seen);
        }
    }

    @Nullable
    private ISCHResearchComputationProvider getResearchNetProvider() {
        TileEntity tileEntity = getNeighbor(getFrontFacing());
        if (tileEntity == null) return null;

        if (tileEntity instanceof TileEntitySCHResearchPipe) {
            return tileEntity.getCapability(NTTileCapabilities.SCH_CABABILITY_RESEARCH_PROVIDER,
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
    public MultiblockAbility<ISCHResearchComputationHatch> getAbility() {
        return isTransmitter() ? NTMultiblockAbility.SCH_RESEARCH_COMPUTATION_DATA_TRANSMISSION :
                NTMultiblockAbility.SCH_RESEARCH_COMPUTATION_DATA_RECEPTION;
    }

    @Override
    public void registerAbilities(List<ISCHResearchComputationHatch> abilityList) {
        abilityList.add(this);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (side == getFrontFacing() && capability == NTTileCapabilities.SCH_CABABILITY_RESEARCH_PROVIDER) {
            return NTTileCapabilities.SCH_CABABILITY_RESEARCH_PROVIDER.cast(this);
        }
        return super.getCapability(capability, side);
    }
}
